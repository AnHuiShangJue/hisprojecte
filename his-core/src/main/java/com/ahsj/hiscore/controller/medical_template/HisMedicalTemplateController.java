package com.ahsj.hiscore.controller.medical_template;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.model.HisMedicalModel;
import com.ahsj.hiscore.services.HisMedicalTemplateService;
import com.ahsj.hiscore.services.HisPrescriptionService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@Description 病历模板控制器
 *@Params
 *@return
 *@Author zhushixiang
 *@Date 2019-09-14
 *@Time 9:00
**/
@Controller
@RequestMapping("/api/medicalTemplate/")
public class HisMedicalTemplateController extends BaseController {
    @Autowired
    HisMedicalTemplateService hisMedicalTemplateService;

    @Autowired
    HisPrescriptionService hisPrescriptionService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 病历模板list界面
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-09-14
     * @Time 9:15
     **/
    @RequestMapping("list/index.ahsj")
    ModelAndView medicalTemplateIndex(String token,boolean isOperate) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medical_template/list");
        if(EmptyUtil.Companion.isNullOrEmpty(isOperate))
            isOperate = false;
        modelAndView.addObject("title", "病历模板管理");
        modelAndView.addObject("token", token);
        modelAndView.addObject("isOperate", isOperate);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 添加/编辑新的病历模板
     * @Params [hisPrescription]
     * @Author zhushixiang
     * @Date 2019-09-14
     * @Time 9:28
     **/
    @RequestMapping("saveOrUpdateTemplate/index.ahsj")
    public ModelAndView addTemplate(HisMedicalTemplate hisMedicalTemplate) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medical_template/saveOrUpdateTemplate");
        HisMedicalTemplate tmp = new HisMedicalTemplate();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalTemplate.getId())||!EmptyUtil.Companion.isNullOrEmpty(hisMedicalTemplate.getTreeId())) {
            tmp = hisMedicalTemplateService.selectByTreeId(hisMedicalTemplate.getTreeId());
        } else {
            tmp.setParentId(hisMedicalTemplate.getParentId());
        }
        modelAndView.addObject("hisMedicalTemplateInfo", tmp);
        modelAndView.addObject("token", hisMedicalTemplate.getToken());
        return modelAndView;
    }

    /**
     * @return
     * @Description 添加/编辑新的病历模板
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-14
     * @Time 9:46
     **/
    @RequestMapping("saveOrUpdateTemplate.ahsj")
    @ResponseBody
    public Message saveOrUpdateTemplate(HisMedicalTemplate hisMedicalTemplate) throws Exception {
        return hisMedicalTemplateService.saveOrUpdateTemplate(hisMedicalTemplate);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 为病历模板添加详细
     * @Params [token, prescriptionId]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:09
     **/
    @RequestMapping("saveOrUpdateDetails/index.ahsj")
    public ModelAndView saveOrUpdateDetails(String token,
                                                    @RequestParam(value = "treeId", required = true) String treeId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medical_template/saveOrUpdateDetails");
        HisMedicalTemplate hisMedicalTemplate = hisMedicalTemplateService.selectByTreeId(treeId);

        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalTemplate)) {
            modelAndView.addObject("hisMedicalTemplate", new HisMedicalTemplate());
        }else {
            modelAndView.addObject("hisMedicalTemplate", hisMedicalTemplate);
        }
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 显示病历模板信息（树节点显示）
     *@Params [lv]
     *@return java.util.List<com.ahsj.hiscore.entity.TreeEntity>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 10:16
    **/
    @RequestMapping("treeCode.ahsj")
    @ResponseBody
    public List<TreeEntity> treeCode(Integer lv) throws Exception {
        if (lv == null) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setId("1");
            treeEntity.setpId("0");
            treeEntity.setName("病历模板菜单");
            treeEntity.setIsParent(true);
            treeEntity.setOpen(false);
            List<TreeEntity> lst = new ArrayList<>();
            lst.add(treeEntity);
            return lst;
        }
        return hisMedicalTemplateService.selectTrreCode();
    }

    /**
     *@Description 保存/修改病历模板明细
     *@Params [hisMedicalTemplate]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 11:26
    **/
    @RequestMapping("saveOrUpdateDetails.ahsj")
    @ResponseBody
    public Message saveOrUpdateDetails(HisMedicalTemplate hisMedicalTemplate) throws Exception {
        //1:个人使用
        if(hisMedicalTemplate.getPermissionType() == 1){
            hisMedicalTemplate.setTargetId(getId());
        }else if(hisMedicalTemplate.getPermissionType() == 2){
            hisMedicalTemplate.setTargetId(Long.valueOf(getDeptId()));
        }else if(hisMedicalTemplate.getPermissionType() == 3){
            hisMedicalTemplate.setTargetId(-1L);
        }
        return hisMedicalTemplateService.saveOrUpdateDetails(hisMedicalTemplate);
    }

    /**
     *@Description 根据TreeId查询相关信息
     *@Params [id]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 14:55
    **/
    @RequestMapping("listByTreeId.ahsj")
    @ResponseBody
    HisMedicalTemplate listByTreeId(String treeId,String token)throws Exception {
        return hisMedicalTemplateService.selectByTreeId(treeId);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 门诊使用病历模板list界面跳转
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-09-14
     * @Time 9:15
     **/
    @RequestMapping("listForMedical/index.ahsj")
    ModelAndView listForMedical(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medical_template/listForMedical");
        modelAndView.addObject("title", "使用病历模板（Use medical record template）");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginUserId", getId());
        modelAndView.addObject("departmentId", getDeptId());
        return modelAndView;
    }

    /**
     *@Description 门诊使用病历模板list界面
     *@Params [model, request, hisMedicalOrder]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 16:38
    **/
    @RequestMapping("listForMedical.ahsj")
    @ResponseBody
    public PageBean<HisMedicalTemplate> listForMedical (Map<String, Object> model, HttpServletRequest request, HisMedicalTemplate hisMedicalTemplate) throws Exception{
        hisPrescriptionService.searchIsAvailable();
        PageBean<HisMedicalTemplate> pageBean = new PageBean<HisMedicalTemplate>();
        pageBean.setParameter(hisMedicalTemplate);
        return hisMedicalTemplateService.listForMedical(pageBean);
    }

    /**
     *@Description 查看门诊病历模板中的病历明细
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 17:33
     **/
    @RequestMapping("viewDetailInMedicalTemplate/index.ahsj")
    ModelAndView viewDetailInMedicalTemplate(String token, @RequestParam(value = "id",required = true)Long id)throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medical_template/viewDetailInMedicalTemplate");
        HisMedicalTemplate hisMedicalTemplate = hisMedicalTemplateService.selectById(id);
        modelAndView.addObject("title", "查看病历明细（View medical details）");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisMedicalTemplate", hisMedicalTemplate);
        return modelAndView;
    }

    /**
     *@Description 删除模板
     *@Params [id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 10:18
    **/
    @RequestMapping("delete.ahsj")
    @ResponseBody
    public Message delete(Long[] id) throws Exception{
        return hisMedicalTemplateService.delete(id);
    }


    /**
     *@Description 保存当前信息为个人模板
     *@Params [hisMedicalModel]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 12:55
    **/
    @PostMapping("saveOrUpdatePersonalTemplate.ahsj")
    @ResponseBody
    Message saveOrUpdatePersonalTemplate(@RequestBody HisMedicalModel hisMedicalModel) throws Exception {
        HisMedical hisMedical = hisMedicalModel.getMediCard();
        Long recordId = hisMedicalModel.getRecordId();
        List<HisMedicationDetails> detailsList = hisMedicalModel.getMediDetail();
        List<HisRecordProject> projects = hisMedicalModel.getProjects();
        return hisMedicalTemplateService.saveOrUpdatePersonalTemplate(hisMedical, detailsList, projects, recordId,getId(),getUserName());
    }

}
