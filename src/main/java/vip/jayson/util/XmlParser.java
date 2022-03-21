package vip.jayson.util;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import vip.jayson.main.RunTool;
import vip.jayson.pojo.dataBean.XMLBean;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlParser {
    public static Map<String, XMLBean> allElementDic = new HashMap<>();


    public static void readXML() {
        try {
            String filePath = RunTool.projectRootPath + "\\Config.xml";
            File f = new File(filePath);
            if (!f.exists()) {
                System.out.println("  Error : Config file doesn't exist!");
                System.exit(1);
            }
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            allElementDic.put(root.getName(), getAllNode(root));
        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
        }

    }

    public static XMLBean getAllNode(Element element){
        XMLBean xmlNodeBean = new XMLBean();
        List<Attribute> attributes = element.attributes();
        Map<String, String> attrMap = new HashMap<>();
        if (element.attributes().size()>0) {
            for (Attribute attribute : attributes) {
                String name = attribute.getName();
                String value = attribute.getValue();
                attrMap.put(name, value);
            }
            xmlNodeBean.setAttrs(attrMap);
        }
        if (element.elements().size() == 0) {
            xmlNodeBean.setText(element.getText());
        } else {
            for (Iterator i = element.elementIterator(); i.hasNext();) {
                Element el = (Element) i.next();
                XMLBean childNodeBean = getAllNode(el);
                xmlNodeBean.getChildNode().put(el.getName(), childNodeBean);
            }
        }
        return xmlNodeBean;
    }


}
