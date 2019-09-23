package com.ahsj.hiscore.controller.dieIn24hours;

import com.ahsj.hiscore.entity.HisDieintwentyfour;
import com.ahsj.hiscore.services.HisDieintwentyfourService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/api/dieIn24Hours/")
public class HisDiein24HoursController extends BaseController {

    @Autowired
    HisDieintwentyfourService hisDieintwentyfourService;

    /**
     *@Description 24小时内死亡记录保存
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/15
     *@Time 17:32
    */
    @RequestMapping(value = "diein24hourSave.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message diein24hourSave (Map<String, Object> model, HttpServletRequest request, HisDieintwentyfour hisDieintwentyfour
//            , @RequestParam(value="id", required=false) Long id
            , @RequestParam(value="patientId", required=false) Long patientId
            , @RequestParam(value="doctorId", required=false) Long doctorId
            , @RequestParam(value="departmentId", required=false) Long departmentId
            , @RequestParam(value="bedId", required=false) Long bedId
//            , @RequestParam(value="name", required=false) String name
//            , @RequestParam(value="type", required=false) Integer type
//            , @RequestParam(value="spec", required=false) String spec
//            , @RequestParam(value="stock", required=false) Integer stock
//            , @RequestParam(value="price", required=false) Double price
//            , @RequestParam(value="isEnable", required=false) Integer isEnable
    ) throws Exception {
        hisDieintwentyfour.setDoctorid(doctorId);
        hisDieintwentyfour.setPatientid(patientId);
        hisDieintwentyfour.setDepartmentid(departmentId);
        hisDieintwentyfour.setBedid(bedId);
        Message aa = hisDieintwentyfourService.save(hisDieintwentyfour);

        return  aa;
    }



}
