package com.gree.grih.datstore.spout;

import org.apache.log4j.Logger;
import org.apache.storm.kafka.KeyValueScheme;
import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * MsgScheme
 * Created by root on 17th.Apr.2017
 */
public class MsgScheme implements KeyValueScheme {

    public List<Object> deserializeKeyAndValue(ByteBuffer key, ByteBuffer value) {
        return null;
    }

    public List<Object> deserialize(ByteBuffer ser) {
        return null;
    }

    public Fields getOutputFields() {
        return null;
    }
}