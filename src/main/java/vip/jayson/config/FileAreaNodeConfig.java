package vip.jayson.config;

import vip.jayson.pojo.fileBean.BytesInfo;
import vip.jayson.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class FileAreaNodeConfig {
    private List<BytesInfo> contentList;
    private  Integer length;

    public List<BytesInfo> getContentList() {
        return contentList;
    }

    public void setContentList(List<BytesInfo> contentList) {
        this.contentList = contentList;
    }

    public Integer getLength() {
        length = 268;
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public FileAreaNodeConfig(String stationName, int stationNum, StringBuilder stationNmaes, List<String> stationNumList) {
        BytesInfo stationId = new BytesInfo("UNIT", 4, "Station_ID", stationName.equals("MAIN")?"00000000": StringUtil.decToHex(stationName, 8));
        BytesInfo stationIndex = new BytesInfo("UNIT", 4, "Station_Index", StringUtil.decToHex(String.valueOf(stationNum), 8));
        BytesInfo stationCount = new BytesInfo("UNIT", 4, "Station_Count", stationName.equals("MAIN")?StringUtil.decToHex(String.valueOf(stationNumList.size() - 1), 8):StringUtil.decToHex("0", 8));
        String stationNames = "";
        if (stationName.equals("MAIN")) {
            for (String station : stationNumList) {
                if (!station.equals("MAIN")) {
                    stationNames = stationNames + StringUtil.decToHex(station, 8);
                }
            }
            stationNames = StringUtil.addAfterZore(stationNames, 256 * 2);
        } else {
            stationNames = StringUtil.addAfterZore("0", 256 * 2);
        }

        BytesInfo stations = new BytesInfo("UNIT", 256, "Stations", stationNames);
        contentList = new ArrayList<>();
        contentList.add(stationId);
        contentList.add(stationIndex);
        contentList.add(stationCount);
        contentList.add(stations);
    }


}
