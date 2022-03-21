package vip.jayson.pojo.ioArea;

import vip.jayson.config.CAAData;
import vip.jayson.main.GenerateADSExcel;
import vip.jayson.pojo.additionalFunctionArea.SwitchInterfaceBlock;
import vip.jayson.pojo.dataBean.CodeAssignBean;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.pojo.tisSwitchDataArea.TISInterfaceBlock;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.*;

public class TCC4BlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo Protocol_Version = new BytesInfo("UNIT", 4, "Protocol_Version");
    private BytesInfo Data_Version = new BytesInfo("UNIT", 4, "Data_Version");
    private BytesInfo Forgive = new BytesInfo("UNIT", 4, "Forgive");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Receive_FXBS_Count = new BytesInfo("UNIT", 4, "Receive_FXBS_Count");
    private BytesInfo Receive_FXBS_Offset = new BytesInfo("UNIT", 4, "Receive_FXBS_Offset");
    private BytesInfo Receive_QDZT_Count = new BytesInfo("UNIT", 4, "Receive_QDZT_Count");
    private BytesInfo Receive_QDZT_Offset = new BytesInfo("UNIT", 4, "Receive_QDZT_Offset");
    private BytesInfo Receive_JJXX_Count = new BytesInfo("UNIT", 4, "Receive_JJXX_Count");
    private BytesInfo Receive_JJXX_Offset = new BytesInfo("UNIT", 4, "Receive_JJXX_Offset");
    private BytesInfo Receive_FHDS_Count = new BytesInfo("UNIT", 4, "Receive_FHDS_Count");
    private BytesInfo Receive_FHDS_Offset = new BytesInfo("UNIT", 4, "Receive_FHDS_Offset");
    private BytesInfo Receive_ZHFH_Count = new BytesInfo("UNIT", 4, "Receive_ZHFH_Count");
    private BytesInfo Receive_ZHFH_Offset = new BytesInfo("UNIT", 4, "Receive_ZHFH_Offset");
    private BytesInfo Receive_WCQJFX_Count = new BytesInfo("UNIT", 4, "Receive_WCQJFX_Count");
    private BytesInfo Receive_WCQJFX_Offset = new BytesInfo("UNIT", 4, "Receive_WCQJFX_Offset");
    private BytesInfo Receive_WCQDZT_Count = new BytesInfo("UNIT", 4, "Receive_WCQDZT_Count");
    private BytesInfo Receive_WCQDZT_Offset = new BytesInfo("UNIT", 4, "Receive_WCQDZT_Offset");
    private BytesInfo Receive_WCFHDS_Count = new BytesInfo("UNIT", 4, "Receive_WCFHDS_Count");
    private BytesInfo Receive_WCFHDS_Offset = new BytesInfo("UNIT", 4, "Receive_WCFHDS_Offset");
    private BytesInfo Receive_LSYL_Count = new BytesInfo("UNIT", 4, "Receive_LSYL_Count");
    private BytesInfo Receive_LSYL_Offset = new BytesInfo("UNIT", 4, "Receive_LSYL_Offset");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private BytesInfo Send_FXKZ_Count = new BytesInfo("UNIT", 4, "Send_FXKZ_Count");
    private BytesInfo Send_FXKZ_Offset = new BytesInfo("UNIT", 4, "Send_FXKZ_Offset");
    private BytesInfo Send_JZDS_Count = new BytesInfo("UNIT", 4, "Send_JZDS_Count");
    private BytesInfo Send_JZDS_Offset = new BytesInfo("UNIT", 4, "Send_JZDS_Offset");
    private BytesInfo Send_DCZT_Count = new BytesInfo("UNIT", 4, "Send_DCZT_Count");
    private BytesInfo Send_DCZT_Offset = new BytesInfo("UNIT", 4, "Send_DCZT_Offset");
    private BytesInfo Send_LSYL_Count = new BytesInfo("UNIT", 4, "Send_LSYL_Count");
    private BytesInfo Send_LSYL_Offset = new BytesInfo("UNIT", 4, "Send_LSYL_Offset");
    private BytesInfo Send_JLXX_Count = new BytesInfo("UNIT", 4, "Send_JLXX_Count");
    private BytesInfo Send_JLXX_Offset = new BytesInfo("UNIT", 4, "Send_JLXX_Offset");
    private List<BytesInfo> Receive_Codes = new ArrayList<>();
    private List<BytesInfo> Send_Codes_Except_JLXX = new ArrayList<>();
    private List<TCC4SendJlXXCodeBean> TCC4SendJlXXCodeBean = new ArrayList<>();
    public static String[] order = {"PID","Index","Local_ID","Remote_ID","Protocol_Version","Data_Version","Forgive","Receive_Count","Receive_FXBS_Count","Receive_FXBS_Offset","Receive_QDZT_Count","Receive_QDZT_Offset","Receive_JJXX_Count","Receive_JJXX_Offset","Receive_FHDS_Count","Receive_FHDS_Offset","Receive_ZHFH_Count","Receive_ZHFH_Offset","Receive_WCQJFX_Count","Receive_WCQJFX_Offset","Receive_WCQDZT_Count","Receive_WCQDZT_Offset","Receive_WCFHDS_Count","Receive_WCFHDS_Offset","Receive_LSYL_Count","Receive_LSYL_Offset","Send_Count","Send_FXKZ_Count","Send_FXKZ_Offset","Send_JZDS_Count","Send_JZDS_Offset","Send_DCZT_Count","Send_DCZT_Offset","Send_LSYL_Count","Send_LSYL_Offset","Send_JLXX_Count","Send_JLXX_Offset","Receive_Codes","Send_Codes_Except_JLXX","TCC4SendJlXXCodeBean"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 3; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo)ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(this.Receive_Codes);
        allContent.addAll(this.Send_Codes_Except_JLXX);
        for (TCC4SendJlXXCodeBean tcc4SendJlXXCodeBean : this.TCC4SendJlXXCodeBean) {
            allContent.addAll(tcc4SendJlXXCodeBean.getAllContent());
        }}
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
            int pid  = CAAData.pid.get("TCC4").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("TCC4").get("Step");
            SwitchInterfaceBlock.switchInfo.get("TCC4").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            TISInterfaceBlock.tisInfo.get("TCC4").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
        } else {
            SwitchInterfaceBlock.switchInfo.get("TCC4").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            TISInterfaceBlock.tisInfo.get("TCC4").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
        }
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INDEX")) {
                TISInterfaceBlock.tisInfo.get("TCC4").get(value).add(StringUtil.decToHex(value, 8));
                this.Index.setValue(StringUtil.decToHex(value, 8));
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
                SwitchInterfaceBlock.switchInfo.get("TCC4").get(global.get("INDEX").getValue()).add(StringUtil.decToHex(value, 8));
            }
        }
        Integer fixOffset = 148;
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

            } else if (!codeBean.getBlockName().equals("JLXX")) {
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = "Send_" + codeBean.getBlockName() + "_Count";
                String offset = "Send_" + codeBean.getBlockName() + "_Offset";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(fixOffset + (this.Receive_Codes.size() + this.Send_Codes_Except_JLXX.size()) * 4), 8));
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    this.Send_Codes_Except_JLXX.add(bytesInfo);
                }
            } else {
                TCC4SendJlXXCodeBean tcc4SendJlXXCodeBean = new TCC4SendJlXXCodeBean();
                tcc4SendJlXXCodeBean.setAll(codeBean);
                this.TCC4SendJlXXCodeBean.add(tcc4SendJlXXCodeBean);
            }
        }
        this.Send_JLXX_Count.setValue(StringUtil.decToHex(String.valueOf(this.TCC4SendJlXXCodeBean.size()), 8));
        this.Send_JLXX_Offset.setValue(StringUtil.decToHex(String.valueOf(fixOffset + (this.Receive_Codes.size() + this.Send_Codes_Except_JLXX.size()) * 4), 8));
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

    public BytesInfo getReceive_FXBS_Count() {
        return Receive_FXBS_Count;
    }

    public void setReceive_FXBS_Count(BytesInfo receive_FXBS_Count) {
        Receive_FXBS_Count = receive_FXBS_Count;
    }

    public BytesInfo getReceive_FXBS_Offset() {
        return Receive_FXBS_Offset;
    }

    public void setReceive_FXBS_Offset(BytesInfo receive_FXBS_Offset) {
        Receive_FXBS_Offset = receive_FXBS_Offset;
    }

    public BytesInfo getReceive_QDZT_Count() {
        return Receive_QDZT_Count;
    }

    public void setReceive_QDZT_Count(BytesInfo receive_QDZT_Count) {
        Receive_QDZT_Count = receive_QDZT_Count;
    }

    public BytesInfo getReceive_QDZT_Offset() {
        return Receive_QDZT_Offset;
    }

    public void setReceive_QDZT_Offset(BytesInfo receive_QDZT_Offset) {
        Receive_QDZT_Offset = receive_QDZT_Offset;
    }

    public BytesInfo getReceive_JJXX_Count() {
        return Receive_JJXX_Count;
    }

    public void setReceive_JJXX_Count(BytesInfo receive_JJXX_Count) {
        Receive_JJXX_Count = receive_JJXX_Count;
    }

    public BytesInfo getReceive_JJXX_Offset() {
        return Receive_JJXX_Offset;
    }

    public void setReceive_JJXX_Offset(BytesInfo receive_JJXX_Offset) {
        Receive_JJXX_Offset = receive_JJXX_Offset;
    }

    public BytesInfo getReceive_FHDS_Count() {
        return Receive_FHDS_Count;
    }

    public void setReceive_FHDS_Count(BytesInfo receive_FHDS_Count) {
        Receive_FHDS_Count = receive_FHDS_Count;
    }

    public BytesInfo getReceive_FHDS_Offset() {
        return Receive_FHDS_Offset;
    }

    public void setReceive_FHDS_Offset(BytesInfo receive_FHDS_Offset) {
        Receive_FHDS_Offset = receive_FHDS_Offset;
    }

    public BytesInfo getReceive_ZHFH_Count() {
        return Receive_ZHFH_Count;
    }

    public void setReceive_ZHFH_Count(BytesInfo receive_ZHFH_Count) {
        Receive_ZHFH_Count = receive_ZHFH_Count;
    }

    public BytesInfo getReceive_ZHFH_Offset() {
        return Receive_ZHFH_Offset;
    }

    public void setReceive_ZHFH_Offset(BytesInfo receive_ZHFH_Offset) {
        Receive_ZHFH_Offset = receive_ZHFH_Offset;
    }

    public BytesInfo getReceive_WCQJFX_Count() {
        return Receive_WCQJFX_Count;
    }

    public void setReceive_WCQJFX_Count(BytesInfo receive_WCQJFX_Count) {
        Receive_WCQJFX_Count = receive_WCQJFX_Count;
    }

    public BytesInfo getReceive_WCQJFX_Offset() {
        return Receive_WCQJFX_Offset;
    }

    public void setReceive_WCQJFX_Offset(BytesInfo receive_WCQJFX_Offset) {
        Receive_WCQJFX_Offset = receive_WCQJFX_Offset;
    }

    public BytesInfo getReceive_WCQDZT_Count() {
        return Receive_WCQDZT_Count;
    }

    public void setReceive_WCQDZT_Count(BytesInfo receive_WCQDZT_Count) {
        Receive_WCQDZT_Count = receive_WCQDZT_Count;
    }

    public BytesInfo getReceive_WCQDZT_Offset() {
        return Receive_WCQDZT_Offset;
    }

    public void setReceive_WCQDZT_Offset(BytesInfo receive_WCQDZT_Offset) {
        Receive_WCQDZT_Offset = receive_WCQDZT_Offset;
    }

    public BytesInfo getReceive_WCFHDS_Count() {
        return Receive_WCFHDS_Count;
    }

    public void setReceive_WCFHDS_Count(BytesInfo receive_WCFHDS_Count) {
        Receive_WCFHDS_Count = receive_WCFHDS_Count;
    }

    public BytesInfo getReceive_WCFHDS_Offset() {
        return Receive_WCFHDS_Offset;
    }

    public void setReceive_WCFHDS_Offset(BytesInfo receive_WCFHDS_Offset) {
        Receive_WCFHDS_Offset = receive_WCFHDS_Offset;
    }

    public BytesInfo getReceive_LSYL_Count() {
        return Receive_LSYL_Count;
    }

    public void setReceive_LSYL_Count(BytesInfo receive_LSYL_Count) {
        Receive_LSYL_Count = receive_LSYL_Count;
    }

    public BytesInfo getReceive_LSYL_Offset() {
        return Receive_LSYL_Offset;
    }

    public void setReceive_LSYL_Offset(BytesInfo receive_LSYL_Offset) {
        Receive_LSYL_Offset = receive_LSYL_Offset;
    }

    public BytesInfo getSend_Count() {
        return Send_Count;
    }

    public void setSend_Count(BytesInfo send_Count) {
        Send_Count = send_Count;
    }

    public BytesInfo getSend_FXKZ_Count() {
        return Send_FXKZ_Count;
    }

    public void setSend_FXKZ_Count(BytesInfo send_FXKZ_Count) {
        Send_FXKZ_Count = send_FXKZ_Count;
    }

    public BytesInfo getSend_FXKZ_Offset() {
        return Send_FXKZ_Offset;
    }

    public void setSend_FXKZ_Offset(BytesInfo send_FXKZ_Offset) {
        Send_FXKZ_Offset = send_FXKZ_Offset;
    }

    public BytesInfo getSend_JZDS_Count() {
        return Send_JZDS_Count;
    }

    public void setSend_JZDS_Count(BytesInfo send_JZDS_Count) {
        Send_JZDS_Count = send_JZDS_Count;
    }

    public BytesInfo getSend_JZDS_Offset() {
        return Send_JZDS_Offset;
    }

    public void setSend_JZDS_Offset(BytesInfo send_JZDS_Offset) {
        Send_JZDS_Offset = send_JZDS_Offset;
    }

    public BytesInfo getSend_DCZT_Count() {
        return Send_DCZT_Count;
    }

    public void setSend_DCZT_Count(BytesInfo send_DCZT_Count) {
        Send_DCZT_Count = send_DCZT_Count;
    }

    public BytesInfo getSend_DCZT_Offset() {
        return Send_DCZT_Offset;
    }

    public void setSend_DCZT_Offset(BytesInfo send_DCZT_Offset) {
        Send_DCZT_Offset = send_DCZT_Offset;
    }

    public BytesInfo getSend_LSYL_Count() {
        return Send_LSYL_Count;
    }

    public void setSend_LSYL_Count(BytesInfo send_LSYL_Count) {
        Send_LSYL_Count = send_LSYL_Count;
    }

    public BytesInfo getSend_LSYL_Offset() {
        return Send_LSYL_Offset;
    }

    public void setSend_LSYL_Offset(BytesInfo send_LSYL_Offset) {
        Send_LSYL_Offset = send_LSYL_Offset;
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

    public List<BytesInfo> getReceive_Codes() {
        return Receive_Codes;
    }

    public void setReceive_Codes(List<BytesInfo> receive_Codes) {
        Receive_Codes = receive_Codes;
    }

    public List<BytesInfo> getSend_Codes_Except_JLXX() {
        return Send_Codes_Except_JLXX;
    }

    public void setSend_Codes_Except_JLXX(List<BytesInfo> send_Codes_Except_JLXX) {
        Send_Codes_Except_JLXX = send_Codes_Except_JLXX;
    }

    public List<vip.jayson.pojo.ioArea.TCC4SendJlXXCodeBean> getTCC4SendJlXXCodeBean() {
        return TCC4SendJlXXCodeBean;
    }

    public void setTCC4SendJlXXCodeBean(List<vip.jayson.pojo.ioArea.TCC4SendJlXXCodeBean> TCC4SendJlXXCodeBean) {
        this.TCC4SendJlXXCodeBean = TCC4SendJlXXCodeBean;
    }
}
