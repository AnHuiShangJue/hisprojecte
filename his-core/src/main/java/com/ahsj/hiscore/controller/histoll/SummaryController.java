package com.ahsj.hiscore.controller.histoll;

import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.dto.MedicationExportExcel;
import com.ahsj.hiscore.entity.dto.PharmacyExportExcel;
import com.ahsj.hiscore.entity.dto.Summary;
import com.ahsj.hiscore.services.ExportExcelService;
import com.ahsj.hiscore.services.HisTollDetailsService;
import core.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @ClassName : SummaryController
 * @Description :
 * @Author : XJP
 * @Date: 2020-05-26 14:04
 */


@Controller
@RequestMapping("/api/summary")
public class SummaryController extends BaseController {


    @Autowired
    ExportExcelService exportExcelService;


    /**
     * @return void
     * @Description
     * @MethodName medicationExportExcel
     * @Params [request, medicationExportExcel, response, session, token]
     * @Author XJP
     * @Date 2020/6/1
     * @Time 14:37
     **/
    @GetMapping("/medicationExportExcel.ahsj")
    @ResponseBody
    public void medicationExportExcel(HttpServletRequest request, MedicationExportExcel medicationExportExcel,
                                      HttpServletResponse response, HttpSession session, String token, @RequestParam(value = "lowTime") Date lowTime, @RequestParam(value = "highTime") Date highTime) throws Exception {

        exportExcelService.medicationExportExcel(request, response, session, medicationExportExcel);
    }

    /**
     * @return void
     * @Description
     * @MethodName pharmacyExportExcel
     * @Params [request, pharmacyExportExcel, response, session, token]
     * @Author XJP
     * @Date 2020/6/1
     * @Time 14:37
     **/
    @GetMapping("/pharmacyExportExcel.ahsj")
    @ResponseBody
    public void pharmacyExportExcel(HttpServletRequest request, PharmacyExportExcel pharmacyExportExcel,
                                    HttpServletResponse response, HttpSession session, String token, @RequestParam(value = "lowTime") Date lowTime, @RequestParam(value = "highTime") Date highTime) throws Exception {

        exportExcelService.pharmacyExportExcel(request, response, session, pharmacyExportExcel);
    }


    @PostMapping("/medicationListSum.ahsj")
    @ResponseBody
    public Double medicationListSum(MedicationExportExcel medicationExportExcel) throws Exception {
        Double aDouble = exportExcelService.medicationListSum(medicationExportExcel);
        return aDouble;
    }


    @PostMapping("/pharmacyListSum.ahsj")
    @ResponseBody
    public Double pharmacyListSum(PharmacyExportExcel pharmacyExportExcel) throws Exception {

        Double aDouble = exportExcelService.pharmacyListSum(pharmacyExportExcel);
        return aDouble;
    }

}
