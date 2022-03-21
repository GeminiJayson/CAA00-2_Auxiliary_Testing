package vip.jayson.pojo.vtlArea;

import vip.jayson.config.CAAData;
import vip.jayson.main.GenerateADSExcel;
import vip.jayson.pojo.dataBean.CodeAssignBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class VtlSegExpItemBean {
    private BytesInfo Count = new BytesInfo("UNIT", 4, "Count");
    private List<BytesInfo> varId = new ArrayList<>();
    public static String[] order = {"Count","varId"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        if (allContent.size() == 0) {
        allContent.add(this.Count);
        allContent.addAll(this.varId);}
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(String[] vars) {
        this.Count.setValue(StringUtil.decToHex(String.valueOf(vars.length), 8));
        for (String var : vars) {
            String varName = var.trim();
            String varNum = "";
            boolean ifNegative = false;
            if (varName.contains(".N.")) {
                varName = varName.split("\\.N\\.")[1];
                ifNegative = true;
            }
            varNum = GenerateADSExcel.currentStationCodeInfo.get(varName).getCodeNum();
            if (ifNegative) {
                varNum = StringUtil.decToHex(String.valueOf(Integer.parseInt(StringUtil.hexToDec(varNum)) + 1), 8);
            }
            this.varId.add(new BytesInfo("UNIT", 4, varName, varNum, ifNegative?"值取反":""));
        }
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

    public List<BytesInfo> getVarId() {
        return varId;
    }

    public void setVarId(List<BytesInfo> varId) {
        this.varId = varId;
    }
}
