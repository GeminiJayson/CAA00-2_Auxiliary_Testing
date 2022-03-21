package vip.jayson.pojo.ioArea;

import vip.jayson.config.CAAData;
import vip.jayson.pojo.additionalFunctionArea.SwitchInterfaceBlock;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class RBC1BlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo Protocol_Version = new BytesInfo("UNIT", 4, "Protocol_Version");
    private BytesInfo Configure_Version = new BytesInfo("UNIT", 4, "Configure_Version");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private BytesInfo ZNQD_Count = new BytesInfo("UNIT", 4, "ZNQD_Count");
    private BytesInfo BSFX_Count = new BytesInfo("UNIT", 4, "BSFX_Count");
    private BytesInfo BSFQ_Count = new BytesInfo("UNIT", 4, "BSFQ_Count");
    private BytesInfo JLXX_Count = new BytesInfo("UNIT", 4, "JLXX_Count");
    private BytesInfo JJQ_Count = new BytesInfo("UNIT", 4, "JJQ_Count");
    private BytesInfo DCXX_Count = new BytesInfo("UNIT", 4, "DCXX_Count");
    private BytesInfo LCXHZT_Count = new BytesInfo("UNIT", 4, "LCXHZT_Count");
    private List<BytesInfo> Send_Codes = new ArrayList<>();
    public static String[] order = {"PID","Index","Local_ID","Remote_ID","Protocol_Version","Configure_Version","Send_Count","ZNQD_Count","BSFX_Count","BSFQ_Count","JLXX_Count","JJQ_Count","DCXX_Count","LCXHZT_Count","Send_Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 1; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(this.Send_Codes);}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public BytesInfo getPID() {
        return PID;
    }

    public void setPID(BytesInfo PID) {
        this.PID = PID;
    }

    public BytesInfo getIndex() {
        return Index;
    }

    public void setIndex(BytesInfo index) {
        Index = index;
    }

    public BytesInfo getLocal_ID() {
        return Local_ID;
    }

    public void setLocal_ID(BytesInfo local_ID) {
        Local_ID = local_ID;
    }

    public BytesInfo getRemote_ID() {
        return Remote_ID;
    }

    public void setRemote_ID(BytesInfo remote_ID) {
        Remote_ID = remote_ID;
    }

    public BytesInfo getProtocol_Version() {
        return Protocol_Version;
    }

    public void setProtocol_Version(BytesInfo protocol_Version) {
        Protocol_Version = protocol_Version;
    }

    public BytesInfo getConfigure_Version() {
        return Configure_Version;
    }

    public void setConfigure_Version(BytesInfo configure_Version) {
        Configure_Version = configure_Version;
    }

    public BytesInfo getSend_Count() {
        return Send_Count;
    }

    public void setSend_Count(BytesInfo send_Count) {
        Send_Count = send_Count;
    }

    public BytesInfo getZNQD_Count() {
        return ZNQD_Count;
    }

    public void setZNQD_Count(BytesInfo ZNQD_Count) {
        this.ZNQD_Count = ZNQD_Count;
    }

    public BytesInfo getBSFX_Count() {
        return BSFX_Count;
    }

    public void setBSFX_Count(BytesInfo BSFX_Count) {
        this.BSFX_Count = BSFX_Count;
    }

    public BytesInfo getBSFQ_Count() {
        return BSFQ_Count;
    }

    public void setBSFQ_Count(BytesInfo BSFQ_Count) {
        this.BSFQ_Count = BSFQ_Count;
    }

    public BytesInfo getJLXX_Count() {
        return JLXX_Count;
    }

    public void setJLXX_Count(BytesInfo JLXX_Count) {
        this.JLXX_Count = JLXX_Count;
    }

    public BytesInfo getJJQ_Count() {
        return JJQ_Count;
    }

    public void setJJQ_Count(BytesInfo JJQ_Count) {
        this.JJQ_Count = JJQ_Count;
    }

    public BytesInfo getDCXX_Count() {
        return DCXX_Count;
    }

    public void setDCXX_Count(BytesInfo DCXX_Count) {
        this.DCXX_Count = DCXX_Count;
    }

    public BytesInfo getLCXHZT_Count() {
        return LCXHZT_Count;
    }

    public void setLCXHZT_Count(BytesInfo LCXHZT_Count) {
        this.LCXHZT_Count = LCXHZT_Count;
    }

    public List<BytesInfo> getSend_Codes_Except_JLXX() {
        return Send_Codes;
    }

    public void setSend_Codes(List<BytesInfo> send_Codes_Except_JLXX) {
        Send_Codes = send_Codes_Except_JLXX;
    }

    public void setAll(InterfaceFileBean oneBlock) {
        LinkedHashMap<String, CodeInfoBean> global = oneBlock.getGlobal();
        List<CodeBean> send = oneBlock.getSend();
        if (!global.containsKey("PID")) {
            int pid  = CAAData.pid.get("RBC1").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("RBC1").get("Step");
            SwitchInterfaceBlock.switchInfo.get("RBC1").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
        } else {
            SwitchInterfaceBlock.switchInfo.get("RBC1").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
        }
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INDEX")) {
                this.Index.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("LOCALID")) {
                this.Local_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("REMOTEID")) {
                this.Remote_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("DV")) {
                this.Protocol_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("CV")) {
                this.Configure_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("SWITCH_WEIGHT")) {
                SwitchInterfaceBlock.switchInfo.get("RBC1").get(global.get("INDEX").getValue()).add(StringUtil.decToHex(value, 8));
            }
        }
        for (CodeBean codeBean : send) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String sendLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Send_Count.setValue(StringUtil.decToHex(sendLength,8));

            } else {
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = codeBean.getBlockName() + "_Count";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    this.Send_Codes.add(bytesInfo);
                }
            }
        }

    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
}
