package vip.jayson.pojo.ioArea;

import vip.jayson.config.CAAData;
import vip.jayson.pojo.additionalFunctionArea.SwitchInterfaceBlock;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.pojo.tisSwitchDataArea.TISInterfaceBlock;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.*;

public class CBI3BlockBean {
    private int startAddr;
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo Protocol_Version = new BytesInfo("UNIT", 4, "Protocol_Version");
    private BytesInfo Data_Version = new BytesInfo("UNIT", 4, "Data_Version");
    private BytesInfo Forgive = new BytesInfo("UNIT", 4, "Forgive");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Receive_ZJXX_Count = new BytesInfo("UNIT", 4, "Receive_ZJXX_Count");
    private BytesInfo Receive_YL_Count = new BytesInfo("UNIT", 4, "Receive_YL_Count");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private BytesInfo Send_ZJXX_Count = new BytesInfo("UNIT", 4, "Send_ZJXX_Count");
    private BytesInfo Send_YL_Count = new BytesInfo( "UNIT", 4, "Send_YL_Count");
    private List<BytesInfo> Receive_Codes = new ArrayList<>();
    private List<BytesInfo> Send_Codes = new ArrayList<>();
    public static String[] order = {"PID","Index","Local_ID","Remote_ID","Protocol_Version","Data_Version","Forgive","Receive_Count","Receive_ZJXX_Count","Receive_YL_Count","Send_Count","Send_ZJXX_Count","Send_YL_Count","Receive_Codes","Send_Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 2; i++) {
                BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
                allContent.add(fieldValueByFieldName);
            }
            allContent.addAll(Receive_Codes);
            allContent.addAll(Send_Codes);
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(InterfaceFileBean oneBlock) {
        LinkedHashMap<String, CodeInfoBean> global = oneBlock.getGlobal();
        List<CodeBean> receive = oneBlock.getReceive();
        List<CodeBean> send = oneBlock.getSend();
        if (!global.containsKey("PID")) {
            int pid  = CAAData.pid.get("CBI3").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("CBI3").get("Step");
            SwitchInterfaceBlock.switchInfo.get("CBI3").put(StringUtil.hexToDec(this.Index.getValue()), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            TISInterfaceBlock.tisInfo.get("CBI3").put(StringUtil.hexToDec(this.Index.getValue()), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
        } else {
            SwitchInterfaceBlock.switchInfo.get("CBI3").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            TISInterfaceBlock.tisInfo.get("CBI3").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
        }
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INDEX")) {
                this.Index.setValue(StringUtil.decToHex(value, 8));
                TISInterfaceBlock.tisInfo.get("CBI3").get(value).add(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("LOCALID")) {
                this.Local_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("REMOTEID")) {
                this.Remote_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("PR_VER")) {
                this.Protocol_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("DA_VER")) {
                this.Data_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("FORGIVE")) {
                this.Forgive.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value)*1000), 8));
            }
            if (globalKey.equals("SWITCH_WEIGHT")) {
                SwitchInterfaceBlock.switchInfo.get("CBI3").get(StringUtil.hexToDec(this.Index.getValue())).add(StringUtil.decToHex(value, 8));
            }
        }
        for (CodeBean codeBean : receive) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Receive_Count.setValue(StringUtil.decToHex(receiveLength,8));

            } else{
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = "Receive_" + codeBean.getBlockName() + "_Count";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Receive Code");
                    this.Receive_Codes.add(bytesInfo);
                }
            }
        }
        for (CodeBean codeBean : send) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String sendLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Send_Count.setValue(StringUtil.decToHex(sendLength,8));

            } else {
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = "Send_" + codeBean.getBlockName() + "_Count";
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

    public BytesInfo getData_Version() {
        return Data_Version;
    }

    public void setData_Version(BytesInfo data_Version) {
        Data_Version = data_Version;
    }

    public BytesInfo getForgive() {
        return Forgive;
    }

    public void setForgive(BytesInfo forgive) {
        Forgive = forgive;
    }

    public BytesInfo getReceive_Count() {
        return Receive_Count;
    }

    public void setReceive_Count(BytesInfo receive_Count) {
        Receive_Count = receive_Count;
    }

    public BytesInfo getReceive_ZJXX_Count() {
        return Receive_ZJXX_Count;
    }

    public void setReceive_ZJXX_Count(BytesInfo receive_ZJXX_Count) {
        Receive_ZJXX_Count = receive_ZJXX_Count;
    }

    public BytesInfo getReceive_YL_Count() {
        return Receive_YL_Count;
    }

    public void setReceive_YL_Count(BytesInfo receive_YL_Count) {
        Receive_YL_Count = receive_YL_Count;
    }

    public BytesInfo getSend_Count() {
        return Send_Count;
    }

    public void setSend_Count(BytesInfo send_Count) {
        Send_Count = send_Count;
    }

    public BytesInfo getSend_ZJXX_Count() {
        return Send_ZJXX_Count;
    }

    public void setSend_ZJXX_Count(BytesInfo send_ZJXX_Count) {
        Send_ZJXX_Count = send_ZJXX_Count;
    }

    public BytesInfo getSend_YL_Count() {
        return Send_YL_Count;
    }

    public void setSend_YL_Count(BytesInfo send_YL_Count) {
        Send_YL_Count = send_YL_Count;
    }

    public List<BytesInfo> getReceive_Codes() {
        return Receive_Codes;
    }

    public void setReceive_Codes(List<BytesInfo> receive_Codes) {
        Receive_Codes = receive_Codes;
    }

    public List<BytesInfo> getSend_Codes() {
        return Send_Codes;
    }

    public void setSend_Codes(List<BytesInfo> send_Codes) {
        Send_Codes = send_Codes;
    }
}
