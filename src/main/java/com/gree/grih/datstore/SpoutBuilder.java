package com.gree.grih.datstore;

import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;

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

        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        return kafkaSpout;
    }
}
