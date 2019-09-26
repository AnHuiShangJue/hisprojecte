package com.ahsj.hiscore.controller.histoll;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisRegisteredService;
import com.ahsj.hiscore.services.HisTollRecordService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/api/hisTollRecord/")
public class HisTollRecordController extends BaseController {
    @Autowired
    HisTollRecordService hisTollRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRegisteredService hisRegisteredService;


    /**
     * @Description
     * @Params: [token, hisTollRecord]
     * @Author: dingli
     * @Return: java.lang.String
     * @Date 2019/9/11
     * @Time 13:30
     **/
  /*  @RequestMapping("getPrice.ahsj")
    @ResponseBody
    public String getPrice(String token, HisTollRecord hisTollRecord) throws Exception {
        return JSON.toJSONString(list(hisTollRecord).getData().get(0).getPrice());
    }*/

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 住院结算基本信息
     * @Params [medicalRecordId, token]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:13
     **/
    @RequestMapping("hospital/details.ahsj")
    @ResponseBody
    HisTollRecordDetails hospitalDetails(String medicalRecordId, String token) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(medicalRecordId);
        if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage))
            return new HisTollRecordDetails();
        return hisTollRecordService.hospitalDetails(hisHospitalManage.getMedicalNumber());
    }

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 根据挂号编号拉取对应门诊信息
     * @Params [medicalRecordId, token]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 11:22
     **/

    @RequestMapping("outpatient/details.ahsj")
    @ResponseBody
    HisTollRecordDetails outpatientlDetails(String registerNumber) throws Exception {
        //根据挂号编号查询出挂号ID
        HisMedicalRecord hisMedicalRecord = new HisMedicalRecord();
        if (registerNumber.contains("R")) {
            HisRegistered hisRegistered = hisRegisteredService.selectByNumber(registerNumber);
            //根据挂号ID查询出就诊编号
            if (EmptyUtil.Companion.isNullOrEmpty(hisRegistered))
                return new HisTollRecordDetails();
            hisMedicalRecord = hisMedicalRecordService.selectByRegisterId(hisRegistered.getId());
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalRecord))
                return new HisTollRecordDetails();
            return hisTollRecordService.outpatientDetails(hisMedicalRecord.getMedicalRecordId());
        } else {
            return hisTollRecordService.outpatientDetails(registerNumber);
        }
    }


    /**
     * @return core.message.Message
     * @Description 住院结算
     * @Params [hisTollHospiModel]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:13
     **/
    @RequestMapping("hospital/save.ahsj")
    @ResponseBody
    Message hospitalSave(@RequestBody HisTollHospiModel hisTollHospiModel) throws Exception {
        String Id = hisTollHospiModel.getHisTollRecord().getMedicalRecordId();
        if (Id != null) {
            if (Id.contains("HR")) {
                hisTollHospiModel.getHisTollRecord().setMedicalRecordId(hisHospitalManageService.selectByHospNumber(hisTollHospiModel.getHisTollRecord().getMedicalRecordId()).getMedicalNumber());
            }
        }
        hisHospitalManageService.selectByNumber(hisTollHospiModel.getHisTollRecord().getMedicalRecordId()).getMedicalNumber();
        return hisTollRecordService.hospitalSave(hisTollHospiModel);
    }


    /**
     * @return core.message.Message
     * @Description 出院结算
     * @Params [hisTollHospiModel]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:13
     **/
    @RequestMapping("hospital/exit.ahsj")
    @ResponseBody
    Message hospitalExit(@RequestBody HisTollHospiModel hisTollHospiModel) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(hisTollHospiModel.getHisTollRecord().getMedicalRecordId());
        //如果有则继续，无则直接返回不存在
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)) {
            hisTollHospiModel.getHisTollRecord().setMedicalRecordId(hisHospitalManage.getMedicalNumber());
            return hisTollRecordService.hospitalExit(hisTollHospiModel);
        }
        return MessageUtil.createMessage(false, "该住院记录不存在就诊记录，请联系管理员");
    }

    /**
     * @return core.message.Message
     * @Description 保存收费信息（收费记录）
     * @Params [hisTollHospiModel]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 15:46
     **/
    @RequestMapping("outpatient/save.ahsj")
    @ResponseBody
    Message outpatientSave(@RequestBody HisTollHospiModel hisTollHospiModel) throws Exception {
        return hisTollRecordService.outpatientSave(hisTollHospiModel);
    }

    @RequestMapping("list/index.ahsj")
    ModelAndView list(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/listFor");
        modelAndView.addObject("title", "财务统计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @param hisTollRecord
     * @return
     * @Description 门诊住院分页查询
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 12:22
     **/
    @RequestMapping("list/details.ahsj")
    @ResponseBody
    public PageBean<HisTollRecord> list(HisTollRecord hisTollRecord) throws Exception {
        PageBean<HisTollRecord> pageBean = new PageBean<HisTollRecord>();
        pageBean.setParameter(hisTollRecord);
        pageBean = hisTollRecordService.getAllHisTollRecord(pageBean);
        return pageBean;

    }

    /**
     * @param number
     * @return
     * @Description 根据流水号查询收费信息
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 14:08
     **/
    @RequestMapping("details/index.ahsj")
    ModelAndView getDetails(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/showFor");
        modelAndView.addObject("title", "财务统计");
        modelAndView.addObject("hisTollRecord", hisTollRecordService.getHisTollRecord(number));
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @param hisTollRecord
     * @return
     * @Description 获取总价格
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 21:14
     **/
    @RequestMapping("getPrice")
    @ResponseBody
    HisTollRecord getPrices(String token, HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordService.getPrice(hisTollRecord);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 打印凭证
     * @Params [token]
     * @Author chenzhicai
     * @Date 2019-07-24
     * @Time 13:59
     **/
    @RequestMapping("/hospital/print.ahsj")
    ModelAndView print(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisCharge/print");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 退药明细
     * @Param token:
     * @Param id:
     * @Param vocher:
     * @Param recordNumber:
     * @Param recoverTheFee:
     * @Author: dingli
     * @return: core.message.Message
     * @Date 2019/7/25
     * @Time 15:46
     **/
    @RequestMapping("returnDrug/save.ahsj")
    @ResponseBody
    Message returnDrug(@RequestBody HisTollHospiModel hisTollHospiModel) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollHospiModel.getHisTollRecord().getRecoverTheFee()))
            return MessageUtil.createMessage(false, "数据格式错误");
        return hisTollRecordService.saveHisApplicationForDrugReturn(hisTollHospiModel);
    }

    /**
     * @Description 退药打印, 根据交易流水号
     * @Param number
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/7/29
     * @Time 14:24
     **/
    @RequestMapping("printDrug/index.ahsj")
    ModelAndView printDrug(String number, String patientName, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_drugReturn_print");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description 就诊卡收费统计
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/12
     * @Time 14:38
     **/
    @RequestMapping("/listVisitCard/index.ahsj")
    ModelAndView listVisitCard(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/listVisitCard");
        modelAndView.addObject("title", "就诊卡收费统计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 充值卡的财务统计
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/12
     * @Time 15:15
     **/
    @RequestMapping("/getVisitCard.ahsj")
    @ResponseBody
    public PageBean<HisTollRecord> getVisitCard(HisTollRecord hisTollRecord) throws Exception {
        PageBean<HisTollRecord> pageBean = new PageBean<HisTollRecord>();
        pageBean.setParameter(hisTollRecord);
        pageBean = hisTollRecordService.getVisitCard(pageBean);
        return pageBean;

    }

    /**
     * @Description 统计就诊卡价格
     * @Params: [token, hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/12
     * @Time 15:41
     **/
    @RequestMapping("/getPriceVisitCard")
    @ResponseBody
    HisTollRecord getPriceVisitCard(String token, HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordService.getPriceVisitCard(hisTollRecord);
    }

    /**
     * @Description 充值卡的详细信息
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/12
     * @Time 16:14
     **/
    @RequestMapping("/detailsVisit/index.ahsj")
    ModelAndView detailsVisit(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/showVisit");
        modelAndView.addObject("title", "财务统计");
        modelAndView.addObject("hisTollRecord", hisTollRecordService.selectVisitCard(number));
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 药品收费统计
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/12
     * @Time 16:45
     **/
    @RequestMapping("/listDrug/index.ahsj")
    ModelAndView listDrug(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/listDrug");
        modelAndView.addObject("title", "药品收费统计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 项目收费统计
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/12
     * @Time 16:46
     **/
    @RequestMapping("/listProject/index.ahsj")
    ModelAndView listProject(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/listProject");
        modelAndView.addObject("title", "项目收费统计");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 根据不同的编号获取相同的信息   服务于通用刷卡模块
     * @Params [registerNumber]
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 13:36
     **/
    @RequestMapping("common/details.ahsj")
    @ResponseBody
    HisTollRecordDetails commonDetails(String commonNumber) throws Exception {
        //无论编号为什么 首先转化为就诊记录编号
        //如果编号为R开头  代表是挂号编号
        //定义medicalNumber变量接收就诊记录编号
        String medicalNumber = "";
        if (StringUtils.equals(commonNumber.substring(0, 1), "R")) {
            //根据挂号编号查询出挂号ID
            HisRegistered hisRegistered = hisRegisteredService.selectByNumber(commonNumber);
            //根据挂号ID查询出就诊编号
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByRegisterId(hisRegistered.getId());
            medicalNumber = hisMedicalRecord.getMedicalRecordId();
        }
        //如果编号为HR开头 代表是住院编号
        else if (StringUtils.equals(commonNumber.substring(0, 2), "HR")) {
            //根据住院编号查询出就诊编号
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(commonNumber);
            medicalNumber = hisHospitalManage.getMedicalNumber();
        }
        ////如果编号为HM开头 代表是就诊记录编号
        else if (StringUtils.equals(commonNumber.substring(0, 2), "HM")) {
            medicalNumber = commonNumber;
        }
        //根据就诊编号搜索出公共刷卡模块需要的一些信息
        HisTollRecordDetails hisTollRecordDetails = hisTollRecordService.selectByMedicalNumberForCommonDetails(medicalNumber);
        return hisTollRecordService.selectByMedicalNumberForCommonDetails(medicalNumber);
    }

    /**
     * @Description
     * @Params: [hisTollRecord] 财务统计药品分页查询
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 9:09
     **/
    @RequestMapping("/getAllDrug.ahsj")
    @ResponseBody
    public PageBean<HisTollRecord> getAllDrug(HisTollRecord hisTollRecord) throws Exception {
        PageBean<HisTollRecord> pageBean = new PageBean<HisTollRecord>();
        pageBean.setParameter(hisTollRecord);
        pageBean = hisTollRecordService.getAllDrug(pageBean);
        return pageBean;
    }

    /**
     * @Description 财务统计药品分页查询价格
     * @Params: [token, hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 9:58
     **/
    @RequestMapping("getDrugPrice")
    @ResponseBody
    HisTollRecord getDrugPrice(String token, HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordService.getDrugPrice(hisTollRecord);
    }

    /**
     * @Description
     * @Params: [hisTollRecord] 财务统计项目分页查询
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 10:53
     **/
    @RequestMapping("/getAllProject.ahsj")
    @ResponseBody
    public PageBean<HisTollRecord> getAllProject(HisTollRecord hisTollRecord) throws Exception {
        PageBean<HisTollRecord> pageBean = new PageBean<HisTollRecord>();
        pageBean.setParameter(hisTollRecord);
        pageBean = hisTollRecordService.getAllDrug(pageBean);
        return pageBean;
    }

    @RequestMapping("pharmacylist/index.ahsj")
    ModelAndView pharmacylist(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/pharmacylist");
        modelAndView.addObject("title", "药库盘点");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 药库盘点
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/19
     * @Time 10:22
     **/
    @RequestMapping("/pharmacyInventory.ahsj")
    @ResponseBody
    public PageBean<HisTollRecord> pharmacyInventory(HisTollRecord hisTollRecord) throws Exception {
        PageBean<HisTollRecord> pageBean = new PageBean<HisTollRecord>();
        pageBean.setParameter(hisTollRecord);
        pageBean = hisTollRecordService.pharmacyInventory(pageBean);
        return pageBean;
    }

    /**
     * @Description 药品盘点价格
     * @Params: [token, hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/19
     * @Time 11:37
     **/
    @RequestMapping("getPharmacyinventoryPrice")
    @ResponseBody
    HisTollRecord getPharmacyinventoryPrice(String token, HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordService.getPharmacyinventoryPrice(hisTollRecord);
    }

    @RequestMapping("consumableInventory/index.ahsj")
    ModelAndView consumableInventory(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/consumableInventory");
        modelAndView.addObject("title", "耗材盘点");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 药库盘点详细信息
     * @Params: [token, createDate, drugName]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/26
     * @Time 10:56
     **/
    @RequestMapping("getDrugDetails/index.ahsj")
    ModelAndView getDrugDetails(String token, String createDate, String drugName,String saleCounts,String stock) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisFinance/pharmacyDetail");
        modelAndView.addObject("title", "药库盘点详细信息");
        modelAndView.addObject("createDate", createDate);
        modelAndView.addObject("drugName", drugName);
        modelAndView.addObject("saleCounts", saleCounts);
        modelAndView.addObject("stock", stock);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 药库盘点详细信息
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/26
     * @Time 11:10
     **/
    @RequestMapping("/pharmacyInventoryDetail.ahsj")
    @ResponseBody
    public PageBean<HisTollRecord> pharmacyInventoryDetail(String createDate, String drugName) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(createDate);
        HisTollRecord hisTollRecord = new HisTollRecord();
        hisTollRecord.setCreateDate(date);
        hisTollRecord.setDetailsName(drugName);
        PageBean<HisTollRecord> pageBean = new PageBean<HisTollRecord>();
        pageBean.setParameter(hisTollRecord);
        pageBean = hisTollRecordService.pharmacyInventoryDetail(pageBean);
        return pageBean;
    }
}