package com.gree.grih.datstore.conf;

/**
 * Configurer
 * Created by root on 03th.May.2017.
 */

public class Configurer {
    //topology name
    public static final String TOPOLOGY_NAME = "topology";

    //kafka spout
    public static final String KAFKA_SPOUT_ID = "kafkaspout.id";
    public static final String KAFKA_ZKHOST = "kafka.zkHost";
    public static final String KAFKA_ZKROOT = "kafka.zkRoot";
    public static final String KAFKA_TOPIC = "kafa.topic";
    public static final String KAFKA_CLIENT_ID = "kafka.clientId";
    public static final String KAFKA_SPOUT_COUNT = "kafkaspout.count";

    //hbase bolt
    public static final String HBASE_BOLT_ID = "hbasebolt.id";
    public static final String HBASE_BOLT_COUNT = "hbasebolt.count";

    //zookeeper servers
    public static final String ZK_SERVER1 = "zkServer1";
    public static final String ZK_SERVER2 = "zkServer2";
    public static final String ZK_SERVER3 = "zkServer3";
    public static final String ZK_PORT = "zkPort";

    //Hbase configuration
    public static final String HBASE_TABLE_REALTIME = "hbase.table.realtime";
    public static final String HBASE_TABLE_HISTORY = "hbase.table.history";
}
