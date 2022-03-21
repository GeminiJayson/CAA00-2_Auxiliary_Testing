package vip.jayson.pojo.dataBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ConfigBean {
    private LinkedHashMap<String, String> global;
    private LinkedHashMap<String, String> publicSection;
    private List<LinkedHashMap<String, String>> station;

    public ConfigBean() {
        this.station = new ArrayList<>();
    }

    public ConfigBean(LinkedHashMap<String, String> global) {
        this.global = global;
    }

    public ConfigBean(LinkedHashMap<String, String> global, LinkedHashMap<String, String> publicSection, List<LinkedHashMap<String, String>> station) {
        this.global = global;
        this.publicSection = publicSection;
        this.station = station;
    }

    public LinkedHashMap<String, String> getGlobal() {
        return global;
    }

    public void setGlobal(LinkedHashMap<String, String> global) {
        this.global = global;
    }

    public List<LinkedHashMap<String, String>> getStation() {
        return station;
    }

    public void setStation(List<LinkedHashMap<String, String>> station) {
        this.station = station;
    }

    public LinkedHashMap<String, String> getPublicSection() {
        return publicSection;
    }

    public void setPublicSection(LinkedHashMap<String, String> publicSection) {
        this.publicSection = publicSection;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "global=" + global +
                ", publicSection=" + publicSection +
                ", station=" + station +
                '}';
    }
}
