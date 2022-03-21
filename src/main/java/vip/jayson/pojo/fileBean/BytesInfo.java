package vip.jayson.pojo.fileBean;

/**
 * @author Gemini Jayson
 */
public class BytesInfo {
    private Integer startAddr;
    private String type;
    private Integer bytes;
    private String name;
    private String value;
    private String comment;

    public BytesInfo() {
    }

    public BytesInfo(String type, Integer bytes, String name) {
        this.type = type;
        this.bytes = bytes;
        this.name = name;
    }
    public BytesInfo(Integer startAddr, String type, Integer bytes, String name) {
        this.startAddr = startAddr;
        this.type = type;
        this.bytes = bytes;
        this.name = name;
    }
    public BytesInfo(String type, Integer bytes, String name, String value) {
        this.type = type;
        this.bytes = bytes;
        this.name = name;
        this.value = value;
    }
    public BytesInfo(Integer startAddr, String type, Integer bytes, String name, String value) {
        this.startAddr = startAddr;
        this.type = type;
        this.bytes = bytes;
        this.name = name;
        this.value = value;
    }

    public BytesInfo(String type, Integer bytes, String name, String value, String comment) {
        this.type = type;
        this.bytes = bytes;
        this.name = name;
        this.value = value;
        this.comment = comment;
    }
    public BytesInfo(Integer startAddr, String type, Integer bytes, String name, String value, String comment) {
        this.startAddr = startAddr;
        this.type = type;
        this.bytes = bytes;
        this.name = name;
        this.value = value;
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBytes() {
        return bytes;
    }

    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStartAddr() {
        return startAddr;
    }

    public void setStartAddr(Integer startAddr) {
        this.startAddr = startAddr;
    }

    @Override
    public String toString() {
        return "BytesInfo{" +
                "startAddr='" + startAddr + '\'' +
                ", type='" + type + '\'' +
                ", bytes=" + bytes +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
