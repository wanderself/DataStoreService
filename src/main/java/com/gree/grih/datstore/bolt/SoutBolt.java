package com.gree.grih.datstore.bolt;

import com.google.gson.Gson;
import com.gree.grih.datstore.jsonsBean.AirConData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * SoutBolt
 * Created by root on 17th.Apr.2017
 */
public class SoutBolt extends BaseBasicBolt {

    public static Configuration HBASE_CONFIG = new Configuration();
    private static Logger LOG = LoggerFactory.getLogger(SoutBolt.class);

    static {
        HBASE_CONFIG = HBaseConfiguration.create();
        HBASE_CONFIG.addResource(new Path("/src/main/resources/hbase-site.xml"));
        HBASE_CONFIG.set("hbase.rootdir", "hdfs://10.2.5.203/hbase");
    }

    private AirConData data;
    private Connection connection;
    private Table table;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        try {
            System.setProperty("HADOOP_USER_NAME", "greejsj");
            connection = ConnectionFactory.createConnection(HBASE_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        System.out.println(tuple.getString(0));
//        LOG.info(tuple.toString());
        data = decode(tuple.getString(0));
        String lastRowKey = gets("GRIH:REALTIME", data.mac, "last", "lastRowKey");
        if (lastRowKey != null) {
            endTime(data, lastRowKey);
        }
        hisInsert(data);
        realTimeIns(data);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }

    public void hisInsert(AirConData data) {
        try {
            table = connection.getTable(TableName.valueOf("GRIH:TEST"));

            Put put = new Put(Bytes.toBytes(data.rowKey));
            puts(put, data);
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realTimeIns(AirConData data) {
        try {
            table = connection.getTable(TableName.valueOf("GRIH:REALTIME"));
            Put put = new Put(Bytes.toBytes(data.mac));
            puts(put, data);
            put.addColumn(Bytes.toBytes("last"), Bytes.toBytes("lastRowKey"), Bytes.toBytes(data.rowKey));

            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void puts(Put put, AirConData data) {
        put.addColumn(Bytes.toBytes("DevInfoRes"), Bytes.toBytes("mac"), Bytes.toBytes(data.mac));
        put.addColumn(Bytes.toBytes("DevInfoRes"), Bytes.toBytes("mid"), Bytes.toBytes(data.mid));
        put.addColumn(Bytes.toBytes("DevInfoRes"), Bytes.toBytes("barCode"), Bytes.toBytes(data.DevInfoRes.tiaoma));

        put.addColumn(Bytes.toBytes("Time"), Bytes.toBytes("ctime"), Bytes.toBytes(data.ctime));
        put.addColumn(Bytes.toBytes("Time"), Bytes.toBytes("svrCtime"), Bytes.toBytes(data.svrCtime));

        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("ykajxhbzw"), Bytes.toBytes(data.CtlStatus.ykajxhbzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("fmmbzw"), Bytes.toBytes(data.CtlStatus.fmmbzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("ykqfmbzw"), Bytes.toBytes(data.CtlStatus.ykqfmbzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("f4mbzw"), Bytes.toBytes(data.CtlStatus.f4mbzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("f3mbzw"), Bytes.toBytes(data.CtlStatus.f3mbzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("f2mbza"), Bytes.toBytes(data.CtlStatus.f2mbza));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("f1mbzw"), Bytes.toBytes(data.CtlStatus.f1mbzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("f0mbzw"), Bytes.toBytes(data.CtlStatus.f0mbzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("kgj"), Bytes.toBytes(data.CtlStatus.kgj));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("ms"), Bytes.toBytes(data.CtlStatus.ms));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sm"), Bytes.toBytes(data.CtlStatus.sm));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sf"), Bytes.toBytes(data.CtlStatus.sf));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("fs"), Bytes.toBytes(data.CtlStatus.fs));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("wd"), Bytes.toBytes(data.CtlStatus.wd));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("jk"), Bytes.toBytes(data.CtlStatus.jk));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("dg"), Bytes.toBytes(data.CtlStatus.dg));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("cq"), Bytes.toBytes(data.CtlStatus.cq));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("wddw"), Bytes.toBytes(data.CtlStatus.wddw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("hsxlwdsb"), Bytes.toBytes(data.CtlStatus.hsxlwdsb));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("hq"), Bytes.toBytes(data.CtlStatus.hq));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("wifikgkzw"), Bytes.toBytes(data.CtlStatus.wifikgkzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("wifihfcfkzw"), Bytes.toBytes(data.CtlStatus.wifihfcfkzw));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sxsf"), Bytes.toBytes(data.CtlStatus.sxsf));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("zysf"), Bytes.toBytes(data.CtlStatus.zysf));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sfg"), Bytes.toBytes(data.CtlStatus.sfg));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("wdxsms"), Bytes.toBytes(data.CtlStatus.wdxsms));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("qcsx"), Bytes.toBytes(data.CtlStatus.qcsx));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sdxs"), Bytes.toBytes(data.CtlStatus.sdxs));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sdjlsgn"), Bytes.toBytes(data.CtlStatus.sdjlsgn));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("zr"), Bytes.toBytes(data.CtlStatus.zr));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("gz"), Bytes.toBytes(data.CtlStatus.gz));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("zrms"), Bytes.toBytes(data.CtlStatus.zrms));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("eco8dzr"), Bytes.toBytes(data.CtlStatus.eco8dzr));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("qzgn"), Bytes.toBytes(data.CtlStatus.qzgn));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("csms"), Bytes.toBytes(data.CtlStatus.csms));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sm4"), Bytes.toBytes(data.CtlStatus.sm4));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sm23"), Bytes.toBytes(data.CtlStatus.sm23));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("jygn"), Bytes.toBytes(data.CtlStatus.jygn));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("sffsf5m"), Bytes.toBytes(data.CtlStatus.sffsf5m));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("ddfs"), Bytes.toBytes(data.CtlStatus.ddfs));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("xtkzwjcbjs"), Bytes.toBytes(data.CtlStatus.xtkzwjcbjs));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("xtkzwrfmk"), Bytes.toBytes(data.CtlStatus.xtkzwrfmk));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("xtkzwwifimk"), Bytes.toBytes(data.CtlStatus.xtkzwwifimk));
        put.addColumn(Bytes.toBytes("CtlSend"), Bytes.toBytes("xtkzwykle"), Bytes.toBytes(data.CtlStatus.xtkzwykle));

        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("txbb"), Bytes.toBytes(data.InOutStatus.in.txbb));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("txsd"), Bytes.toBytes(data.InOutStatus.in.txsd));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("lnjx"), Bytes.toBytes(data.InOutStatus.in.lnjx));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("dpbp"), Bytes.toBytes(data.InOutStatus.in.dpbp));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("njnldm"), Bytes.toBytes(data.InOutStatus.in.njnldm));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("dypl"), Bytes.toBytes(data.InOutStatus.in.dypl));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("gdfs"), Bytes.toBytes(data.InOutStatus.in.gdfs));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("dyzl"), Bytes.toBytes(data.InOutStatus.in.dyzl));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("lmzl"), Bytes.toBytes(data.InOutStatus.in.lmzl));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("lxdm"), Bytes.toBytes(data.InOutStatus.in.lxdm));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("hjgwb"), Bytes.toBytes(data.InOutStatus.in.hjgwb));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("ngzjgwb"), Bytes.toBytes(data.InOutStatus.in.ngzjgwb));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("sdcgq"), Bytes.toBytes(data.InOutStatus.in.sdcgq));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("fjzl"), Bytes.toBytes(data.InOutStatus.in.fjzl));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("fjds"), Bytes.toBytes(data.InOutStatus.in.fjds));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("jyxphqsjbz"), Bytes.toBytes(data.InOutStatus.in.jyxphqsjbz));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("jdccgn"), Bytes.toBytes(data.InOutStatus.in.jdccgn));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("frgn"), Bytes.toBytes(data.InOutStatus.in.frgn));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("jygn"), Bytes.toBytes(data.InOutStatus.in.jygn));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("jkgn"), Bytes.toBytes(data.InOutStatus.in.jkgn));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("hqgn"), Bytes.toBytes(data.InOutStatus.in.hqgn));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("ssggn"), Bytes.toBytes(data.InOutStatus.in.ssggn));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("dsfsxz"), Bytes.toBytes(data.InOutStatus.in.dsfsxz));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("txmh"), Bytes.toBytes(data.InOutStatus.in.txmh));
        put.addColumn(Bytes.toBytes("InnerUnit"), Bytes.toBytes("jx"), Bytes.toBytes(data.InOutStatus.in.jx));

        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("txbb"), Bytes.toBytes(data.InOutStatus.out.txbb));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("txsd"), Bytes.toBytes(data.InOutStatus.out.txsd));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("lnjx"), Bytes.toBytes(data.InOutStatus.out.lnjx));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("bpdp"), Bytes.toBytes(data.InOutStatus.out.bpdp));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("wjnldm"), Bytes.toBytes(data.InOutStatus.out.wjnldm));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("gdfs"), Bytes.toBytes(data.InOutStatus.out.gdfs));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("dyzl"), Bytes.toBytes(data.InOutStatus.out.dyzl));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("swjlx"), Bytes.toBytes(data.InOutStatus.out.swjlx));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("ysjxh"), Bytes.toBytes(data.InOutStatus.out.ysjxh));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("cflx"), Bytes.toBytes(data.InOutStatus.out.cflx));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("fjzl"), Bytes.toBytes(data.InOutStatus.out.fjzl));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("fjgs"), Bytes.toBytes(data.InOutStatus.out.fjgs));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("fjds"), Bytes.toBytes(data.InOutStatus.out.fjds));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("dplnqyrdyw"), Bytes.toBytes(data.InOutStatus.out.dplnqyrdyw));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("ysjyrdyw"), Bytes.toBytes(data.InOutStatus.out.ysjyrdyw));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("xqzyryw"), Bytes.toBytes(data.InOutStatus.out.xqzyryw));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("dzpzfyw"), Bytes.toBytes(data.InOutStatus.out.dzpzfyw));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("jxm"), Bytes.toBytes(data.InOutStatus.out.jxm));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("wfjipmmk"), Bytes.toBytes(data.InOutStatus.out.wfjipmmk));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("ysjipmmk"), Bytes.toBytes(data.InOutStatus.out.ysjipmmk));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("njcxbbh"), Bytes.toBytes(data.InOutStatus.out.njcxbbh));
        put.addColumn(Bytes.toBytes("OuterUnit"), Bytes.toBytes("wjcxbbh"), Bytes.toBytes(data.InOutStatus.out.wjcxbbh));

        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("kgjzt"), Bytes.toBytes(data.InStatusFault.kgjzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("yxms"), Bytes.toBytes(data.InStatusFault.yxms));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("nfjzs"), Bytes.toBytes(data.InStatusFault.nfjzs));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snsdwd"), Bytes.toBytes(data.InStatusFault.snsdwd));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snhjwd"), Bytes.toBytes(data.InStatusFault.snhjwd));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snzfqzjwd"), Bytes.toBytes(data.InStatusFault.snzfqzjwd));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("syx"), Bytes.toBytes(data.InStatusFault.syx));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("sfms"), Bytes.toBytes(data.InStatusFault.sfms));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("qzcs"), Bytes.toBytes(data.InStatusFault.qzcs));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("qzzr"), Bytes.toBytes(data.InStatusFault.qzzr));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("qzzl"), Bytes.toBytes(data.InStatusFault.qzzl));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("bcdwdz"), Bytes.toBytes(data.InStatusFault.bcdwdz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("sfkzwjzrwdbc"), Bytes.toBytes(data.InStatusFault.sfkzwjzrwdbc));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snhjsd"), Bytes.toBytes(data.InStatusFault.snhjsd));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("jszt"), Bytes.toBytes(data.InStatusFault.jszt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("savezt"), Bytes.toBytes(data.InStatusFault.savezt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("hqzt"), Bytes.toBytes(data.InStatusFault.hqzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("jyzt"), Bytes.toBytes(data.InStatusFault.jyzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("flfzt"), Bytes.toBytes(data.InStatusFault.flfzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("fnlzt"), Bytes.toBytes(data.InStatusFault.fnlzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("dfrzt"), Bytes.toBytes(data.InStatusFault.dfrzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("kczt"), Bytes.toBytes(data.InStatusFault.kczt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("ysjzssdz"), Bytes.toBytes(data.InStatusFault.ysjzssdz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("dzpzfkdsdyxzt"), Bytes.toBytes(data.InStatusFault.dzpzfkdsdyxzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("dzpzfkdsdz"), Bytes.toBytes(data.InStatusFault.dzpzfkdsdz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("wsfjsdyxzt"), Bytes.toBytes(data.InStatusFault.wsfjsdyxzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("mbpqwdsdyxzt"), Bytes.toBytes(data.InStatusFault.mbpqwdsdyxzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("mbgrdsdyxzt"), Bytes.toBytes(data.InStatusFault.mbgrdsdyxzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("swtjyqbz"), Bytes.toBytes(data.InStatusFault.swtjyqbz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snqtcgqgz"), Bytes.toBytes(data.InStatusFault.snqtcgqgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snswcgqgz"), Bytes.toBytes(data.InStatusFault.snswcgqgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snsdcgqgz"), Bytes.toBytes(data.InStatusFault.snsdcgqgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snhjgwbgz"), Bytes.toBytes(data.InStatusFault.snhjgwbgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snzfqzjgwbgz"), Bytes.toBytes(data.InStatusFault.snzfqzjgwbgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("njzbyscqtxgz"), Bytes.toBytes(data.InStatusFault.njzbyscqtxgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snjsmbh"), Bytes.toBytes(data.InStatusFault.snjsmbh));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("zdajds"), Bytes.toBytes(data.InStatusFault.zdajds));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("hdmgz"), Bytes.toBytes(data.InStatusFault.hdmgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("xzkdpyc"), Bytes.toBytes(data.InStatusFault.xzkdpyc));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("txmgz"), Bytes.toBytes(data.InStatusFault.txmgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("jyxpdxgz"), Bytes.toBytes(data.InStatusFault.jyxpdxgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("snfjgz"), Bytes.toBytes(data.InStatusFault.snfjgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("qfpbxz"), Bytes.toBytes(data.InStatusFault.qfpbxz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("csfsxz"), Bytes.toBytes(data.InStatusFault.csfsxz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("wkjdxz"), Bytes.toBytes(data.InStatusFault.wkjdxz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("zysfzt"), Bytes.toBytes(data.InStatusFault.zysfzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("sxsfzt"), Bytes.toBytes(data.InStatusFault.sxsfzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("rszl"), Bytes.toBytes(data.InStatusFault.rszl));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("dredmsyxzt"), Bytes.toBytes(data.InStatusFault.dredmsyxzt));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("spmkrfgz"), Bytes.toBytes(data.InStatusFault.spmkrfgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("jcbtxjfgz"), Bytes.toBytes(data.InStatusFault.jcbtxjfgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("njwdywjgzOe"), Bytes.toBytes(data.InStatusFault.njwdywjgzOe));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("sfjggzfc"), Bytes.toBytes(data.InStatusFault.sfjggzfc));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("wifigzgm"), Bytes.toBytes(data.InStatusFault.wifigzgm));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("nwjtxgz"), Bytes.toBytes(data.InStatusFault.nwjtxgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("djdzbh"), Bytes.toBytes(data.InStatusFault.djdzbh));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("xsbhqdbgz"), Bytes.toBytes(data.InStatusFault.xsbhqdbgz));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("dbpxsbyc"), Bytes.toBytes(data.InStatusFault.dbpxsbyc));
        put.addColumn(Bytes.toBytes("InnerStatus"), Bytes.toBytes("njglxhgz"), Bytes.toBytes(data.InStatusFault.njglxhgz));

        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjkgzt"), Bytes.toBytes(data.OutStatusFault.ysjkgzt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("hsms"), Bytes.toBytes(data.OutStatusFault.hsms));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjyxzs"), Bytes.toBytes(data.OutStatusFault.ysjyxzs));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wfj1zs"), Bytes.toBytes(data.OutStatusFault.wfj1zs));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wfj2zs"), Bytes.toBytes(data.OutStatusFault.wfj2zs));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjyxgl"), Bytes.toBytes(data.OutStatusFault.ysjyxgl));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("dzpzfkd"), Bytes.toBytes(data.OutStatusFault.dzpzfkd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("zlmxdy"), Bytes.toBytes(data.OutStatusFault.zlmxdy));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swhjwd"), Bytes.toBytes(data.OutStatusFault.swhjwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swlnqzjwd"), Bytes.toBytes(data.OutStatusFault.swlnqzjwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swpqwd"), Bytes.toBytes(data.OutStatusFault.swpqwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("savezt"), Bytes.toBytes(data.OutStatusFault.savezt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wjfnlzt"), Bytes.toBytes(data.OutStatusFault.wjfnlzt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wjjyzt"), Bytes.toBytes(data.OutStatusFault.wjjyzt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("dzpzfzt"), Bytes.toBytes(data.OutStatusFault.dzpzfzt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("sfzdjzt"), Bytes.toBytes(data.OutStatusFault.sfzdjzt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("hyzt"), Bytes.toBytes(data.OutStatusFault.hyzt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ptcszt"), Bytes.toBytes(data.OutStatusFault.ptcszt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("csyq"), Bytes.toBytes(data.OutStatusFault.csyq));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("tscszt"), Bytes.toBytes(data.OutStatusFault.tscszt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wjrytjgz"), Bytes.toBytes(data.OutStatusFault.wjrytjgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("hszczt"), Bytes.toBytes(data.OutStatusFault.hszczt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wjacdlz"), Bytes.toBytes(data.OutStatusFault.wjacdlz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("syx"), Bytes.toBytes(data.OutStatusFault.syx));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("sfms"), Bytes.toBytes(data.OutStatusFault.sfms));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qzcs"), Bytes.toBytes(data.OutStatusFault.qzcs));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qzzr"), Bytes.toBytes(data.OutStatusFault.qzzr));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qzzl"), Bytes.toBytes(data.OutStatusFault.qzzl));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("dredmsyxzt"), Bytes.toBytes(data.OutStatusFault.dredmsyxzt));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("glggbhxjp"), Bytes.toBytes(data.OutStatusFault.glggbhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("mkdlbhxjp"), Bytes.toBytes(data.OutStatusFault.mkdlbhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("mkwdbhxjp"), Bytes.toBytes(data.OutStatusFault.mkwdbhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("zlmxdybhxjp"), Bytes.toBytes(data.OutStatusFault.zlmxdybhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ghbhxjp"), Bytes.toBytes(data.OutStatusFault.ghbhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("fjdbhxjp"), Bytes.toBytes(data.OutStatusFault.fjdbhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("pqbhxjp"), Bytes.toBytes(data.OutStatusFault.pqbhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wjacdlbhxjp"), Bytes.toBytes(data.OutStatusFault.wjacdlbhxjp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("gzgwbgz"), Bytes.toBytes(data.OutStatusFault.gzgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("pqgwbgz"), Bytes.toBytes(data.OutStatusFault.pqgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("hjgwbgz"), Bytes.toBytes(data.OutStatusFault.hjgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swlnqzjgwbgz"), Bytes.toBytes(data.OutStatusFault.swlnqzjgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("mkgwbdlgz"), Bytes.toBytes(data.OutStatusFault.mkgwbdlgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjrgzbh"), Bytes.toBytes(data.OutStatusFault.ysjrgzbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("pqbh"), Bytes.toBytes(data.OutStatusFault.pqbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("gfhbh"), Bytes.toBytes(data.OutStatusFault.gfhbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wjacdlbh"), Bytes.toBytes(data.OutStatusFault.wjacdlbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("mkdlfobh"), Bytes.toBytes(data.OutStatusFault.mkdlfobh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("mkwdbh"), Bytes.toBytes(data.OutStatusFault.mkwdbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("fjdbh"), Bytes.toBytes(data.OutStatusFault.fjdbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("glggbh"), Bytes.toBytes(data.OutStatusFault.glggbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjqnxbhqxttqx"), Bytes.toBytes(data.OutStatusFault.ysjqnxbhqxttqx));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("pfcglgz"), Bytes.toBytes(data.OutStatusFault.pfcglgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("zlmxdyggbh"), Bytes.toBytes(data.OutStatusFault.zlmxdyggbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("zlmxdygdbh"), Bytes.toBytes(data.OutStatusFault.zlmxdygdbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qfbh"), Bytes.toBytes(data.OutStatusFault.qfbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("msct"), Bytes.toBytes(data.OutStatusFault.msct));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("snwjxbpp"), Bytes.toBytes(data.OutStatusFault.snwjxbpp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ytdnwjglljytxljbpp"), Bytes.toBytes(data.OutStatusFault.ytdnwjglljytxljbpp));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("jyxpdxgz"), Bytes.toBytes(data.OutStatusFault.jyxpdxgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("glxhyc"), Bytes.toBytes(data.OutStatusFault.glxhyc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("stfhxyc"), Bytes.toBytes(data.OutStatusFault.stfhxyc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("xzkdpyc"), Bytes.toBytes(data.OutStatusFault.xzkdpyc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swfj2gz"), Bytes.toBytes(data.OutStatusFault.swfj2gz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swfj1gz"), Bytes.toBytes(data.OutStatusFault.swfj1gz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("gwbhswfj"), Bytes.toBytes(data.OutStatusFault.gwbhswfj));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("xtdybh"), Bytes.toBytes(data.OutStatusFault.xtdybh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("xtgybh"), Bytes.toBytes(data.OutStatusFault.xtgybh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("zlmxdydlgz"), Bytes.toBytes(data.OutStatusFault.zlmxdydlgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("zjdljcgz"), Bytes.toBytes(data.OutStatusFault.zjdljcgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("drcdgz"), Bytes.toBytes(data.OutStatusFault.drcdgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjxdldljcgz"), Bytes.toBytes(data.OutStatusFault.ysjxdldljcgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjsb"), Bytes.toBytes(data.OutStatusFault.ysjsb));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjtcbh"), Bytes.toBytes(data.OutStatusFault.ysjtcbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjdz"), Bytes.toBytes(data.OutStatusFault.ysjdz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qdsb"), Bytes.toBytes(data.OutStatusFault.qdsb));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qdmkfw"), Bytes.toBytes(data.OutStatusFault.qdmkfw));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("sc"), Bytes.toBytes(data.OutStatusFault.sc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjbmyc"), Bytes.toBytes(data.OutStatusFault.ysjbmyc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qdbhjgwbgz"), Bytes.toBytes(data.OutStatusFault.qdbhjgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("jljcqbh"), Bytes.toBytes(data.OutStatusFault.jljcqbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("wpbh"), Bytes.toBytes(data.OutStatusFault.wpbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("cgqljbh"), Bytes.toBytes(data.OutStatusFault.cgqljbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qdbtxgz"), Bytes.toBytes(data.OutStatusFault.qdbtxgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjxdlgl"), Bytes.toBytes(data.OutStatusFault.ysjxdlgl));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("jlsrdyyc"), Bytes.toBytes(data.OutStatusFault.jlsrdyyc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("fjtsbtxgz"), Bytes.toBytes(data.OutStatusFault.fjtsbtxgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("yfgwbgz"), Bytes.toBytes(data.OutStatusFault.yfgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qfgwbgz"), Bytes.toBytes(data.OutStatusFault.qfgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swlnqrggwbgz"), Bytes.toBytes(data.OutStatusFault.swlnqrggwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swlnqcggwbgz"), Bytes.toBytes(data.OutStatusFault.swlnqcggwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("lmwdgwbgz"), Bytes.toBytes(data.OutStatusFault.lmwdgwbgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swjlmjrqsxgz"), Bytes.toBytes(data.OutStatusFault.swjlmjrqsxgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swjlmjrqjdqzlgz"), Bytes.toBytes(data.OutStatusFault.swjlmjrqjdqzlgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ipmmkwd"), Bytes.toBytes(data.OutStatusFault.ipmmkwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("xtyc"), Bytes.toBytes(data.OutStatusFault.xtyc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("dyztw"), Bytes.toBytes(data.OutStatusFault.dyztw));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("dlztw"), Bytes.toBytes(data.OutStatusFault.dlztw));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("yfgwbwd"), Bytes.toBytes(data.OutStatusFault.yfgwbwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("qfgwbwd"), Bytes.toBytes(data.OutStatusFault.qfgwbwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swlnqrgwd"), Bytes.toBytes(data.OutStatusFault.swlnqrgwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("swlnqcgwd"), Bytes.toBytes(data.OutStatusFault.swlnqcgwd));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ysjpqgwbh"), Bytes.toBytes(data.OutStatusFault.ysjpqgwbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("jlglbh"), Bytes.toBytes(data.OutStatusFault.jlglbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("ggwbh"), Bytes.toBytes(data.OutStatusFault.ggwbh));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("deepromgz"), Bytes.toBytes(data.OutStatusFault.deepromgz));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("whwdyc"), Bytes.toBytes(data.OutStatusFault.whwdyc));
        put.addColumn(Bytes.toBytes("OuterStatus"), Bytes.toBytes("zrggwjp"), Bytes.toBytes(data.OutStatusFault.zrggwjp));

    }

    public String gets(String tableName, String rowKey, String family, String qualifier) {
        String value = null;
        try {
            table = connection.getTable(TableName.valueOf(tableName));
            Get getRow = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(getRow);
            value = Bytes.toString(result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void endTime(AirConData data, String lastRowKey) {
        try {
            table = connection.getTable(TableName.valueOf("GRIH:TEST"));

            Put put = new Put(Bytes.toBytes(lastRowKey));
            put.addColumn(Bytes.toBytes("Time"), Bytes.toBytes("endTime"), Bytes.toBytes(data.svrCtime));

            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AirConData decode(String jstr) {
        Gson gson = new Gson();
        AirConData data = gson.fromJson(jstr, AirConData.class);
        return data;
    }

    @Override
    public void cleanup() {
        super.cleanup();
        try {
            table.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
