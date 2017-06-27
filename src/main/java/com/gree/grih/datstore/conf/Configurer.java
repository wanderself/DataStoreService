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
    public static final String KAFKA_TOPIC_KEY = "kafa.topic.key";
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
    public static final String REALTIME_FAMILY_LASTROWKEY = "hbase.realtime.family.lastRowKey";
    public static final String HBASE_TABLE_HISTORY = "hbase.table.history";

    public static final String MYSQL_BOLT_ID = "mysql.bolt.id";
    public static final String MYSQL_BOLT_COUNT = "mysql.bolt.count";

    //mysql
    public static final String MYSQL_DRIVER = "mysql.driver";
    public static final String MYSQL_WRITE_URL = "mysql.write.url";
    public static final String MYSQL_WRITE_USER = "mysql.write.user";
    public static final String MYSQL_WRITE_PASSWD = "mysql.write.password";
    public static final String MYSQL_WRITE_MINPOOLSIZE = "mysql.write.MinPoolSize";
    public static final String MYSQL_WRITE_ACQUIREINCREMENT = "mysql.write.AcquireIncrement";
    public static final String MYSQL_WRITE_MAXPOOLSIZE = "mysql.write.MaxPoolSize";
    public static final String MYSQL_WRITE_MAXIDLETIME = "mysql.write.MaxIdleTime";
    public static final String MYSQL_READ_URL = "mysql.read.url";
    public static final String MYSQL_READ_USER = "mysql.read.user";
    public static final String MYSQL_READ_PASSWD = "mysql.read.password";
    public static final String MYSQL_READ_MINPOOLSIZE = "mysql.read.MinPoolSize";
    public static final String MYSQL_READ_ACQUIREINCREMENT = "mysql.read.AcquireIncrement";
    public static final String MYSQL_READ_MAXPOOLSIZE = "mysql.read.MaxPoolSize";
    public static final String MYSQL_READ_MAXIDLETIME = "mysql.read.MaxIdleTime";

}
