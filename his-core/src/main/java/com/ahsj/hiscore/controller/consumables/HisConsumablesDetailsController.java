package com.ahsj.hiscore.controller.consumables;


import com.ahsj.hiscore.entity.HisConsumablesDetails;
import com.ahsj.hiscore.services.HisConsumablesDetailsService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api/hisconsumablesdetails/")
public class HisConsumablesDetailsController extends BaseController {

    @Autowired
    HisConsumablesDetailsService hisConsumablesDetailsService;

    /**
     * @return
     * @Description 分页查询
     * @Params
     * @Author jin
     * @Date 2019/6/28
     * @Time 20:48
     */
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageBean<HisConsumablesDetails> list(Map<String, Object> model, HttpServletRequest request, HisConsumablesDetails hisConsumablesDetails) throws Exception {
        PageBean<HisConsumablesDetails> pageBean = new PageBean<HisConsumablesDetails>();
        pageBean.setParameter(hisConsumablesDetails);
        pageBean = hisConsumablesDetailsService.list(pageBean);
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 分页查询  耗材申请
     * @Params [model, request, hisConsumablesDetails]
     * @Author XJP
     * @Date 2019/8/14
     * @Time 9:43
     **/
    @PostMapping("lists.ahsj")
    @ResponseBody
    public PageBean<HisConsumablesDetails> lists(HisConsumablesDetails hisConsumablesDetails) throws Exception {
        PageBean<HisConsumablesDetails> pageBean = new PageBean<>();
        pageBean.setParameter(hisConsumablesDetails);
        pageBean = hisConsumablesDetailsService.lists(pageBean);
        return pageBean;
    }

    /**
     * @return
     * @Description onelist
     * @Params
     * @Author jin
     * @Date 2019/7/31
     * @Time 11:11
     */
    @RequestMapping(value = "onelist.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageBean<HisConsumablesDetails> onelist(Map<String, Object> model, HttpServletRequest request, HisConsumablesDetails hisConsumablesDetails) throws Exception {
        PageBean<HisConsumablesDetails> pageBean = new PageBean<HisConsumablesDetails>();
        pageBean.setParameter(hisConsumablesDetails);
        pageBean = hisConsumablesDetailsService.onelist(pageBean);
        return pageBean;
    }


    /**
     * @return
     * @Description M$V
     * @Params
     * @Author jin
     * @Date 2019/6/28
     * @Time 21:27
     */

    @RequestMapping("consumablesdetails/index.ahsj")
    ModelAndView enterconsumablesIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/consumablesdetails");
        modelAndView.addObject("title", "耗材库存管理");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("oneconsumablesdetails/index.ahsj")
    ModelAndView oneEnterConsumablesIndex(String token, Long consumablesId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/oneconsumabledetails");
        modelAndView.addObject("title", "耗材详情");
        modelAndView.addObject("token", token);
        modelAndView.addObject("consumablesId", consumablesId);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 耗材申请详情
     * @Params [id, token]
     * @Author XJP
     * @Date 2019/8/15
     * @Time 18:20
     **/
    @GetMapping("/hisConsumablesDetails/consumablesDetailsId.ahsj")
    public ModelAndView consumablesDetailsId(@RequestParam("consumableNumber") String consumableNumber, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/exitConsumablesForApp");
        modelAndView.addObject("title", "耗材申请详情");
        modelAndView.addObject("token", token);
        modelAndView.addObject("consumableNumber", consumableNumber);
        return modelAndView;
    }

    /**
     * @return
     * @Description 报损页面跳转
     * @Params
     * @Author jin
     * @Date 2019/7/9
     * @Time 9:37
     */
    @RequestMapping(value = "destory/index.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    ModelAndView destoryIndex(String token, Long consumablesId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/destorychoose");
        modelAndView.addObject("title", "耗材报损");
        modelAndView.addObject("token", token);
        modelAndView.addObject("consumablesId", consumablesId);
        return modelAndView;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 根据ids 查询耗材库集合
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    @PostMapping("hisConsumablesDetails/byIds.ahsj")
    @ResponseBody
    public List<HisConsumablesDetails> getHisConsumablesDetailsByIds(@RequestParam("ids[]") Long[] ids) {
        List<HisConsumablesDetails> consumablesDetailsList = hisConsumablesDetailsService.getHisConsumablesDetailsByIds(ids);
        return consumablesDetailsList;
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 根据ids  nums  申请并保存耗材集合
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    @PostMapping("save/hisConsumablesDetails/byIds/nums.ahsj")
    @ResponseBody
    public Message getHisConsumablesDetailsByIdsAndNum(@RequestParam("ids") Long[] ids, @RequestParam("nums") Integer[] nums, @RequestParam("medicalRecordId") String medicalRecordId) throws Exception {
        Long id = getId();
        return hisConsumablesDetailsService.getHisConsumablesDetailsByIdsAndNum(ids, nums, id, medicalRecordId);
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 审核耗材出库
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    @PostMapping("hisConsumablesDetails/getHisConsumablesDetailsInfo.ahsj")
    @ResponseBody
    public PageBean<HisConsumablesDetails> getHisConsumablesDetailsInfo(HisConsumablesDetails hisConsumablesDetails) {
        System.out.println(hisConsumablesDetails.toString());
        PageBean<HisConsumablesDetails> pageBean = new PageBean<HisConsumablesDetails>();
        pageBean.setParameter(hisConsumablesDetails);
        pageBean = hisConsumablesDetailsService.getHisConsumablesDetailsInfo(pageBean);
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [hisConsumablesDetails]
     * @Author XJP
     * @Date 2019/8/15
     * @Time 20:37
     **/
    @PostMapping("hisConsumablesDetails/getHisConsumablesDetailsInfoss.ahsj")
    @ResponseBody
    public PageBean<HisConsumablesDetails> getHisConsumablesDetailsInfos(HisConsumablesDetails hisConsumablesDetails) throws Exception {
        PageBean<HisConsumablesDetails> pageBean = new PageBean<>();
        pageBean.setParameter(hisConsumablesDetails);
        pageBean = hisConsumablesDetailsService.getHisConsumablesDetailsInfos(pageBean);
        return pageBean;
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 页面引入
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    @GetMapping("getHisConsumablesDetailsInfo/list.ahsj")
    public ModelAndView list(String token, @RequestParam("medicalRecordId") String medicalRecordId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumablesDetails/consumableApplication");
        modelAndView.addObject("title", "耗材申请");
        modelAndView.addObject("token", token);
        modelAndView.addObject("medicalRecordId", medicalRecordId);
        modelAndView.addObject("id", getUserId());
        return modelAndView;
    }

}
