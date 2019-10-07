package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicationDetailsMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.TranslateModel.HisMedicationDetailsTranslate;
import com.ahsj.hiscore.entity.TranslateModel.HisProjectTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateDelete;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.*;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HisMedicationDetailsServicelmpl implements HisMedicationDetailsService {


    private Logger log = LoggerFactory.getLogger(HisMedicationDetailsServicelmpl.class.getSimpleName());


    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisInfusionService hisInfusionService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Description list
     * @Params [pageBean]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:12
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicationDetails> list(PageBean<HisMedicationDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicationDetailsMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:12
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisMedicationDetailsMapper.deleteByPrimaryKey(id);
            // log.info("--------------------药品明细删除翻译发送开始--------------------------");
            TranslateDelete translateDelete = new TranslateDelete();
            translateDelete.setId(id);
            translateDelete.setModel(Constants.TRANSLATE_HIS_MEDICATIONDETAILS);
            amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
            // log.info(JsonUtils.serialize(translateDelete));
            // log.info("--------------------药品明细删除翻译发送结束--------------------------");
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return core.message.Message
     * @Description 新增或保存
     * @Params [hisMedicationDetailsList]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:12
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(List<HisMedicationDetails> hisMedicationDetailsList) throws Exception {
        //根据就诊记录删除所有
        hisMedicationDetailsMapper.deleteByMedicalRecordIdNotIsOutOrIspay(hisMedicationDetailsList.get(0).getMedicalRecordId());
        //新增一批用药明细
        hisMedicationDetailsMapper.insertBatch(hisMedicationDetailsList);
       /* for (HisMedicationDetails hisMedicationDetails : hisMedicationDetailsList) {
            log.info("--------------------药品明细新增翻译发送开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisMedicationDetailsTranslate hisMedicineInfoTranslate = new HisMedicationDetailsTranslate();
            BeanUtils.copyProperties(hisMedicationDetails, hisMedicineInfoTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicationDetailsTranslate(hisMedicineInfoTranslate);
            amqpTemplat.convertAndSend("com.ahsj.addHisMedicationDetails", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------药品明细新增翻译发送结束--------------------------");

        }*/


        TranslateModels translateModels = new TranslateModels();
        BaseLoginUser loginUser = new BaseLoginUser();
        List<HisMedicationDetailsTranslate> infoTranslates = new ArrayList<>();
        for (HisMedicationDetails hisMedicationDetails  : hisMedicationDetailsList) {
            //  log.info("--------------------药品明细新增翻译发送开始---------------------------");
            HisMedicationDetailsTranslate translate = new HisMedicationDetailsTranslate();
            BeanUtils.copyProperties(hisMedicationDetails, translate);
            infoTranslates.add(translate);
            // log.info("--------------------药品明细新增翻译发送结束---------------------------");
        }
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisMedicationDetails(infoTranslates);
        amqpTemplat.convertAndSend("com.ahsj.addHisMedicationDetailsList", JsonUtils.serialize(translateModels));
        log.info(JsonUtils.serialize(translateModels));

        return MessageUtil.createMessage(true, "新增成功");
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Description 根据就诊记录ID查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:12
     **/
    @Override
    @Transactional(readOnly = false)
    public List<HisMedicationDetails> selectByRecordId(Long id) throws Exception {
        return hisMedicationDetailsMapper.selectByRecordId(id);
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Description 根据就诊记录ID查询未付费且未出药的用药明细
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-08-15
     * @Time 17:44
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> selectByRecordIdNotIsOutOrIsPay(Long id) {
        return hisMedicationDetailsMapper.selectByRecordIdNotIsOutOrIsPay(id);
    }

    /**
     * @param medicalNumber
     * @Description 打印用药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Date 2019/8/19
     * @Time 13:58
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> selectByMedicalNumber(String medicalNumber) throws Exception {
        List<HisMedicationDetails> hisMedicationDetails = hisMedicationDetailsMapper.selectByMedicalNumber(medicalNumber);
        for (HisMedicationDetails h : hisMedicationDetails) {
            Translate translate = new Translate();
            translate.setTranslateId(h.getmId());//药品基本表ID
            translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            translates.stream().filter(f-> StringUtils.equals(h.getDrugsName(),f.getTranslateChina())).forEach(f -> h.setTdrugsName(f.getTranslateKhmer()));//赋翻译字段
            translates.stream().filter(f-> StringUtils.equals(h.getDrugsSpec(),f.getTranslateChina())).forEach(f -> h.setTdrugsSpec(f.getTranslateKhmer()));//赋翻译字段
        }
        return hisMedicationDetails;
    }

    /**
     * @param record
     * @return
     * @Description 修改付费状态
     * @Author: dingli
     * @Date 2019/7/18
     * @Time 14:41
     **/
    @Override
    public int updateHisMedicationDetails(HisMedicationDetails record) throws Exception {
        return hisMedicationDetailsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @return void
     * @Description 批量更新药是否已出状态
     * @Params [hisMedicationDetails]
     * @Author zhushixiang
     * @Date 2019-07-23
     * @Time 20:38
     **/
    @Override
    @Transactional(readOnly = false)
    public void updateBatch(List<HisMedicationDetails> hisMedicationDetails) {
        hisMedicationDetailsMapper.updateBatch(hisMedicationDetails);
    }

    /**
     * @Description 统计各个时间段的售药数目
     * @Author muxu
     * @Date 2019/7/28
     * @Time 22:45
     * @Return
     * @Params
     **/
    @Override
    public Integer[] selectMedicationDetailsDataCount(Date createDate) throws Exception {
        Integer[] outs = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (Integer i : outs) {
            i = new Integer(0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        //取到当前日期的数据
        List<HisMedicationDetails> hisMedicationDetailsList = hisMedicationDetailsMapper.selectMedicationDetailsDataCount(createDate, calendar.getTime());
        if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList) && hisMedicationDetailsList.size() != 0) {
            for (HisMedicationDetails hisMedicationDetails1 : hisMedicationDetailsList) {
                Calendar date = Calendar.getInstance();
                date.setTime(hisMedicationDetails1.getCreateDate());
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

    @Override
    public PageBean<HisMedicationDetails> useDrug(PageBean<HisMedicationDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicationDetailsMapper.useDrug(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @功能说明
     * @Params [hisMedicationDetails]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 11:02
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> queryTranslateInfoByDate(HisMedicationDetails hisMedicationDetails) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<HisMedicationDetails> translateList = hisMedicationDetailsMapper.queryTranslateInfoByDate(hisMedicationDetails);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 16:35
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> queryAll() throws Exception {
        List<HisMedicationDetails> hisMedicationDetails = hisMedicationDetailsMapper.queryAll();
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
            log.info("查询失败");
            return new ArrayList<>();
        } else {
            return hisMedicationDetails;
        }
    }

    /**
     * @Description 查询输液单可选药品信息
     * @Author muxu
     * @Date 2019/8/29
     * @Time 15:08
     * @Return
     * @Params
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicationDetails> selectOutpatientInfusionByMedicalRecordId(PageBean<HisMedicationDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicationDetailsMapper.selectOutpatientInfusionByMedicalRecordId(pageBean)));
        return pageBean;
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Date 2019/9/7
     * @Time 11:17
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> selectPrint(String number) throws Exception {
        HisRegistered hisRegistered =hisRegisteredService.selectByNumber(number);
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByRegisterId(hisRegistered.getId());
        //搜索出所有未付款的用药明细
        List<HisMedicationDetails> hisMedicationDetailsList = hisMedicationDetailsMapper.selectPrint(number);
        //搜索出所有未付款的输液单
        List<HisInfusion> hisInfusionList = hisInfusionService.selectByRecordNumberAndNotPay(hisMedicalRecord.getMedicalRecordId());

        List<HisMedicationDetails> medicationDetailsList = new ArrayList<>();
        //遍历用药明细
   /*     if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList) && hisMedicationDetailsList.size() != 0) {
            for (HisMedicationDetails hisMedicationDetails : hisMedicationDetailsList) {
                List<HisInfusion> hisInfusions = hisInfusionService.selectByMedicationId(hisMedicationDetails.getId());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisInfusions) && hisInfusions.size() != 0){
                    Integer count = hisMedicationDetails.getCount();
                    for (HisInfusion hisInfusion : hisInfusions) {
                        HisMedicationDetails save = new HisMedicationDetails();
                        save.setDrugsName(hisMedicationDetails.getDrugsName());
                        save.setCount(Integer.valueOf(hisInfusion.getDosage()));
                        save.setmId(hisMedicationDetails.getmId());
                        save.setDrugsSpec(hisMedicationDetails.getDrugsSpec());
                        save.setPrice(hisMedicationDetails.getPrice());
                        save.setTotalPrice(hisMedicationDetails.getTotalPrice());
                        save.setDescription(hisInfusion.getUsages());
                        count -= Integer.valueOf(hisInfusion.getDosage());
                        medicationDetailsList.add(save);
                    }
                    if(count > 0){
                        hisMedicationDetails.setCount(count);
                        medicationDetailsList.add(hisMedicationDetails);
                    }
                }else {
                    medicationDetailsList.add(hisMedicationDetails);
                }
            }
        }*/

   //遍历输液单表 为打印页面添加输液单
        if(!EmptyUtil.Companion.isNullOrEmpty(hisInfusionList) && hisInfusionList.size() != 0){
            for (HisInfusion hisInfusion : hisInfusionList) {
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList) && hisMedicationDetailsList.size() != 0){
                    for (HisMedicationDetails hisMedicationDetails : hisMedicationDetailsList) {
                        if(hisInfusion.getMedicationId().equals(hisMedicationDetails.getId()) ){
                            hisMedicationDetails.setCount(hisMedicationDetails.getCount() - Integer.valueOf(hisInfusion.getDosage()));
                            HisMedicationDetails save = new HisMedicationDetails();
                            save.setDrugsName(hisInfusion.getDrugname());
                            save.setCount(Integer.valueOf(hisInfusion.getDosage()));
                            save.setmId(hisMedicationDetails.getmId());
                            save.setDrugsSpec(hisMedicationDetails.getDrugsSpec());
                            save.setPrice(hisMedicationDetails.getPrice());
                            save.setTotalPrice(hisMedicationDetails.getPrice().multiply(BigDecimal.valueOf(save.getCount())));
                            save.setDescription(hisInfusion.getUsages());
                            medicationDetailsList.add(save);
                            break;
                        }
                    }
                }
            }
        }

        //编辑添加过输液单后的用药明细列表
        for (HisMedicationDetails hisMedicationDetails : hisMedicationDetailsList) {
            if(hisMedicationDetails.getCount() > 0){
                medicationDetailsList.add(hisMedicationDetails);
            }
        }
        for (HisMedicationDetails hisMedicationDetails : medicationDetailsList) {
            Translate translate = new Translate();//翻译
            translate.setTranslateId(hisMedicationDetails.getmId());
            translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                HisMedicineInfo hisMedicineInfo = hisMedicineInfoService.selectById(hisMedicationDetails.getmId());
                translates.stream().filter(f -> f.getTranslateChina().equals(hisMedicineInfo.getDrugsName())).forEach(f -> hisMedicationDetails.setTdrugsName(f.getTranslateKhmer()));
                translates.stream().filter(f -> f.getTranslateChina().equals(hisMedicationDetails.getDrugsSpec())).forEach(f -> hisMedicationDetails.setTdrugsSpec(f.getTranslateKhmer()));
            }
            Translate translate1 = new Translate();
            translate1.setTranslateId(hisMedicationDetails.getId());
            translate1.setTranslateType(Constants.TRANSLATE_HIS_MEDICATIONDETAILS);
            List<Translate> translates2 = iTranslateService.queryTranslate(translate1);
            if (!EmptyUtil.Companion.isNullOrEmpty(translates2)) {
                translates2.stream().filter(f -> f.getTranslateChina().equals(hisMedicationDetails.getDescription())).forEach(e -> hisMedicationDetails.setTdescription(e.getTranslateKhmer()));
            }


        }
        return medicationDetailsList;
    }

    /**
     *@Description 查询出当前就诊编号未付费的药品
     *@Params [recordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:34
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> selectByRecordIdNotIsPay(Long medicalRecordId) throws Exception {
        return hisMedicationDetailsMapper.selectByRecordIdNotIsPay(medicalRecordId);
    }

    /**
     *@Description
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 19:01
    **/
    @Override
    @Transactional(readOnly = false)
    public Message insert(HisMedicationDetails hisMedicationDetails) throws Exception {
         hisMedicationDetailsMapper.insert(hisMedicationDetails);
         return MessageUtil.createMessage(true,"新增用药明细成功");
    }

    @Override
    @Transactional(readOnly = true)
    public HisMedicationDetails selectById(Long correspondId) {
        return hisMedicationDetailsMapper.selectByPrimaryKey(correspondId);
    }

    /**
     *@Description
     *@Params [hisMedicalOrderDetail]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 10:52
    **/
    @Override
    @Transactional(readOnly = false)
    public void update(HisMedicationDetails hisMedicationDetails) {
        hisMedicationDetailsMapper.updateByPrimaryKey(hisMedicationDetails);
    }

    /**
     *@Description 单条删除
     *@Params [id]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 11:18
    **/
    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) throws Exception {
        hisMedicationDetailsMapper.deleteByPrimaryKey(id);
    }

    /**
     *@Description 根据就诊记录ID查询未付的药品
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 17:57
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicationDetails> listByRecordIdAndNotPay(PageBean<HisMedicationDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicationDetailsMapper.listByRecordIdAndNotPay(pageBean)));
        return pageBean;
    }

    /**
     *@Description 根据ids 批量查找
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 19:24
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> selectByIds(Long[] ids) throws Exception {
        return hisMedicationDetailsMapper.selectByIds(ids);
    }
}
