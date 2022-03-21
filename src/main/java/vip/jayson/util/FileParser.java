package vip.jayson.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileParser {


    /**
     * 去除文件中的注释，以";"或"#"开头，顺便去除UTF-8等文件的BOM头
     *
     * @param source
     * @return
     */
    public static String removeComments(String source) {
        String result = source;

        if (result.contains(";")) {
            result = result.substring(0, result.indexOf(";"));
        }

        if (result.contains("#")) {
            result = result.substring(0, result.indexOf("#"));
        }

        if (result.contains("***")) {
            result = result.substring(0, result.indexOf("***"));
        }
        if (result.contains("//")) {
            result = result.substring(0, result.indexOf("//"));
        }
        String trim = result.trim();
        if ("".equals(trim)) {
            return null;
        } else {
            return result.trim();
        }
    }


    /**
     * 判断是否是数字类型
     *
     * @param str
     * @return 如果为空返回false  匹配返回true
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取该路径下所有文件路径
     *
     * @param file
     */
    public static List<String> traverse(File file) {
        List<String> resultList = new ArrayList<>();
        File[] files = file.listFiles();
        if (files == null) {
            return resultList;
        }
        for (File file1 : files) {
            if (file1.isFile()) {
                resultList.add(file1.getPath());
            } else {
                resultList.addAll(traverse(file1));
            }
        }
        return resultList;
    }


}
