package vip.jayson.pojo.additionalFunctionArea;

import vip.jayson.main.GenerateADSExcel;
import vip.jayson.pojo.dataBean.QcAlarmBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class QCDIBean {
    private BytesInfo IN_RACK_INCOM = new BytesInfo("UNIT", 4, "IN_RACK_INCOM_ID");
    private BytesInfo OUT_RACK_INCOM = new BytesInfo("UNIT", 4, "OUT_RACK_INCOM_ID");
    private BytesInfo OUT_RACK_HZCOM = new BytesInfo("UNIT", 4, "OUT_RACK_HZCOM_ID");
    private BytesInfo IN = new BytesInfo("UNIT", 4, "IN_ID");
    private BytesInfo OUT = new BytesInfo("UNIT", 4, "OUT_ID");
    private BytesInfo YQWC_ALARM_DELAY = new BytesInfo("UNIT", 4, "YQWC_ALARM_DELAY");
    private BytesInfo WQYC_ALARM_DELAY = new BytesInfo("UNIT", 4, "WQYC_ALARM_DELAY");
    public static String[] order = {"IN_RACK_INCOM","OUT_RACK_INCOM","OUT_RACK_HZCOM","IN","OUT","YQWC_ALARM_DELAY","WQYC_ALARM_DELAY"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
        for (int i = 0; i < order.length; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(LinkedHashMap<String, String> block) {
        for (String fieldName : block.keySet()) {
            String objectName = fieldName;
            if (fieldName.contains("-RACK-")) {
                objectName = fieldName.replace("-RACK-", "_RACK_");
            }
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(objectName, this);
            String fieldValue = block.get(fieldName);
            if (Pattern.matches("[0-9]+s", fieldValue)) {
                fieldValue = String.valueOf(Integer.parseInt(fieldValue.split("s")[0]) * 1000);
            } else if (Pattern.matches("[0-9]+S", fieldValue)) {
                fieldValue = String.valueOf(Integer.parseInt(fieldValue.split("S")[0]) * 1000);
            } else {
                fieldValue = StringUtil.hexToDec(GenerateADSExcel.currentStationCodeInfo.get(fieldValue).getCodeNum());
            }
            fieldValueByFieldName.setValue(StringUtil.decToHex(fieldValue, 8));
        }
    }
    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getIN_RACK_INCOM() {
        return IN_RACK_INCOM;
    }

    public void setIN_RACK_INCOM(BytesInfo IN_RACK_INCOM) {
        this.IN_RACK_INCOM = IN_RACK_INCOM;
    }

    public BytesInfo getOUT_RACK_INCOM() {
        return OUT_RACK_INCOM;
    }

    public void setOUT_RACK_INCOM(BytesInfo OUT_RACK_INCOM) {
        this.OUT_RACK_INCOM = OUT_RACK_INCOM;
    }

    public BytesInfo getOUT_RACK_HZCOM() {
        return OUT_RACK_HZCOM;
    }

    public void setOUT_RACK_HZCOM(BytesInfo OUT_RACK_HZCOM) {
        this.OUT_RACK_HZCOM = OUT_RACK_HZCOM;
    }

    public BytesInfo getIN() {
        return IN;
    }

    public void setIN(BytesInfo IN) {
        this.IN = IN;
    }

    public BytesInfo getOUTD() {
        return OUT;
    }

    public void setOUT(BytesInfo OUT) {
        this.OUT = OUT;
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
}
