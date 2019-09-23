/******************************************************************************
 * 
 * 版权所有：安徽驭鹏轮胎有限公司
 * 
 ******************************************************************************
 * 注意：本内容仅限于安徽驭鹏轮胎有限公司内部使用，禁止转发
 *****************************************************************************/
package com.ahsj.hiscore.common.excel;


import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.WebContextUtil;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.util.CollectionUtils;
import utils.EmptyUtil;
import utils.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
public class ExcelImportAnalysizer {
	static SqlSessionFactory sqlSessionFactory=null;
    static{
        sqlSessionFactory = SpringContextHolder.getBean(SqlSessionFactory.class);
    }
	public static Map<String,Object> excelCheck(Class c, List<List<String>> list, int needCheckType) {
		boolean isExsts = c.isAnnotationPresent(POExcelImportAnnotation.class);
		POExcelImportAnnotation poean = (POExcelImportAnnotation) c.getAnnotation(POExcelImportAnnotation.class);
		boolean readTitle = poean.readTitle();
		int startRowIndex = poean.startRowIndex();
		List<String> errorList = new ArrayList<String>();
		List<String> headErrorList = new ArrayList<String>();
		List<Object> dataList = new ArrayList<Object>();
		List<String> cell = null;
		List<Object> processedHeader = new ArrayList<Object>();
		Map<Integer,List<String>> exitsListMap=new HashMap<Integer, List<String>>();
		List<String> listValue=new ArrayList<String>();
		List<ExcelErrorObject> errorObjList=new ArrayList<ExcelErrorObject>();
		Map<Integer,String> headMap=new HashMap<Integer, String>();
		int faildNum=0;//失败条数
		int errorTemp=0;
		POHeaderImporttAnnotation[] headers = poean.header();
		if (headers != null && headers.length > 0) {
			readHead(list,headMap,1);
			list.remove(1);
		}else{
			readHead(list,headMap,0);
			list.remove(0);
		}
		startRowIndex = startRowIndex + 1;
		if (isExsts) {
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < list.size(); i++) {
				errorTemp=errorObjList.size();
				if (readTitle && i == 0) {
					cell = list.get(i);
					if (headers != null && headers.length > 0) {
						int j = 0;
						for (POHeaderImporttAnnotation header : headers) {
							String value = null;
							String colName=null;
							if (!EmptyUtil.Companion.isNullOrEmpty(header.processName())) {
								ICellProcess cellProcee=null;
								try {
									cellProcee = (ICellProcess) Class.forName(header.processName()).newInstance();
									value = cellProcee.doProcess(cell.get(j))[1];
									String sql = header.sqlCheck();
									if (StringUtils.isNotBlank(sql)) {
										//value = (String) dao.queryBySQLForOneCell(sql,value);
									}
									
								} catch (Exception e) {
									value=null;
									colName = cellProcee.doProcess(cell.get(j))[0];
								}
								if(EmptyUtil.Companion.isNullOrEmpty(value)){
									headErrorList.add(colName+"不能为空或填写有误！");
								}
								processedHeader.add(value);
							}
							j++;
						}
					}
				}else {
					cell = list.get(i);
					Object o = null;
					try {
						o = c.newInstance();
					} catch (Exception e) {
		
					}
					if (headers != null && headers.length > 0) {
						int j = 0;
						for (POHeaderImporttAnnotation header : headers) {
							if (!EmptyUtil.Companion.isNullOrEmpty(header
									.renderAttribute())) {
								setValue(o, c, header.renderAttribute(),
										header.renderAttributeType(),
										processedHeader.get(j));
							}
							j++;
						}
					}
		           
					for (int j = 0; j < fields.length; j++) {
						Field field = fields[j];
						if (field.isAnnotationPresent(ColumnCheckAnnotation.class)) {
							// 判断此字段可需要check
							if (field.isAnnotationPresent(NeedCheckAnnotation.class)) {
								NeedCheckAnnotation needCheckAnnotation = field.getAnnotation(NeedCheckAnnotation.class);
								boolean doCheck = false;
								for (int index : needCheckAnnotation.needCheckIndex()) {
									if (needCheckType == index) {
										doCheck = true;
										break;
									}
								}
								
								if (!doCheck) {
									continue;
								}
							}
							
							ColumnCheckAnnotation ann = field
									.getAnnotation(ColumnCheckAnnotation.class);
							boolean isisRepeat=ann.isRepeat();
							
							int index = ann.index();
							boolean nullable = ann.nullable();
							String value = null;
							try {
								value = cell.get(index);
							} catch (Exception e) {
								value = null;
							}
							Object v = null;
							if (!nullable) {
								if (!ann.dataType().equals(DataType.getuser)) {
									if (EmptyUtil.Companion.isNullOrEmpty(value)){
										errorList.add("excel 第"
												+ (startRowIndex + i) + "行"
												+ headMap.get(index) + "不能为空！");
										addErrorObject(startRowIndex + i,headMap.get(index),"不能为空",errorObjList);
										continue;
									}
								}
								v = dataTypeCheck(errorList, ann, index, i,
										value, startRowIndex,field,errorObjList,headMap);
							} else {
								if (ann.dataType().equals(DataType.getuser)) {
									v = dataTypeCheck(errorList, ann, index, i,
											value, startRowIndex,field,errorObjList,headMap);
								}
								if (!EmptyUtil.Companion.isNullOrEmpty(value)) {
									v = dataTypeCheck(errorList, ann, index, i,
											value, startRowIndex,field,errorObjList,headMap);
								
								}
		
							}
							if (v != null) {
								if(!isisRepeat){
									List<String> e=exitsListMap.get(j);
									if(e!=null&&e.contains(value)){
										errorList.add("excel 第"
												+ (startRowIndex + i) + "行"
												+ headMap.get(index) + "在excel中已存在！");
										addErrorObject(startRowIndex,headMap.get(index),"在excel中已存在",errorObjList);
										continue;
									}
									String rePepeatSql=ann.rePepeatSql();
									if(StringUtils.isNotBlank(rePepeatSql) && value!=null){
									    SqlSession sqlSession = sqlSessionFactory.openSession();
										Object obj=sqlSession.selectOne(rePepeatSql,value);
										if(obj!=null){
											errorList.add("excel 第"
													+ (startRowIndex + i) + "行"
													+ headMap.get(index) + "数据库中已存在！");
											addErrorObject(startRowIndex + i,headMap.get(index),"数据库中已存在",errorObjList);
											continue;
										}
									}
									listValue.add(value);
									exitsListMap.put(j, listValue);
								}
								Class<?> typeClass = field.getType();
									setValue(o, c, field.getName(), typeClass,v);
							}
						}
					}
					if(errorTemp!=errorObjList.size()){
						faildNum++;
					}else{
						setValue(o, c, "rowIndex", Integer.class,startRowIndex + i);
						dataList.add(o);
					}
					
					
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorList", errorList);//错误信息
		map.put("headErrorList", headErrorList);//错误信息
		map.put("dataList", dataList);//校验通过的数据
		map.put("errorObjList", errorObjList);//错误信息封装到对象
		map.put("faildNum", faildNum);//失败条数
		map.put("successNum", dataList.size());//成功条数
		return map;
	}

	/** 
	* @Description: 对其进行设值
	* @Author: diaoyufei
	* @Date: 2018/8/21 0021 
	* @Time: 8:53
	*/ 
	public static Map<String,Object> excelCheckMany(Class c, List<List<String>> list, int needCheckType, XSSFSheet sheet) {
		boolean isExsts = c.isAnnotationPresent(POExcelImportAnnotation.class);
		POExcelImportAnnotation poean = (POExcelImportAnnotation) c.getAnnotation(POExcelImportAnnotation.class);
		boolean readTitle = poean.readTitle();
		int startRowIndex = poean.startRowIndex();
		List<String> errorList = new ArrayList<String>();
		List<String> headErrorList = new ArrayList<String>();
		List<Object> dataList = new ArrayList<Object>();
		List<String> cell = null;
		List<Object> processedHeader = new ArrayList<Object>();
		Map<Integer,List<String>> exitsListMap=new HashMap<Integer, List<String>>();
		List<String> listValue=new ArrayList<String>();
		List<ExcelErrorObject> errorObjList=new ArrayList<ExcelErrorObject>();
		Map<Integer,String> headMap=new HashMap<Integer, String>();
		int faildNum=0;//失败条数
		int errorTemp=0;
		startRowIndex = startRowIndex + 1;
		if (isExsts) {
            Field[] fields = c.getDeclaredFields();
            Field field = null;
            ColumnPostion columnPostion = null;
            Object o = null;
            Object v = null;
            try {
                 o = c.newInstance();
                 v = c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < fields.length; j++) {
                field = fields[j];
                if (field.isAnnotationPresent(ColumnPostion.class)) {
                    columnPostion = field.getAnnotation(ColumnPostion.class);
                    String row1 = columnPostion.row1();
                    String row2 = columnPostion.row2();
                    char  x1 =   row1.charAt(0);
                    char  x2  = row2.charAt(0);
                    char temp = 'A';
                    int rowx1 = x1-temp;
                    int rowx2 = x2 -temp;
                    String row1y =  row1.substring(1,row1.length());
                    String row2y =  row2.substring(1,row2.length());
                    int  rowy1  =Integer.parseInt(row1y) - 1;
                    int  rowy2  =Integer.parseInt(row2y) - 1;
                    String value1 = list.get(rowy1).get(rowx1);
                    String value2 = list.get(rowy2).get(rowx2);
                    if (value1 != null ){
                        try{
                            BigDecimal value11= new BigDecimal(value1);
                            ReflectUtils.setFieldValue(o,field.getName(),value11);
                        }catch(Exception e){
                            errorList.add("Excel 的sheet名为"+sheet.getSheetName()+ "的第"+ row1 + "单元格数据格式错误");
                        }
                    }
                    if(value2 != null){
                        try {
                            BigDecimal value12= new BigDecimal(value2);
                            ReflectUtils.setFieldValue(v,field.getName(),value12);
                        }catch (Exception e){
                            errorList.add("Excel 的sheet名为"+sheet.getSheetName()+ "的第"+ row2 +"单元格数据格式错误");
                        }
                    }
                }
            }
            dataList.add(o);
            dataList.add(v);
        }
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorList", errorList);//错误信息
		map.put("headErrorList", headErrorList);//错误信息
		map.put("dataList", dataList);//校验通过的数据
		map.put("errorObjList", errorObjList);//错误信息封装到对象
		map.put("faildNum", faildNum);//失败条数
		map.put("successNum", dataList.size());//成功条数
		return map;
	}


	private static void readHead(List<List<String>> list, Map<Integer, String> headMap ,int k) {
		if(!CollectionUtils.isEmpty(list)){
			for(int i=0;i<list.get(k).size();i++){
				headMap.put(i, list.get(k).get(i));
			}
		}
		
	}
	public static Map<String, Object> excelCheck(Class c, List<List<String>> list) {
		return excelCheck(c,list,-1);	
	}

	/**
	 * 调用set方法把值set到对象当中
	 * 
	 * @param obj
	 *            指定对象
	 * @param clazz
	 *            对象的class
	 * @param field
	 *            需要设置值的field对象
	 * @param typeClass
	 *            field的类型的class
	 * @param value
	 *            对应的值
	 */
	private static void setValue(Object obj, Class<?> clazz, String field,
			Class<?> typeClass, Object value) {
		String methodName = "set" + field.substring(0, 1).toUpperCase()
				+ field.substring(1);
		try {
			if("rowIndex".equals(field)){
				clazz=clazz.getSuperclass();
			}
			Method method = clazz.getDeclaredMethod(methodName,
					new Class[] { typeClass });
			if (typeClass == BigDecimal.class) {
			    if (value != null) {
			        method.invoke(obj, new BigDecimal(value.toString()));
			    }
			} else if(typeClass == Date.class) {
			    if (value != null) {
			        Calendar ca = Calendar.getInstance();
			        ca.setTimeInMillis(Long.valueOf(String.valueOf(value)));
                    method.invoke(obj, ca.getTime());
                }
			} else if (typeClass == Integer.class) {
				if (value != null) {
					method.invoke(obj, Integer.parseInt(value.toString()));
				}
			} else if(typeClass == String.class) {
				if (value != null) {
					method.invoke(obj, value.toString());
				}
			}else {
				method.invoke(obj, new Object[] { value });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Object dataTypeCheck(List<String> errorList,
			ColumnCheckAnnotation ann, int index, int i, Object value,
			int startRowIndex,Field field, List<ExcelErrorObject> errorObjList, Map<Integer,String> headMap) {
		switch (ann.dataType()) {
		case Integer:
			try {
				value = new BigDecimal((String) value).intValue();
			} catch (Exception e) {
				e.printStackTrace();
				errorList.add("excel 第" + (startRowIndex + i) + "行"
						+ headMap.get(index) + "必须为数字！");
				addErrorObject(startRowIndex + i,headMap.get(index),"必须为数字",errorObjList);
				return null;
			}
			break;
		case Float:
			try {
				value = new BigDecimal((String) value).floatValue();
			} catch (Exception e) {
				e.printStackTrace();
				errorList.add("excel 第" + (startRowIndex + i) + "行"
						+ headMap.get(index)+ "必须为数字！");
				addErrorObject(startRowIndex + i,headMap.get(index),"必须为数字",errorObjList);
				return null;
			}
			break;
		case Double:
			try {
				value = new BigDecimal((String) value).doubleValue();
			} catch (Exception e) {
				e.printStackTrace();
				errorList.add("excel 第" + (startRowIndex + i) + "行"
						+headMap.get(index) + "必须为数字！");
				addErrorObject(startRowIndex + i,headMap.get(index),"必须为数字",errorObjList);
				return null;
			}
			break;
		case Bigdecimal:
            try {
                value = new BigDecimal((String) value);
            } catch (Exception e) {
                e.printStackTrace();
                errorList.add("excel 第" + (startRowIndex + i) + "行"
                        +headMap.get(index) + "必须为数字！");
                addErrorObject(startRowIndex + i,headMap.get(index),"必须为数字",errorObjList);
                return null;
            }
            break;
		case Enum:
				String [] enums=ann.enums();
				String [] enumCNs=ann.enumCNs();
				Map<String,String> emap=new HashMap<String, String>();
				if(enums.length!=enumCNs.length){
					throw new RuntimeException("枚举值和枚举名长度不相同！");
				}else if(enums.length<=0 || enumCNs.length<=0){
					throw new RuntimeException("DataType为Enum时，enumCNs和enums不能为空！");
				}else{
					for(int k=0;k<enums.length;k++){
						emap.put(enumCNs[k], enums[k]);
					}
				}
				Class clazz=field.getType();
				if(clazz.getSimpleName().equals("String")){
					if(value!=null){
					 value =emap.get(value);
					 if(value==null){
							errorList.add("excel 第" + (startRowIndex + i) + "行"
									+headMap.get(index)+ "对应的值不正确！");
							addErrorObject(startRowIndex + i,headMap.get(index),"对应的值不正确",errorObjList);
							return null;
						}
					}
				}
				if(clazz.getSimpleName().equals("Integer")){
					if(value!=null){
						value=emap.get(value);
						if(value==null){
							errorList.add("excel 第" + (startRowIndex + i) + "行"
									+headMap.get(index)+ "对应的值不正确！");
							addErrorObject(startRowIndex + i,headMap.get(index),"对应的值不正确",errorObjList);
							return null;
						}
					   value =Integer.parseInt((String)value);
					  
					}
				}
			
			break;
		case Code:
            ConcurrentHashMap<String, List<SysCodeDetail>> codeDatas = (ConcurrentHashMap<String, List<SysCodeDetail>>) WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_TABLE);
            List<SysCodeDetail> lst = codeDatas.get(ann.codeKey());
            if (lst == null || lst.size() <= 0) {
              errorList.add("excel 第" + (startRowIndex + i) + "行"
              + headMap.get(index)+ "对应的值的数据字典不存在！");
              addErrorObject(startRowIndex + i,headMap.get(index),"对应的值的数据字典不存在！",errorObjList);
              return null;
            }
            boolean has = false;
            for (SysCodeDetail sysCodeDetail : lst) {
                if (sysCodeDetail.getValue().equals(value)) {
                    has = true;
                    value = sysCodeDetail.getCode();
                    break;
                }
            }
            
            if (!has) {
                errorList.add("excel 第" + (startRowIndex + i) + "行"
                       + headMap.get(index)+ "对应的值的在数据字典中不存在！");
                addErrorObject(startRowIndex + i,headMap.get(index),"对应的值的在数据字典中不存在！",errorObjList);
                return null;
            }
        break;
		case date:
			String dateFormat = ann.dateFormat();
			if (StringUtils.isBlank(dateFormat)) {
				throw new RuntimeException("date类型时dateFormat为必填项！");
			}
			try {
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				value = format.parse(((String) value)).getTime();
			} catch (Exception e) {
				errorList.add("excel 第" + (startRowIndex + i) + "行"
						+ headMap.get(index) + "必须为规定的时间格式！");
				addErrorObject(startRowIndex + i,headMap.get(index),"必须为规定的时间格式",errorObjList);
				return null;
			}
			break;
		case sqlcheck:
			String checksql = ann.checksql();
			if (StringUtils.isBlank(checksql)) {
				throw new RuntimeException("sqlcheck类型时sqlcheck为必填项！");
			}
			if(value!=null){
			  SqlSession sqlSession = sqlSessionFactory.openSession();
			//  value = sqlSession.selectOne(checksql, value);
			  if (EmptyUtil.Companion.isNullOrEmpty(value)) {
					errorList.add("excel 第" + (startRowIndex + i) + "行"
							+ headMap.get(index)+ "数据库中不存在！");
					addErrorObject(startRowIndex + i,headMap.get(index),"数据库中不存在",errorObjList);
					return null;
				}
			}
			
			break;
		default:
			int length = ann.length();
			if (value != null && ((String) value).length() > length) {
				errorList.add("excel 第" + (startRowIndex + i) + "行"
						+ headMap.get(index) + "超过允许长度！");
				addErrorObject(startRowIndex + i,headMap.get(index),"超过允许长度",errorObjList);
				return null;
			}
			break;
		}
		return value;
	}

	private static  void addErrorObject(int row,String colName,String errorMessage,List<ExcelErrorObject> errorObjectList){
		ExcelErrorObject error=new ExcelErrorObject();
		error.setRow(row);
		error.setColName(colName);
		error.setErrorMessage(errorMessage);
		errorObjectList.add(error);
	}
}
