package com.ahsj.hiscore.controller.hosptalregist;
import com.ahsj.hiscore.entity.HisHosptalregist;
import com.ahsj.hiscore.services.*;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import core.message.Message;
@RestController
@RequestMapping("/api/hosptalregist/")
public class hosptalregistController  extends BaseController {
   @Autowired
    HisWradService hisWradService;
    @Autowired
    HisHosptalregistService hisHosptalregistService;
    @Autowired
    HisMedicalRecordService hisMedicalRecordService;
    @Autowired
    HisPatientService hisPatientService;
    @Autowired
    HisMedicalService hisMedicalService;

    @RequestMapping("add/index.ahsj")
    /**  
         *@Description 
           * @param token
         *@Author: dingli
         *@return    
         *@Date 2019/7/13
         *@Time 15:50
        **/
    ModelAndView addIndex(String token)throws Exception{

        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hosptalregist/add");
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
        modelAndView.addObject("loginName",getUserName());
        return modelAndView;
    }

    @RequestMapping(value = "getHisWard.ahsj")
    /**  
         *@Description 
           * @param departmentId
     * @param token
         *@Author: dingli
         *@return    
         *@Date 2019/7/13
         *@Time 15:51
        **/
    public String  getDoctor(Long departmentId,String token) throws Exception{
        return  JSON.toJSONString(hisWradService.selectHisWard());
    }

    @RequestMapping("toadd.ahsj")
    /**  
         *@Description 
           * @param token
     * @param hisHosptalregist
         *@Author: dingli
         *@return    
         *@Date 2019/7/13
         *@Time 16:20
        **/
    public Message addHosptalregist(String token, HisHosptalregist hisHosptalregist) throws Exception{
        return  hisHosptalregistService.saveOrUpdate(hisHosptalregist);

    }


}
