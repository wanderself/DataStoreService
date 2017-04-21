package com.gree.grih.datstore;

import org.apache.log4j.Logger;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/**
 * com.gree.grih.datstore
 * Created by root on 17th.Apr.2017
 */
public class SoutBolt extends BaseBasicBolt {

    private static Logger LOG = Logger.getLogger(SoutBolt.class);

    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        System.out.println(tuple.getString(0));
        LOG.info(tuple.getString(0));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }
}
