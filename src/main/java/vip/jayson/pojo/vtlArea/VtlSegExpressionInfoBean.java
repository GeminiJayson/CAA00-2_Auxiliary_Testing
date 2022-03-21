package vip.jayson.pojo.vtlArea;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class VtlSegExpressionInfoBean {
    private BytesInfo Type = new BytesInfo("UNIT", 4, "Type", StringUtil.decToHex("0", 8));
    private BytesInfo Item = new BytesInfo("UNIT", 4, "Item");
    private BytesInfo Offset = new BytesInfo("UNIT", 4, "Offset");
    public static String[] order = {"Type", "Item","Offset"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
        allContent.add(this.Type);
        allContent.add(this.Item);
        allContent.add(this.Offset);}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(boolean ifTime) {

    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getType() {
        return Type;
    }

    public void setType(BytesInfo type) {
        Type = type;
    }

    public BytesInfo getItem() {
        return Item;
    }

    public void setItem(BytesInfo item) {
        Item = item;
    }

    public BytesInfo getOffset() {
        return Offset;
    }

    public void setOffset(BytesInfo offset) {
        Offset = offset;
    }
}
