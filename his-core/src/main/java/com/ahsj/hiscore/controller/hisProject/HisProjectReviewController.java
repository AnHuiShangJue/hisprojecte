package com.ahsj.hiscore.controller.hisProject;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.Result;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisProjectService;
import com.ahsj.hiscore.services.HisRecordProjectService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import core.controller.BaseController;


@Controller
@RequestMapping("/api/hisprojectreview/")
public class HisProjectReviewController extends BaseController {

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    /**
     * @Description 进行诊疗项目病人list
     * @Author  muxu
     * @Date  2019/8/16
     * @Time 16:30
     * @Return
     * @Params
    **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    public PageBean<HisRecordProject> list(Map<String, Object> model, HttpServletRequest request, HisRecordProject HisRecordProject) throws Exception {
        PageBean<HisRecordProject> pageBean = new PageBean<HisRecordProject>();
        pageBean.setParameter(HisRecordProject);
        return hisRecordProjectService.ItemPatientList(pageBean);
    }
    
    /**
     * @Description 项目审核
     * @Author  muxu
     * @Date  2019/8/17
     * @Time 10:14
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @Params [model, request, HisRecordProject]
    **/

    @ResponseBody
    @RequestMapping(value = "projectreview.ahsj", method = {RequestMethod.POST})
    public PageBean<HisRecordProject> projectreview(Map<String, Object> model, HttpServletRequest request, HisRecordProject HisRecordProject) throws Exception {
        PageBean<HisRecordProject> pageBean = new PageBean<HisRecordProject>();
        pageBean.setParameter(HisRecordProject);
        return hisRecordProjectService.selectByMedicalRecordIdList(pageBean);
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/8/17
     * @Time 18:12
     * @Return
     * @Params
    **/
    @RequestMapping(value = "UpdateReviewStatus.ahsj")
    @ResponseBody
    public Message UpdateReviewStatus(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "id", required = true) Long id) throws Exception {
        if (null != request.getParameter("id")) {
            return hisRecordProjectService.UpdateReviewStatus(id);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @Description 获取项目打印信息
     * @Author  muxu
     * @Date  2019/8/19
     * @Time 10:49
     * @Return com.ahsj.hiscore.entity.HisRecordProject
     * @Params [model, request, id]
    **/
    @RequestMapping(value = "getProject.ahsj")
    @ResponseBody
    public HisRecordProject getProject(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "projectId", required = true) Long id) throws Exception {
        if (null != request.getParameter("projectId")) {
            return hisRecordProjectService.getProject(id);
        } else return null;
    }



    /**
     * @Description list modelAndView
     * @Author  muxu
     * @Date  2019/8/16
     * @Time 16:35
     * @Return
     * @Params
    **/
    @RequestMapping("list/index.ahsj")
    ModelAndView listIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/itempatientlist");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @Description 项目审核 modelAndView
     * @Author  muxu
     * @Date  2019/8/16
     * @Time 16:37
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token]
    **/
    @RequestMapping("projectreview/index.ahsj")
    ModelAndView projectreview(String token,HisRecordProject hisRecordProject) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/projectreview");
        //modelAndView.addObject("userName", getUserName());
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("medicalRecordNumber",hisMedicalRecordService.selectById(hisRecordProject.getRecordsId()).getMedicalRecordId());
        modelAndView.addObject("departmentId",getDeptId());
        return modelAndView;
    }
    /**
     * @Description 收费项目打印 modelAndView
     * @Author  muxu
     * @Date  2019/8/19
     * @Time 9:40
     * @Return
     * @Params
    **/
    @RequestMapping("projectprinting/index.ahsj")
    ModelAndView projectprinting(String token,HisRecordProject hisRecordProject) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/projectprinting");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("id",hisRecordProject.getId());
        return modelAndView;
    }
}
