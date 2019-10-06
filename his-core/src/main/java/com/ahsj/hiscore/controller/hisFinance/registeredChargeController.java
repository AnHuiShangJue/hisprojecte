package com.ahsj.hiscore.controller.hisFinance;

import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.services.HisFinanceService;
import com.ahsj.hiscore.services.HisRegisteredpayService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/19
 * @Time 16:48
 **/
@RestController
@RequestMapping("/api/registeredCharge/")
public class registeredChargeController {
    /**
     * className registeredChargeController
     *
     * @author dingli
     * @date 2019/7/19 16:48
     */
    @Autowired
    HisFinanceService hisFinanceService;
    @Autowired
    HisRegisteredpayService hisRegisteredpayService;

    /**
     * @Description 跳转财务统计挂号收费统计页面
     * @Param token:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/7/24
     * @Time 9:48
     **/
    @RequestMapping("list/index.ahsj")
    ModelAndView list(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/list");
        modelAndView.addObject("title", "财务统计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 分页查询挂号收费统计
     * @Param hisRegisteredpay:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisRegisteredpay>
     * @Date 2019/7/24
     * @Time 9:46
     **/
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    public PageBean<HisRegisteredpay> list(HisRegisteredpay hisRegisteredpay) throws Exception {
        if (hisRegisteredpay.getLowTime() != null || hisRegisteredpay.getUpTime() != null) {
            hisRegisteredpay.setYears(null);
            hisRegisteredpay.setMonths(null);
        }
        PageBean<HisRegisteredpay> pageBean = new PageBean<HisRegisteredpay>();
        pageBean.setParameter(hisRegisteredpay);
        pageBean = hisFinanceService.getAllHisFinance(pageBean);
        return pageBean;
    }

    /**
     * @Description 跳转财务统计挂号收费详情
     * @Param token:
     * @Param number:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/7/24
     * @Time 9:45
     **/
    @RequestMapping(value = "details/index.ahsj", method = {RequestMethod.GET})
    ModelAndView details(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/show");
        modelAndView.addObject("title", "财务统计");
        modelAndView.addObject("token", token);
        modelAndView.addObject("HisRegisteredpay", hisFinanceService.getHisRegisteredpay(number));
        return modelAndView;
    }

    /**
     * @Description 获取当前条件下查询的总价格
     * @Param token:
     * @Param hisRegisteredpay:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisRegisteredpay
     * @Date 2019/7/24
     * @Time 9:48
     **/
    @RequestMapping("getPrice")
    @ResponseBody
    HisRegisteredpay getPrices(String token, HisRegisteredpay hisRegisteredpay) throws Exception {
        return hisRegisteredpayService.getPrice(hisRegisteredpay);
    }


}

