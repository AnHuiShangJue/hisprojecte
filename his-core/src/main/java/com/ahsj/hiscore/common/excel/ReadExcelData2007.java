/******************************************************************************
 * 
 * 版权所有：安徽驭鹏轮胎有限公司
 * 
 ******************************************************************************
 * 注意：本内容仅限于安徽驭鹏轮胎有限公司内部使用，禁止转发
 *****************************************************************************/
package com.ahsj.hiscore.common.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.EmptyUtil;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ReadExcelData2007 {

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
	public static List<Map> readExcelData(Workbook workBook, int needCheckIndex, Class  ... clazz) {
		XSSFWorkbook book = (XSSFWorkbook) workBook;
		int sheetSize = book.getNumberOfSheets();
		List<Map> workBookData = new ArrayList<Map>();
		for (int i = 0; i < sheetSize; i++) {
			if(sheetSize==clazz.length){
			XSSFSheet sheet = book.getSheetAt(i);
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
	public static List<Map> readExcelDataMany(Workbook workBook, int needCheckIndex, String[] sheetNames, Class  ... clazz) {
		XSSFWorkbook book = (XSSFWorkbook) workBook;
		int sheetSize = book.getNumberOfSheets();
		List<Map> workBookData = new ArrayList<Map>();
		List<String> errorListTemp = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		int type = 0;
		String year ="";
		for(int j=0;j<sheetNames.length;j++){
			for (int i = 0; i < sheetSize; i++) {
				XSSFSheet sheet = book.getSheetAt(i);
				String sheetName = sheet.getSheetName();
				if(	sheetNames[j].equals(sheetName)){
					List<List<String>> dataList = readSheetColumnData(clazz[j], sheet);
					//获取Datatables
					//读取第一个sheet 里面的资产负债表，将表格类型取出来 (判断是否为)
					String[] checkValue = {"适用执行企业会计制度的企业","适用执行企业会计准则的一般企业","适用执行小企业会计准则的企业"};
					String[] checkNames = {"D7","E7","E6"};  //会计  一般  小型
					//报表时间 资产负债表的起始时间 年取值
					String[] checkTime = {"I8","L7","H8"};  //会计 一般 小型
					char temp = 'A';
					String rowytemp =null;
					String value =null;
					if(j==0){
						//通过sheetName 1 资产负债表 去确定企业高企税务类型 为 哪三种
						for(int k=0;k<checkNames.length;k++){
							char x = checkNames[k].charAt(0);
							int rowx = x-temp;
							rowytemp =  checkNames[k].substring(1,checkNames[k].length());
							int  rowy  =Integer.parseInt(rowytemp) - 1;
							value = dataList.get(rowy).get(rowx);
							//非空判断
							if(!EmptyUtil.Companion.isNullOrEmpty(value)) {
								//检查是否包含头部三种类型的check
								for (int m = 0; m < checkValue.length; m++) {
									if (value.contains(checkValue[m])) {
										type = m;
										break;
									}
								}
							}
						}

						//获取类型后来读取时间
						char x = checkTime[type].charAt(0);
						int rowx = x-temp;
						rowytemp =  checkTime[type].substring(1,checkTime[type].length());
						int  rowy  =Integer.parseInt(rowytemp) - 1;
						String checkStartTime = dataList.get(rowy).get(rowx);
						//判断时间格式是否正确
						if(!EmptyUtil.Companion.isNullOrEmpty(checkStartTime)){
							if(checkStartTime.substring(4,5).equals("年")){
								year = checkStartTime.substring(0,4);
							}else{
								errorListTemp.add("Excel的sheet名称为"+	sheetNames[j]+"的"+ checkTime[type]+ "单元格的时间格式错误");
							}

						}else{
							errorListTemp.add("Excel的sheet名称为"+	sheetNames[j]+"的"+ checkTime[type]+ "单元格的起始时间不能为空");
						}

					}
					Map<String, Object> map = ExcelImportAnalysizer.excelCheckMany(clazz[type*2+j],dataList,needCheckIndex,sheet);
					//将map中errorList取出来合并
					if(j==0){
						errorList = (List<String>) map.get("errorList");
						errorList.addAll(errorListTemp);
					}
					workBookData.add(map);
					//利润表之后操作
					if(j==1){
						Map<String,String> typeMap = new HashMap<>();
						typeMap.put("type", String.valueOf(type));
						typeMap.put("year",year);
						workBookData.add(typeMap);
					}

				}
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
			XSSFSheet sheet) {
		POExcelImportAnnotation poean = (POExcelImportAnnotation) clazz
				.getAnnotation(POExcelImportAnnotation.class);
		int startRowIndex = poean.startRowIndex()-1;
		XSSFRow excelheadRow = sheet.getRow(startRowIndex);
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
				XSSFCell colCell = sheet.getRow(rowIndex).getCell(colIndex);
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
			XSSFRow columnRow = sheet.getRow(i);
			if (columnRow != null) {
				obj = new ArrayList<String>();
				for (int j = 0; j < excelLastcell; j++) { // 列循环
					XSSFCell colCell = columnRow.getCell(j);
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
	 * 读取sheet页里面的数据
	 *  科学计数  https://blog.csdn.net/u014267869/article/details/52513751/
	 * @param sheet sheet对象
	 * @param clazz 与sheet映射的实体类class
	 **/

	public static List<List<String>> readSheetColumnDataAll(Class clazz,
														 XSSFSheet sheet) {
		POExcelImportAnnotation poean = (POExcelImportAnnotation) clazz
				.getAnnotation(POExcelImportAnnotation.class);
		int startRowIndex = poean.startRowIndex()-1;
		XSSFRow excelheadRow = sheet.getRow(startRowIndex);
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
				XSSFCell colCell = sheet.getRow(rowIndex).getCell(colIndex);
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
			XSSFRow columnRow = sheet.getRow(i);
			if (columnRow != null) {
				obj = new ArrayList<String>();
				for (int j = 0; j < excelLastcell; j++) { // 列循环
					NumberFormat nf = NumberFormat.getInstance();
					XSSFCell colCell = columnRow.getCell(j);
					String value = getStringCellValue(colCell);
				//	String s = nf.format(value);
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
	public static String getStringCellValue(XSSFCell cell) {
		if (cell == null) {
			return null;
		}

		String result = "";
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(
						"yyyy-MM-dd");
				result = TIME_FORMATTER.format(cell.getDateCellValue());
			} else {
				double doubleValue = cell.getNumericCellValue();
				result = "" + doubleValue;
			}
			break;
		case XSSFCell.CELL_TYPE_STRING:
			if (cell.getRichStringCellValue() == null) {
				result = null;
			} else {
				result = cell.getRichStringCellValue().getString();
			}
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			result = null;
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
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