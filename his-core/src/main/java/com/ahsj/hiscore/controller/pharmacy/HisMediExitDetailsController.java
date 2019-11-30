package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.entity.HisBed;
import com.ahsj.hiscore.entity.HisMediExitDetails;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.entity.model.HisMedicalModel;
import com.ahsj.hiscore.services.HisMediEnterDetailsService;
import com.ahsj.hiscore.services.HisMediExitDetailsService;
import com.ahsj.hiscore.services.HisMedicineInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/mediexit/")
public class HisMediExitDetailsController extends BaseController {
    @Autowired
    HisMediExitDetailsService hisMediExitDetailsService;

    @Autowired
    HisMediEnterDetailsService hisMediEnterDetailsService;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;

    /**
     * @return core.message.Message
     * @Description 药品出库（后台逻辑）
     * @Params [model, hisMediExitDetails]
     * @Author zhushixiang
     * @Date 2019-06-26
     * @Time 12:01
     **/
    @RequestMapping(value = "mediExit.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HisMediExitDetails hisMediExitDetails) throws Exception {
        return hisMediExitDetailsService.mediExit(hisMediExitDetails);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 手动出库
     * @Params [token, planNumber]
     * @Author zhushixiang
     * @Date 2019-07-06
     * @Time 16:10
     **/
    @RequestMapping(value = "/manualDepot/index.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    ModelAndView manualDepot(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/manualDepot");
        modelAndView.addObject("title", "手动出库");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 药品出库按钮
     * @Params [model, request, ids, numbers, prices]
     * @Author zhushixiang
     * @Date 2019-07-06
     * @Time 21:10
     **/
    @RequestMapping(value = "medicineExit.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message medicineExit(Map<String, Object> model, HttpRequest request,
                                @RequestParam(value = "ids", required = true) Long[] ids,
                                @RequestParam(value = "numbers", required = true) Long[] numbers,
                                @RequestParam(value = "prices", required = true) Double[] prices) throws Exception {
        return hisMediExitDetailsService.medicineExit(ids, numbers, prices);
    }

    /**
     * @return core.message.Message
     * @Description 智能出库
     * @Params [model, request, ids, numbers]
     * @Author zhushixiang
     * @Date 2019-07-08
     * @Time 17:36
     **/
    @RequestMapping(value = "IntelligenceExit.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message IntelligenceExit(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
            , @RequestParam(value = "numbers", required = true) Integer[] numbers
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisMediExitDetailsService.IntelligenceExit(ids, numbers);
        } else {

            return MessageUtil.createMessage(false, "请选择至少一条数据");
        }
    }

    /**
     * @return core.message.Message
     * @Description 根据就诊编号/住院编号/交易流水号药品出库
     * @Params [model, number]
     * @Author zhushixiang
     * @Date 2019-07-23
     * @Time 16:39
     **/
    @PostMapping(value = "mediExitByNumber.ahsj")
    @ResponseBody
    public Message mediExitByNumber(@RequestBody HisMedicalModel hisMedicalModel) throws Exception {
        List<HisMedicationDetails> hisMedicationDetailsList = hisMedicalModel.getMediDetail();
        return hisMediExitDetailsService.mediExitByNumber(hisMedicationDetailsList);
    }

    /**
     * @param ids
     * @Description 跳转打印页面
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/16
     * @Time 17:42
     **/
    @RequestMapping("printDrug.ahsj")
    ModelAndView printDrug(String ids, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/printDrug");
        modelAndView.addObject("title", "打印出药");
        modelAndView.addObject("token", token);
        modelAndView.addObject("ids", ids);
        return modelAndView;
    }

    /**
     * @param ids
     * @Description 查询出药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/8/17
     * @Time 13:24
     **/
    @PostMapping(value = "getDrug.ahsj")
    @ResponseBody
    public List<HisMediExitDetails> getDrug(@RequestParam(value = "ids", required = true) Long[] ids, String token) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(ids)) {
            List<HisMediExitDetails> hisMediExitDetailsList = hisMediExitDetailsService.listByIds(ids);
            if(!EmptyUtil.Companion.isNullOrEmpty(hisMediExitDetailsList)&&hisMediExitDetailsList.size()!=0){
                for (HisMediExitDetails hisMediExitDetails : hisMediExitDetailsList) {
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMediExitDetails.getSex())){
                        if(hisMediExitDetails.getSex() == 1){
                            hisMediExitDetails.setSexName("男");
                        }else {
                            hisMediExitDetails.setSexName("女");
                        }
                    }
                }
            }
            return hisMediExitDetailsList;
        }
        return new ArrayList<HisMediExitDetails>();
    }

    /**
     * @Description 获取历史出药
     * @Params: [ids, token]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/9/16
     * @Time 9:54
     **/
    @PostMapping(value = "getDrugHistory.ahsj")
    @ResponseBody
    public List<HisMediExitDetails> getDrugHistory(@RequestParam(value = "ids", required = true) Long[] ids, String token) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(ids)) {
            return hisMediExitDetailsService.listByIdsHistory(ids);
        }
        return new ArrayList<HisMediExitDetails>();
    }

    /**
     * @Description 分页查询出库记录
     * @Params: [hisMediExitDetails]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/10/6
     * @Time 15:53
     **/
    @RequestMapping("stockRemoval/index.ahsj")
    @ResponseBody
    public PageBean<HisMediExitDetails> list(HisMediExitDetails hisMediExitDetails) throws Exception {
        PageBean<HisMediExitDetails> pageBean = new PageBean<HisMediExitDetails>();
        pageBean.setParameter(hisMediExitDetails);
        hisMediExitDetailsService.getAllMediExit(pageBean);
        return pageBean;
    }

    /**
     * @Description 药品出库详细信息查看
     * @Params: [id, token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/6
     * @Time 16:08
     **/
    @RequestMapping("stockRemovalDetail.ahsj")
    ModelAndView stockRemovalDetail(String createDate, String token, String recordNumber, String userName, String tollNumber) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/stockRemovalDetail");
        modelAndView.addObject("title", "药品出库详细信息查看");
        modelAndView.addObject("token", token);
        modelAndView.addObject("createDate", createDate);
        modelAndView.addObject("recordNumber", recordNumber);
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("tollNumber", tollNumber);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [hisMediExitDetails]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/10/6
     * @Time 16:30
     **/
    @RequestMapping("stockRemovalDetail/index.ahsj")
    @ResponseBody
    public PageBean<HisMediExitDetails> listDetail(HisMediExitDetails hisMediExitDetails) throws Exception {
        PageBean<HisMediExitDetails> pageBean = new PageBean<HisMediExitDetails>();
        pageBean.setParameter(hisMediExitDetails);
        hisMediExitDetailsService.getAllMediExitDetail(pageBean);
        return pageBean;
    }

    /**
     * @Description 药品出库详细信息打印
     * @Params: [tollNumber, token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/6
     * @Time 17:32
     **/
    @RequestMapping("printStockRemoval.ahsj")
    ModelAndView stockRemovalDetail(String tollNumber, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/printStockRemoval");
        modelAndView.addObject("title", "");
        modelAndView.addObject("token", token);
        modelAndView.addObject("tollNumber", tollNumber);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [tollNumber]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/10/6
     * @Time 18:07
     **/
    @RequestMapping("removal/index.ahsj")
    @ResponseBody
    public List<HisMediExitDetails> removal(String tollNumber,String token) throws Exception {
        return hisMediExitDetailsService.getRemovalBytollNumber(tollNumber);
    }

}
