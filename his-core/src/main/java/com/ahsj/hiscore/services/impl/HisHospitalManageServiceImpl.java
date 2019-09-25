package com.ahsj.hiscore.services.impl;


import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import jdk.nashorn.internal.objects.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;


@Service
public class HisHospitalManageServiceImpl implements HisHospitalManageService {
    Logger logger = LoggerFactory.getLogger(HisDailyRecordServiceImpl.class.getSimpleName());

    @Autowired
    HisHospitalManageMapper hisHospitalManageMapper;

    @Autowired
    HisRegisteredMapper hisRegisteredMapper;

    @Autowired
    HisPatientInfoMapper hisPatientInfoMapper;

    @Autowired
    HisDoctorInfoMapper hisDoctorInfoMapper;

    @Autowired
    HisMedicalMapper hisMedicalMapper;
    @Autowired
    HisHosptalregistMapper hisHosptalregistMapper;

    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisHosptalregistService hisHosptalregistService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisMedicalRecordMapper hisMedicalRecordMapper;

    @Autowired
    HisTollRecordService hisTollRecordService;

    @Autowired
    HisUserDoctorMapper hisUserDoctorMapper;

    @Autowired
    HisNewbornService hisNewbornService;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    HisProjectMapper hisProjectMapper;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisTollRecordMapper hisTollRecordMapper;

    @Autowired
    HisTollDetailsMapper hisTollDetailsMapper;

    /**
     * @Description 新增
     * @Author muxu
     * @Date 2019/6/27
     * @Time 15:31
     * @Return core.message.Message
     * @Params [hisMedicalRecord, request]
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisHospitalManage hisHospitalManage) throws Exception {
        List<HisHospitalManage> check = hisHospitalManageMapper.selectByHosOrder(hisHospitalManage.getPatientId());
        HisPatientInfo hisPatientInfo = hisPatientInfoMapper.selectByPrimaryKey(hisHospitalManage.getPatientId());
        HisHosptalregist hisHosptalregist = hisHosptalregistMapper.selectByPrimaryKey(hisHospitalManage.getHosptalRegistId());
        HisMedicalRecord hisMedicalRecord = new HisMedicalRecord();
        if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getId())) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageMapper.selectByNumber(hisHospitalManage.getMedicalNumber()))) {
                if (check.size() > 0 && check.get(0).getIsDischarged() == 1) {
                    return MessageUtil.createMessage(false, "审核失败，该病人还未出院！");

                } else {
                    hisHospitalManage.setIsDischarged(1);
                    hisHosptalregist.setCareLevel(hisHospitalManage.getCareLevel());
                    hisHosptalregist.setDoctorId(hisHospitalManage.getDoctorId());
                    hisHosptalregist.setNurseId(hisHospitalManage.getNurseId());
                    hisHosptalregist.setBedId(hisHospitalManage.getBedId());
                    hisHosptalregist.setWardId(hisHospitalManage.getWardId());
                    hisHosptalregist.setDepartmentId(hisHospitalManage.getDepartmentId());
                    hisHosptalregist.setInpatientDiagnosis(hisHospitalManage.getInpatientDiagnosis());
                    hisHosptalregist.setRemark(hisHospitalManage.getRemarks());
                    hisHosptalregist.setOutpatientDiagnosis(hisHospitalManage.getOutpatientDiagnosis());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisHosptalregist.getMedicalNumber())) {

                        hisMedicalRecord.setPatientId(hisHospitalManage.getPatientId());
                        hisMedicalRecordService.recEnter(null, String.valueOf(hisHospitalManage.getDoctorId()), 2, hisHospitalManage.getPatientId());
                        List<HisMedicalRecord> hisMedicalRecord1 = hisMedicalRecordMapper.selectByMedicalRecordOrder(hisHospitalManage.getPatientId());
                        hisHospitalManage.setMedicalNumber(hisMedicalRecord1.get(0).getMedicalRecordId());
                        hisHospitalManage.setHospitalizationDay("1");
                        hisHospitalManage.setDepositWarning(new BigDecimal(300));
                        hisHospitalManage.setPayHospitalizationDay("");
                        hisHospitalManage.setSex(hisPatientInfo.getSex());
                        hisHospitalManage.setRestDeposit(new BigDecimal(0));
                        hisHospitalManageMapper.insert(hisHospitalManage);
                        hisHosptalregistMapper.updateByPrimaryKey(hisHosptalregist);
                        return MessageUtil.createMessage(true, "审核通过，住院成功！");

                    } else {
                        //有待修改
                        hisHospitalManage.setHospitalizationDay("1");
                        hisHospitalManage.setDepositWarning(new BigDecimal(300));
                        hisHospitalManage.setPayHospitalizationDay("");
                        hisHospitalManage.setSex(hisPatientInfo.getSex());
                        hisHospitalManage.setRestDeposit(new BigDecimal(0));
                        hisHospitalManageMapper.insert(hisHospitalManage);
                        hisHosptalregistMapper.updateByPrimaryKey(hisHosptalregist);
                        return MessageUtil.createMessage(true, "审核通过，住院成功！");
                    }
                }
            } else {
                return MessageUtil.createMessage(false, "该条就诊编号已被使用过，已作废！！");
            }
        } else {
            HisHospitalManage check1 = hisHospitalManageMapper.selectByPrimaryKey(hisHospitalManage.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(check1)) {
                hisHospitalManage.setSex(check1.getSex());
                hisHospitalManage.setDepartmentId(check1.getDepartmentId());
                hisHospitalManage.setDoctorId(check1.getDoctorId());
                hisHospitalManage.setHospitalizationDay(check1.getHospitalizationDay());
                hisHospitalManage.setPayHospitalizationDay(check1.getPayHospitalizationDay());
                hisHospitalManage.setTollDetailsId(check1.getTollDetailsId());
                hisHospitalManage.setRestDeposit(check1.getRestDeposit());
                hisHospitalManage.setIsDischarged(check1.getIsDischarged());
                hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);
                return MessageUtil.createMessage(true, "更新成功！");
            } else {
                return MessageUtil.createMessage(false, "数据异常请联系管理员！");
            }
        }
    }


    /**
     * @Description 就诊记录新增
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:04
     * @Return core.message.Message
     * @Params [hisMedicalRecord]
     **/
    @Override
    @Transactional(readOnly = false)
    public Message hosEnter(Long id) throws Exception {
        HisHospitalManage check = hisHospitalManageMapper.selectByPrimaryKey(id);
        HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByPrimaryKey(id);
        HisTollRecordDetails hisTollRecordDetails = hisTollRecordService.hospitalDetails(hisHospitalManage.getMedicalNumber());
        if (!EmptyUtil.Companion.isNullOrEmpty(check)) {
            if (hisTollRecordDetails.getMoney().doubleValue() == 0.00 && hisTollRecordDetails.getRestDeposit().intValue() == 0) {
                hisHospitalManage.setIsDischarged(2);
                hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);
                HisNewborn hisNewborn = new HisNewborn();
                HisBed hisBed = hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId());
                hisNewborn.setBedNumber(hisBed.getNumber());
                hisNewbornService.updateIsEnable(hisNewborn);
                return MessageUtil.createMessage(true, "出院成功");
            } else {
                return MessageUtil.createMessage(false, "出院失败，请结清费用！！");
            }

        } else {
            return MessageUtil.createMessage(false, "出院失败，数据出错，请联系管理员！");

        }
    }


    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/17
     * @Time 14:00
     * @Return
     * @Params
     **/
    @Override
    @Transactional(readOnly = false)
    public Message fail(Long id) throws Exception {
        HisHosptalregist hisHosptalregist = hisHosptalregistMapper.selectByPrimaryKey(id);
        hisHosptalregist.setIsFail(3);
        hisHosptalregistService.insertIsFail(id);
        return MessageUtil.createMessage(true, "审核不通过");
    }

    /**
     * @Description list
     * @Author muxu
     * @Date 2019/6/27
     * @Time 16:22
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisHospitalManage> list(PageBean<HisHospitalManage> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.list(pageBean)));

        return pageBean;

    }

    /**
     * @Description 根据id查询
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:04
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [patientId]
     **/

    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectById(Long id){
        return CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.selectByPrimaryKey(id));
    }

    /**
     * @Description 删除
     * @Author muxu
     * @Date 2019/7/8
     * @Time 11:02
     * @Return core.message.Message
     * @Params [ids]
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisHospitalManageMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功！");
    }

    /**
     * @Description 根据登记表id查询
     * @Author muxu
     * @Date 2019/7/13
     * @Time 19:57
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [id]
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectByHosptalRegistId(Long id) {
        return hisHospitalManageMapper.selectByHosptalRegistId(id);
    }

    /**
     * @Description 根据patientId查询
     * @Author muxu
     * @Date 2019/7/13
     * @Time 19:58
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [id]
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectByPatientId(Long id) {
        return hisHospitalManageMapper.selectByPatientId(id);
    }

    /**
     * @Description 根据状态查询
     * @Author muxu
     * @Date 2019/7/13
     * @Time 19:58
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [patientId]
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectIsDischarged(Long patientId) {
        return null;
    }


    /**
     * @Description 根据patientId  list查询，并根据创建时间倒排序
     * @Author muxu
     * @Date 2019/7/13
     * @Time 19:58
     * @Return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params [hosptalRegistId]
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisHospitalManage> selectByHosOrder(Long patientId) {
        return CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.selectByHosOrder(patientId));

    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Description 住院信息表的基本信息查询，因list名被占用,故起为其他名（只查询未出院的）
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-15
     * @Time 11:44
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisHospitalManage> listForDetails(PageBean<HisHospitalManage> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.listForDetails(pageBean)));
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Description 根据就诊编号查看住院信息
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-16
     * @Time 9:52
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectByNumber(String medicalNumber) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.selectByNumber(medicalNumber));
    }


    /**
     * @Description 住院天数定时任务
     * @Author  muxu
     * @Date  2019/9/24
     * @Time 18:24
     * @Return void
     * @Params []
    **/
    @Override
    @Transactional(readOnly = false)
    public void startAddDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        logger.info(createdate);

        //根据创建时间查出记录
        List<HisHospitalManage> hisHospitalManageList = hisHospitalManageMapper.selectByDate();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageList) && !EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageList.size())) {
            Long[] ids = new Long[hisHospitalManageList.size()];
            int i = 0;
            for (HisHospitalManage h : hisHospitalManageList
            ) {
                ids[i] = h.getId();
                i++;
            }
            logger.info("-------------------开始住院天数日结-----------------------");
            String alreadyHospitalizationDay = startDateDaily(hisHospitalManageList);
            hisHospitalManageMapper.updateBatchForDaily(hisHospitalManageList);
            logger.info("-----------------住院天数日结个数为：" + hisHospitalManageList.size() + "-----------------------");
            logger.info("-------------------结束住院天数日结-----------------------");
        } else {
            logger.info("-------------------住院天数日结结束，今天无可结算天数-----------------------");
        }
    }


    private String startDateDaily(List<HisHospitalManage> hisHospitalManageList) {
        for (HisHospitalManage hd : hisHospitalManageList) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            hd.setHospitalizationDay(String.valueOf((date.getTime() - hd.getCreateDate().getTime()) / (24 * 60 * 60 * 1000) + 1));
        }
        return null;
    }


    /**
     * @Description 护理费用定时任务
     * @Author  muxu
     * @Date  2019/9/24
     * @Time 18:28
     * @Return
     * @Params
    **/
    @Override
    @Transactional(readOnly = false)
    public void startAddcareLevel() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        logger.info(createdate);

        List<HisHospitalManage> hisHospitalManageList = hisHospitalManageMapper.selectByDate();
        if (hisHospitalManageList.size()==0){
            logger.info("-------------------无护理消费人员-----------------------");
        }else {
            logger.info("-------------------扫描到" + hisHospitalManageList.size() + "病人进行护理消费日结-----------------------");
            if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageList) && !EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageList.size())) {
                Long[] ids = new Long[hisHospitalManageList.size()];
                List<HisHospitalManage> hisHospitalManageList1 = new ArrayList<>();
                int i = 0;
                String number ="0";
                for (HisHospitalManage h : hisHospitalManageList
                ) {
                    ids[i] = h.getId();
                    HisHospitalManage hisHospitalManage1 = hisHospitalManageMapper.selectByPrimaryKey(ids[i]);
                    hisHospitalManage1.setId(ids[i]);
                    HisMedicalRecord hisMedicalRecord = hisMedicalRecordMapper.selectByMedicalRecordId(hisHospitalManage1.getMedicalNumber());
                    HisRecordProject hisRecordProject = new HisRecordProject();
                    HisTollDetails hisTollDetails = new HisTollDetails();
                    HisTollRecord hisTollRecord = new HisTollRecord();
                    hisRecordProject.setRecordId(hisMedicalRecord.getId());
                    //一级护理
                    if (hisHospitalManage1.getCareLevel()==1){
                        number = "120100003（ACAC001）";
                    }else if (hisHospitalManage1.getCareLevel()==2){   //二级护理
                        number = "120100004（ACAB001）";
                    }else if (hisHospitalManage1.getCareLevel()==3){   //三级护理
                        number = "120100005（ACAA001）";
                    }else if (hisHospitalManage1.getCareLevel()==4){  //四级护理
                        number = "120100014（ACNZ001）";
                    }else if (hisHospitalManage1.getCareLevel()==5){  //特级监护
                        number = "120100002（ACAD001）";
                    }else if (hisHospitalManage1.getCareLevel()==6){  //重症监护
                        number = "120100015（ACNZ002）";
                    }
                    HisProject hisProject =hisProjectMapper.queryHisProjectByNumber(number);
                    hisHospitalManage1.setRestDeposit(h.getRestDeposit().subtract(hisProject.getPrice()));
                    hisRecordProject.setName(hisProject.getName());
                    hisRecordProject.setType(hisProject.getType());
                    hisRecordProject.setPrice(hisProject.getPrice());
                    hisRecordProject.setNumber(hisProject.getNumber());
                    hisRecordProject.setPinyinCode(hisProject.getPinyinCode());
                    hisRecordProject.setUnit(hisProject.getUnit());
                    hisRecordProject.setIsChecked(new Short("2"));
                    hisRecordProject.setIsPayed(new Short("1"));
                    hisRecordProject.setIsBack(2);
                    hisRecordProject.setProjectId(hisProject.getId());
                    hisRecordProject.setNum(1);
                    hisRecordProject.setCreateDate(date);
                    hisRecordProject.setUpdateDate(date);
                    hisRecordProjectService.insert(hisRecordProject);
                    hisTollDetails.setName(hisProject.getName());
                    hisTollDetails.setTargetId(hisRecordProject.getId());
                    hisTollDetails.setType(2);
                    hisTollDetails.setMoney(hisProject.getPrice());
                    hisTollDetails.setCreateDate(date);
                    hisTollDetails.setUpdateDate(date);
                    hisTollRecord.setMoney(hisProject.getPrice());
                    hisTollRecord.setActualCharge(hisProject.getPrice());
                    hisTollRecord.setRecoverTheFee(new BigDecimal("0.00"));
                    hisTollRecord.setType(2);
                    hisTollRecord.setAttenchType(2);
                    hisTollRecord.setCreateDate(date);
                    hisTollRecord.setUpdateDate(date);
                    hisTollRecord.setDeposit(h.getRestDeposit().subtract(hisProject.getPrice()));
                    String createdate1 = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    int count = hisTollRecordMapper.selectNumbCount(createdate1) + 1;
                    //编号
                    String number1 = createdate1 + String.format("%05d", count);
                    number1 = "HTR" + number1;
                    hisTollRecord.setNumber(number1);
                    hisTollRecord.setMedicalRecordId(hisMedicalRecord.getMedicalRecordId());
                    hisTollRecord.setIsSettlement(2);
                    hisTollRecordMapper.insert(hisTollRecord);
                    hisTollDetails.setTollRecordId(hisTollRecord.getId());
                    hisTollDetailsMapper.insert(hisTollDetails);
                    hisHospitalManageList1.add(hisHospitalManage1);
                    i++;
                }
                logger.info("-------------------开始护理费用日结-----------------------");
                hisHospitalManageMapper.updateBatchForDaily1(hisHospitalManageList1);
                logger.info("-----------------护理费用日结人数为：" + hisHospitalManageList.size() + "-----------------------");
                logger.info("-------------------结束护理费用日结-----------------------");
        }else {
                logger.info("-------------------护理费用日结结束，今天无可结算费用-----------------------");
            }
        }
    }

    /**
     * @Description 时间段内住院人数统计
     * @Author muxu
     * @Date 2019/7/28
     * @Time 22:00
     * @Return
     * @Params
     **/


    @Override
    @Transactional(readOnly = true)
    public Integer[] selectHospitalManageDataCount(Date createDate) throws Exception {
        Integer[] outs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (Integer i : outs) {
            i = new Integer(0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        //取到当前日期的数据
        List<HisHospitalManage> hisHospitalManageList = hisHospitalManageMapper.selectForCalculateDate(createDate, calendar.getTime());
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageList) && hisHospitalManageList.size() != 0) {
            for (HisHospitalManage hisHospitalManage1 : hisHospitalManageList) {
                Calendar date = Calendar.getInstance();
                date.setTime(hisHospitalManage1.getCreateDate());
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
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据住院编号查询
     * @Params [medicalRecordId]
     * @Author chenzhicai
     * @Date 2019-07-30
     * @Time 14:18
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectByHospNumber(String medicalRecordId) {
        return hisHospitalManageMapper.selectByHospNumber(medicalRecordId);
    }

    /**
     * @param
     * @Description 未出院病床数
     * @Author: dingli
     * @return: int
     * @Date 2019/8/13
     * @Time 17:29
     **/
    @Override
    @Transactional(readOnly = true)
    public int countUseBed() throws Exception {
        return hisHospitalManageMapper.countUseBed();
    }

    /**
     * @param
     * @Description 获取所有未出现的信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Date 2019/8/13
     * @Time 18:00
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisHospitalManage> getHisHospitalManageAll(HisHospitalManage hisHospitalManage) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.getHisHospitalManageAll(hisHospitalManage));
    }

    /**
     * @param number
     * @Description 根据病床号获取详细信息
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/8/14
     * @Time 9:40
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage getHisHospitalManageByNumber(String number) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageMapper.getHisHospitalManageByNumber(number);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)) {
            return CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.getHisHospitalManageByNumber(number));
        }
        return new HisHospitalManage();
    }

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @功能说明 查询新生儿的病床号
     * @Params []
     * @Author XJP
     * @Date 2019/9/11
     * @Time 13:19
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisHospitalManage> getHisHospitalManageBed() throws Exception {
        List<HisHospitalManage> hisHospitalManageBed = hisHospitalManageMapper.getHisHospitalManageBed();
        if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalManageBed)) {
            return new ArrayList<>();
        } else {
            List<HisHospitalManage> collect = hisHospitalManageBed.stream().filter(e -> e.getBedsNumber() != null).collect(Collectors.toList());
            return collect;
        }
    }

    /**
     * @return
     * @Description 根据patientId查看病人当前是否正在住院
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 15:16
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectByPatientIdAndInpatient(Long patientId) throws Exception {
        return hisHospitalManageMapper.selectByPatientIdAndInpatient(patientId);
    }

    /**
     * @return
     * @Description 修改
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 15:32
     **/
    @Override
    @Transactional(readOnly = false)
    public void update(HisHospitalManage hisHospitalManage) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage))
            hisHospitalManageMapper.updateByPrimaryKey(hisHospitalManage);
    }

    /**
     * @Description 根据住院编号获取病人信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/9/14
     * @Time 15:41
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalManage selectNumber(String number) {
        return CodeHelper.getInstance().setCodeValue(hisHospitalManageMapper.selectNumber(number));
    }
}


