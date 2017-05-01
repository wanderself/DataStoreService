package com.gree.grih.datstore.jsonsBean;

/**
 * AirConData
 * Created by root on 22th.四月.2017
 */
public class AirConData {

    public String mac;
    public String mid;
    public String rowKey;
    public String ctime;
    public String svrCtime;
    public CtlStatus CtlStatus;
    public InOutUnit InOutStatus;
    public InnerStatus InStatusFault;
    public OuterStatus OutStatusFault;
    public String MonthPower;
    public BarCode DevInfoRes;

    public static class CtlStatus {
        public String ykajxhbzw;
        public String fmmbzw;
        public String ykqfmbzw;
        public String f4mbzw;
        public String f3mbzw;
        public String f2mbza;
        public String f1mbzw;
        public String f0mbzw;
        public String kgj;
        public String ms;
        public String sm;
        public String sf;
        public String fs;
        public String wd;
        public String jk;
        public String dg;
        public String cq;
        public String wddw;
        public String hsxlwdsb;
        public String hq;
        public String wifikgkzw;
        public String wifihfcfkzw;
        public String sxsf;
        public String zysf;
        public String sfg;
        public String wdxsms;
        public String qcsx;
        public String sdxs;
        public String sdjlsgn;
        public String zr;
        public String gz;
        public String zrms;
        public String eco8dzr;
        public String qzgn;
        public String csms;
        public String sm4;
        public String sm23;
        public String jygn;
        public String sffsf5m;
        public String ddfs;
        public String xtkzwjcbjs;
        public String xtkzwrfmk;
        public String xtkzwwifimk;
        public String xtkzwykle;
    }

    public static class InOutUnit {

        public InnerUnit in;
        public OuterUnit out;

        public static class InnerUnit {
            public String txbb;
            public String txsd;
            public String lnjx;
            public String dpbp;
            public String njnldm;
            public String dypl;
            public String gdfs;
            public String dyzl;
            public String lmzl;
            public String lxdm;
            public String hjgwb;
            public String ngzjgwb;
            public String sdcgq;
            public String fjzl;
            public String fjds;
            public String jyxphqsjbz;
            public String jdccgn;
            public String frgn;
            public String jygn;
            public String jkgn;
            public String hqgn;
            public String ssggn;
            public String dsfsxz;
            public String txmh;
            public String jx;
        }

        public static class OuterUnit {
            public String txbb;
            public String txsd;
            public String lnjx;
            public String bpdp;
            public String wjnldm;
            public String gdfs;
            public String dyzl;
            public String swjlx;
            public String ysjxh;
            public String cflx;
            public String fjzl;
            public String fjgs;
            public String fjds;
            public String dplnqyrdyw;
            public String ysjyrdyw;
            public String xqzyryw;
            public String dzpzfyw;
            public String jxm;
            public String wfjipmmk;
            public String ysjipmmk;
            public String njcxbbh;
            public String wjcxbbh;
        }
    }

    public static class InnerStatus {
        public String kgjzt;
        public String yxms;
        public String nfjzs;
        public String snsdwd;
        public String snhjwd;
        public String snzfqzjwd;
        public String syx;
        public String sfms;
        public String qzcs;
        public String qzzr;
        public String qzzl;
        public String bcdwdz;
        public String sfkzwjzrwdbc;
        public String snhjsd;
        public String jszt;
        public String savezt;
        public String hqzt;
        public String jyzt;
        public String flfzt;
        public String fnlzt;
        public String dfrzt;
        public String kczt;
        public String ysjzssdz;
        public String dzpzfkdsdyxzt;
        public String dzpzfkdsdz;
        public String wsfjsdyxzt;
        public String mbpqwdsdyxzt;
        public String mbgrdsdyxzt;
        public String swtjyqbz;
        public String snqtcgqgz;
        public String snswcgqgz;
        public String snsdcgqgz;
        public String snhjgwbgz;
        public String snzfqzjgwbgz;
        public String njzbyscqtxgz;
        public String snjsmbh;
        public String zdajds;
        public String hdmgz;
        public String xzkdpyc;
        public String txmgz;
        public String jyxpdxgz;
        public String snfjgz;
        public String qfpbxz;
        public String csfsxz;
        public String wkjdxz;
        public String zysfzt;
        public String sxsfzt;
        public String rszl;
        public String dredmsyxzt;
        public String spmkrfgz;
        public String jcbtxjfgz;
        public String njwdywjgzOe;
        public String sfjggzfc;
        public String wifigzgm;
        public String nwjtxgz;
        public String djdzbh;
        public String xsbhqdbgz;
        public String dbpxsbyc;
        public String njglxhgz;
    }

    public static class OuterStatus {
        public String ysjkgzt;
        public String hsms;
        public String ysjyxzs;
        public String wfj1zs;
        public String wfj2zs;
        public String ysjyxgl;
        public String dzpzfkd;
        public String zlmxdy;
        public String swhjwd;
        public String swlnqzjwd;
        public String swpqwd;
        public String savezt;
        public String wjfnlzt;
        public String wjjyzt;
        public String dzpzfzt;
        public String sfzdjzt;
        public String hyzt;
        public String ptcszt;
        public String csyq;
        public String tscszt;
        public String wjrytjgz;
        public String hszczt;
        public String wjacdlz;
        public String syx;
        public String sfms;
        public String qzcs;
        public String qzzr;
        public String qzzl;
        public String dredmsyxzt;
        public String glggbhxjp;
        public String mkdlbhxjp;
        public String mkwdbhxjp;
        public String zlmxdybhxjp;
        public String ghbhxjp;
        public String fjdbhxjp;
        public String pqbhxjp;
        public String wjacdlbhxjp;
        public String gzgwbgz;
        public String pqgwbgz;
        public String hjgwbgz;
        public String swlnqzjgwbgz;
        public String mkgwbdlgz;
        public String ysjrgzbh;
        public String pqbh;
        public String gfhbh;
        public String wjacdlbh;
        public String mkdlfobh;
        public String mkwdbh;
        public String fjdbh;
        public String glggbh;
        public String ysjqnxbhqxttqx;
        public String pfcglgz;
        public String zlmxdyggbh;
        public String zlmxdygdbh;
        public String qfbh;
        public String msct;
        public String snwjxbpp;
        public String ytdnwjglljytxljbpp;
        public String jyxpdxgz;
        public String glxhyc;
        public String stfhxyc;
        public String xzkdpyc;
        public String swfj2gz;
        public String swfj1gz;
        public String gwbhswfj;
        public String xtdybh;
        public String xtgybh;
        public String zlmxdydlgz;
        public String zjdljcgz;
        public String drcdgz;
        public String ysjxdldljcgz;
        public String ysjsb;
        public String ysjtcbh;
        public String ysjdz;
        public String qdsb;
        public String qdmkfw;
        public String sc;
        public String ysjbmyc;
        public String qdbhjgwbgz;
        public String jljcqbh;
        public String wpbh;
        public String cgqljbh;
        public String qdbtxgz;
        public String ysjxdlgl;
        public String jlsrdyyc;
        public String fjtsbtxgz;
        public String yfgwbgz;
        public String qfgwbgz;
        public String swlnqrggwbgz;
        public String swlnqcggwbgz;
        public String lmwdgwbgz;
        public String swjlmjrqsxgz;
        public String swjlmjrqjdqzlgz;
        public String ipmmkwd;
        public String xtyc;
        public String dyztw;
        public String dlztw;
        public String yfgwbwd;
        public String qfgwbwd;
        public String swlnqrgwd;
        public String swlnqcgwd;
        public String ysjpqgwbh;
        public String jlglbh;
        public String ggwbh;
        public String deepromgz;
        public String whwdyc;
        public String zrggwjp;
    }

    public static class BarCode {
        public String tiaoma;
    }
}
