package com.ahsj.hiscore.controller.consumables;

import com.ahsj.hiscore.entity.HisExitConsumablesDetails;
import com.ahsj.hiscore.services.HisConsumablesDetailsService;
import com.ahsj.hiscore.services.HisExitConsumablesDetailsService;
import com.ahsj.hiscore.services.HisHospitalConsumablesDetailsService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/hisexitconsumables/")
public class HisExitConsumablesDetailsController extends BaseController {

    @Autowired
    HisExitConsumablesDetailsService hisExitConsumablesDetailsService;
    
    @Autowired
    HisHospitalConsumablesDetailsService hisHospitalConsumablesDetailsService;

    /**
     *@Description 耗材出库的list
     *@Params
     *@return 
     *@Author jin
     *@Date 2019/7/9
     *@Time 9:42
    */
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisExitConsumablesDetails> list (Map<String, Object> model, HttpServletRequest request, HisExitConsumablesDetails hisExitConsumablesDetails) throws Exception{
        PageBean<HisExitConsumablesDetails> pageBean = new PageBean<HisExitConsumablesDetails>();
        pageBean.setParameter(hisExitConsumablesDetails);
        return hisExitConsumablesDetailsService.list(pageBean);
    }

    /**
     *@Description 智能出库
     *@Params
     *@return
     *@Author jin
     *@Date 2019/6/25
     *@Time 10:49
     */
    @RequestMapping(value = "exitConsumablesSmart.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message exitConsumables (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
            , @RequestParam(value="numbers", required=true) Integer[] numbers
            , @RequestParam(value="personInCharge", required=false) String personInCharge
            , @RequestParam(value="expectedTime", required=false) Date expectedTime
    ) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(personInCharge)){
            personInCharge = getUserName();
        }

        String ids1 = request.getParameter("ids[]");
        if(null != request.getParameter("ids")){
            return  hisExitConsumablesDetailsService.exitConsumablesSmart(ids,numbers,personInCharge,expectedTime);
        }else if(!StringUtils.isEmpty( request.getParameter("ids[]"))) {
            return hisExitConsumablesDetailsService.exitConsumablesSmart(ids, numbers, personInCharge, expectedTime);
        }else {
            return MessageUtil.createMessage(false,"请选择至少一条数据");
        }
    }

    /**
     *@功能说明 智能出库
     *@Params [model, request, ids, numbers, personInCharge, expectedTime]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/8/8
     *@Time 15:09
    **/
    @RequestMapping(value = "exitConsumablesSmarts.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message exitConsumabless (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids[]", required=true) Long[] ids
            , @RequestParam(value="personInCharge", required=false) String personInCharge
            , @RequestParam(value="expectedTime", required=false) Date expectedTime
    ) throws Exception {

        if (EmptyUtil.Companion.isNullOrEmpty(personInCharge)){
            personInCharge = getUserName();
        }
        if (EmptyUtil.Companion.isNullOrEmpty(ids)){
            return MessageUtil.createMessage(false,"请选择至少一条数据");
        }
        List<Integer> nums = hisHospitalConsumablesDetailsService.selectHisHospitalConsumablesDetailsByIds(ids);
        Integer[] numbers = nums.toArray(new Integer[nums.size()]);
        if(null != request.getParameter("ids[]")){
            return  hisExitConsumablesDetailsService.exitConsumablesSmarts(ids,numbers,personInCharge,expectedTime);
        }else {
            return MessageUtil.createMessage(false,"请选择至少一条数据");
        }
    }

    /**
     *@Description 手动出库
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/8
     *@Time 19:39
    */

    @RequestMapping(value = "exitConsumables.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message medicineExit (Map<String, Object> model, HttpRequest request
                                 ,@RequestParam(value = "ids",required = true) Long[] ids
                                 ,@RequestParam(value = "numbers",required = true )Integer[] numbers
                                 ,@RequestParam(value = "prices",required = true)Double[] prices
            , @RequestParam(value="personInCharge", required=false) String personInCharge
            , @RequestParam(value="expectedTime", required=false) Date expectedTime
    ) throws Exception {
        return  hisExitConsumablesDetailsService.exitConsumables(ids,numbers,prices,personInCharge,expectedTime);
    }

    /**
     *@Description 手动出库(M V)
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/8
     *@Time 18:47
    */
    @RequestMapping(value = "exitConsumables/index.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    ModelAndView exitConsumablesIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/exitconsumables");
        modelAndView.addObject("title","手动出库");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 出库记录list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/31
     *@Time 14:07
    */
    @RequestMapping(value = "exitconsumables/index.ahsj")
    ModelAndView exitRecord(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/exitconsumablesRecord");
        modelAndView.addObject("title","出库记录列表");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 护士站模块发起的耗材出库申请
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/7
     *@Time 15:50
     */
    @RequestMapping(value = "exitForHos/index.ahsj")
    ModelAndView exitForHos(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/exitConsumablesForHos");
        modelAndView.addObject("title","耗材出库申请");
        modelAndView.addObject("token",token);
        return modelAndView;
    }




    /**
     *@Description 跳转到出库详细页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/17
     *@Time 14:31
    */
    @RequestMapping(value = "exitDetails/index.ahsj")
    ModelAndView exitDetails(String token,String exitNumber){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/exitconsumablesRecordDetails");
        modelAndView.addObject("title","耗材出库记录详情");
        modelAndView.addObject("token",token);
        modelAndView.addObject("exitNumber",exitNumber);
        return modelAndView;
    }

    /**
     *@Description 出库记录详情的列表
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/17
     *@Time 14:35
    */
    @ResponseBody
    @RequestMapping(value = "listByNumber.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisExitConsumablesDetails> listByNumber (Map<String, Object> model, HttpServletRequest request, HisExitConsumablesDetails hisExitConsumablesDetails,String exitNumber) throws Exception{
        PageBean<HisExitConsumablesDetails> pageBean = new PageBean<HisExitConsumablesDetails>();
        pageBean.setParameter(hisExitConsumablesDetails);
        return hisExitConsumablesDetailsService.listByNumber(pageBean);
    }


    /**
     *@Description 跳转到耗材出库申请打印单
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/17
     *@Time 17:25
    */
    @RequestMapping(value = "printf/index.ahsj")
    ModelAndView printf(String token,String exitNumber,String hisHospitalManagerId){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumablesDetails/materialsOutbound");
        modelAndView.addObject("title","耗材出库申请单打印");
        modelAndView.addObject("token",token);
        modelAndView.addObject("exitNumber",exitNumber);//耗材申请编号
        modelAndView.addObject("hisHospitalManagerId",hisHospitalManagerId);//就诊记录编号
        return modelAndView;
    }


    /**
     *@Description 获取到耗材申请的数据 List
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/17
     *@Time 17:39
    */
    @PostMapping(value = "getExitConsumabes.ahsj")
    @ResponseBody
    public List<HisExitConsumablesDetails> getExitConsumabes(String token,HisExitConsumablesDetails hisExitConsumablesDetails) throws Exception {
        PageBean<HisExitConsumablesDetails> pageBean = new PageBean<HisExitConsumablesDetails>();
        pageBean.setParameter(hisExitConsumablesDetails);
        return hisExitConsumablesDetailsService.getExitConsumabes(pageBean);
    }
}
