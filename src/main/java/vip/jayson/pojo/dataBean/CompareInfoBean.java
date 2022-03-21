package vip.jayson.pojo.dataBean;

public class CompareInfoBean {
    private Integer start;
    private Integer length;
    private String value;

    public CompareInfoBean() {
    }

    public CompareInfoBean(Integer start, Integer length, String value) {
        this.start = start;
        this.length = length;
        this.value = value;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "比较二进制{" +
                "开始地址=" + start +
                ", 字长=" + length +
                ", 值/十六进制='" + value + '\'' +
                '}';
    }
}
