package vip.jayson.dataDeal;

import vip.jayson.config.CAAData;
import vip.jayson.config.SectionEnum;
import vip.jayson.config.VarType;
import vip.jayson.pojo.dataBean.*;
import vip.jayson.util.LogConsole;

import java.util.*;

public class FileCodeAssign {

    /**
     * 分配编号和码位
     */
    public static void assignNumberAndCode(String[] assignNumberOrder, Integer stationNum){
        CAAData.stationOneName = CAAData.stationNumList.get(stationNum);
        LogConsole.info("分配" + CAAData.stationOneName + "变量编号以及码组信息");
        CAAData.singleCodeIndexStart = 10000 + 240000 / (CAAData.stationNumList.size() - 1) * (stationNum - 1);
        List<CodeAssignBean> sectionCodeInfoAssignList = new ArrayList<>();
        for (String orderKey : assignNumberOrder) {
            CAAData.codeOrder = 0;
            CAAData.codeInfoList = new ArrayList<>();
            switch (orderKey) {
                case "GPV":
                    FileCodeAssign.assignPublicCode("COMMON");
                    break;
                case "LPV":
                    FileCodeAssign.assignPublicCode("LOCK");
                    break;
                case "GSV":
                    FileCodeAssign.assignSingleCommonVar(stationNum);
                    break;
                case "LSV":
                    FileCodeAssign.assignSingleLockVar(stationNum);
                    break;
                case "TSV":
                    FileCodeAssign.assignSingleTimeVar(stationNum);
                    break;
                default:
                    FileCodeAssign.assignSingleInputVar(orderKey, stationNum);
                    break;
            }
            if (CAAData.codeInfoList.size() > 0) {
                sectionCodeInfoAssignList.add(new CodeAssignBean(orderKey, CAAData.codeInfoList));
                CAAData.codeAssignList.put(CAAData.stationOneName, sectionCodeInfoAssignList);
            }
        }
        if (CAAData.ifGenerateLK) {
            FileCodeAssign.assignLKVar(stationNum);
        }
    }
    public static void assignPublicCode(String type) {
        try {
            String codeType = (String) VarType.class.getDeclaredField(type).get(new VarType());
            for (LinkedHashMap<String, CodeInfoBean> typeCode : CAAData.publicCode.get(type)) {
                CodeInfoBean code = typeCode.get(CAAData.stationOneName);
                if (code.getCodeNum() == null) {
                    CodeInfoBean numAndCode = CodeAssignInfo.getNumAndCode(code.getValue(), codeType, 0, CAAData.codeOrder, CAAData.publicCodeIndexStart);
                    CAAData.codeInfoList.add(numAndCode);
                    typeCode.replaceAll((k, v) -> numAndCode);
                    CAAData.codeOrder++;
                    CAAData.publicCodeIndexStart++;
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }



    public static void assignSingleCommonVar(Integer stationNum) {
        List<String> normal = new ArrayList<>();
        for (Map<String, List<String>> stringListMap : CAAData.vtlFileDic.get(CAAData.stationOneName)) {
            normal.addAll(stringListMap.get("NORMAL"));
        }
        for (String codeName : normal) {
            CodePos codePos = FileCodeAssign.getCodePos(CAAData.stationOneName, codeName);
            boolean perm0Flag = false;
            boolean ifPublicCode = false;
            for (LinkedHashMap<String, CodeInfoBean> common : CAAData.publicCode.get("COMMON")) {
                CodeInfoBean commonStationCodeInfo = common.get(CAAData.stationOneName);
                if (commonStationCodeInfo.getCodeName().equals(codeName)) {
                    ifPublicCode = true;
                    commonStationCodeInfo.setValue(codeName);
                    CAAData.codeInfoList.add(commonStationCodeInfo);
                    if (codePos != null) {
                        FileCodeAssign.setCodeInfo(codePos, commonStationCodeInfo);
                    }
                }
            }
            if (!ifPublicCode) {
                CodeInfoBean numAndCode = CodeAssignInfo.getNumAndCode(codeName, VarType.COMMON, stationNum, CAAData.codeOrder, CAAData.singleCodeIndexStart);
                if (codeName.equals("PERM0")) {
                    perm0Flag = true;
                    if (CAAData.perm0CodeInfo == null) {
                        CAAData.codeInfoList.add(numAndCode);
                        if (codePos != null) {
                            FileCodeAssign.setCodeInfo(codePos, numAndCode);
                        }
                        CAAData.perm0CodeInfo = numAndCode;
                        CAAData.codeOrder++;
                        CAAData.singleCodeIndexStart++;
                    } else {
                        CAAData.codeInfoList.add(CAAData.perm0CodeInfo);
                        if (codePos != null) {
                            FileCodeAssign.setCodeInfo(codePos, CAAData.perm0CodeInfo);
                        }
                    }
                } else {
                    CAAData.codeInfoList.add(numAndCode);
                    if (codePos != null) {
                        FileCodeAssign.setCodeInfo(codePos, numAndCode);
                    }
                    CAAData.codeOrder++;
                    CAAData.singleCodeIndexStart++;
                }
            }


            if (!perm0Flag && codeName.equals("PERM0")) {
                CodeInfoBean numAndCode = CodeAssignInfo.getNumAndCode(codeName, VarType.COMMON, stationNum, CAAData.codeOrder, CAAData.singleCodeIndexStart);
                CAAData.codeInfoList.add(numAndCode);
                CAAData.codeOrder++;
                CAAData.singleCodeIndexStart++;
                CAAData.perm0CodeInfo = numAndCode;
                break;
            }

        }
    }
    public static void assignSingleLockVar(Integer stationNum) {
        List<String> lock = new ArrayList<>();
        for (Map<String, List<String>> stringListMap : CAAData.vtlFileDic.get(CAAData.stationOneName)) {
            lock.addAll(stringListMap.get("LOCK"));
        }

        for (String lockCode : lock) {
            boolean ifPublicCode = false;
            for (LinkedHashMap<String, CodeInfoBean> lockPublic : CAAData.publicCode.get("LOCK")) {
                CodeInfoBean lockStationCodeInfoBean = lockPublic.get(CAAData.stationOneName);
                if (lockStationCodeInfoBean.getCodeName().equals(lockCode)) {
                    ifPublicCode = true;
                    lockStationCodeInfoBean.setValue(lockCode);
                    CAAData.codeInfoList.add(lockStationCodeInfoBean);
                }
            }
            if (!ifPublicCode) {
                CodeInfoBean numAndCode = CodeAssignInfo.getNumAndCode(lockCode, VarType.LOCK, stationNum, CAAData.codeOrder, CAAData.singleCodeIndexStart);
                CAAData.codeInfoList.add(numAndCode);
                CAAData.codeOrder++;
                CAAData.singleCodeIndexStart++;
            }
        }
    }
    public static void assignSingleTimeVar(Integer stationNum) {
        List<String> time = new ArrayList<>();
        for (Map<String, List<String>> stringListMap : CAAData.vtlFileDic.get(CAAData.stationOneName)) {
            time.addAll(stringListMap.get("TIME"));
        }

        for (String timeCode : time) {
            CodeInfoBean numAndCode = CodeAssignInfo.getNumAndCode(timeCode, VarType.TIME, stationNum, CAAData.codeOrder, CAAData.singleCodeIndexStart);
            CAAData.codeInfoList.add(numAndCode);
            CAAData.codeOrder++;
            CAAData.singleCodeIndexStart++;
        }
    }
    public static void assignSingleInputVar(String orderKey, Integer stationNum) {
        String paramType = null;
        try {
            paramType = (String) VarType.class.getDeclaredField(orderKey).get(new VarType());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        Map<String, TreeMap<String, InterfaceFileBean>> interfaceFileBeanMap = CAAData.stationFileDic.get(CAAData.stationOneName);
        TreeMap<String, InterfaceFileBean> indexInterfaceFileBeanMap = interfaceFileBeanMap.get(orderKey);
        if (indexInterfaceFileBeanMap != null) {
            for (String indexKey : indexInterfaceFileBeanMap.keySet()) {
                InterfaceFileBean interfaceFileBean = indexInterfaceFileBeanMap.get(indexKey);
                List<CodeBean> receive = interfaceFileBean.getReceive();
                if (receive.size() > 1) {
                    for (CodeBean codeBean : receive) {
                        if (!codeBean.getBlockName().equals(SectionEnum.OVERVIEW.name())) {
                            LinkedHashMap<String, CodeInfoBean> codeInfo = codeBean.getCodeInfo();
                            for (String codeKey : codeInfo.keySet()) {
                                CodeInfoBean codeInfoBean = codeInfo.get(codeKey);
                                String codeName = codeInfoBean.getValue();
                                if (codeName.equals("RESERVE")) {
                                    if (CAAData.reserveCodeInfo != null) {
                                        CodeInfoBean codeInfoBean1 = CAAData.reserveCodeInfo;
                                        CAAData.codeInfoList.add(codeInfoBean1);
                                        codeInfoBean.setCodeName(codeInfoBean1.getCodeName());
                                        codeInfoBean.setCodeNum(codeInfoBean1.getCodeNum());
                                        codeInfoBean.setCodeTrue(codeInfoBean1.getCodeTrue());
                                        codeInfoBean.setCodeFalse(codeInfoBean1.getCodeFalse());
                                        if (CAAData.codeOrder == 0) {
                                            CAAData.codeOrder++;
                                        }
                                    } else {
                                        CodeInfoBean numAndCode = CodeAssignInfo.getNumAndCode(codeName, paramType, stationNum, CAAData.codeOrder, CAAData.singleCodeIndexStart);
                                        CAAData.codeInfoList.add(numAndCode);
                                        codeInfoBean.setCodeName(codeName);
                                        codeInfoBean.setCodeNum(numAndCode.getCodeNum());
                                        codeInfoBean.setCodeTrue(numAndCode.getCodeTrue());
                                        codeInfoBean.setCodeFalse(numAndCode.getCodeFalse());
                                        CAAData.reserveCodeInfo = numAndCode;
                                        CAAData.codeOrder++;
                                        CAAData.singleCodeIndexStart++;
                                    }
                                } else {
                                    boolean ifPublicCode = false;
                                    for (String publicKey : CAAData.publicCode.keySet()) {
                                        List<LinkedHashMap<String, CodeInfoBean>> sectionPublic = CAAData.publicCode.get(publicKey);
                                        for (LinkedHashMap<String, CodeInfoBean> stationPublic : sectionPublic) {
                                            CodeInfoBean stationPublicCode = stationPublic.get(CAAData.stationOneName);
                                            if (stationPublicCode.getCodeName().equals(codeName)) {
                                                ifPublicCode = true;
                                                stationPublicCode.setValue(codeName);
                                                codeInfoBean.setCodeName(stationPublicCode.getCodeName());
                                                codeInfoBean.setCodeNum(stationPublicCode.getCodeNum());
                                                codeInfoBean.setCodeTrue(stationPublicCode.getCodeTrue());
                                                codeInfoBean.setCodeFalse(stationPublicCode.getCodeFalse());
                                                CAAData.codeInfoList.add(stationPublicCode);
                                                if (CAAData.codeOrder == 0) {
                                                    CAAData.codeOrder++;
                                                }
                                            }
                                        }
                                    }
                                    if (!ifPublicCode) {
                                        CodeInfoBean numAndCode = CodeAssignInfo.getNumAndCode(codeName, paramType, stationNum, CAAData.codeOrder, CAAData.singleCodeIndexStart);
                                        CAAData.codeInfoList.add(numAndCode);
                                        codeInfoBean.setCodeName(codeName);
                                        codeInfoBean.setCodeNum(numAndCode.getCodeNum());
                                        codeInfoBean.setCodeTrue(numAndCode.getCodeTrue());
                                        codeInfoBean.setCodeFalse(numAndCode.getCodeFalse());
                                        CAAData.codeOrder++;
                                        CAAData.singleCodeIndexStart++;
                                    }

                                }

                            }
                        }

                    }
                }
            }
        }

    }
    public static void assignLKVar(Integer stationNum) {
        CAAData.codeOrder = 0;
        List<CodeInfoBean> newList = new ArrayList<>();
        for (CodeInfoBean codeInfoBean : CAAData.lkInputVarsInfo.get(CAAData.stationOneName)) {
            String codeType = codeInfoBean.getCodeType();
            String codeName = codeInfoBean.getCodeName();
            newList.add(CodeAssignInfo.getNumAndCode(codeName, codeType, stationNum, CAAData.codeOrder, CAAData.singleCodeIndexStart));
            CAAData.codeOrder++;
            CAAData.singleCodeIndexStart++;
        }
        CAAData.lkInputVarsInfo.put(CAAData.stationOneName, newList);
    }

    public static CodePos getCodePos(String stationOneName, String codeName) {
        Map<String, TreeMap<String, InterfaceFileBean>> interfaceFileBeanMap = CAAData.stationFileDic.get(stationOneName);
        for (String sectionKey : interfaceFileBeanMap.keySet()) {
            TreeMap<String, InterfaceFileBean> interfaceFileBeanTreeMap = interfaceFileBeanMap.get(sectionKey);
            for (String indexKey : interfaceFileBeanTreeMap.keySet()) {
                InterfaceFileBean interfaceFileBean = interfaceFileBeanTreeMap.get(indexKey);
                List<CodeBean> send = interfaceFileBean.getSend();
                List<CodeBean> receive = interfaceFileBean.getReceive();
                for (CodeBean sendCodeBean : send) {
                    LinkedHashMap<String, CodeInfoBean> sendCodeInfo = sendCodeBean.getCodeInfo();
                    for (String codeKey : sendCodeInfo.keySet()) {
                        CodeInfoBean codeInfoBean = sendCodeInfo.get(codeKey);
                        if (codeName.equals(codeInfoBean.getValue())) {
                            return new CodePos(sectionKey, indexKey, "send", sendCodeBean.getBlockName(), sendCodeBean.getFlagInfo(),codeKey);
                        }
                    }
                }
                for (CodeBean receiveCodeBean : receive) {
                    LinkedHashMap<String, CodeInfoBean> receiveCodeInfo = receiveCodeBean.getCodeInfo();
                    for (String codeKey : receiveCodeInfo.keySet()) {
                        CodeInfoBean codeInfoBean = receiveCodeInfo.get(codeKey);
                        if (codeName.equals(codeInfoBean.getValue())) {
                            return new CodePos(sectionKey, indexKey, "receive", receiveCodeBean.getBlockName(), receiveCodeBean.getFlagInfo(), codeKey);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void setCodeInfo(CodePos codePos, CodeInfoBean newCodeInfoBean) {
        Map<String, TreeMap<String, InterfaceFileBean>> interfaceFileBeanMap = CAAData.stationFileDic.get(CAAData.stationOneName);
        TreeMap<String, InterfaceFileBean> interfaceFileBeanTreeMap = interfaceFileBeanMap.get(codePos.getSectionKey());
        InterfaceFileBean interfaceFileBean = interfaceFileBeanTreeMap.get(codePos.getFileIndexKey());
        if ("send".equals(codePos.getType())) {
            List<CodeBean> send = interfaceFileBean.getSend();
            for (CodeBean sendCodeBean : send) {
                if (sendCodeBean.getBlockName().equals(codePos.getCodeSection())) {
                    if (sendCodeBean.getFlagInfo().equals(codePos.getCodeSectionFlag())) {
                        LinkedHashMap<String, CodeInfoBean> sendCodeInfo = sendCodeBean.getCodeInfo();
                        sendCodeInfo.put(codePos.getCodeIndexKey(), newCodeInfoBean);
                    }

                }
            }
        } else if("receive".equals(codePos.getType())){
            List<CodeBean> receive = interfaceFileBean.getReceive();
            for (CodeBean receiveCodeBean : receive) {
                if (receiveCodeBean.getBlockName().equals(codePos.getCodeSection())) {
                    if (receiveCodeBean.getFlagInfo().equals(codePos.getCodeSectionFlag())) {
                        LinkedHashMap<String, CodeInfoBean> receiveCodeInfo = receiveCodeBean.getCodeInfo();
                        receiveCodeInfo.put(codePos.getCodeIndexKey(), newCodeInfoBean);
                    }
                }
            }
        }
    }

}
