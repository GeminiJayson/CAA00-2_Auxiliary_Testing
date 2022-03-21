package vip.jayson.config;

import vip.jayson.pojo.dataBean.*;

import java.util.*;

public class CAAData {
    //主配置文件信息
    public static ConfigBean configBean;
    //码组信息
    public static List<Map<String, String>> codeData;
    //MMI是否参与切机
    public static boolean ifMmiSwitch;
    //公共配置PID信息
    public static Map<String, Map<String, Integer>> pid;
    //公共码位信息
    public static LinkedHashMap<String, List<LinkedHashMap<String, CodeInfoBean>>> publicCode;
    //输出补零码位信息
    public static CodeInfoBean perm0CodeInfo;
    //输入补零码位信息
    public static CodeInfoBean reserveCodeInfo;
    // 单站接口文件集合
    public static LinkedHashMap<String, Map<String, TreeMap<String, InterfaceFileBean>>> stationFileDic;
    // 单站驱采报警文件集合
    public static LinkedHashMap<String, QcAlarmBean> qcAlarmFileDic;
    // 单站VTL文件集合
    public static LinkedHashMap<String, List<Map<String, List<String>>>> vtlFileDic;
    //是否生成LK
    public static boolean ifGenerateLK = false;
    //LK输入变量信息
    public  static Map<String, List<CodeInfoBean>> lkInputVarsInfo;
    //是否生成TIS
    public static boolean ifGenerateTIS;
    //公共码位码组分配其实下标
    public static Integer publicCodeIndexStart = 0;
    //单站码位码组分配其实下标
    public static Integer singleCodeIndexStart;
    //分配好编号和码组的集合
    public static Map<String, List<CodeAssignBean>> codeAssignList = new HashMap<>();
    //每个模块的编号码位信息
    public static List<CodeInfoBean> codeInfoList;
    //码位顺序
    public static Integer codeOrder;
    //当前站号名称
    public static String stationOneName;
    //所有站号名称集合
    public static List<String> stationNumList = new ArrayList<>();
    //列控区内容
    public static Map<String, List<String>> lkZoneInfoDIc = new HashMap<>();
    //CAA对比辅助表信息
    public static Map<String, List<CompareInfoBean>> compareInfoDic = new HashMap<>();
    //CAA对比辅助表信息LK
    public static Map<String, List<CompareInfoBean>> compareInfoLKDic = new HashMap<>();
    //CAA200 ADS内容
    public static Map<String, List<String>> caaADSContentDic = new HashMap<>();
    //CAA200 LK ADS内容
    public static Map<String, List<String>> lkCAAADSContentDic = new HashMap<>();
    //MMI or ATS 安全控制码位记载
    public static Map<String, List<String>> mmiSafeCommand = new HashMap<>();
}
