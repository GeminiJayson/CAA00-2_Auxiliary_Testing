package vip.jayson.pojo.dataBean;



public class CodeInfoBean {
    private String codeType;
    private String codeName;
    private String codeNum;
    private Integer codeAssignIndex;
    private String codeTrue;
    private String codeFalse;
    private String value;

    public CodeInfoBean() {
    }

    public CodeInfoBean(String value) {
        this.value = value;
    }

    public CodeInfoBean(String codeType, String codeName, String codeNum, Integer codeAssignIndex, String codeTrue, String codeFalse) {
        this.codeType = codeType;
        this.codeName = codeName;
        this.codeNum = codeNum;
        this.codeAssignIndex = codeAssignIndex;
        this.codeTrue = codeTrue;
        this.codeFalse = codeFalse;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Integer getCodeAssignIndex() {
        return codeAssignIndex;
    }

    public void setCodeAssignIndex(Integer codeAssignIndex) {
        this.codeAssignIndex = codeAssignIndex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeTrue() {
        return codeTrue;
    }

    public void setCodeTrue(String codeTrue) {
        this.codeTrue = codeTrue;
    }

    public String getCodeFalse() {
        return codeFalse;
    }

    public void setCodeFalse(String codeFalse) {
        this.codeFalse = codeFalse;
    }

    @Override
    public String toString() {
        return "CodeInfoBean{" +
                "codeName='" + codeName + '\'' +
                ", codeNum='" + codeNum + '\'' +
                ", codeTrue='" + codeTrue + '\'' +
                ", codeFalse='" + codeFalse + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
