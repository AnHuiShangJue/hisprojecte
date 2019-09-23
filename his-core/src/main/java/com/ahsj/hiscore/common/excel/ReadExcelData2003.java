/******************************************************************************
 * 
 * 版权所有：安徽驭鹏轮胎有限公司
 * 
 ******************************************************************************
 * 注意：本内容仅限于安徽驭鹏轮胎有限公司内部使用，禁止转发
 *****************************************************************************/
package com.ahsj.hiscore.common.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectInfoController
 * <p>
 * Date:     2019/8/17 13:17
 *
 * @author XJP
 * @create 2019/8/17
 * @since 1.0.0
 */
public class ReadExcelData2003 {

    /**
     * 开始从excel读取数据
     * 
     * @param workBook 工作簿对象
     * @param clazz 支持多个sheet与多个对象映射
     * 
     **/
    public static List<Map> readExcelData(Workbook workBook, Class ... clazz) {
        return readExcelData(workBook,-1,clazz);
    }
    
    /**
     * 开始从excel读取数据
      * @param workBook 工作簿对象
     * @param clazz 支持多个sheet与多个对象映射
     * @param needCheckIndex 需要check的列
     **/
    public static List<Map> readExcelData(Workbook workBook, int needCheckIndex, Class ... clazz) {
        HSSFWorkbook book = (HSSFWorkbook) workBook;
        int sheetSize = book.getNumberOfSheets();
        List<Map> workBookData = new ArrayList<Map>();
        for (int i = 0; i < sheetSize; i++) {
            if(sheetSize==clazz.length){
            HSSFSheet sheet = book.getSheetAt(i);
            List<List<String>> dataList = readSheetColumnData(clazz[i], sheet);
            Map<String, Object> map = ExcelImportAnalysizer.excelCheck(clazz[i],
                    dataList,needCheckIndex);
            workBookData.add(map);
            }else{
                throw new RuntimeException("sheet数量与class长度不一致！");
            }
        }
        return workBookData;
    }


    /**
     * 开始从excel读取数据
     * @param workBook 工作簿对象
     * @param clazz 支持多个sheet与多个对象映射
     * @param needCheckIndex 需要check的列
     **/
    public static List<Map> readExcelDataMany(Workbook workBook, int needCheckIndex, Class ... clazz) {
        HSSFWorkbook book = (HSSFWorkbook) workBook;
        int sheetSize = book.getNumberOfSheets();
        List<Map> workBookData = new ArrayList<Map>();
        for (int i = 0; i < sheetSize; i++) {
            if(sheetSize==clazz.length){
                HSSFSheet sheet = book.getSheetAt(i);
                String sheetName =  sheet.getSheetName();
                List<List<String>> dataList = readSheetColumnData(clazz[i], sheet);
                Map<String, Object> map = ExcelImportAnalysizer.excelCheck(clazz[i],
                        dataList,needCheckIndex);
                workBookData.add(map);
            }else{
                throw new RuntimeException("sheet数量与class长度不一致！");
            }
        }
        return workBookData;
    }


    /**
     * 读取sheet页里面的数据
      * 
     * @param sheet sheet对象
     * @param clazz 与sheet映射的实体类class
     **/

    public static List<List<String>> readSheetColumnData(Class clazz,
            HSSFSheet sheet) {
        POExcelImportAnnotation poean = (POExcelImportAnnotation) clazz
                .getAnnotation(POExcelImportAnnotation.class);
        int startRowIndex = poean.startRowIndex()-1;
        HSSFRow excelheadRow = sheet.getRow(startRowIndex);
        int excelLastcell = excelheadRow.getLastCellNum(); // excel总列数
        List<String> obj = null;
        int excelRowNum = sheet.getLastRowNum(); // excel总行数
        List<List<String>> listDatas = new ArrayList<List<String>>();
        // 处理头部信息
        // 如果有多个头信息 定义多个
        POHeaderImporttAnnotation[] headers = poean.header();
        if (headers != null && headers.length > 0) {
            obj = new ArrayList<String>();
            for (POHeaderImporttAnnotation header : headers) {
                int rowIndex = header.rowIndex();
                int colIndex = header.colIndex();
                HSSFCell colCell = sheet.getRow(rowIndex).getCell(colIndex);
                String value = getStringCellValue(colCell);
                if (value != null) {
                    value = value.trim();
                }
                if (StringUtils.isBlank(value)) {
                    value = null;
                }
                // 多个头放到第一条信息中
                obj.add(value);
            }
            // 多个头放到第一条信息中
            listDatas.add(obj);
        }

        for (int i = startRowIndex; i < excelRowNum + 1; i++) {// 行循环
            HSSFRow columnRow = sheet.getRow(i);
            if (columnRow != null) {
                obj = new ArrayList<String>();
                for (int j = 0; j < excelLastcell; j++) { // 列循环
                    HSSFCell colCell = columnRow.getCell(j);
                      String value = getStringCellValue(colCell);
                    if (value != null) {
                        value = value.trim();
                    }
                    if (StringUtils.isBlank(value)) {
                        value = null;
                    }
                    obj.add(value);
                }
                listDatas.add(obj);
            }
        }
        return listDatas;
    }

    /**
     * 获得单元格字符串
     * 
     * 
     */
    public static String getStringCellValue(HSSFCell cell) {
        if (cell == null) {
            return null;
        }

        String result = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_BOOLEAN:
            result = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                java.text.SimpleDateFormat TIME_FORMATTER = new java.text.SimpleDateFormat(
                        "yyyy-MM-dd");
                result = TIME_FORMATTER.format(cell.getDateCellValue());
            } else {
                double doubleValue = cell.getNumericCellValue();
                result = "" + doubleValue;
            }
            break;
        case HSSFCell.CELL_TYPE_STRING:
            if (cell.getRichStringCellValue() == null) {
                result = null;
            } else {
                result = cell.getRichStringCellValue().getString();
            }
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            result = null;
            break;
        case HSSFCell.CELL_TYPE_FORMULA:
            try {
                result = String.valueOf(cell.getNumericCellValue());
            } catch (Exception e) {
                result = cell.getRichStringCellValue().getString();
            }
            break;
        default:
            result = "";
        }

        return result;
    }

}

