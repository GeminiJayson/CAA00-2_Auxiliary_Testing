package vip.jayson.pojo.dataBean;

import java.util.List;

public class CodeAssignBean {
    private String sectionName;
    private String interfaceFileName;
    private String interfaceFileSectionName;
    private List<CodeInfoBean> codeInfoList;

    public CodeAssignBean() {
    }

    public CodeAssignBean(String sectionName, List<CodeInfoBean> codeInfoList) {
        this.sectionName = sectionName;
        this.codeInfoList = codeInfoList;
    }

    public CodeAssignBean(String sectionName, String interfaceFileName, String interfaceFileSectionName, List<CodeInfoBean> codeInfoList) {
        this.sectionName = sectionName;
        this.interfaceFileName = interfaceFileName;
        this.interfaceFileSectionName = interfaceFileSectionName;
        this.codeInfoList = codeInfoList;
    }

    public CodeAssignBean(String sectionName, String interfaceFileSectionName, List<CodeInfoBean> codeInfoList) {
        this.sectionName = sectionName;
        this.interfaceFileSectionName = interfaceFileSectionName;
        this.codeInfoList = codeInfoList;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getInterfaceFileName() {
        return interfaceFileName;
    }

    public void setInterfaceFileName(String interfaceFileName) {
        this.interfaceFileName = interfaceFileName;
    }

    public String getInterfaceFileSectionName() {
        return interfaceFileSectionName;
    }

    public void setInterfaceFileSectionName(String interfaceFileSectionName) {
        this.interfaceFileSectionName = interfaceFileSectionName;
    }

    public List<CodeInfoBean> getCodeInfoList() {
        return codeInfoList;
    }

    public void setCodeInfoList(List<CodeInfoBean> codeInfoList) {
        this.codeInfoList = codeInfoList;
    }
}
