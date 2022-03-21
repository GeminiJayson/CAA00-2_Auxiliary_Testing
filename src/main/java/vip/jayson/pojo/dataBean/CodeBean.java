package vip.jayson.pojo.dataBean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CodeBean {
    private String blockName;
    private Map<String, Integer> flagInfo = new HashMap<>();
    private LinkedHashMap<String, CodeInfoBean> codeInfo;

    public CodeBean() {
    }

    public CodeBean(String blockName, LinkedHashMap<String, CodeInfoBean> codeInfo) {
        this.blockName = blockName;
        this.codeInfo = codeInfo;
    }

    public CodeBean(String blockName, Map<String, Integer> flagInfo, LinkedHashMap<String, CodeInfoBean> codeInfo) {
        this.blockName = blockName;
        this.flagInfo = flagInfo;
        this.codeInfo = codeInfo;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Map<String, Integer> getFlagInfo() {
        return flagInfo;
    }

    public void setFlagInfo(Map<String, Integer> flagInfo) {
        this.flagInfo = flagInfo;
    }

    public LinkedHashMap<String, CodeInfoBean> getCodeInfo() {
        return codeInfo;
    }

    public void setCodeInfo(LinkedHashMap<String, CodeInfoBean> codeInfo) {
        this.codeInfo = codeInfo;
    }

    @Override
    public String toString() {
        return "CodeBean{" +
                "blockName='" + blockName + '\'' +
                ", flagInfo=" + flagInfo +
                ", codeInfo=" + codeInfo +
                '}';
    }
}
