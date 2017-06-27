package com.gree.grih.datstore.bolt;

import com.gree.grih.datstore.db.mysql.adapter.DataSource;
import com.gree.grih.datstore.db.mysql.dao.Impl.RealTimeDaoImpl;
import com.gree.grih.datstore.db.mysql.dto.RealTime;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * ProcedureBolt
 * Created by root on 27th.Jun.2017
 */
public class ProcedureBolt extends BaseRichBolt {


    private static Logger logger = LoggerFactory.getLogger(ProcedureBolt.class);
    private Connection conn;
    private Properties configs;
    private OutputCollector collector;

    public ProcedureBolt(Properties configs) {
        this.configs = configs;
    }

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple tuple) {


        try {
            conn = DataSource.getInstance(configs).getWriteConnection();

            RealTime realTime = new RealTime();
            realTime.setMac(tuple.getStringByField("mac"));
            realTime.setMid(tuple.getStringByField("mid"));
            realTime.setDate(tuple.getStringByField("time"));
            realTime.setTime(tuple.getStringByField("time"));
            realTime.setMode((String) RealTime.getMs().get(tuple.getStringByField("ms")));
            realTime.setFengsu(RealTime.getFs().get(tuple.getStringByField("fs")));
            realTime.setChaoqiang(RealTime.getCq().get(tuple.getStringByField("cq")));
            realTime.setDengguang(RealTime.getDg().get(tuple.getStringByField("dg")));
            realTime.setFure(RealTime.getFr().get(tuple.getStringByField("fr")));
            realTime.setKaiguanji(RealTime.getKgj().get(tuple.getStringByField("kgj")));
            realTime.setSaofeng(RealTime.getSf().get(tuple.getStringByField("sf")));
            realTime.setShuimian(RealTime.getSleep().get(tuple.getStringByField("sleep")));
            realTime.setShuimianMode(RealTime.getSleepMode().get(tuple.getStringByField("sleepMode")));
            realTime.setWendu(Float.parseFloat(tuple.getStringByField("wd")));
            realTime.setWendudanwei(RealTime.getWddw().get(tuple.getStringByField("wddw")));
            realTime.setSxsaofeng((String) RealTime.getSxsf().get(tuple.getStringByField("sxsf")));
            realTime.setZysaofeng((String) RealTime.getZysf().get(tuple.getStringByField("zysf")));

            RealTimeDaoImpl realTimeDao = new RealTimeDaoImpl();
            realTimeDao.update(conn, realTime);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }


    }


    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("msg"));
    }
}
