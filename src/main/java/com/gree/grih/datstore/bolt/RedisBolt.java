package com.gree.grih.datstore.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;
import java.util.Properties;

/**
 * RedisBolt
 * Created by root on 12th.May.2017
 */
public class RedisBolt extends BaseRichBolt {

    private OutputCollector outputCollector;
    private Properties config;

    public RedisBolt(Properties config) {
        this.config = config;
    }


    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    public void execute(Tuple tuple) {
        System.out.println(tuple.toString());
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("msg"));
    }
}
