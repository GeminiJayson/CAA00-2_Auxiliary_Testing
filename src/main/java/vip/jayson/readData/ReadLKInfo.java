package vip.jayson.readData;

import com.sun.jna.Library;
import com.sun.jna.Native;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.util.StringUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadLKInfo {
    public static Map<String, List<CodeInfoBean>> readLKVars(String filePath) {
        Map<String, List<CodeInfoBean>> result = new HashMap<>();
        List<CodeInfoBean> codeInfoList = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = reader.readLine()) != null) {
                String line = str.trim();
                if (line.equals("")) {
                    continue;
                }
                String[] lineInfo = line.split(" ");
                String stationName = lineInfo[0];
                String code = lineInfo[1];
                CodeInfoBean codeInfoBean = new CodeInfoBean();
                codeInfoBean.setCodeName(code);
                codeInfoBean.setValue(code);
                codeInfoBean.setCodeType("70000000");
                if (result.containsKey(stationName)) {
                    result.get(stationName).add(codeInfoBean);
                } else {
                    codeInfoList = new ArrayList<>();
                    codeInfoList.add(codeInfoBean);
                    result.put(stationName, codeInfoList);
                }
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
