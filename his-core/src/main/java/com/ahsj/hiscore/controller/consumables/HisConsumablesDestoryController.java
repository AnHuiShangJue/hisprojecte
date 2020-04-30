package com.ahsj.hiscore.controller.consumables;

import com.ahsj.hiscore.entity.HisConsumablesDestory;
import com.ahsj.hiscore.services.HisConsumablesDestoryService;
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
import java.util.Map;

@Controller
@RequestMapping("/api/hisconsumablesdestory")
public class HisConsumablesDestoryController extends BaseController {
    @Autowired
    HisConsumablesDestoryService hisConsumablesDestoryService;

    /**
     *@Description 提交报损记录
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/9
     *@Time 14:03
    */

    @RequestMapping(value = "/index.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message index(Map<String, Object> model, HttpRequest request,
                         @RequestParam(value = "ids",required = true) Long[] ids,
                         @RequestParam(value = "numbers",required = true )Integer[] numbers,
                         @RequestParam(value = "reasons",required = false )String[] reasons,
                         @RequestParam(value = "destoryTypes",required = true )String[] destoryTypes
//                         @RequestParam(value = "validityPeriods",required = true )String[] validityPeriods
    ) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(ids))
            return MessageUtil.createMessage(false,"请将耗材入库信息填写完整");
        if (EmptyUtil.Companion.isNullOrEmpty(numbers))
            return MessageUtil.createMessage(false,"请填写报损数量");
//        return hisConsumablesDestoryService.index(ids,numbers,reasons,destoryTypes,validityPeriods);
        return hisConsumablesDestoryService.index(ids,numbers,reasons,destoryTypes);
    }


    /**
     *@Description 报损表list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/10
     *@Time 9:41
    */
    @RequestMapping(value = "/list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisConsumablesDestory> list (Map<String, Object> model, HttpServletRequest request, HisConsumablesDestory hisConsumablesDestory) throws Exception{
        PageBean<HisConsumablesDestory> pageBean = new PageBean<HisConsumablesDestory>();
        pageBean.setParameter(hisConsumablesDestory);
        pageBean = hisConsumablesDestoryService.list(pageBean);
        return pageBean;
    }


    /**
     *@Description 修改审核字段
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/10
     *@Time 14:51
    */
    @RequestMapping(value = "/review.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message review(Map<String, Object> model, HttpRequest request,
                         @RequestParam(value = "ids",required = true) Long[] ids
    ) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(ids))
            return MessageUtil.createMessage(false,"请选择报损记录");
//        return hisConsumablesDestoryService.index(ids,numbers,reasons,destoryTypes,validityPeriods);
        return hisConsumablesDestoryService.review(ids);
    }
    
    /**
     *@Description 审核未通过
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/16
     *@Time 15:58
    */
    @RequestMapping(value = "/unreview.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message unreview(Map<String, Object> model, HttpRequest request,
                          @RequestParam(value = "ids",required = true) Long[] ids
    ) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(ids))
            return MessageUtil.createMessage(false,"请选择报损记录");
//        return hisConsumablesDestoryService.index(ids,numbers,reasons,destoryTypes,validityPeriods);
        return hisConsumablesDestoryService.unreview(ids);
    }
    
    
    /**
     *@Description m and v  报损审核页面
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/10
     *@Time 9:51
    */

    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/destoryreview");
        modelAndView.addObject("title","耗材审核列表");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 报损详情页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/10
     *@Time 15:36
    */
    @RequestMapping("/destorydetails/index.ahsj")
    ModelAndView destoryIndex(String token,Long id){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/destorydetails");
        modelAndView.addObject("title","耗材审核列表");
        modelAndView.addObject("token",token);
        if (!EmptyUtil.Companion.isNullOrEmpty(id)){
            modelAndView.addObject("hisConsumablesDestory",hisConsumablesDestoryService.selectByPrimaryId(id));
        }
        return modelAndView;
    }

}
