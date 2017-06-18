package com.gree.grih.datstore.spout;

import org.apache.storm.kafka.StringKeyValueScheme;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * MsgScheme
 * Created by root on 17th.Apr.2017
 */
public class KafkaKeyMsgScheme extends StringKeyValueScheme {

    public KafkaKeyMsgScheme() {
        super();
    }

    @Override
    public List<Object> deserializeKeyAndValue(ByteBuffer key, ByteBuffer value) {
        if (key == null) {
            return deserialize(value);
        }
        String keyString = StringScheme.deserializeString(key);
        String valueString = StringScheme.deserializeString(value);
        return new Values(keyString, valueString);
    }

    @Override
    public List<Object> deserialize(ByteBuffer bytes) {
        return super.deserialize(bytes);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("key", "value");
    }
}