package vip.jayson.readData;

import vip.jayson.config.CAAData;
import vip.jayson.config.ConfigEnum;
import vip.jayson.config.SectionEnum;
import vip.jayson.interfaceFile.*;
import vip.jayson.main.RunTool;
import vip.jayson.pojo.dataBean.CodeBean;
import vip.jayson.pojo.dataBean.InterfaceFileBean;
import vip.jayson.util.StringUtil;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import vip.jayson.util.LogConsole;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class ReadInterfaceFileConfig {
    /**
     * 获取所有输入文件信息
     *
     * @param mainPath 主配置文件路径
     */
    public static void getAllInputFile(String mainPath, String mainConfigName, String codeDataName) {

        // 获取主配置文件信息
        CAAData.configBean = ConfigTab.readConfigTAB(mainPath + mainConfigName);
        //获取CAA200-2工具输出文件夹位置
        RunTool.outPut = CAAData.configBean.getGlobal().get(ConfigEnum.CAA_ADSDIR.name());
        // 是否MMI切机标值
        String MMISwitchInfo = CAAData.configBean.getPublicSection().get(ConfigEnum.MMI_ATS_NOT_SWITCH_FLAG.name());
        if (MMISwitchInfo == null || !MMISwitchInfo.equals("TRUE")) {
            CAAData.ifMmiSwitch = true;
        } else {
            CAAData.ifMmiSwitch = false;
        }
        String codeDataPath = CAAData.configBean.getGlobal().get(ConfigEnum.CAA_DEFDIR.name());
        // 获取码组源文件信息
        CAAData.codeData = CodeData.readCodeData(mainPath + codeDataPath + "\\" + codeDataName);
        String pidName = CAAData.configBean.getPublicSection().get(ConfigEnum.CAA_PID_FILE.name());
        // 获取PID配置信息
        CAAData.pid = PID.readPID(mainPath + pidName);
        String publicCodeName = CAAData.configBean.getPublicSection().get(ConfigEnum.CAA_SHARECODE_FILE.name());
        // 获取公共码位配置信息
        CAAData.publicCode = PublicCode.readPublicCode(mainPath + publicCodeName);
        // 单站接口文件集合
        CAAData.stationFileDic = new LinkedHashMap<>();
        // 单站驱采报警文件集合
        CAAData.qcAlarmFileDic = new LinkedHashMap<>();
        // 单站VTL文件集合
        CAAData.vtlFileDic = new LinkedHashMap<>();
        //获取接口输入文件信息
        readInterfaceFile(mainPath);
    }
    public static void readInterfaceFile(String mainPath){
        // 当前站站号
        String stationName = null;
        List<LinkedHashMap<String, String>> stationsInfo = CAAData.configBean.getStation();
        for (LinkedHashMap<String, String> stationInfo : stationsInfo) {
            String configPath = mainPath + StringUtil.trimChar(stationInfo.get(ConfigEnum.CAA_CONFIG.name()), '.');
            File file = new File(configPath);
            List<Map<String, String>> stationConfig = ConfigTab.readStationConfig(configPath);
            LogConsole.info("读取Station " + stationConfig.get(0).get("STATION") + "接口文件！");
            Map<String, TreeMap<String, InterfaceFileBean>> interfaceFileMap = new HashMap<>();
            for (Map<String, String> interfaceName : stationConfig) {
                for (String key : interfaceName.keySet()) {
                    LogConsole.info(key + "...");
                    String value = interfaceName.get(key);
                    if (key.equals(SectionEnum.STATION.name())) {
                        stationName = value;
                        CAAData.stationNumList.add(value);
                    } else if (key.equals(ConfigEnum.CAA_CFG_FILE.name())) {
                        CAAData.qcAlarmFileDic.put(stationName, QcAlarm.readQcAlarm(file.getParent() + "\\" + value));
                    } else if (key.equals(ConfigEnum.CAA_VTL_FILE.name())) {
                        if (CAAData.vtlFileDic.get(stationName) != null) {
                            CAAData.vtlFileDic.get(stationName).add(Vtl.readVtl(file.getParent() + "\\" + value));
                        } else {
                            List<Map<String, List<String>>> vtlList = new ArrayList<>();
                            vtlList.add(Vtl.readVtl(file.getParent() + "\\" + value));
                            CAAData.vtlFileDic.put(stationName, vtlList);
                        }
                    } else if(key.equals(ConfigEnum.CAA_SCM_SIGN_ID_FILE.name())){

                    } else {
                        InterfaceFileBean oneInterfaceFileBean = InterfaceFile.readInterfaceFile(file.getParent() + "\\" + value);
                        String index = oneInterfaceFileBean.getGlobal().get("INDEX").getValue();
                        String interfaceFileName = getInterfaceType(oneInterfaceFileBean);
                        if (key.equals(ConfigEnum.CAA_RSSP1_FILE.name())) {
                            interfaceFileName = setRSSP1(oneInterfaceFileBean);
                        } else if (key.equals(ConfigEnum.CAA_RSSP2_FILE.name())) {
                            interfaceFileName = setRSSP2(oneInterfaceFileBean);
                        } else if (key.equals(ConfigEnum.CAA_FSFB2_FILE.name())) {
                            interfaceFileName = setFSFB2(oneInterfaceFileBean);
                        } else if (key.equals(ConfigEnum.CAA_IPS200MMI_FILE.name())) {
                            interfaceFileName = setMMI(oneInterfaceFileBean);
                        } else if (key.equals(ConfigEnum.CAA_CIATS_FILE.name())) {
                            interfaceFileName = setMMI(oneInterfaceFileBean);
                        } else if (key.equals(ConfigEnum.CAA_OLC_FILE.name())) {
                            interfaceFileName = "OLC";
                        } else if (key.equals(ConfigEnum.CAA_SCM_FILE.name())) {
                            interfaceFileName = "CCSACEM";
                        }
                        if (interfaceFileMap.containsKey(interfaceFileName)) {
                            interfaceFileMap.get(interfaceFileName).put(index, oneInterfaceFileBean);
                        } else {
                            TreeMap<String, InterfaceFileBean> sortFileIndex = new TreeMap<>();
                            sortFileIndex.put(index, oneInterfaceFileBean);
                            interfaceFileMap.put(interfaceFileName, sortFileIndex);
                        }
                    }
                }
            }
            CAAData.stationFileDic.put(stationName, interfaceFileMap);
        }

    }

    private static String setRSSP1(InterfaceFileBean oneInterfaceFileBean){
        String interfaceFileName = getInterfaceType(oneInterfaceFileBean);
        if (interfaceFileName.equals("TCC")) {
            String protocol = oneInterfaceFileBean.getGlobal().get("PROTOCOL").getValue();
            if (protocol.equals("4")) {
                interfaceFileName = interfaceFileName + "4";
            }
        }
        if (interfaceFileName.equals("CBI")) {
            String protocol = oneInterfaceFileBean.getGlobal().get("PROTOCOL").getValue();
            if (protocol.equals("3")) {
                interfaceFileName = interfaceFileName + "3";
            }
        }
        if (interfaceFileName.equals("VOBC")) {
            interfaceFileName = "HL" + interfaceFileName;
        }
        return interfaceFileName;
    }
    private static String setRSSP2(InterfaceFileBean oneInterfaceFileBean){
        String interfaceFileName = getInterfaceType(oneInterfaceFileBean);
        if (interfaceFileName.equals("RBC")) {
            String protocol = oneInterfaceFileBean.getGlobal().get("PROTOCOL").getValue();
            if (protocol.equals("3") || protocol.equals("621.1-2018-PRO1")) {
                interfaceFileName = interfaceFileName + "1";
            }
            if (protocol.equals("1") || protocol.equals("621.1-2018-PRO2")) {
                interfaceFileName = interfaceFileName + "2";
            }
            if (protocol.equals("2") || protocol.equals("621.1-2018-PRO3")) {
                interfaceFileName = interfaceFileName + "3";
            }
            if (protocol.equals("4")) {
                interfaceFileName = interfaceFileName + "4";
            }
        }
        return interfaceFileName;
    }
    private static String setFSFB2(InterfaceFileBean oneInterfaceFileBean){
        String type = oneInterfaceFileBean.getGlobal().get("TYPE").getValue();
        if (type.equals("1")) {
            return "FSFB2";
        } else if (type.equals("2")) {
            return "CCFSFB2";
        }
        return null;
    }
    private static String setMMI(InterfaceFileBean oneInterfaceFileBean){
        String interfaceFileName = getInterfaceType(oneInterfaceFileBean);
        if (interfaceFileName.equals("MMI") || interfaceFileName.equals("ATS")) {
            return "MMI";
        }
        return null;
    }
    private static String getInterfaceType(InterfaceFileBean oneInterfaceFileBean){
        List<CodeBean> receive = oneInterfaceFileBean.getReceive();
        List<CodeBean> send = oneInterfaceFileBean.getSend();
        if (receive.size() > 0) {
            return receive.get(0).getCodeInfo().get("SOURCE").getValue();
        } else {
            return send.get(0).getCodeInfo().get("DESTINATION").getValue();
        }
    }
}
