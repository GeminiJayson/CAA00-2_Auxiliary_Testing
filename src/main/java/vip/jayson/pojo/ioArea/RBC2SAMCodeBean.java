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
import java.util.Map;

public class RBC2SAMCodeBean {
    private BytesInfo SAM_INDEX = new BytesInfo("UNIT", 4, "SAM_INDEX");
    private BytesInfo OID = new BytesInfo("UNIT", 4, "OID");
    private BytesInfo Count = new BytesInfo("UNIT", 4, "Count");
    private List<BytesInfo> Codes = new ArrayList<>();
    public static String[] order = {"SAM_INDEX","OID","Count","Codes"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size()==0) {
            for (int i = 0; i < order.length - 1; i++) {
            BytesInfo fieldValueByFieldName = (BytesInfo) ObjectUtil.getFieldValueByFieldName(order[i], this);
            allContent.add(fieldValueByFieldName);
        }
        allContent.addAll(Codes);}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(CodeBean codeBean) {
        Map<String, Integer> flagInfo = codeBean.getFlagInfo();
        this.SAM_INDEX.setValue(StringUtil.decToHex(String.valueOf(flagInfo.get("OID")), 8));
        this.OID.setValue(StringUtil.decToHex(String.valueOf(flagInfo.get("OID")), 8));
        LinkedHashMap<String, CodeInfoBean> codeInfo = codeBean.getCodeInfo();
        this.Count.setValue(StringUtil.decToHex(String.valueOf(codeInfo.size()), 8));
        for (CodeInfoBean value : codeInfo.values()) {
            BytesInfo bytesInfo = new BytesInfo("UNIT", 4, value.getCodeName(), value.getCodeNum(), "SAM_Send");
            this.Codes.add(bytesInfo);
        }
    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getSAM_INDEX() {
        return SAM_INDEX;
    }

    public void setSAM_INDEX(BytesInfo SAM_INDEX) {
        this.SAM_INDEX = SAM_INDEX;
    }

    public BytesInfo getOID() {
        return OID;
    }

    public void setOID(BytesInfo OID) {
        this.OID = OID;
    }

    public BytesInfo getCount() {
        return Count;
    }

    public void setCount(BytesInfo count) {
        Count = count;
    }

    public List<BytesInfo> getCodes() {
        return Codes;
    }

    public void setCodes(List<BytesInfo> codes) {
        Codes = codes;
    }
}
