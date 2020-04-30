package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.entity.PageBean;
import core.message.BoolMessage;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/17
 * @Time 16:03
 **/
@Service
public class HisTollRecordServiceImpl implements HisTollRecordService {
    @Autowired
    HisTollRecordMapper hisTollRecordMapper;

    @Autowired
    HisTollDetailsService hisTollDetailsService;

    @Autowired
    HisChargeService hisChargeService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisRecordProjectService HisRecordProjectService;

    @Autowired
    HisHospitalManageMapper hisHospitalManageMapper;

    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;
    @Autowired
    HisRecordProjectMapper hisRecordProjectMapper;
    @Autowired
    HisApplicationForDrugReturnDetailsService hisApplicationForDrugReturnDetailsService;
    @Autowired
    HisApplicationForDrugReturnService hisApplicationForDrugReturnService;

    @Autowired
    HisApplicationForDrugReturnDetailsMapper hisApplicationForDrugReturnDetailsMapper;

    @Autowired
    HisApplicationForDrugReturnMapper hisApplicationForDrugReturnMapper;

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRefundProjectInfoMapper hisRefundProjectInfoMapper;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisRefundProjectMapper hisRefundProjectMapper;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisRecordConsumablesMapper hisRecordConsumablesMapper;

    @Autowired
    HisRefundConsumablesService hisRefundConsumablesService;

    @Override
    @Transactional(readOnly = false)
    public Message addHisTollRecord(List<HisCharge> record, HisTollRecord hisTollRecord) throws Exception {
        String createdate = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());//当前时间年月日
        String medicalRecordId = record.get(0).getMedicalRecordId();
        String number = createdate + String.format("%05d", hisTollRecordMapper.selectNumCount(createdate) + 1);
        boolean flase = true;
        for (HisCharge hc : record) {
            if (hc.getIsPay() == 2) { //未付生成新的订单
                flase = false;
                HisTollRecord ht = new HisTollRecord();
                ht.setNumber(number);
                ht.setType(hc.getRecordType());
                ht.setAttenchId(hc.getId());
                ht.setAttenchType(1);//门诊收费
                ht.setMedicalRecordId(medicalRecordId);
                ht.setMoney(hisTollRecord.getMoney());
                ht.setActualCharge(hisTollRecord.getActualCharge());
                ht.setRecoverTheFee(hisTollRecord.getRecoverTheFee());
                ht.setIsSettlement(2);
                hisTollRecordMapper.insert(ht);//插入后获取id
                HisTollDetails hisTollDetails = new HisTollDetails();  //明细
                hisTollDetails.setName(hc.getRecordName());
                hisTollDetails.setRecords(hisTollRecord.getRemark());
                hisTollDetails.setTargetId(hc.getId());
                hisTollDetails.setType(hc.getRecordType());
                hisTollDetails.setMoney(hisTollRecord.getMoney());
                hisTollDetails.setTollRecordId(ht.getId());
                hisTollDetails.setIsSettlement(2);
                hisTollDetailsService.insertSelective(hisTollDetails);
                if (hc.getRecordType() == 1) {//药品 未付改成已付
                    HisMedicationDetails hisMedicationDetails = new HisMedicationDetails();
                    hisMedicationDetails.setId(hc.getId());
                    hisMedicationDetails.setIsPay(1);
                    hisMedicationDetailsService.updateHisMedicationDetails(hisMedicationDetails);
                }
                if (hc.getRecordType() == 2) {//项目未付
                    HisRecordProject hisRecordProject = new HisRecordProject();
                    hisRecordProject.setId(hc.getId());
                    hisRecordProject.setIsPayed(new Short("1"));
                    HisRecordProjectService.updateHisRecordProject(hisRecordProject);
                }
            }

        }
        if (flase) {
            return MessageUtil.createMessage(false, "付款失败，没有未付款");
        }
        return MessageUtil.createMessage(true, "付款成功");
    }


    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 查询住院收费细节信息
     * @Params [medicalRecordId]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:08
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecordDetails hospitalDetails(String medicalRecordId) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollRecordMapper.hospitalDetails(medicalRecordId)))
            return new HisTollRecordDetails();
        HisTollRecordDetails hisTollRecordDetails = CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.hospitalDetails(medicalRecordId));
        if (hisTollRecordDetails.getRestDeposit().compareTo(new BigDecimal(0)) == -1) {
            hisTollRecordDetails.setMoney(hisTollRecordDetails.getMoney().add(hisTollRecordDetails.getRestDeposit().abs()));
        }
        return hisTollRecordDetails;
    }


    /*
     *  为了护士站引入的查询消费明细
     * */
    @Override
    @Transactional(readOnly = true)
    public HisTollRecordDetails hospitalDetailsForNurse(String medicalNumber) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollRecordMapper.hospitalDetails(medicalNumber)))
            return new HisTollRecordDetails();
        HisTollRecordDetails hisTollRecordDetails = CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.hospitalDetails(medicalNumber));
        return hisTollRecordDetails;
    }

    /**
     * @return core.message.Message
     * @Description 保存住院收费信息
     * @Params [hisTollHospiModel]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message hospitalSave(HisTollHospiModel hisTollHospiModel) {

        //处理住院交易
        HisTollRecord hisTollRecord = hisTollHospiModel.getHisTollRecord();
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollRecord.getActualCharge())) {
            hisTollRecord.setActualCharge(new BigDecimal("0"));
        }

        PageBean<HisTollDetails> pageBean = new PageBean<HisTollDetails>();
        HisTollDetails hd = new HisTollDetails();
        hd.setMedicalRecordId(hisTollRecord.getMedicalRecordId());
        pageBean.setParameter(hd);
        List<HisTollDetails> hisTollDetails = hisTollDetailsService.listByMecordIdForSave(pageBean).getData();
        BigDecimal sumPrice = new BigDecimal(0);
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetails))
            //            return MessageUtil.createMessage(false, "无可付费信息");
            for (int i = 0; i < hisTollDetails.size(); i++) {
                BigDecimal p = new BigDecimal(hisTollDetails.get(i).getMoney().toString());
                sumPrice = sumPrice.add(p);
            }
        if (sumPrice.compareTo(hisTollRecord.getActualCharge()) == 1) {
            return MessageUtil.createMessage(false, "总金额小于应收金额，请重新输入!!!");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
        //编号
        String number = createdate + String.format("%05d", count);
        number = "HTR" + number;
        hisTollRecord.setNumber(number);
        hisTollRecord.setType(2);
        hisTollRecord.setIsSettlement(2);
        hisTollRecord.setAttenchType(2);


        //修改住院押金
        HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByNumber(hisTollRecord.getMedicalRecordId());
        hisHospitalManage.setRestDeposit(hisHospitalManage.getRestDeposit().subtract(hisTollRecord.getMoney()));

        //无收费记录，则为交押金
        hisTollRecord.setDeposit(hisHospitalManage.getRestDeposit());
        hisTollRecordMapper.insert(hisTollRecord);
        if (hisTollDetails.size() == 0 && !hisTollRecord.getDeposit().equals(0)) {
            hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);
            return MessageUtil.createMessage(true, number + "押金交付成功！，当前总押金为" + hisHospitalManage.getRestDeposit());
        }


        //处理住院交易明细
        HandleHisTollDetails(hisTollDetails, hisTollRecord.getId());
        //        if(hisTollDetails.get)

        BoolMessage message = (BoolMessage) hisTollDetailsService.saveForHospi(hisTollDetails);
        if (!message.isSuccess())
            return MessageUtil.createMessage(false, "保存失败！请联系管理人员");

        //这里是存在缴费记录，则需要修改住院已付的数目
        HandleHospitalManagerData(hisHospitalManage, hisTollDetails);
        hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);

        //修改用药明细付费状态
        List<HisMedicationDetails> hisMedicationDetails = new ArrayList<HisMedicationDetails>();
        HandleHisMedicationDetils(hisMedicationDetails, hisTollDetails);
        if (hisMedicationDetails.size() != 0)
            hisMedicationDetailsMapper.setPayBatch(hisMedicationDetails);
        //修改诊疗项目付费状态
        List<HisRecordProject> hisRecordProjects = new ArrayList<HisRecordProject>();
        HandleRecordProjects(hisRecordProjects, hisTollDetails);
        if (hisRecordProjects.size() != 0)
            hisRecordProjectMapper.setPayBatch(hisRecordProjects);
        return MessageUtil.createMessage(true, number + "收费成功！");
    }

    /**
     * @param
     * @return
     * @Description 住院押金充值
     * @Author: czc
     * @Date 2019/11/23
     * @Time 20:49
     **/
    @Override
    @Transactional(readOnly = false)
    public Message hospitalSaveForRestDepo(HisTollHospiModel hisTollHospiModel) throws Exception {
        //处理住院交易
        HisTollRecord hisTollRecord = hisTollHospiModel.getHisTollRecord();
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollRecord.getActualCharge())) {
            hisTollRecord.setActualCharge(new BigDecimal("0"));
        }
        HisTollDetails hd = new HisTollDetails();


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
        //编号
        String number = createdate + String.format("%05d", count);
        number = "HTR" + number;
        hisTollRecord.setNumber(number);
        hisTollRecord.setType(2);
        hisTollRecord.setIsSettlement(2);
        hisTollRecord.setAttenchType(2);


        HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByNumber(hisTollRecord.getMedicalRecordId());
        hisHospitalManage.setRestDeposit(hisHospitalManage.getRestDeposit().add(hisTollRecord.getActualCharge()));

        //住院押金充值中总应收为0,以做区分
        hisTollRecord.setMoney(new BigDecimal(0));
        hisTollRecord.setDeposit(hisHospitalManage.getRestDeposit());
        hisTollRecordMapper.insert(hisTollRecord);
        hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);
        hd.setName("住院押金充值");
        hd.setMoney(hisTollRecord.getActualCharge());
        hd.setType(3);
        hd.setTollRecordId(hisTollRecord.getId());
        hd.setTargetId(null);
        hisTollDetailsService.insert(hd);
        return MessageUtil.createMessage(true, number + "押金交付成功！，当前总押金为" + hisHospitalManage.getRestDeposit());

    }

    /**
     * @return void
     * @Description 处理住院数据状态
     * @Params [hisHospitalManage, hisTollDetails]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:09
     **/
    private void HandleHospitalManagerData(HisHospitalManage hisHospitalManage, List<HisTollDetails> hisTollDetails) {
        StringBuffer payDays = new StringBuffer("");
        StringBuffer payDaysId = new StringBuffer("");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getPayHospitalizationDay())) {
            payDays.append(hisHospitalManage.getPayHospitalizationDay());
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getTollDetailsId())) {
            payDaysId.append(hisHospitalManage.getTollDetailsId());
        }
        for (HisTollDetails h1 : hisTollDetails) {
            if (h1.getType().equals(3)) {
                payDays.append("," + h1.getNum());
                payDaysId.append("," + h1.getId());
                hisHospitalManage.setPayHospitalizationDay(payDays.toString());
                hisHospitalManage.setTollDetailsId(payDaysId.toString());
            }
        }
    }

    /**
     * @return void
     * @Description 处理就诊项目信息
     * @Params [hisRecordProjects, hisTollDetails]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:09
     **/
    private void HandleRecordProjects(List<HisRecordProject> hisRecordProjects, List<HisTollDetails> hisTollDetails) {
        for (HisTollDetails h1 : hisTollDetails) {
            if (h1.getType().equals(2)) {
                HisRecordProject tmp = new HisRecordProject();
                tmp.setId(h1.getTargetId());
                tmp.setPrice(h1.getPrice());
                tmp.setIsPayed(new Short("1"));
                hisRecordProjects.add(tmp);
            }
        }
    }

    /**
     * @return void
     * @Description 处理就诊药品信息
     * @Params [hisMedicationDetails, hisTollDetails]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:09
     **/
    private void HandleHisMedicationDetils(List<HisMedicationDetails> hisMedicationDetails, List<HisTollDetails> hisTollDetails) {
        for (HisTollDetails h1 : hisTollDetails) {
            if (h1.getType().equals(1)) {
                HisMedicationDetails tmp = new HisMedicationDetails();
                tmp.setId(h1.getTargetId());
                tmp.setIsPay(1);
                tmp.setTotalPrice(h1.getMoney());
                tmp.setCount(h1.getNum());
                hisMedicationDetails.add(tmp);
            }
        }
    }

    /**
     * @return void
     * @Description 处理收费细节
     * @Params [hisTollDetails, id]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:10
     **/
    private void HandleHisTollDetails(List<HisTollDetails> hisTollDetails, Long id) {
        for (HisTollDetails h :
                hisTollDetails) {
            h.setTollRecordId(id);
        }
    }

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 根据就诊编号拉去对应信息
     * @Params [medicalRecordId]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 14:19
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecordDetails outpatientDetails(String medicalRecordId) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollRecordMapper.outpatientDetails(medicalRecordId)))
            return new HisTollRecordDetails();
        return CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.outpatientDetails(medicalRecordId));
    }

    /**
     * @return core.message.Message
     * @Description 保存门诊收费信息（收费记录）
     * @Params [hisTollHospiModel]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 15:49
     **/
    @Override
    @Transactional(readOnly = false)
    public Message outpatientSave(HisTollHospiModel hisTollHospiModel) throws Exception {
        //处理门诊交易
        HisTollRecord hisTollRecord = hisTollHospiModel.getHisTollRecord();
        PageBean<HisTollDetails> pageBean = new PageBean<HisTollDetails>();
        HisTollDetails hd = new HisTollDetails();
        HisMedicalRecord hisMedicalRecord = new HisMedicalRecord();
        if ("R".equals(hisTollRecord.getRegisterNumber().substring(0, 1))) {
            hd.setRegisterNumber(hisTollRecord.getRegisterNumber());
            HisRegistered hisRegistered = hisRegisteredService.selectByNumber(hisTollRecord.getRegisterNumber());
            hisMedicalRecord = hisMedicalRecordService.selectByRegisterId(hisRegistered.getId());
        } else if ("HM".equals(hisTollRecord.getRegisterNumber().substring(0, 2))) {
            hd.setMedicalReocordNumber(hisTollRecord.getRegisterNumber());
        }
        pageBean.setParameter(hd);
        List<HisTollDetails> hisTollDetails = hisTollDetailsService.listByMecordIdForOutpatientSave(pageBean).getData();
        BigDecimal sumPrice = new BigDecimal("0");
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetails))
            //            return MessageUtil.createMessage(false, "无可付费信息");
            for (int i = 0; i < hisTollDetails.size(); i++) {
                BigDecimal p = new BigDecimal(hisTollDetails.get(i).getMoney().toString());
                sumPrice = sumPrice.add(p);
            }
        if (sumPrice.compareTo(hisTollRecord.getActualCharge()) == 1) {
            return MessageUtil.createMessage(false, "总金额小于应收金额，请重新输入!!!");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
        //编号
        String number = createdate + String.format("%05d", count);
        number = "HTR" + number;
        if ("R".equals(hisTollRecord.getRegisterNumber().substring(0, 1))) {
            hisTollRecord.setMedicalRecordId(hisMedicalRecord.getMedicalRecordId());
        } else if ("HM".equals(hisTollRecord.getRegisterNumber().substring(0, 2))) {
            hisTollRecord.setMedicalRecordId(hd.getMedicalReocordNumber());
        }
        hisTollRecord.setNumber(number);
        hisTollRecord.setType(2);
        hisTollRecord.setIsSettlement(2);
        hisTollRecord.setAttenchType(1);
        hisTollRecordMapper.insert(hisTollRecord);

        //处理住院交易明细
        HandleHisTollDetails(hisTollDetails, hisTollRecord.getId());
        BoolMessage message = (BoolMessage) hisTollDetailsService.saveForHospi(hisTollDetails);
        if (!message.isSuccess())
            return MessageUtil.createMessage(false, "保存失败！请联系管理人员");

        //修改用药明细付费状态
        List<HisMedicationDetails> hisMedicationDetails = new ArrayList<HisMedicationDetails>();
        HandleHisMedicationDetils(hisMedicationDetails, hisTollDetails);
        if (hisMedicationDetails.size() != 0)
            hisMedicationDetailsMapper.setPayBatch(hisMedicationDetails);
        //修改诊疗项目付费状态
        List<HisRecordProject> hisRecordProjects = new ArrayList<HisRecordProject>();
        HandleRecordProjects(hisRecordProjects, hisTollDetails);
        if (hisRecordProjects.size() != 0)
            hisRecordProjectMapper.setPayBatch(hisRecordProjects);
        return MessageUtil.createMessage(true, number + "收费成功！");
    }

    /**
     * @param pageBean
     * @return
     * @Description 分页查询门诊住院
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 19:39
     **/
    @Transactional(readOnly = true)
    @Override
    public PageBean<HisTollRecord> getAllHisTollRecord(PageBean<HisTollRecord> pageBean) throws Exception {
        List<HisTollRecord> list = hisTollRecordMapper.getAllHisTollRecord(pageBean);//条件查询所取得的所有结果
        pageBean.setData(CodeHelper.getInstance().setCodeValue(list));
        return pageBean;

    }

    /**
     * @param number
     * @return
     * @Description 根据交易流水号查询信息
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 14:16
     **/
    @Transactional(readOnly = true)
    @Override
    public HisTollRecord getHisTollRecord(String number) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.getHisTollRecord(number));
    }

    /**
     * @Description
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 10:02
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecord getPrice(HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordMapper.getPrice(hisTollRecord);
    }

    /**
     * @Description 根据退费凭证退费
     * @Param vocher:
     * @Author: dingli
     * @return: core.message.Message
     * @Date 2019/7/25
     * @Time 14:16
     **/
    @Transactional(readOnly = false)
    @Override
    public Message saveHisApplicationForDrugReturn(HisTollHospiModel hisTollHospiModel) throws Exception {
        //根据退药id查询是否已经退款了  2 未退款
        List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetails = hisTollHospiModel.getHisApplicationForDrugReturnDetailsList();
        HisTollRecord hisTollRecord1 = hisTollHospiModel.getHisTollRecord();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollRecord1.getDeposit())) { //住院退费
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByMedicalNumber(hisApplicationForDrugReturnService.selectByVoucher(hisApplicationForDrugReturnDetails.get(0).getVoucher()).getRecordNumber());
            hisHospitalManage.setRestDeposit(hisTollRecord1.getDeposit());
            hisHospitalManageService.update(hisHospitalManage);
        }
        BigDecimal sumPrice = new BigDecimal("0");
      /*  for (int i = 0; i < hisApplicationForDrugReturnDetails.size(); i++) {
            HisApplicationForDrugReturnDetails hisApplicationForDrugReturnDetails1 = hisApplicationForDrugReturnDetailsMapper.selectByPrimaryKey(hisApplicationForDrugReturnDetails.get(i).getId());
            sumPrice = sumPrice.add(hisApplicationForDrugReturnDetails1.getTotalPrice());
        }
        if (sumPrice.compareTo(hisTollRecord1.getRecoverTheFee()) != 0) {
            return MessageUtil.createMessage(false, "退款金额不一致");
        }*/
        String createdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
        //编号
        String number = createdate + String.format("%05d", count);
        number = "HTR" + number;


        HisTollRecord hisTollRecord = new HisTollRecord();
        hisTollRecord.setMoney(new BigDecimal(0));
        hisTollRecord.setActualCharge(new BigDecimal(0));
        hisTollRecord.setRecoverTheFee(hisTollRecord1.getRecoverTheFee());
        hisTollRecord.setAttenchType(4);//退药
        hisTollRecord.setType(1);
        hisTollRecord.setMedicalRecordId(hisApplicationForDrugReturnService.selectByVoucher
                (hisApplicationForDrugReturnDetails.get(0).getVoucher()).getRecordNumber());
        hisTollRecord.setIsSettlement(2);//未结算
        hisTollRecord.setNumber(number);//交易流水号
        hisTollRecordMapper.insert(hisTollRecord);
        //明细
        for (HisApplicationForDrugReturnDetails h : hisApplicationForDrugReturnDetails) {
            HisTollDetails hisTollDetails = new HisTollDetails();
            hisTollDetails.setIsSettlement(2);
            hisTollDetails.setName(h.getDrugsName());
            hisTollDetails.setTargetId(h.getId());
            //            hisTollDetails.setMoney(h.getPrice());
            hisTollDetails.setMoney(h.getPrice().multiply(new BigDecimal(h.getReturnCount())));
            hisTollDetails.setType(4);//药品退费
            hisTollDetails.setTollRecordId(hisTollRecord.getId());
            hisTollDetailsService.insert(hisTollDetails);
            HisApplicationForDrugReturn hisApplicationForDrugReturn = hisApplicationForDrugReturnService.selectByVoucher(h.getVoucher());
            hisApplicationForDrugReturn.setId(hisApplicationForDrugReturn.getId());
            hisApplicationForDrugReturn.setIsPayed(1);//将退药设置已退款
            hisApplicationForDrugReturnService.updateByPrimaryKeySelective(hisApplicationForDrugReturn);
        }
        return MessageUtil.createMessage(true, "退费成功！" + number);
    }

    /**
     * @return pssage
     * @Description 出院结算
     * @Params [hisTollHospiModel]
     * @Author chenzhicai
     * @Date 2019-07-22
     * @Time 16:07
     **/
    @Override
    @Transactional(readOnly = false)
    public Message hospitalExit(HisTollHospiModel hisTollHospiModel) {
        //处理住院交易
        HisTollRecord hisTollRecord = hisTollHospiModel.getHisTollRecord();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
        //编号
        String number = createdate + String.format("%05d", count);
        number = "HTR" + number;
        PageBean<HisTollDetails> pageBean = new PageBean<HisTollDetails>();
        HisTollDetails hd = new HisTollDetails();
        hd.setMedicalRecordId(hisTollRecord.getMedicalRecordId());
        pageBean.setParameter(hd);
        List<HisTollDetails> hisTollDetails = hisTollDetailsService.listByMecordIdForSave(pageBean).getData();
        BigDecimal sumPrice = new BigDecimal(0);
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetails)) {
            hisTollRecord.setNumber(number);
            hisTollRecord.setType(2);
            hisTollRecord.setIsSettlement(2);
            hisTollRecord.setAttenchType(9);
            hisTollRecordMapper.insert(hisTollRecord);
            HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByNumber(hisTollRecord.getMedicalRecordId());
            hisHospitalManage.setRestDeposit(new BigDecimal(0.00));
            hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);
            return MessageUtil.createMessage(true, number + "收费结算成功！");
        }
        for (int i = 0; i < hisTollDetails.size(); i++) {
            BigDecimal p = new BigDecimal(hisTollDetails.get(i).getMoney().toString());
            sumPrice = sumPrice.add(p);
        }
        if (sumPrice.compareTo(hisTollRecord.getActualCharge()) == 1) {
            return MessageUtil.createMessage(false, "总金额小于应收金额，请重新输入!!!");
        }
        hisTollRecord.setNumber(number);
        hisTollRecord.setType(2);
        hisTollRecord.setIsSettlement(2);
        hisTollRecord.setAttenchType(9);
        hisTollRecordMapper.insert(hisTollRecord);

        //修改住院押金
        HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByNumber(hisTollRecord.getMedicalRecordId());
        hisHospitalManage.setRestDeposit(new BigDecimal(0.00));

        //处理住院交易明细
        HandleHisTollDetails(hisTollDetails, hisTollRecord.getId());
        if (hisTollDetails.size() != 0) {
            BoolMessage message = (BoolMessage) hisTollDetailsService.saveForHospi(hisTollDetails);
            if (!message.isSuccess())
                return MessageUtil.createMessage(false, "保存失败！请联系管理人员");
        }

        //这里是存在缴费记录，则需要修改住院已付的数目
        HandleHospitalManagerData(hisHospitalManage, hisTollDetails);
        hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);

        //修改用药明细付费状态
        List<HisMedicationDetails> hisMedicationDetails = new ArrayList<HisMedicationDetails>();
        HandleHisMedicationDetils(hisMedicationDetails, hisTollDetails);
        if (hisMedicationDetails.size() != 0)
            hisMedicationDetailsMapper.setPayBatch(hisMedicationDetails);


        //修改耗材 明细付费状态
        HisRecordConsumables hisRecordConsumables = new HisRecordConsumables();
        hisRecordConsumables.setMedicalRecordNumber(hisTollRecord.getMedicalRecordId());
        hisRecordConsumables.setIsPayed(Constants.BASE_TWO);
        List<HisRecordConsumables> hisRecordConsumablesList = hisRecordConsumablesMapper.selectByHisRecordConsumables(hisRecordConsumables);
        //伪删
        hisRecordConsumablesMapper.updateByIsDelete(hisRecordConsumablesList);
        for (HisRecordConsumables recordConsumables : hisRecordConsumablesList) {
            recordConsumables.setIsPayed(Constants.BASE_ONE);
            recordConsumables.setIsDelete(Constants.HIS_DELETE_FALSE);
            recordConsumables.setId(null);
        }
        hisRecordConsumablesMapper.insertList(hisRecordConsumablesList);

        //修改诊疗项目付费状态
        List<HisRecordProject> hisRecordProjects = new ArrayList<HisRecordProject>();
        HandleRecordProjects(hisRecordProjects, hisTollDetails);
        if (hisRecordProjects.size() != 0)
            hisRecordProjectMapper.setPayBatch(hisRecordProjects);
        return MessageUtil.createMessage(true, number + "收费结算成功！");
    }

    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/28
     * @Time 22:18
     * @Return
     * @Params
     **/
    @Override
    public Integer[] selectTollRecordDataCount(Date createDate) throws Exception {
        Integer[] outs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (Integer i : outs) {
            i = new Integer(0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        //取到当前日期的数据
        List<HisTollRecord> hisTollRecordList = hisTollRecordMapper.selectTollRecordDataCount(createDate, calendar.getTime());
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollRecordList) && hisTollRecordList.size() != 0) {
            for (HisTollRecord hisTollRecord1 : hisTollRecordList) {
                Calendar date = Calendar.getInstance();
                date.setTime(hisTollRecord1.getCreateDate());
                int hour = date.get(Calendar.HOUR_OF_DAY);
                switch (hour) {
                    case 0:
                        outs[1]++;
                        break;
                    case 1:
                        outs[2]++;
                        break;
                    case 2:
                        outs[3]++;
                        break;
                    case 3:
                        outs[4]++;
                        break;
                    case 4:
                        outs[5]++;
                        break;
                    case 5:
                        outs[6]++;
                        break;
                    case 6:
                        outs[7]++;
                        break;
                    case 7:
                        outs[8]++;
                        break;
                    case 8:
                        outs[9]++;
                        break;
                    case 9:
                        outs[10]++;
                        break;
                    case 10:
                        outs[11]++;
                        break;
                    case 11:
                        outs[12]++;
                        break;
                    case 12:
                        outs[13]++;
                        break;
                    case 13:
                        outs[14]++;
                        break;
                    case 14:
                        outs[15]++;
                        break;
                    case 15:
                        outs[16]++;
                        break;
                    case 16:
                        outs[17]++;
                        break;
                    case 17:
                        outs[18]++;
                        break;
                    case 18:
                        outs[19]++;
                        break;
                    case 19:
                        outs[20]++;
                        break;
                    case 20:
                        outs[21]++;
                        break;
                    case 21:
                        outs[22]++;
                        break;
                    case 22:
                        outs[23]++;
                        break;
                    case 23:
                        outs[0]++;
                        break;
                    default:
                        break;
                }
            }
        }
        return outs;
    }

    /**
     * @Description 根据交易流水号查询交易信息
     * @Param number:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/7/30
     * @Time 10:55
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecord selectByNumbers(String number) {
        return hisTollRecordMapper.selectByNumbers(number);
    }

    /**
     * @Description 根据交易流水号查询充值卡，退卡详情
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/11
     * @Time 13:47
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecord ListByNumber(String number) {
        return hisTollRecordMapper.ListByNumber(number);
    }

    /**
     * @return
     * @Description 保存收费记录   并返回交易流水号
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-11
     * @Time 10:27
     **/
    @Override
    @Transactional(readOnly = false)
    public String save(HisTollRecord hisTollRecord, List<HisTollDetails> hisTollDetailsList) throws Exception {
        //此save提取公共字段信息秉设置如交易流水号，是否结算

        if (EmptyUtil.Companion.isNullOrEmpty(hisTollRecord.getIsSettlement()))
            hisTollRecord.setIsSettlement(2);
        //生成交易流水号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
        boolean flag = false;
        String number = "";
        while (true) {
            number = "HTR" + createdate + String.format("%05d", count);
            HisTollRecord checkNumber = hisTollRecordMapper.selectByNumber(number);
            if (EmptyUtil.Companion.isNullOrEmpty(checkNumber))
                break;
            else
                count++;
        }
        hisTollRecord.setNumber(number);
        //保存交易记录
        hisTollRecordMapper.insert(hisTollRecord);
        //保存交易记录明细
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList) && hisTollDetailsList.size() != 0) {
            for (HisTollDetails hisTollDetails : hisTollDetailsList) {
                hisTollDetails.setTollRecordId(hisTollRecord.getId());
            }
        }
        //批量报损交易记录明细信息
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList) && hisTollDetailsList.size() != 0) {
            hisTollDetailsService.insertBatch(hisTollDetailsList);
        }
        return number;
    }

    /**
     * @Description 就诊卡的财务统计
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/12
     * @Time 15:13
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollRecord> getVisitCard(PageBean<HisTollRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.getVisitCard(pageBean)));
        return pageBean;
    }

    /**
     * @Description 获取充值卡的总价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/12
     * @Time 15:40
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecord getPriceVisitCard(HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordMapper.getPriceVisitCard(hisTollRecord);
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/12
     * @Time 16:29
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecord selectVisitCard(String number) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.selectVisitCard(number));
    }

    /**
     * @Description 财务统计药品的分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 9:29
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollRecord> getAllDrug(PageBean<HisTollRecord> pageBean) throws Exception {
        List<HisTollRecord> list = CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.getAllDrug(pageBean));
        for (HisTollRecord hisTollRecord : list) {
            if ((hisTollRecord.getType() == 4)) {
                hisTollRecord.setRecoverTheFee(hisTollRecord.getSalePrice());
                hisTollRecord.setSalePrice(new BigDecimal("0"));
            }
        }
        pageBean.setData(list);
        return pageBean;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 根据就诊编号搜索出公共刷卡模块需要的一些信息 如病人信息，医生信息，就诊科室等
     * @Params [medicalNumber]
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 16:04
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecordDetails selectByMedicalNumberForCommonDetails(String medicalNumber) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.selectByMedicalNumberForCommonDetails(medicalNumber));
    }

    /**
     * @Description 财务统计条件查询总价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 10:03
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecord getDrugPrice(HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordMapper.getDrugPrice(hisTollRecord);
    }

    /**
     * @Description 财务统计获取所有项目的分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 11:12
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollRecord> getAllProject(PageBean<HisTollRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.getAllProject(pageBean)));
        return pageBean;
    }

    /**
     * @Description 财务统计 条件查询 获取的价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 11:13
     **/
    @Override
    public HisTollRecord getProjectPrice(HisTollRecord hisTollRecord) throws Exception {
        return null;
    }

    /**
     * @Description 药库盘点
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/19
     * @Time 10:21
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollRecord> pharmacyInventory(PageBean<HisTollRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.pharmacyInventory(pageBean)));
        return pageBean;
    }

    /**
     * @Description 药品盘点价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/19
     * @Time 11:36
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollRecord getPharmacyinventoryPrice(HisTollRecord hisTollRecord) throws Exception {
        return hisTollRecordMapper.getPharmacyinventoryPrice(hisTollRecord);
    }

    /**
     * @Description 项目退费
     * @Params: [hisTollHospiModel]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/25
     * @Time 10:21
     **/
    @Override
    @Transactional(readOnly = false)
    public Message hisProjectSave(HisRefundProjectInfo hisRefundProjectInfo) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo.getRefundSumProce()) || EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo.getVoucher()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo.getTollRecordNumber())) {
            return MessageUtil.createMessage(false, "退款失败!");
        } else {
            HisRefundProjectInfo projectInfo = hisRefundProjectInfoMapper.queryHisRefundProjectInfo(hisRefundProjectInfo);
            if (!EmptyUtil.Companion.isNullOrEmpty(projectInfo)) {
                return MessageUtil.createMessage(false, "退款失败! 该项目已经退款成功 ");
            }
            HisRecordProject hisRecordProject = new HisRecordProject();
            hisRecordProject.setTollRecordNumber(hisRefundProjectInfo.getTollRecordNumber());
            List<HisRecordProject> hisRecordProjectList = hisRecordProjectMapper.pricelistsBytollRecordNumber(hisRecordProject);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisRefundProjectInfo.getDeposit())) { //住院退项目
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByMedicalNumber(hisRefundProjectInfo.getRecordNumber());
              /*  if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getRestDeposit())) {
                    hisHospitalManage.setRestDeposit(hisRefundProjectInfo.getDeposit().add(hisHospitalManage.getRestDeposit()));
                } else {
                    hisHospitalManage.setRestDeposit(hisRefundProjectInfo.getDeposit());
                }*/
                hisHospitalManage.setRestDeposit(hisRefundProjectInfo.getDeposit());
                hisHospitalManageService.update(hisHospitalManage);
            }

        /*    BigDecimal sumPrice = new BigDecimal("0");
            for (int i = 0; i < hisRecordProjectList.size(); i++) {
                sumPrice = sumPrice.add(hisRecordProjectList.get(i).getProjectSumPrice());
            }
            if (sumPrice.compareTo(hisRefundProjectInfo.getRefundSumProce()) != 0) {
                return MessageUtil.createMessage(false, "退款金额不一致");
            }*/
            String createdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
            int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
            //编号
            // HisTollRecord hisTollRecord1 = hisTollRecordMapper.selectByNumber(hisRefundProjectInfo.getTollRecordNumber());//
            String number = createdate + String.format("%05d", count);
            number = "HTR" + number;
            HisTollRecord hisTollRecord = new HisTollRecord();
            hisTollRecord.setMoney(new BigDecimal(0));
            hisTollRecord.setActualCharge(new BigDecimal(0));
            hisTollRecord.setRecoverTheFee(hisRefundProjectInfo.getRefundSumProce());
            hisTollRecord.setAttenchType(8);//退项目
            hisTollRecord.setMedicalRecordId(hisRefundProjectInfo.getRecordNumber());
            hisTollRecord.setIsSettlement(2);//未结算
            hisTollRecord.setNumber(number);//交易流水号
            hisTollRecord.setType(2);
            hisTollRecordMapper.insert(hisTollRecord);
            //明细
            // List<HisRecordProject> pricelistsBytollRecordNumber = hisRecordProjectService.pricelistsBytollRecordNumber(hisRecordProject);
            HisRefundProject hisRefundProject = new HisRefundProject();
            // hisRefundProject.setTollRecordNumber(hisRefundProjectInfo.getTollRecordNumber());
            hisRefundProject.setRecordNumber(hisRefundProjectInfo.getRecordNumber());
            List<HisRefundProject> refundProjectList = hisRefundProjectMapper.queryHisRefundProject(hisRefundProject);
            for (HisRefundProject h : refundProjectList) {
                HisRecordProject hisRecordProject1 = hisRecordProjectService.selectByPrimaryKey(h.getRecordProjectId());
                hisRecordProject1.setIsBack(1);//已退回
                hisRecordProjectService.update(hisRecordProject1);
                HisTollDetails hisTollDetails = new HisTollDetails();
                hisTollDetails.setIsSettlement(2);
                hisTollDetails.setName(hisRecordProject1.getName());
                hisTollDetails.setTargetId(h.getId());
                hisTollDetails.setMoney(hisRecordProject1.getPrice().multiply(new BigDecimal(h.getRefundNum())));
                hisTollDetails.setType(5);//项目退费
                hisTollDetails.setTollRecordId(hisTollRecord.getId());
                hisTollDetailsService.insert(hisTollDetails);
                h.setIsBack(1);  //将退项目设置为已退
                hisRefundProjectMapper.updateByHisRefundProjectListBack(refundProjectList);
            }
            return MessageUtil.createMessage(true, "退费成功！" + number);
        }
    }

    /**
     * @Description 查看药库盘点明细
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/26
     * @Time 11:07
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollRecord> pharmacyInventoryDetail(PageBean<HisTollRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollRecordMapper.pharmacyInventoryDetail(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据交易流水号核对是否为住院
     * @Params [tollNumber]
     * @Author zhushixiang
     * @Date 2019-09-26
     * @Time 22:04
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage checkIsInpatient(String tollNumber) throws Exception {
        //根据交易流水号查询该病人对应的住院信息，主要返回剩余押金，是否已住院（已住院退现金，未出院提醒冲入押金）
        if ("HM".equals(tollNumber.substring(0, 2))) {

        } else if ("HTR".equals(tollNumber.substring(0, 3))) {
            HisTollRecord hisTollRecord = hisTollRecordMapper.selectByNumber(tollNumber);
            tollNumber = hisTollRecord.getMedicalRecordId();
        }
        HisHospitalManage hisHospitalManage = hisHospitalManageService.checkIsInpatient(tollNumber);
        if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage))
            return new HisHospitalManage();
        /*List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList = hisApplicationForDrugReturnDetailsMapper.selectByRecordNumber(hisHospitalManage.getMedicalNumber());
        BigDecimal sumPrice = new BigDecimal("0");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturnDetailsList)&& hisApplicationForDrugReturnDetailsList.size()!=0) {
            for (HisApplicationForDrugReturnDetails hisApplicationForDrugReturnDetails : hisApplicationForDrugReturnDetailsList) {
                sumPrice.add(hisApplicationForDrugReturnDetails.getTotalPrice());
            }
        }
        hisHospitalManage.setRetrunPrice(sumPrice);*/
        return hisHospitalManage;
    }

    //打印所有在医院产生的费用信息
    @Override
    @Transactional(readOnly = true)
    public List<HisTollRecordDetails> selectAllInformationByHRNumber(String hrNumber) throws Exception {
        return hisTollRecordMapper.selectAllInformationByHRNumber(hrNumber);
    }


    /**
     * @return
     * @Description 根据就诊编号HHM查询交易流水号
     * @Params
     * @Author jin
     * @Date 2019/11/9
     * @Time 16:47
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> listNumberByMedicalNumber(String medicalNumber) throws Exception {
        return hisTollRecordMapper.listNumberByMedicalNumber(medicalNumber);

    }

    //根据住院号搜索出所有与此住院号相关的收费明细且实际收费大于0（即交押金的那条数据的明细）
    @Override
    @Transactional(readOnly = true)
    public List<HisTollRecord> selectByHRNumberForAllDeposit(String number) {
        return hisTollRecordMapper.selectByHRNumberForAllDeposit(number);
    }

    /**
     * @return core.message.Message
     * @Description
     * @MethodName saveHisRefundConsumablesInfo
     * @Params [hisRefundConsumables]
     * @Author XJP
     * @Date 2020/4/30
     * @Time 10:34
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveHisRefundConsumablesInfo(HisRefundConsumables hisRefundConsumables) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundConsumables.getRefundSumProce())) {
            return MessageUtil.createMessage(false, "退款失败!");
        } else {


            String createdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
            int count = hisTollRecordMapper.selectNumbCount(createdate) + 1;
            //编号
            String number = createdate + String.format("%05d", count);
            number = "HTR" + number;
            HisTollRecord hisTollRecord = new HisTollRecord();
            hisTollRecord.setMoney(new BigDecimal(0));
            hisTollRecord.setActualCharge(new BigDecimal(0));
            hisTollRecord.setRecoverTheFee(hisRefundConsumables.getRefundSumProce());
            hisTollRecord.setAttenchType(10);//退耗材
            hisTollRecord.setMedicalRecordId(hisRefundConsumables.getRecordNumber());
            hisTollRecord.setIsSettlement(2);//未结算
            hisTollRecord.setNumber(number);//交易流水号
            hisTollRecord.setType(3);
            hisTollRecordMapper.insert(hisTollRecord);

            //明细

            HisRecordConsumables recordConsumables = new HisRecordConsumables();
            recordConsumables.setMedicalRecordNumber(hisRefundConsumables.getRecordNumber());
            //获取待退费耗材信息数据
            List<HisRecordConsumables> hisRecordConsumablesList = hisRecordConsumablesMapper.queryByRecordConsumablesList(recordConsumables);
            for (HisRecordConsumables consumables : hisRecordConsumablesList) {
                HisTollDetails hisTollDetails = new HisTollDetails();
                hisTollDetails.setIsSettlement(2);
                hisTollDetails.setName(consumables.getName());
                hisTollDetails.setMoney(new BigDecimal(consumables.getPrice()).multiply(new BigDecimal(consumables.getRefundNum())));
                hisTollDetails.setType(7);//耗材退费
                hisTollDetails.setTollRecordId(hisTollRecord.getId());
                hisTollDetailsService.insert(hisTollDetails);


                //查询收费记录未退费的数据
                List<HisRecordConsumables> recordConsumablesList = hisRecordConsumablesMapper.queryByNotBack(consumables);
                if (!EmptyUtil.Companion.isNullOrEmpty(recordConsumablesList)){
                    hisRecordConsumablesMapper.updateByIsDelete(recordConsumablesList);

                    Integer num = consumables.getRefundNum();
                    for (HisRecordConsumables hisRecordConsumables : recordConsumablesList) {
                        if (hisRecordConsumables.getComsumablesNum()<num){
                            hisRecordConsumables.setComsumablesNum(0);
                            hisRecordConsumables.setId(null);
                            num = num-hisRecordConsumables.getComsumablesNum();
                            hisRecordConsumables.setIsBack(Constants.HC_BACK_TRUE);
                            continue;
                        }else {
                            hisRecordConsumables.setComsumablesNum(hisRecordConsumables.getComsumablesNum()-num);
                            hisRecordConsumables.setIsBack(Constants.HC_BACK_FALSE);
                            hisRecordConsumables.setId(null);
                            num= 0;
                        }
                    }
                    hisRecordConsumablesMapper.insertList(recordConsumablesList);
                }

            }

            //查询申请退费并审核成功的为退费的数据
            List<HisRefundConsumables> hisRefundConsumablesList = hisRefundConsumablesService.queryByNotBack(hisRefundConsumables.getRecordNumber());
            //伪删
            hisRefundConsumablesService.updateByIsDelete(hisRefundConsumablesList);
            //新增
            for (HisRefundConsumables refundConsumables : hisRefundConsumablesList) {
                refundConsumables.setIsBack(Constants.HC_BACK_TRUE);
                refundConsumables.setId(null);
            }
            hisRefundConsumablesService.insertList(hisRefundConsumablesList);


            return MessageUtil.createMessage(true, "退费成功！" + number);

        }
    }

    @Override
    @Transactional(readOnly = true)
    public HisTollRecord queryByNumber(String number) throws Exception {
        return hisTollRecordMapper.queryByNumber(number);
    }
}