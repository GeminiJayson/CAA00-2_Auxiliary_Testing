package vip.jayson.pojo.dataBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLBean {
    private Map<String, String> attrs;
    private String text;
    private Map<String, XMLBean> childNode;

    public XMLBean() {
        childNode = new HashMap<>();
    }

    public XMLBean(Map<String, String> attrs, String text, Map<String, XMLBean> childNode) {
        this.attrs = attrs;
        this.text = text;
        this.childNode = childNode;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, XMLBean> getChildNode() {
        return childNode;
    }

    public void setChildNode(Map<String, XMLBean> childNode) {
        this.childNode = childNode;
    }
}
