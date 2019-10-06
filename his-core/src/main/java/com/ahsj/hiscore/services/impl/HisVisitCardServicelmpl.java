package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisTollRecordMapper;
import com.ahsj.hiscore.dao.HisVisitCardMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HisVisitCardServicelmpl implements HisVisitCardService {

    @Autowired
    HisVisitCardMapper hisVisitCardMapper;

    @Autowired
    HisPatientInfoService hisPatientInfoService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisTollRecordMapper hisTollRecordMapper;

    @Autowired
    HisTollRecordService hisTollRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    HisTollDetailsService hisTollDetailsService;

    @Autowired
    HisRegisteredService hisRegisteredService;

    /**
     * @return core.message.Message
     * @Description 新增就诊卡/办卡
     * @Params [hisVisitCard]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 14:01
     **/
    @Override
    @Transactional(readOnly = false)
    public Message save(HisVisitCard hisVisitCard) throws Exception {
        //办卡自动生成编号  预留等待补充
        //办卡默认卡状态为未退回
        hisVisitCard.setIsBack(2);
        hisVisitCardMapper.insert(hisVisitCard);
        return MessageUtil.createMessage(true, "新增成功");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisPatientInfo
     * @Description 根据就诊卡编号拉取病人信息
     * @Params [number]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 15:40
     **/
    @Override
    @Transactional(readOnly = false)
    public HisPatientInfo getPatientInfoByNumber(String number) throws Exception {
        HisVisitCard hisVisitCard = hisVisitCardMapper.selectByNumber(number);
        if (EmptyUtil.Companion.isNullOrEmpty(hisVisitCard))
            return new HisPatientInfo();
        HisPatientInfo hisPatientInfo = hisPatientInfoService.selectByPrimaryKey(hisVisitCard.getPatientId());
        if (EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo))
            return new HisPatientInfo();
        return hisPatientInfo;
    }

    /**
     * @Description 根据就诊卡编号查询详细信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisVisitCard
     * @Date 2019/9/10
     * @Time 15:14
     **/
    @Override
    @Transactional(readOnly = true)
    public HisVisitCard selectByNumbers(String number) throws Exception {
        return hisVisitCardMapper.selectByNumbers(number)==null? new HisVisitCard():CodeHelper.getInstance().setCodeValue(hisVisitCardMapper.selectByNumbers(number));
    }

    /**
     * @Description 充值就诊卡
     * @Params: [balance, id]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/10
     * @Time 15:40
     **/
    @Override
    @Transactional(readOnly = false)
    public Message voucherCenter(HisVisitCard hisVisitCard) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisVisitCard.getBalance()) && hisVisitCard.getBalance().compareTo(new BigDecimal(0)) == 1) {
            //如果充值金额不为空并大于0
            HisVisitCard hv = hisVisitCardMapper.selectByPrimaryKey(hisVisitCard.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisVisitCard)) {
                BigDecimal balance1 = hv.getBalance();//卡内余额
                String createdate = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());//当前时间年月日
                String number = createdate + String.format("%03d", hisTollRecordMapper.selectNumCount(createdate) + 1);
                HisTollRecord hisTollRecord = new HisTollRecord();
                hisTollRecord.setMoney(hisVisitCard.getBalance());
                hisTollRecord.setActualCharge(hisVisitCard.getBalance());
                hisTollRecord.setRecoverTheFee(new BigDecimal(0));
                hisTollRecord.setAttenchType(6);//就诊卡充值
                hisTollRecord.setType(1);
                hisTollRecord.setIsSettlement(2);//未结算
                hisTollRecord.setNumber(number);//交易流水号
                hisTollRecord.setMedicalRecordId(hv.getNumber());//卡号
                hisTollRecordMapper.insert(hisTollRecord);
                hisVisitCard.setBalance(balance1.add(hisVisitCard.getBalance()));
                hisVisitCardMapper.updateByPrimaryKeySelective(hisVisitCard);
                return MessageUtil.createMessage(true, "充值成功!" + number);
            } else {
                return MessageUtil.createMessage(false, "数据异常!");
            }
        } else {
            return MessageUtil.createMessage(false, "数据异常!");
        }
    }


    /**
     * @return com.ahsj.hiscore.entity.HisVisitCard
     * @Description 根据就诊卡编号查询就诊卡信息
     * @Params [visitCardNumber]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 17:04
     **/
    @Override
    @Transactional(readOnly = true)
    public HisVisitCard selectByNumber(String visitCardNumber) throws Exception {
        return hisVisitCardMapper.selectByNumber(visitCardNumber);
    }

    /**
     * @return core.message.Message
     * @Description 就诊卡信息修改  多用于金额的修改
     * @Params [hisVisitCard]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 17:50
     **/
    @Override
    @Transactional(readOnly = false)
    public Message update(HisVisitCard hisVisitCard) throws Exception {
        hisVisitCardMapper.updateByPrimaryKeySelective(hisVisitCard);
        return MessageUtil.createMessage(true, "修改成功");
    }

    /**
     * @return core.message.Message
     * @Description 刷卡（目前仅针对门诊,住院）  （未对接实际机器，仅有对数据库的操作）
     * @Params [visitCardNumber]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 17:53
     **/
    @Override
    @Transactional(readOnly = false)
    public Message swipe(String visitCardNumber,String commonNumber)throws Exception {
        //根据就诊卡编号查询病人ID
        HisVisitCard hisVisitCard =selectByNumber(visitCardNumber);
        //根据病人ID查询出最近一条的病人就诊编号
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectTheLastRecordByPatientId(hisVisitCard.getPatientId());
        if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalRecord))
            return MessageUtil.createMessage(false,"无此病人就诊记录");


        if(!StringUtils.isEmpty(commonNumber)) {
            //无论编号为什么 首先转化为就诊记录编号
            //如果编号为R开头  代表是挂号编号
            //定义medicalNumber变量接收就诊记录编号
            String medicalNumber = "";
            if (StringUtils.equals(commonNumber.substring(0, 1), "R")) {
                //根据挂号编号查询出挂号ID
                HisRegistered hisRegistered = hisRegisteredService.selectByNumber(commonNumber);
                //根据挂号ID查询出就诊编号
                HisMedicalRecord getMedicalNumber = hisMedicalRecordService.selectByRegisterId(hisRegistered.getId());
                medicalNumber = getMedicalNumber.getMedicalRecordId();
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
            if (!StringUtils.equals(hisMedicalRecord.getMedicalRecordId(), medicalNumber)) {
                return MessageUtil.createMessage(false, "非本人使用/编号输入错误");
            }
        }

        //根据就诊编号/id查询出对应的项目与药品
        //查询出当前就诊编号未付费的项目列表
        List<HisRecordProject> hisRecordProjectList = hisRecordProjectService.selectByMedicalRecordIdNotIspayed(hisMedicalRecord.getId());
        //查询出当前就诊编号未付费的药品
        List<HisMedicationDetails> hisMedicationDetailsList = hisMedicationDetailsService.selectByRecordIdNotIsPay(hisMedicalRecord.getId());
        //计算总费用
        //定义sumPrice记录总费用
        BigDecimal sumPrice = new BigDecimal("0");
        //定义hisTollDetailsList保存交易记录明细
        List<HisTollDetails> hisTollDetailsList = new ArrayList<>();
        //累加项目费用并修改项目付费状态，仅付费成功才保存状态
        if(!EmptyUtil.Companion.isNullOrEmpty(hisRecordProjectList) &&hisRecordProjectList.size() !=0 ) {
            for (HisRecordProject hisRecordProject : hisRecordProjectList) {
                sumPrice = sumPrice.add(hisRecordProject.getPrice().multiply(BigDecimal.valueOf(hisRecordProject.getNum())));
                hisRecordProject.setIsPayed((short)1);
                HisTollDetails hisTollDetails = new HisTollDetails();
                hisTollDetails.setName(hisRecordProject.getName());
                hisTollDetails.setTargetId(hisRecordProject.getId());
                hisTollDetails.setType(2);
                hisTollDetails.setMoney(hisRecordProject.getPrice().multiply(BigDecimal.valueOf(hisRecordProject.getNum())));
                hisTollDetailsList.add(hisTollDetails);
            }
        }
        //累加药品费用并修改药品付费状态
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList) &&hisMedicationDetailsList.size() !=0 ) {
            for (HisMedicationDetails hisMedicationDetails : hisMedicationDetailsList) {
                sumPrice = sumPrice.add(hisMedicationDetails.getMedicineTotalPrice());
                hisMedicationDetails.setIsPay(1);
                HisTollDetails hisTollDetails = new HisTollDetails();
                hisTollDetails.setName(hisMedicationDetails.getDrugsName());
                hisTollDetails.setTargetId(hisMedicationDetails.getId());
                hisTollDetails.setType(1);
                hisTollDetails.setMoney(hisMedicationDetails.getMedicineTotalPrice());
                hisTollDetailsList.add(hisTollDetails);
            }
        }
        //（住院病人专属）住院费也就是病床费用
            //根据patientId查看病人当前是否正在住院
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByPatientIdAndInpatient(hisVisitCard.getPatientId());
        //计算住院未付天数
        Integer payDay = 0;
        Integer noPayDay = 0;
        if(!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)){
            //根据病床ID查找对应病床信息
            HisBed hisBed =hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId());

            if(!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getPayHospitalizationDay())){
                String[] strings = hisHospitalManage.getPayHospitalizationDay().split(",");
                for (int i = 0; i <strings.length ; i++) {
                    if(!StringUtils.isEmpty(strings[i]))
                        payDay +=Integer.valueOf(strings[i]);
                }
            }
             noPayDay = Integer.valueOf(hisHospitalManage.getHospitalizationDay()) -payDay;
            //累加计算住院费用
            if(noPayDay !=0){
                sumPrice = sumPrice.add(BigDecimal.valueOf(noPayDay).multiply(BigDecimal.valueOf(hisBed.getPrice())));
                HisTollDetails hisTollDetails = new HisTollDetails();
                hisTollDetails.setName(hisBed.getNumber().toString());
                hisTollDetails.setTargetId(hisBed.getId());
                hisTollDetails.setType(3);
                hisTollDetails.setMoney(BigDecimal.valueOf(noPayDay).multiply(BigDecimal.valueOf(hisBed.getPrice())));
                hisTollDetailsList.add(hisTollDetails);
            }
        }


        if(sumPrice.compareTo(new BigDecimal("0")) == 0){
            return MessageUtil.createMessage(false,"无可付费明细");
        }
        //判断就诊卡金额是否足够
            //restDeposit变量记录住院押金
                //如果没有住院信息，住院押金为0 ，如果有 住院押金为对应住院押金
        BigDecimal restDeposit = new BigDecimal("0");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage))
            restDeposit = hisHospitalManage.getRestDeposit();
        //1.金额足够
        if((hisVisitCard.getBalance().add(restDeposit)).compareTo(sumPrice) >= 0) {
            //从就诊卡中扣除对应金额并保存信息
                //就诊卡中金额不足够扣 从押金里面扣
            if(hisVisitCard.getBalance().compareTo(sumPrice) < 0){
                    //从押金中扣除 就诊卡中余额不足的部分
                hisHospitalManage.setRestDeposit(hisHospitalManage.getRestDeposit().subtract(sumPrice.subtract(hisVisitCard.getBalance())));
                    //就诊卡金额设置为0
                hisVisitCard.setBalance(new BigDecimal(0));
            }
                //就诊卡中金额足够直接扣除就诊卡金额即可
            else {
                hisVisitCard.setBalance(hisVisitCard.getBalance().subtract(sumPrice));
            }
            update(hisVisitCard);
            //保存修改药品与项目的付费状态
            if(!EmptyUtil.Companion.isNullOrEmpty(hisRecordProjectList) &&hisRecordProjectList.size() !=0 ) {
                hisRecordProjectService.updateBatchForIsPay(hisRecordProjectList);
            }
            if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList) &&hisMedicationDetailsList.size() !=0 ) {
                hisMedicationDetailsService.updateBatch(hisMedicationDetailsList);
            }
            //保存门诊刷卡交易记录
                //保存交易记录信息（内含保存交易记录明细信息）
            HisTollRecord hisTollRecord = new HisTollRecord();
            hisTollRecord.setMoney(sumPrice);
            hisTollRecord.setActualCharge(sumPrice);
            hisTollRecord.setRecoverTheFee(new BigDecimal("0"));
            hisTollRecord.setAttenchType(7);
            hisTollRecord.setMedicalRecordId(hisMedicalRecord.getMedicalRecordId());
                //记录交易流水号
            String tollRecordNumber = hisTollRecordService.save(hisTollRecord,hisTollDetailsList);
            //根据交易流水号查询当次所付住院费用的明细
            HisTollDetails hisTollDetails = hisTollDetailsService.selectByTollNumberForBedAmount(tollRecordNumber);
            //修改住院已付天数与对应交易明细ID 并保存
            if(!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)) {
                StringBuffer payDays = new StringBuffer("");
                StringBuffer payDaysId = new StringBuffer("");
                if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getPayHospitalizationDay())) {
                    payDays.append(hisHospitalManage.getPayHospitalizationDay());
                }
                if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getTollDetailsId())) {
                    payDaysId.append(hisHospitalManage.getTollDetailsId());
                }
                if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetails)) {
                    payDays.append("," + noPayDay);
                    payDaysId.append("," + hisTollDetails.getId());
                    hisHospitalManage.setPayHospitalizationDay(payDays.toString());
                    hisHospitalManage.setTollDetailsId(payDaysId.toString());
                }
            }
            hisHospitalManageService.update(hisHospitalManage);
            //打印交易凭证 预留等待补充

            return MessageUtil.createMessage(true,tollRecordNumber+"流水单号刷卡收费成功");
        }
        //2.金额不足，提示前往付费台充值并付费
        else {
            return MessageUtil.createMessage(false,"余额不足，请前往付费台充值并付费");
        }
    }

    /**
     * @Description 退卡
     * @Params: [hisVisitCard]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/10
     * @Time 17:52
     **/
    @Override
    @Transactional(readOnly = false)
    public Message returnIdCard(HisVisitCard hisVisitCard) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisVisitCard.getBalance()) && hisVisitCard.getBalance().compareTo(new BigDecimal(0)) != -1) {
            //如果充值金额不为空并大于等于0
            HisVisitCard hv = hisVisitCardMapper.selectByPrimaryKey(hisVisitCard.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisVisitCard)) {
                String createdate = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());//当前时间年月日
                String number = createdate + String.format("%03d", hisTollRecordMapper.selectNumCount(createdate) + 1);
                HisTollRecord hisTollRecord = new HisTollRecord();
                hisTollRecord.setMoney(new BigDecimal(0));
                hisTollRecord.setActualCharge(new BigDecimal(0));
                hisTollRecord.setRecoverTheFee(hisVisitCard.getBalance());
                hisTollRecord.setAttenchType(5);//就诊卡退费
                hisTollRecord.setType(1);
                hisTollRecord.setIsSettlement(2);//未结算
                hisTollRecord.setNumber(number);//交易流水号
                hisTollRecord.setMedicalRecordId(hv.getNumber());//卡号
                hisTollRecordMapper.insert(hisTollRecord);
                hisVisitCard.setBalance(new BigDecimal(0));//清空余额
                hisVisitCard.setIsBack(1);//已退卡
                hisVisitCardMapper.updateByPrimaryKeySelective(hisVisitCard);
                return MessageUtil.createMessage(true, "退费成功!" + number);
            } else {
                return MessageUtil.createMessage(false, "数据异常!");
            }
        } else {
            return MessageUtil.createMessage(false, "数据异常!");
        }
    }
}
