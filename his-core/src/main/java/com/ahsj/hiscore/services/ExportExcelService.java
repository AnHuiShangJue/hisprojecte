package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.dto.MedicationExportExcel;
import com.ahsj.hiscore.entity.dto.PharmacyExportExcel;
import com.ahsj.hiscore.entity.dto.Summary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName : ExportExcelService
 * @Description :
 * @Author : XJP
 * @Date: 2020-06-01 09:07
 */
public interface ExportExcelService  {

    /**
     *@Description
     *@MethodName exportExcels
     *@Params [request, response, session, summary]
     *@return void
     *@Author XJP
     *@Date 2020/6/1
     *@Time 9:10
    **/
    void medicationExportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session, MedicationExportExcel medicationExportExcel)throws Exception;

    /**
     *@Description 
     *@MethodName pharmacyExportExcel
     *@Params [request, response, session, pharmacyExportExcel]
     *@return void
     *@Author XJP
     *@Date 2020/6/1
     *@Time 14:33
    **/
    void pharmacyExportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session, PharmacyExportExcel pharmacyExportExcel) throws IOException;

    Double medicationListSum(MedicationExportExcel medicationExportExcel);

    Double pharmacyListSum(PharmacyExportExcel pharmacyExportExcel);
}
