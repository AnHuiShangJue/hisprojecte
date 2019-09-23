package com.ahsj.hiscore.controller.hisProject;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisInspectionCombinationController
 * <p>
 * Date:     2019/7/11 16:44
 *
 * @author XJP
 * @create 2019/7/11
 * @since 1.0.0
 */

@Controller
@RequestMapping("/api/hisPproject/combination")
public class HisInspectionCombinationController extends BaseController {

    @Autowired
    HisProjectService hisProjectService;
    @Autowired
    HisInspectionCombinationService hisInspectionCombinationService;
    @Autowired
    HisInspecProjectService hisInspecProjectService;


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入收费组合项目添加页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:21
     **/
    @GetMapping("/list/index.ahsj")
    public ModelAndView addIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/combination");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 增加收费组合项目页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:21
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView listIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/combinationList");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [id, token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:21
     **/
    @GetMapping("/update/index.ahsj")
    public ModelAndView UpdateIndex(@RequestParam("id") Long id, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/combinationUpdate");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [id, token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:21
     **/
    @GetMapping("/update/combinationProjectid.ahsj")
    public ModelAndView combinationProjectid(@RequestParam("id") Long id, String token) throws Exception {
        HisInspectionCombination hisInspectionCombination = hisInspectionCombinationService.selectByPrimaryKey(id);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/updatecombinationUpdate");
        modelAndView.addObject("hisInspectionCombination", hisInspectionCombination);
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入收费项目组详情信息修改页面
     * @Params [token, hisInspecProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:21
     **/
    @GetMapping("/update/hisInspecProject.ahsj")
    public ModelAndView queryCombinationByProjectAndInspectionId(String token, HisInspecProject hisInspecProject) throws Exception {
        HisInspecProject combination = hisInspecProjectService.queryCombinationByProjectAndInspectionId(hisInspecProject);
        HisProject hisProject = hisProjectService.selectByPrimaryKey(combination.getProjectId());
        HisInspectionCombination hisInspectionCombination = hisInspectionCombinationService.selectByPrimaryKey(combination.getInspectionId());
        hisProject.setCombinationName(hisInspectionCombination.getName());
        hisProject.setHisProjectnum(combination.getNum());
        hisProject.setInspectionId(combination.getId());
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/updateCombination");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisProject", hisProject);
        modelAndView.addObject("hisInspectionCombination", hisInspectionCombination);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入收费项目详情页面
     * @Params [token, id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:22
     **/
    @GetMapping("/select/combinationProject.ahsj")
    public ModelAndView SelectCombination(String token, @RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/combinationProject");
        modelAndView.addObject("token", token);
        modelAndView.addObject("id", id);
        return modelAndView;
    }


    /**
     * @return core.message.Message
     * @功能说明 批量保存项目组合项目明细信息
     * @Params [ids, nums, hisProjectOrderNums, combinationNumber, combinationName]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:22
     **/
    @PostMapping("/save.ahsj")
    @ResponseBody
    public Message save(@RequestParam("ids[]") Long[] ids,
                        @RequestParam("nums[]") Integer[] nums,
                        @RequestParam("hisProjectOrderNums[]") Integer[] hisProjectOrderNums,
                        @RequestParam("combinationNumber") String combinationNumber,
                        @RequestParam("combinationName") String combinationName
    ) throws Exception {
        return hisInspectionCombinationService.updateSelectiveList(ids, nums, combinationName, combinationNumber, hisProjectOrderNums);
    }


    /**
     * @return core.message.Message
     * @功能说明 加入收费项目明细 保存
     * @Params [ids, nums, hisProjectOrderNums, combinationNumber, combinationName, combinationId]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:23
     **/
    @PostMapping("add/save.ahsj")
    @ResponseBody
    public Message addSave(@RequestParam("ids[]") Long[] ids,
                           @RequestParam("nums[]") Integer[] nums,
                           @RequestParam("hisProjectOrderNums[]") Integer[] hisProjectOrderNums,
                           @RequestParam("combinationNumber") String combinationNumber,
                           @RequestParam("combinationName") String combinationName, @RequestParam("combinationId") Long combinationId
    ) throws Exception {
        return hisInspectionCombinationService.addUpdateSelectiveList(ids, nums, combinationName, combinationNumber, hisProjectOrderNums, combinationId);
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisInspectionCombination>
     * @功能说明 初始化 分页
     * @Params [hisInspectionCombination]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:23
     **/
    @PostMapping("/list.ahsj")
    @ResponseBody
    public PageBean<HisInspectionCombination> queryList(HisInspectionCombination hisInspectionCombination) throws Exception {
        PageBean<HisInspectionCombination> pageBean = new PageBean<>();
        pageBean.setParameter(hisInspectionCombination);
        PageBean<HisInspectionCombination> hisProjectPageBean = hisInspectionCombinationService.queryList(pageBean);
        return hisProjectPageBean;
    }


    /**
     * @return core.message.Message
     * @功能说明 处理修改收费组合项目
     * @Params [hisInspectionCombination]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:23
     **/
    @PostMapping("/update/HisProject/updatecombination.ahsj")
    @ResponseBody
    public Message queryCombination(HisInspectionCombination hisInspectionCombination) throws Exception {
        return hisInspectionCombinationService.updateByPrimaryKeySelective(hisInspectionCombination);
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明 收费项目组详细信息
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:24
     **/
    @PostMapping("/list/query.ahsj")
    @ResponseBody
    public PageBean<HisProject> queryCombinationLists(HisProject hisProject) throws Exception {
        PageBean<HisProject> pageBean = new PageBean<>();
        pageBean.setParameter(hisProject);
        return hisProjectService.queryCombinationIds(pageBean);
    }


    /**
     * @return core.message.Message
     * @功能说明 批量设置状态 项目组合状态
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:24
     **/
    @ResponseBody
    @PostMapping("/setDisable.ahsj")
    public Message updateSetDisable(@RequestParam("ids[]") Long[] ids) throws Exception {
        return hisInspectionCombinationService.updateSetDisable(ids);
    }


    /**
     * @return core.message.Message
     * @功能说明 批量删除 项目组合信息
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:24
     **/
    @ResponseBody
    @PostMapping("/delete/HisCombination.ahsj")
    public Message deleteHisCombination(@RequestParam("ids[]") Long[] ids) throws Exception {
        return hisInspectionCombinationService.deleteHisCombinationByIds(ids);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入新增收费项目组添加明细时 查询已有的明细
     * @Params [id, token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:24
     **/
    @GetMapping("/add/combination/HisCombination.ahsj")
    public ModelAndView selectHisCombination(@RequestParam("id") Long id, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/addCombination");
        HisInspectionCombination combination = hisInspectionCombinationService.selectByPrimaryKey(id);
        List<HisProject> hisProjects = hisInspectionCombinationService.selectInspecProjectList(id);
        modelAndView.addObject("combinationss", combination);
        modelAndView.addObject("token", token);
        modelAndView.addObject("combinationId", id);
        modelAndView.addObject("hisProjects", hisProjects);
        return modelAndView;

    }


    /**
     * @return core.message.Message
     * @功能说明 批量删除收费项目明细
     * @Params [ids, id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    @PostMapping("/delete/HisProject/ids.ahsj")
    @ResponseBody
    public Message deleteHisProject(@RequestParam("ids[]") Long[] ids, @RequestParam("id") Long id) throws Exception {
        return hisInspecProjectService.deleteByIds(ids, id);
    }


    /**
     * @return core.message.Message
     * @功能说明 修改收费项目明细项目数量
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    @PostMapping("/updateHisProject.ahsj")
    @ResponseBody
    public Message updateHisProject(HisProject hisProject) throws Exception {
        return hisInspectionCombinationService.updateHisProject(hisProject);
    }

    /**
     *@Description 查询所有项目组合
     *@Params [token]
     *@return java.lang.String
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 16:42
    **/
    @RequestMapping(value = "getProject.ahsj", method = {RequestMethod.GET})
    @ResponseBody
    public String  getProject(String token) throws Exception{
        String ab = JSON.toJSONString(hisInspectionCombinationService.selectCombineProject());
        return  ab;
    }

    /**
     *@Description 查看门诊病历模板中的组合项目明细
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 17:33
     **/
    @RequestMapping("viewDetailInMedicalTemplate/index.ahsj")
    ModelAndView viewDetailInMedicalTemplate(String token,@RequestParam(value = "combineId",required = true)Long combineId)throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/viewDetailInMedicalTemplate");
        modelAndView.addObject("title", "查看组合项目明细（View portfolio details）");
        modelAndView.addObject("token", token);
        modelAndView.addObject("combineId", combineId);
        return modelAndView;
    }

    /**
     *@Description 查看门诊病历模板中的组合项目明细
     *@Params [hisPrescription]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 17:41
     **/
    @RequestMapping("viewDetailInMedicalTemplate.ahsj")
    @ResponseBody
    public PageBean<HisInspectionCombination> viewDetailInMedicalTemplate(HisInspectionCombination hisInspectionCombination) throws Exception {
        PageBean<HisInspectionCombination> pageEntity = new PageBean<HisInspectionCombination>();
        pageEntity.setParameter(hisInspectionCombination);
        return hisInspectionCombinationService.viewDetailInMedicalTemplate(pageEntity);
    }

}
