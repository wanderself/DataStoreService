package com.gree.grih.datstore.db.mysql.dao.Impl;

import com.gree.grih.datstore.db.mysql.dao.RealTimeDao;
import com.gree.grih.datstore.db.mysql.dto.RealTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * RealTimeDaoImpl
 * Created by wander on 25th.Jun.2017
 */
public class RealTimeDaoImpl implements RealTimeDao {

    private static Logger logger = LoggerFactory.getLogger(RealTimeDaoImpl.class);

    public void update(Connection conn, RealTime realTime) {

        CallableStatement callableStatement = null;
        try {
            String sqlStmt = "CALL grih.acRealTime(?, ?, ?, ?, ?)";
            callableStatement = conn.prepareCall(sqlStmt);
            callableStatement.setString(1, realTime.getMac());
            callableStatement.setString(2, realTime.getMid());
            try {
                callableStatement.setBytes(3, realTime.getMode().getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            callableStatement.setString(4, realTime.getDate());
            callableStatement.setString(5, realTime.getTime());
//            callableStatement.setString(5, realTime.getPositipon());
//            TODO: add the others fields
            logger.info("mac:" + realTime.getMac() + ", mode:" + realTime.getMode());
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
