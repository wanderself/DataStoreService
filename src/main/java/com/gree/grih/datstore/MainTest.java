package com.gree.grih.datstore;

import com.gree.grih.datstore.bolt.SoutBolt;
import org.apache.log4j.Logger;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Main
 * Created by root on 17th.Apr.2017
 */
public class MainTest {

    private Properties configs;
    private static Logger LOG = Logger.getLogger(MainTest.class);

    public static void main(String[] args) {

        try {

            String kafkaZk = "127.0.0.1:2181";
            BrokerHosts brokerHosts = new ZkHosts(kafkaZk);
            String topic = "incoming";
            String zkRoot = "/storm";
            List<String> zkHost = new ArrayList<String>();
            zkHost.add("127.0.0.1");
            String spoutId = "spoutId";

            SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, zkRoot, spoutId);
            spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
            spoutConfig.zkServers = zkHost;
            spoutConfig.zkPort = 2181;
            spoutConfig.ignoreZkOffsets = false;
            spoutConfig.bufferSizeBytes = 1024;
            spoutConfig.fetchMaxWait = 1;
            spoutConfig.fetchSizeBytes = 1024 * 100;
            spoutConfig.stateUpdateIntervalMs = 1000;


            TopologyBuilder builder = new TopologyBuilder();
            builder.setSpout("kafka-spout", new KafkaSpout(spoutConfig), 1);
            builder.setBolt("print-messages", new SoutBolt()).shuffleGrouping("kafka-spout");

            Config config = new Config();
            config.setDebug(false);

            StormTopology stormTopology = builder.createTopology();

            if (args != null && args.length > 0) {
                LOG.info("----------   Starting CloudCluster !   ----------  ");
                config.setNumWorkers(1);
                StormSubmitter.submitTopology(args[0], config, stormTopology);
            } else {
                LOG.info("----------   Starting LocalCluster !   ----------  ");
                config.setMaxTaskParallelism(1);
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("Simple", config, stormTopology);
//                cluster.shutdown();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
