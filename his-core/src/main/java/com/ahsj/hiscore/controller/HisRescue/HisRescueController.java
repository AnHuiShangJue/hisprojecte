package com.ahsj.hiscore.controller.HisRescue;

import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.entity.HisRescue;
import com.ahsj.hiscore.services.HisPatientInfoService;
import com.ahsj.hiscore.services.HisRescueService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRescueController
 * <p>
 * Date:     2019/9/15 10:53
 *
 * @author XJP
 * @create 2019/9/15
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/hisrescue")
public class HisRescueController extends BaseController {

    @Autowired
    HisRescueService hisRescueService;

    @Autowired
    HisPatientInfoService hisPatientInfoService;

    /**
     *@功能说明
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author XJP
     *@Date 2019/9/15
     *@Time 10:58
    **/
    @GetMapping("/list/index.ahsj")
    public ModelAndView listIndex(String token,HisRescue hisRescue,@RequestParam("bid") Long bid) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/hisRescueList");
        modelAndView.addObject("title", "抢救记录");
        modelAndView.addObject("token", token);
        modelAndView.addObject("bid", bid);
        modelAndView.addObject("hisRescue", hisRescue);
        return modelAndView;
    }
    @GetMapping("/add/index.ahsj")
    public ModelAndView addHisRescue(String token,HisRescue hisRescue,@RequestParam("bid") Long bid,Long id,@RequestParam("ids") Integer ids ) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/addOrUpdateHisRescue");
        if (ids == 2){
             hisRescue = hisRescueService.selectByPrimaryKey(id);
            modelAndView.addObject("title", "抢救记录查看");
            modelAndView.addObject("token", token);
            modelAndView.addObject("one", 1);
            modelAndView.addObject("bid", bid);
            modelAndView.addObject("hisRescue", hisRescue);
            return modelAndView;
        }else if(ids == 3){

            hisRescue = hisRescueService.selectByPrimaryKey(id);
            modelAndView.addObject("title", "抢救记录修改");
            modelAndView.addObject("token", token);
            modelAndView.addObject("bid", bid);
            modelAndView.addObject("hisRescue", hisRescue);
            return modelAndView;
        }else {
            HisPatientInfo hisPatientInfo = hisPatientInfoService.selectByPrimaryKey(bid);
            hisRescue.setPatientName(hisPatientInfo.getName());
            modelAndView.addObject("title", "抢救记录新增");
            modelAndView.addObject("token", token);
            modelAndView.addObject("hisRescue", hisRescue);
            modelAndView.addObject("bid", bid);
            return modelAndView;
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRescue>
     * @功能说明
     * @Params [hisRescue]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 10:58
     **/
    @PostMapping("/list.ahsj")
    public PageBean<HisRescue> queryList(HisRescue hisRescue) throws Exception {
        PageBean<HisRescue> pageBean = new PageBean<>();
        pageBean.setParameter(hisRescue);
        PageBean<HisRescue> hisRescuePageBean = hisRescueService.queryList(pageBean);
        return hisRescuePageBean;
    }

    /**
     *@功能说明 增添和修改抢救人员信息
     *@Params [hisRescue]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRescue>
     *@Author XJP
     *@Date 2019/9/15
     *@Time 11:49
    **/
    @PostMapping("/addOrUpdateHisRescue.ahsj")
    public Message addOrUpdateHisRescue(HisRescue hisRescue) throws Exception {
        return hisRescueService.addOrUpdateHisRescue(hisRescue);
    }

    /**
     *@功能说明 修改抢救人员信息
     *@Params [hisRescue]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRescue>
     *@Author XJP
     *@Date 2019/9/15
     *@Time 11:49
     **/
    @PostMapping("/update/HisRescue.ahsj")
    public Message updateHisRescue(HisRescue hisRescue) throws Exception {
        return hisRescueService.updateHisRescue(hisRescue);
    }

    /**
     *@功能说明 医生抢救签字
     *@Params [hisRescue]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/9/16
     *@Time 9:26
    **/
    @PostMapping("/signature/HisRescue.ahsj")
    public Message signature(HisRescue hisRescue) throws Exception {
        return hisRescueService.signature(hisRescue,getId());
    }
}