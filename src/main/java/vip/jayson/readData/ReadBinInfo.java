package vip.jayson.readData;

import vip.jayson.util.StringUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReadBinInfo {
    public static List<String> readBinInfo(String filePath) {
        List<String> result = new ArrayList<>();
        File file = new File(filePath);
        try {
            InputStream fileIn = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(fileIn);
            byte[] buffer = new byte[1];
            while ((in.read(buffer)) != -1) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                result.add(StringUtil.decToHex(Byte.toUnsignedInt(byteBuffer.get()) + "", 2).toUpperCase());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    public static String getSpecifiedLengthBin(List<String> binList, Integer start, Integer length) {
        StringBuilder result = new StringBuilder();
        List<String> subList = binList.subList(start, start + length);
        for (String bytes : subList) {
            result.append(bytes);
        }
        return result.toString().toLowerCase(Locale.ROOT);
    }
}
