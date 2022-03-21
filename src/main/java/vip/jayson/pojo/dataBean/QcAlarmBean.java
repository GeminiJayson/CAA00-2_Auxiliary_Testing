package vip.jayson.pojo.dataBean;

import java.util.LinkedHashMap;

public class QcAlarmBean {
    private LinkedHashMap<String, String> qcbjSection;
    private Integer qcdiNum;
    private Integer qhcjNum;
    private Integer dccjNum;
    private LinkedHashMap<String, LinkedHashMap<String, String>> qcdi;
    private LinkedHashMap<String, LinkedHashMap<String, String>> qhcj;
    private LinkedHashMap<String, LinkedHashMap<String, String>> dccj;

    public QcAlarmBean() {
        this.qcdi = new LinkedHashMap<>();
        this.qhcj = new LinkedHashMap<>();
        this.dccj = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, String> getQcbjSection() {
        return qcbjSection;
    }

    public void setQcbjSection(LinkedHashMap<String, String> qcbjSection) {
        this.qcbjSection = qcbjSection;
    }

    public Integer getQcdiNum() {
        return qcdiNum;
    }

    public void setQcdiNum(Integer qcdiNum) {
        this.qcdiNum = qcdiNum;
    }

    public Integer getQhcjNum() {
        return qhcjNum;
    }

    public void setQhcjNum(Integer qhcjNum) {
        this.qhcjNum = qhcjNum;
    }

    public Integer getDccjNum() {
        return dccjNum;
    }

    public void setDccjNum(Integer dccjNum) {
        this.dccjNum = dccjNum;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getQcdi() {
        return qcdi;
    }

    public void setQcdi(LinkedHashMap<String, LinkedHashMap<String, String>> qcdi) {
        this.qcdi = qcdi;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getQhcj() {
        return qhcj;
    }

    public void setQhcj(LinkedHashMap<String, LinkedHashMap<String, String>> qhcj) {
        this.qhcj = qhcj;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getDccj() {
        return dccj;
    }

    public void setQccj(LinkedHashMap<String, LinkedHashMap<String, String>> dccj) {
        this.dccj = dccj;
    }

    @Override
    public String toString() {
        return "QcAlarmBean{" +
                "qcbjSection=" + qcbjSection +
                ", qcdiNum=" + qcdiNum +
                ", qhcjNum=" + qhcjNum +
                ", dccjNum=" + dccjNum +
                ", qcdi=" + qcdi +
                ", qhcj=" + qhcj +
                ", dccj=" + dccj +
                '}';
    }
}
