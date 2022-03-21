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

public class RBC2BlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo VCI_UV = new BytesInfo("UNIT", 4, "VCI_UV");
    private BytesInfo VCI_SI = new BytesInfo("UNIT", 4, "VCI_SI");
    private BytesInfo VCI_SV = new BytesInfo("UNIT", 4, "VCI_SV");
    private BytesInfo SAM_Count = new BytesInfo("UNIT", 4, "SAM_Count");
    private BytesInfo SAM_Offset = new BytesInfo("UNIT", 4, "SAM_Offset");
    private BytesInfo EMAM_Count = new BytesInfo("UNIT", 4, "EMAM_Count");
    private BytesInfo EMAM_Offset = new BytesInfo("UNIT", 4, "EMAM_Offset");
    private List<RBC2SAMCodeBean> RBC2SAMCodes = new ArrayList<>();
    private List<RBC2EMAMCodeBean> RBC2EMAMCodes = new ArrayList<>();
    public static String[] order = {"PID","Index","Local_ID","Remote_ID","VCI_UV","VCI_SI","VCI_SV","SAM_Count","SAM_Offset","EMAM_Count","EMAM_Offset","RBC2SAMCodes","RBC2EMAMCodes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 2; i++) {
                BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
                allContent.add(fieldValueByFieldName);
            }
            for (RBC2SAMCodeBean rbc2SAMCode : RBC2SAMCodes) {
                allContent.addAll(rbc2SAMCode.getAllContent());
            }
            for (RBC2EMAMCodeBean rbc2EMAMCode : RBC2EMAMCodes) {
                allContent.addAll(rbc2EMAMCode.getAllContent());
            }
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(InterfaceFileBean oneBlock) {
        LinkedHashMap<String, CodeInfoBean> global = oneBlock.getGlobal();
        List<CodeBean> send = oneBlock.getSend();
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            if (!global.containsKey("PID")) {
                int pid  = CAAData.pid.get("RBC2").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("RBC2").get("Step");
                SwitchInterfaceBlock.switchInfo.get("RBC2").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
                this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
            } else {
                SwitchInterfaceBlock.switchInfo.get("RBC2").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
                this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
            }
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
            if (globalKey.equals("UV")) {
                this.VCI_UV.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("SI")) {
                this.VCI_SI.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("SV")) {
                this.VCI_SV.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("SWITCH_WEIGHT")) {
                SwitchInterfaceBlock.switchInfo.get("RBC2").get(global.get("INDEX").getValue()).add(StringUtil.decToHex(value, 8));
            }
        }

        int fixOffset = 11 * 4;
        for (CodeBean codeBean : send) {
            if (!codeBean.getBlockName().equals("OVERVIEW")) {
                if (codeBean.getBlockName().equals("SAM")) {
                    RBC2SAMCodeBean rbc2SAMCodeBean = new RBC2SAMCodeBean();
                    rbc2SAMCodeBean.setAll(codeBean);
                    this.RBC2SAMCodes.add(rbc2SAMCodeBean);
                }
                if (codeBean.getBlockName().equals("EMAM")) {
                    RBC2EMAMCodeBean rbc2EMAMCodeBean = new RBC2EMAMCodeBean();
                    rbc2EMAMCodeBean.setAll(codeBean);
                    this.RBC2EMAMCodes.add(rbc2EMAMCodeBean);
                }
            }
        }

        this.SAM_Count.setValue(StringUtil.decToHex(String.valueOf(this.RBC2SAMCodes.size()), 8));
        this.SAM_Offset.setValue(StringUtil.decToHex(String.valueOf(fixOffset), 8));
        this.EMAM_Count.setValue(StringUtil.decToHex(String.valueOf(this.RBC2EMAMCodes.size()), 8));
        int EMAMOffset = fixOffset;
        for (RBC2SAMCodeBean rbc2SAMCode : this.RBC2SAMCodes) {
            EMAMOffset += rbc2SAMCode.getAllContent().size() * 4;
        }
        this.EMAM_Offset.setValue(StringUtil.decToHex(String.valueOf(EMAMOffset), 8));
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

    public BytesInfo getVCI_UV() {
        return VCI_UV;
    }

    public void setVCI_UV(BytesInfo VCI_UV) {
        this.VCI_UV = VCI_UV;
    }

    public BytesInfo getVCI_SI() {
        return VCI_SI;
    }

    public void setVCI_SI(BytesInfo VCI_SI) {
        this.VCI_SI = VCI_SI;
    }

    public BytesInfo getVCI_SV() {
        return VCI_SV;
    }

    public void setVCI_SV(BytesInfo VCI_SV) {
        this.VCI_SV = VCI_SV;
    }

    public BytesInfo getSAM_Count() {
        return SAM_Count;
    }

    public void setSAM_Count(BytesInfo SAM_Count) {
        this.SAM_Count = SAM_Count;
    }

    public BytesInfo getSAM_Offset() {
        return SAM_Offset;
    }

    public void setSAM_Offset(BytesInfo SAM_Offset) {
        this.SAM_Offset = SAM_Offset;
    }

    public BytesInfo getEMAM_Count() {
        return EMAM_Count;
    }

    public void setEMAM_Count(BytesInfo EMAM_Count) {
        this.EMAM_Count = EMAM_Count;
    }

    public BytesInfo getEMAM_Offset() {
        return EMAM_Offset;
    }

    public void setEMAM_Offset(BytesInfo EMAM_Offset) {
        this.EMAM_Offset = EMAM_Offset;
    }



    public List<RBC2SAMCodeBean> getRBC2SAMCodes() {
        return RBC2SAMCodes;
    }

    public void setRBC2SAMCodes(List<RBC2SAMCodeBean> RBC2SAMCodes) {
        this.RBC2SAMCodes = RBC2SAMCodes;
    }

    public List<RBC2EMAMCodeBean> getRBC2EMAMCodes() {
        return RBC2EMAMCodes;
    }

    public void setRBC2EMAMCodes(List<RBC2EMAMCodeBean> RBC2EMAMCodes) {
        this.RBC2EMAMCodes = RBC2EMAMCodes;
    }
}
