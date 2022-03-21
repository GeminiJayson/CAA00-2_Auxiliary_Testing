package vip.jayson.util;
import java.math.BigInteger;
public class StringUtil {
    /**
     * 去掉首尾指定字符串
     * @param source 原字符串
     * @param element 指定去除字符
     * @return
     */
    public static String trimChar(String source,char element){

        boolean beginIndexFlag = true;

        boolean endIndexFlag = true;

        do{

            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;

            int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();

            source = source.substring(beginIndex, endIndex);

            beginIndexFlag = (source.indexOf(element) == 0);

            endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());

        } while (beginIndexFlag || endIndexFlag);

        return source;

    }
    /**
     * 判断是否为绝对路径
     * @param path
     * @return
     */
    public static boolean judgePathAbsolute(String path){
        return path.indexOf(":") > 0;
    }



    /**
     * 十六进制字符串转二进制字符串
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    public static String hexStrToBinStr(String hexString){
        //判空
        if(hexString == null || hexString.length() == 0) {
            return null;
        }
        if (hexString.contains("0x")) {
            String lowerString = hexString.toLowerCase();
            hexString = lowerString.substring(lowerString.indexOf("x") + 1,lowerString.length());
        }
        //合法性校验
        if(!hexString.matches("[a-fA-F0-9]*") || hexString.length() % 2 != 0) {
            return null;
        }
        //计算
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;

    }
    /**
     * 2进制字符串转16进制字符串
     * @param bString 2进制字符串
     * @return 字节数组
     */
    public static String binStrTohexStr(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
        {return null;}
        //合法性校验
        if(!bString.matches("[0-1]*")) {
            return null;
        }
        StringBuffer tmp=new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }
    /**
     * 10进制字符串转指定位数2进制字符串
     */
    public static String decToBinStr(Integer decNum , Integer size) {
        StringBuilder binStr = new StringBuilder();
        for(int i= size-1;i>=0;i--) {
            binStr.append((decNum >> i) & 1);
        }
        return binStr.toString();
    }
    /**
     * 2进制字符串转10进制字符串
     * @param bString 2进制字符串
     * @return 字节数组
     */
    public static String binStrToDecStr(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
        {return null;}
        //合法性校验
        if(!bString.matches("[0-1]*")) {
            return null;
        }
        StringBuffer tmp=new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        String hexString = tmp.toString();
        return hexToDec(hexString);
    }

    /**
     * 字符串转化成为16进制字符串
     */
    public static String strToHex(String s, Integer size) {
        String hexStr = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            hexStr = hexStr + s4;
        }
        return addAfterZore(hexStr, size);
    }
    /**
     * 16进制直接转换成为字符串
     */
    public static String hexToStr(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 10字符串转为指定位数16进制字符串
     */
    public static String decToHex(String dec, Integer size) {
        if (dec.contains("0x")) {
            dec = hexToDec(dec.split("0x")[1]);
        }
        String hexStr = Integer.toHexString(Integer.parseInt(dec));
        hexStr = addFrontZore(hexStr, size);
        return hexStr;
    }
    /**
     * 16进制字符串转为10进制字符串
     */
    public static String hexToDec(String hex) {
        return String.valueOf(new BigInteger(hex, 16));
    }

    /**
     * 向前补充指定位数0
     */
    public static String addFrontZore(String str, int size){
        if (str.length()<size){
            str= "0"+str;
            str=addFrontZore(str,size);
            return str;
        }else {
            return str;
        }
    }
    /**
     * 向后补充指定位数0
     */
    public static String addAfterZore(String str, int size){
        if (str.length()<size){
            str= str + "0";
            str=addAfterZore(str,size);
            return str;
        }else {
            return str;
        }
    }




}
