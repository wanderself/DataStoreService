package com.gree.grih.datstore;

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
 * com.gree.grih.datstore
 * Created by root on 17th.Apr.2017
 */
public class MainTopology {

    private static Logger LOG = Logger.getLogger(MainTopology.class);
    public Properties configs;
    public SpoutBuilder spoutBuilder;
    public BoltBuilder boltBuilder;


    public static void main(String[] args) {
        MainTopology mainTopology = new MainTopology("config.properties");
        mainTopology.submitTopology(args);

    }


    public MainTopology(String configFileName) {
        configs = new Properties();

        try {
            configs.load(MainTopology.class.getResourceAsStream(configFileName));
            boltBuilder = new BoltBuilder(configs);
            spoutBuilder = new SpoutBuilder(configs);
        } catch (IOException e) {
            LOG.error("Failed to load configFile!");
            e.printStackTrace();
        }
    }


    private void submitTopology(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        KafkaSpout kafkaSpout = spoutBuilder.buildKafkaSpout();
        SoutBolt soutBolt = boltBuilder.buildSoutBolt();

        int kafkaSpoutCount = Integer.parseInt(configs.getProperty(Configurer.KAFKA_SPOUT_COUNT));
        builder.setSpout(configs.getProperty(Configurer.KAFKA_SPOUT_ID), kafkaSpout, kafkaSpoutCount);

        int soutBoltCount = Integer.parseInt(configs.getProperty(Configurer.HBASE_BOLT_COUNT));
        builder.setBolt(configs.getProperty(Configurer.HBASE_BOLT_ID), soutBolt, soutBoltCount);

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
            cluster.shutdown();
        }
    }

}


//        try {

//            String kafkaZk = "127.0.0.1:2181";
//            BrokerHosts brokerHosts = new ZkHosts(kafkaZk);
//            String topic = "incoming";
//            String zkRoot = "/storm";
//            List<String> zkHost = new ArrayList<String>();
//            zkHost.add("127.0.0.1");
//            String spoutId = "spoutId";
//
//            SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, zkRoot, spoutId);
//            spoutConfig.scheme = new SchemeAsMultiScheme(new MsgScheme());
//            spoutConfig.zkServers = zkHost;
//            spoutConfig.zkPort = 2181;
//            spoutConfig.ignoreZkOffsets = false;
//            spoutConfig.bufferSizeBytes = 1024;
//            spoutConfig.fetchMaxWait = 1;
//            spoutConfig.fetchSizeBytes = 1024 * 100;
//            spoutConfig.stateUpdateIntervalMs = 1000;


//            TopologyBuilder builder = new TopologyBuilder();
//            builder.setSpout("kafka-spout", new KafkaSpout(spoutConfig), 1);
//            builder.setBolt("print-messages", new SoutBolt()).shuffleGrouping("kafka-spout");
//
//            Config config = new Config();
//            config.setDebug(false);
//
//            StormTopology stormTopology = builder.createTopology();
//
//            if (args != null && args.length > 0) {
//                LOG.info("----------   Starting CloudCluster !   ----------  ");
//                config.setNumWorkers(1);
//                StormSubmitter.submitTopology(args[0], config, stormTopology);
//            } else {
//                LOG.info("----------   Starting LocalCluster !   ----------  ");
//                config.setMaxTaskParallelism(1);
//                LocalCluster cluster = new LocalCluster();
//                cluster.submitTopology("Simple", config, stormTopology);
////                cluster.shutdown();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }