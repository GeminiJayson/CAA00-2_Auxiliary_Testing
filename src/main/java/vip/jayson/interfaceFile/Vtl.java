package vip.jayson.interfaceFile;

import vip.jayson.config.SectionEnum;
import vip.jayson.util.FileParser;
import vip.jayson.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Vtl {
    /**
     * 读取VTL文件
     *
     * @param filePath
     * @return
     */
    public static Map<String, List<String>> readVtl(String filePath) {
        Map<String, List<String>> vtlInfo = new HashMap<>();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            String currentSection = SectionEnum.NEUTRAL.name();
            List<String> currentProperties = new ArrayList<>();
            while ((str = reader.readLine()) != null) {
                // 去掉尾部的注释、去掉首尾空格
                str = FileParser.removeComments(str);
                if (str == null) {
                    continue;
                }
                // 是否一个新section开始了
                if (str.startsWith("-") && str.endsWith("-")) {
                    String newSection = StringUtil.trimChar(str,'-');
                    if (newSection.contains("END")){
                        continue;
                    }
                    if (!currentSection.equals(newSection)) {
                        switch (currentSection) {
                            //一般变量
                            case "CURRENT RESULT SECTION":
                                vtlInfo.put("NORMAL", currentProperties);
                                break;
                            //时间变量
                            case "TIMER EXPRESSION RESULT SECTION":
                                vtlInfo.put("TIME", currentProperties);
                                break;
                            //自保变量
                            case "SELF-LATCHED PARAMETER SECTION":
                                vtlInfo.put("LOCK", currentProperties);
                                break;
                            //布尔表达式
                            case "BOOLEAN EQUATION SECTION":
                                vtlInfo.put("BOOLEXP", currentProperties);
                                break;
                            default:
                                break;
                        }
                        currentProperties = new ArrayList<>();
                        currentSection = newSection;
                    }
                } else {
                    currentProperties.add(str);
                }
            }
            switch (currentSection) {
                case "CURRENT RESULT SECTION":
                    vtlInfo.put("NORMAL", currentProperties);
                    break;
                case "TIMER EXPRESSION RESULT SECTION":
                    vtlInfo.put("TIME", currentProperties);
                    break;
                case "SELF-LATCHED PARAMETER SECTION":
                    vtlInfo.put("LOCK", currentProperties);
                    break;
                case "BOOLEAN EQUATION SECTION":
                    vtlInfo.put("BOOLEXP", currentProperties);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return vtlInfo;
    }
}
