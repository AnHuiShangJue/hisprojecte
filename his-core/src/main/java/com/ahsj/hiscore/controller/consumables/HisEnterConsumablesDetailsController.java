package com.ahsj.hiscore.controller.consumables;

import com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails;
import com.ahsj.hiscore.entity.HisEnterConsumablesDetails;
import com.ahsj.hiscore.services.HisConsumablesBuyplanDetailsService;
import com.ahsj.hiscore.services.HisEnterConsumablesDetailsService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 耗材采购入库信息
 */
@Controller
@RequestMapping("/api/hisenterconsumables")
public class HisEnterConsumablesDetailsController extends BaseController {

    @Autowired
    HisEnterConsumablesDetailsService hisEnterConsumablesDetailsService;

    @Autowired
    HisConsumablesBuyplanDetailsService hisConsumablesBuyplanDetailsService;


    /**
     *@Description 耗材入库
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/5
     *@Time 21:18
    */
    @RequestMapping(value = "/mediEnter.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message mediEnter(Map<String, Object>model, HttpRequest request,
                             @RequestParam(value = "ids",required = true) Long[] ids,
                             @RequestParam(value = "numbers",required = true )Integer[] numbers,
                             @RequestParam(value = "prices",required = true)Double[] prices,
                             @RequestParam(value = "buyplanCode",required = true)String buyplanCode,
                             @RequestParam(value = "validityPeriods",required = true)String[] validityPeriods) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(numbers) || EmptyUtil.Companion.isNullOrEmpty(prices) || EmptyUtil.Companion.isNullOrEmpty(validityPeriods))
            return MessageUtil.createMessage(false,"请将耗材入库信息填写完整");
        return hisEnterConsumablesDetailsService.mediEnter(ids,numbers,prices,buyplanCode,validityPeriods);
    }

    /**
     *@Description list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/8
     *@Time 20:52
    */
    @ResponseBody
    @RequestMapping(value = "/list.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisEnterConsumablesDetails> list (HisEnterConsumablesDetails hisEnterConsumablesDetails) throws Exception{
        PageBean<HisEnterConsumablesDetails> pageBean = new PageBean<HisEnterConsumablesDetails>();
        pageBean.setParameter(hisEnterConsumablesDetails);
        return hisEnterConsumablesDetailsService.list(pageBean);
    }

    /**
     *@Description 查询某种耗材的入库记录
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/9
     *@Time 10:04
    */
    @ResponseBody
    @RequestMapping(value = "/onelist.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisEnterConsumablesDetails> onelist (Map<String, Object> model, HttpServletRequest request
            ,@RequestParam(value = "consumablesCode",required = true) String consumablesCode
    ) throws Exception{
        return hisEnterConsumablesDetailsService.onelist(consumablesCode);
    }



    /**
     *@Description M$V
     *@Params
     *@return
     *@Author jin
     *@Date 2019/6/23
     *@Time 11:13
    */
    @RequestMapping("/enterconsumables/index.ahsj")
    ModelAndView enterconsumablesIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/enterconsumables");
        modelAndView.addObject("title","耗材入库列表");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description
     *@MethodName mediEnterSure
     *@Params [token, buyplanId]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author XJP
     *@Date 2020/4/24
     *@Time 17:38
    **/
    @RequestMapping("/mediEnterSure/index.ahsj")
    ModelAndView mediEnterSure(String token,Long id)throws Exception{
        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetailsList = hisConsumablesBuyplanDetailsService.selectByBuyplanforList(id);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/enterconsumablesfromplan");
        modelAndView.addObject("title","耗材入库确认");
        modelAndView.addObject("token",token);
        modelAndView.addObject("hisConsumablesBuyplanDetailsList",hisConsumablesBuyplanDetailsList);
        return modelAndView;
    }

}

