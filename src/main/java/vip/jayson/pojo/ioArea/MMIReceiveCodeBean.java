package vip.jayson.pojo.ioArea;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class MMIReceiveCodeBean {
    private BytesInfo Signal = new BytesInfo("UNIT", 4, "Signal");
    private BytesInfo Index = new BytesInfo("UNIT", 4, "Index");
    private BytesInfo Safe_Command = new BytesInfo("UNIT", 4, "Safe_Command");
    private BytesInfo Retern_Value = new BytesInfo("UNIT", 4, "Retern_Value");
    private BytesInfo Safe_Command_Value = new BytesInfo("UNIT", 4, "Safe_Command_Value");
    private BytesInfo Safe_Command_Signal = new BytesInfo("UNIT", 4, "Safe_Command_Signal");
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            allContent.add(this.Signal);
        allContent.add(this.Index);
        allContent.add(this.Safe_Command);
        allContent.add(this.Retern_Value);
        allContent.add(this.Safe_Command_Value);
        allContent.add(this.Safe_Command_Signal);}
        return allContent;
    }
    public BytesInfo getSignal() {
        return Signal;
    }

    public void setSignal(BytesInfo signal) {
        Signal = signal;
    }

    public BytesInfo getIndex() {
        return Index;
    }

    public void setIndex(BytesInfo index) {
        Index = index;
    }

    public BytesInfo getSafe_Command() {
        return Safe_Command;
    }

    public void setSafe_Command(BytesInfo safe_Command) {
        Safe_Command = safe_Command;
    }

    public BytesInfo getRetern_Value() {
        return Retern_Value;
    }

    public void setRetern_Value(BytesInfo retern_Value) {
        Retern_Value = retern_Value;
    }

    public BytesInfo getSafe_Command_Value() {
        return Safe_Command_Value;
    }

    public void setSafe_Command_Value(BytesInfo safe_Command_Value) {
        Safe_Command_Value = safe_Command_Value;
    }

    public BytesInfo getSafe_Command_Signal() {
        return Safe_Command_Signal;
    }

    public void setSafe_Command_Signal(BytesInfo safe_Command_Signal) {
        Safe_Command_Signal = safe_Command_Signal;
    }
    public static Integer getLength(){
        return 4 * 6;
    }
}
