package com.gree.grih.datstore;

import java.util.Properties;

/**
 * BoltBuilder
 * Created by wander on 21th.Apr.2017
 */
public class BoltBuilder {

    public Properties config;

    public BoltBuilder(Properties config) {
        this.config = config;
    }

    public SoutBolt buildSoutBolt() {
        return new SoutBolt();
    }
}
