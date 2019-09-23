package com.ahsj.hiscore.controller.dunningListController;

import com.ahsj.hiscore.services.HisDunningListService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;


@Controller
@RequestMapping("api/hisdunninglist/")
public class dunningListController extends BaseController {

    @Autowired
    HisDunningListService hisDunningListService;

    /**
     *@Description 保存缴费通知单信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/5
     *@Time 14:12
    */
    @RequestMapping(value = "save.ahsj",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message save(Map<String, Object> model, HttpServletRequest request,String token
//            , @RequestParam(value="id", required=false) Long id
            , @RequestParam(value="MedicalRecordId", required=false) String MedicalRecordId  //住院编号
            , @RequestParam(value="money", required=false) Double money   //缴费
            , @RequestParam(value="number", required=false) String number  //缴费单编号
    )throws Exception{

        Message aa = hisDunningListService.save(MedicalRecordId,money,number);
        return aa;

    }

}
