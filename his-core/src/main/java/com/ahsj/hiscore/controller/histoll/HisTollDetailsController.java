package com.ahsj.hiscore.controller.histoll;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/hisTollDetails/")
public class HisTollDetailsController extends BaseController {
    @Autowired
    HisTollDetailsService hisTollDetailsService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisTollRecordService hisTollRecordService;

    @Autowired
    HisRegisteredService hisRegisteredService;


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 门诊收费结算
     * @Params
     * @Author zhushixiang
     * @Date 2019-07-20
     * @Time 23:35
     **/
    @ResponseBody
    @RequestMapping(value = "listForOutpatientCharges.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisTollDetails> listForOutpatientCharges(Map<String, Object> model, HttpServletRequest request, HisTollDetails hisTollDetails) throws Exception {
        PageBean<HisTollDetails> pageBean = new PageBean<HisTollDetails>();
        pageBean.setParameter(hisTollDetails);
        return hisTollDetailsService.listForOutpatientCharges(pageBean);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 门诊收费结算（前端）
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-07-21
     * @Time 16:39
     **/
    @RequestMapping("listForOutpatientCharges/index.ahsj")
    ModelAndView listForOutpatientCharges(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/listForOutpatientCharges");
        modelAndView.addObject("title", "收费明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 返回住院收费页面
     * @Params [token]
     * @Author chenzhicai
     * @Date 2019-07-21
     * @Time 18:33
     **/
    @RequestMapping("hospital/list/index.ahsj")
    public ModelAndView hospitalIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_hospital");
        modelAndView.addObject("title", "收费明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }


    /**
     * @return
     * @Description 查询住院收费列表
     * @Params
     * @Author chenzhicai
     * @Date 2019-07-21
     * @Time 13:56
     **/
    @RequestMapping("hospital/list.ahsj")
    @ResponseBody
    public PageBean<HisTollDetails> hospitalListIndex(HisTollDetails hisTollDetails) throws Exception {
        PageBean<HisTollDetails> hisTollDetailsPageBean = new PageBean<HisTollDetails>();
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(hisTollDetails.getMedicalRecordId());
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)) {
            hisTollDetails.setMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisTollDetailsPageBean.setParameter(hisTollDetails);
            System.out.println(hisTollDetailsService.listByMecordId(hisTollDetailsPageBean));
            return hisTollDetailsService.listByMecordId(hisTollDetailsPageBean);
        } else {
            hisTollDetailsPageBean.setData(new ArrayList<HisTollDetails>());
            return hisTollDetailsPageBean;
        }
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 返回出院收费页面
     * @Params [token]
     * @Author chenzhicai
     * @Date 2019-07-21
     * @Time 18:33
     **/
    @RequestMapping("hospital/exit/list/index.ahsj")
    public ModelAndView hospitalExitIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_exithospital");
        modelAndView.addObject("title", "收费明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 返回门诊收费页面
     * @Params [token]
     * @Author chenzhicai
     * @Date 2019-07-21
     * @Time 18:33
     **/
    @RequestMapping("outpatient/list/index.ahsj")
    public ModelAndView outPatientIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_outpatient");
        modelAndView.addObject("title", "收费明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;


    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 查询门诊收费列表
     * @Params [hisTollDetails]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 10:31
     **/
    @RequestMapping("outpatient/list.ahsj")
    @ResponseBody
    public PageBean<HisTollDetails> outpatientListIndex(HisTollDetails hisTollDetails) throws Exception {
        PageBean<HisTollDetails> hisTollDetailsPageBean = new PageBean<HisTollDetails>();
        hisTollDetailsPageBean.setParameter(hisTollDetails);
        if (hisTollDetails.getRegisterNumber().contains("HM")) {
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisTollDetails.getRegisterNumber());
            HisRegistered hisRegistered = hisRegisteredService.selectById(hisMedicalRecord.getRegisteredId());
            hisTollDetails.setRegisterNumber(hisRegistered.getNumber());
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetails.getRegisterNumber())) {
            return hisTollDetailsService.listForOutpatientByMecordId(hisTollDetailsPageBean);
        } else {
            hisTollDetailsPageBean.setData(new ArrayList<HisTollDetails>());
            return hisTollDetailsPageBean;
        }
    }

    /**
     * @Description 分页查询
     * @Param hisTollDetails:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/7/24
     * @Time 16:51
     **/
    @RequestMapping("hisTollDetails/list.ahsj")
    @ResponseBody
    public PageBean<HisTollDetails> listHisTollDetails(Long tollRecordId, HisTollDetails hisTollDetails, PageBean<HisTollDetails> pageBean) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(tollRecordId)) {
            hisTollDetails.setTollRecordId(tollRecordId);
            pageBean.setParameter(hisTollDetails);
            return hisTollDetailsService.listHisTollDetails(pageBean);
        } else {
            return new PageBean<HisTollDetails>();
        }
    }

    /**
     * @Description 跳转退药收费
     * @Param token:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/1
     * @Time 9:59
     **/

    @RequestMapping("drugReturn/list/index.ahsj")
    public ModelAndView drugReturn(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_drugReturn");
        modelAndView.addObject("title", "退药收费");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @Description 跳转住院收费打印页面
     * @Param token:
     * @Param number:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/3
     * @Time 11:30
     **/
    @RequestMapping("printHospitalOne/index.ahsj")
    ModelAndView printHospitalOne(String token, String number, Integer type) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_print_one");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @RequestMapping("printHospitalTwo/index.ahsj")
    ModelAndView printHospitalTwo(String token, String number, Integer type) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_print_two");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @RequestMapping("printHospitalThree/index.ahsj")
    ModelAndView printHospitalThree(String token, String number, Integer type) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_print_three");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        modelAndView.addObject("type", type);
        return modelAndView;
    }


    /**
     * @Description 查找收费明细
     * @Param number:
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/8/3
     * @Time 18:06
     **/
    @RequestMapping("printShow/index.ahsj")
    @ResponseBody
    List<HisTollDetails> printShow(String number, String token, HisTollDetails hisTollDetails) throws Exception {//没有明细
        List<HisTollDetails> list = new ArrayList<HisTollDetails>();
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsService.listByNumber(number))) {
            HisTollRecord ht = hisTollRecordService.selectByNumbers(number);
            hisTollDetails.setCreateDate(ht.getCreateDate());
            hisTollDetails.setCreateName(ht.getUserName());
            hisTollDetails.setTotalPrices(ht.getMoney());
            hisTollDetails.setRecoverTheFee(new BigDecimal("0"));
            hisTollDetails.setMoney(new BigDecimal("0"));
            list.add(hisTollDetails);
        } else {
            list = hisTollDetailsService.listByNumber(number);
        }
        return list;
    }

    /**
     * @Description
     * @Params: [number, token, hisTollDetails]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 20:25
     **/
    @RequestMapping("printShowFor/index.ahsj")
    @ResponseBody
    HisTollDetails printShowFor(String number) throws Exception {//没有明细
        return hisTollDetailsService.listByNumberFor(number);
    }

    /**
     * @Description 退卡退费
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/10
     * @Time 14:31
     **/
    @RequestMapping("/returnTheCard/index.ahsj")
    @ResponseBody
    public ModelAndView list_returnIdCard(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_returnIdCard");
        modelAndView.addObject("title", "退卡退费");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @Description 就诊卡充值
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/10
     * @Time 14:31
     **/
    @RequestMapping("/voucherCenter/index.ahsj")
    @ResponseBody
    public ModelAndView voucherCenter(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_voucherCenter");
        modelAndView.addObject("title", "就诊卡充值");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @return
     * @Description 通用刷卡模块显示消费明细
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 16:26
     **/
    @RequestMapping("commonSwipe/list.ahsj")
    @ResponseBody
    public PageBean<HisTollDetails> commonSwipeList(HisTollDetails hisTollDetails) throws Exception {
        PageBean<HisTollDetails> hisTollDetailsPageBean = new PageBean<HisTollDetails>();
        hisTollDetailsPageBean.setParameter(hisTollDetails);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetails.getCommonNumber())) {
            //根据公共编号 搜索出对应的消费明细
            return hisTollDetailsService.listForcommonSwipeByCommonNumber(hisTollDetailsPageBean);
        } else {
            hisTollDetailsPageBean.setData(new ArrayList<HisTollDetails>());
            return hisTollDetailsPageBean;
        }
    }


    /**
     * @Description 出院打印
     * @Params: [number, token, hisTollDetails]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 18:28
     **/
    @RequestMapping("printShowLeave/index.ahsj")
    @ResponseBody
    List<HisTollDetails> printShowLeave(String number, String token, HisTollDetails hisTollDetails) throws Exception {//没有明细
        List<HisTollDetails> list = new ArrayList<HisTollDetails>();
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsService.listByNumberLeave(number))) {
            HisTollRecord ht = hisTollRecordService.selectByNumbers(number);
            hisTollDetails.setCreateDate(ht.getCreateDate());
            hisTollDetails.setCreateName(ht.getUserName());
            hisTollDetails.setTotalPrices(ht.getMoney());
            hisTollDetails.setRecoverTheFee(ht.getRecoverTheFee());
            list.add(hisTollDetails);
        } else {
            list = hisTollDetailsService.listByNumberLeave(number);
        }
        return list;
    }



    /**
     * @Description 门诊收费打印
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/8
     * @Time 11:21
     **/
    @RequestMapping("printHospitalOneDetail/index.ahsj")
    ModelAndView printHospitalOneDetail(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/list_print_one");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/8
     * @Time 11:23
     **/
    @RequestMapping("printHospitalTwoDetail/index.ahsj")
    ModelAndView printHospitalTwoDetail(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/list_print_two");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/8
     * @Time 11:32
     **/
    @RequestMapping("printHospitalThreeDetail/index.ahsj")
    ModelAndView printHospitalThreeDetail(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/list_print_three");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/8
     * @Time 11:34
     **/
    @RequestMapping("printDrugFourDetail/index.ahsj")
    ModelAndView printDrugFourDetail(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/list_print_four");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/8
     * @Time 13:22
     **/
    @RequestMapping("printDrugFiveDetail/index.ahsj")
    ModelAndView printDrugFiveDetail(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/list_print_five");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description 获取护理费，观察费
     * @Params: `
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollDetails
     * @Date 2019/10/9
     * @Time 17:26
     **/
    @RequestMapping("printShowThere/index.ahsj")
    @ResponseBody
    HisTollDetails printShowThere(String number) throws Exception {//没有明细
        return hisTollDetailsService.printShowThere(number);
    }
}
