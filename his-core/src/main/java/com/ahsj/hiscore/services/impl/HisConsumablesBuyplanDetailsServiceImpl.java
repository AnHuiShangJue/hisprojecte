package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisConsumablesBuyplanDetailsMapper;
import com.ahsj.hiscore.dao.HisConsumablesBuyplanMapper;
import com.ahsj.hiscore.dao.HisConsumablesMapper;
import com.ahsj.hiscore.entity.HisConsumables;
import com.ahsj.hiscore.entity.HisConsumablesBuyplan;
import com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails;
import com.ahsj.hiscore.entity.TranslateModel.HisConsumablesBuyplanDetailsTranslate;
import com.ahsj.hiscore.entity.TranslateModel.HisConsumablesBuyplanTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateDelete;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisConsumablesBuyplanDetailsService;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HisConsumablesBuyplanDetailsServiceImpl implements HisConsumablesBuyplanDetailsService {

    private Logger log = LoggerFactory.getLogger(HisConsumablesBuyplanDetailsServiceImpl.class.getSimpleName());


    @Autowired
    HisConsumablesMapper hisConsumablesMapper;
    @Autowired
    HisConsumablesBuyplanDetailsMapper hisConsumablesBuyplanDetailsMapper;
    @Autowired
    HisConsumablesBuyplanMapper hisConsumablesBuyplanMapper;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;

    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/7/4
     * @Time 12:58
     */
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(Long[] ids, Integer[] numbers, Long buyplanId, String personInCharge, String expectedTime, Double[] prices) throws Exception {
        double allBudget = 0;
        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetailsList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            HisConsumables hisConsumables = hisConsumablesMapper.selectByPrimaryKey(ids[i]);
            HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails = new HisConsumablesBuyplanDetails();

            hisConsumablesBuyplanDetails.setConsumablesId(hisConsumables.getId());
            hisConsumablesBuyplanDetails.setEnterCountPlan(numbers[i]);
            hisConsumablesBuyplanDetails.setName(hisConsumables.getName());
            hisConsumablesBuyplanDetails.setPrice(prices[i]);
            hisConsumablesBuyplanDetails.setBuyplanId(buyplanId);
            allBudget += prices[i] * numbers[i];
            hisConsumablesBuyplanDetails.setSpec(hisConsumables.getSpec());

            hisConsumablesBuyplanDetailsList.add(hisConsumablesBuyplanDetails);
        }

        //list插入
        hisConsumablesBuyplanDetailsMapper.insertBatch(hisConsumablesBuyplanDetailsList);

        //肖俊鹏 添加
        for (HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails : hisConsumablesBuyplanDetailsList) {
            log.info("--------------------耗材采购明细新增翻译发送开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisConsumablesBuyplanDetailsTranslate translate = new HisConsumablesBuyplanDetailsTranslate();
            BeanUtils.copyProperties(hisConsumablesBuyplanDetails, translate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisConsumablesBuyplanDetailsTranslate(translate);
            amqpTemplat.convertAndSend("com.ahsj.addHisConsumablesBuyplanDetails", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------耗材采购明细新增翻译发送结束--------------------------");
        }

        HisConsumablesBuyplan hisConsumablesBuyplan = new HisConsumablesBuyplan();
        hisConsumablesBuyplan.setBuyplanId(buyplanId);
        hisConsumablesBuyplan.setPersonInCharge(personInCharge);
        //将expectedTime格式化yyyy/MM/dd日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date expectedDate = null;
        expectedDate = format.parse(expectedTime);
        hisConsumablesBuyplan.setExpectedTime(expectedDate);
        hisConsumablesBuyplan.setBudget(allBudget);
        hisConsumablesBuyplan.setCompletion(2);
        hisConsumablesBuyplanMapper.insert(hisConsumablesBuyplan);

        log.info("--------------------耗材采购新增翻译发送开始--------------------------");
        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        HisConsumablesBuyplanTranslate translate = new HisConsumablesBuyplanTranslate();
        BeanUtils.copyProperties(hisConsumablesBuyplan, translate);
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisConsumablesBuyplanTranslate(translate);
        amqpTemplat.convertAndSend("com.ahsj.addHisConsumablesBuyplan", JsonUtils.serialize(translateModels));
        log.info(JsonUtils.serialize(translateModels));
        log.info("--------------------耗材采购新增翻译发送结束--------------------------");
        return MessageUtil.createMessage(true, "耗材采购计划生成成功");
    }


    /**
     * @return
     * @Description 根据药品计划查找
     * @Params
     * @Author jin
     * @Date 2019/7/5
     * @Time 9:41
     */
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesBuyplanDetails> selectByBuyplanId(Long buyplanId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisConsumablesBuyplanDetailsMapper.selectByBuyplanId(buyplanId));
    }


    /**
     * @return
     * @Description 删除
     * @Params
     * @Author jin
     * @Date 2019/7/5
     * @Time 10:42
     */

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumablesBuyplanDetails> details(PageBean<HisConsumablesBuyplanDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesBuyplanDetailsMapper.details(pageBean)));
        return pageBean;
    }


    @Override
    @Transactional(readOnly = false)
    public Message saveDetails(Long[] ids, Integer[] numbers, Double[] prices, Long buyplanId) throws Exception {
        hisConsumablesBuyplanDetailsMapper.deleteByBuyplanId(buyplanId);
        TranslateDelete translateDelete = new TranslateDelete();
        translateDelete.setId(buyplanId);
        translateDelete.setModel(Constants.TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS);
        amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
        HisConsumablesBuyplan hisConsumablesBuyplan = hisConsumablesBuyplanMapper.selectByBuyplanId(buyplanId);
        //allBudget：预算
        double allBudget = 0;

        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetailsList = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            HisConsumables hisConsumables = hisConsumablesMapper.selectByPrimaryKey(ids[i]);
            HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails = new HisConsumablesBuyplanDetails();
            hisConsumablesBuyplanDetails.setBuyplanId(buyplanId);
            hisConsumablesBuyplanDetails.setConsumablesId(ids[i]);
            hisConsumablesBuyplanDetails.setEnterCountPlan(numbers[i]);
            hisConsumablesBuyplanDetails.setPrice(prices[i]);
            hisConsumablesBuyplanDetails.setName(hisConsumables.getName());
            allBudget += prices[i] * numbers[i];
            hisConsumablesBuyplanDetails.setSpec(hisConsumables.getSpec());
            hisConsumablesBuyplanDetailsList.add(hisConsumablesBuyplanDetails);

        }

        hisConsumablesBuyplanDetailsMapper.insertBatch(hisConsumablesBuyplanDetailsList);
        //肖俊鹏 添加
        for (HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails : hisConsumablesBuyplanDetailsList) {
            log.info("--------------------耗材采购明细新增翻译发送开始--------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisConsumablesBuyplanDetailsTranslate translate = new HisConsumablesBuyplanDetailsTranslate();
            BeanUtils.copyProperties(hisConsumablesBuyplanDetails, translate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisConsumablesBuyplanDetailsTranslate(translate);
            amqpTemplat.convertAndSend("com.ahsj.addHisConsumablesBuyplanDetails", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------耗材采购明细新增翻译发送结束--------------------------");
        }
        hisConsumablesBuyplan.setBudget(allBudget);
        hisConsumablesBuyplanMapper.updateByPrimaryKeySelective(hisConsumablesBuyplan);
        log.info("--------------------耗材采购修改翻译发送开始--------------------------");
        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        HisConsumablesBuyplanTranslate translate = new HisConsumablesBuyplanTranslate();
        BeanUtils.copyProperties(hisConsumablesBuyplan, translate);
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisConsumablesBuyplanTranslate(translate);
        amqpTemplat.convertAndSend("com.ahsj.updateHisConsumablesBuyplan", JsonUtils.serialize(translateModels));
        log.info(JsonUtils.serialize(translateModels));
        log.info("--------------------耗材采购修改翻译发送结束--------------------------");
        return MessageUtil.createMessage(true, "药品采购计划更新成功");
    }

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)){
            return MessageUtil.createMessage(false,"耗材信息错误");
        }else {
            for (Long id : ids) {
                hisConsumablesBuyplanDetailsMapper.deleteByPrimaryKey(id);
            }
            return MessageUtil.createMessage(true,"删除成功");
        }
    }


    /**
     * @return
     * @Description 按计划编号查找
     * @Params
     * @Author jin
     * @Date 2019/7/5
     * @Time 20:23
     */
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesBuyplanDetails> selectByBuyplanforList(Long buyplanId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisConsumablesBuyplanDetailsMapper.selectByBuyplanforList(buyplanId));
    }

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long buyplanId) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(buyplanId)) {
            return MessageUtil.createMessage(false, "参数异常");
        } else {
            hisConsumablesBuyplanDetailsMapper.deleteByBuyplanId(buyplanId);
            log.info("--------------------耗材采购明细删除翻译发送开始--------------------------");
            TranslateDelete translateDelete = new TranslateDelete();
            translateDelete.setId(buyplanId);
            translateDelete.setModel(Constants.TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS);
            amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
            log.info(JsonUtils.serialize(translateDelete));
            log.info("--------------------耗材采购明细删除翻译发送结束--------------------------");
            return MessageUtil.createMessage(true, "删除成功");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     * @功能说明
     * @Params [hisConsumablesBuyplanDetails]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 11:06
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesBuyplanDetails> queryTranslateInfoByDate(HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumablesBuyplanDetails)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
           log.info("-----------参数时间----------------"+hisConsumablesBuyplanDetails.getUpdateDate());
            List<HisConsumablesBuyplanDetails> translateList = hisConsumablesBuyplanDetailsMapper.queryTranslateInfoByDate(hisConsumablesBuyplanDetails);

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
    public List<HisConsumablesBuyplanDetails> queryAll() throws Exception {
        List<HisConsumablesBuyplanDetails>  hisConsumablesBuyplanDetails =  hisConsumablesBuyplanDetailsMapper.queryAll();
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumablesBuyplanDetails)){
            log.info("查询失败");
            return new ArrayList<>();
        }else {
            return hisConsumablesBuyplanDetails;
        }
    }
}



