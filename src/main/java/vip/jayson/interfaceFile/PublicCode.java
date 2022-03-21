package vip.jayson.interfaceFile;

import vip.jayson.config.SectionEnum;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.util.FileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PublicCode {
  /**
   * 读取PublicCode文件
   *
   * @param filePath
   */
  public static LinkedHashMap<String, List<LinkedHashMap<String, CodeInfoBean>>> readPublicCode(
      String filePath) {
    File file = new File(filePath);
    BufferedReader reader = null;
    LinkedHashMap<String, List<LinkedHashMap<String, CodeInfoBean>>> publicCodeDic =
        new LinkedHashMap<>();
    try {
      String str = null;
      reader = new BufferedReader(new FileReader(file));
      String currentSection = SectionEnum.GLOBAL.name();
      List<LinkedHashMap<String, CodeInfoBean>> currentProperties = new ArrayList<>();
      while ((str = reader.readLine()) != null) {
        str = FileParser.removeComments(str);
        if (str == null) {
          continue;
        }
        // 是否一个新section开始了
        if (str.startsWith("[") && str.endsWith("]")) {
          String newSection = str.substring(1, str.length() - 1).trim();
          if (!currentSection.equals(newSection)) {
            publicCodeDic.put(currentSection, currentProperties);
            currentProperties = new ArrayList<>();
            currentSection = newSection;
          }
        } else {
          LinkedHashMap<String, CodeInfoBean> codeInfo = new LinkedHashMap<>();
          String[] stationCodeArr = str.split("=");
          for (String stationInfo : stationCodeArr) {
            String[] stationCodeInfo = stationInfo.split(",");
            String stationName = stationCodeInfo[0].trim();
            String stationCode = stationCodeInfo[1].trim();
            codeInfo.put(stationName, new CodeInfoBean(stationCode));
          }
          currentProperties.add(codeInfo);
        }
      }
      publicCodeDic.put(currentSection, currentProperties);
      publicCodeDic.remove(SectionEnum.GLOBAL.name());
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
    return publicCodeDic;
  }
}
