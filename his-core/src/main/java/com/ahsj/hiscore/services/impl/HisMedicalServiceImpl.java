package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.dao.HisMedicalMapper;
import com.ahsj.hiscore.entity.HisMedical;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.TranslateModel.HisMedicalTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisMedicalService;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

/**
 * @program: his
 * @description:HisMedicalService
 * @author: chenzhicai
 * @create: 2019-07-11-17-01
 **/
@Service
public class HisMedicalServiceImpl implements HisMedicalService {

    private Logger log = LoggerFactory.getLogger(HisMedicalServiceImpl.class.getSimpleName());


    @Autowired
    HisMedicalMapper hisMedicalMapper;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    ITranslateService iTranslateService;

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisMedical]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 10:22
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedical hisMedical) throws Exception {
        HisMedical checkForExit = hisMedicalMapper.selectByRecordId(hisMedical.getRecordId());
        if (!EmptyUtil.Companion.isNullOrEmpty(checkForExit) ) {
            hisMedicalMapper.updateByPrimaryKeySelective(hisMedical);

            log.info("--------------------治疗计划更新翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisMedicalTranslate hisMedicalTranslate = new HisMedicalTranslate();
            BeanUtils.copyProperties(hisMedical, hisMedicalTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicalTranslate(hisMedicalTranslate);
            amqpTemplat.convertAndSend("com.ahsj.update.hisMedical", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------治疗计划更新翻译发送结束---------------------------");

            return MessageUtil.createMessage(true, "更新成功！");
        } else {
            hisMedicalMapper.insert(hisMedical);

            log.info("--------------------治疗计划新增翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisMedicalTranslate hisMedicalTranslate = new HisMedicalTranslate();
            BeanUtils.copyProperties(hisMedical, hisMedicalTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicalTranslate(hisMedicalTranslate);
            amqpTemplat.convertAndSend("com.ahsj.add.hisMedical", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------治疗计划新增翻译发送结束---------------------------");

            return MessageUtil.createMessage(true, "保存成功！");
        }
    }

    /**
     * @return com.ahsj.hiscore.entity.HisMedical
     * @Description 根绝就诊编号查询门诊流程
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:04
     **/
    @Override
    @Transactional(readOnly = false)
    public HisMedical selectByRecordId(Long id) throws Exception {
        return hisMedicalMapper.selectByRecordId(id);
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisMedical
     * @Date 2019/9/7
     * @Time 12:54
     **/
    @Override
    @Transactional(readOnly = true)
    public HisMedical selectPrint(String number) throws Exception {
        HisMedical hisMedical = hisMedicalMapper.selectPrint(number);
        Translate translate = new Translate();//翻译
        translate.setTranslateId(hisMedical.getId());
        translate.setTranslateType(Constants.TRANSLATE_HIS_HHISMEDICAL);
        List<Translate> translates = iTranslateService.queryTranslate(translate);
        if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
            for (Translate translate1 : translates) {
                if (translate1.getTranslateChina().equals(hisMedical.getNowCondition())) {
                    hisMedical.setTnowCondition(translate1.getTranslateKhmer());
                    if (!EmptyUtil.Companion.isNullOrEmpty(translate1.getTranslateJoin())) {
                        hisMedical.setTnowCondition1(translate1.getTranslateJoin());
                    }
                }
                if (translate1.getTranslateChina().equals(hisMedical.getHiscondition())) {
                    hisMedical.setThiscondition(translate1.getTranslateKhmer());
                    if (!EmptyUtil.Companion.isNullOrEmpty(translate1.getTranslateJoin())) {
                        hisMedical.setThiscondition1(translate1.getTranslateJoin());
                    }
                }
                if (translate1.getTranslateChina().equals(hisMedical.getChiefcomplaint())) {
                    hisMedical.setTchiefcomplaint(translate1.getTranslateKhmer());
                    if (!EmptyUtil.Companion.isNullOrEmpty(translate1.getTranslateJoin())) {
                        hisMedical.setTchiefcomplaint1(translate1.getTranslateJoin());
                    }
                }

                if (translate1.getTranslateChina().equals(hisMedical.getPersonalHistory())) {
                    hisMedical.setTpersonalHistory(translate1.getTranslateKhmer());
                    if (!EmptyUtil.Companion.isNullOrEmpty(translate1.getTranslateJoin())) {
                        hisMedical.setTpersonalHistory1(translate1.getTranslateJoin());
                    }
                }

                if (translate1.getTranslateChina().equals(hisMedical.getAllergies())) {
                    hisMedical.setTallergies(translate1.getTranslateKhmer());
                    if (!EmptyUtil.Companion.isNullOrEmpty(translate1.getTranslateJoin())) {
                        hisMedical.setTallergies1(translate1.getTranslateJoin());
                    }
                }

                if (translate1.getTranslateChina().equals(hisMedical.getFamilyHistory())) {
                    hisMedical.setTfamilyHistory(translate1.getTranslateKhmer());
                    if (!EmptyUtil.Companion.isNullOrEmpty(translate1.getTranslateJoin())) {
                        hisMedical.setTfamilyHistory1(translate1.getTranslateJoin());
                    }
                }
                if (translate1.getTranslateChina().equals(hisMedical.getOther())) {
                    hisMedical.setTother(translate1.getTranslateKhmer());
                    if (!EmptyUtil.Companion.isNullOrEmpty(translate1.getTranslateJoin())) {
                        hisMedical.setTother1(translate1.getTranslateJoin());
                    }
                }
            }


        }
        return hisMedical;
    }

    /**
     *@Description 根据就诊记录ID查询当次就诊判断
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedical
     *@Author zhushixiang
     *@Date 2019-09-09
     *@Time 17:37
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedical selectByMedicalRecordId(Long medicalRecordid) throws Exception {
        return hisMedicalMapper.selectByMedicalRecordId(medicalRecordid);
    }
}

    