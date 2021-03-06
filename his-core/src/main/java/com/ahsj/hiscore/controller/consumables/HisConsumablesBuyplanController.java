package com.ahsj.hiscore.controller.consumables;


import com.ahsj.hiscore.entity.HisConsumablesBuyplan;
import com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails;
import com.ahsj.hiscore.services.HisConsumablesBuyplanDetailsService;
import com.ahsj.hiscore.services.HisConsumablesBuyplanService;
import core.entity.PageBean;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import core.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import utils.EmptyUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 耗材采购计划基础信息
 */


@Controller
@RequestMapping("/api/hisconsumablesbuyplan")
public class HisConsumablesBuyplanController{

    @Autowired
    HisConsumablesBuyplanService hisConsumablesBuyplanService;

    @Autowired
    HisConsumablesBuyplanDetailsService hisConsumablesBuyplanDetailsService;

    /**
     *@Description 新增或更新
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/3
     *@Time 11:16
    */
    @RequestMapping(value = "/saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate (HisConsumablesBuyplan hisConsumablesBuyplan) throws Exception {

        return  hisConsumablesBuyplanService.saveOrUpdate(hisConsumablesBuyplan);
    }




    /**
     *@Description 删除
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/4
     *@Time 10:02
    */

    @ResponseBody
    @RequestMapping(value = "/delete.ahsj", method = {RequestMethod.POST})
    public Message delete (@RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(!EmptyUtil.Companion.isNullOrEmpty(ids)){
            hisConsumablesBuyplanService.delete(ids);
            return MessageUtil.createMessage(true,"删除成功");
        }else{
            return MessageUtil.createMessage(false,"参数异常");
        }
    }
    
    
    


    /**
     *@Description 获取采购清单列表
     *@MethodName list
     *@Params [hisConsumablesBuyplan]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesBuyplan>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 12:52
    **/
    @RequestMapping(value = "/list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisConsumablesBuyplan> list (HisConsumablesBuyplan hisConsumablesBuyplan) throws Exception{
        PageBean<HisConsumablesBuyplan> pageBean = new PageBean<HisConsumablesBuyplan>();
        pageBean.setParameter(hisConsumablesBuyplan);
        pageBean = hisConsumablesBuyplanService.list(pageBean);
        return pageBean;
    }


    
    
    /**
     *@Description 跳转到页面
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/3
     *@Time 17:58
    */

    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView=new ModelAndView("backend/hiscore/consumables/consumablesbuylist");//第一个列表页面
        modelAndView.addObject("title","耗材采购计划信息");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("/add/index.ahsj")
    ModelAndView addIndex(String token){
        ModelAndView modelAndView=new ModelAndView("backend/hiscore/consumables/addplanlist");//新增计划页面
        modelAndView.addObject("title","耗材采购计划信息添加");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("/update/index.ahsj")
    ModelAndView updateIndex(String token,HisConsumablesBuyplan hisConsumablesBuyplan){
        ModelAndView modelAndView=new ModelAndView("backend/hiscore/consumables/buyplanupdate");//采购计划编辑更新页面

        if(!EmptyUtil.Companion.isNullOrEmpty(hisConsumablesBuyplan.getId())){
             hisConsumablesBuyplan = hisConsumablesBuyplanService.selectById(hisConsumablesBuyplan.getId());
            modelAndView.addObject("hisConsumablesBuyplan",hisConsumablesBuyplan);
        }else{
            modelAndView.addObject("hisConsumablesBuyplan",hisConsumablesBuyplan);
        }
        modelAndView.addObject("title","耗材采购计划信息编辑");
        modelAndView.addObject("token", token);
        return modelAndView;
    }





}
