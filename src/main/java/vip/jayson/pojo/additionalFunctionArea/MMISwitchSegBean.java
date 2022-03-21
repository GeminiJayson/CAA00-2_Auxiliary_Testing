package vip.jayson.pojo.additionalFunctionArea;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

public class MMISwitchSegBean {
    private BytesInfo MMI_ATS_SWITCH_FLAG = new BytesInfo("UNIT", 4, "MMI_ATS_SWITCH_FLAG", StringUtil.decToHex("1", 8));
    public static String[] order = {"MMI_ATS_SWITCH_FLAG"};
    public BytesInfo getMMI_ATS_SWITCH_FLAG() {
        return MMI_ATS_SWITCH_FLAG;
    }

    public void setMMI_ATS_SWITCH_FLAG(BytesInfo MMI_ATS_SWITCH_FLAG) {
        this.MMI_ATS_SWITCH_FLAG = MMI_ATS_SWITCH_FLAG;
    }
}
