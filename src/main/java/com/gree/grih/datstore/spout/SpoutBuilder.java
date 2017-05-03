package com.gree.grih.datstore.spout;

import com.gree.grih.datstore.conf.Configurer;
import org.apache.storm.kafka.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;

/**
 * SpoutBuilder
 * Created by wander on 19th.Apr.2017
 */
public class SpoutBuilder {

    private Properties config;

    public SpoutBuilder(Properties config) {
        this.config = config;
    }

    public KafkaSpout buildKafkaSpout() {

        BrokerHosts brokerHosts = new ZkHosts(config.getProperty(Configurer.KAFKA_ZKHOST));
        String topic = config.getProperty(Configurer.KAFKA_TOPIC);
        String zkRoot = config.getProperty(Configurer.KAFKA_ZKROOT);
        String clientId = config.getProperty(Configurer.KAFKA_CLIENT_ID);
        List<String> zkServers = new ArrayList<String>(asList(config.getProperty(Configurer.ZK_SERVER1), config.getProperty(Configurer.ZK_SERVER2), config.getProperty(Configurer.ZK_SERVER3)));

        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, zkRoot, clientId);
        spoutConfig.scheme = new KeyValueSchemeAsMultiScheme(new KafkaKeyMsgScheme());
        spoutConfig.zkServers = zkServers;
        spoutConfig.zkPort = Integer.valueOf(config.getProperty(Configurer.ZK_PORT));
        spoutConfig.ignoreZkOffsets = false;
        spoutConfig.bufferSizeBytes = 1024;
        spoutConfig.fetchMaxWait = 1;
        spoutConfig.fetchSizeBytes = 1024 * 100;
        spoutConfig.stateUpdateIntervalMs = 1000;

        return new KafkaSpout(spoutConfig);
    }
}
