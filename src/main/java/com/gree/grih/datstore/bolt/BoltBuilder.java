package com.gree.grih.datstore.bolt;

import java.util.Properties;

/**
 * BoltBuilder
 * Created by wander on 21th.Apr.2017
 */
public class BoltBuilder {

    private Properties config;

    public BoltBuilder(Properties config) {
        this.config = config;
    }

    public SoutBolt buildSoutBolt() {
        return new SoutBolt(config);
    }
}
