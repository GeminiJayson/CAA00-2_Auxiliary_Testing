package vip.jayson.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import vip.jayson.config.CAAData;
import vip.jayson.main.RunTool;
import vip.jayson.pojo.dataBean.CodeAssignBean;
import vip.jayson.pojo.dataBean.CodeInfoBean;
import vip.jayson.pojo.dataBean.CompareInfoBean;
import vip.jayson.pojo.fileBean.BytesInfo;

import java.io.*;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.*;

public class ExcelParser {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    private static final String[] ADS_CONTENT_TITLE = {"起始地址/DEC", "起始地址/HEX", "字长", "结束地址/DEC", "结束地址/HEX", "字段名称-Name", "字段值类型-Type", "十进制值/DEC", "十六进制值/HEX", "备注"};
    private static final String[] CODE_NUMBER_ASSIGN = {"变量类型名称", "变量类型值/HEX", "变量名称", "编号/HEX", "码组分配下标/DEC", "码组真值/HEX", "码组假值/HEX"};
    private static final String EXCEL_FILE_PATH = "./Help.xls";
    private static final String ADS_EXCEL_FILE_PATH = "./TestCase/CAA200_2 Output File Comparison File.xlsx";
    private static final String EXCEL_NEW_FILE_PATH = "./CAA200_2 Output File Comparison File-New.xlsx";

    public static void writeADSInfo(String stationName, List<BytesInfo> dataList, SXSSFWorkbook workbook, XSSFCellStyle hssfCellStyle) throws IOException {
        //创建工作表sheet

        SXSSFSheet sheet = workbook.createSheet(stationName + " ADS BIN");
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //创建第一行
        SXSSFRow row = sheet.createRow(0);
        SXSSFCell cell = null;
        //插入第一行的表头
        for (int i = 0; i < ADS_CONTENT_TITLE.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(hssfCellStyle);
            cell.setCellValue(ADS_CONTENT_TITLE[i]);
        }
        for (int i = 1; i < dataList.size() + 1; i++) {
            BytesInfo rowInfo = dataList.get(i - 1);
            Integer bytes = rowInfo.getBytes();
            Integer beginAddr = rowInfo.getStartAddr();
            Integer endAddr = beginAddr + bytes - 1;
            String name = rowInfo.getName();
            String type = rowInfo.getType();
            String value = rowInfo.getValue();
            String comment = rowInfo.getComment();
            SXSSFRow nrow = sheet.createRow(i);
            SXSSFCell ncell = nrow.createCell(0);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(beginAddr);
            ncell = nrow.createCell(1);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(StringUtil.decToHex(String.valueOf(beginAddr), 8));
            ncell = nrow.createCell(2);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(bytes);
            ncell = nrow.createCell(3);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(endAddr);
            ncell = nrow.createCell(4);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(StringUtil.decToHex(String.valueOf(endAddr), 8));
            ncell = nrow.createCell(5);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(name);
            ncell = nrow.createCell(6);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(type);
            if (value == null) {
                value = "00000000";
            }
            if (value == "RBC1SEG") {
                value = "00000000";
            }
            ncell = nrow.createCell(7);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(StringUtil.hexToDec(value));
            ncell = nrow.createCell(8);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(value);
            ncell = nrow.createCell(9);
            ncell.setCellStyle(hssfCellStyle);
            ncell.setCellValue(comment);
        }
    }

    public static void writeCodeAndNumberInfo(Map<String, List<CodeAssignBean>> codeAssignList, HSSFWorkbook workbook, HSSFCellStyle hssfcellstyle) {

        //创建工作表sheet
        for (String stationName : codeAssignList.keySet()) {
            HSSFSheet sheet = workbook.createSheet(stationName + " 码组与编号分配");
            sheet.setDefaultColumnWidth(15);
            //创建第一行
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = null;
            //插入第一行的表头
            for (int i = 0; i < CODE_NUMBER_ASSIGN.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(hssfcellstyle);
                cell.setCellValue(CODE_NUMBER_ASSIGN[i]);
            }
            int index = 1;
            List<CodeAssignBean> codeAssignBeans = codeAssignList.get(stationName);
            for (CodeAssignBean codeAssignBean : codeAssignBeans) {
                String sectionName = codeAssignBean.getSectionName();
                for (CodeInfoBean codeInfoBean : codeAssignBean.getCodeInfoList()) {
                    HSSFRow nrow = sheet.createRow(index);
                    HSSFCell ncell = nrow.createCell(0);
                    ncell.setCellStyle(hssfcellstyle);
                    ncell.setCellValue(sectionName);
                    ncell = nrow.createCell(1);
                    ncell.setCellStyle(hssfcellstyle);
                    ncell.setCellValue(codeInfoBean.getCodeType());
                    ncell = nrow.createCell(2);
                    ncell.setCellStyle(hssfcellstyle);
                    ncell.setCellValue(codeInfoBean.getCodeName());
                    ncell = nrow.createCell(3);
                    ncell.setCellStyle(hssfcellstyle);
                    ncell.setCellValue(codeInfoBean.getCodeNum());
                    ncell = nrow.createCell(4);
                    ncell.setCellStyle(hssfcellstyle);
                    ncell.setCellValue(codeInfoBean.getCodeAssignIndex());
                    ncell = nrow.createCell(5);
                    ncell.setCellStyle(hssfcellstyle);
                    ncell.setCellValue(codeInfoBean.getCodeTrue());
                    ncell = nrow.createCell(6);
                    ncell.setCellStyle(hssfcellstyle);
                    ncell.setCellValue(codeInfoBean.getCodeFalse());
                    index++;
                }
            }

        }
    }

    public static void writeLKCodeAndNumberInfo(Map<String, List<CodeInfoBean>> codeAssignList, HSSFWorkbook workbook, HSSFCellStyle hssfCellStyle){

        //创建工作表sheet
        for (String stationName : codeAssignList.keySet()) {
            HSSFSheet sheet = workbook.createSheet(stationName + " LK码组与编号分配");
            sheet.setDefaultColumnWidth(15);
            //创建第一行
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = null;
            //插入第一行的表头
            for (int i = 0; i < CODE_NUMBER_ASSIGN.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(hssfCellStyle);
                cell.setCellValue(CODE_NUMBER_ASSIGN[i]);
            }
            int index = 1;
            String sectionName = "LK";
            List<CodeInfoBean> codeInfoBeans = codeAssignList.get(stationName);
            for (CodeInfoBean codeInfoBean : codeInfoBeans) {
                HSSFRow nrow = sheet.createRow(index);
                HSSFCell ncell = nrow.createCell(0);
                ncell.setCellStyle(hssfCellStyle);
                ncell.setCellValue(sectionName);
                ncell = nrow.createCell(1);
                ncell.setCellStyle(hssfCellStyle);
                ncell.setCellValue(codeInfoBean.getCodeType());
                ncell = nrow.createCell(2);
                ncell.setCellStyle(hssfCellStyle);
                ncell.setCellValue(codeInfoBean.getCodeName());
                ncell = nrow.createCell(3);
                ncell.setCellStyle(hssfCellStyle);
                ncell.setCellValue(codeInfoBean.getCodeNum());
                ncell = nrow.createCell(4);
                ncell.setCellStyle(hssfCellStyle);
                ncell.setCellValue(codeInfoBean.getCodeAssignIndex());
                ncell = nrow.createCell(5);
                ncell.setCellStyle(hssfCellStyle);
                ncell.setCellValue(codeInfoBean.getCodeTrue());
                ncell = nrow.createCell(6);
                ncell.setCellStyle(hssfCellStyle);
                ncell.setCellValue(codeInfoBean.getCodeFalse());
                index++;
            }
        }

    }

    public static void writeCodeAndNumberHelpInfo(Map<String, List<CodeAssignBean>> codeAssignList, HSSFWorkbook workbook, HSSFCellStyle hssfcellstyle) {

        //创建工作表sheet
        for (String stationName : codeAssignList.keySet()) {
            HSSFSheet sheet1 = workbook.createSheet(stationName + " 码组与编号分配辅助信息");

            sheet1.setDefaultColumnWidth(15);

            int index = 1;
            List<CodeAssignBean> codeAssignBeans = codeAssignList.get(stationName);
            for (CodeAssignBean codeAssignBean : codeAssignBeans) {
                String sectionName = codeAssignBean.getSectionName();
                for (CodeInfoBean codeInfoBean : codeAssignBean.getCodeInfoList()) {
                    HSSFRow nrow1 = sheet1.createRow((index - 1) * 6);
                    HSSFCell ncell1 = nrow1.createCell(0);
                    ncell1.setCellStyle(hssfcellstyle);
                    ncell1.setCellValue(sectionName);
                    ncell1 = nrow1.createCell(1);
                    ncell1.setCellStyle(hssfcellstyle);
                    ncell1.setCellValue("Code:" + codeInfoBean.getCodeName());
                    ncell1 = nrow1.createCell(2);
                    ncell1.setCellStyle(hssfcellstyle);
                    ncell1.setCellValue(StringUtil.hexToDec(codeInfoBean.getCodeNum()));
                    HSSFRow nrow2 = sheet1.createRow((index - 1) * 6 + 1);
                    HSSFCell ncell2 = nrow2.createCell(0);
                    ncell2.setCellStyle(hssfcellstyle);
                    ncell2.setCellValue(sectionName);
                    ncell2 = nrow2.createCell(1);
                    ncell2.setCellStyle(hssfcellstyle);
                    ncell2.setCellValue("Value");
                    ncell2 = nrow2.createCell(2);
                    ncell2.setCellStyle(hssfcellstyle);
                    ncell2.setCellValue(0);
                    HSSFRow nrow3 = sheet1.createRow((index - 1) * 6 + 2);
                    HSSFCell ncell3 = nrow3.createCell(0);
                    ncell3.setCellStyle(hssfcellstyle);
                    ncell3.setCellValue(sectionName);
                    ncell3 = nrow3.createCell(1);
                    ncell3.setCellStyle(hssfcellstyle);
                    ncell3.setCellValue("True_Value");
                    ncell3 = nrow3.createCell(2);
                    ncell3.setCellStyle(hssfcellstyle);
                    ncell3.setCellValue(StringUtil.hexToDec(codeInfoBean.getCodeTrue()));
                    HSSFRow nrow4 = sheet1.createRow((index - 1) * 6 + 3);
                    HSSFCell ncell4 = nrow4.createCell(0);
                    ncell4.setCellStyle(hssfcellstyle);
                    ncell4.setCellValue(sectionName);
                    ncell4 = nrow4.createCell(1);
                    ncell4.setCellStyle(hssfcellstyle);
                    ncell4.setCellValue("FAlse_Value");
                    ncell4 = nrow4.createCell(2);
                    ncell4.setCellStyle(hssfcellstyle);
                    ncell4.setCellValue(StringUtil.hexToDec(codeInfoBean.getCodeFalse()));
                    HSSFRow nrow5 = sheet1.createRow((index - 1) * 6 + 4);
                    HSSFCell ncell5 = nrow5.createCell(0);
                    ncell5.setCellStyle(hssfcellstyle);
                    ncell5.setCellValue(sectionName);
                    ncell5 = nrow5.createCell(1);
                    ncell5.setCellStyle(hssfcellstyle);
                    ncell5.setCellValue("Tick");
                    ncell5 = nrow5.createCell(2);
                    ncell5.setCellStyle(hssfcellstyle);
                    ncell5.setCellValue(0);
                    HSSFRow nrow6 = sheet1.createRow((index - 1) * 6 + 5);
                    HSSFCell ncell6 = nrow6.createCell(0);
                    ncell6.setCellStyle(hssfcellstyle);
                    ncell6.setCellValue(sectionName);
                    ncell6 = nrow6.createCell(1);
                    ncell6.setCellStyle(hssfcellstyle);
                    ncell6.setCellValue("Name_Index");
                    ncell6 = nrow6.createCell(2);
                    ncell6.setCellStyle(hssfcellstyle);
                    ncell6.setCellValue(0);
                    index++;
                }
            }

        }
    }

    public static void writeLKCodeAndNumberHelpInfo(Map<String, List<CodeInfoBean>> codeAssignList, HSSFWorkbook workbook, HSSFCellStyle hssfCellStyle) {
        //创建工作表sheet
        for (String stationName : codeAssignList.keySet()) {
            HSSFSheet sheet1 = workbook.createSheet(stationName + " LK码组与编号分配辅助信息");
            sheet1.setDefaultColumnWidth(15);
            int index = 1;
            List<CodeInfoBean> codeInfoBeans = codeAssignList.get(stationName);
            String sectionName = "LK";
            for (CodeInfoBean codeInfoBean : codeInfoBeans) {
                HSSFRow nrow1 = sheet1.createRow((index - 1) * 6);
                HSSFCell ncell1 = nrow1.createCell(0);
                ncell1.setCellStyle(hssfCellStyle);
                ncell1.setCellValue(sectionName);
                ncell1 = nrow1.createCell(1);
                ncell1.setCellStyle(hssfCellStyle);
                ncell1.setCellValue("Code:" + codeInfoBean.getCodeName());
                ncell1 = nrow1.createCell(2);
                ncell1.setCellStyle(hssfCellStyle);
                ncell1.setCellValue(StringUtil.hexToDec(codeInfoBean.getCodeNum()));
                HSSFRow nrow2 = sheet1.createRow((index - 1) * 6 + 1);
                HSSFCell ncell2 = nrow2.createCell(0);
                ncell2.setCellStyle(hssfCellStyle);
                ncell2.setCellValue(sectionName);
                ncell2 = nrow2.createCell(1);
                ncell2.setCellStyle(hssfCellStyle);
                ncell2.setCellValue("Value");
                ncell2 = nrow2.createCell(2);
                ncell2.setCellStyle(hssfCellStyle);
                ncell2.setCellValue(0);
                HSSFRow nrow3 = sheet1.createRow((index - 1) * 6 + 2);
                HSSFCell ncell3 = nrow3.createCell(0);
                ncell3.setCellStyle(hssfCellStyle);
                ncell3.setCellValue(sectionName);
                ncell3 = nrow3.createCell(1);
                ncell3.setCellStyle(hssfCellStyle);
                ncell3.setCellValue("True_Value");
                ncell3 = nrow3.createCell(2);
                ncell3.setCellStyle(hssfCellStyle);
                ncell3.setCellValue(StringUtil.hexToDec(codeInfoBean.getCodeTrue()));
                HSSFRow nrow4 = sheet1.createRow((index - 1) * 6 + 3);
                HSSFCell ncell4 = nrow4.createCell(0);
                ncell4.setCellStyle(hssfCellStyle);
                ncell4.setCellValue(sectionName);
                ncell4 = nrow4.createCell(1);
                ncell4.setCellStyle(hssfCellStyle);
                ncell4.setCellValue("FAlse_Value");
                ncell4 = nrow4.createCell(2);
                ncell4.setCellStyle(hssfCellStyle);
                ncell4.setCellValue(StringUtil.hexToDec(codeInfoBean.getCodeFalse()));
                HSSFRow nrow5 = sheet1.createRow((index - 1) * 6 + 4);
                HSSFCell ncell5 = nrow5.createCell(0);
                ncell5.setCellStyle(hssfCellStyle);
                ncell5.setCellValue(sectionName);
                ncell5 = nrow5.createCell(1);
                ncell5.setCellStyle(hssfCellStyle);
                ncell5.setCellValue("Tick");
                ncell5 = nrow5.createCell(2);
                ncell5.setCellStyle(hssfCellStyle);
                ncell5.setCellValue(0);
                HSSFRow nrow6 = sheet1.createRow((index - 1) * 6 + 5);
                HSSFCell ncell6 = nrow6.createCell(0);
                ncell6.setCellStyle(hssfCellStyle);
                ncell6.setCellValue(sectionName);
                ncell6 = nrow6.createCell(1);
                ncell6.setCellStyle(hssfCellStyle);
                ncell6.setCellValue("Name_Index");
                ncell6 = nrow6.createCell(2);
                ncell6.setCellStyle(hssfCellStyle);
                ncell6.setCellValue(0);
                index++;
            }

        }
    }

    public static void writeBoolInfo(Map<String, List<CodeAssignBean>> codeAssignList, HSSFWorkbook workbook, HSSFCellStyle hssfCellStyle) {

        //创建工作表sheet
        for (String stationName : codeAssignList.keySet()) {
            HSSFSheet sheet2 = workbook.createSheet(stationName + " 布尔表达式VTL段-表达式辅助信息");
            sheet2.setDefaultColumnWidth(15);
            int index = 1;
            List<CodeAssignBean> codeAssignBeans = codeAssignList.get(stationName);
            for (CodeAssignBean codeAssignBean : codeAssignBeans) {
                String sectionName = codeAssignBean.getSectionName();
                for (CodeInfoBean codeInfoBean : codeAssignBean.getCodeInfoList()) {
                    if (sectionName.equals("GSV")) {
                        HSSFRow nrow21 = sheet2.createRow((index - 1) * 3);
                        HSSFCell ncell21 = nrow21.createCell(0);
                        ncell21.setCellStyle(hssfCellStyle);
                        ncell21.setCellValue("Type");
                        ncell21 = nrow21.createCell(1);
                        ncell21.setCellStyle(hssfCellStyle);
                        ncell21.setCellValue(0);
                        ncell21 = nrow21.createCell(2);
                        ncell21.setCellStyle(hssfCellStyle);
                        ncell21.setCellValue("Size: " + codeInfoBean.getCodeName());
                        ncell21 = nrow21.createCell(3);
                        ncell21.setCellStyle(hssfCellStyle);
                        ncell21.setCellValue(1);
                        HSSFRow nrow22 = sheet2.createRow((index - 1) * 3 + 1);
                        HSSFCell ncell22 = nrow22.createCell(0);
                        ncell22.setCellStyle(hssfCellStyle);
                        ncell22.setCellValue("Item: " + codeInfoBean.getCodeName());
                        ncell22 = nrow22.createCell(1);
                        ncell22.setCellStyle(hssfCellStyle);
                        ncell22.setCellValue(StringUtil.hexToDec(codeInfoBean.getCodeNum()));
                        ncell22 = nrow22.createCell(2);
                        ncell22.setCellStyle(hssfCellStyle);
                        ncell22.setCellValue("Count：1th item");
                        ncell22 = nrow22.createCell(3);
                        ncell22.setCellStyle(hssfCellStyle);
                        ncell22.setCellValue(1);
                        HSSFRow nrow23 = sheet2.createRow((index - 1) * 3 + 2);
                        HSSFCell ncell23 = nrow23.createCell(0);
                        ncell23.setCellStyle(hssfCellStyle);
                        ncell23.setCellValue("Offset");
                        ncell23 = nrow23.createCell(1);
                        ncell23.setCellStyle(hssfCellStyle);
                        ncell23.setCellValue(11056 + 80 + (index - 3) * 12);
                        ncell23 = nrow23.createCell(2);
                        ncell23.setCellStyle(hssfCellStyle);
                        ncell23.setCellValue("VarID：PERM0");
                        ncell23 = nrow23.createCell(3);
                        ncell23.setCellStyle(hssfCellStyle);
                        ncell23.setCellValue("50595624");
                    }
                    index++;
                }
            }

        }
    }
    public static void writeHelpInfo(){
        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle HSSFCellStyle = setStyle(workbook);
        if (CAAData.ifGenerateLK) {
            writeLKCodeAndNumberInfo(CAAData.lkInputVarsInfo, workbook, HSSFCellStyle);
            writeLKCodeAndNumberHelpInfo(CAAData.lkInputVarsInfo, workbook, HSSFCellStyle);
        }
        writeCodeAndNumberInfo(CAAData.codeAssignList, workbook, HSSFCellStyle);
        writeCodeAndNumberHelpInfo(CAAData.codeAssignList, workbook, HSSFCellStyle);
        writeBoolInfo(CAAData.codeAssignList, workbook, HSSFCellStyle);
        //创建excel文件
        File file = new File(EXCEL_FILE_PATH);
        try {
            //将excel写入
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public static void writeADSFile(Map<String, List<BytesInfo>> stationAllInfo){
        //创建excel工作簿
        SXSSFWorkbook workbook = new SXSSFWorkbook (100);
        XSSFCellStyle xSSFCellStyle = setStyle(workbook);
        for (String stationName : stationAllInfo.keySet()) {
            try {
                writeADSInfo(stationName, stationAllInfo.get(stationName), workbook, xSSFCellStyle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //创建excel文件
        File file = new File(ADS_EXCEL_FILE_PATH);
        try {
            //将excel写入
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }




    public static HSSFCellStyle setStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        HSSFFont font = workbook.createFont();
        font.setFontName("思源黑体 CN NORMAL");
        font.setFontHeightInPoints((short) 11);//设置字体大小
        cellStyle.setFont(font);//选择需要用到的字体格式
        cellStyle.setWrapText(true);//设置自动换行
        return cellStyle;
    }

    public static XSSFCellStyle setStyle(SXSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontName("思源黑体 CN NORMAL");
        font.setFontHeightInPoints((short) 11);//设置字体大小
        cellStyle.setFont(font);//选择需要用到的字体格式
        cellStyle.setWrapText(true);//设置自动换行
        return cellStyle;
    }

    public static List<CompareInfoBean> readCAA200ExcelInfo(String sheetName){
        List<CompareInfoBean> result = new ArrayList<>();
        String filePath = RunTool.projectRootPath + "\\" + RunTool.testCase + "\\" + RunTool.caaExcelPath;
        sheetName += " BIN";
        InputStream inputStream = null;
        HSSFWorkbook workbook = null;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new HSSFWorkbook(inputStream);
            HSSFSheet hssfSheet = workbook.getSheet(sheetName);
            for(int i = 1;i<hssfSheet.getLastRowNum();i++){
                HSSFRow row  = hssfSheet.getRow(i);
                Integer start = Integer.parseInt(getCellContent(row.getCell(1)));
                Integer length = Integer.parseInt(getCellContent(row.getCell(3)));
                String value = getCellContent(row.getCell(9)).split("0x")[1].toUpperCase(Locale.ROOT);
                result.add(new CompareInfoBean(start, length, value));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<CompareInfoBean> readBINExcelInfo(String sheetName){
        List<CompareInfoBean> result = new ArrayList<>();
        String filePath = RunTool.projectRootPath + "\\" + RunTool.excelPath;
        InputStream inputStream = null;
        XSSFWorkbook workbook = null;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream);
            XSSFSheet hssfSheet = workbook.getSheet(sheetName);
            for(int i = 1; i<hssfSheet.getLastRowNum();i++){
                XSSFRow row  = hssfSheet.getRow(i);
                Integer start = Integer.parseInt(getCellContent(row.getCell(0)));
                Integer length = Integer.parseInt(getCellContent(row.getCell(2)));
                String value = getCellContent(row.getCell(8)).toLowerCase(Locale.ROOT);
                result.add(new CompareInfoBean(start, length, value));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getCellContent(HSSFCell cell ) {
        String cellValue = "null";
        if (cell != null) {
            switch (cell.getCellType()) {                     // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
                case NUMERIC:
                    cellValue = String.valueOf((int) cell.getNumericCellValue()).split("\\.")[0];
                    break;
                case FORMULA:
                    try {
                        cellValue = String.valueOf(cell.getNumericCellValue()).split("\\.")[0];;
                    } catch (IllegalStateException e) {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case BLANK:
                    cellValue = "";
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case ERROR:
                    cellValue = String.valueOf(cell.getErrorCellValue());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + cell.getCellType());
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
    public static String getCellContent(XSSFCell cell ) {
        String cellValue = "null";
        if (cell != null) {
            switch (cell.getCellType()) {                     // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
                case NUMERIC:
                    cellValue = String.valueOf((int) cell.getNumericCellValue()).split("\\.")[0];
                    break;
                case FORMULA:
                    try {
                        cellValue = String.valueOf(cell.getNumericCellValue()).split("\\.")[0];;
                    } catch (IllegalStateException e) {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case BLANK:
                    cellValue = "";
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case ERROR:
                    cellValue = String.valueOf(cell.getErrorCellValue());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + cell.getCellType());
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}
