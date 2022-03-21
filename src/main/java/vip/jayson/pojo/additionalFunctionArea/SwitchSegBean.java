package vip.jayson.pojo.additionalFunctionArea;

import vip.jayson.config.CAAData;
import vip.jayson.pojo.dataBean.ConfigBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SwitchSegBean {
    private BytesInfo Count = new BytesInfo("UNIT", 4, "Count");
    private BytesInfo SWITCH_DELAY_CYCLES = new BytesInfo("UNIT", 4, "SWITCH_DELAY_CYCLES", StringUtil.decToHex("3", 8));
    private BytesInfo Reserved_Field_1 = new BytesInfo("UNIT", 4, "Reserved_Field_1", StringUtil.decToHex("0", 8));
    private BytesInfo Reserved_Field_2 = new BytesInfo("UNIT", 4, "Reserved_Field_2", StringUtil.decToHex("0", 8));
    private BytesInfo Reserved_Field_3 = new BytesInfo("UNIT", 4, "Reserved_Field_3", StringUtil.decToHex("0", 8));
    private BytesInfo Reserved_Field_4 = new BytesInfo("UNIT", 4, "Reserved_Field_4", StringUtil.decToHex("0", 8));
    private BytesInfo Reserved_Field_5 = new BytesInfo("UNIT", 4, "Reserved_Field_5", StringUtil.decToHex("0", 8));
    private BytesInfo Reserved_Field_6 = new BytesInfo("UNIT", 4, "Reserved_Field_6", StringUtil.decToHex("0", 8));
    private List<GASInfoBean> GASInfo = new ArrayList<>();
    public static String[] order = {"Count","SWITCH_DELAY_CYCLES","Reserved_Field_1","Reserved_Field_2","Reserved_Field_3","Reserved_Field_4","Reserved_Field_5","Reserved_Field_6","GASInfo"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
            for (int i = 0; i < order.length - 1; i++) {
                BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
                allContent.add(fieldValueByFieldName);
            }
            for (GASInfoBean info : this.GASInfo) {
                allContent.addAll(info.getAllContent());
            }
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(LinkedHashMap<String, LinkedHashMap<String, List<String>>> switchInfo) {
        for (String blockKey : switchInfo.keySet()) {
            for (List<String> index : switchInfo.get(blockKey).values()) {
                if (index.size() > 1 && index.get(1).equals("00000064")) {
                    GASInfoBean gasInfoBean = new GASInfoBean();
                    gasInfoBean.setAll(index);
                    this.GASInfo.add(gasInfoBean);
                }
            }
        }
        this.Count.setValue(StringUtil.decToHex(String.valueOf(this.GASInfo.size()),8));
        ConfigBean configBean = CAAData.configBean;
        if (configBean.getGlobal().containsKey("SWITCH_DELAY_CYCLES")) {
            this.SWITCH_DELAY_CYCLES.setValue(StringUtil.decToHex(configBean.getGlobal().get("SWITCH_DELAY_CYCLES"),8));
        }
    }
    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getCount() {
        return Count;
    }

    public void setCount(BytesInfo count) {
        Count = count;
    }

    public BytesInfo getSWITCH_DELAY_CYCLES() {
        return SWITCH_DELAY_CYCLES;
    }

    public void setSWITCH_DELAY_CYCLES(BytesInfo SWITCH_DELAY_CYCLES) {
        this.SWITCH_DELAY_CYCLES = SWITCH_DELAY_CYCLES;
    }

    public BytesInfo getReserved_Field_1() {
        return Reserved_Field_1;
    }

    public void setReserved_Field_1(BytesInfo reserved_Field_1) {
        Reserved_Field_1 = reserved_Field_1;
    }

    public BytesInfo getReserved_Field_2() {
        return Reserved_Field_2;
    }

    public void setReserved_Field_2(BytesInfo reserved_Field_2) {
        Reserved_Field_2 = reserved_Field_2;
    }

    public BytesInfo getReserved_Field_3() {
        return Reserved_Field_3;
    }

    public void setReserved_Field_3(BytesInfo reserved_Field_3) {
        Reserved_Field_3 = reserved_Field_3;
    }

    public BytesInfo getReserved_Field_4() {
        return Reserved_Field_4;
    }

    public void setReserved_Field_4(BytesInfo reserved_Field_4) {
        Reserved_Field_4 = reserved_Field_4;
    }

    public BytesInfo getReserved_Field_5() {
        return Reserved_Field_5;
    }

    public void setReserved_Field_5(BytesInfo reserved_Field_5) {
        Reserved_Field_5 = reserved_Field_5;
    }

    public BytesInfo getReserved_Field_6() {
        return Reserved_Field_6;
    }

    public void setReserved_Field_6(BytesInfo reserved_Field_6) {
        Reserved_Field_6 = reserved_Field_6;
    }

    public List<GASInfoBean> getGASInfo() {
        return GASInfo;
    }

    public void setGASInfo(List<GASInfoBean> GASInfo) {
        this.GASInfo = GASInfo;
    }


}
