package com.ahsj.hiscore.controller.medicalrecordword;

import com.ahsj.hiscore.entity.MedicalRecordWord;
import com.ahsj.hiscore.services.MedicalRecordWordService;
import com.ahsj.hiscore.services.TemplateMedialReocSerivce;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-20-16-46
 **/
@Controller
@RequestMapping("/api/medicalrecordword")
public class MedicalRecordWordController extends BaseController {

    @Autowired
    MedicalRecordWordService medicalRecordWordService;

    @Autowired
    TemplateMedialReocSerivce templateMedialReocSerivce;

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
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/inpatient_medical_record/listByHM_word");
        modelAndView.addObject("medicalNumber", medicalNumber);
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginUserId", getId());
        modelAndView.addObject("templatesList", templateMedialReocSerivce.listForMedical(getId()));
        modelAndView.addObject("commonTemplatesList", templateMedialReocSerivce.listForMedical(new Long(-1)));
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
    public PageBean<MedicalRecordWord> list(String token ,String medicalNumber,String fileName){
        PageBean<MedicalRecordWord> pageBean = new PageBean<MedicalRecordWord>();
        MedicalRecordWord medicalRecordWord = new MedicalRecordWord();
        medicalRecordWord.setHospitalNumber(medicalNumber);
        medicalRecordWord.setFileName(fileName);
        pageBean.setParameter(medicalRecordWord);
        return medicalRecordWordService.list(pageBean);
    }

}

    