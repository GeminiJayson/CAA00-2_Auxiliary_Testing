package vip.jayson.pojo.vtlArea;

import vip.jayson.config.CAAData;
import vip.jayson.main.GenerateADSExcel;
import vip.jayson.pojo.dataBean.CodeAssignBean;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.pojo.ioArea.HLVOBCCodeBean;
import vip.jayson.pojo.varArea.VarSegVarBean;
import vip.jayson.util.ObjectUtil;
import vip.jayson.util.StringUtil;

import java.util.*;

public class VtlSegBean {
    private BytesInfo Size = new BytesInfo("UNIT", 4, "Size");
    private List<VtlSegExpressionInfoBean> expInfo = new ArrayList<>();
    private LinkedHashMap<String, VtlSegExpressionStrucBean> expStruc = new LinkedHashMap<>();
    public static String[] order = {"Size","expInfo","expStruc"};
    private List<BytesInfo> allContent = new ArrayList<>();

    public List<BytesInfo> getAllContent() {
        allContent.add(this.Size);
        for (VtlSegExpressionInfoBean vtlSegExpressionInfoBean : this.expInfo) {
            allContent.addAll(vtlSegExpressionInfoBean.getAllContent());
        }
        for (VtlSegExpressionStrucBean vtlSegExpressionStrucBean : expStruc.values()) {
            allContent.addAll(vtlSegExpressionStrucBean.getAllContent());
        }
        return allContent;
    }

    public void setAllContent(List<BytesInfo> allContent) {
        this.allContent = allContent;
    }
    public void setAll(List<String> boolExps) {
        Map<String, String> delayDic = new HashMap<>();
        LinkedHashMap<String, String> expressionDic = new LinkedHashMap<>();
        for (int i = 0; i < boolExps.size(); i++) {
            String expression = boolExps.get(i);
            String[] split = expression.split("=");
            String left = split[0].trim();
            String right = split[1].trim();
            String[] leftItem = left.split("( )+");
            if (leftItem[0].equals("TIME")) {
                String delayTime = right.split("( )+")[0];
                String expressionNext = boolExps.get(i + 1);
                String[] splitNext = expressionNext.split("=");
                String leftNext = splitNext[0].trim();
                String[] leftItemNext = leftNext.split("( )+");
                delayDic.put(leftItemNext[1], StringUtil.decToHex(String.valueOf(Integer.parseInt(delayTime)*1000), 8));
            } else if (leftItem[0].equals("BOOL")) {
                expressionDic.put(leftItem[1], right);
            }
        }
        this.Size.setValue(StringUtil.decToHex(String.valueOf(expressionDic.size()), 8));
        for (String varKey : expressionDic.keySet()) {
            String expressContent = expressionDic.get(varKey).split("\\(")[1].trim().split("\\)")[0].trim();
            String[] items = expressContent.split("\\+");
            VtlSegExpressionStrucBean vtlSegExpressionStrucBean = new VtlSegExpressionStrucBean();
            vtlSegExpressionStrucBean.setAll(items);
            this.expStruc.put(varKey, vtlSegExpressionStrucBean);
        }
        int fixOffset = 4 + expressionDic.size() * 3 * 4;
        for (String varKey : expressionDic.keySet()) {
            VtlSegExpressionInfoBean vtlSegExpressionInfoBean = new VtlSegExpressionInfoBean();
            if (delayDic.containsKey(varKey)) {
                vtlSegExpressionInfoBean.getType().setValue(delayDic.get(varKey));
            }
            vtlSegExpressionInfoBean.getItem().setValue(GenerateADSExcel.currentStationCodeInfo.get(varKey).getCodeNum());
            vtlSegExpressionInfoBean.getItem().setComment(varKey);
            int currentVarOffset = 0;
            for (String exeVarKey : this.expStruc.keySet()) {
                if (exeVarKey.equals(varKey)) {
                    break;
                } else {

                    currentVarOffset += this.expStruc.get(exeVarKey).getAllContent().size() * 4;
                }
            }
            vtlSegExpressionInfoBean.getOffset().setValue(StringUtil.decToHex(String.valueOf(fixOffset + currentVarOffset), 8));
            this.expInfo.add(vtlSegExpressionInfoBean);
        }

    }



    public Integer getLength() {
        return this.allContent.size() * 4;
    }
    public BytesInfo getSize() {
        return this.Size;
    }

    public void setSize(BytesInfo size) {
        this.Size = size;
    }



    public List<VtlSegExpressionInfoBean> getExpInfo() {
        return this.expInfo;
    }

    public void setExpInfo(List<VtlSegExpressionInfoBean> expInfo) {
        this.expInfo = expInfo;
    }

    public LinkedHashMap<String, VtlSegExpressionStrucBean> getExpStruc() {
        return this.expStruc;
    }

    public void setExpStruc(LinkedHashMap<String, VtlSegExpressionStrucBean> expStruc) {
        this.expStruc = expStruc;
    }
}
