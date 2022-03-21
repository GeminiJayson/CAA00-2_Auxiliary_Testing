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

public class RBC4BlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Protocol = new BytesInfo("UNIT", 4, "Protocol");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo Data_Version = new BytesInfo("UNIT", 4, "Data_Version");
    private BytesInfo Configure_Version = new BytesInfo("UNIT", 4, "Configure_Version");
    private BytesInfo Forgive = new BytesInfo("UNIT", 4, "Forgive");
    private BytesInfo MSG_TO_SDM_FLAG = new BytesInfo("UNIT", 4, "MSG_TO_SDM_FLAG");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Receive_XNQD_Count = new BytesInfo("UNIT", 4, "Receive_XNQD_Count");
    private BytesInfo Receive_XNQD_Offset = new BytesInfo("UNIT", 4, "Receive_XNQD_Offset");
    private BytesInfo Receive_YCC_Count = new BytesInfo("UNIT", 4, "Receive_YCC_Count");
    private BytesInfo Receive_YCC_Offset = new BytesInfo("UNIT", 4, "Receive_YCC_Offset");
    private BytesInfo Receive_JJLCZT_Count = new BytesInfo("UNIT", 4, "Receive_JJLCZT_Count");
    private BytesInfo Receive_JJLCZT_Offset = new BytesInfo("UNIT", 4, "Receive_JJLCZT_Offset");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private BytesInfo Send_ZNQD_Count = new BytesInfo("UNIT", 4, "Send_ZNQD_Count");
    private BytesInfo Send_ZNQD_Offset = new BytesInfo("UNIT", 4, "Send_ZNQD_Offset");
    private BytesInfo Send_BSFX_Count = new BytesInfo("UNIT", 4, "Send_BSFX_Count");
    private BytesInfo Send_BSFX_Offset = new BytesInfo("UNIT", 4, "Send_BSFX_Offset");
    private BytesInfo Send_BSFQ_Count = new BytesInfo("UNIT", 4, "Send_BSFQ_Count");
    private BytesInfo Send_BSFQ_Offset = new BytesInfo("UNIT", 4, "Send_BSFQ_Offset");
    private BytesInfo Send_JLXX_Count = new BytesInfo("UNIT", 4, "Send_JLXX_Count");
    private BytesInfo Send_JLXX_Offset = new BytesInfo("UNIT", 4, "Send_JLXX_Offset");
    private BytesInfo Send_JJQ_Count = new BytesInfo("UNIT", 4, "Send_JJQ_Count");
    private BytesInfo Send_JJQ_Offset = new BytesInfo("UNIT", 4, "Send_JJQ_Offset");
    private BytesInfo Send_DCXX_Count = new BytesInfo("UNIT", 4, "Send_DCXX_Count");
    private BytesInfo Send_DCXX_Offset = new BytesInfo("UNIT", 4, "Send_DCXX_Offset");
    private BytesInfo Send_LCXHZT_Count = new BytesInfo("UNIT", 4, "Send_LCXHZT_Count");
    private BytesInfo Send_LCXHZT_Offset = new BytesInfo("UNIT", 4, "Send_LCXHZT_Offset");
    private BytesInfo Send_ZJJZ_Count = new BytesInfo("UNIT", 4, "Send_ZJJZ_Count");
    private BytesInfo Send_ZJJZ_Offset = new BytesInfo("UNIT", 4, "Send_ZJJZ_Offset");
    private BytesInfo Send_QJBSZS_Count = new BytesInfo("UNIT", 4, "Send_QJBSZS_Count");
    private BytesInfo Send_QJBSZS_Offset = new BytesInfo("UNIT", 4, "Send_QJBSZS_Offset");
    private List<BytesInfo> Receive_Codes = new ArrayList<>();
    private List<BytesInfo> Send_Codes = new ArrayList<>();
    public static String[] order = {"PID","Index","Protocol","Local_ID","Remote_ID","Data_Version","Configure_Version","Forgive","MSG_TO_SDM_FLAG","Receive_Count","Receive_XNQD_Count","Receive_XNQD_Offset","Receive_YCC_Count","Receive_YCC_Offset","Receive_JJLCZT_Count","Receive_JJLCZT_Offset","Send_Count","Send_ZNQD_Count","Send_ZNQD_Offset","Send_BSFX_Count","Send_BSFX_Offset","Send_BSFQ_Count","Send_BSFQ_Offset","Send_JLXX_Count","Send_JLXX_Offset","Send_JJQ_Count","Send_JJQ_Offset","Send_DCXX_Count","Send_DCXX_Offset","Send_LCXHZT_Count","Send_LCXHZT_Offset","Send_ZJJZ_Count","Send_ZJJZ_Offset","Send_QJBSZS_Count","Send_QJBSZS_Offset","Receive_Codes","Send_Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 2; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(Receive_Codes);
        allContent.addAll(Send_Codes);}
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
            int pid  = CAAData.pid.get("RBC4").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("RBC4").get("Step");
            SwitchInterfaceBlock.switchInfo.get("RBC4").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
        } else {
            SwitchInterfaceBlock.switchInfo.get("RBC4").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
        }
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INDEX")) {
                this.Index.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("PROTOCOL")) {
                this.Protocol.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("LOCALID")) {
                this.Local_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("REMOTEID")) {
                this.Remote_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("DV")) {
                this.Data_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("CV")) {
                this.Configure_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("FORGIVE")) {
                this.Forgive.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value) * 1000), 8));
            }
            if (globalKey.equals("MSG_TO_SDM_FLAG")) {
                this.MSG_TO_SDM_FLAG.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("SWITCH_WEIGHT")) {
                SwitchInterfaceBlock.switchInfo.get("RBC4").get(global.get("INDEX").getValue()).add(StringUtil.decToHex(value, 8));
            }
        }
        Integer fixOffset = 35 * 4;
        for (CodeBean codeBean : receive) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Receive_Count.setValue(StringUtil.decToHex(receiveLength,8));

            } else{
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = "Receive_" + codeBean.getBlockName() + "_Count";
                String offset = "Receive_" + codeBean.getBlockName() + "_Offset";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(fixOffset + this.Receive_Codes.size() * 4), 8));
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
                String offset = "Send_" + codeBean.getBlockName() + "_Offset";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(fixOffset + (this.Receive_Codes.size() + this.Send_Codes.size()) * 4), 8));
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

    public BytesInfo getProtocol() {
        return Protocol;
    }

    public void setProtocol(BytesInfo protocol) {
        Protocol = protocol;
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

    public BytesInfo getData_Version() {
        return Data_Version;
    }

    public void setData_Version(BytesInfo data_Version) {
        Data_Version = data_Version;
    }

    public BytesInfo getConfigure_Version() {
        return Configure_Version;
    }

    public void setConfigure_Version(BytesInfo configure_Version) {
        Configure_Version = configure_Version;
    }

    public BytesInfo getForgive() {
        return Forgive;
    }

    public void setForgive(BytesInfo forgive) {
        Forgive = forgive;
    }

    public BytesInfo getMSG_TO_SDM_FLAG() {
        return MSG_TO_SDM_FLAG;
    }

    public void setMSG_TO_SDM_FLAG(BytesInfo MSG_TO_SDM_FLAG) {
        this.MSG_TO_SDM_FLAG = MSG_TO_SDM_FLAG;
    }

    public BytesInfo getReceive_Count() {
        return Receive_Count;
    }

    public void setReceive_Count(BytesInfo receive_Count) {
        Receive_Count = receive_Count;
    }

    public BytesInfo getReceive_XNQD_Count() {
        return Receive_XNQD_Count;
    }

    public void setReceive_XNQD_Count(BytesInfo receive_XNQD_Count) {
        Receive_XNQD_Count = receive_XNQD_Count;
    }

    public BytesInfo getReceive_XNQD_Offset() {
        return Receive_XNQD_Offset;
    }

    public void setReceive_XNQD_Offset(BytesInfo receive_XNQD_Offset) {
        Receive_XNQD_Offset = receive_XNQD_Offset;
    }

    public BytesInfo getReceive_YCC_Count() {
        return Receive_YCC_Count;
    }

    public void setReceive_YCC_Count(BytesInfo receive_YCC_Count) {
        Receive_YCC_Count = receive_YCC_Count;
    }

    public BytesInfo getReceive_YCC_Offset() {
        return Receive_YCC_Offset;
    }

    public void setReceive_YCC_Offset(BytesInfo receive_YCC_Offset) {
        Receive_YCC_Offset = receive_YCC_Offset;
    }

    public BytesInfo getReceive_JJLCZT_Count() {
        return Receive_JJLCZT_Count;
    }

    public void setReceive_JJLCZT_Count(BytesInfo receive_JJLCZT_Count) {
        Receive_JJLCZT_Count = receive_JJLCZT_Count;
    }

    public BytesInfo getReceive_JJLCZT_Offset() {
        return Receive_JJLCZT_Offset;
    }

    public void setReceive_JJLCZT_Offset(BytesInfo receive_JJLCZT_Offset) {
        Receive_JJLCZT_Offset = receive_JJLCZT_Offset;
    }

    public BytesInfo getSend_Count() {
        return Send_Count;
    }

    public void setSend_Count(BytesInfo send_Count) {
        Send_Count = send_Count;
    }

    public BytesInfo getSend_ZNQD_Count() {
        return Send_ZNQD_Count;
    }

    public void setSend_ZNQD_Count(BytesInfo send_ZNQD_Count) {
        Send_ZNQD_Count = send_ZNQD_Count;
    }

    public BytesInfo getSend_ZNQD_Offset() {
        return Send_ZNQD_Offset;
    }

    public void setSend_ZNQD_Offset(BytesInfo send_ZNQD_Offset) {
        Send_ZNQD_Offset = send_ZNQD_Offset;
    }

    public BytesInfo getSend_BSFX_Count() {
        return Send_BSFX_Count;
    }

    public void setSend_BSFX_Count(BytesInfo send_BSFX_Count) {
        Send_BSFX_Count = send_BSFX_Count;
    }

    public BytesInfo getSend_BSFX_Offset() {
        return Send_BSFX_Offset;
    }

    public void setSend_BSFX_Offset(BytesInfo send_BSFX_Offset) {
        Send_BSFX_Offset = send_BSFX_Offset;
    }

    public BytesInfo getSend_BSFQ_Count() {
        return Send_BSFQ_Count;
    }

    public void setSend_BSFQ_Count(BytesInfo send_BSFQ_Count) {
        Send_BSFQ_Count = send_BSFQ_Count;
    }

    public BytesInfo getSend_BSFQ_Offset() {
        return Send_BSFQ_Offset;
    }

    public void setSend_BSFQ_Offset(BytesInfo send_BSFQ_Offset) {
        Send_BSFQ_Offset = send_BSFQ_Offset;
    }

    public BytesInfo getSend_JLXX_Count() {
        return Send_JLXX_Count;
    }

    public void setSend_JLXX_Count(BytesInfo send_JLXX_Count) {
        Send_JLXX_Count = send_JLXX_Count;
    }

    public BytesInfo getSend_JLXX_Offset() {
        return Send_JLXX_Offset;
    }

    public void setSend_JLXX_Offset(BytesInfo send_JLXX_Offset) {
        Send_JLXX_Offset = send_JLXX_Offset;
    }

    public BytesInfo getSend_JJQ_Count() {
        return Send_JJQ_Count;
    }

    public void setSend_JJQ_Count(BytesInfo send_JJQ_Count) {
        Send_JJQ_Count = send_JJQ_Count;
    }

    public BytesInfo getSend_JJQ_Offset() {
        return Send_JJQ_Offset;
    }

    public void setSend_JJQ_Offset(BytesInfo send_JJQ_Offset) {
        Send_JJQ_Offset = send_JJQ_Offset;
    }

    public BytesInfo getSend_DCXX_Count() {
        return Send_DCXX_Count;
    }

    public void setSend_DCXX_Count(BytesInfo send_DCXX_Count) {
        Send_DCXX_Count = send_DCXX_Count;
    }

    public BytesInfo getSend_DCXX_Offset() {
        return Send_DCXX_Offset;
    }

    public void setSend_DCXX_Offset(BytesInfo send_DCXX_Offset) {
        Send_DCXX_Offset = send_DCXX_Offset;
    }

    public BytesInfo getSend_LCXHZT_Count() {
        return Send_LCXHZT_Count;
    }

    public void setSend_LCXHZT_Count(BytesInfo send_LCXHZT_Count) {
        Send_LCXHZT_Count = send_LCXHZT_Count;
    }

    public BytesInfo getSend_LCXHZT_Offset() {
        return Send_LCXHZT_Offset;
    }

    public void setSend_LCXHZT_Offset(BytesInfo send_LCXHZT_Offset) {
        Send_LCXHZT_Offset = send_LCXHZT_Offset;
    }

    public BytesInfo getSend_ZJJZ_Count() {
        return Send_ZJJZ_Count;
    }

    public void setSend_ZJJZ_Count(BytesInfo send_ZJJZ_Count) {
        Send_ZJJZ_Count = send_ZJJZ_Count;
    }

    public BytesInfo getSend_ZJJZ_Offset() {
        return Send_ZJJZ_Offset;
    }

    public void setSend_ZJJZ_Offset(BytesInfo send_ZJJZ_Offset) {
        Send_ZJJZ_Offset = send_ZJJZ_Offset;
    }

    public BytesInfo getSend_QJBSZS_Count() {
        return Send_QJBSZS_Count;
    }

    public void setSend_QJBSZS_Count(BytesInfo send_QJBSZS_Count) {
        Send_QJBSZS_Count = send_QJBSZS_Count;
    }

    public BytesInfo getSend_QJBSZS_Offset() {
        return Send_QJBSZS_Offset;
    }

    public void setSend_QJBSZS_Offset(BytesInfo send_QJBSZS_Offset) {
        Send_QJBSZS_Offset = send_QJBSZS_Offset;
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
