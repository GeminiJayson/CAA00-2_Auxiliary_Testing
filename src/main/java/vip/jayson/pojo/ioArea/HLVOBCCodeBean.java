package vip.jayson.pojo.ioArea;

import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HLVOBCCodeBean {
    private BytesInfo ID = new BytesInfo("UNIT", 4, "ID");
    private List<BytesInfo> Codes = new ArrayList<>();
    private List<HLVOBCCodeBean> idCodeInfoList = new ArrayList<>();
    public static String[] order = {"ID","Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<HLVOBCCodeBean> getIdCodeInfoList() {
        return idCodeInfoList;
    }

    public void setIdCodeInfoList(List<HLVOBCCodeBean> idCodeInfoList) {
        this.idCodeInfoList = idCodeInfoList;
    }

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (HLVOBCCodeBean hlvobcCodeBean : this.idCodeInfoList) {
            allContent.add(hlvobcCodeBean.ID);
            allContent.addAll(hlvobcCodeBean.Codes);
        }}
        return allContent;
    }
    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(CodeBean codeBean){
        String blockName = codeBean.getBlockName();
        switch (blockName){
            case "GDQD":{

                this.getID().setValue(StringUtil.decToHex(String.valueOf(codeBean.getFlagInfo().get("GDQDID")), 8));
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    if (codeName.equals("DEFAULT")) {
                        codeNum = "00000000";
                    }
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    this.getCodes().add(bytesInfo);
                }
                this.idCodeInfoList.add(this);
                break;
            }
            case "XHJ":
            case "PSD":
            case "PSDFAO":{
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                for (String id : codeInfos.keySet()) {
                    HLVOBCCodeBean hlvobcCodeBean = new HLVOBCCodeBean();
                    hlvobcCodeBean.getID().setValue(StringUtil.decToHex(id, 8));
                    String codeName = codeInfos.get(id).getCodeName();
                    String codeNum = codeInfos.get(id).getCodeNum();
                    if (codeName.equals("DEFAULT")) {
                        codeNum = "00000000";
                    }
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    hlvobcCodeBean.getCodes().add(bytesInfo);
                    this.idCodeInfoList.add(hlvobcCodeBean);
                }
                break;
            }
            default:break;
        }
    }
    public Integer getLength() {
        int length = 0;
        for (HLVOBCCodeBean hlvobcCodeBean : this.idCodeInfoList) {
            length += (hlvobcCodeBean.getCodes().size() + 1) * 4;
        }
        return length;
    }
    public BytesInfo getID() {
        return ID;
    }

    public void setID(BytesInfo ID) {
        this.ID = ID;
    }

    public List<BytesInfo> getCodes() {
        return Codes;
    }

    public void setCodes(List<BytesInfo> codes) {
        Codes = codes;
    }
}
