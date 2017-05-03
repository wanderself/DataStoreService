package com.gree.grih.datstore.spout;

import org.apache.storm.kafka.StringKeyValueScheme;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.tuple.Fields;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * MsgScheme
 * Created by root on 17th.Apr.2017
 */
public class KafkaKeyMsgScheme extends StringKeyValueScheme {

    public List<Object> deserializeKeyAndValue(ByteBuffer key, ByteBuffer value) {
        if (key == null) {
            return deserialize(value);
        }
        String keyString = StringScheme.deserializeString(key);
        String valueString = StringScheme.deserializeString(value);
        ArrayList<Object> tuple = new ArrayList<Object>();
        tuple.add(keyString);
        tuple.add(valueString);
        return tuple;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("key", "value");
    }
}