package vip.jayson.pojo.fileBean;

public class VTLSegExpressiionInfo {

    private BytesInfo type;
    private BytesInfo item;
    private BytesInfo offset;

    public VTLSegExpressiionInfo() {
        this.type = new BytesInfo("UNIT", 4, "Type");
        this.item = new BytesInfo("UNIT", 4, "Item");
        this.offset = new BytesInfo("UNIT", 4, "Offest");
    }

    public BytesInfo getType() {
        return type;
    }

    public void setType(BytesInfo type) {
        this.type = type;
    }

    public BytesInfo getItem() {
        return item;
    }

    public void setItem(BytesInfo item) {
        this.item = item;
    }

    public BytesInfo getOffset() {
        return offset;
    }

    public void setOffset(BytesInfo offset) {
        this.offset = offset;
    }
}
