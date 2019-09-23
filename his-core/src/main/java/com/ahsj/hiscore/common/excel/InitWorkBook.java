/******************************************************************************
 * 
 * 版权所有：安徽驭鹏轮胎有限公司
 * 
 ******************************************************************************
 * 注意：本内容仅限于安徽驭鹏轮胎有限公司内部使用，禁止转发
 *****************************************************************************/
package com.ahsj.hiscore.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
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
public class InitWorkBook {
    /**
     * 根据后缀名创建不同的workbook实例
     * 
     * @param filePath
     *            文件路径
     * @return Workbook
     */
    private static Workbook createWorkbook(String filePath, InputStream fis) {
        Workbook workbook = null;
        try {
            if (filePath.indexOf(".xlsx") > 0) {// 如果后缀带xlsx，创建XSSFWorkbook
                OPCPackage op= OPCPackage.open(fis);
                workbook = new XSSFWorkbook(op);
            } else {
                workbook = new HSSFWorkbook(fis);// 如果后缀不带xlsx，创建HSSFWorkbook
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fis!=null)
                try {
                    fis.close();
                   
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return workbook;
    }

    /**
     * 读取数据放入到相应的对象集合中
     * 
     * @param filePath
     *            文件路径
     * @param c
     *            要映射类的字节码，支持多个实体与多个sheet一一对应
     * @return对象集合
     */
    public static List<Map> readExcelData(String filePath, InputStream fis,Class ... c) {
        return readExcelData(filePath,fis,-1,c);
    }

    /**
     * 读取数据放入到相应的对象集合中
     * 
     * @param filePath
     *            文件路径
     * @param c 要映射类的字节码，支持多个实体与多个sheet一一对应
     * @return对象集合
     */
    public static List<Map> readExcelData(String filePath, InputStream fis, int needCheck,Class ... c) {
        Workbook workbook = createWorkbook(filePath,fis);
        if (workbook instanceof HSSFWorkbook) {
            return ReadExcelData2003.readExcelData(workbook, needCheck,c);
        } else {
            return ReadExcelData2007.readExcelData(workbook,needCheck,c);
        }
    }


    /**
     * 读取数据放入到相应的对象集合中
     *
     * @param filePath
     *            文件路径
     * @param c
     *            要映射类的字节码，支持多个实体与多个sheet一一对应
     *            sheetNames 数组顺序与class需要保持对应关系
     * @return对象集合
     */
    public static List<Map> readExcelDataMany(String filePath, InputStream fis, String[] sheetNames,Class ... c) {
        return readExcelDataMany(filePath,fis,-1,sheetNames,c);
    }


    /**
     * 读取数据放入到相应的对象集合中
     *
     * @param filePath
     *            文件路径
     * @param c 要映射类的字节码，支持多个实体与多个sheet一一对应
     * @return对象集合
     */
    public static List<Map> readExcelDataMany(String filePath, InputStream fis, int needCheck, String[] sheetNames,Class ... c) {
        Workbook workbook = createWorkbook(filePath,fis);
        if (workbook instanceof HSSFWorkbook) {
            return ReadExcelData2003.readExcelDataMany(workbook, needCheck,c);
        } else {
            return ReadExcelData2007.readExcelDataMany(workbook,needCheck,sheetNames,c);
        }
    }
}
