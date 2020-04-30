package com.ahsj.hiscore.services.impl;


import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisConsumablesDetailsMapper;
import com.ahsj.hiscore.dao.HisEnterConsumablesDetailsMapper;
import com.ahsj.hiscore.entity.HisConsumables;
import com.ahsj.hiscore.entity.HisConsumablesDetails;
import com.ahsj.hiscore.entity.HisEnterConsumablesDetails;
import com.ahsj.hiscore.entity.HisHospitalConsumablesDetails;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.feign.IUserService;
import com.ahsj.hiscore.services.HisConsumablesDetailsService;
import com.ahsj.hiscore.services.HisConsumablesService;
import com.ahsj.hiscore.services.HisHospitalConsumablesDetailsService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HisConsumablesDetailsServiceImpl implements HisConsumablesDetailsService {

    private Logger log = LoggerFactory.getLogger(HisConsumablesDetailsServiceImpl.class.getSimpleName());


    @Autowired
    HisConsumablesDetailsMapper hisConsumablesDetailsMapper;

    @Autowired
    HisConsumablesService hisConsumablesService;

    @Autowired
    HisHospitalConsumablesDetailsService hisHospitalConsumablesDetailsService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    IUserService iUserService;

    @Autowired
    HisEnterConsumablesDetailsMapper hisEnterConsumablesDetailsMapper;

    @Autowired
    AmqpTemplate amqpTemplat;


    /**
     * @return
     * @Description CRUD
     * @Params
     * @Author jin
     * @Date 2019/6/28
     * @Time 21:04
     */

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumablesDetails> list(PageBean<HisConsumablesDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesDetailsMapper.list(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumablesDetails> onelist(PageBean<HisConsumablesDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesDetailsMapper.onelist(pageBean)));
        return pageBean;
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 根据ids 查询药库集合
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesDetails> getHisConsumablesDetailsByIds(Long[] ids) {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            return null;
        } else {
            List<HisConsumablesDetails> list = new ArrayList<>();
            for (Long id : ids) {
                HisConsumablesDetails hisConsumablesDetails = hisConsumablesDetailsMapper.selectByPrimaryKey(id);
                list.add(hisConsumablesDetails);
            }
            return list;
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 根据ids  nums  申请并保存耗材集合
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message getHisConsumablesDetailsByIdsAndNum(Long[] ids, Integer[] nums, Long id, String medicalRecordId) throws Exception {
     if (EmptyUtil.Companion.isNullOrEmpty(ids) || EmptyUtil.Companion.isNullOrEmpty(nums) || EmptyUtil.Companion.isNullOrEmpty(medicalRecordId)) {
            log.info("申请失败 , 申请数据不能为空！");
            return MessageUtil.createMessage(false, " 申请失败 , 申请数据不能为空！");
        } else {
            for (Integer num : nums) {
                if (EmptyUtil.Companion.isNullOrEmpty(num)) {
                    log.info("保存失败 , 耗材申请数量数据不能为空！");
                    return MessageUtil.createMessage(false, " 保存失败 , 耗材申请数量数据不能为空！");
                }
            }
            List<HisHospitalConsumablesDetails> consumablesDetailsArrayList = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            SimpleDateFormat formatone = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatone.parse(formatone.format(new Date()));
            String consumableNumber = "CA" + format.format(new Date());
            for (int i = 0; i < ids.length; i++) {

                log.info("耗材库id 值  : {}", ids[i]);
                // HisEnterConsumablesDetails hisEnterConsumablesDetails = hisEnterConsumablesDetailsMapper.selectByPrimaryKey(ids[i]);
                HisEnterConsumablesDetails hisEnterConsumablesDetails = hisEnterConsumablesDetailsMapper.selectByPrimaryKey(ids[i]);
                log.info("查询库存 : {}", hisEnterConsumablesDetails.toString());

                log.info("耗材库存量为 : {}", hisEnterConsumablesDetails.getSurplus());
                if (hisEnterConsumablesDetails.getSurplus() < nums[i]) {
                    return MessageUtil.createMessage(false, " 申请失败 , 耗材名 : " + hisEnterConsumablesDetails.getName() + "   申请数量已超过库存数 , 请重新申请！");
                }
                HisConsumables hisConsumables = hisConsumablesService.selectByConsumablesCode(hisEnterConsumablesDetails.getConsumablesCode());
                if (hisConsumables.getIsEnable() == 2) {
                    return MessageUtil.createMessage(false, " 申请失败 , 耗材名 : " + hisConsumables.getName() + "   已停用, 无法申请 , 请重新申请！");
                }
                HisHospitalConsumablesDetails hisHospitalConsumablesDetails = new HisHospitalConsumablesDetails();
                hisHospitalConsumablesDetails.setComsumablesNum(nums[i]);
                hisHospitalConsumablesDetails.setIsOutbound(2);
                hisHospitalConsumablesDetails.setHisConsumablesDetailsId(ids[i]);
                hisHospitalConsumablesDetails.setHisHospitalManagerId(id);
                hisHospitalConsumablesDetails.setApplicationTime(date);
                hisHospitalConsumablesDetails.setConsumableNumber(consumableNumber);
                hisHospitalConsumablesDetails.setMedicalRecordNumber(medicalRecordId);
                hisHospitalConsumablesDetails.setConsumablesCode(hisConsumables.getConsumablesCode());
                hisHospitalConsumablesDetails.setIsDelete(Constants.HIS_DELETE_FALSE);
                consumablesDetailsArrayList.add(hisHospitalConsumablesDetails);

            }
            hisHospitalConsumablesDetailsService.insertHisHospitalConsumablesDetails(consumablesDetailsArrayList);
            log.info("提交耗材申请成功 ！！！！！");
            return MessageUtil.createMessage(true, " 提交耗材申请成功 ！！！！");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 审核耗材出库
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumablesDetails> getHisConsumablesDetailsInfo(PageBean<HisConsumablesDetails> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesDetailsMapper.getHisConsumablesDetailsInfo(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [hisConsumablesDetails]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:16
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesDetails> queryTranslateInfoByDate(HisConsumablesDetails hisConsumablesDetails) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumablesDetails)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<HisConsumablesDetails> translateList = hisConsumablesDetailsMapper.queryTranslateInfoByDate(hisConsumablesDetails);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:16
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumablesDetails> queryAll() throws Exception {
        List<HisConsumablesDetails> consumablesDetailsList = hisConsumablesDetailsMapper.queryAll();
        if (EmptyUtil.Companion.isNullOrEmpty(consumablesDetailsList)) {
            log.info("查询失败");
            return new ArrayList<>();
        } else {
            return consumablesDetailsList;
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/14
     * @Time 9:45
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumablesDetails> lists(PageBean<HisConsumablesDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesDetailsMapper.lists(pageBean)));
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:16
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumablesDetails> getHisConsumablesDetailsInfos(PageBean<HisConsumablesDetails> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesDetailsMapper.getHisConsumablesDetailsGroupBy(pageBean)));
        return pageBean;
    }


}
