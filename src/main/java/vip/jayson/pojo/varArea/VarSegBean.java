package vip.jayson.pojo.varArea;

import vip.jayson.config.CAAData;
import vip.jayson.main.GenerateADSExcel;
import vip.jayson.pojo.additionalFunctionArea.SwitchInterfaceBlock;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class VarSegBean {
    private BytesInfo Type = new BytesInfo("UNIT", 4, "Type");
    private BytesInfo Count = new BytesInfo("UNIT", 4, "Count");
    private List<VarSegVarBean> vars = new ArrayList<>();
    private List<BytesInfo> allContent = new ArrayList<>();
    public BytesInfo getType() {
        return Type;
    }

    public void setType(BytesInfo type) {
        Type = type;
    }

    public BytesInfo getCount() {
        return Count;
    }

    public void setCount(BytesInfo count) {
        Count = count;
    }

    public List<VarSegVarBean> getVars() {
        return vars;
    }

    public void setVars(List<VarSegVarBean> vars) {
        this.vars = vars;
    }
    public List<BytesInfo> getAllContent() {
        allContent.add(this.Type);
        allContent.add(this.Count);
        for (VarSegVarBean var : this.vars) {
            allContent.addAll(var.getAllContent());
        }
        return allContent;
    }
    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(String varType, List<CodeInfoBean> codeInfos){
        this.Type.setValue(varType);
        this.Count.setValue(StringUtil.decToHex(String.valueOf(codeInfos.size()), 8));
        for (CodeInfoBean codeInfo : codeInfos) {
            boolean ifPublicCode = false;
            LinkedHashMap<String, List<LinkedHashMap<String, CodeInfoBean>>> publicCode = CAAData.publicCode;
            for (List<LinkedHashMap<String, CodeInfoBean>> value : publicCode.values()) {
                for (LinkedHashMap<String, CodeInfoBean> stringCodeInfoBeanLinkedHashMap : value) {
                    CodeInfoBean codeInfoBean = (CodeInfoBean)stringCodeInfoBeanLinkedHashMap.values().toArray()[0];
                    if (codeInfoBean.getCodeName().equals(codeInfo.getCodeName())) {
                        ifPublicCode = true;
                    }
                }
            }
            if (!ifPublicCode || GenerateADSExcel.currentStationName.equals("MAIN")) {
                VarSegVarBean varSegVarBean = new VarSegVarBean();
                varSegVarBean.setAllContent(codeInfo);
                if (codeInfo.getCodeName().equals("RESERVE") && !GenerateADSExcel.reserveHasExist) {
                    vars.add(varSegVarBean);
                    GenerateADSExcel.reserveHasExist = true;
                } else if(!codeInfo.getCodeName().equals("RESERVE")){
                    vars.add(varSegVarBean);
                }
            }
            this.Count.setValue(StringUtil.decToHex(String.valueOf(vars.size()), 8));

        }
    }
    public Integer getLength(){
        return this.allContent.size() * 4;
    }
}
