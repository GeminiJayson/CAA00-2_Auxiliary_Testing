package vip.jayson.interfaceFile;

import vip.jayson.util.FileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PID {
    /**
     * 读取PID文件
     * @param filePath
     */
    public static Map<String, Map<String, Integer>> readPID(String filePath){
        File file = new File(filePath);
        BufferedReader reader = null;
        Map<String, Map<String, Integer>> pidDic = new HashMap<>();
        try{
            String str = null;
            reader = new BufferedReader(new FileReader(file));
            while ((str = reader.readLine()) != null) {
                str = FileParser.removeComments(str);
                if (str == null) {
                    continue;
                }
                Map<String, Integer> filePIDInfo = new HashMap<>();
                String[] fileInfo = str.split("=");
                String key = fileInfo[0].trim();
                String value = fileInfo[1].trim();
                String[] pidInfo = value.split(",");
                filePIDInfo.put("Start", Integer.valueOf(pidInfo[0]));
                filePIDInfo.put("End", Integer.valueOf(pidInfo[1]));
                filePIDInfo.put("Step", Integer.valueOf(pidInfo[2]));
                pidDic.put(key, filePIDInfo);
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
        return pidDic;
    }

}
