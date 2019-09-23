package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisConsumablesBuyplanDetailsMapper;
import com.ahsj.hiscore.dao.HisConsumablesBuyplanMapper;
import com.ahsj.hiscore.entity.HisConsumablesBuyplan;
import com.ahsj.hiscore.entity.TranslateModel.HisConsumablesBuyplanTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateDelete;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisConsumablesBuyplanService;
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
public class HisConsumablesBuyplanServiceImpl implements HisConsumablesBuyplanService {

    private Logger log = LoggerFactory.getLogger(HisConsumablesBuyplanServiceImpl.class.getSimpleName());


    @Autowired
    HisConsumablesBuyplanMapper hisConsumablesBuyplanMapper;

    @Autowired
    HisConsumablesBuyplanDetailsMapper hisConsumablesBuyplanDetailsMapper;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;


    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/7/3
     * @Time 12:07
     */
    @Override
    @Transactional(readOnly = false)
    public Message Update(HisConsumablesBuyplan hisMedicinePurchasingPlanRecord) throws Exception {
        HisConsumablesBuyplan checkId = hisConsumablesBuyplanMapper.selectByPrimaryKey(hisMedicinePurchasingPlanRecord.getId());
        if (EmptyUtil.Companion.isNullOrEmpty(checkId)) {
            return MessageUtil.createMessage(false, "参数错误");
        } else {
            checkId.setPersonInCharge(hisMedicinePurchasingPlanRecord.getPersonInCharge());
            checkId.setExpectedTime(hisMedicinePurchasingPlanRecord.getExpectedTime());
            hisConsumablesBuyplanMapper.updateByPrimaryKeySelective(checkId);
            log.info("--------------------耗材采购修改翻译发送开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisConsumablesBuyplanTranslate translate = new HisConsumablesBuyplanTranslate();
            BeanUtils.copyProperties(checkId, translate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisConsumablesBuyplanTranslate(translate);
            amqpTemplat.convertAndSend("com.ahsj.updateHisConsumablesBuyplan", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------耗材采购修改翻译发送开始--------------------------");
            return MessageUtil.createMessage(true, "更新成功");
        }
    }


    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumablesBuyplan> list(PageBean<HisConsumablesBuyplan> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesBuyplanMapper.list(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisConsumablesBuyplanMapper.deleteByPrimaryKey(id);
            log.info("--------------------耗材采购删除翻译发送开始--------------------------");
            TranslateDelete translateDelete = new TranslateDelete();
            translateDelete.setId(id);
            translateDelete.setModel(Constants.TRANSLATE_HIS_CONSUMABLESBUYPLAN);
            amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
            log.info(JsonUtils.serialize(translateDelete));
            log.info("--------------------耗材采购删除翻译发送结束--------------------------");
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    @Override
    @Transactional(readOnly = true)
    public HisConsumablesBuyplan selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisConsumablesBuyplanMapper.selectByPrimaryKey(id));
    }


    /**
     * @return
     * @Description 根据计划编号查找
     * @Params
     * @Author jin
     * @Date 2019/7/5
     * @Time 9:53
     */
    @Override
    @Transactional(readOnly = true)
    public HisConsumablesBuyplan selectByBuyplanId(Long buyplanId) {
        return CodeHelper.getInstance().setCodeValue(hisConsumablesBuyplanMapper.selectByBuyplanId(buyplanId));
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplan>
     * @功能说明
     * @Params [hisConsumablesBuyplan]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 11:08
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesBuyplan> queryTranslateInfoByDate(HisConsumablesBuyplan hisConsumablesBuyplan) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumablesBuyplan)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<HisConsumablesBuyplan> translateList = hisConsumablesBuyplanMapper.queryTranslateInfoByDate(hisConsumablesBuyplan);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesBuyplan> queryAll() throws Exception {
        List<HisConsumablesBuyplan>  hisConsumablesBuyplans =  hisConsumablesBuyplanMapper.queryAll();
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumablesBuyplans)){
            log.info("查询失败");
            return new ArrayList<>();
        }else {
            return hisConsumablesBuyplans;
        }
    }

}
