package com.gree.grih.datstore;

/**
 * Created by root on 4/21/17.
 */
public class Configurer {
    //topology name
    public static final String TOPOLOGY_NAME = "topology";

    //kafka spout
    public static final String KAFKA_SPOUT_ID = "kafka-spout.id";
    public static final String KAFKA_ZKHOST = "kafka.zkHost";
    public static final String KAFKA_ZKROOT = "kafka.zkRoot";
    public static final String KAFKA_TOPIC = "kafa.topic";
    public static final String KAFKA_CLIENT_ID = "kafka.clientId";
    public static final String KAFKA_SPOUT_COUNT = "kafkaspout.count";

    //hbase bolt
    public static final String HBASE_BOLT_ID = "hbase-bolt";
    public static final String HBASE_BOLT_COUNT = "hbasebolt.count";
}
