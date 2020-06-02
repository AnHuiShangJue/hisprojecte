package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.dto.MedicationExportExcel;
import com.ahsj.hiscore.entity.dto.PharmacyExportExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName : PharmacyExportExcelMapper
 * @Description :
 * @Author : XJP
 * @Date: 2020-06-01 14:31
 */

@Mapper
public interface PharmacyExportExcelMapper extends BaseMapper<PharmacyExportExcel> {

    /**
     *@Description
     *@MethodName medicationexportexcel
     *@Params [pharmacyExportExcel]
     *@return java.util.List<com.ahsj.hiscore.entity.dto.MedicationExportExcel>
     *@Author XJP
     *@Date 2020/6/1
     *@Time 14:32
    **/
    List<PharmacyExportExcel> pharmacyExportExcel(PharmacyExportExcel pharmacyExportExcel);
}
