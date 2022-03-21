package vip.jayson.pojo.ioArea;

import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TCC4SendJlXXCodeBean {
    private BytesInfo STA = new BytesInfo("UNIT", 4, "STA");
    private BytesInfo JLH = new BytesInfo("UNIT", 4, "JLH");
    private BytesInfo QDS = new BytesInfo("UNIT", 4, "QDS");
    private BytesInfo Count = new BytesInfo("UNIT", 4, "Count");
    private List<BytesInfo> JLXX_Codes = new ArrayList<>();
    public static String[] order = {"STA","JLH","QDS","Count","JLXX_Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 1; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(this.JLXX_Codes);}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public BytesInfo getSTA() {
        return STA;
    }

    public void setSTA(BytesInfo STA) {
        this.STA = STA;
    }

    public BytesInfo getJLH() {
        return JLH;
    }

    public void setJLH(BytesInfo JLH) {
        this.JLH = JLH;
    }

    public BytesInfo getQDS() {
        return QDS;
    }

    public void setQDS(BytesInfo QDS) {
        this.QDS = QDS;
    }

    public BytesInfo getCount() {
        return Count;
    }

    public void setCount(BytesInfo count) {
        Count = count;
    }

    public List<BytesInfo> getJLXX_Codes() {
        return JLXX_Codes;
    }

    public void setJLXX_Codes(List<BytesInfo> JLXX_Codes) {
        this.JLXX_Codes = JLXX_Codes;
    }
    public void setAll(CodeBean codeBean) {
        Map<String, Integer> flagInfo = codeBean.getFlagInfo();
        this.STA.setValue(StringUtil.decToHex(String.valueOf(flagInfo.get("STA")), 8));
        this.JLH.setValue(StringUtil.decToHex(String.valueOf(flagInfo.get("JLH")), 8));
        this.QDS.setValue(StringUtil.decToHex(String.valueOf(flagInfo.get("QDS")), 8));
        LinkedHashMap<String, CodeInfoBean> codeInfo = codeBean.getCodeInfo();
        this.Count.setValue(StringUtil.decToHex(String.valueOf(codeInfo.size()), 8));
        for (CodeInfoBean value : codeInfo.values()) {
            BytesInfo bytesInfo = new BytesInfo("UNIT", 4, value.getCodeName(), value.getCodeNum(), "JLXX_Send");
            this.JLXX_Codes.add(bytesInfo);
        }
    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
}
