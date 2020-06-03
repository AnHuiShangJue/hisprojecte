package com.ahsj.hiscore.controller.hisFinance;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.common.utils.CloneUtil;
import com.ahsj.hiscore.entity.HisTollDetails;
import com.ahsj.hiscore.entity.dto.HisFinanceCondition;
import com.ahsj.hiscore.entity.vo.HisFinanceVO;
import com.ahsj.hiscore.services.HisFinanceService;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description 财务统计模块
 * @Params
 * @return
 * @Author zhushixiang
 * @Date 2020-05-20
 * @Time 14:14
 **/
@Controller
@RequestMapping("/api/hisFinance/")
public class hisFinanceController extends BaseController {
    @Autowired
    private HisFinanceService hisFinanceService;

    @RequestMapping("total/index.ahsj")
    ModelAndView total(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/total");
        modelAndView.addObject("title", "医院收费总计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("notInHospitalTotal/index.ahsj")
    ModelAndView notInHospitalTotal(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/notInHospitalTotal");
        modelAndView.addObject("title", "门诊收费总计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("inHospitalTotal/index.ahsj")
    ModelAndView inHospitalTotal(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/inHospitalTotal");
        modelAndView.addObject("title", "住院收费总计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("hismedicine/index.ahsj")
    ModelAndView hismedicine(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/medicineInfo");
        modelAndView.addObject("title", "药品销售成本明细");
        modelAndView.addObject("token", token);
        return modelAndView;
    }
    @RequestMapping("pharmacy/index.ahsj")
    ModelAndView pharmacy(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/pharmacyInfoDetails");
        modelAndView.addObject("title", "药品库存明细");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return java.math.BigDecimal
     * @Description 获取指定时间段内医院收入总价
     * @Params []
     * @Author zhushixiang
     * @Date 2020-06-02
     * @Time 9:51
     **/
    @ResponseBody
    @RequestMapping("getTotal.ahsj")
    public List<HisTollDetails> getTotal(HisFinanceCondition hisFinanceCondition) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisFinanceCondition.getRightTime())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(hisFinanceCondition.getRightTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hisFinanceCondition.setRightTime(calendar.getTime());
        }
        List<HisTollDetails> total = hisFinanceService.getTotal(hisFinanceCondition);
        return total;
    }

    /**
     * @return java.math.BigDecimal
     * @Description 获取指定时间段内医院住院收入总价
     * @Params []
     * @Author zhushixiang
     * @Date 2020-06-02
     * @Time 9:51
     **/
    @ResponseBody
    @RequestMapping("getInhospitalTotal.ahsj")
    public List<HisTollDetails> getInhospitalTotal(HisFinanceCondition hisFinanceCondition) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisFinanceCondition.getRightTime())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(hisFinanceCondition.getRightTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hisFinanceCondition.setRightTime(calendar.getTime());
        }
        List<HisTollDetails> total = hisFinanceService.getInhospitalTotal(hisFinanceCondition);
        return total;
    }

    /**
     * @return java.math.BigDecimal
     * @Description 获取指定时间段内医院门诊收入总价
     * @Params []
     * @Author zhushixiang
     * @Date 2020-06-02
     * @Time 9:51
     **/
    @ResponseBody
    @RequestMapping("getNotInhospitalTotal.ahsj")
    public List<HisTollDetails> getNotInhospitalTotal(HisFinanceCondition hisFinanceCondition) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisFinanceCondition.getRightTime())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(hisFinanceCondition.getRightTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hisFinanceCondition.setRightTime(calendar.getTime());
        }
        List<HisTollDetails> total = hisFinanceService.getNotInhospitalTotal(hisFinanceCondition);
        return total;
    }

    /**
     * @return void
     * @Description 导出excel
     * @Params [leftTime, rightTime]
     * @Author zhushixiang
     * @Date 2020-06-02
     * @Time 16:58
     **/
    @RequestMapping("exportExcel.ahsj")
    @ResponseBody
    public void exportExcel(@RequestParam(value = "leftTime") Date leftTime, @RequestParam(value = "rightTime") Date rightTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HisFinanceCondition hisFinanceCondition = new HisFinanceCondition();
        hisFinanceCondition.setLeftTime(leftTime);
        if (!EmptyUtil.Companion.isNullOrEmpty(rightTime)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rightTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hisFinanceCondition.setRightTime(calendar.getTime());
        }
        List<HisTollDetails> hisTollDetailsList = hisFinanceService.getTotalByDay(hisFinanceCondition);
        List<HisFinanceVO> hisFinanceVOList = new ArrayList<>();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList)) {
            Date currentTime = hisTollDetailsList.get(0).getCreateDate();
            BigDecimal total = new BigDecimal("0");
            HisFinanceVO hisFinanceVO = new HisFinanceVO();
            hisFinanceVO.clear();
            for (HisTollDetails hisTollDetails : hisTollDetailsList) {
                if (!currentTime.equals(hisTollDetails.getCreateDate())) {
                    hisFinanceVO.setTotal(total);
                    hisFinanceVO.setCreateDate(currentTime);
                    hisFinanceVOList.add(CloneUtil.clone(hisFinanceVO));
                    currentTime = hisTollDetails.getCreateDate();
                    hisFinanceVO.clear();
                    total = BigDecimal.valueOf(0);
                }
                if (hisTollDetails.getType().equals(1)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setMedicineTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(2)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setProjectTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(3)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setBedTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(4)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackMedicineTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(5)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackProjectTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(6)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setConsumablesTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(7)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackConsumablesTotal(hisTollDetails.getMoney());
                    continue;
                }

            }
            // 处理最后一条数据
            hisFinanceVO.setTotal(total);
            hisFinanceVO.setCreateDate(currentTime);
            hisFinanceVOList.add(CloneUtil.clone(hisFinanceVO));

            HashMap<String, Object> beans = new HashMap<String, Object>();
            String psth = null;
            psth = Constants.HIS_SYS_EXCEL_GET_TOTAL_BY_DAY;
            beans.put("hisFinanceVOList", hisFinanceVOList);
            JxlsUtil.export(request, response, psth, "医院收费统计", beans);
            return;
        }
    }

    /**
     * @return void
     * @Description 导出excel(仅住院)
     * @Params [leftTime, rightTime]
     * @Author zhushixiang
     * @Date 2020-06-02
     * @Time 16:58
     **/
    @RequestMapping("exportExcelInHospital.ahsj")
    @ResponseBody
    public void exportExcelInHospital(@RequestParam(value = "leftTime") Date leftTime, @RequestParam(value = "rightTime") Date rightTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HisFinanceCondition hisFinanceCondition = new HisFinanceCondition();
        hisFinanceCondition.setLeftTime(leftTime);
        if (!EmptyUtil.Companion.isNullOrEmpty(rightTime)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rightTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hisFinanceCondition.setRightTime(calendar.getTime());
        }
        List<HisTollDetails> hisTollDetailsList = hisFinanceService.getInHospitalTotalByDay(hisFinanceCondition);
        List<HisFinanceVO> hisFinanceVOList = new ArrayList<>();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList)) {
            Date currentTime = hisTollDetailsList.get(0).getCreateDate();
            BigDecimal total = new BigDecimal("0");
            HisFinanceVO hisFinanceVO = new HisFinanceVO();
            hisFinanceVO.clear();
            for (HisTollDetails hisTollDetails : hisTollDetailsList) {
                if (!currentTime.equals(hisTollDetails.getCreateDate())) {
                    hisFinanceVO.setTotal(total);
                    hisFinanceVO.setCreateDate(currentTime);
                    hisFinanceVOList.add(CloneUtil.clone(hisFinanceVO));
                    currentTime = hisTollDetails.getCreateDate();
                    hisFinanceVO.clear();
                    total = BigDecimal.valueOf(0);
                }
                if (hisTollDetails.getType().equals(1)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setMedicineTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(2)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setProjectTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(3)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setBedTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(4)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackMedicineTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(5)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackProjectTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(6)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setConsumablesTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(7)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackConsumablesTotal(hisTollDetails.getMoney());
                    continue;
                }

            }
            // 处理最后一条数据
            hisFinanceVO.setTotal(total);
            hisFinanceVO.setCreateDate(currentTime);
            hisFinanceVOList.add(CloneUtil.clone(hisFinanceVO));

            HashMap<String, Object> beans = new HashMap<String, Object>();
            String psth = null;
            psth = Constants.HIS_SYS_EXCEL_GET_INHOSPITAL_TOTAL_BY_DAY;
            beans.put("hisFinanceVOList", hisFinanceVOList);
            JxlsUtil.export(request, response, psth, "医院住院收费统计", beans);
            return;
        }
    }


    /**
     * @return void
     * @Description 导出excel(仅门诊)
     * @Params [leftTime, rightTime]
     * @Author zhushixiang
     * @Date 2020-06-02
     * @Time 16:58
     **/
    @RequestMapping("exportExcelNotInHospital.ahsj")
    @ResponseBody
    public void exportExcelNotInHospital(@RequestParam(value = "leftTime") Date leftTime, @RequestParam(value = "rightTime") Date rightTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HisFinanceCondition hisFinanceCondition = new HisFinanceCondition();
        hisFinanceCondition.setLeftTime(leftTime);
        if (!EmptyUtil.Companion.isNullOrEmpty(rightTime)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rightTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hisFinanceCondition.setRightTime(calendar.getTime());
        }
        List<HisTollDetails> hisTollDetailsList = hisFinanceService.getNotInHospitalTotalByDay(hisFinanceCondition);
        List<HisFinanceVO> hisFinanceVOList = new ArrayList<>();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList)) {
            Date currentTime = hisTollDetailsList.get(0).getCreateDate();
            BigDecimal total = new BigDecimal("0");
            HisFinanceVO hisFinanceVO = new HisFinanceVO();
            hisFinanceVO.clear();
            for (HisTollDetails hisTollDetails : hisTollDetailsList) {
                if (!currentTime.equals(hisTollDetails.getCreateDate())) {
                    hisFinanceVO.setTotal(total);
                    hisFinanceVO.setCreateDate(currentTime);
                    hisFinanceVOList.add(CloneUtil.clone(hisFinanceVO));
                    currentTime = hisTollDetails.getCreateDate();
                    hisFinanceVO.clear();
                    total = BigDecimal.valueOf(0);
                }
                if (hisTollDetails.getType().equals(1)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setMedicineTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(2)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setProjectTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(3)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setBedTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(4)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackMedicineTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(5)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackProjectTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(6)) {
                    total = total.add(hisTollDetails.getMoney());
                    hisFinanceVO.setConsumablesTotal(hisTollDetails.getMoney());
                    continue;
                } else if (hisTollDetails.getType().equals(7)) {
                    total = total.subtract(hisTollDetails.getMoney());
                    hisFinanceVO.setBackConsumablesTotal(hisTollDetails.getMoney());
                    continue;
                }

            }
            // 处理最后一条数据
            hisFinanceVO.setTotal(total);
            hisFinanceVO.setCreateDate(currentTime);
            hisFinanceVOList.add(CloneUtil.clone(hisFinanceVO));

            HashMap<String, Object> beans = new HashMap<String, Object>();
            String psth = null;
            psth = Constants.HIS_SYS_EXCEL_GET_NOT_INHOSPITAL_TOTAL_BY_DAY;
            beans.put("hisFinanceVOList", hisFinanceVOList);
            JxlsUtil.export(request, response, psth, "医院门诊收费统计", beans);
            return;
        }
    }
}
