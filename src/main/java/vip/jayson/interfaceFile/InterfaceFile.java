package vip.jayson.interfaceFile;

import vip.jayson.config.CAAData;
import vip.jayson.config.KeyNameEnum;
import vip.jayson.config.SectionEnum;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.util.FileParser;
import vip.jayson.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author GeminiJayson
 */
public class InterfaceFile {
    /**
     * 读取所有接口文件
     *
     * @param filePath
     */
    public static InterfaceFileBean readInterfaceFile(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        InterfaceFileBean interfaceFileBean = new InterfaceFileBean();
        CodeBean codeBean = new CodeBean();
        try {
            String str = null;
            reader = new BufferedReader(new FileReader(file));
            String currentSection = SectionEnum.GLOBAL.name();
            LinkedHashMap<String, CodeInfoBean> lineInfo = new LinkedHashMap<>();
            //初始码位类型为中性码位-只做标记使用，不参与数据
            String codeType = SectionEnum.NEUTRAL.name();
            while ((str = reader.readLine()) != null) {
                //注释信息不会返回null
                str = FileParser.removeComments(str);
                if (str == null) {
                    continue;
                }
                //是否为发送码位
                if (str.contains(KeyNameEnum.SOURCE.name()) && str.contains("IPS-200")) {
                    //前一个数据块是否为GLOBAL
                    if (currentSection.equals(SectionEnum.GLOBAL.name())) {
                        interfaceFileBean.setGlobal(lineInfo);
                    }
                    //当前码位类型为接收码位，并且当前段为总览信息，说明接收码位无块码位
                    if (codeType.equals(SectionEnum.RECEIVE.name())
                            && currentSection.equals(SectionEnum.OVERVIEW.name())) {
                        codeBean.setBlockName(SectionEnum.NEUTRAL_CODE.name());
                        codeBean.setCodeInfo(lineInfo);
                        interfaceFileBean.getReceive().add(codeBean);
                    //当前块不为全局
                    } else if (!currentSection.equals(SectionEnum.GLOBAL.name())) {
                        codeBean.setBlockName(currentSection);
                        codeBean.setCodeInfo(lineInfo);
                        interfaceFileBean.getReceive().add(codeBean);
                    }
                    codeType = SectionEnum.SEND.name();
                    currentSection = SectionEnum.OVERVIEW.name();
                    lineInfo = new LinkedHashMap<>();
                    codeBean = new CodeBean();
                //是否为接收码位
                } else if (str.contains(KeyNameEnum.SOURCE.name()) && !str.contains("IPS-200")) {
                    //前一个数据块是否为GLOBAL
                    if (currentSection.equals(SectionEnum.GLOBAL.name())) {
                        interfaceFileBean.setGlobal(lineInfo);
                    }
                    //当前码位类型为发送码位，并且当前段为总览信息，说明发送码位无块码位
                    if (codeType.equals(SectionEnum.SEND.name())
                            && currentSection.equals(SectionEnum.OVERVIEW.name())) {
                        codeBean.setBlockName(SectionEnum.NEUTRAL_CODE.name());
                        codeBean.setCodeInfo(lineInfo);
                        interfaceFileBean.getSend().add(codeBean);
                    //当前块不为全局
                    } else if (!currentSection.equals(SectionEnum.GLOBAL.name())) {
                        codeBean.setBlockName(currentSection);
                        codeBean.setCodeInfo(lineInfo);
                        interfaceFileBean.getSend().add(codeBean);
                    }
                    currentSection = SectionEnum.OVERVIEW.name();
                    codeType = SectionEnum.RECEIVE.name();
                    lineInfo = new LinkedHashMap<>();
                    codeBean = new CodeBean();
                //码位块
                } else if (str.startsWith("[") && str.endsWith("]")) {
                    String newSection = str.substring(1, str.length() - 1).trim();
                    codeBean.setBlockName(currentSection);
                    codeBean.setCodeInfo(lineInfo);
                    if (!currentSection.equals(SectionEnum.OVERVIEW.name())) {
                        if (codeType.equals(SectionEnum.RECEIVE.name())) {
                            interfaceFileBean.getReceive().add(codeBean);
                        } else if (codeType.equals(SectionEnum.SEND.name())) {
                            interfaceFileBean.getSend().add(codeBean);
                        }
                    }
                    currentSection = newSection;
                    codeBean = new CodeBean();
                    lineInfo = new LinkedHashMap<>();
                }
                String[] info = str.split("=");
                String key = null;
                String value = null;
                //当前 行 为数据信息
                if (info.length == 2) {
                    key = info[0].trim();
                    value = info[1].trim();
                    //版本和协议预处理
                    if (value.contains(".")) {
                        if (value.contains("V")) {
                            value = value.split("V")[1];
                        }
                        if (value.contains("v")) {
                            value = value.split("v")[1];
                        }
                        if (value.contains("1-2018-PRO1")) {
                            value = "3";
                        } else if (value.contains("1-2018-PRO2")) {
                            value = "1";
                        } else if (value.contains("1-2018-PRO3")) {
                            value = "2";
                        } else {
                            String[] split = value.split("\\.");
                            StringBuilder hex = new StringBuilder();
                            for (String s : split) {
                                hex.append(StringUtil.decToHex(s, 2));
                            }

                            value = StringUtil.hexToDec(hex.toString());
                        }
                    }
                    //十六进制处理
                    if (value.contains("0x")) {
                        value = StringUtil.hexToDec(value.split("0x")[1]);
                    }
                    //时间处理S
                    if (Pattern.matches("[0-9].*S", value)) {
                        value = value.split("S")[0];
                    }
                    if (Pattern.matches("[0-9].*s", value)) {
                        value = value.split("s")[0];
                    }
                    if (Pattern.matches("[0-9].*ms", value)) {
                        value = value.split("ms")[0];
                    }
                    if (Pattern.matches("[0-9].*MS", value)) {
                        value = value.split("MS")[0];
                    }
                    //PROTOCOL_TYPE预处理
                    if (value.contains("RSSP-I")) {
                        value = "1";
                    }
                    if (value.contains("RSSP-II")) {
                        value = "2";
                    }
                    //MMI or ATS 安全控制码位保存
                    if (value.contains(",")) {
                        if (!key.equals("PID") && !key.equals("REMOTE_ID") && !key.equals("BZXX")) {
                            String[] split = value.split(",");
                            List<String> strings = new ArrayList<>();
                            for (int i = 0; i < split.length; i++) {
                                if (i != 0) {
                                    strings.add(split[i].trim());
                                }
                            }
                            CAAData.mmiSafeCommand.put(split[0], strings);
                            value = split[0].trim();
                        }

                    }
                    lineInfo.put(key, new CodeInfoBean(value));
                }

                //储存信息为码位总览信息
                if (lineInfo.size() == 3
                        && lineInfo.containsKey(KeyNameEnum.SOURCE.name())
                        && lineInfo.containsKey(KeyNameEnum.DESTINATION.name())
                        && lineInfo.containsKey(KeyNameEnum.MESSAGE.name())) {
                    codeBean.setBlockName(SectionEnum.OVERVIEW.name());
                    lineInfo.put(
                            KeyNameEnum.MESSAGE.name(),
                            new CodeInfoBean(lineInfo.get(KeyNameEnum.MESSAGE.name()).getValue()
                                    .split("LENGTH")[1]
                                    .split("\\(")[1]
                                    .split("\\)")[0]));
                    codeBean.setCodeInfo(lineInfo);
                    if (codeType.equals(SectionEnum.RECEIVE.name())) {
                        interfaceFileBean.getReceive().add(codeBean);
                    } else if (codeType.equals(SectionEnum.SEND.name())) {
                        interfaceFileBean.getSend().add(codeBean);
                    }
                    codeBean = new CodeBean();
                    lineInfo = new LinkedHashMap<>();
                }
                //当前 行 的信息为块标志信息
                if (key != null
                        && !FileParser.isInteger(key)
                        && !currentSection.equals(SectionEnum.OVERVIEW.name())
                        && !currentSection.equals(SectionEnum.GLOBAL.name())) {
                    lineInfo.remove(key);
                    if (lineInfo.size() > 0) {
                        codeBean.setBlockName(currentSection);
                        codeBean.setCodeInfo(lineInfo);
                        if (codeType.equals(SectionEnum.RECEIVE.name())) {
                            interfaceFileBean.getReceive().add(codeBean);
                        } else if (codeType.equals(SectionEnum.SEND.name())) {
                            interfaceFileBean.getSend().add(codeBean);
                        }
                        codeBean = new CodeBean();
                        lineInfo = new LinkedHashMap<>();
                    }
                    if (FileParser.isInteger(value)) {
                        codeBean.getFlagInfo().put(key, Integer.valueOf(value));
                    }


                }
            }
            //将最后读取信息存入
            if (lineInfo.size() > 0) {
                if (codeType.equals(SectionEnum.SEND.name())
                        && currentSection.equals(SectionEnum.OVERVIEW.name())) {
                    codeBean.setBlockName(SectionEnum.NEUTRAL_CODE.name());
                    codeBean.setCodeInfo(lineInfo);
                    interfaceFileBean.getSend().add(codeBean);
                } else if (codeType.equals(SectionEnum.RECEIVE.name())
                        && currentSection.equals(SectionEnum.OVERVIEW.name())) {
                    codeBean.setBlockName(SectionEnum.NEUTRAL_CODE.name());
                    codeBean.setCodeInfo(lineInfo);
                    interfaceFileBean.getSend().add(codeBean);
                } else if (codeType.equals(SectionEnum.SEND.name())) {
                    codeBean.setBlockName(currentSection);
                    codeBean.setCodeInfo(lineInfo);
                    interfaceFileBean.getSend().add(codeBean);
                } else if (codeType.equals(SectionEnum.RECEIVE.name())) {
                    codeBean.setBlockName(currentSection);
                    codeBean.setCodeInfo(lineInfo);
                    interfaceFileBean.getReceive().add(codeBean);
                }
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
        return interfaceFileBean;
    }


}
