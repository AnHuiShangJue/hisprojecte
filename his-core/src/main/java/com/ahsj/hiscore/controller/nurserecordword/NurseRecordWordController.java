package com.ahsj.hiscore.controller.nurserecordword;

import com.ahsj.hiscore.entity.MedicalRecordWord;
import com.ahsj.hiscore.entity.NursingRecordWord;
import com.ahsj.hiscore.services.NursingRecordTemplateService;
import com.ahsj.hiscore.services.NursingRecordWordService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-21-16-43
 **/
@Controller
@RequestMapping("/api/nurserecordword/")
public class NurseRecordWordController extends BaseController {

    @Autowired
    NursingRecordTemplateService nursingRecordTemplateService;

    @Autowired
    NursingRecordWordService nursingRecordWordService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 大病历word版页面
     * @Params [token, medicalNumber]
     * @Author chenzhicai
     * @Date 2019-09-21
     * @Time 11:19
     **/

    @RequestMapping("list/index.ahsj")
    public ModelAndView listIndex(String token, String medicalNumber,Integer isReadyOnly) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/listByNC_word");
        modelAndView.addObject("medicalNumber", medicalNumber);
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginUserId", getId());
        modelAndView.addObject("commonTemplatesList", nursingRecordTemplateService.listForMedical(new Long(-1)));
        //1能操作，2不能操作
        modelAndView.addObject("isReadyOnly", isReadyOnly);
        return modelAndView;
    }


    /**
     *@Description 查询病历list
     *@Params [token, medicalNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicalRecordWord>
     *@Author chenzhicai
     *@Date 2019-09-21
     *@Time 11:21
     **/
    @RequestMapping("list.ahsj")
    @ResponseBody
    public PageBean<NursingRecordWord> list(String token , String medicalNumber,String fileName){
        PageBean<NursingRecordWord> pageBean = new PageBean<NursingRecordWord>();
        NursingRecordWord medicalRecordWord = new NursingRecordWord();
        medicalRecordWord.setHospitalNumber(medicalNumber);
        medicalRecordWord.setFileName(fileName);
        pageBean.setParameter(medicalRecordWord);
        return nursingRecordWordService.list(pageBean);
    }
}

    