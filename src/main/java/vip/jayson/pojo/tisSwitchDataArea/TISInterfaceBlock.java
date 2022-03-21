package vip.jayson.pojo.tisSwitchDataArea;

import java.util.LinkedHashMap;
import java.util.List;

public class TISInterfaceBlock {
    public static LinkedHashMap<String, LinkedHashMap<String, List<String>>> tisInfo = new LinkedHashMap<>();
    public static void init() {
        tisInfo.put("OC", new LinkedHashMap<>());
        tisInfo.put("TCC4", new LinkedHashMap<>());
        tisInfo.put("CBI3", new LinkedHashMap<>());
    }
}
