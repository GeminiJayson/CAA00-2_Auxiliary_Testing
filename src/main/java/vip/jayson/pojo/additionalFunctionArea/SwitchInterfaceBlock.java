package vip.jayson.pojo.additionalFunctionArea;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SwitchInterfaceBlock {
    public static LinkedHashMap<String, LinkedHashMap<String, List<String>>> switchInfo = new LinkedHashMap<>();
    public static void init() {
        switchInfo.put("OC", new LinkedHashMap<>());
        switchInfo.put("TCC4", new LinkedHashMap<>());
        switchInfo.put("RBC1", new LinkedHashMap<>());
        switchInfo.put("RBC2", new LinkedHashMap<>());
        switchInfo.put("RBC3", new LinkedHashMap<>());
        switchInfo.put("CBI3", new LinkedHashMap<>());
        switchInfo.put("KDAC", new LinkedHashMap<>());
        switchInfo.put("RBC4", new LinkedHashMap<>());
        switchInfo.put("HLCI", new LinkedHashMap<>());
    }
}
