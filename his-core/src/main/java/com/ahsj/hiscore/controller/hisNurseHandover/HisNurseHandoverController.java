package com.ahsj.hiscore.controller.hisNurseHandover;


import com.ahsj.hiscore.entity.HisNurseHandover;
import com.ahsj.hiscore.entity.HisNurseHandoverPatientInfo;
import com.ahsj.hiscore.services.HisNurseHandoverPatientInfoService;
import com.ahsj.hiscore.services.HisNurseHandoverService;
import core.controller.BaseController;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/hisNurseHandover")
public class HisNurseHandoverController extends BaseController {
    @Autowired
    HisNurseHandoverService hisNurseHandoverService;

    @Autowired
    HisNurseHandoverPatientInfoService hisNurseHandoverPatientInfoService;

    /**
     *@Description 保存交接单信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/15
     *@Time 11:11
    */

    @ResponseBody
    @PostMapping("/saveHandover.ahsj")
    public Message save (Map<String, Object> model, HttpServletRequest request, HisNurseHandover hisNurseHandover, List<HisNurseHandoverPatientInfo> hisNurseHandoverPatientInfoList
//            , @RequestParam(value="id", required=true) Long id
//            , @RequestParam(value="name", required=false) String name
//            , @RequestParam(value="type", required=false) Integer type
//            , @RequestParam(value="spec", required=false) String spec
//            , @RequestParam(value="stock", required=false) Integer stock
//            , @RequestParam(value="price", required=false) Double price
//            , @RequestParam(value="isEnable", required=false) Integer isEnable
    ) throws Exception {
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
        if (EmptyUtil.Companion.isNullOrEmpty(hisNurseHandover)){
            return MessageUtil.createMessage(false,"保存失败");

        }else {
            hisNurseHandoverPatientInfoService.save(hisNurseHandoverPatientInfoList);
            hisNurseHandoverService.save(hisNurseHandover);
            return MessageUtil.createMessage(true,"保存成功");

        }

    }




}
