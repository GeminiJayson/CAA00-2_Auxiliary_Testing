package vip.jayson.interfaceFile;

import vip.jayson.config.SectionEnum;
import vip.jayson.pojo.dataBean.ConfigBean;
import vip.jayson.util.FileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 读取Config.TAB信息
 * @author GeminiJayson
 * */
public class ConfigTab {
  /**
   * 读取MainConfigTAB文件
   *
   * @param filePath
   * @return
   */
  public static ConfigBean readConfigTAB(String filePath) {
    ConfigBean configBean = new ConfigBean();
    File file = new File(filePath);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String str = null;
      String currentSection = SectionEnum.GLOBAL.name();
      LinkedHashMap<String, String> currentProperties = new LinkedHashMap<>();
      while ((str = reader.readLine()) != null) {
        // 去掉尾部的注释、去掉首尾空格
        str = FileParser.removeComments(str);
        if (str == null) {
          continue;
        }
        // 是否一个新section开始了
        if (str.startsWith("[") && str.endsWith("]")) {
          String newSection = str.substring(1, str.length() - 1).trim();
          if (!currentSection.equals(newSection)) {
            if (currentSection.equals(SectionEnum.GLOBAL.name())) {
              configBean.setGlobal(currentProperties);
            } else if (currentSection.equals(SectionEnum.PUBLIC.name())) {
              configBean.setPublicSection(currentProperties);
            } else if (currentSection.contains(SectionEnum.STATION.name())) {
              configBean.getStation().add(currentProperties);
            }
            currentProperties = new LinkedHashMap<>();
            currentSection = newSection;
          }
        } else {
          String[] sectionInfoArr = str.split("=");
          String sectionInfoKey = sectionInfoArr[0].trim();
          String sectionInfoValue = sectionInfoArr[1].trim();
          currentProperties.put(sectionInfoKey, sectionInfoValue);
        }
      }
      configBean.getStation().add(currentProperties);
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
    return configBean;
  }
  /**
   * 读取StationConfigTAB文件
   *
   * @param filePath
   * @return
   */
  public static List<Map<String, String>> readStationConfig(String filePath) {
    List<Map<String, String>> result = new ArrayList<>();
    File file = new File(filePath);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String str = null;
      while ((str = reader.readLine()) != null) {
        // 去掉尾部的注释、去掉首尾空格
        str = FileParser.removeComments(str);
        if (str == null) {
          continue;
        }
        Map<String, String> properties = new HashMap<>();
        String[] infoArr = str.split("=");
        String key = infoArr[0].trim();
        String value = infoArr[1].trim();
        properties.put(key, value);
        result.add(properties);
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
