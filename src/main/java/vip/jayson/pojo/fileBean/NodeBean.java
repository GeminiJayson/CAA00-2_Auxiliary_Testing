package vip.jayson.pojo.fileBean;

import java.util.ArrayList;
import java.util.List;

public class NodeBean {
    private StructureHead structureHead;
    private List<BytesInfo> nodeContent = new ArrayList<>();
    private List<BytesInfo> childNodeAddr = new ArrayList<>();
    private List<NodeBean> childNodeContent = new ArrayList<>();

    public StructureHead getStructureHead() {
        return structureHead;
    }

    public void setStructureHead(StructureHead structureHead) {
        this.structureHead = structureHead;
    }

    public List<BytesInfo> getNodeContent() {
        return nodeContent;
    }

    public void setNodeContent(List<BytesInfo> nodeContent) {
        this.nodeContent = nodeContent;
    }

    public List<BytesInfo> getChildNodeAddr() {
        return childNodeAddr;
    }

    public void setChildNodeAddr(List<BytesInfo> childNodeAddr) {
        this.childNodeAddr = childNodeAddr;
    }

    public List<NodeBean> getChildNodeContent() {
        return childNodeContent;
    }

    public void setChildNodeContent(List<NodeBean> childNodeContent) {
        this.childNodeContent = childNodeContent;
    }
    public List<BytesInfo> getAllNodeContent(NodeBean nodeBean, List<BytesInfo> nodeAllContent){
        nodeAllContent.addAll(nodeBean.getStructureHead().getAllContent());
        if (nodeBean.getNodeContent() != null) {
            nodeAllContent.addAll(nodeBean.getNodeContent());
        }
        if (nodeBean.getChildNodeAddr() != null) {
            nodeAllContent.addAll(nodeBean.getChildNodeAddr());
        }

        List<NodeBean> childNodeContent = nodeBean.getChildNodeContent();
        for (NodeBean bean : childNodeContent) {
            nodeAllContent = getAllNodeContent(bean, nodeAllContent);
        }
        return nodeAllContent;
    }

}
