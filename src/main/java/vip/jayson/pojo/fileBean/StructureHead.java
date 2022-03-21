package vip.jayson.pojo.fileBean;

import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class StructureHead {
    private BytesInfo size;
    private BytesInfo version;
    private BytesInfo dataVersion;
    private BytesInfo type;
    private BytesInfo id;
    private BytesInfo name;
    private BytesInfo childCount;
    private BytesInfo contentLength;
    private Integer length;
    private Integer beginAddress;
    public Integer getLength() {
        length = 92;
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public StructureHead() {
        this.size = new BytesInfo("UNIT", 4, "Size", "0000005C","Structure header size, fixed value 92 bytes\n结构头大小，固定值92字节\n");
        this.type = new BytesInfo("UNIT", 4, "Type");
        this.id = new BytesInfo("UNIT", 4, "ID");
        this.name = new BytesInfo("String", 64, "Name");
        this.version = new BytesInfo("UNIT", 4, "Version", "00000000","Structure version, default value is 0\n结构版本，默认为0\n");
        this.dataVersion = new BytesInfo("UNIT", 4, "Data_Version", "00000000","Data version, default value is 0\n数据版本，默认为0\n");
        this.childCount = new BytesInfo("UNIT", 4, "Child_Count");
        this.contentLength = new BytesInfo("UNIT", 4, "Content_Length");
    }

    public BytesInfo getType() {
        return type;
    }

    public void setType(BytesInfo type) {
        this.type = type;
    }

    public BytesInfo getId() {
        return id;
    }

    public void setId(BytesInfo id) {
        this.id = id;
    }

    public BytesInfo getName() {
        return name;
    }

    public void setName(BytesInfo name) {
        this.name = name;
    }

    public BytesInfo getChildCount() {
        return childCount;
    }

    public void setChildCount(BytesInfo childCount) {
        this.childCount = childCount;
    }

    public BytesInfo getContentLength() {
        return contentLength;
    }

    public void setContentLength(BytesInfo contentLength) {
        this.contentLength = contentLength;
    }

    public BytesInfo getSize() {
        return size;
    }

    public void setSize(BytesInfo size) {
        this.size = size;
    }

    public BytesInfo getVersion() {
        return version;
    }

    public void setVersion(BytesInfo version) {
        this.version = version;
    }

    public BytesInfo getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(BytesInfo dataVersion) {
        this.dataVersion = dataVersion;
    }
    public List<BytesInfo> getAllContent(){
        List<BytesInfo> allContent = new ArrayList<>();
        allContent.add(this.size);
        allContent.add(this.type);
        allContent.add(this.id);
        allContent.add(this.name);
        allContent.add(this.version);
        allContent.add(this.dataVersion);
        allContent.add(this.childCount);
        allContent.add(this.contentLength);
        return allContent;
    }
    public void setFiledBeginAddress(int beginAddress){
        this.beginAddress = beginAddress;
        this.size.setStartAddr(beginAddress);
        this.type.setStartAddr(beginAddress + 4);
        this.id.setStartAddr(beginAddress + 4 * 2);
        this.name.setStartAddr(beginAddress + 4 * 3);
        this.version.setStartAddr(beginAddress + 4 * 3 + 64);
        this.dataVersion.setStartAddr(beginAddress + 4 * 4 + 64);
        this.childCount.setStartAddr(beginAddress + 4 * 5 + 64);
        this.contentLength.setStartAddr(beginAddress + 4 * 6 + 64);
    }
}
