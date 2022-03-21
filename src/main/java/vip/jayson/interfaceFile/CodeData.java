package vip.jayson.interfaceFile;

import vip.jayson.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 码组数据读取
 * @author GeminiJayson
 */
public class CodeData {
    public static List<Map<String, String>> readCodeData(String filePath) {
        List<Map<String, String>> result = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = reader.readLine()) != null) {
                // 去掉尾部的注释、去掉首尾空格
                String[] resultList = str.trim().split("\t");
                Map<String, String> code = new HashMap<>();
                code.put("TRUE", StringUtil.addFrontZore(resultList[1].trim(), 8));
                code.put("FALSE", StringUtil.addFrontZore(resultList[2].trim(), 8));
                result.add(code);
                code = new HashMap<>();
                code.put("TRUE", StringUtil.addFrontZore(resultList[3].trim(), 8));
                code.put("FALSE", StringUtil.addFrontZore(resultList[4].trim(), 8));
                result.add(code);
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
        return result;

    }


}
