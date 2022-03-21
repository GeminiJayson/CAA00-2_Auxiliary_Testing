package vip.jayson.pojo.dataBean;

import java.util.Map;

public class CodePos {
    private String sectionKey;
    private String fileIndexKey;
    private String type;
    private String codeSection;
    private Map<String, Integer> codeSectionFlag;
    private String codeIndexKey;

    public CodePos() {
    }

    public CodePos(String sectionKey, String fileIndexKey, String type, String codeSection, Map<String, Integer> codeSectionFlag, String codeIndexKey) {
        this.sectionKey = sectionKey;
        this.fileIndexKey = fileIndexKey;
        this.type = type;
        this.codeSection = codeSection;
        this.codeSectionFlag = codeSectionFlag;
        this.codeIndexKey = codeIndexKey;
    }

    public void setCodeSection(String codeSection) {
        this.codeSection = codeSection;
    }

    public Map<String, Integer> getCodeSectionFlag() {
        return codeSectionFlag;
    }

    public void setCodeSectionFlag(Map<String, Integer> codeSectionFlag) {
        this.codeSectionFlag = codeSectionFlag;
    }

    public String getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(String sectionKey) {
        this.sectionKey = sectionKey;
    }

    public String getFileIndexKey() {
        return fileIndexKey;
    }

    public void setFileIndexKey(String fileIndexKey) {
        this.fileIndexKey = fileIndexKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCodeSection() {
        return codeSection;
    }

    public void setCodeSectionIndex(String codeSection) {
        this.codeSection = codeSection;
    }

    public String getCodeIndexKey() {
        return codeIndexKey;
    }

    public void setCodeIndexKey(String codeIndexKey) {
        this.codeIndexKey = codeIndexKey;
    }
}
