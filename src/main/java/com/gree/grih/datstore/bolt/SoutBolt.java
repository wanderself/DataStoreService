package com.gree.grih.datstore.bolt;

import com.google.gson.Gson;
import com.gree.grih.datstore.jsonsBean.AirConData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.io.IOException;

/**
 * SoutBolt
 * Created by root on 17th.Apr.2017
 */
public class SoutBolt extends BaseBasicBolt {

    private static Logger LOG = Logger.getLogger(SoutBolt.class);
    public static Configuration HBASE_CONFIG = new Configuration();

    static {
        HBASE_CONFIG = HBaseConfiguration.create();
        HBASE_CONFIG.addResource(new Path("/src/main/resources/hbase-site.xml"));
    }

    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        System.out.println(tuple.getString(0));
        LOG.info(tuple.toString());

        AirConData data = decode(tuple.getString(0));

        insert(data);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }

    public AirConData decode(String jstr) {
        Gson gson = new Gson();
        AirConData data = gson.fromJson(jstr, AirConData.class);
        return data;
    }

    public void insert(AirConData data) {

        System.out.println(data.ctime);
        System.out.println(data.mac);
        System.out.println(data.rowKey);
        System.out.println(data.svrCtime);

        Connection connection = null;
        try {
            System.setProperty("HADOOP_USER_NAME", "greejsj");

            connection = ConnectionFactory.createConnection(HBASE_CONFIG);
            Table table = connection.getTable(TableName.valueOf("GRIH:TEST"));


            Put put = new Put(Bytes.toBytes(data.rowKey));
            put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("mac"), Bytes.toBytes(data.mac));
            put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("time"), Bytes.toBytes(data.ctime));
            put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("name"), Bytes.toBytes("CtlSend"));
            put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("age"), Bytes.toBytes("15"));
            put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("addy"), Bytes.toBytes("Zhuhai"));
            put.addColumn(Bytes.toBytes("DispatchSys"), Bytes.toBytes("mac"), Bytes.toBytes(data.mac));
            put.addColumn(Bytes.toBytes("DispatchSys"), Bytes.toBytes("time"), Bytes.toBytes(data.ctime));
            put.addColumn(Bytes.toBytes("DispatchSys"), Bytes.toBytes("barCode"), Bytes.toBytes(data.DevInfoRes.tiaoma));
            put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("mac"), Bytes.toBytes(data.mac));
            put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("time"), Bytes.toBytes(data.ctime));
            put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("name"), Bytes.toBytes("InnerStatus"));

            table.put(put);

            table.close();
            connection.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
