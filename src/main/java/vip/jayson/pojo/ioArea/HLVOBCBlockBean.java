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

public class HLVOBCBlockBean {
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Interface_Type = new BytesInfo("UNIT", 4, "Interface_Type");
    private BytesInfo PID_NUM = new BytesInfo("UNIT", 4, "PID_NUM");
    private BytesInfo PID_Offset = new BytesInfo("UNIT", 4, "PID_Offset");
    private BytesInfo Remote_ID_NUM = new BytesInfo("UNIT", 4, "Remote_ID_NUM");
    private BytesInfo Remote_ID_Offset = new BytesInfo("UNIT", 4, "Remote_ID_Offset");
    private BytesInfo CBI_ID = new BytesInfo("UNIT", 4, "CBI_ID");
    private BytesInfo City_ID = new BytesInfo("UNIT", 4, "City_ID");
    private BytesInfo Manufacturer_ID = new BytesInfo("UNIT", 4, "Manufacturer_ID");
    private BytesInfo The_First_BZXX = new BytesInfo("UNIT", 4, "The_First_BZXX");
    private BytesInfo The_Second_BZXX = new BytesInfo("UNIT", 4, "The_Second_BZXX");
    private BytesInfo The_Third_BZXX = new BytesInfo("UNIT", 4, "The_Third_BZXX");
    private BytesInfo The_Fourth_BZXX = new BytesInfo("UNIT", 4, "The_Fourth_BZXX");
    private BytesInfo PR_VER = new BytesInfo("UNIT", 4, "PR_VER");
    private BytesInfo LineMap_VER = new BytesInfo("UNIT", 4, "LineMap_VER");
    private BytesInfo Timeout = new BytesInfo("UNIT", 4, "Timeout");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Receive_GDQD_Count = new BytesInfo("UNIT", 4, "Receive_GDQD_Count");
    private BytesInfo Receive_GDQD_Offset = new BytesInfo("UNIT", 4, "Receive_GDQD_Offset");
    private BytesInfo Receive_XHJ_Count = new BytesInfo("UNIT", 4, "Receive_XHJ_Count");
    private BytesInfo Receive_XHJ_Offset = new BytesInfo("UNIT", 4, "Receive_XHJ_Offset");
    private BytesInfo Receive_PSD_Count = new BytesInfo("UNIT", 4, "Receive_PSD_Count");
    private BytesInfo Receive_PSD_Offset = new BytesInfo("UNIT", 4, "Receive_PSD_Offset");
    private BytesInfo Receive_PSDFAO_Count = new BytesInfo("UNIT", 4, "Receive_PSDFAO_Count");
    private BytesInfo Receive_PSDFAO_Offset = new BytesInfo("UNIT", 4, "Receive_PSDFAO_Offset");
    private BytesInfo Receive_CITY_Count = new BytesInfo("UNIT", 4, "Receive_CITY_Count");
    private BytesInfo Receive_CITY_Offset = new BytesInfo("UNIT", 4, "Receive_CITY_Offset");
    private BytesInfo Receive_MANUFACTURER_Count = new BytesInfo("UNIT", 4, "Receive_MANUFACTURER_Count");
    private BytesInfo Receive_MANUFACTURER_Offset = new BytesInfo("UNIT", 4, "Receive_MANUFACTURER_Offset");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private BytesInfo Send_XHJ_Count = new BytesInfo("UNIT", 4, "Send_XHJ_Count");
    private BytesInfo Send_XHJ_Offset = new BytesInfo("UNIT", 4, "Send_XHJ_Offset");
    private BytesInfo Send_PSD_Count = new BytesInfo("UNIT", 4, "Send_PSD_Count");
    private BytesInfo Send_PSD_Offset = new BytesInfo("UNIT", 4, "Send_PSD_Offset");
    private BytesInfo Send_PSDFAO_Count = new BytesInfo("UNIT", 4, "Send_PSDFAO_Count");
    private BytesInfo Send_PSDFAO_Offset = new BytesInfo("UNIT", 4, "Send_PSDFAO_Offset");
    private BytesInfo Send_CITY_Count = new BytesInfo("UNIT", 4, "Send_CITY_Count");
    private BytesInfo Send_CITY_Offset = new BytesInfo("UNIT", 4, "Send_CITY_Offset");
    private BytesInfo Send_MANUFACTURER_Count = new BytesInfo("UNIT", 4, "Send_MANUFACTURER_Count");
    private BytesInfo Send_MANUFACTURER_Offset = new BytesInfo("UNIT", 4, "Send_MANUFACTURER_Offset");
    private List<BytesInfo> pidInfo = new ArrayList<>();
    private List<BytesInfo> remoteIdInfo = new ArrayList<>();
    private List<HLVOBCCodeBean> Receive_GDQD = new ArrayList<>();
    private HLVOBCCodeBean Receive_XHJ = new HLVOBCCodeBean();
    private HLVOBCCodeBean Receive_PSD = new HLVOBCCodeBean();
    private HLVOBCCodeBean Receive_PSDFAO = new HLVOBCCodeBean();
    private List<BytesInfo> Receive_CITY_MANUFACTURER = new ArrayList<>();
    private HLVOBCCodeBean Send_XHJ = new HLVOBCCodeBean();
    private HLVOBCCodeBean Send_PSD = new HLVOBCCodeBean();
    private HLVOBCCodeBean Send_PSDFAO = new HLVOBCCodeBean();
    private List<BytesInfo> Send_CITY_MANUFACTURER = new ArrayList<>();
    public static String[] order = {"Index","Interface_Type","PID_NUM","PID_Offset","Remote_ID_NUM","Remote_ID_Offset","CBI_ID","City_ID","Manufacturer_ID","The_First_BZXX","The_Second_BZXX","The_Third_BZXX","The_Fourth_BZXX","PR_VER","LineMap_VER","Timeout","Receive_Count","Receive_GDQD_Count","Receive_GDQD_Offset","Receive_XHJ_Count","Receive_XHJ_Offset","Receive_PSD_Count","Receive_PSD_Offset","Receive_PSDFAO_Count","Receive_PSDFAO_Offset","Receive_CITY_Count","Receive_CITY_Offset","Receive_MANUFACTURER_Count","Receive_MANUFACTURER_Offset","Send_Count","Send_XHJ_Count","Send_XHJ_Offset","Send_PSD_Count","Send_PSD_Offset","Send_PSDFAO_Count","Send_PSDFAO_Offset","Send_CITY_Count","Send_CITY_Offset","Send_MANUFACTURER_Count","Send_MANUFACTURER_Offset","pidInfo","remoteIdInfo","Receive_GDQD","Receive_XHJ","Receive_PSD","Receive_PSDFAO","Receive_CITY_MANUFACTURER","Send_XHJ","Send_PSD","Send_PSDFAO","Send_CITY_MANUFACTURER"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 11; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(this.pidInfo);
        allContent.addAll(this.remoteIdInfo);
        for (HLVOBCCodeBean hlvobcCodeBean : this.Receive_GDQD) {
            allContent.addAll(hlvobcCodeBean.getAllContent());
        }
        allContent.addAll(this.Receive_XHJ.getAllContent());
        allContent.addAll(this.Receive_PSD.getAllContent());
        allContent.addAll(this.Receive_PSDFAO.getAllContent());
        allContent.addAll(this.Receive_CITY_MANUFACTURER);
        allContent.addAll(this.Send_XHJ.getAllContent());
        allContent.addAll(this.Send_PSD.getAllContent());
        allContent.addAll(this.Send_PSDFAO.getAllContent());
        allContent.addAll(this.Send_CITY_MANUFACTURER);}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(InterfaceFileBean oneBlock) {
        LinkedHashMap<String, CodeInfoBean> global = oneBlock.getGlobal();
        List<CodeBean> receive = oneBlock.getReceive();
        List<CodeBean> send = oneBlock.getSend();
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INDEX")) {
                this.Index.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("INTERFACETYPE")) {
                this.Interface_Type.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("PID_NUM")) {
                this.PID_NUM.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("PID")) {
                String[] split = value.split(",");
                for (int i = 0; i < split.length; i++) {
                    this.pidInfo.add(new BytesInfo("UNIT", 4, "The " + i + "th PID", StringUtil.decToHex(split[i].trim(), 8)));
                }
            }
            if (globalKey.equals("REMOTE_ID")) {
                String[] split = value.split(",");
                for (int i = 0; i < split.length; i++) {
                    this.remoteIdInfo.add(new BytesInfo("UNIT", 4, "The " + i + "th Remote Id", StringUtil.decToHex(split[i].trim(), 8)));
                }
                this.Remote_ID_NUM.setValue(StringUtil.decToHex(String.valueOf(this.remoteIdInfo.size()), 8));
            }
            if (globalKey.equals("HLHTCI_ID")) {
                this.CBI_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("CITY_ID")) {
                this.City_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("MANUFACTURER_ID")) {
                this.Manufacturer_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("BZXX")) {
                String[] split = value.split(",");
                this.The_First_BZXX.setValue(StringUtil.decToHex(split[0].trim(), 8));
                this.The_Second_BZXX.setValue(StringUtil.decToHex(split[1].trim(), 8));
                this.The_Third_BZXX.setValue(StringUtil.decToHex(split[2].trim(), 8));
                this.The_Fourth_BZXX.setValue(StringUtil.decToHex(split[3].trim(), 8));
            }
            if (globalKey.equals("PR_VER")) {
                this.PR_VER.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("LINEMAP_VER")) {
                this.LineMap_VER.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("APP_TIMEOUT")) {
                this.Timeout.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value)*1000), 8));
            }
        }
        Integer currentOffset = 40 * 4;
        this.PID_Offset.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
        currentOffset += Integer.parseInt(StringUtil.hexToDec(this.PID_NUM.getValue())) * 4;
        this.Remote_ID_Offset.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
        currentOffset += Integer.parseInt(StringUtil.hexToDec(this.Remote_ID_NUM.getValue())) * 4;
        this.Receive_GDQD_Count.setValue(StringUtil.decToHex("0", 8));
        this.Receive_GDQD_Offset.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
        for (CodeBean codeBean : receive) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Receive_Count.setValue(StringUtil.decToHex(receiveLength,8));
            } else if (codeBean.getBlockName().equals("GDQD")) {
                HLVOBCCodeBean hlvobcCodeBean = new HLVOBCCodeBean();
                hlvobcCodeBean.setAll(codeBean);
                this.Receive_GDQD.add(hlvobcCodeBean);
                currentOffset += hlvobcCodeBean.getLength();
            } else if (!codeBean.getBlockName().equals("CITY") && !codeBean.getBlockName().equals("MANUFACTURER")) {
                String codes = "Receive_" + codeBean.getBlockName();
                String count = "Receive_" + codeBean.getBlockName() + "_Count";
                String offset = "Receive_" + codeBean.getBlockName() + "_Offset";
                HLVOBCCodeBean codesBean = (HLVOBCCodeBean)ObjectUtil.getFieldValueByFieldName(codes, this);
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                codesBean.setAll(codeBean);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codesBean.getIdCodeInfoList().size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
                currentOffset += codesBean.getLength();

            } else {
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = "Receive_" + codeBean.getBlockName() + "_Count";
                String offset = "Receive_" + codeBean.getBlockName() + "_Offset";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));

                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    this.Receive_CITY_MANUFACTURER.add(bytesInfo);
                    currentOffset += 4;
                }

            }
        }
        this.Receive_GDQD_Count.setValue(StringUtil.decToHex(String.valueOf(this.Receive_GDQD.size()), 8));
        for (CodeBean codeBean : send) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String sendLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Send_Count.setValue(StringUtil.decToHex(sendLength,8));

            }  else if (!codeBean.getBlockName().equals("CITY") && !codeBean.getBlockName().equals("MANUFACTURER")) {
                String codes = "Send_" + codeBean.getBlockName();
                String count = "Send_" + codeBean.getBlockName() + "_Count";
                String offset = "Send_" + codeBean.getBlockName() + "_Offset";
                HLVOBCCodeBean codesBean = (HLVOBCCodeBean)ObjectUtil.getFieldValueByFieldName(codes, this);
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                codesBean.setAll(codeBean);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codesBean.getIdCodeInfoList().size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
                currentOffset += codesBean.getLength();

            } else {
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                String count = "Send_" + codeBean.getBlockName() + "_Count";
                String offset = "Send_" + codeBean.getBlockName() + "_Offset";
                BytesInfo countBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(count, this);
                BytesInfo offsetBytesInfo = (BytesInfo)ObjectUtil.getFieldValueByFieldName(offset, this);
                countBytesInfo.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
                offsetBytesInfo.setValue(StringUtil.decToHex(String.valueOf(currentOffset), 8));
                for (CodeInfoBean codeInfo : codeInfos.values()) {
                    String codeName = codeInfo.getCodeName();
                    String codeNum = codeInfo.getCodeNum();
                    BytesInfo bytesInfo = new BytesInfo("UNIT", 4, codeName, codeNum, "Send Code");
                    this.Send_CITY_MANUFACTURER.add(bytesInfo);
                    currentOffset += 4;
                }

            }
        }

    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getIndex() {
        return Index;
    }

    public void setIndex(BytesInfo index) {
        Index = index;
    }

    public BytesInfo getInterface_Type() {
        return Interface_Type;
    }

    public void setInterface_Type(BytesInfo interface_Type) {
        Interface_Type = interface_Type;
    }

    public BytesInfo getPID_NUM() {
        return PID_NUM;
    }

    public void setPID_NUM(BytesInfo PID_NUM) {
        this.PID_NUM = PID_NUM;
    }

    public BytesInfo getPID_Offset() {
        return PID_Offset;
    }

    public void setPID_Offset(BytesInfo PID_Offset) {
        this.PID_Offset = PID_Offset;
    }

    public BytesInfo getRemote_ID_NUM() {
        return Remote_ID_NUM;
    }

    public void setRemote_ID_NUM(BytesInfo remote_ID_NUM) {
        Remote_ID_NUM = remote_ID_NUM;
    }

    public BytesInfo getRemote_ID_Offset() {
        return Remote_ID_Offset;
    }

    public void setRemote_ID_Offset(BytesInfo remote_ID_Offset) {
        Remote_ID_Offset = remote_ID_Offset;
    }

    public BytesInfo getCBI_ID() {
        return CBI_ID;
    }

    public void setCBI_ID(BytesInfo CBI_ID) {
        this.CBI_ID = CBI_ID;
    }

    public BytesInfo getCity_ID() {
        return City_ID;
    }

    public void setCity_ID(BytesInfo city_ID) {
        City_ID = city_ID;
    }

    public BytesInfo getManufacturer_ID() {
        return Manufacturer_ID;
    }

    public void setManufacturer_ID(BytesInfo manufacturer_ID) {
        Manufacturer_ID = manufacturer_ID;
    }

    public BytesInfo getThe_First_BZXX() {
        return The_First_BZXX;
    }

    public void setThe_First_BZXX(BytesInfo the_First_BZXX) {
        The_First_BZXX = the_First_BZXX;
    }

    public BytesInfo getThe_Second_BZXX() {
        return The_Second_BZXX;
    }

    public void setThe_Second_BZXX(BytesInfo the_Second_BZXX) {
        The_Second_BZXX = the_Second_BZXX;
    }

    public BytesInfo getThe_Third_BZXX() {
        return The_Third_BZXX;
    }

    public void setThe_Third_BZXX(BytesInfo the_Third_BZXX) {
        The_Third_BZXX = the_Third_BZXX;
    }

    public BytesInfo getThe_Fourth_BZXX() {
        return The_Fourth_BZXX;
    }

    public void setThe_Fourth_BZXX(BytesInfo the_Fourth_BZXX) {
        The_Fourth_BZXX = the_Fourth_BZXX;
    }

    public BytesInfo getPR_VER() {
        return PR_VER;
    }

    public void setPR_VER(BytesInfo PR_VER) {
        this.PR_VER = PR_VER;
    }

    public BytesInfo getLineMap_VER() {
        return LineMap_VER;
    }

    public void setLineMap_VER(BytesInfo lineMap_VER) {
        LineMap_VER = lineMap_VER;
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

    public BytesInfo getReceive_GDQD_Count() {
        return Receive_GDQD_Count;
    }

    public void setReceive_GDQD_Count(BytesInfo receive_GDQD_Count) {
        Receive_GDQD_Count = receive_GDQD_Count;
    }

    public BytesInfo getReceive_GDQD_Offset() {
        return Receive_GDQD_Offset;
    }

    public void setReceive_GDQD_Offset(BytesInfo receive_GDQD_Offset) {
        Receive_GDQD_Offset = receive_GDQD_Offset;
    }

    public BytesInfo getReceive_XHJ_Count() {
        return Receive_XHJ_Count;
    }

    public void setReceive_XHJ_Count(BytesInfo receive_XHJ_Count) {
        Receive_XHJ_Count = receive_XHJ_Count;
    }

    public BytesInfo getReceive_XHJ_Offset() {
        return Receive_XHJ_Offset;
    }

    public void setReceive_XHJ_Offset(BytesInfo receive_XHJ_Offset) {
        Receive_XHJ_Offset = receive_XHJ_Offset;
    }

    public BytesInfo getReceive_PSD_Count() {
        return Receive_PSD_Count;
    }

    public void setReceive_PSD_Count(BytesInfo receive_PSD_Count) {
        Receive_PSD_Count = receive_PSD_Count;
    }

    public BytesInfo getReceive_PSD_Offset() {
        return Receive_PSD_Offset;
    }

    public void setReceive_PSD_Offset(BytesInfo receive_PSD_Offset) {
        Receive_PSD_Offset = receive_PSD_Offset;
    }

    public BytesInfo getReceive_PSDFAO_Count() {
        return Receive_PSDFAO_Count;
    }

    public void setReceive_PSDFAO_Count(BytesInfo receive_PSDFAO_Count) {
        Receive_PSDFAO_Count = receive_PSDFAO_Count;
    }

    public BytesInfo getReceive_PSDFAO_Offset() {
        return Receive_PSDFAO_Offset;
    }

    public void setReceive_PSDFAO_Offset(BytesInfo receive_PSDFAO_Offset) {
        Receive_PSDFAO_Offset = receive_PSDFAO_Offset;
    }

    public BytesInfo getReceive_CITY_Count() {
        return Receive_CITY_Count;
    }

    public void setReceive_CITY_Count(BytesInfo receive_CITY_Count) {
        Receive_CITY_Count = receive_CITY_Count;
    }

    public BytesInfo getReceive_CITY_Offset() {
        return Receive_CITY_Offset;
    }

    public void setReceive_CITY_Offset(BytesInfo receive_CITY_Offset) {
        Receive_CITY_Offset = receive_CITY_Offset;
    }

    public BytesInfo getReceive_MANUFACTURER_Count() {
        return Receive_MANUFACTURER_Count;
    }

    public void setReceive_MANUFACTURER_Count(BytesInfo receive_MANUFACTURER_Count) {
        Receive_MANUFACTURER_Count = receive_MANUFACTURER_Count;
    }

    public BytesInfo getReceive_MANUFACTURER_Offset() {
        return Receive_MANUFACTURER_Offset;
    }

    public void setReceive_MANUFACTURER_Offset(BytesInfo receive_MANUFACTURER_Offset) {
        Receive_MANUFACTURER_Offset = receive_MANUFACTURER_Offset;
    }

    public BytesInfo getSend_Count() {
        return Send_Count;
    }

    public void setSend_Count(BytesInfo send_Count) {
        Send_Count = send_Count;
    }

    public BytesInfo getSend_XHJ_Count() {
        return Send_XHJ_Count;
    }

    public void setSend_XHJ_Count(BytesInfo send_XHJ_Count) {
        Send_XHJ_Count = send_XHJ_Count;
    }

    public BytesInfo getSend_XHJ_Offset() {
        return Send_XHJ_Offset;
    }

    public void setSend_XHJ_Offset(BytesInfo send_XHJ_Offset) {
        Send_XHJ_Offset = send_XHJ_Offset;
    }

    public BytesInfo getSend_PSD_Count() {
        return Send_PSD_Count;
    }

    public void setSend_PSD_Count(BytesInfo send_PSD_Count) {
        Send_PSD_Count = send_PSD_Count;
    }

    public BytesInfo getSend_PSD_Offset() {
        return Send_PSD_Offset;
    }

    public void setSend_PSD_Offset(BytesInfo send_PSD_Offset) {
        Send_PSD_Offset = send_PSD_Offset;
    }

    public BytesInfo getSend_PSDFAO_Count() {
        return Send_PSDFAO_Count;
    }

    public void setSend_PSDFAO_Count(BytesInfo send_PSDFAO_Count) {
        Send_PSDFAO_Count = send_PSDFAO_Count;
    }

    public BytesInfo getSend_PSDFAO_Offset() {
        return Send_PSDFAO_Offset;
    }

    public void setSend_PSDFAO_Offset(BytesInfo send_PSDFAO_Offset) {
        Send_PSDFAO_Offset = send_PSDFAO_Offset;
    }

    public BytesInfo getSend_CITY_Count() {
        return Send_CITY_Count;
    }

    public void setSend_CITY_Count(BytesInfo send_CITY_Count) {
        Send_CITY_Count = send_CITY_Count;
    }

    public BytesInfo getSend_CITY_Offset() {
        return Send_CITY_Offset;
    }

    public void setSend_CITY_Offset(BytesInfo send_CITY_Offset) {
        Send_CITY_Offset = send_CITY_Offset;
    }

    public BytesInfo getSend_MANUFACTURER_Count() {
        return Send_MANUFACTURER_Count;
    }

    public void setSend_MANUFACTURER_Count(BytesInfo send_MANUFACTURER_Count) {
        Send_MANUFACTURER_Count = send_MANUFACTURER_Count;
    }

    public BytesInfo getSend_MANUFACTURER_Offset() {
        return Send_MANUFACTURER_Offset;
    }

    public void setSend_MANUFACTURER_Offset(BytesInfo send_MANUFACTURER_Offset) {
        Send_MANUFACTURER_Offset = send_MANUFACTURER_Offset;
    }

    public List<BytesInfo> getPidInfo() {
        return pidInfo;
    }

    public void setPidInfo(List<BytesInfo> pidInfo) {
        this.pidInfo = pidInfo;
    }

    public List<BytesInfo> getRemoteIdInfo() {
        return remoteIdInfo;
    }

    public void setRemoteIdInfo(List<BytesInfo> remoteIdInfo) {
        this.remoteIdInfo = remoteIdInfo;
    }



    public List<BytesInfo> getReceive_CITY_MANUFACTURER() {
        return Receive_CITY_MANUFACTURER;
    }

    public void setReceive_CITY_MANUFACTURER(List<BytesInfo> receive_CITY_MANUFACTURER) {
        Receive_CITY_MANUFACTURER = receive_CITY_MANUFACTURER;
    }



    public List<BytesInfo> getSend_CITY_MANUFACTURER() {
        return Send_CITY_MANUFACTURER;
    }

    public void setSend_CITY_MANUFACTURER(List<BytesInfo> send_CITY_MANUFACTURER) {
        Send_CITY_MANUFACTURER = send_CITY_MANUFACTURER;
    }

    public List<HLVOBCCodeBean> getReceive_GDQD() {
        return Receive_GDQD;
    }

    public void setReceive_GDQD(List<HLVOBCCodeBean> receive_GDQD) {
        Receive_GDQD = receive_GDQD;
    }

    public HLVOBCCodeBean getReceive_XHJ() {
        return Receive_XHJ;
    }

    public void setReceive_XHJ(HLVOBCCodeBean receive_XHJ) {
        Receive_XHJ = receive_XHJ;
    }

    public HLVOBCCodeBean getReceive_PSD() {
        return Receive_PSD;
    }

    public void setReceive_PSD(HLVOBCCodeBean receive_PSD) {
        Receive_PSD = receive_PSD;
    }

    public HLVOBCCodeBean getReceive_PSDFAO() {
        return Receive_PSDFAO;
    }

    public void setReceive_PSDFAO(HLVOBCCodeBean receive_PSDFAO) {
        Receive_PSDFAO = receive_PSDFAO;
    }

    public HLVOBCCodeBean getSend_XHJ() {
        return Send_XHJ;
    }

    public void setSend_XHJ(HLVOBCCodeBean send_XHJ) {
        Send_XHJ = send_XHJ;
    }

    public HLVOBCCodeBean getSend_PSD() {
        return Send_PSD;
    }

    public void setSend_PSD(HLVOBCCodeBean send_PSD) {
        Send_PSD = send_PSD;
    }

    public HLVOBCCodeBean getSend_PSDFAO() {
        return Send_PSDFAO;
    }

    public void setSend_PSDFAO(HLVOBCCodeBean send_PSDFAO) {
        Send_PSDFAO = send_PSDFAO;
    }
}
