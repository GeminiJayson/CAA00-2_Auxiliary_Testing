package vip.jayson.main;

import vip.jayson.config.CAAData;
import vip.jayson.dataDeal.FileCodeAssign;
import vip.jayson.gui.main.ATT;
import vip.jayson.pojo.dataBean.CompareInfoBean;
import vip.jayson.pojo.dataBean.XMLBean;
import vip.jayson.readData.ReadBinInfo;
import vip.jayson.readData.ReadInterfaceFileConfig;
import vip.jayson.readData.ReadLKInfo;
import vip.jayson.util.ExcelParser;
import vip.jayson.util.FileParser;
import vip.jayson.util.LogConsole;
import vip.jayson.util.XmlParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunTool {

    public static String outPut;
    public static String projectRootPath;
    /**对比表格路径含名称*/
    public static String caaExcelPath;
    public static String testCase;
    /**二进制对比 Excel文件路径*/
    public static String excelPath;
    public static String codeDataName;
    public static String mainConfigName;
    public static String[] blockOrder;
    public static String[] singleStationVarOrder;
    public static String[] assignNumberOrderArray;
    public static String[] assignCodeOrderArray;
    /**LK输入变量路径*/
    private static String lkConfigName;
    /**LK输入变量路径*/
    private static String lkInputPath;
    /**LK输出路径*/
    private static String lkOutputPath;
    /**二进制对比 二进制文件路径*/
    private static String binPath;
    /**二进制对比 二进制文件和Excel表sheet表名映射*/
    private static Map<String, String> compareMapping = new HashMap<>();

    public static void readConfig() {
        projectRootPath = System.getProperty("user.dir");
        XmlParser.readXML();
        Map<String, XMLBean> allElementDic = XmlParser.allElementDic;
        Map<String, XMLBean> configuration = allElementDic.get("Configuration").getChildNode();
        for (String childNodeName : configuration.keySet()) {
            XMLBean childNode = configuration.get(childNodeName);
            if (childNodeName.equals("ATTConfig")) {
                Map<String, String> attrs = childNode.getAttrs();
                testCase = attrs.get("testPath");
                caaExcelPath = attrs.get("compareFile");
            } else if (childNodeName.equals("TestConfig")) {
                Map<String, String> attrs = childNode.getAttrs();
                mainConfigName = attrs.get("configName");
                codeDataName = attrs.get("codeDataName");
                if (childNode.getChildNode().containsKey("LKConfig")) {
                    Map<String, String> lkConfigAttrs = childNode.getChildNode().get("LKConfig").getAttrs();
                    if (lkConfigAttrs.get("ifGenerate").equals("TRUE")) {
                        CAAData.ifGenerateLK = true;
                    }
                    lkConfigName = lkConfigAttrs.get("configName");
                    lkInputPath = lkConfigAttrs.get("inputPath");
                    lkOutputPath = lkConfigAttrs.get("outputPath");
                } else {
                    CAAData.ifGenerateLK = false;
                }
                if (childNode.getChildNode().containsKey("TISConfig")) {
                    Map<String, String> tisConfigAttrs = childNode.getChildNode().get("TISConfig").getAttrs();
                    if (tisConfigAttrs.get("ifGenerate").equals("TRUE")) {
                        CAAData.ifGenerateTIS = true;
                    }
                } else {
                    CAAData.ifGenerateTIS = false;
                }
            } else if (childNodeName.equals("CAAConfig")) {
                blockOrder = childNode.getChildNode().get("IOAreaBlockOrder").getText().split(",");
                singleStationVarOrder = childNode.getChildNode().get("SingleStationVarAreaSegOrder").getText().split(",");
                assignNumberOrderArray = childNode.getChildNode().get("AssignNumberOrder").getText().split(",");
                assignCodeOrderArray = childNode.getChildNode().get("AssignCodeOrder").getText().split(",");
            } else if (childNodeName.equals("CompareBIN")) {
                Map<String, String> attrs = childNode.getAttrs();
                Map<String, XMLBean> childNode1 = childNode.getChildNode();
                excelPath = attrs.get("excelPath");
                binPath = attrs.get("binFilePath");
                for (String mapKey : childNode1.keySet()) {
                    XMLBean mapBean = childNode1.get(mapKey);
                    Map<String, XMLBean> mapBeanChild = mapBean.getChildNode();
                    XMLBean bin = mapBeanChild.get("Bin");
                    XMLBean excel = mapBeanChild.get("Excel");
                    compareMapping.put(bin.getText(), excel.getText());
                }
            }
        }
    }




    public static void lctRunCAA() {
        CAAData.ifGenerateLK = true;
        String startCmd = "cmd /c start " +
                RunTool.projectRootPath + "\\" + RunTool.testCase + "\\LCT_CAA.exe " +
                RunTool.projectRootPath + "\\" + RunTool.testCase + " " +
                RunTool.lkConfigName + " " +
                RunTool.lkInputPath + " " +
                RunTool.lkOutputPath +
                " 1";
        try {
            Runtime.getRuntime().exec(startCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void generateHelpInfo() {
        if (CAAData.ifGenerateLK) {
            LogConsole.info("读取列控区数据：变量信息");
            CAAData.lkInputVarsInfo = ReadLKInfo.readLKVars(RunTool.projectRootPath + "\\" + RunTool.testCase + "\\" + RunTool.lkInputPath + "\\Vars.txt");
            for (int i = 0; i < CAAData.stationNumList.size(); i++) {
                String stationName = CAAData.stationNumList.get(i);
                String zoneInfoName = null;
                if ("MAIN".equals(stationName)) {
                    zoneInfoName = "0.dat";
                } else {
                    zoneInfoName = stationName + ".dat";
                }
                LogConsole.info("读取列控区数据：" + stationName + "列控区二进制数据");
                CAAData.lkZoneInfoDIc.put(stationName, ReadBinInfo.readBinInfo(RunTool.projectRootPath + "\\" + RunTool.lkInputPath + "\\" + zoneInfoName));
                LogConsole.info("读取列控区数据：" + stationName + "含列控数据的CAA200-2工具输出");
                CAAData.lkCAAADSContentDic.put(stationName, ReadBinInfo.readBinInfo(RunTool.projectRootPath + "\\" + RunTool.testCase + "\\" + RunTool.lkOutputPath + "\\" + "CAA200-2Out\\" + stationName + ".ADS"));
            }
        }
        //为每个站变量分配编号
        for (int i = 1; i < CAAData.stationNumList.size(); i++) {
            FileCodeAssign.assignNumberAndCode(assignNumberOrderArray, i);
        }

    }

    public static void compareCAA() {
        for (int i = 0; i < CAAData.stationNumList.size(); i++) {
            String stationName = CAAData.stationNumList.get(i);
            LogConsole.info("对比Station " + stationName + "数据......");
            CAAData.compareInfoDic.put(stationName, ExcelParser.readCAA200ExcelInfo(stationName));
            int num = 0;
            for (CompareInfoBean compareInfoBean : CAAData.compareInfoDic.get(stationName)) {
                num++;
                //ATT.progressBar1.setValue(LogConsole.getPercent(num, CAAData.compareInfoDic.get(stationName).size()));
                CAAData.caaADSContentDic.put(stationName, ReadBinInfo.readBinInfo(RunTool.projectRootPath + "\\" + RunTool.testCase + "\\" + RunTool.outPut + "\\" + "CAA200-2Out\\" + stationName + ".ADS"));
                String specifiedLengthBin = ReadBinInfo.getSpecifiedLengthBin(CAAData.caaADSContentDic.get(stationName), compareInfoBean.getStart(), compareInfoBean.getLength());
                if (specifiedLengthBin.equals(compareInfoBean.getValue())) {
                    LogConsole.info("Pass:" + compareInfoBean);
                } else {
                    LogConsole.error("Fail:" + compareInfoBean);
                }
            }
            if (CAAData.ifGenerateLK) {
                CAAData.compareInfoLKDic.put(stationName + " LK", ExcelParser.readCAA200ExcelInfo(stationName + " LK"));
            }
        }
    }

    public static void compareBIN() {
        List<String> traverse = FileParser.traverse(new File(RunTool.projectRootPath + "\\" + RunTool.binPath));
        Map<String, List<String>> binResult = new HashMap<>();
        Map<String, List<CompareInfoBean>> excelInfo = new HashMap<>();
        for (String filepath : traverse) {
            String fileName = new File(filepath).getName();
            if (RunTool.compareMapping.containsKey(fileName)) {
                List<String> binInfo = ReadBinInfo.readBinInfo(filepath);
                binResult.put(fileName, binInfo);
                excelInfo.put(fileName, ExcelParser.readBINExcelInfo(RunTool.compareMapping.get(fileName)));
            }
        }
        for (String binFileName : RunTool.compareMapping.keySet()) {
            LogConsole.info("对比 " + binFileName + " 文件数据......");
            int num = 0;
            for (CompareInfoBean compareInfoBean : excelInfo.get(binFileName)) {
                num ++;
                //ATT.progressBar1.setValue(LogConsole.getPercent(num, excelInfo.get(binFileName).size()));
                String specifiedLengthBin = ReadBinInfo.getSpecifiedLengthBin(binResult.get(binFileName), compareInfoBean.getStart(), compareInfoBean.getLength());
                if (specifiedLengthBin.equals(compareInfoBean.getValue())) {
                    LogConsole.info("Pass:" + compareInfoBean);
                } else {
                    LogConsole.error("Fail:" + compareInfoBean + "，工具生成值为：" + specifiedLengthBin);
                }
            }
        }
    }
    public static void main(String[] args) {
        LogConsole.info("-------CAA200-2 辅助测试工具--------");
        readConfig();
        String mainPath = RunTool.projectRootPath + "\\" + RunTool.testCase + "\\";
        CAAData.stationNumList.add("MAIN");
        ReadInterfaceFileConfig.getAllInputFile(mainPath, RunTool.mainConfigName, RunTool.codeDataName);
        generateHelpInfo();
        ExcelParser.writeHelpInfo();
        GenerateADSExcel.generateADSExcel(CAAData.stationNumList);
        compareBIN();
    }

}
