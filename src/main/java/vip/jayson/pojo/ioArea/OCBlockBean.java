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

public class OCBlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Interface_Type = new BytesInfo("UNIT", 4, "Interface_Type");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo Protocol_Version = new BytesInfo("UNIT", 4, "Protocol_Version");
    private BytesInfo Data_Version = new BytesInfo("UNIT", 4, "Data_Version");
    private BytesInfo Timeout = new BytesInfo("UNIT", 4, "Timeout");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private List<BytesInfo> Receive_Codes = new ArrayList<>();
    private List<BytesInfo> Send_Codes = new ArrayList<>();
    public static String[] order = {"PID", "Interface_Type", "Index","Local_ID","Remote_ID","Protocol_Version","Data_Version","Timeout","Receive_Count","Send_Count","Receive_Codes","Send_Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 2; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(this.Receive_Codes);
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

    public BytesInfo getInterface_Type() {
        return Interface_Type;
    }

    public void setInterface_Type(BytesInfo interface_Type) {
        Interface_Type = interface_Type;
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

    public BytesInfo getTimeout() {
        return Timeout;
    }

    public void setTimeout(BytesInfo timeout) {
        Timeout = timeout;
    }

    public BytesInfo getReceive_Count() {
        return Receive_Count;
    }

    public void setReceive_Count(BytesInfo receive_Count) {
        Receive_Count = receive_Count;
    }

    public BytesInfo getSend_Count() {
        return Send_Count;
    }

    public void setSend_Count(BytesInfo send_Count) {
        Send_Count = send_Count;
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
    public void setAll(InterfaceFileBean oneBlock){
        System.out.println();
        LinkedHashMap<String, CodeInfoBean> global = oneBlock.getGlobal();
        List<CodeBean> receive = oneBlock.getReceive();
        List<CodeBean> send = oneBlock.getSend();
        if (!global.containsKey("PID")) {
            int pid  = CAAData.pid.get("OC").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("OC").get("Step");
            SwitchInterfaceBlock.switchInfo.get("OC").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            TISInterfaceBlock.tisInfo.get("OC").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
        } else {
            SwitchInterfaceBlock.switchInfo.get("OC").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            TISInterfaceBlock.tisInfo.get("OC").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
        }
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INTERFACETYPE")) {
                this.Interface_Type.setValue(StringUtil.decToHex(codeInfoBean.getValue(), 8));

            }
            if (globalKey.equals("INDEX")) {
                TISInterfaceBlock.tisInfo.get("OC").get(value).add(StringUtil.decToHex(value, 8));
                this.Index.setValue(StringUtil.decToHex(codeInfoBean.getValue(), 8));

            }
            if (globalKey.equals("LOCALID")) {
                this.Local_ID.setValue(StringUtil.decToHex(codeInfoBean.getValue(), 8));

            }
            if (globalKey.equals("REMOTEID")) {
                this.Remote_ID.setValue(StringUtil.decToHex(codeInfoBean.getValue(), 8));

            }
            if (globalKey.equals("PR_VER")) {
                this.Protocol_Version.setValue(StringUtil.decToHex(codeInfoBean.getValue(), 8));

            }
            if (globalKey.equals("DA_VER")) {
                this.Data_Version.setValue(StringUtil.decToHex(codeInfoBean.getValue(), 8));

            }
            if (globalKey.equals("APP_TIMEOUT")) {
                this.Timeout.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(codeInfoBean.getValue()) * 1000), 8));

            }
            if (globalKey.equals("SWITCH_WEIGHT")) {
                SwitchInterfaceBlock.switchInfo.get("OC").get(global.get("INDEX").getValue()).add(StringUtil.decToHex(value, 8));
            }
        }
        for (CodeBean codeBean : receive) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Receive_Count.setValue(StringUtil.decToHex(receiveLength,8));
            } else if (codeBean.getBlockName().contains("NEUTRAL_CODE")){
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
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
            } else if (codeBean.getBlockName().contains("NEUTRAL_CODE")){
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    this.Send_Codes.add(bytesInfo);
                }
            }
        }
    }
    public Integer getLength(){
        int receiveCodeNum = Integer.parseInt(StringUtil.hexToDec(this.Receive_Count.getValue()));
        int sendCodeNum = Integer.parseInt(StringUtil.hexToDec(this.Send_Count.getValue()));
        return 4 * (10 + receiveCodeNum + sendCodeNum);
    }


}
