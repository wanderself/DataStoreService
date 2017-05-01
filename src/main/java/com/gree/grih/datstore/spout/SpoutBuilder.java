package com.gree.grih.datstore.spout;

import com.gree.grih.datstore.conf.Configurer;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * SpoutBuilder
 * Created by wander on 19th.Apr.2017
 */
public class SpoutBuilder {

    public Properties config;

    public SpoutBuilder(Properties config) {
        this.config = config;
    }

    public KafkaSpout buildKafkaSpout() {

        BrokerHosts brokerHosts = new ZkHosts(config.getProperty(Configurer.KAFKA_ZKHOST));
        String topic = config.getProperty(Configurer.KAFKA_TOPIC);
        String zkRoot = config.getProperty(Configurer.KAFKA_ZKROOT);
        String clientId = config.getProperty(Configurer.KAFKA_CLIENT_ID);
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, zkRoot, clientId);
        List<String> zkServers = new ArrayList<String>();
        zkServers.add("10.2.5.202");
        zkServers.add("10.2.5.203");
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringKeyValueScheme());
        spoutConfig.zkServers = zkServers;
        spoutConfig.zkPort = 2181;
        spoutConfig.ignoreZkOffsets = false;
        spoutConfig.bufferSizeBytes = 1024;
        spoutConfig.fetchMaxWait = 1;
        spoutConfig.fetchSizeBytes = 1024 * 100;
        spoutConfig.stateUpdateIntervalMs = 1000;
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        return kafkaSpout;
    }
}
