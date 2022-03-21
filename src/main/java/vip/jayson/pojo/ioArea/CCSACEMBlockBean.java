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
import java.util.LinkedHashMap;
import java.util.List;

public class CCSACEMBlockBean {

    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo CBI_ID = new BytesInfo("UNIT", 4, "CBI_ID");
    private BytesInfo Life_Time = new BytesInfo("UNIT", 4, "Life_Time");
    private BytesInfo MaxTop_CC_NUM = new BytesInfo("UNIT", 4, "MaxTop_CC_NUM", "00000000");
    private BytesInfo CC_COMM_CYCLE = new BytesInfo("UNIT", 4, "CC_COMM_CYCLE", "00000000");
    private BytesInfo Request_CBI_MSG_Timeout = new BytesInfo("UNIT", 4, "Request_CBI_MSG_Timeout", "00002710");
    private BytesInfo Receive_Count = new BytesInfo("UNIT", 4, "Receive_Count");
    private BytesInfo Receive_Offset = new BytesInfo("UNIT", 4, "Receive_Offset");
    private BytesInfo Send_Count = new BytesInfo("UNIT", 4, "Send_Count");
    private BytesInfo Send_Offset = new BytesInfo("UNIT", 4, "Send_Offset");
    private List<BytesInfo> Receive_Codes = new ArrayList<>();
    private List<BytesInfo> Send_Codes = new ArrayList<>();
    public static String[] order = {"Index","CBI_ID","Life_Time","MaxTop_CC_NUM","CC_COMM_CYCLE","Request_CBI_MSG_Timeout","Receive_Count","Receive_Offset","Send_Count","Send_Offset","Receive_Codes","Send_Codes"};
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
        for (String globalKey : global.keySet()) {
            CodeInfoBean codeInfoBean = global.get(globalKey);
            String value = codeInfoBean.getValue();
            if (globalKey.equals("INDEX")) {
                this.Index.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("CBI_ID")) {
                this.CBI_ID.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("LIFETIME")) {
                this.Life_Time.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value)*1000), 8));
            }

            this.MaxTop_CC_NUM.setValue(StringUtil.decToHex("0", 8));

            if (globalKey.equals("CC_COMM_CYCLE")) {
                this.CC_COMM_CYCLE.setValue(StringUtil.decToHex(value, 8));
            }
            if (globalKey.equals("REQUEST_CBI_MSG_TIMEOUT")) {
                this.Request_CBI_MSG_Timeout.setValue(StringUtil.decToHex(String.valueOf(Integer.parseInt(value)*1000), 8));
            }
        }
        Integer fixOffset = 10 * 4;
        for (CodeBean codeBean : receive) {
            if (codeBean.getBlockName().equals("OVERVIEW")) {
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Receive_Count.setValue(StringUtil.decToHex(receiveLength,8));

            } else{
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                this.Receive_Offset.setValue(StringUtil.decToHex(String.valueOf(fixOffset),8));
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
                String receiveLength = codeBean.getCodeInfo().get("MESSAGE").getValue();
                this.Send_Count.setValue(StringUtil.decToHex(receiveLength,8));
            } else{
                LinkedHashMap<String, CodeInfoBean> codeInfos = codeBean.getCodeInfo();
                this.Send_Offset.setValue(StringUtil.decToHex(String.valueOf(fixOffset + this.Receive_Codes.size() * 4),8));
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
    public BytesInfo getIndex() {
        return Index;
    }

    public void setIndex(BytesInfo index) {
        Index = index;
    }

    public BytesInfo getCBI_ID() {
        return CBI_ID;
    }

    public void setCBI_ID(BytesInfo CBI_ID) {
        this.CBI_ID = CBI_ID;
    }

    public BytesInfo getLife_Time() {
        return Life_Time;
    }

    public void setLife_Time(BytesInfo life_Time) {
        Life_Time = life_Time;
    }

    public BytesInfo getMaxTop_CC_NUM() {
        return MaxTop_CC_NUM;
    }

    public void setMaxTop_CC_NUM(BytesInfo maxTop_CC_NUM) {
        MaxTop_CC_NUM = maxTop_CC_NUM;
    }

    public BytesInfo getCC_COMM_CYCLE() {
        return CC_COMM_CYCLE;
    }

    public void setCC_COMM_CYCLE(BytesInfo CC_COMM_CYCLE) {
        this.CC_COMM_CYCLE = CC_COMM_CYCLE;
    }

    public BytesInfo getRequest_CBI_MSG_Timeout() {
        return Request_CBI_MSG_Timeout;
    }

    public void setRequest_CBI_MSG_Timeout(BytesInfo request_CBI_MSG_Timeout) {
        Request_CBI_MSG_Timeout = request_CBI_MSG_Timeout;
    }

    public BytesInfo getReceive_Count() {
        return Receive_Count;
    }

    public void setReceive_Count(BytesInfo receive_Count) {
        Receive_Count = receive_Count;
    }

    public BytesInfo getReceive_Offset() {
        return Receive_Offset;
    }

    public void setReceive_Offset(BytesInfo receive_Offset) {
        Receive_Offset = receive_Offset;
    }

    public BytesInfo getSend_Count() {
        return Send_Count;
    }

    public void setSend_Count(BytesInfo send_Count) {
        Send_Count = send_Count;
    }

    public BytesInfo getSend_Offset() {
        return Send_Offset;
    }

    public void setSend_Offset(BytesInfo send_Offset) {
        Send_Offset = send_Offset;
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
