package vip.jayson.pojo.additionalFunctionArea;

import vip.jayson.config.CAAData;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.dataBean.QcAlarmBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class QCBJBlockBean {
    private BytesInfo INDEX = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo DOWN_FLAG = new BytesInfo("UNIT", 4, "DOWN_FLAG");
    private BytesInfo MANUAL_FLAG = new BytesInfo("UNIT", 4, "MANUAL_FLAG");
    private BytesInfo YQWC_ALARM_FLAG = new BytesInfo("UNIT", 4, "YQWC_ALARM_FLAG");
    private BytesInfo WQYC_ALARM_FLAG = new BytesInfo("UNIT", 4, "WQYC_ALARM_FLAG");
    private BytesInfo CJYC_ALARM_FLAG = new BytesInfo("UNIT", 4, "CJYC_ALARM_FLAG");
    private BytesInfo YQWC_ALARM_DELAY = new BytesInfo("UNIT", 4, "YQWC_ALARM_DELAY");
    private BytesInfo WQYC_ALARM_DELAY  = new BytesInfo("UNIT", 4, "WQYC_ALARM_DELAY ");
    private BytesInfo CJYC_ALARM_DELAY = new BytesInfo("UNIT", 4, "CJYC_ALARM_DELAY");
    private BytesInfo QCDI_Count = new BytesInfo("UNIT", 4, "QCDI_Count");
    private BytesInfo QCDI_Offset = new BytesInfo("UNIT", 4, "QCDI_Offset");
    private BytesInfo QHCJ_Count = new BytesInfo("UNIT", 4, "QHCJ_Count");
    private BytesInfo QHCJ_Offset = new BytesInfo("UNIT", 4, "QHCJ_Offset");
    private BytesInfo DCCJ_Count = new BytesInfo("UNIT", 4, "DCCJ_Count");
    private BytesInfo DCCJ_Offset = new BytesInfo("UNIT", 4, "DCCJ_Offset");
    private List<QCDIBean> QCDIInfo = new ArrayList<>();
    private List<QHCJBean> QHCJInfo = new ArrayList<>();
    private List<DCCJBean> DCCJInfo = new ArrayList<>();
    public static String[] order = {"INDEX","DOWN_FLAG","MANUAL_FLAG","YQWC_ALARM_FLAG","WQYC_ALARM_FLAG","CJYC_ALARM_FLAG","YQWC_ALARM_DELAY","WQYC_ALARM_DELAY","CJYC_ALARM_DELAY","QCDI_Count","QCDI_Offset","QHCJ_Count","QHCJ_Offset","DCCJ_Count","DCCJ_Offset","QCDIInfo","QHCJInfo","DCCInfo"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
            for (int i = 0; i < order.length - 3; i++) {
                BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
                allContent.add(fieldValueByFieldName);
            }
            for (QCDIBean qcdiBean : this.QCDIInfo) {
                allContent.addAll(qcdiBean.getAllContent());
            }
            for (QHCJBean qhciBean : this.QHCJInfo) {
                allContent.addAll(qhciBean.getAllContent());
            }
            for (DCCJBean dccjBean : this.DCCJInfo) {
                allContent.addAll(dccjBean.getAllContent());
            }

        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(QcAlarmBean qcAlarmFile) {
        LinkedHashMap<String, String> qcbjSection = qcAlarmFile.getQcbjSection();
        for (String fieldName : qcbjSection.keySet()) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(fieldName, this);
            String fieldValue = qcbjSection.get(fieldName);
            if (fieldValue.contains("s")) {
                fieldValue = String.valueOf(Integer.parseInt(fieldValue.split("s")[0]) * 1000);
            }
            if (fieldValue.contains("S")) {
                fieldValue = String.valueOf(Integer.parseInt(fieldValue.split("S")[0]) * 1000);
            }
            fieldValueByFieldName.setValue(StringUtil.decToHex(fieldValue, 8));
        }
        this.QCDI_Count.setValue(StringUtil.decToHex(String.valueOf(qcAlarmFile.getQcdiNum()), 8));
        this.QHCJ_Count.setValue(StringUtil.decToHex(String.valueOf(qcAlarmFile.getQhcjNum()), 8));
        this.DCCJ_Count.setValue(StringUtil.decToHex(String.valueOf(qcAlarmFile.getDccjNum()), 8));
        int currentOffset = 15 * 4;
        this.QCDI_Offset.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
        for (LinkedHashMap<String, String> qcdiBlock : qcAlarmFile.getQcdi().values()) {
            QCDIBean qcdiBean = new QCDIBean();
            qcdiBean.setAll(qcdiBlock);
            if (qcdiBean.getWQYC_ALARM_DELAY().getValue() == null) {
                qcdiBean.getWQYC_ALARM_DELAY().setValue(this.WQYC_ALARM_DELAY.getValue());
            }
            if (qcdiBean.getYQWC_ALARM_DELAY().getValue() == null) {
                qcdiBean.getYQWC_ALARM_DELAY().setValue(this.YQWC_ALARM_DELAY.getValue());
            }
            this.QCDIInfo.add(qcdiBean);
            currentOffset += qcdiBean.getAllContent().size() * 4;
        }
        this.QHCJ_Offset.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
        for (LinkedHashMap<String, String> qhcjBlock : qcAlarmFile.getQhcj().values()) {
            QHCJBean qhcjBean = new QHCJBean();
            qhcjBean.setAll(qhcjBlock);
            if (qhcjBean.getCJYC_ALARM_DELAY().getValue() == null) {
                qhcjBean.getCJYC_ALARM_DELAY().setValue(this.CJYC_ALARM_DELAY.getValue());
            }
            this.QHCJInfo.add(qhcjBean);
            currentOffset += qhcjBean.getAllContent().size() * 4;

        }
        this.DCCJ_Offset.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
        for (LinkedHashMap<String, String> dccjBlock : qcAlarmFile.getDccj().values()) {
            DCCJBean dccjBean = new DCCJBean();
            dccjBean.setAll(dccjBlock);
            if (dccjBean.getCJYC_ALARM_DELAY().getValue() == null) {
                dccjBean.getCJYC_ALARM_DELAY().setValue(this.CJYC_ALARM_DELAY.getValue());
            }
            this.DCCJInfo.add(dccjBean);

        }

    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getINDEX() {
        return INDEX;
    }

    public void setINDEX(BytesInfo INDEX) {
        this.INDEX = INDEX;
    }

    public BytesInfo getDOWN_FLAG() {
        return DOWN_FLAG;
    }

    public void setDOWN_FLAG(BytesInfo DOWN_FLAG) {
        this.DOWN_FLAG = DOWN_FLAG;
    }

    public BytesInfo getMANUAL_FLAG() {
        return MANUAL_FLAG;
    }

    public void setMANUAL_FLAG(BytesInfo MANUAL_FLAG) {
        this.MANUAL_FLAG = MANUAL_FLAG;
    }

    public BytesInfo getYQWC_ALARM_FLAG() {
        return YQWC_ALARM_FLAG;
    }

    public void setYQWC_ALARM_FLAG(BytesInfo YQWC_ALARM_FLAG) {
        this.YQWC_ALARM_FLAG = YQWC_ALARM_FLAG;
    }

    public BytesInfo getWQYC_ALARM_FLAG() {
        return WQYC_ALARM_FLAG;
    }

    public void setWQYC_ALARM_FLAG(BytesInfo WQYC_ALARM_FLAG) {
        this.WQYC_ALARM_FLAG = WQYC_ALARM_FLAG;
    }

    public BytesInfo getCJYC_ALARM_FLAG() {
        return CJYC_ALARM_FLAG;
    }

    public void setCJYC_ALARM_FLAG(BytesInfo CJYC_ALARM_FLAG) {
        this.CJYC_ALARM_FLAG = CJYC_ALARM_FLAG;
    }

    public BytesInfo getYQWC_ALARM_DELAY() {
        return YQWC_ALARM_DELAY;
    }

    public void setYQWC_ALARM_DELAY(BytesInfo YQWC_ALARM_DELAY) {
        this.YQWC_ALARM_DELAY = YQWC_ALARM_DELAY;
    }

    public BytesInfo getWQYC_ALARM_DELAY() {
        return WQYC_ALARM_DELAY;
    }

    public void setWQYC_ALARM_DELAY(BytesInfo WQYC_ALARM_DELAY) {
        this.WQYC_ALARM_DELAY = WQYC_ALARM_DELAY;
    }

    public BytesInfo getCJYC_ALARM_DELAY() {
        return CJYC_ALARM_DELAY;
    }

    public void setCJYC_ALARM_DELAY(BytesInfo CJYC_ALARM_DELAY) {
        this.CJYC_ALARM_DELAY = CJYC_ALARM_DELAY;
    }

    public BytesInfo getQCDI_Count() {
        return QCDI_Count;
    }

    public void setQCDI_Count(BytesInfo QCDI_Count) {
        this.QCDI_Count = QCDI_Count;
    }

    public BytesInfo getQCDI_Offset() {
        return QCDI_Offset;
    }

    public void setQCDI_Offset(BytesInfo QCDI_Offset) {
        this.QCDI_Offset = QCDI_Offset;
    }

    public BytesInfo getQHCJ_Count() {
        return QHCJ_Count;
    }

    public void setQHCJ_Count(BytesInfo QHCJ_Count) {
        this.QHCJ_Count = QHCJ_Count;
    }

    public BytesInfo getQHCJ_Offset() {
        return QHCJ_Offset;
    }

    public void setQHCJ_Offset(BytesInfo QHCJ_Offset) {
        this.QHCJ_Offset = QHCJ_Offset;
    }

    public BytesInfo getDCCJ_Count() {
        return DCCJ_Count;
    }

    public void setDCCJ_Count(BytesInfo DCCJ_Count) {
        this.DCCJ_Count = DCCJ_Count;
    }

    public BytesInfo getDCCJ_Offset() {
        return DCCJ_Offset;
    }

    public void setDCCJ_Offset(BytesInfo DCCJ_Offset) {
        this.DCCJ_Offset = DCCJ_Offset;
    }

    public List<QCDIBean> getQCDIInfo() {
        return QCDIInfo;
    }

    public void setQCDIInfo(List<QCDIBean> QCDIInfo) {
        this.QCDIInfo = QCDIInfo;
    }

    public List<QHCJBean> getQHCJInfo() {
        return QHCJInfo;
    }

    public void setQHCJInfo(List<QHCJBean> QHCJInfo) {
        this.QHCJInfo = QHCJInfo;
    }

    public List<DCCJBean> getDCCJInfo() {
        return DCCJInfo;
    }

    public void setDCCInfo(List<DCCJBean> DCCJInfo) {
        this.DCCJInfo = DCCJInfo;
    }
}
