package vip.jayson.pojo.ioArea;

import vip.jayson.config.CAAData;
import vip.jayson.main.GenerateADSExcel;
import vip.jayson.pojo.additionalFunctionArea.SwitchInterfaceBlock;
import vip.jayson.pojo.dataBean.CodeAssignBean;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MMIBlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Safe_Command_Result_Time = new BytesInfo("UNIT", 4, "Safe_Command_Result_Time", "00001388");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo Protocol_Type = new BytesInfo("UNIT", 4, "Protocol_Type");
    private BytesInfo Safe_Command_Time = new BytesInfo("UNIT", 4, "Safe_Command_Time");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private List<MMIReceiveCodeBean> Receive_Codes = new ArrayList<>();
    private List<BytesInfo> Send_Codes = new ArrayList<>();
    public BytesInfo getPID() {
        return PID;
    }
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            allContent.add(this.PID);
        allContent.add(this.Safe_Command_Result_Time);
        allContent.add(this.Index);
        allContent.add(this.Local_ID);
        allContent.add(this.Remote_ID);
        allContent.add(this.Protocol_Type);
        allContent.add(this.Safe_Command_Time);
        allContent.add(this.Receive_Count);
        allContent.add(this.Send_Count);
        for (MMIReceiveCodeBean receive_code : this.Receive_Codes) {
            allContent.addAll(receive_code.getAllContent());
        }
        allContent.addAll(this.Send_Codes);}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setPID(BytesInfo PID) {
        this.PID = PID;
    }

    public BytesInfo getSafe_Command_Result_Time() {
        return Safe_Command_Result_Time;
    }

    public void setSafe_Command_Result_Time(BytesInfo safe_Command_Result_Time) {
        Safe_Command_Result_Time = safe_Command_Result_Time;
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

    public BytesInfo getProtocol_Type() {
        return Protocol_Type;
    }

    public void setProtocol_Type(BytesInfo protocol_Type) {
        Protocol_Type = protocol_Type;
    }

    public BytesInfo getSafe_Command_Time() {
        return Safe_Command_Time;
    }

    public void setSafe_Command_Time(BytesInfo safe_Command_Time) {
        Safe_Command_Time = safe_Command_Time;
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

    public List<MMIReceiveCodeBean> getReceive_Codes() {
        return Receive_Codes;
    }

    public void setReceive_Codes(List<MMIReceiveCodeBean> receive_Codes) {
        Receive_Codes = receive_Codes;
    }

    public List<BytesInfo> getSend_Codes() {
        return Send_Codes;
    }

    public void setSend_Codes(List<BytesInfo> send_Codes) {
        Send_Codes = send_Codes;
    }

    public void setAll(InterfaceFileBean oneBlock){
        LinkedHashMap<String, CodeInfoBean> global = oneBlock.getGlobal();
        List<CodeBean> receive = oneBlock.getReceive();
        List<CodeBean> send = oneBlock.getSend();
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();
            this.PID.setValue(StringUtil.decToHex("0", 8));

            if (globalKey.equals("SAFECOMMAND_OPERATION_RESULT_JUDGMENT_TIME")) {
                this.Safe_Command_Result_Time.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value)*1000), 8));

            }
            if (globalKey.equals("INDEX")) {
                this.Index.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("LOCALID")) {
                this.Local_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("REMOTEID")) {
                this.Remote_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("PROTOCOL_TYPE")) {
                this.Protocol_Type.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("SAFECOMMAND_OPERATION_INTERVAL_TIME")) {
                this.Safe_Command_Time.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value)*1000), 8));
            }
        }
        for (CodeBean codeBean : receive) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Receive_Count.setValue(StringUtil.decToHex(receiveLength,8));

            } else if (codeBean.getBlockName().contains("NEUTRAL_CODE")){
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                for (String key : codeInfos.keySet()) {
                    CodeInfoBean codeInfoBean = codeInfos.get(key);
                    MMIReceiveCodeBean mmiReceiveCodeBean = new MMIReceiveCodeBean();
                    mmiReceiveCodeBean.getSignal().setValue(codeInfoBean.getCodeNum());
                    mmiReceiveCodeBean.getIndex().setValue(StringUtil.decToHex(key,8));
                    String codeName = codeInfoBean.getCodeName();
                    if (CAAData.mmiSafeCommand.containsKey(codeName)) {
                        List<String> strings = CAAData.mmiSafeCommand.get(codeName);
                        if (strings.size() == 1) {
                            mmiReceiveCodeBean.getSafe_Command().setValue(StringUtil.decToHex("1",8));
                            mmiReceiveCodeBean.getRetern_Value().setValue(StringUtil.decToHex("0",8));
                            mmiReceiveCodeBean.getSafe_Command_Value().setValue(StringUtil.decToHex("0",8));
                            mmiReceiveCodeBean.getSafe_Command_Signal().setValue(StringUtil.decToHex("0",8));
                        } else {
                            mmiReceiveCodeBean.getSafe_Command().setValue(StringUtil.decToHex("1",8));
                            mmiReceiveCodeBean.getRetern_Value().setValue(StringUtil.decToHex("1",8));
                            mmiReceiveCodeBean.getSafe_Command_Value().setValue(StringUtil.decToHex(strings.get(2),8));
                            for (String s : CAAData.codeAssignList.keySet()) {
                                List<CodeAssignBean> codeAssignBeans = CAAData.codeAssignList.get(GenerateADSExcel.currentStationName);
                                for (CodeAssignBean codeAssignBean : codeAssignBeans) {
                                    List<CodeInfoBean> codeInfoList = codeAssignBean.getCodeInfoList();
                                    for (CodeInfoBean infoBean : codeInfoList) {
                                        if (infoBean.getCodeName().equals(strings.get(1))) {
                                            mmiReceiveCodeBean.getSafe_Command_Signal().setValue(infoBean.getCodeNum());
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        mmiReceiveCodeBean.getSafe_Command().setValue(StringUtil.decToHex("0",8));
                        mmiReceiveCodeBean.getRetern_Value().setValue(StringUtil.decToHex("0",8));
                        mmiReceiveCodeBean.getSafe_Command_Value().setValue(StringUtil.decToHex("0",8));
                        mmiReceiveCodeBean.getSafe_Command_Signal().setValue(StringUtil.decToHex("0",8));
                    }
                    this.Receive_Codes.add(mmiReceiveCodeBean);
                }
            }
        }
        for (CodeBean codeBean : send) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Send_Count.setValue(StringUtil.decToHex(receiveLength,8));

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
        return 4 * (9 + this.Send_Codes.size()) + this.Receive_Codes.size() * MMIReceiveCodeBean.getLength();
    }
}
