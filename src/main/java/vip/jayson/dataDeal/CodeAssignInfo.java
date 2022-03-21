package vip.jayson.dataDeal;

import vip.jayson.config.CAAData;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class CodeAssignInfo {
    /**
     * 分配码位编号
     * @param codeType 码位类型
     * @param stationNum 车站编号
     * @param codeOrder 码位顺序
     * @return 十六进制的码位编号字符串
     */
    public static String assignNumber(String codeType, Integer stationNum, Integer codeOrder) {
        String aSection = StringUtil.hexStrToBinStr(codeType).substring(0, 8);
        String bSection = StringUtil.decToBinStr(stationNum, 6);
        String cSection = StringUtil.decToBinStr(codeOrder, 17);
        String dSection = "0";
        String numBinString = aSection + bSection + cSection + dSection;
        return StringUtil.binStrTohexStr(numBinString);
    }

    /**
     * 分配码组真假值
     * @param codeArrayIndex 码组下标 0开始
     * @return 真假值字典
     */
    public static Map<String, String> assignCode(Integer codeArrayIndex) {
        return CAAData.codeData.get(codeArrayIndex);
    }

    /**
     * 获取码位编号和码组真假值
     * @param codeName 码位名称
     * @param codeType 码位类型
     * @param stationNum 车站号
     * @param codeOrder 码位顺序
     * @param codeArrayIndex 码组下标
     * @return 码位对象
     */
    public static CodeInfoBean getNumAndCode(String codeName, String codeType, Integer stationNum, Integer codeOrder, Integer codeArrayIndex) {
        String codeNum = null;
        Map<String, String> codeTF = new HashMap<>();
        if (codeName.equals("DEFAULT")) {
            codeNum = "00000000";
            codeTF.put("TRUE", "00000000");
            codeTF.put("FALSE", "00000000");
        } else {
            codeNum = assignNumber(codeType, stationNum, codeOrder);
            codeTF = assignCode(codeArrayIndex);
        }
        return new CodeInfoBean(codeType, codeName, codeNum, codeArrayIndex + 1, codeTF.get("TRUE"), codeTF.get("FALSE"));
    }
}
