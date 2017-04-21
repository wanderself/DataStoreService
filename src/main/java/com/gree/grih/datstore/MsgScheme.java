package com.gree.grih.datstore;

import org.apache.log4j.Logger;
import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;


import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by root on 4/21/17.
 */
public class MsgScheme implements Scheme {
    private static final Logger LOGGER = Logger.getLogger(MsgScheme.class);

    @Override
    public List<Object> deserialize(ByteBuffer ser) {
        try {
            String msg = new String(ser.array(), "UTF-8");
            return new Values(msg);
        } catch (Exception e) {
            LOGGER.error("Cannot parse the provided message!");
        }

        return null;
    }


    @Override
    public Fields getOutputFields() {
        return new Fields("msg");
    }
}