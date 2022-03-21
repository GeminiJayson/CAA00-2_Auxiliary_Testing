package vip.jayson.pojo.fileBean;

public class VarStructureBody {
    private BytesInfo code;
    private BytesInfo value;
    private BytesInfo trueValue;
    private BytesInfo falseValue;
    private BytesInfo tick;
    private BytesInfo nameIndex;

    public VarStructureBody() {
        this.code = new BytesInfo("UNIT", 4, "Code");
        this.value = new BytesInfo("UNIT", 4, "Value", "00000000", "Variable value, default 0变量值，默认为0");
        this.trueValue = new BytesInfo("UNIT", 4, "True_Value");
        this.falseValue = new BytesInfo("UNIT", 4, "False_Value");
        this.tick = new BytesInfo("UNIT", 4, "Tick", "00000000","Tick, default 0周期值，默认为0");
        this.nameIndex = new BytesInfo("UNIT", 4, "Name_Index", "00000000","Index, default 0索引，默认为0");
    }

    public BytesInfo getCode() {
        return code;
    }

    public void setCode(BytesInfo code) {
        this.code = code;
    }

    public BytesInfo getValue() {
        return value;
    }

    public void setValue(BytesInfo value) {
        this.value = value;
    }

    public BytesInfo getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(BytesInfo trueValue) {
        this.trueValue = trueValue;
    }

    public BytesInfo getFalseValue() {
        return falseValue;
    }

    public void setFalseValue(BytesInfo falseValue) {
        this.falseValue = falseValue;
    }

    public BytesInfo getTick() {
        return tick;
    }

    public void setTick(BytesInfo tick) {
        this.tick = tick;
    }

    public BytesInfo getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(BytesInfo nameIndex) {
        this.nameIndex = nameIndex;
    }
}
