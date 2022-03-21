package vip.jayson.pojo.ioArea;

import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
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

public class KDACBlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Interface_Type = new BytesInfo("UNIT", 4, "Interface_Type");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo Protocol_Version = new BytesInfo("UNIT", 4, "Protocol_Version");
    private BytesInfo Data_Version = new BytesInfo("UNIT", 4, "Data_Version");
    private BytesInfo Timeout = new BytesInfo("UNIT", 4, "Timeout");
    private BytesInfo ACTXZT_Count = new BytesInfo("UNIT", 4, "ACTXZT_Count");
    private BytesInfo ACTXZT_Offset = new BytesInfo("UNIT", 4, "ACTXZT_Offset");
    private BytesInfo AC_BLOCK_Count = new BytesInfo("UNIT", 4, "AC_BLOCK_Count");
    private BytesInfo AC_BLOCK_Offset = new BytesInfo("UNIT", 4, "AC_BLOCK_Offset");
    private BytesInfo AC_HEAD_Count = new BytesInfo("UNIT", 4, "AC_HEAD_Count");
    private BytesInfo AC_HEAD_Offset = new BytesInfo("UNIT", 4, "AC_HEAD_Offset");
    private BytesInfo RESET_CMD_Count = new BytesInfo("UNIT", 4, "RESET_CMD_Count");
    private BytesInfo RESET_CMD_Offset = new BytesInfo("UNIT", 4, "RESET_CMD_Offset");
    private BytesInfo PRE_RESET_CMD_Count = new BytesInfo("UNIT", 4, "PRE_RESET_CMD_Count");
    private BytesInfo PRE_RESET_CMD_Offset = new BytesInfo("UNIT", 4, "PRE_RESET_CMD_Offset");
    private List<BytesInfo> Codes = new ArrayList<>();
    public static String[] order = {"PID","Interface_Type","Index","Local_ID","Remote_ID","Protocol_Version","Data_Version","Timeout","ACTXZT_Count","ACTXZT_Offset","AC_BLOCK_Count","AC_BLOCK_Offset","AC_HEAD_Count","AC_HEAD_Offset","RESET_CMD_Count","RESET_CMD_Offset","PRE_RESET_CMD_Count","PRE_RESET_CMD_Offset","Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 1; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(Codes);}
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
            int pid  = CAAData.pid.get("KDAC").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("KDAC").get("Step");
            SwitchInterfaceBlock.switchInfo.get("KDAC").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
        } else {
            SwitchInterfaceBlock.switchInfo.get("KDAC").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
        }
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INTERFACETYPE")) {
                this.Interface_Type.setValue(StringUtil.decToHex(value, 8));
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
            if (globalKey.equals("PR_VER")) {
                this.Protocol_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("DA_VER")) {
                this.Data_Version.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("APP_TIMEOUT")) {
                this.Timeout.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value)*1000), 8));
            }

            if (globalKey.equals("SWITCH_WEIGHT")) {
                SwitchInterfaceBlock.switchInfo.get("KDAC").get(global.get("INDEX").getValue()).add(StringUtil.decToHex(value, 8));
            }
        }
        Integer fixOffset = 18 * 4;
        for (CodeBean codeBean : receive) {
            if (!codeBean.getBlockName().equals("OVERVIEW")) {
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = codeBean.getBlockName() + "_Count";
                String offset = codeBean.getBlockName() + "_Offset";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                if (codeBean.getBlockName().equals("AC_BLOCK")) {
                    countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeBean.getFlagInfo().get("BLOCK_NUM")), 8));
                } else if(codeBean.getBlockName().equals("AC_HEAD")){
                    countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeBean.getFlagInfo().get("AC_HEAD")), 8));
                } else {
                    countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                }
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(fixOffset + this.Codes.size() * 4), 8));
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Receive Code");
                    this.Codes.add(bytesInfo);
                }
            }
        }
        for (CodeBean codeBean : send) {
            if (!codeBean.getBlockName().equals("OVERVIEW")) {
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = codeBean.getBlockName() + "_Count";
                String offset = codeBean.getBlockName() + "_Offset";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(fixOffset + this.Codes.size() * 4), 8));
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    this.Codes.add(bytesInfo);
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

    public BytesInfo getACTXZT_Count() {
        return ACTXZT_Count;
    }

    public void setACTXZT_Count(BytesInfo ACTXZT_Count) {
        this.ACTXZT_Count = ACTXZT_Count;
    }

    public BytesInfo getACTXZT_Offset() {
        return ACTXZT_Offset;
    }

    public void setACTXZT_Offset(BytesInfo ACTXZT_Offset) {
        this.ACTXZT_Offset = ACTXZT_Offset;
    }

    public BytesInfo getAC_BLOCK_Count() {
        return AC_BLOCK_Count;
    }

    public void setAC_BLOCK_Count(BytesInfo AC_BLOCK_Count) {
        this.AC_BLOCK_Count = AC_BLOCK_Count;
    }

    public BytesInfo getAC_BLOCK_Offset() {
        return AC_BLOCK_Offset;
    }

    public void setAC_BLOCK_Offset(BytesInfo AC_BLOCK_Offset) {
        this.AC_BLOCK_Offset = AC_BLOCK_Offset;
    }

    public BytesInfo getAC_HEAD_Count() {
        return AC_HEAD_Count;
    }

    public void setAC_HEAD_Count(BytesInfo AC_HEAD_Count) {
        this.AC_HEAD_Count = AC_HEAD_Count;
    }

    public BytesInfo getAC_HEAD_Offset() {
        return AC_HEAD_Offset;
    }

    public void setAC_HEAD_Offset(BytesInfo AC_HEAD_Offset) {
        this.AC_HEAD_Offset = AC_HEAD_Offset;
    }

    public BytesInfo getRESET_CMD_Count() {
        return RESET_CMD_Count;
    }

    public void setRESET_CMD_Count(BytesInfo RESET_CMD_Count) {
        this.RESET_CMD_Count = RESET_CMD_Count;
    }

    public BytesInfo getRESET_CMD_Offset() {
        return RESET_CMD_Offset;
    }

    public void setRESET_CMD_Offset(BytesInfo RESET_CMD_Offset) {
        this.RESET_CMD_Offset = RESET_CMD_Offset;
    }

    public BytesInfo getPRE_RESET_CMD_Count() {
        return PRE_RESET_CMD_Count;
    }

    public void setPRE_RESET_CMD_Count(BytesInfo PRE_RESET_CMD_Count) {
        this.PRE_RESET_CMD_Count = PRE_RESET_CMD_Count;
    }

    public BytesInfo getPRE_RESET_CMD_Offset() {
        return PRE_RESET_CMD_Offset;
    }

    public void setPRE_RESET_CMD_Offset(BytesInfo PRE_RESET_CMD_Offset) {
        this.PRE_RESET_CMD_Offset = PRE_RESET_CMD_Offset;
    }

    public List<BytesInfo> getCodes() {
        return Codes;
    }

    public void setCodes(List<BytesInfo> codes) {
        Codes = codes;
    }
}
