package com.ahsj.hiscore.controller.dataStatistics;

import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisMedicationDetailsService;
import com.ahsj.hiscore.services.HisRegisteredService;
import com.ahsj.hiscore.services.HisTollRecordService;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/api/hisdatastatistics/")
public class HisDataStatisticsController extends BaseController {

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisTollRecordService hisTollRecordService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    /**
     * @Description 时间段挂号数量
     * @Author   muxu
     * @Date 2019/7/28
     * @Time 21:52
     * @Return com.ahsj.hiscore.entity.HisRegistered
     * @Params [token, hisRegistered]
    **/
    @ResponseBody
    @RequestMapping(value = "selectByReistDataCount.ahsj" ,method = {RequestMethod.POST})
    public Integer[] selectByReistDataCount(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="createDate", required=false) Date createDate)throws Exception{
        if (null != request.getParameter("createDate")) {
            return  hisRegisteredService.selectByReistDataCount(createDate);
            }else {
            return null;
        }
    }


    /**
     * @Description 时间段内住院数量
     * @Author   muxu
     * @Date 2019/7/28
     * @Time 21:52
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token]
    **/

    @ResponseBody
    @RequestMapping(value = "selectHospitalManageDataCount.ahsj",method = {RequestMethod.POST})
    public Integer[] selectHospitalManageDataCount(Map<String, Object> model, HttpServletRequest request
    , @RequestParam(value="createDate", required=false) Date createDate)throws Exception {
        if (null != request.getParameter("createDate")) {
            return hisHospitalManageService.selectHospitalManageDataCount(createDate);

        }else {
            return null;
        }
    }

    /**
     * @Description 统计各个时间段内的收费数目
     * @Author   muxu
     * @Date 2019/7/28
     * @Time 22:10
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token]
    **/

    @ResponseBody
    @RequestMapping(value = "selectTollRecordDataCount.ahsj" ,method = {RequestMethod.POST})
    public Integer[] selectTollRecordDataCount(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="createDate", required=false) Date createDate)throws Exception{
        if (null != request.getParameter("createDate")) {
            return  hisTollRecordService.selectTollRecordDataCount(createDate);
        }else {
            return null;
        }
    }
    /**
     * @Description 统计各个时间段内的售药数目
     * @Author   muxu
     * @Date 2019/7/28
     * @Time 22:10
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token]
    **/
    @ResponseBody
    @RequestMapping(value = "selectMedicationDetailsDataCount.ahsj",method = {RequestMethod.POST})
    public Integer[] selectMedicationDetailsDataCount(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="createDate", required=false) Date createDate)throws Exception{
        if (null != request.getParameter("createDate")) {
            return  hisMedicationDetailsService.selectMedicationDetailsDataCount(createDate);
        }else {
            return null;
        }
    }

    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/statisticalAnalysis/dataStatistics");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

}
