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

public class HLCIBlockBean {
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Interface_Type = new BytesInfo("UNIT", 4, "Interface_Type", "00000606");
    private BytesInfo Local_ID = new BytesInfo("UNIT", 4, "Local_ID");
    private BytesInfo Remote_ID = new BytesInfo("UNIT", 4, "Remote_ID");
    private BytesInfo City_ID = new BytesInfo("UNIT", 4, "City_ID");
    private BytesInfo Manufacturer_ID = new BytesInfo("UNIT", 4, "Manufacturer_ID");
    private BytesInfo PR_VER = new BytesInfo("UNIT", 4, "PR_VER");
    private BytesInfo LineMap_VER = new BytesInfo("UNIT", 4, "LineMap_VER");
    private BytesInfo Timeout = new BytesInfo("UNIT", 4, "Timeout");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Receive_DCZT_Count = new BytesInfo("UNIT", 4, "Receive_DCZT_Count");
    private BytesInfo Receive_DCZT_Offset = new BytesInfo("UNIT", 4, "Receive_DCZT_Offset");
    private BytesInfo Receive_WLQDZT_Count = new BytesInfo("UNIT", 4, "Receive_WLQDZT_Count");
    private BytesInfo Receive_WLQDZT_Offset = new BytesInfo("UNIT", 4, "Receive_WLQDZT_Offset");
    private BytesInfo Receive_LJQDZT_Count = new BytesInfo("UNIT", 4, "Receive_LJQDZT_Count");
    private BytesInfo Receive_LJQDZT_Offset = new BytesInfo("UNIT", 4, "Receive_LJQDZT_Offset");
    private BytesInfo Receive_XHJZT_Count = new BytesInfo("UNIT", 4, "Receive_XHJZT_Count");
    private BytesInfo Receive_XHJZT_Offset = new BytesInfo("UNIT", 4, "Receive_XHJZT_Offset");
    private BytesInfo Receive_PSDZT_Count = new BytesInfo("UNIT", 4, "Receive_PSDZT_Count");
    private BytesInfo Receive_PSDZT_Offset = new BytesInfo("UNIT", 4, "Receive_PSDZT_Offset");
    private BytesInfo Receive_ESPZT_Count = new BytesInfo("UNIT", 4, "Receive_ESPZT_Count");
    private BytesInfo Receive_ESPZT_Offset = new BytesInfo("UNIT", 4, "Receive_ESPZT_Offset");
    private BytesInfo Receive_ZCZT_Count = new BytesInfo("UNIT", 4, "Receive_ZCZT_Count");
    private BytesInfo Receive_ZCZT_Offset = new BytesInfo("UNIT", 4, "Receive_ZCZT_Offset");
    private BytesInfo Receive_FDGZT_Count = new BytesInfo("UNIT", 4, "Receive_FDGZT_Count");
    private BytesInfo Receive_FDGZT_Offset = new BytesInfo("UNIT", 4, "Receive_FDGZT_Offset");
    private BytesInfo Receive_SDJSZT_Count = new BytesInfo("UNIT", 4, "Receive_SDJSZT_Count");
    private BytesInfo Receive_SDJSZT_Offset = new BytesInfo("UNIT", 4, "Receive_SDJSZT_Offset");
    private BytesInfo Receive_TSRZT_Count = new BytesInfo("UNIT", 4, "Receive_TSRZT_Count");
    private BytesInfo Receive_TSRZT_Offset = new BytesInfo("UNIT", 4, "Receive_TSRZT_Offset");
    private BytesInfo Receive_CITY_Count = new BytesInfo("UNIT", 4, "Receive_CITY_Count");
    private BytesInfo Receive_CITY_Offset = new BytesInfo("UNIT", 4, "Receive_CITY_Offset");
    private BytesInfo Receive_MANUFACTURER_Count = new BytesInfo("UNIT", 4, "Receive_MANUFACTURER_Count");
    private BytesInfo Receive_MANUFACTURER_Offset = new BytesInfo("UNIT", 4, "Receive_MANUFACTURER_Offset");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private BytesInfo Send_DCZT_Count = new BytesInfo("UNIT", 4, "Send_DCZT_Count");
    private BytesInfo Send_DCZT_Offset = new BytesInfo("UNIT", 4, "Send_DCZT_Offset");
    private BytesInfo Send_WLQDZT_Count = new BytesInfo("UNIT", 4, "Send_WLQDZT_Count");
    private BytesInfo Send_WLQDZT_Offset = new BytesInfo("UNIT", 4, "Send_WLQDZT_Offset");
    private BytesInfo Send_LJQDZT_Count = new BytesInfo("UNIT", 4, "Send_LJQDZT_Count");
    private BytesInfo Send_LJQDZT_Offset = new BytesInfo("UNIT", 4, "Send_LJQDZT_Offset");
    private BytesInfo Send_XHJZT_Count = new BytesInfo("UNIT", 4, "Send_XHJZT_Count");
    private BytesInfo Send_XHJZT_Offset = new BytesInfo("UNIT", 4, "Send_XHJZT_Offset");
    private BytesInfo Send_PSDZT_Count = new BytesInfo("UNIT", 4, "Send_PSDZT_Count");
    private BytesInfo Send_PSDZT_Offset = new BytesInfo("UNIT", 4, "Send_PSDZT_Offset");
    private BytesInfo Send_ESPZT_Count = new BytesInfo("UNIT", 4, "Send_ESPZT_Count");
    private BytesInfo Send_ESPZT_Offset = new BytesInfo("UNIT", 4, "Send_ESPZT_Offset");
    private BytesInfo Send_ZCZT_Count = new BytesInfo("UNIT", 4, "Send_ZCZT_Count");
    private BytesInfo Send_ZCZT_Offset = new BytesInfo("UNIT", 4, "Send_ZCZT_Offset");
    private BytesInfo Send_FDGZT_Count = new BytesInfo("UNIT", 4, "Send_FDGZT_Count");
    private BytesInfo Send_FDGZT_Offset = new BytesInfo("UNIT", 4, "Send_FDGZT_Offset");
    private BytesInfo Send_SDJSZT_Count = new BytesInfo("UNIT", 4, "Send_SDJSZT_Count");
    private BytesInfo Send_SDJSZT_Offset = new BytesInfo("UNIT", 4, "Send_SDJSZT_Offset");
    private BytesInfo Send_TSRZT_Count = new BytesInfo("UNIT", 4, "Send_TSRZT_Count");
    private BytesInfo Send_TSRZT_Offset = new BytesInfo("UNIT", 4, "Send_TSRZT_Offset");
    private BytesInfo Send_CITY_Count = new BytesInfo("UNIT", 4, "Send_CITY_Count");
    private BytesInfo Send_CITY_Offset = new BytesInfo("UNIT", 4, "Send_CITY_Offset");
    private BytesInfo Send_MANUFACTURER_Count = new BytesInfo("UNIT", 4, "Send_MANUFACTURER_Count");
    private BytesInfo Send_MANUFACTURER_Offset = new BytesInfo("UNIT", 4, "Send_MANUFACTURER_Offset");
    private List<BytesInfo> Receive_Codes = new ArrayList<>();
    private List<BytesInfo> Send_Codes = new ArrayList<>();
    public static String[] order = {"PID","Index","Interface_Type","Local_ID","Remote_ID","City_ID","Manufacturer_ID","PR_VER","LineMap_VER","Timeout","Receive_Count","Receive_DCZT_Count","Receive_DCZT_Offset","Receive_WLQDZT_Count","Receive_WLQDZT_Offset","Receive_LJQDZT_Count","Receive_LJQDZT_Offset","Receive_XHJZT_Count","Receive_XHJZT_Offset","Receive_PSDZT_Count","Receive_PSDZT_Offset","Receive_ESPZT_Count","Receive_ESPZT_Offset","Receive_ZCZT_Count","Receive_ZCZT_Offset","Receive_FDGZT_Count","Receive_FDGZT_Offset","Receive_SDJSZT_Count","Receive_SDJSZT_Offset","Receive_TSRZT_Count","Receive_TSRZT_Offset","Receive_CITY_Count","Receive_CITY_Offset","Receive_MANUFACTURER_Count","Receive_MANUFACTURER_Offset","Send_Count","Send_DCZT_Count","Send_DCZT_Offset","Send_WLQDZT_Count","Send_WLQDZT_Offset","Send_LJQDZT_Count","Send_LJQDZT_Offset","Send_XHJZT_Count","Send_XHJZT_Offset","Send_PSDZT_Count","Send_PSDZT_Offset","Send_ESPZT_Count","Send_ESPZT_Offset","Send_ZCZT_Count","Send_ZCZT_Offset","Send_FDGZT_Count","Send_FDGZT_Offset","Send_SDJSZT_Count","Send_SDJSZT_Offset","Send_TSRZT_Count","Send_TSRZT_Offset","Send_CITY_Count","Send_CITY_Offset","Send_MANUFACTURER_Count","Send_MANUFACTURER_Offset","Receive_Codes","Send_Codes"};
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
    public void setAll(InterfaceFileBean oneBlock) {
        LinkedHashMap<String, CodeInfoBean> global = oneBlock.getGlobal();
        List<CodeBean> receive = oneBlock.getReceive();
        List<CodeBean> send = oneBlock.getSend();
        if (!global.containsKey("PID")) {
            int pid  = CAAData.pid.get("HLCI").get("Start") + (Integer.parseInt(global.get("INDEX").getValue()) - 1) * CAAData.pid.get("HLCI").get("Step");
            SwitchInterfaceBlock.switchInfo.get("HLCI").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(String.valueOf(pid), 8));}});
            this.PID.setValue(StringUtil.decToHex(String.valueOf(pid), 8));
        } else {
            SwitchInterfaceBlock.switchInfo.get("HLCI").put(global.get("INDEX").getValue(), new ArrayList<String>(){{this.add(StringUtil.decToHex(global.get("PID").getValue(), 8));}});
            this.PID.setValue(StringUtil.decToHex(global.get("PID").getValue(), 8));
        }
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();

            if (globalKey.equals("INDEX")) {
                this.Index.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("INTERFACETYPE")) {
                this.Interface_Type.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("LOCAL_ID")) {
                this.Local_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("REMOTE_ID")) {
                this.Remote_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("CITY_ID")) {
                this.City_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("MANUFACTURER_ID")) {
                this.Manufacturer_ID.setValue(StringUtil.decToHex(value, 8));
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
            if (globalKey.equals("SWITCH_WEIGHT")) {
                SwitchInterfaceBlock.switchInfo.get("HLCI").get(global.get("INDEX").getValue()).add(StringUtil.decToHex(value, 8));
            }
        }
        Integer fixOffset = 60 * 4;
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

            } else{
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
                    if (codeName.equals("DEFAULT")) {
                        codeNum = "00000000";
                    }
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

    public BytesInfo getInterface_Type() {
        return Interface_Type;
    }

    public void setInterface_Type(BytesInfo interface_Type) {
        Interface_Type = interface_Type;
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

    public BytesInfo getReceive_DCZT_Count() {
        return Receive_DCZT_Count;
    }

    public void setReceive_DCZT_Count(BytesInfo receive_DCZT_Count) {
        Receive_DCZT_Count = receive_DCZT_Count;
    }

    public BytesInfo getReceive_DCZT_Offset() {
        return Receive_DCZT_Offset;
    }

    public void setReceive_DCZT_Offset(BytesInfo receive_DCZT_Offset) {
        Receive_DCZT_Offset = receive_DCZT_Offset;
    }

    public BytesInfo getReceive_WLQDZT_Count() {
        return Receive_WLQDZT_Count;
    }

    public void setReceive_WLQDZT_Count(BytesInfo receive_WLQDZT_Count) {
        Receive_WLQDZT_Count = receive_WLQDZT_Count;
    }

    public BytesInfo getReceive_WLQDZT_Offset() {
        return Receive_WLQDZT_Offset;
    }

    public void setReceive_WLQDZT_Offset(BytesInfo receive_WLQDZT_Offset) {
        Receive_WLQDZT_Offset = receive_WLQDZT_Offset;
    }

    public BytesInfo getReceive_LJQDZT_Count() {
        return Receive_LJQDZT_Count;
    }

    public void setReceive_LJQDZT_Count(BytesInfo receive_LJQDZT_Count) {
        Receive_LJQDZT_Count = receive_LJQDZT_Count;
    }

    public BytesInfo getReceive_LJQDZT_Offset() {
        return Receive_LJQDZT_Offset;
    }

    public void setReceive_LJQDZT_Offset(BytesInfo receive_LJQDZT_Offset) {
        Receive_LJQDZT_Offset = receive_LJQDZT_Offset;
    }

    public BytesInfo getReceive_XHJZT_Count() {
        return Receive_XHJZT_Count;
    }

    public void setReceive_XHJZT_Count(BytesInfo receive_XHJZT_Count) {
        Receive_XHJZT_Count = receive_XHJZT_Count;
    }

    public BytesInfo getReceive_XHJZT_Offset() {
        return Receive_XHJZT_Offset;
    }

    public void setReceive_XHJZT_Offset(BytesInfo receive_XHJZT_Offset) {
        Receive_XHJZT_Offset = receive_XHJZT_Offset;
    }

    public BytesInfo getReceive_PSDZT_Count() {
        return Receive_PSDZT_Count;
    }

    public void setReceive_PSDZT_Count(BytesInfo receive_PSDZT_Count) {
        Receive_PSDZT_Count = receive_PSDZT_Count;
    }

    public BytesInfo getReceive_PSDZT_Offset() {
        return Receive_PSDZT_Offset;
    }

    public void setReceive_PSDZT_Offset(BytesInfo receive_PSDZT_Offset) {
        Receive_PSDZT_Offset = receive_PSDZT_Offset;
    }

    public BytesInfo getReceive_ESPZT_Count() {
        return Receive_ESPZT_Count;
    }

    public void setReceive_ESPZT_Count(BytesInfo receive_ESPZT_Count) {
        Receive_ESPZT_Count = receive_ESPZT_Count;
    }

    public BytesInfo getReceive_ESPZT_Offset() {
        return Receive_ESPZT_Offset;
    }

    public void setReceive_ESPZT_Offset(BytesInfo receive_ESPZT_Offset) {
        Receive_ESPZT_Offset = receive_ESPZT_Offset;
    }

    public BytesInfo getReceive_ZCZT_Count() {
        return Receive_ZCZT_Count;
    }

    public void setReceive_ZCZT_Count(BytesInfo receive_ZCZT_Count) {
        Receive_ZCZT_Count = receive_ZCZT_Count;
    }

    public BytesInfo getReceive_ZCZT_Offset() {
        return Receive_ZCZT_Offset;
    }

    public void setReceive_ZCZT_Offset(BytesInfo receive_ZCZT_Offset) {
        Receive_ZCZT_Offset = receive_ZCZT_Offset;
    }

    public BytesInfo getReceive_FDGZT_Count() {
        return Receive_FDGZT_Count;
    }

    public void setReceive_FDGZT_Count(BytesInfo receive_FDGZT_Count) {
        Receive_FDGZT_Count = receive_FDGZT_Count;
    }

    public BytesInfo getReceive_FDGZT_Offset() {
        return Receive_FDGZT_Offset;
    }

    public void setReceive_FDGZT_Offset(BytesInfo receive_FDGZT_Offset) {
        Receive_FDGZT_Offset = receive_FDGZT_Offset;
    }

    public BytesInfo getReceive_SDJSZT_Count() {
        return Receive_SDJSZT_Count;
    }

    public void setReceive_SDJSZT_Count(BytesInfo receive_SDJSZT_Count) {
        Receive_SDJSZT_Count = receive_SDJSZT_Count;
    }

    public BytesInfo getReceive_SDJSZT_Offset() {
        return Receive_SDJSZT_Offset;
    }

    public void setReceive_SDJSZT_Offset(BytesInfo receive_SDJSZT_Offset) {
        Receive_SDJSZT_Offset = receive_SDJSZT_Offset;
    }

    public BytesInfo getReceive_TSRZT_Count() {
        return Receive_TSRZT_Count;
    }

    public void setReceive_TSRZT_Count(BytesInfo receive_TSRZT_Count) {
        Receive_TSRZT_Count = receive_TSRZT_Count;
    }

    public BytesInfo getReceive_TSRZT_Offset() {
        return Receive_TSRZT_Offset;
    }

    public void setReceive_TSRZT_Offset(BytesInfo receive_TSRZT_Offset) {
        Receive_TSRZT_Offset = receive_TSRZT_Offset;
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

    public BytesInfo getSend_WLQDZT_Count() {
        return Send_WLQDZT_Count;
    }

    public void setSend_WLQDZT_Count(BytesInfo send_WLQDZT_Count) {
        Send_WLQDZT_Count = send_WLQDZT_Count;
    }

    public BytesInfo getSend_WLQDZT_Offset() {
        return Send_WLQDZT_Offset;
    }

    public void setSend_WLQDZT_Offset(BytesInfo send_WLQDZT_Offset) {
        Send_WLQDZT_Offset = send_WLQDZT_Offset;
    }

    public BytesInfo getSend_LJQDZT_Count() {
        return Send_LJQDZT_Count;
    }

    public void setSend_LJQDZT_Count(BytesInfo send_LJQDZT_Count) {
        Send_LJQDZT_Count = send_LJQDZT_Count;
    }

    public BytesInfo getSend_LJQDZT_Offset() {
        return Send_LJQDZT_Offset;
    }

    public void setSend_LJQDZT_Offset(BytesInfo send_LJQDZT_Offset) {
        Send_LJQDZT_Offset = send_LJQDZT_Offset;
    }

    public BytesInfo getSend_XHJZT_Count() {
        return Send_XHJZT_Count;
    }

    public void setSend_XHJZT_Count(BytesInfo send_XHJZT_Count) {
        Send_XHJZT_Count = send_XHJZT_Count;
    }

    public BytesInfo getSend_XHJZT_Offset() {
        return Send_XHJZT_Offset;
    }

    public void setSend_XHJZT_Offset(BytesInfo send_XHJZT_Offset) {
        Send_XHJZT_Offset = send_XHJZT_Offset;
    }

    public BytesInfo getSend_PSDZT_Count() {
        return Send_PSDZT_Count;
    }

    public void setSend_PSDZT_Count(BytesInfo send_PSDZT_Count) {
        Send_PSDZT_Count = send_PSDZT_Count;
    }

    public BytesInfo getSend_PSDZT_Offset() {
        return Send_PSDZT_Offset;
    }

    public void setSend_PSDZT_Offset(BytesInfo send_PSDZT_Offset) {
        Send_PSDZT_Offset = send_PSDZT_Offset;
    }

    public BytesInfo getSend_ESPZT_Count() {
        return Send_ESPZT_Count;
    }

    public void setSend_ESPZT_Count(BytesInfo send_ESPZT_Count) {
        Send_ESPZT_Count = send_ESPZT_Count;
    }

    public BytesInfo getSend_ESPZT_Offset() {
        return Send_ESPZT_Offset;
    }

    public void setSend_ESPZT_Offset(BytesInfo send_ESPZT_Offset) {
        Send_ESPZT_Offset = send_ESPZT_Offset;
    }

    public BytesInfo getSend_ZCZT_Count() {
        return Send_ZCZT_Count;
    }

    public void setSend_ZCZT_Count(BytesInfo send_ZCZT_Count) {
        Send_ZCZT_Count = send_ZCZT_Count;
    }

    public BytesInfo getSend_ZCZT_Offset() {
        return Send_ZCZT_Offset;
    }

    public void setSend_ZCZT_Offset(BytesInfo send_ZCZT_Offset) {
        Send_ZCZT_Offset = send_ZCZT_Offset;
    }

    public BytesInfo getSend_FDGZT_Count() {
        return Send_FDGZT_Count;
    }

    public void setSend_FDGZT_Count(BytesInfo send_FDGZT_Count) {
        Send_FDGZT_Count = send_FDGZT_Count;
    }

    public BytesInfo getSend_FDGZT_Offset() {
        return Send_FDGZT_Offset;
    }

    public void setSend_FDGZT_Offset(BytesInfo send_FDGZT_Offset) {
        Send_FDGZT_Offset = send_FDGZT_Offset;
    }

    public BytesInfo getSend_SDJSZT_Count() {
        return Send_SDJSZT_Count;
    }

    public void setSend_SDJSZT_Count(BytesInfo send_SDJSZT_Count) {
        Send_SDJSZT_Count = send_SDJSZT_Count;
    }

    public BytesInfo getSend_SDJSZT_Offset() {
        return Send_SDJSZT_Offset;
    }

    public void setSend_SDJSZT_Offset(BytesInfo send_SDJSZT_Offset) {
        Send_SDJSZT_Offset = send_SDJSZT_Offset;
    }

    public BytesInfo getSend_TSRZT_Count() {
        return Send_TSRZT_Count;
    }

    public void setSend_TSRZT_Count(BytesInfo send_TSRZT_Count) {
        Send_TSRZT_Count = send_TSRZT_Count;
    }

    public BytesInfo getSend_TSRZT_Offset() {
        return Send_TSRZT_Offset;
    }

    public void setSend_TSRZT_Offset(BytesInfo send_TSRZT_Offset) {
        Send_TSRZT_Offset = send_TSRZT_Offset;
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
