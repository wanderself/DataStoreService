package com.gree.grih.datstore;

import com.gree.grih.datstore.bolt.BoltBuilder;
import com.gree.grih.datstore.bolt.ProcedureBolt;
import com.gree.grih.datstore.bolt.RedisBolt;
import com.gree.grih.datstore.bolt.SoutBolt;
import com.gree.grih.datstore.conf.Configurer;
import com.gree.grih.datstore.spout.SpoutBuilder;
import org.apache.log4j.Logger;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.topology.TopologyBuilder;

import java.io.IOException;
import java.util.Properties;

/**
 * MainTopology
 * Created by root on 17th.Apr.2017
 */
public class MainTopology {

    private static Logger LOG = Logger.getLogger(MainTopology.class);
    private Properties configs;
    private SpoutBuilder spoutBuilder;
    private BoltBuilder boltBuilder;


    private MainTopology(String configFileName) {
        configs = new Properties();

        try {
            configs.load(MainTopology.class.getResourceAsStream("/" + configFileName));
            boltBuilder = new BoltBuilder(configs);
            spoutBuilder = new SpoutBuilder(configs);
        } catch (IOException e) {
            LOG.error("Failed to load configFile!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainTopology mainTopology = new MainTopology("config.properties");
        mainTopology.submitTopology(args);

    }

    private void submitTopology(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        KafkaSpout kafkaSpout = spoutBuilder.buildKafkaSpout();
        SoutBolt soutBolt = boltBuilder.buildSoutBolt();
        ProcedureBolt procedureBolt = boltBuilder.buildRedisBolt();

        String kafkaSpoutID = configs.getProperty(Configurer.KAFKA_SPOUT_ID);
        int kafkaSpoutCount = Integer.parseInt(configs.getProperty(Configurer.KAFKA_SPOUT_COUNT));
        builder.setSpout(kafkaSpoutID, kafkaSpout, kafkaSpoutCount);

        String soutBoltID = configs.getProperty(Configurer.HBASE_BOLT_ID);
        int soutBoltCount = Integer.parseInt(configs.getProperty(Configurer.HBASE_BOLT_COUNT));
        builder.setBolt(soutBoltID, soutBolt, soutBoltCount).shuffleGrouping(kafkaSpoutID);

        String procedureBoltID = configs.getProperty(Configurer.MYSQL_BOLT_ID);
        int procedureBoltCount = Integer.parseInt(configs.getProperty(Configurer.MYSQL_BOLT_COUNT));
        builder.setBolt(procedureBoltID, procedureBolt, procedureBoltCount).shuffleGrouping(soutBoltID);

        Config conf = new Config();
        conf.setDebug(false);

        String topologyName = configs.getProperty(Configurer.TOPOLOGY_NAME);

        if (args != null && args.length > 0) {
            LOG.info("----------   Starting CloudCluster !   ----------  ");
            conf.setNumWorkers(1);
            try {
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        } else {
            LOG.info("----------   Starting LocalCluster !   ----------  ");
            conf.setMaxTaskParallelism(1);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology(topologyName, conf, builder.createTopology());
//            cluster.shutdown();
        }
    }

}
