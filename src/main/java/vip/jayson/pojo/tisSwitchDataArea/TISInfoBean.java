package vip.jayson.pojo.tisSwitchDataArea;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class TISInfoBean {
    private BytesInfo INDEX = new BytesInfo("UNIT", 4, "INDEX");
    private BytesInfo PID = new BytesInfo("UNIT", 4, "PID");
    private BytesInfo Type = new BytesInfo("UNIT", 4, "Type");
    private BytesInfo Protocol_Type = new BytesInfo("UNIT", 4, "Protocol Type", StringUtil.decToHex("1", 8));
    private BytesInfo PID_Count = new BytesInfo("UNIT", 4, "PID Count", StringUtil.decToHex("4", 8));
    public static String[] order = {"INDEX","PID","Type","Protocol_Type","PID_Count"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
            for (int i = 0; i < order.length - 1; i++) {
                BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
                allContent.add(fieldValueByFieldName);
            }
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(Integer type, List<String> tisBlockInfo) {
        this.Type.setValue(StringUtil.decToHex(String.valueOf(type), 8));
        this.PID.setValue(tisBlockInfo.get(0));
        this.INDEX.setValue(tisBlockInfo.get(1));
    }
    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getINDEX() {
        return INDEX;
    }

    public void setINDEX(BytesInfo INDEX) {
        this.INDEX = INDEX;
    }

    public BytesInfo getPID() {
        return PID;
    }

    public void setPID(BytesInfo PID) {
        this.PID = PID;
    }

    public BytesInfo getType() {
        return Type;
    }

    public void setType(BytesInfo type) {
        Type = type;
    }

    public BytesInfo getProtocol_Type() {
        return Protocol_Type;
    }

    public void setProtocol_Type(BytesInfo protocol_Type) {
        Protocol_Type = protocol_Type;
    }

    public BytesInfo getPID_Count() {
        return PID_Count;
    }

    public void setPID_Count(BytesInfo PID_Count) {
        this.PID_Count = PID_Count;
    }
}
