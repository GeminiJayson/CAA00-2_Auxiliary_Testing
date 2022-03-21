package vip.jayson.main;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.pojo.fileBean.NodeBean;
import vip.jayson.util.StringUtil;

import java.util.List;

public class SuppInfo {
    public static Integer suppStarAddr(NodeBean nodeBean, Integer startAddr){
        nodeBean.getStructureHead().setFiledBeginAddress(startAddr);
        startAddr += nodeBean.getStructureHead().getLength();
        List<BytesInfo> nodeContent = nodeBean.getNodeContent();
        for (BytesInfo bytesInfo : nodeContent) {

            bytesInfo.setStartAddr(startAddr);
            startAddr += bytesInfo.getBytes();
        }
        List<BytesInfo> childNodeAddr = nodeBean.getChildNodeAddr();
        for (BytesInfo bytesInfo : childNodeAddr) {
            bytesInfo.setStartAddr(startAddr);
            startAddr += bytesInfo.getBytes();
        }
        List<NodeBean> childNodeContent = nodeBean.getChildNodeContent();
        for (NodeBean bean : childNodeContent) {
            startAddr = suppStarAddr(bean, startAddr);
        }
        return startAddr;
    }
    public static void suppChildAddr(NodeBean nodeBean){
        List<NodeBean> childNodeContent = nodeBean.getChildNodeContent();
        if (childNodeContent.size() > 0) {
            for (int index = 0; index < childNodeContent.size(); index++) {
                Integer startAddr = childNodeContent.get(index).getStructureHead().getSize().getStartAddr();
                String name = childNodeContent.get(index).getStructureHead().getName().getComment();
                nodeBean.getChildNodeAddr().get(index).setComment(name);
                nodeBean.getChildNodeAddr().get(index).setValue(StringUtil.decToHex(String.valueOf(startAddr), 8));
            }
        }
        for (NodeBean bean : childNodeContent) {
            suppChildAddr(bean);
        }

    }
}
