package vip.jayson.pojo.vtlArea;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class VtlSegExpressionStrucBean {
    private BytesInfo Size = new BytesInfo("UNIT", 4, "Size");
    private List<VtlSegExpItemBean> itemInfo = new ArrayList<>();
    public static String[] order = {"Size","itemInfo"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
            allContent.add(this.Size);
            for (VtlSegExpItemBean vatlSegExpItemBean : itemInfo) {
                allContent.addAll(vatlSegExpItemBean.getAllContent());
            }
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(String[] items) {
        this.Size.setValue(StringUtil.decToHex(String.valueOf(items.length), 8));
        for (String item : items) {
            String[] vars = item.trim().split("\\*");
            VtlSegExpItemBean vtlSegExpItemBean = new VtlSegExpItemBean();
            vtlSegExpItemBean.setAll(vars);
            this.itemInfo.add(vtlSegExpItemBean);
        }
    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public Integer getAllContentLength() {
        return this.allContent.size();
    }
    public BytesInfo getSize() {
        return Size;
    }

    public void setSize(BytesInfo size) {
        Size = size;
    }

    public List<VtlSegExpItemBean> getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(List<VtlSegExpItemBean> itemInfo) {
        this.itemInfo = itemInfo;
    }
}
