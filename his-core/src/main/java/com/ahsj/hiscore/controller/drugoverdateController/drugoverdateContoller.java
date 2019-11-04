package com.ahsj.hiscore.controller.drugoverdateController;

import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.services.HisMediEnterDetailsService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("api/drugoverdate/")
public class drugoverdateContoller extends BaseController {


    @Autowired
    HisMediEnterDetailsService hisMediEnterDetailsService;



    /**
     *@Description 查询快过期的药品
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/1
     *@Time 17:19
    */
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisMediEnterDetails> list(Map<String, Object> model, HttpServletRequest request, HisMediEnterDetails hisMediEnterDetails) throws Exception {
        PageBean<HisMediEnterDetails> hisMediEnterDetailsPageBean = new PageBean<HisMediEnterDetails>();
        hisMediEnterDetailsPageBean.setParameter(hisMediEnterDetails);
        return hisMediEnterDetailsService.listOverdate(hisMediEnterDetailsPageBean);
    }


    /**
     *@Description 跳转到过期预警页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/1
     *@Time 17:51
    */
    @RequestMapping("overdate/index.ahsj")
    ModelAndView overDate(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/overdate/overdate");
        modelAndView.addObject("title", "药品过期预警");
        modelAndView.addObject("token", token);
        return modelAndView;
    }



}
