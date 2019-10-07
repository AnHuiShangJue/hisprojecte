package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisAnkleTemplateMapper;
import com.ahsj.hiscore.entity.HisAnkleTemplate;
import com.ahsj.hiscore.entity.TranslateModel.HisAnkleTemplateTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisAnkleTemplateService;
import core.entity.PageBean;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class HisAnkleTemplateServiceImpl implements HisAnkleTemplateService {

    private Logger log = LoggerFactory.getLogger(HisAnkleTemplateServiceImpl.class.getSimpleName());

    @Autowired
    HisAnkleTemplateMapper hisAnkleTemplateMapper;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;

    /**
     *@Description 查找护嘱模板
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 15:50
    */
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisAnkleTemplate> listWithName(PageBean<HisAnkleTemplate> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisAnkleTemplateMapper.listWithName(pageBean)));
        return pageBean;
    }
    /**
     *@Description 查找护嘱明细
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 17:11
    */
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisAnkleTemplate> list(String templateNumber) throws Exception {
        PageBean<HisAnkleTemplate> pageBean = new PageBean<>();
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisAnkleTemplateMapper.list(templateNumber)));
        return pageBean;
    }

    /**
     *@Description 新增或更新
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 20:05
    */
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisAnkleTemplate hisAnkleTemplate) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisAnkleTemplate.getId())){
            //id为空则新增
            //查找该就诊记录对应的记录list
            List<HisAnkleTemplate> hisAnkleTemplateList = hisAnkleTemplateMapper.selectListByNumber(hisAnkleTemplate.getTemplateNumber());
            if (hisAnkleTemplate.getOrderNum() == 0){
                return MessageUtil.createMessage(false,"序号请从1开始新增");
            }else if (hisAnkleTemplate.getOrderNum()> hisAnkleTemplateList.size()+1){
                return MessageUtil.createMessage(false,"请按序号新增");

            }else if (hisAnkleTemplate.getOrderNum() > hisAnkleTemplateList.size()){
                //直接插入
                hisAnkleTemplateMapper.insert(hisAnkleTemplate);
                // log.info("--------------------护嘱新增翻译发送开始--------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisAnkleTemplateTranslate translate = new HisAnkleTemplateTranslate();
                BeanUtils.copyProperties(hisAnkleTemplate, translate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisAnkleTemplateTranslate(translate);
                amqpTemplat.convertAndSend("com.ahsj.addHisAnkleTemplate", JsonUtils.serialize(translateModels));
                // log.info(JsonUtils.serialize(translateModels));
                // log.info("--------------------护嘱新增翻译发送结束--------------------------");

                return MessageUtil.createMessage(true,"新增成功");

            }else {
                if (hisAnkleTemplate.getOrderNum() == 1){
                    for (int i=0;i<hisAnkleTemplateList.size();++i){
                        hisAnkleTemplateList.get(i).setOrderNum(i+2);
                    }
                    hisAnkleTemplateMapper.updateBatch(hisAnkleTemplateList);
                    for (HisAnkleTemplate ankleTemplate : hisAnkleTemplateList) {
                        //  log.info("--------------------护嘱修改翻译发送开始--------------------------");
                        BaseLoginUser loginUser = new BaseLoginUser();
                        TranslateModels translateModels = new TranslateModels();
                        HisAnkleTemplateTranslate translate = new HisAnkleTemplateTranslate();
                        BeanUtils.copyProperties(ankleTemplate, translate);
                        translateModels.setUserId(loginUser.getId());
                        translateModels.setHisAnkleTemplateTranslate(translate);
                        amqpTemplat.convertAndSend("com.ahsj.updateHisAnkleTemplate", JsonUtils.serialize(translateModels));
                        //  log.info(JsonUtils.serialize(translateModels));
                        //  log.info("--------------------护嘱修改翻译发送结束--------------------------");
                    }

                }else {
                    for (int i= hisAnkleTemplate.getOrderNum()-1;i<hisAnkleTemplateList.size();++i){
                        hisAnkleTemplateList.get(i).setOrderNum(i+2);
                    }
                    hisAnkleTemplateMapper.updateBatch(hisAnkleTemplateList);
                    for (HisAnkleTemplate ankleTemplate : hisAnkleTemplateList) {
                        // log.info("--------------------护嘱修改翻译发送开始--------------------------");
                        BaseLoginUser loginUser = new BaseLoginUser();
                        TranslateModels translateModels = new TranslateModels();
                        HisAnkleTemplateTranslate translate = new HisAnkleTemplateTranslate();
                        BeanUtils.copyProperties(ankleTemplate, translate);
                        translateModels.setUserId(loginUser.getId());
                        translateModels.setHisAnkleTemplateTranslate(translate);
                        amqpTemplat.convertAndSend("com.ahsj.updateHisAnkleTemplate", JsonUtils.serialize(translateModels));
                        //  log.info(JsonUtils.serialize(translateModels));
                        //  log.info("--------------------护嘱修改翻译发送结束--------------------------");
                    }
                }
                hisAnkleTemplateMapper.insert(hisAnkleTemplate);
                // log.info("--------------------护嘱新增翻译发送开始--------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisAnkleTemplateTranslate translate = new HisAnkleTemplateTranslate();
                BeanUtils.copyProperties(hisAnkleTemplate, translate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisAnkleTemplateTranslate(translate);
                amqpTemplat.convertAndSend("com.ahsj.addHisAnkleTemplate", JsonUtils.serialize(translateModels));
                //   log.info(JsonUtils.serialize(translateModels));
                //   log.info("--------------------护嘱新增翻译发送结束--------------------------");
                return MessageUtil.createMessage(true,"插入成功");

            }

        }else {
            //id不为空则更新
            //根据主键查找

            //更新
            HisAnkleTemplate check = hisAnkleTemplateMapper.selectByPrimaryKey(hisAnkleTemplate.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(check)){
                //不为空---更新
                hisAnkleTemplateMapper.updateByPrimaryKey(hisAnkleTemplate);
                //   log.info("--------------------护嘱修改翻译发送开始--------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisAnkleTemplateTranslate translate = new HisAnkleTemplateTranslate();
                BeanUtils.copyProperties(hisAnkleTemplate, translate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisAnkleTemplateTranslate(translate);
                amqpTemplat.convertAndSend("com.ahsj.updateHisAnkleTemplate", JsonUtils.serialize(translateModels));
                //    log.info(JsonUtils.serialize(translateModels));
                //   log.info("--------------------护嘱修改翻译发送结束--------------------------");
                return MessageUtil.createMessage(true,"更新成功!");
            }else {
                return MessageUtil.createMessage(false,"更新失败!");
            }
        }

    }

    @Override
    @Transactional(readOnly = false)
    public Message deletetemp(String[] tempNumber) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(tempNumber)){

            return MessageUtil.createMessage(false,"删除失败，参数错误");

        }else {
            for (String s : tempNumber) {
                hisAnkleTemplateMapper.deletetemp(s);
            }
            return MessageUtil.createMessage(true,"删除成功");
        }
    }

    @Override
    public Message deletetempdetails(Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)){

            return MessageUtil.createMessage(false,"删除失败，参数错误");

        }else {
            for (Long id : ids) {
                hisAnkleTemplateMapper.deletetempdetails(id);
            }
            return MessageUtil.createMessage(true,"删除成功");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public HisAnkleTemplate selectByPrimaryId(Long id) throws Exception {
        return hisAnkleTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public List<HisAnkleTemplate> selectTemplate() throws Exception {
        return hisAnkleTemplateMapper.selectTemplate();
    }

    @Override
    @Transactional(readOnly = false)
    public List<HisAnkleTemplate> selectAllTemplate(String templateNumber) throws Exception {
        return hisAnkleTemplateMapper.selectListByNumber(templateNumber);
    }

    /**
     *@功能说明 
     *@Params [hisAnkleTemplate]
     *@return java.util.List<com.ahsj.hiscore.entity.HisAnkleTemplate>
     *@Author XJP
     *@Date 2019/8/8
     *@Time 18:18
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisAnkleTemplate> queryTranslateInfoByDate(HisAnkleTemplate hisAnkleTemplate) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisAnkleTemplate)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<HisAnkleTemplate> translateList = hisAnkleTemplateMapper.queryTranslateInfoByDate(hisAnkleTemplate);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }
    /**
     * @param
     * @Description 查询所有的HisAnkleTemplate信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisAnkleTemplate>
     * @Date 2019/8/11
     * @Time 15:45
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisAnkleTemplate> queryAll() throws Exception {
        return hisAnkleTemplateMapper.queryHisAnkleTemplateAll();
    }
}



