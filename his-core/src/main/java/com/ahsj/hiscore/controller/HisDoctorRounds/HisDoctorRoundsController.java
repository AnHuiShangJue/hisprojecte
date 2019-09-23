package com.ahsj.hiscore.controller.HisDoctorRounds;

import com.ahsj.hiscore.entity.HisDoctorRounds;
import com.ahsj.hiscore.services.HisDoctorRoundsService;
import core.entity.BaseEntity;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hisdoctorrounds")
public class HisDoctorRoundsController extends BaseEntity {

    @Autowired
    HisDoctorRoundsService hisDoctorRoundsService;

    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request, HisDoctorRounds hisDoctorRounds
//            , @RequestParam(value="id", required=true) Long id
    ) throws Exception {
//        HisConsumables hisConsumables = new HisConsumables();
//        if(null != request.getParameter("id")){
//            hisConsumables.setId(id);
//        }
//        if(null != request.getParameter("name")){
//            hisConsumables.setName(name);
//        }
//        if(null != request.getParameter("type")){
//            hisConsumables.setType(type);
//        }
//        if(null != request.getParameter("spec")){
//            hisConsumables.setSpec(spec);
//        }
//        if(null != request.getParameter("stock")){
//            hisConsumables.setStock(stock);
//        }
//        if(null != request.getParameter("price")){
//            hisConsumables.setPrice(price);
//        }
//        if(null != request.getParameter("isEnable")){
//            hisConsumables.setIsEnable(isEnable);
//        }
        return  hisDoctorRoundsService.saveOrUpdate(hisDoctorRounds);
    }




}
