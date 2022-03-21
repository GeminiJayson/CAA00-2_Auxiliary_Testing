package vip.jayson.pojo.varArea;

import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class VarSegVarBean {
    private BytesInfo Code = new BytesInfo("UNIT", 4, "Code");
    private BytesInfo Value = new BytesInfo("UNIT", 4, "Index", StringUtil.decToHex("0", 8));
    private BytesInfo True_Value = new BytesInfo("UNIT", 4, "True_Value");
    private BytesInfo False_Value = new BytesInfo("UNIT", 4, "False_Value");
    private BytesInfo Tick = new BytesInfo("UNIT", 4, "Tick", StringUtil.decToHex("0", 8));
    private BytesInfo Name_Index = new BytesInfo("UNIT", 4, "City_ID", StringUtil.decToHex("0", 8));
    private List<BytesInfo> allContent = new ArrayList<>();
    public List<BytesInfo> getAllContent() {
        allContent.add(this.Code);
        allContent.add(this.Value);
        allContent.add(this.True_Value);
        allContent.add(this.False_Value);
        allContent.add(this.Tick);
        allContent.add(this.Name_Index);
        return allContent;
    }
    public void setAllContent(CodeInfoBean codeInfoBean) {
        this.Code.setValue(codeInfoBean.getCodeNum());
        this.Code.setComment(codeInfoBean.getCodeName());
        this.True_Value.setValue(codeInfoBean.getCodeTrue());
        this.False_Value.setValue(codeInfoBean.getCodeFalse());
    }
    public BytesInfo getCode() {
        return Code;
    }

    public void setCode(BytesInfo code) {
        Code = code;
    }

    public BytesInfo getValue() {
        return Value;
    }

    public void setValue(BytesInfo value) {
        Value = value;
    }

    public BytesInfo getTrue_Value() {
        return True_Value;
    }

    public void setTrue_Value(BytesInfo true_Value) {
        True_Value = true_Value;
    }

    public BytesInfo getFalse_Value() {
        return False_Value;
    }

    public void setFalse_Value(BytesInfo false_Value) {
        False_Value = false_Value;
    }

    public BytesInfo getTick() {
        return Tick;
    }

    public void setTick(BytesInfo tick) {
        Tick = tick;
    }

    public BytesInfo getName_Index() {
        return Name_Index;
    }

    public void setName_Index(BytesInfo name_Index) {
        Name_Index = name_Index;
    }
}
