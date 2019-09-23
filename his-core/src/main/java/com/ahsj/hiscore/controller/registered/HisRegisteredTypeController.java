package com.ahsj.hiscore.controller.registered;

import com.ahsj.hiscore.controller.BaseOAController;
import com.ahsj.hiscore.entity.HisRegisteredType;
import com.ahsj.hiscore.services.HisRegisteredTypeService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @program: his
 * @description:HisRegisteredType
 * @author: chenzhicai
 * @create: 2019-07-01-17-35
 **/
@Controller
@RequestMapping("/api/registeredtype/")
public class HisRegisteredTypeController extends BaseOAController {

    @Autowired
    HisRegisteredTypeService hisRegisteredTypeService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 列表页面
     * @Params [token]
     * @Author chenzhicai
     * @Date 2019/7/1
     * @Time 5:38 PM
     **/
    @RequestMapping("list/index.ahsj")
    ModelAndView listrIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/registeredtype/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 页面初始化
     * @Params [token, id]
     * @Author chenzhicai
     * @Date 2019/7/1
     * @Time 5:38 PM
     **/
    @RequestMapping("update/index.ahsj")
    ModelAndView updateIndex(String token, Long id) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/registeredtype/list");
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            modelAndView.addObject("registeredtype", hisRegisteredTypeService.selectById(id));
        } else {
            modelAndView.addObject("registeredtype", new HisRegisteredType());
        }
        modelAndView.addObject("title", "挂号类型信息添加");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/2
     *@Time 14:45
    */

    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisRegisteredType> list (Map<String, Object> model, HttpServletRequest request, HisRegisteredType hisRegisteredType) throws Exception{
        PageBean<HisRegisteredType> pageBean = new PageBean<HisRegisteredType>();
        pageBean.setParameter(hisRegisteredType);
        pageBean = hisRegisteredTypeService.list(pageBean);
        return pageBean;
    }
    
    /**
     *@Description 跳转到update页面
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/2
     *@Time 16:28
    */
    @RequestMapping("toupdate/index.ahsj")
    ModelAndView ToUpdateIndex(String token, Long id) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/registeredtype/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            modelAndView.addObject("registeredtype", hisRegisteredTypeService.selectById(id));
        } else {
            modelAndView.addObject("registeredtype", new HisRegisteredType());
        }
        modelAndView.addObject("title", "挂号类型信息添加");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     *@Description 更新或新增
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/2
     *@Time 21:10
    */

    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id
            , @RequestParam(value="registerId", required=false) Long registerId
            , @RequestParam(value="typeId", required=false) Integer typeId
            , @RequestParam(value="orgId", required=false) Integer orgId
            , @RequestParam(value="money", required=false) BigDecimal money
            , @RequestParam(value="moneyType", required=false) Integer moneyType
            , @RequestParam(value="timeType", required=false) Integer timeType
    ) throws Exception {
        HisRegisteredType hisRegisteredType = new HisRegisteredType();
        if(null != request.getParameter("id")){
            hisRegisteredType.setId(id);
        }
        if(null != request.getParameter("registerId")){
            hisRegisteredType.setRegisterId(registerId);
        }
        if(null != request.getParameter("typeId")){
            hisRegisteredType.setTypeId(typeId);
        }
        if(null != request.getParameter("orgId")){
            hisRegisteredType.setOrgId(orgId);
        }
        if(null != request.getParameter("money")){
            hisRegisteredType.setMoney(money);
        }
        if(null != request.getParameter("moneyType")){
            hisRegisteredType.setMoneyType(moneyType);
        }
        if(null != request.getParameter("timeType")){
            hisRegisteredType.setTimeType(timeType);
        }

        return  hisRegisteredTypeService.saveOrUpdate(hisRegisteredType);
    }

    /**
     *@Description 删除
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/4
     *@Time 18:32
    */

    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisRegisteredTypeService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }


}

    