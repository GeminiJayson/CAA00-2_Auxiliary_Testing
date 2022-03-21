package vip.jayson.interfaceFile;

import vip.jayson.config.SectionEnum;
import vip.jayson.pojo.dataBean.QcAlarmBean;
import vip.jayson.util.FileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/** @author GeminiJayson */
public class QcAlarm {
  /**
   * 读取QcAlarm文件
   *
   * @param filePath
   * @return
   */
  public static QcAlarmBean readQcAlarm(String filePath) {
    QcAlarmBean qcAlarmBean = new QcAlarmBean();
    File file = new File(filePath);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String str = null;
      String currentSection = SectionEnum.NEUTRAL.name();
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
          if (!currentSection.equals(SectionEnum.NEUTRAL.name())) {
            if ("QCBJ".equals(currentSection)) {
              qcAlarmBean.setQcbjSection(currentProperties);
            } else if (currentSection.contains("QCDI")) {
              qcAlarmBean.getQcdi().put(currentSection, currentProperties);
            } else if (currentSection.contains("QHCJ")) {
              qcAlarmBean.getQhcj().put(currentSection, currentProperties);
            } else if (currentSection.contains("DCCJ")) {
              qcAlarmBean.getDccj().put(currentSection, currentProperties);
            }
          }

          currentProperties = new LinkedHashMap<>();
          currentSection = newSection;

        } else {
          String[] sectionInfoArr = str.split("=");
          String sectionInfoKey = sectionInfoArr[0].trim();
          String sectionInfoValue = sectionInfoArr[1].trim();
          if ("QCDINUM".equals(sectionInfoKey)) {
            qcAlarmBean.setQcdiNum(Integer.valueOf(sectionInfoValue));
          } else if ("QHCJNUM".equals(sectionInfoKey)) {
            qcAlarmBean.setQhcjNum(Integer.valueOf(sectionInfoValue));
          } else if ("DCCJNUM".equals(sectionInfoKey)) {
            qcAlarmBean.setDccjNum(Integer.valueOf(sectionInfoValue));
          } else {
            currentProperties.put(sectionInfoKey, sectionInfoValue);
          }
        }
      }
      if (currentSection.contains("QCDI")) {
        qcAlarmBean.getQcdi().put(currentSection, currentProperties);
      } else if (currentSection.contains("QHCJ")) {
        qcAlarmBean.getQhcj().put(currentSection, currentProperties);
      } else if (currentSection.contains("DCCJ")) {
        qcAlarmBean.getDccj().put(currentSection, currentProperties);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return qcAlarmBean;
  }
}
