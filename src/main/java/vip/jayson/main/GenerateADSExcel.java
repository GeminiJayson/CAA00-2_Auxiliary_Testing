package vip.jayson.main;

import vip.jayson.config.CAAData;
import vip.jayson.config.FileAreaNodeConfig;
import vip.jayson.pojo.additionalFunctionArea.QCBJBlockBean;
import vip.jayson.pojo.additionalFunctionArea.SwitchInterfaceBlock;
import vip.jayson.pojo.additionalFunctionArea.SwitchSegBean;
import vip.jayson.pojo.dataBean.*;
import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.config.NodeConfigs;
import vip.jayson.pojo.fileBean.NodeBean;
import vip.jayson.pojo.fileBean.StructureHead;
import vip.jayson.pojo.ioArea.*;
import vip.jayson.pojo.tisSwitchDataArea.TISAreaBean;
import vip.jayson.pojo.tisSwitchDataArea.TISInterfaceBlock;
import vip.jayson.pojo.varArea.VarSegBean;
import vip.jayson.pojo.vtlArea.VtlSegBean;
import vip.jayson.util.ExcelParser;
import vip.jayson.util.StringUtil;

import java.util.*;

public class GenerateADSExcel {
    public static boolean reserveHasExist;
    public static String currentStationName;
    public static Map<String, CodeInfoBean> currentStationCodeInfo = new HashMap<>();
    public static Map<String, NodeBean> fileBeanMap = new HashMap<>();
    public static void getADSInfo(List<String> stationNumList){
        int stationNum = 0;
        StringBuilder stationNmaes = new StringBuilder();
        for (int i = 1; i < stationNumList.size(); i++) {
            stationNmaes.append(stationNumList.get(i));
        }

        for (String stationName : stationNumList) {
            currentStationName = stationName;
            //生成文件节点
            NodeBean fileNodeBean = new NodeBean();
            //生成文件节点结构头
            StructureHead fileNodStructureHead = generateStructureHead(1, stationName, null);
            FileAreaNodeConfig fileNodeConfig = new FileAreaNodeConfig(stationName, stationNum, stationNmaes, stationNumList);
            fileNodeBean.setNodeContent(fileNodeConfig.getContentList());
            fileNodStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(fileNodeConfig.getLength()), 8));
            LinkedHashMap<String, BytesInfo> fileNodeChildNodeAddr = new LinkedHashMap<>();
            if (stationName.equals("MAIN")) {
                if (CAAData.publicCode.size()>0) {
                    NodeBean varAreaNodeBean = new NodeBean();
                    StructureHead varAreaNodeStructureHead = generateStructureHead(2, "VAR", null);
                    varAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                    LinkedHashMap<String, BytesInfo> varAreaNodeChildNodeAddr = new LinkedHashMap<>();
                    LinkedHashMap<String, List<LinkedHashMap<String, CodeInfoBean>>> publicCode = CAAData.publicCode;
                    for (String publicKey : publicCode.keySet()) {
                        if (publicKey.equals("COMMON")) {
                            NodeBean commonVarSegNodeBean = new NodeBean();
                            StructureHead commonVarSegNodeStructureHead = generateStructureHead(4, "GPVBOOLSEG", null);
                            commonVarSegNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                            VarSegBean varSegBean = new VarSegBean();
                            List<CodeInfoBean> allCodeInfo = new ArrayList<>();
                            for (LinkedHashMap<String, CodeInfoBean> stringCodeInfoBeanLinkedHashMap : publicCode.get(publicKey)) {
                                allCodeInfo.add((CodeInfoBean)stringCodeInfoBeanLinkedHashMap.values().toArray()[0]);
                            }
                            varSegBean.setAll(NodeConfigs.varType.get("GPV"), allCodeInfo);
                            commonVarSegNodeBean.setNodeContent(varSegBean.getAllContent());
                            commonVarSegNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(varSegBean.getLength()),8));
                            commonVarSegNodeBean.setStructureHead(commonVarSegNodeStructureHead);
                            varAreaNodeChildNodeAddr.put(StringUtil.hexToStr(commonVarSegNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                            varAreaNodeBean.getChildNodeContent().add(commonVarSegNodeBean);
                        }
                        if (publicKey.equals("LOCK")) {
                            NodeBean lockVarSegNodeBean = new NodeBean();
                            StructureHead lockVarSegNodeStructureHead = generateStructureHead(4, "LPVBOOLSEG", null);
                            lockVarSegNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                            VarSegBean varSegBean = new VarSegBean();
                            List<CodeInfoBean> allCodeInfo = new ArrayList<>();
                            for (LinkedHashMap<String, CodeInfoBean> stringCodeInfoBeanLinkedHashMap : publicCode.get(publicKey)) {
                                allCodeInfo.add((CodeInfoBean)stringCodeInfoBeanLinkedHashMap.values().toArray()[0]);
                            }
                            varSegBean.setAll(NodeConfigs.varType.get("LPV"), allCodeInfo);
                            lockVarSegNodeBean.setNodeContent(varSegBean.getAllContent());
                            lockVarSegNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(varSegBean.getLength()),8));
                            lockVarSegNodeBean.setStructureHead(lockVarSegNodeStructureHead);
                            varAreaNodeChildNodeAddr.put(StringUtil.hexToStr(lockVarSegNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                            varAreaNodeBean.getChildNodeContent().add(lockVarSegNodeBean);
                        }

                    }
                    varAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(varAreaNodeChildNodeAddr.size()), 8));
                    varAreaNodeBean.setStructureHead(varAreaNodeStructureHead);
                    varAreaNodeBean.getChildNodeAddr().addAll(varAreaNodeChildNodeAddr.values());
                    fileNodeBean.getChildNodeContent().add(varAreaNodeBean);
                    fileNodeChildNodeAddr.put(StringUtil.hexToStr(varAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                }
                if (CAAData.ifMmiSwitch) {
                    NodeBean addFuncAreaNodeBean = new NodeBean();
                    StructureHead addFuncAreaNodeStructureHead = generateStructureHead(2, "AFZ", null);
                    addFuncAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("1", 8));
                    LinkedHashMap<String, BytesInfo> addAreaNodeChildNodeAddr = new LinkedHashMap<>();
                    NodeBean mmiSegNodeBean = new NodeBean();
                    StructureHead mmiSegNodeStructureHead = generateStructureHead(4, "MMISWITCHSEG", null);
                    mmiSegNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                    mmiSegNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("4", 8));
                    mmiSegNodeBean.setStructureHead(mmiSegNodeStructureHead);
                    mmiSegNodeBean.setNodeContent(Collections.singletonList(new BytesInfo("UNIT", 4, "MMI_ATS_SWITCH_FLAG", StringUtil.decToHex("1", 8))));
                    addAreaNodeChildNodeAddr.put(StringUtil.hexToStr(mmiSegNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                    addFuncAreaNodeBean.setStructureHead(addFuncAreaNodeStructureHead);
                    addFuncAreaNodeBean.setChildNodeAddr(Collections.singletonList(addAreaNodeChildNodeAddr.get(StringUtil.hexToStr(mmiSegNodeStructureHead.getName().getValue()))));
                    addFuncAreaNodeBean.setChildNodeContent(Collections.singletonList(mmiSegNodeBean));
                    fileNodeBean.getChildNodeContent().add(addFuncAreaNodeBean);
                    fileNodeChildNodeAddr.put(StringUtil.hexToStr(addFuncAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                }
                if (CAAData.ifGenerateLK) {
                    NodeBean lkAreaNodeBean = new NodeBean();
                    StructureHead lkAreaNodeStructureHead = generateStructureHead(2, "LK", null);
                    lkAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                    List<String> mainLKAreaInfo = CAAData.lkZoneInfoDIc.get("MAIN");
                    List<BytesInfo> lkAreaContent = new ArrayList<>();
                    for (String info : mainLKAreaInfo) {
                        BytesInfo bytesInfo = new BytesInfo("UNIT", 1, "LKAreaInfo", info);
                        lkAreaContent.add(bytesInfo);
                    }
                    lkAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(lkAreaContent.size()), 8));
                    lkAreaNodeBean.setStructureHead(lkAreaNodeStructureHead);
                    lkAreaNodeBean.setNodeContent(lkAreaContent);
                    fileNodeBean.getChildNodeContent().add(lkAreaNodeBean);
                    fileNodeChildNodeAddr.put(StringUtil.hexToStr(lkAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                }
                fileNodStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(fileNodeChildNodeAddr.size()),8));
                fileNodeBean.setStructureHead(fileNodStructureHead);
                fileNodeBean.getChildNodeAddr().addAll(fileNodeChildNodeAddr.values());
                fileBeanMap.put(stationName, fileNodeBean);
            } else {
                Map<String, TreeMap<String, InterfaceFileBean>> stationFileDic = CAAData.stationFileDic.get(stationName);
                SwitchInterfaceBlock.init();
                TISInterfaceBlock.init();
                if (stationFileDic.size()>0) {
                    //IO区
                    NodeBean ioAreaNodeBean = new NodeBean();
                    StructureHead ioAreaNodeStructureHead = generateStructureHead(2, "IO", null);
                    LinkedHashMap<String, BytesInfo> ioAreaNodeChildNodeAddr = new LinkedHashMap<>();
                    for (String currentBlock : RunTool.blockOrder) {
                        TreeMap<String, InterfaceFileBean> segMap = stationFileDic.get(currentBlock);
                        if (segMap != null) {
                            NodeBean segNodeBean = new NodeBean();
                            StructureHead segNodeStructureHead = generateStructureHead(4, currentBlock + "SEG", null);
                            segNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                            LinkedHashMap<String, BytesInfo> segNodeChildNodeAddr = new LinkedHashMap<>();
                            getIOBlockInfo(segMap, currentBlock, segNodeChildNodeAddr, segNodeBean);
                            segNodeStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(segNodeChildNodeAddr.size()), 8));
                            segNodeBean.setStructureHead(segNodeStructureHead);
                            segNodeBean.getChildNodeAddr().addAll(segNodeChildNodeAddr.values());
                            ioAreaNodeChildNodeAddr.put(StringUtil.hexToStr(segNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                            ioAreaNodeBean.getChildNodeContent().add(segNodeBean);
                        }
                    }
                    ioAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(ioAreaNodeChildNodeAddr.size()), 8));
                    ioAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                    ioAreaNodeBean.setStructureHead(ioAreaNodeStructureHead);
                    ioAreaNodeBean.getChildNodeAddr().addAll(ioAreaNodeChildNodeAddr.values());
                    if (ioAreaNodeChildNodeAddr.size() > 0) {
                        fileNodeBean.getChildNodeContent().add(ioAreaNodeBean);
                        fileNodeChildNodeAddr.put(StringUtil.hexToStr(ioAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                    }

                    //变量区
                    reserveHasExist = false;
                    NodeBean varAreaNodeBean = new NodeBean();
                    StructureHead varAreaNodeStructureHead = generateStructureHead(2, "VAR", null);
                    LinkedHashMap<String, BytesInfo> varAreaNodeChildNodeAddr = new LinkedHashMap<>();
                    varAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                    List<CodeAssignBean> codeAssignList = CAAData.codeAssignList.get(stationName);
                    if (CAAData.ifGenerateLK) {
                        CodeAssignBean codeAssignBean = new CodeAssignBean();
                        codeAssignBean.setSectionName("LK");
                        codeAssignBean.setCodeInfoList(CAAData.lkInputVarsInfo.get(stationName));
                        codeAssignList.add(codeAssignBean);
                    }
                    for (String order : RunTool.singleStationVarOrder) {
                        for (CodeAssignBean varType : codeAssignList) {
                            String sectionName = varType.getSectionName();
                            if (order.equals(sectionName)) {
                                NodeBean varSegNodeBean = new NodeBean();
                                StructureHead varSegNodeStructureHead = generateStructureHead(4, order + "BOOLSEG", null);
                                varSegNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                                VarSegBean varSegBean = new VarSegBean();
                                List<CodeInfoBean> allCodeInfo = varType.getCodeInfoList();
                                varSegBean.setAll(NodeConfigs.varType.get(order), allCodeInfo);
                                if (varSegBean.getVars().size()>0) {
                                    varSegNodeBean.setNodeContent(varSegBean.getAllContent());
                                    varSegNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(varSegBean.getLength()),8));
                                    varSegNodeBean.setStructureHead(varSegNodeStructureHead);
                                    varAreaNodeChildNodeAddr.put(StringUtil.hexToStr(varSegNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                                    varAreaNodeBean.getChildNodeContent().add(varSegNodeBean);
                                }
                            }
                        }
                    }
                    varAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(varAreaNodeChildNodeAddr.size()), 8));
                    varAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                    varAreaNodeBean.setStructureHead(varAreaNodeStructureHead);
                    varAreaNodeBean.getChildNodeAddr().addAll(varAreaNodeChildNodeAddr.values());
                    if (varAreaNodeChildNodeAddr.size()> 0) {
                        fileNodeBean.getChildNodeContent().add(varAreaNodeBean);
                        fileNodeChildNodeAddr.put(StringUtil.hexToStr(varAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                    }

                    //Bool表达式区
                    for (CodeAssignBean codeAssignBean : codeAssignList) {
                        for (CodeInfoBean codeInfoBean : codeAssignBean.getCodeInfoList()) {
                            currentStationCodeInfo.put(codeInfoBean.getCodeName(), codeInfoBean);
                        }
                    }
                    NodeBean boolExpAreaNodeBean = new NodeBean();
                    StructureHead boolExpAreaNodeStructureHead = generateStructureHead(2, "BOOL", null);
                    LinkedHashMap<String, BytesInfo> boolExpAreaNodeChildNodeAddr = new LinkedHashMap<>();
                    List<Map<String, List<String>>> vtlFileList = CAAData.vtlFileDic.get(stationName);
                    for (int vtlIndex = 0; vtlIndex < vtlFileList.size(); vtlIndex++) {
                        Map<String, List<String>> singleFileInfo = vtlFileList.get(vtlIndex);
                        List<String> boolExps = singleFileInfo.get("BOOLEXP");
                        if (boolExps.size()>0) {
                            NodeBean vtlSegNodeBean = new NodeBean();
                            StructureHead vtlSegNodeStructureHead = generateStructureHead(4, "VTLSEG", String.valueOf(vtlIndex));
                            vtlSegNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                            VtlSegBean vtlSegBean = new VtlSegBean();
                            vtlSegBean.setAll(boolExps);
                            vtlSegNodeBean.getNodeContent().addAll(vtlSegBean.getAllContent());
                            vtlSegNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(vtlSegBean.getLength()), 8));
                            vtlSegNodeBean.setStructureHead(vtlSegNodeStructureHead);
                            boolExpAreaNodeChildNodeAddr.put(StringUtil.hexToStr(vtlSegNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                            boolExpAreaNodeBean.getChildNodeContent().add(vtlSegNodeBean);
                        }
                    }
                    boolExpAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(boolExpAreaNodeChildNodeAddr.size()), 8));
                    boolExpAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                    boolExpAreaNodeBean.setStructureHead(boolExpAreaNodeStructureHead);
                    boolExpAreaNodeBean.getChildNodeAddr().addAll(boolExpAreaNodeChildNodeAddr.values());
                    if (boolExpAreaNodeChildNodeAddr.size() > 0) {
                        fileNodeBean.getChildNodeContent().add(boolExpAreaNodeBean);
                        fileNodeChildNodeAddr.put(StringUtil.hexToStr(boolExpAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                    }
                    //附加功能区
                    NodeBean addFuncAreaNodeBean = new NodeBean();
                    StructureHead addFuncAreaNodeStructureHead = generateStructureHead(2, "AFZ", null);
                    LinkedHashMap<String, BytesInfo> addFuncAreaNodeChildNodeAddr = new LinkedHashMap<>();
                    QcAlarmBean qcAlarmFile = CAAData.qcAlarmFileDic.get(stationName);
                    if (qcAlarmFile != null) {
                        NodeBean qcbjSegNodeBean = new NodeBean();
                        StructureHead qcbjSegNodeStructureHead = generateStructureHead(4, "QCBJSEG", null);
                        qcbjSegNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                        qcbjSegNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("1", 8));
                        NodeBean qcbjBlockNodeBean = new NodeBean();
                        StructureHead qcbjBlockNodeStructureHead = generateStructureHead(8, "QCBJ", null);
                        QCBJBlockBean qcbjBlockBean = new QCBJBlockBean();
                        qcbjBlockBean.setAll(qcAlarmFile);
                        qcbjBlockNodeBean.getNodeContent().addAll(qcbjBlockBean.getAllContent());
                        qcbjBlockNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                        qcbjBlockNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(qcbjBlockBean.getLength()), 8));
                        qcbjBlockNodeBean.setStructureHead(qcbjBlockNodeStructureHead);
                        qcbjSegNodeBean.getChildNodeAddr().add(new BytesInfo("UNIT", 4, "childAddress"));
                        qcbjSegNodeBean.getChildNodeContent().add(qcbjBlockNodeBean);
                        qcbjSegNodeBean.setStructureHead(qcbjSegNodeStructureHead);
                        addFuncAreaNodeChildNodeAddr.put(StringUtil.hexToStr(qcbjSegNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                        addFuncAreaNodeBean.getChildNodeContent().add(qcbjSegNodeBean);
                    }
                    NodeBean switchSegNodeBean = new NodeBean();
                    StructureHead switchSegNodeStructureHead = generateStructureHead(4, "SWITCHSEG", null);
                    switchSegNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                    SwitchSegBean switchSegBean = new SwitchSegBean();
                    switchSegBean.setAll(SwitchInterfaceBlock.switchInfo);
                    if (switchSegBean.getGASInfo().size() > 0) {
                        switchSegNodeBean.getNodeContent().addAll(switchSegBean.getAllContent());
                        switchSegNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(switchSegBean.getLength()),8));
                        switchSegNodeBean.setStructureHead(switchSegNodeStructureHead);
                        addFuncAreaNodeChildNodeAddr.put(StringUtil.hexToStr(switchSegNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                        addFuncAreaNodeBean.getChildNodeContent().add(switchSegNodeBean);
                    }
                    addFuncAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(addFuncAreaNodeChildNodeAddr.size()), 8));
                    addFuncAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex("0", 8));
                    addFuncAreaNodeBean.setStructureHead(addFuncAreaNodeStructureHead);
                    addFuncAreaNodeBean.getChildNodeAddr().addAll(addFuncAreaNodeChildNodeAddr.values());
                    if (addFuncAreaNodeChildNodeAddr.size() > 0) {
                        fileNodeBean.getChildNodeContent().add(addFuncAreaNodeBean);
                        fileNodeChildNodeAddr.put(StringUtil.hexToStr(addFuncAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                    }

                    // TIS 切机区
                    if (CAAData.ifGenerateTIS) {
                        NodeBean tisAreaNodeBean = new NodeBean();
                        StructureHead tisAreaNodeStructureHead = generateStructureHead(2, "TIS", null);
                        tisAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0",8));
                        TISAreaBean tisAreaBean = new TISAreaBean();
                        tisAreaBean.setAll(TISInterfaceBlock.tisInfo);
                        if (tisAreaBean.getTISInfo().size() > 0) {
                            tisAreaNodeBean.getNodeContent().addAll(tisAreaBean.getAllContent());
                            tisAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(tisAreaBean.getLength()),8));
                            tisAreaNodeBean.setStructureHead(tisAreaNodeStructureHead);
                            fileNodeBean.getChildNodeContent().add(tisAreaNodeBean);
                            fileNodeChildNodeAddr.put(StringUtil.hexToStr(tisAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                        }
                    }
                }
                //LK 区
                if (CAAData.ifGenerateLK) {
                    NodeBean lkAreaNodeBean = new NodeBean();
                    StructureHead lkAreaNodeStructureHead = generateStructureHead(2, "LK", null);
                    lkAreaNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
                    List<String> stationLKAreaInfo = CAAData.lkZoneInfoDIc.get(stationName);
                    List<BytesInfo> lkAreaContent = new ArrayList<>();
                    for (String info : stationLKAreaInfo) {
                        BytesInfo bytesInfo = new BytesInfo("UNIT", 1, "LKAreaInfo", info);
                        lkAreaContent.add(bytesInfo);
                    }
                    lkAreaNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(lkAreaContent.size()), 8));
                    lkAreaNodeBean.setStructureHead(lkAreaNodeStructureHead);
                    lkAreaNodeBean.setNodeContent(lkAreaContent);
                    fileNodeBean.getChildNodeContent().add(lkAreaNodeBean);
                    fileNodeChildNodeAddr.put(StringUtil.hexToStr(lkAreaNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
                }
                fileNodStructureHead.getChildCount().setValue(StringUtil.decToHex(String.valueOf(fileNodeChildNodeAddr.size()), 8));
                fileNodeBean.setStructureHead(fileNodStructureHead);
                fileNodeBean.getChildNodeAddr().addAll(fileNodeChildNodeAddr.values());
                fileBeanMap.put(stationName, fileNodeBean);
            }
            stationNum++;
        }
    }
    public static void getIOBlockInfo(TreeMap<String, InterfaceFileBean> segMap, String flag, LinkedHashMap<String, BytesInfo> segNodeChildNodeAddr, NodeBean segNodeBean){
        for (String index : segMap.keySet()) {
            InterfaceFileBean oneBlock = segMap.get(index);
            NodeBean blockNodeBean = new NodeBean();
            StructureHead blockNodeStructureHead = generateStructureHead(8, flag, index);
            List<BytesInfo> allContent = null;
            Integer length = null;
            switch (flag){
                case "OC":{
                    OCBlockBean OCBlock = new OCBlockBean();
                    OCBlock.setAll(oneBlock);
                    allContent = OCBlock.getAllContent();
                    length = OCBlock.getLength();
                    break;
                }
                case "MMI":{
                    MMIBlockBean MMIBlock = new MMIBlockBean();
                    MMIBlock.setAll(oneBlock);
                    allContent = MMIBlock.getAllContent();
                    length = MMIBlock.getLength();
                    break;
                }
                case "TCC4":{
                    TCC4BlockBean TCC4Block = new TCC4BlockBean();
                    TCC4Block.setAll(oneBlock);
                    allContent = TCC4Block.getAllContent();
                    length = TCC4Block.getLength();
                    break;
                }
                case "RBC1":{
                    RBC1BlockBean RBC1Block = new RBC1BlockBean();
                    RBC1Block.setAll(oneBlock);
                    allContent = RBC1Block.getAllContent();
                    length = RBC1Block.getLength();
                    break;
                }
                case "RBC2": {
                    RBC2BlockBean RBC2Block = new RBC2BlockBean();
                    RBC2Block.setAll(oneBlock);
                    allContent = RBC2Block.getAllContent();
                    length = RBC2Block.getLength();
                    break;
                }
                case "RBC3": {
                    RBC3BlockBean RBC3Block = new RBC3BlockBean();
                    RBC3Block.setAll(oneBlock);
                    allContent = RBC3Block.getAllContent();
                    length = RBC3Block.getLength();
                    break;
                }
                case "CBI3": {
                    CBI3BlockBean CBI3Block = new CBI3BlockBean();
                    CBI3Block.setAll(oneBlock);
                    allContent = CBI3Block.getAllContent();
                    length = CBI3Block.getLength();
                    break;
                }
                case "KDAC": {
                    KDACBlockBean KDACBlock = new KDACBlockBean();
                    KDACBlock.setAll(oneBlock);
                    allContent = KDACBlock.getAllContent();
                    length = KDACBlock.getLength();
                    break;
                }
                case "RBC4": {
                    RBC4BlockBean RBC4Block = new RBC4BlockBean();
                    RBC4Block.setAll(oneBlock);
                    allContent = RBC4Block.getAllContent();
                    length = RBC4Block.getLength();
                    break;
                }
                case "FSFB2": {
                    FSFB2BlockBean FSFB2Block = new FSFB2BlockBean();
                    FSFB2Block.setAll(oneBlock);
                    allContent = FSFB2Block.getAllContent();
                    length = FSFB2Block.getLength();
                    break;
                }
                case "OLC": {
                    OLCBlockBean OLCBlock = new OLCBlockBean();
                    OLCBlock.setAll(oneBlock);
                    allContent = OLCBlock.getAllContent();
                    length = OLCBlock.getLength();
                    break;
                }
                case "CCFSFB2": {
                    CCFSFB2BlockBean CCFSFB2Block = new CCFSFB2BlockBean();
                    CCFSFB2Block.setAll(oneBlock);
                    allContent = CCFSFB2Block.getAllContent();
                    length = CCFSFB2Block.getLength();
                    break;
                }
                case "CCSACEM": {
                    CCSACEMBlockBean CCSACEMBlock = new CCSACEMBlockBean();
                    CCSACEMBlock.setAll(oneBlock);
                    allContent = CCSACEMBlock.getAllContent();
                    length = CCSACEMBlock.getLength();
                    break;
                }
                case "HLCI": {
                    HLCIBlockBean HLCIBlock = new HLCIBlockBean();
                    HLCIBlock.setAll(oneBlock);
                    allContent = HLCIBlock.getAllContent();
                    length = HLCIBlock.getLength();
                    break;
                }
                case "HLVOBC": {
                    HLVOBCBlockBean HLVOBCBlock = new HLVOBCBlockBean();
                    HLVOBCBlock.setAll(oneBlock);
                    allContent = HLVOBCBlock.getAllContent();
                    length = HLVOBCBlock.getLength();
                    break;
                }
                default:break;
            }
            blockNodeBean.getNodeContent().addAll(allContent);
            blockNodeStructureHead.getChildCount().setValue(StringUtil.decToHex("0", 8));
            blockNodeStructureHead.getContentLength().setValue(StringUtil.decToHex(String.valueOf(length), 8));
            blockNodeBean.setStructureHead(blockNodeStructureHead);
            segNodeChildNodeAddr.put(StringUtil.hexToStr(blockNodeStructureHead.getName().getValue()), new BytesInfo("UNIT", 4, "childAddress"));
            segNodeBean.getChildNodeContent().add(blockNodeBean);
        }

    }
    public static StructureHead generateStructureHead(Integer type, String flag, String index) {
        String id = null;
        String name = null;
        StructureHead structureHead = new StructureHead();
        structureHead.getType().setValue(StringUtil.decToHex(String.valueOf(type), 8));
        structureHead.getType().setComment(flag);
        if (type == 1) {
            if (flag.equals("MAIN")) {
                id = "00000000";
            } else {
                id = StringUtil.decToHex(flag, 8);
            }
            name = flag + "_ADS";
        } else if (type == 2) {
            List<String> nameAndId = NodeConfigs.zoneStructureNameAndId.get(flag);
            name = nameAndId.get(0);
            id = nameAndId.get(1);
        } else if (type == 4) {
            List<String> nameAndId = NodeConfigs.segmentStructureNameAndId.get(flag);
            name = nameAndId.get(0);
            id = nameAndId.get(1);
            if (index != null) {
                name += index;
            }
        } else if (type == 8) {
            List<String> nameAndId = NodeConfigs.blocStructureNameAndId.get(flag);
            if (flag.equals("QCBJ")) {
                name = nameAndId.get(0);
            }else{
                name = nameAndId.get(0) + "_" + index;}
            id = nameAndId.get(1);
        }
        structureHead.getId().setValue(id);
        structureHead.getId().setComment(name);
        structureHead.getName().setValue(StringUtil.strToHex(name, 128));
        structureHead.getName().setComment(name);
        return structureHead;
    }
    public static void suppInfo(List<String> stationNumList){
        getADSInfo(stationNumList);
        for (String stationName : fileBeanMap.keySet()) {
            NodeBean nodeBean = fileBeanMap.get(stationName);
            SuppInfo.suppStarAddr(nodeBean, 0);
            SuppInfo.suppChildAddr(nodeBean);
        }
    }
    public static void generateADSExcel(List<String> stationNumList){
        suppInfo(stationNumList);
        Map<String, List<BytesInfo>> stationAllInfo = new HashMap<>();
        for (String stationName : fileBeanMap.keySet()) {
            NodeBean nodeBean = fileBeanMap.get(stationName);
            List<BytesInfo> allNodeContent = nodeBean.getAllNodeContent(nodeBean, new ArrayList<BytesInfo>());
            stationAllInfo.put(stationName, allNodeContent);
        }
        ExcelParser.writeADSFile(stationAllInfo);
    }
}
