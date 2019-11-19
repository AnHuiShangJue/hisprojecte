package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisHosptalregistMapper;
import com.ahsj.hiscore.dao.HisMedicalMapper;
import com.ahsj.hiscore.dao.HisPatientInfoMapper;
import com.ahsj.hiscore.entity.HisHosptalregist;
import com.ahsj.hiscore.entity.HisMedical;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.services.HisHosptalregistService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalService;
import com.ahsj.hiscore.services.HisPatientService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HisHosptalregistServiceImpl implements HisHosptalregistService {
    @Autowired
    HisHosptalregistMapper hisHosptalregistMapper;
    @Autowired
    HisPatientInfoMapper hisPatientInfoMapper;
    @Autowired
    HisPatientService hisPatientService;
    @Autowired
    HisMedicalRecordService hisMedicalRecordService;
    @Autowired
    HisMedicalMapper hisMedicalMapper;

    @Autowired
    HisMedicalService hisMedicalService;

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisHosptalregist]
     * @Author dingli
     * @Date 2019-07-10
     * @Time 9:36
     **/
    @Transactional(readOnly = false)
    @Override
    public Message saveOrUpdate(HisHosptalregist hisHosptalregist) throws Exception {
            HisPatientInfo hisPatientInfo = new HisPatientInfo(); //病人信息
            hisPatientInfo.setName(hisHosptalregist.getName());
            hisPatientInfo.setIdcard(hisHosptalregist.getIdcard());
            hisPatientInfo.setPhonenumber(hisHosptalregist.getPhonenumber());
            hisPatientInfo.setLocation(hisHosptalregist.getLocation());
            hisPatientInfo.setHeight(hisHosptalregist.getHeight());
            hisPatientInfo.setWeight(hisHosptalregist.getWeight());
            hisPatientInfo.setIsMarried(hisHosptalregist.getIsMarried());
            hisPatientInfo.setSex(hisHosptalregist.getSex());
            hisPatientInfo.setAge(hisHosptalregist.getAge());
            HisPatientInfo check = hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard());
            String createdate = new SimpleDateFormat("yyyyMMdd").format(new Date());//当前时间年月日
            if (EmptyUtil.Companion.isNullOrEmpty(hisHosptalregist.getId())) {//新增
                if (!EmptyUtil.Companion.isNullOrEmpty(hisHosptalregist.getMedicalNumber())) {
                    HisMedicalRecord check1 = hisMedicalRecordService.selectByMedicalRecordId(hisHosptalregist.getMedicalNumber());
                    HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHosptalregist.getMedicalNumber());
                    if (!EmptyUtil.Companion.isNullOrEmpty(check1)) {
                        //入院登记不存在
                        if (!EmptyUtil.Companion.isNullOrEmpty(check)) {
                            hisPatientInfo.setId(check.getId());
                            //身份证号存在,把主键给新传入的病人对象
                            if (check.getName().equals(hisPatientInfo.getName())) {//身份证号与名字相同
                                HisMedical hisMedical = hisMedicalMapper.selectByRecordId(hisMedicalRecord.getId());
                                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedical)) {
                                    hisPatientService.saveOrUpdate(hisPatientInfo);//更新病人信息
                                    hisHosptalregist.setNumber("HR" + createdate + String.format("%05d", hisHosptalregistMapper.selectNumCount(createdate) + 1));//设置编号
                                    hisHosptalregist.setPatientId(hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard()).getId());
                                    hisMedical.setChiefcomplaint(hisHosptalregist.getOutpatientDiagnosis());
                                    hisHosptalregistMapper.insert(hisHosptalregist);//入院登记

                                    hisMedicalService.saveOrUpdate(hisMedical);//门诊诊断甩回
                                    return MessageUtil.createMessage(true, ""+hisHosptalregist.getId());
                                } else {
                                    return MessageUtil.createMessage(false, "前有未完成操作，请先完成就诊流程！");
                                }
                            } else {
                                return MessageUtil.createMessage(false, "新增失败,身份证号与姓名不符！");
                            }
                        } else { //身份证号不存在
                            return MessageUtil.createMessage(false, "新增失败，无该身份证病人信息！");
                        }
                    } else {
                        return MessageUtil.createMessage(false, "查询失败，就诊编号不存在！");
                    }
                } else {//修改入院登记
                    if (!EmptyUtil.Companion.isNullOrEmpty(check)) {
                        hisPatientInfo.setId(check.getId());
                        //身份证号存在,把主键给新传入的病人对象
//                        if (check.getName().equals(hisPatientInfo.getName())) {//身份证号与名字相同
                            hisPatientService.saveOrUpdate(hisPatientInfo);//更新病人信息
                            hisHosptalregist.setNumber("HR" + createdate + String.format("%05d", hisHosptalregistMapper.selectNumCount(createdate) + 1));//设置编号
                            hisHosptalregist.setPatientId(hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard()).getId());
                            hisHosptalregist.setMedicalNumber(null);
                            hisHosptalregistMapper.insert(hisHosptalregist);//入院登记
                            return MessageUtil.createMessage(true, ""+hisHosptalregist.getId());
 /*                       } else {
                            return MessageUtil.createMessage(false, "新增失败,身份证号与姓名不符！该");
                        }*/
                    } else { //身份证号不存在
                        hisPatientService.saveOrUpdate(hisPatientInfo);//更新病人信息
                        hisHosptalregist.setNumber("HR" + createdate + String.format("%05d", hisHosptalregistMapper.selectNumCount(createdate) + 1));//设置编号
                        hisHosptalregist.setPatientId(hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard()).getId());
                        hisHosptalregist.setMedicalNumber(null);
                        hisHosptalregistMapper.insert(hisHosptalregist);//入院登记
                        return MessageUtil.createMessage(true, ""+hisHosptalregist.getId());
                    }
                }
            } else {
                return MessageUtil.createMessage(false, "数据错误，请联系管理员！");

            }
        }


    /**
     * @Description selectById
     * @Author muxu
     * @Date 2019/7/11
     * @Time 9:45
     * @Return
     * @Params
     **/

    @Override
    @Transactional(readOnly = true)
    public HisHosptalregist selectById(Long id) throws Exception{
        return hisHosptalregistMapper.selectByPrimaryKey(id);
    }

    /**
     * @Description isfail
     * @Author   muxu
     * @Date 2019/7/17
     * @Time 17:37
     * @Return
     * @Params
    **/

    @Override
    @Transactional(readOnly = false)
    public HisHosptalregist insertIsFail(Long id) throws Exception {
        HisHosptalregist hisHosptalregist = hisHosptalregistMapper.selectByPrimaryKey(id);
        hisHosptalregistMapper.updateIsFail(hisHosptalregist);
        return hisHosptalregist;
    }
}

