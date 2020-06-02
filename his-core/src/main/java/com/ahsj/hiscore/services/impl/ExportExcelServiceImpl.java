package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.dao.HisHandoverMapper;
import com.ahsj.hiscore.dao.MedicationExportExcelMapper;
import com.ahsj.hiscore.dao.PharmacyExportExcelMapper;
import com.ahsj.hiscore.entity.dto.MedicationExportExcel;
import com.ahsj.hiscore.entity.dto.PharmacyExportExcel;
import com.ahsj.hiscore.entity.dto.Summary;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.services.ExportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName : ExportExcelServiceImpl
 * @Description :
 * @Author : XJP
 * @Date: 2020-06-01 09:07
 */

@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    @Autowired
    MedicationExportExcelMapper medicationExportExcelMapper;

    @Autowired
    PharmacyExportExcelMapper pharmacyExportExcelMapper;


    /**
     *@Description
     *@MethodName medicationExportExcel
     *@Params [request, response, session, medicationExportExcel]
     *@return void
     *@Author XJP
     *@Date 2020/6/1
     *@Time 14:34
    **/
    @Override
    @Transactional(readOnly = true)
    public void medicationExportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session, MedicationExportExcel medicationExportExcel) throws Exception {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        psth = Constants.HIS_SYS_MEDICATIONEXPORTEXCEL_FILE_URL;
//        String psthkm = Constants.HIS_SYS_EXCEL_PROJECT_KM_FILE_URL;
        List<MedicationExportExcel> medicationexportexcel = medicationExportExcelMapper.medicationexportexcel(medicationExportExcel);

        beans.put("medicationexportexcel", medicationexportexcel);
        JxlsUtil.export(request, response, psth, "药品销售成本明细", beans);
        return;
    }

    /**
     *@Description
     *@MethodName pharmacyExportExcel
     *@Params [request, response, session, pharmacyExportExcel]
     *@return void
     *@Author XJP
     *@Date 2020/6/1
     *@Time 14:37
    **/
    @Override
    @Transactional(readOnly = true)
    public void pharmacyExportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session, PharmacyExportExcel pharmacyExportExcel) throws IOException {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        psth = Constants.HIS_SYS_PHARMACYEXPORTEXCEL_FILE_URL;
        //        String psthkm = Constants.HIS_SYS_EXCEL_PROJECT_KM_FILE_URL;
        List<PharmacyExportExcel> pharmacyExportExcels = pharmacyExportExcelMapper.pharmacyExportExcel(pharmacyExportExcel);

        beans.put("pharmacyExportExcels", pharmacyExportExcels);
        JxlsUtil.export(request, response, psth, "药品库存明细", beans);
        return;
    }


}
