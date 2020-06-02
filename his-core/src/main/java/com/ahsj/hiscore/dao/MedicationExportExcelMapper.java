package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.dto.MedicationExportExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName : MedicationExportExcelMapper
 * @Description :
 * @Author : XJP
 * @Date: 2020-05-29 16:06
 */
@Mapper
public interface MedicationExportExcelMapper extends BaseMapper<MedicationExportExcel> {
    
    /**
     *@Description 
     *@MethodName medicationexportexcel
     *@Params [medicationExportExcel]
     *@return java.util.List<com.ahsj.hiscore.entity.dto.MedicationExportExcel>
     *@Author XJP
     *@Date 2020/6/1
     *@Time 9:07
    **/
    List<MedicationExportExcel>  medicationexportexcel(MedicationExportExcel medicationExportExcel);


}
