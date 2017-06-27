package com.gree.grih.datstore.db.mysql.dao;


import com.gree.grih.datstore.db.mysql.dto.RealTime;

import java.sql.Connection;

/**
 * RealTimeDao
 * Created by wander on 25th.Jun.2017
 */
public interface RealTimeDao {
    void update(Connection conn, RealTime realTime);
}
