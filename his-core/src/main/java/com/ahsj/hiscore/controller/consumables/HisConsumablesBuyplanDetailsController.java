package com.ahsj.hiscore.controller.consumables;

import com.ahsj.hiscore.entity.HisConsumablesBuyplan;
import com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails;
import com.ahsj.hiscore.services.HisConsumablesBuyplanDetailsService;
import com.ahsj.hiscore.services.HisConsumablesBuyplanService;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/hisconsumablesbuyplandetails")
public class HisConsumablesBuyplanDetailsController extends BaseController {

    @Autowired
    HisConsumablesBuyplanDetailsService hisConsumablesBuyplanDetailsService;

    @Autowired
    HisConsumablesBuyplanService hisConsumablesBuyplanService;


    /**
     *@Description 新增
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/5
     *@Time 9:35
    */
    @RequestMapping(value = "/save.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HttpRequest request,
                                @RequestParam(value = "ids",required = true) Long[] ids,
                                @RequestParam(value = "numbers",required = true )Integer[] numbers,
                                @RequestParam(value = "personInCharge",required = true )String personInCharge, //负责人
                                @RequestParam(value = "expectedTime",required = true )String expectedTime, //预期时间
                                @RequestParam(value = "prices",required = true )Double[] prices //价格
    ) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(personInCharge)){
            return MessageUtil.createMessage(false,"请输入负责人姓名");
        }else if(EmptyUtil.Companion.isNullOrEmpty(expectedTime)){
            return MessageUtil.createMessage(false,"请设置预定采购日期");
        }else {
            //存到详细表（ids，调入数目，采购计划编号）&存到采购计划记录表（负责人，预期时间，价格s）
            hisConsumablesBuyplanDetailsService.saveOrUpdate(ids,numbers,personInCharge,expectedTime,prices);
            return MessageUtil.createMessage(true,"添加采购计划成功");
        }
    }



    /**
     *@Description 耗材采购详情信息数据
     *@MethodName details
     *@Params [hisConsumablesBuyplanDetails]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:01
    **/
    @RequestMapping(value = "/details.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public PageBean<HisConsumablesBuyplanDetails> details (HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails) throws Exception{
        PageBean<HisConsumablesBuyplanDetails> pageBean = new PageBean<HisConsumablesBuyplanDetails>();
        pageBean.setParameter(hisConsumablesBuyplanDetails);
        pageBean = hisConsumablesBuyplanDetailsService.details(pageBean);
        return pageBean;
    }


    /**
     *@Description 保存更新的详细信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/5
     *@Time 14:04
    */
    @RequestMapping(value = "/saveDetails.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message saveDetails(@RequestParam(value = "ids",required = true) Long[] ids,
                               @RequestParam(value = "numbers",required = true )Integer[] numbers,
                               @RequestParam(value = "prices",required = true)Double[] prices,
                               @RequestParam(value = "buyplanCode",required = true)String buyplanCode) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(numbers) || EmptyUtil.Companion.isNullOrEmpty(prices)){
            return MessageUtil.createMessage(false,"请将耗材的单价和数量信息填写完整");
        }else {
            hisConsumablesBuyplanDetailsService.saveDetails(ids,numbers,prices,buyplanCode);
            return MessageUtil.createMessage(true,"修改成功");
        }
    }

    /**
     *@Description 页面跳转
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/5
     *@Time 9:35
    */

    @RequestMapping("/details/index.ahsj")
    ModelAndView detailsIndex(String token, Long id){
        HisConsumablesBuyplan hisConsumablesBuyplan =hisConsumablesBuyplanService.selectById(id);
        //未完成采购入库计划
        if(hisConsumablesBuyplan.getCompletion()==2) {
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/buyplandetails");
            modelAndView.addObject("title", "耗材采购计划详细信息表");
            modelAndView.addObject("token", token);
            modelAndView.addObject("buyplanCode", hisConsumablesBuyplan.getBuyplanCode());
            return modelAndView;
        } else {
            //完成采购入库计划
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/buyplandetailscompletion");
            modelAndView.addObject("title", "耗材采购计划详细信息表");
            modelAndView.addObject("token", token);
            modelAndView.addObject("buyplanCode", hisConsumablesBuyplan.getBuyplanCode());
            return modelAndView;
        }
    }

    /**
     *@Description 耗材采购计划编辑页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/5
     *@Time 13:18
    */

    @RequestMapping("/edit/index.ahsj")
    ModelAndView editIndex(String token,String buyplanCode)throws Exception{
        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetailsList = hisConsumablesBuyplanDetailsService.selectByBuyplanCode(buyplanCode);
        ModelAndView modelAndView=new ModelAndView("backend/hiscore/consumables/onebuyplanlistedit");
        modelAndView.addObject("title","耗材采购计划详细信息编辑");
        modelAndView.addObject("token", token);
        modelAndView.addObject("buyplanCode", buyplanCode);
        modelAndView.addObject("hisConsumablesBuyplanDetailsList",hisConsumablesBuyplanDetailsList);
        return modelAndView;
    }


    /**
     *@Description 耗材计划删除
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/12
     *@Time 18:11
    */
    @RequestMapping(value = "/delete.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message delete(Map<String, Object>model, HttpRequest request,
                               @RequestParam(value = "ids",required = true) Long[] ids
    ) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(ids)){
            return MessageUtil.createMessage(false,"耗材信息错误");
        }else {
            hisConsumablesBuyplanDetailsService.delete(ids);
            return MessageUtil.createMessage(true,"修改成功");
        }
    }







}
