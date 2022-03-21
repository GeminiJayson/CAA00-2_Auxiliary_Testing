package vip.jayson.pojo.additionalFunctionArea;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GASInfoBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo DURATION = new BytesInfo("UNIT", 4, "DURATION", StringUtil.decToHex("0", 8));
    private BytesInfo SWITCH_WEIGHT = new BytesInfo("UNIT", 4, "SWITCH_WEIGHT");
    private BytesInfo SINGLE_JUDGMENT = new BytesInfo("UNIT", 4, "SINGLE_JUDGMENT", StringUtil.decToHex("0", 8));
    public static String[] order = {"PID","DURATION","SWITCH_WEIGHT","SINGLE_JUDGMENT"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
            for (int i = 0; i < order.length; i++) {
                BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
                allContent.add(fieldValueByFieldName);
            }
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(List<String> switchBlockInfo) {
        this.PID.setValue(switchBlockInfo.get(0));
        this.SWITCH_WEIGHT.setValue(switchBlockInfo.get(1));
    }
    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getPID() {
        return PID;
    }

    public void setPID(BytesInfo PID) {
        this.PID = PID;
    }

    public BytesInfo getDURATION() {
        return DURATION;
    }

    public void setDURATION(BytesInfo DURATION) {
        this.DURATION = DURATION;
    }

    public BytesInfo getSWITCH_WEIGHT() {
        return SWITCH_WEIGHT;
    }

    public void setSWITCH_WEIGHT(BytesInfo SWITCH_WEIGHT) {
        this.SWITCH_WEIGHT = SWITCH_WEIGHT;
    }

    public BytesInfo getSINGLE_JUDGMENT() {
        return SINGLE_JUDGMENT;
    }

    public void setSINGLE_JUDGMENT(BytesInfo SINGLE_JUDGMENT) {
        this.SINGLE_JUDGMENT = SINGLE_JUDGMENT;
    }
}
