package com.ahsj.hiscore.controller.PreoperativeSummaryRecord;

import com.ahsj.hiscore.entity.HisDiagnosisTreatment;
import com.ahsj.hiscore.entity.PreoperativeSummaryRecord;
import com.ahsj.hiscore.services.PreoperativeSummaryRecordService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: PreoperativeSummaryRecordControllrt
 * <p>
 * Date:     2019/9/17 13:32
 *
 * @author XJP
 * @create 2019/9/17
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/preoperativesummaryrecord")
public class PreoperativeSummaryRecordController extends BaseController {

    @Autowired
    PreoperativeSummaryRecordService preoperativeSummaryRecordService;

    @GetMapping("/add/index.ahsj")
    public ModelAndView index(String token, PreoperativeSummaryRecord preoperativeSummaryRecord) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/preoperativesummaryrecord/addOrUpdate");//templates/backend/hiscore/hisdiagnosistreatment/addOrUpdate.html
        if (EmptyUtil.Companion.isNullOrEmpty(preoperativeSummaryRecord.getId())) {
            modelAndView.addObject("preoperativeSummaryRecord", preoperativeSummaryRecord);
            modelAndView.addObject("token", token);
            return modelAndView;
        } else {
            preoperativeSummaryRecord = preoperativeSummaryRecordService.selectByPrimaryKey(preoperativeSummaryRecord.getId());
            modelAndView.addObject("preoperativeSummaryRecord", preoperativeSummaryRecord);
            modelAndView.addObject("token", token);
            return modelAndView;
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 增添和修改术前记录信息
     * @Params [hisDiagnosisTreatment]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 10:20
     **/
    @PostMapping("/addOrUpdatePreoperativeSummaryRecord.ahsj")
    public Message addOrUpdatePreoperativeSummaryRecord(PreoperativeSummaryRecord PreoperativeSummaryRecord) throws Exception {
        return preoperativeSummaryRecordService.addOrUpdatePreoperativeSummaryRecord(PreoperativeSummaryRecord);
    }

    /**
     *@功能说明 医生术前记录签字
     *@Params [hisRescue]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/9/16
     *@Time 9:26
     **/
   /* @PostMapping("/signature/PreoperativeSummaryRecord.ahsj")
    public Message signature(PreoperativeSummaryRecord preoperativeSummaryRecord) throws Exception {
        return preoperativeSummaryRecordService.signature(preoperativeSummaryRecord,getId());
    }*/

}
