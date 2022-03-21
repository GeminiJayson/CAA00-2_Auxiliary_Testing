package vip.jayson.config;

/**
 * @author GeminiJayson
 */

public enum ConfigEnum {
    /*用来配置输出文件的路径*/
    CAA_ADSDIR,
    /*用来配置ADSDATA文件夹的路径*/
    CAA_DEFDIR,
    /*用来配置PID值文件的路径*/
    CAA_PID_FILE,
    /*用来配置公共变量文件的路径*/
    CAA_SHARECODE_FILE,
    /*用来配置单站的CONFIG文件的路径*/
    CAA_CONFIG,
    /*用来配置MMI/ATS应用切机功能，当且仅当值为TRUE时表示MMI/ATS不参与应用切记功能，其他情况（非TRUE或者没有配置）表示MMI/ATS参与应用切机功能*/
    MMI_ATS_NOT_SWITCH_FLAG,
    /*支持配置TCC4/CBI3/OC/KADC/互联互通CI/互联互通VOBC类型的通信接口文件*/
    CAA_RSSP1_FILE,
    /*支持配置RBC1/RBC2/RBC3/RBC4类型的通信接口文件*/
    CAA_RSSP2_FILE,
    /*支持配置MMI类型的通信接口文件*/
    CAA_IPS200MMI_FILE,
    /*支持配置地铁ATS通信接口文件*/
    CAA_CIATS_FILE,
    /*支持配置通用FSFB2和车载FSFB2通信接口文件*/
    CAA_FSFB2_FILE,
    /*支持配置VTL文件*/
    CAA_VTL_FILE,
    /*支持配置驱采报警文件*/
    CAA_CFG_FILE,
    /*支持OLC通信接口文件*/
    CAA_OLC_FILE,
    /*支持车载SACEM通信接口文件*/
    CAA_SCM_FILE,
    /*支持SACEM接口文件生成TXT的ID以及签名值*/
    CAA_SCM_SIGN_ID_FILE,
}
