package com.gree.grih.datstore.db.mysql.adapter;

import com.gree.grih.datstore.conf.Configurer;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DataSource
 * Created by root on 06th.May.2017
 */
public class DataSource {

    private static DataSource dataSource;
    private ComboPooledDataSource readDataSource;
    private ComboPooledDataSource writeDataSource;

    private DataSource(Properties config) {

        readDataSource = new ComboPooledDataSource();
        writeDataSource = new ComboPooledDataSource();

        try {
            readDataSource.setDriverClass(config.getProperty(Configurer.MYSQL_DRIVER));
            readDataSource.setJdbcUrl(config.getProperty(Configurer.MYSQL_READ_URL));
            readDataSource.setUser(config.getProperty(Configurer.MYSQL_READ_USER));
            readDataSource.setPassword(config.getProperty(Configurer.MYSQL_READ_PASSWD));

            readDataSource.setMinPoolSize(Integer.parseInt(config.getProperty(Configurer.MYSQL_READ_MINPOOLSIZE)));
            readDataSource.setMaxPoolSize(Integer.parseInt(config.getProperty(Configurer.MYSQL_READ_MAXPOOLSIZE)));
            readDataSource.setMaxIdleTime(Integer.parseInt(config.getProperty(Configurer.MYSQL_READ_MAXIDLETIME)));
            readDataSource.setAcquireIncrement(Integer.parseInt(config.getProperty(Configurer.MYSQL_READ_ACQUIREINCREMENT)));


            writeDataSource.setDriverClass(config.getProperty(Configurer.MYSQL_DRIVER));
            writeDataSource.setJdbcUrl(config.getProperty(Configurer.MYSQL_WRITE_URL));
            writeDataSource.setUser(config.getProperty(Configurer.MYSQL_WRITE_USER));
            writeDataSource.setPassword(config.getProperty(Configurer.MYSQL_WRITE_PASSWD));

            writeDataSource.setMinPoolSize(Integer.parseInt(config.getProperty(Configurer.MYSQL_WRITE_MINPOOLSIZE)));
            writeDataSource.setMaxPoolSize(Integer.parseInt(config.getProperty(Configurer.MYSQL_WRITE_MAXPOOLSIZE)));
            writeDataSource.setMaxIdleTime(Integer.parseInt(config.getProperty(Configurer.MYSQL_WRITE_MAXIDLETIME)));
            writeDataSource.setAcquireIncrement(Integer.parseInt(config.getProperty(Configurer.MYSQL_WRITE_ACQUIREINCREMENT)));

        } catch (PropertyVetoException e) {
            e.printStackTrace();
            System.err.println("DataSource: " + e.getMessage());
        }

    }

    public static DataSource getInstance(Properties config) {

        if (dataSource == null) {
            dataSource = new DataSource(config);
        }
        return dataSource;
    }

    public Connection getReadConnection() throws SQLException {
        return readDataSource.getConnection();
    }

    public Connection getWriteConnection() throws SQLException {
        return writeDataSource.getConnection();
    }
}
