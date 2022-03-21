package vip.jayson.pojo.tisSwitchDataArea;

import vip.jayson.config.CAAData;
import vip.jayson.pojo.additionalFunctionArea.GASInfoBean;
import vip.jayson.pojo.dataBean.ConfigBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TISAreaBean {
    private BytesInfo Count = new BytesInfo("UNIT", 4, "Count");
    private List<TISInfoBean> TISInfo = new ArrayList<>();
    public static String[] order = {"Count","TISInfo"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
            for (int i = 0; i < order.length - 1; i++) {
                BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
                allContent.add(fieldValueByFieldName);
            }
            for (TISInfoBean info : this.TISInfo) {
                allContent.addAll(info.getAllContent());
            }
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(LinkedHashMap<String, LinkedHashMap<String, List<String>>> tisInfo) {
        for (String blockKey : tisInfo.keySet()) {
            Integer type = 0;
            if (blockKey.equals("TCC4")) {
                type = 1;
            } else if (blockKey.equals("CBI3")) {
                type = 2;
            } else {
                type = 3;
            }
            for (List<String> index : tisInfo.get(blockKey).values()) {
                TISInfoBean tisInfoBean = new TISInfoBean();
                tisInfoBean.setAll(type, index);
                this.TISInfo.add(tisInfoBean);
            }
        }
        this.Count.setValue(StringUtil.decToHex(String.valueOf(this.TISInfo.size()),8));
    }
    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getCount() {
        return Count;
    }

    public void setCount(BytesInfo count) {
        Count = count;
    }

    public List<TISInfoBean> getTISInfo() {
        return TISInfo;
    }

    public void setTISInfo(List<TISInfoBean> TISInfo) {
        this.TISInfo = TISInfo;
    }
}
