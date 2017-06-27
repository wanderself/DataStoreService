package com.gree.grih.datstore.db.mysql.dto;

import com.google.common.collect.ImmutableMap;

import java.util.Map;


/**
 * RealTime
 * Created by wander on 20th.Jun.2017
 */
public class RealTime {
    private String mac;
    private String mid;
    private String date;
    private String time;
    private String positipon;
    private String mode;
    private String fengsu;
    private String chaoqiang;
    private String dengguang;
    private String fure;
    private String kaiguanji;
    private String saofeng;
    private String shuimian;
    private String shuimianMode;
    private float wendu;
    private String wendudanwei;
    private String sxsaofeng;
    private String zysaofeng;


    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPositipon() {
        return positipon;
    }

    public void setPositipon(String positipon) {
        this.positipon = positipon;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFengsu() {
        return fengsu;
    }

    public void setFengsu(String fengsu) {
        this.fengsu = fengsu;
    }

    public String getChaoqiang() {
        return chaoqiang;
    }

    public void setChaoqiang(String chaoqiang) {
        this.chaoqiang = chaoqiang;
    }

    public String getDengguang() {
        return dengguang;
    }

    public void setDengguang(String dengguang) {
        this.dengguang = dengguang;
    }

    public String getFure() {
        return fure;
    }

    public void setFure(String fure) {
        this.fure = fure;
    }

    public String getKaiguanji() {
        return kaiguanji;
    }

    public void setKaiguanji(String kaiguanji) {
        this.kaiguanji = kaiguanji;
    }

    public String getSaofeng() {
        return saofeng;
    }

    public void setSaofeng(String saofeng) {
        this.saofeng = saofeng;
    }

    public String getShuimian() {
        return shuimian;
    }

    public void setShuimian(String shuimian) {
        this.shuimian = shuimian;
    }

    public String getShuimianMode() {
        return shuimianMode;
    }

    public void setShuimianMode(String shuimianMode) {
        this.shuimianMode = shuimianMode;
    }

    public float getWendu() {
        return wendu;
    }

    public void setWendu(float wendu) {
        this.wendu = wendu;
    }

    public String getWendudanwei() {
        return wendudanwei;
    }

    public void setWendudanwei(String wendudanwei) {
        this.wendudanwei = wendudanwei;
    }

    public String getSxsaofeng() {
        return sxsaofeng;
    }

    public void setSxsaofeng(String sxsaofeng) {
        this.sxsaofeng = sxsaofeng;
    }

    public String getZysaofeng() {
        return zysaofeng;
    }

    public void setZysaofeng(String zysaofeng) {
        this.zysaofeng = zysaofeng;
    }

    public static ImmutableMap<Object, Object> getMs() {
        return ms;
    }

    public static Map<String, String> getFs() {
        return fs;
    }

    public static Map<String, String> getCq() {
        return cq;
    }

    public static Map<String, String> getDg() {
        return dg;
    }

    public static Map<String, String> getFr() {
        return fr;
    }

    public static Map<String, String> getKgj() {
        return kgj;
    }

    public static Map<String, String> getSf() {
        return sf;
    }

    public static Map<String, String> getSleep() {
        return sleep;
    }

    public static Map<String, String> getSleepMode() {
        return sleepMode;
    }

    public static Map<String, String> getWddw() {
        return wddw;
    }

    public static Map<Object, Object> getSxsf() {
        return sxsf;
    }

    public static Map<Object, Object> getZysf() {
        return zysf;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String svrCtime) {
        this.date = svrCtime.split(" ")[0];
    }

    public String getTime() {
        return time;
    }

    public void setTime(String svrCtime) {
        this.time = svrCtime.split(" ")[1];
    }

    @Override
    public String toString() {
        return "RealTime{" +
                "mac='" + mac + '\'' +
                ", mid='" + mid + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", positipon='" + positipon + '\'' +
                ", mode='" + mode + '\'' +
                ", fengsu='" + fengsu + '\'' +
                ", chaoqiang='" + chaoqiang + '\'' +
                ", dengguang='" + dengguang + '\'' +
                ", fure='" + fure + '\'' +
                ", kaiguanji='" + kaiguanji + '\'' +
                ", saofeng='" + saofeng + '\'' +
                ", shuimian='" + shuimian + '\'' +
                ", shuimianMode='" + shuimianMode + '\'' +
                ", wendu=" + wendu +
                ", wendudanwei='" + wendudanwei + '\'' +
                ", sxsaofeng='" + sxsaofeng + '\'' +
                ", zysaofeng='" + zysaofeng + '\'' +
                '}';
    }

    //0, "自动", 1, "制冷", 2, "抽湿", 3, "送风", 4, "制热", 5, "节能"
    private static final ImmutableMap<Object, Object> ms = ImmutableMap.builder()
            .put("0", "自动")
            .put("1", "制冷")
            .put("2", "抽湿")
            .put("3", "送风")
            .put("4", "制热")
            .put("5", "节能")
            .build();

    private static final Map<String, String> fs = ImmutableMap.of("0", "自动风", "1", "低风", "2", "中风", "3", "高风");

    private static final Map<String, String> cq = ImmutableMap.of("0", "超强关", "1", "超强开");
    private static final Map<String, String> dg = ImmutableMap.of("0", "灯光关", "1", "灯光开");
    private static final Map<String, String> fr = ImmutableMap.of("0", "辅热开", "1", "辅热关");
    private static final Map<String, String> kgj = ImmutableMap.of("0", "关机", "1", "开机");
    private static final Map<String, String> sf = ImmutableMap.of("0", "扫风关", "1", "扫风开");
    private static final Map<String, String> sleep = ImmutableMap.of("0", "睡眠关", "1", "睡眠开");
    private static final Map<String, String> sleepMode = ImmutableMap.of("0", "睡眠关", "1", "新睡眠", "2", "普通睡眠", "3", "自定义睡眠");
    private static final Map<String, String> wddw = ImmutableMap.of("0", "摄氏", "1", "华氏");
    private static final Map<Object, Object> sxsf = ImmutableMap.builder()
            .put("0", "关")
            .put("1", "15扫风")
            .put("2", "1位置")
            .put("3", "2位置")
            .put("4", "3位置")
            .put("5", "4位置")
            .put("6", "5位置")
            .put("7", "35扫风")
            .put("8", "25扫风")
            .put("9", "24扫风")
            .put("10", "14扫风")
            .put("11", "13扫风")
            .build();

    private static final Map<Object, Object> zysf = ImmutableMap.builder()
            .put("0", "关")
            .put("1", "同向扫风")
            .put("2", "1位置")
            .put("3", "2位置")
            .put("4", "3位置")
            .put("5", "4位置")
            .put("6", "5位置")
            .put("12", "15位置")
            .put("13", "相向扫风")
            .build();


}
