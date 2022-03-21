package vip.jayson.pojo.additionalFunctionArea;

import vip.jayson.main.GenerateADSExcel;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class DCCJBean {
    private BytesInfo IN_RACK_IN1COM = new BytesInfo("UNIT", 4, "IN_RACK_IN1COM_ID");
    private BytesInfo IN_RACK_IN2COM = new BytesInfo("UNIT", 4, "IN_RACK_IN2COM_ID");
    private BytesInfo IN_RACK_IN3COM = new BytesInfo("UNIT", 4, "IN_RACK_IN3COM_ID");
    private BytesInfo IN1 = new BytesInfo("UNIT", 4, "IN1_ID");
    private BytesInfo IN2 = new BytesInfo("UNIT", 4, "IN2_ID");
    private BytesInfo IN3 = new BytesInfo("UNIT", 4, "IN3_ID");
    private BytesInfo CJYC_ALARM_DELAY = new BytesInfo("UNIT", 4, "CJYC_ALARM_DELAY");
    public static String[] order = {"IN_RACK_IN1COM","IN_RACK_IN2COM","IN_RACK_IN3COM","IN1","IN2","IN3","CJYC_ALARM_DELAY"};
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

    public BytesInfo getIN_RACK_IN1COM() {
        return IN_RACK_IN1COM;
    }

    public void setIN_RACK_IN1COM(BytesInfo IN_RACK_IN1COM) {
        this.IN_RACK_IN1COM = IN_RACK_IN1COM;
    }

    public BytesInfo getIN_RACK_IN2COM() {
        return IN_RACK_IN2COM;
    }

    public void setIN_RACK_IN2COM(BytesInfo IN_RACK_IN2COM) {
        this.IN_RACK_IN2COM = IN_RACK_IN2COM;
    }

    public BytesInfo getIN_RACK_IN3COM() {
        return IN_RACK_IN3COM;
    }

    public void setIN_RACK_IN3COM(BytesInfo IN_RACK_IN3COM) {
        this.IN_RACK_IN3COM = IN_RACK_IN3COM;
    }

    public BytesInfo getIN1() {
        return IN1;
    }

    public void setIN1(BytesInfo IN1) {
        this.IN1 = IN1;
    }

    public BytesInfo getIN2() {
        return IN2;
    }

    public void setIN2(BytesInfo IN2) {
        this.IN2 = IN2;
    }

    public BytesInfo getIN3() {
        return IN3;
    }

    public void setIN3(BytesInfo IN3) {
        this.IN3 = IN3;
    }

    public BytesInfo getCJYC_ALARM_DELAY() {
        return CJYC_ALARM_DELAY;
    }

    public void setCJYC_ALARM_DELAY(BytesInfo CJYC_ALARM_DELAY) {
        this.CJYC_ALARM_DELAY = CJYC_ALARM_DELAY;
    }
}
