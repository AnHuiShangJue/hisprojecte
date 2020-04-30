package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.DateNumber;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRefundConsumablesMapper;
import com.ahsj.hiscore.entity.HisRecordConsumables;
import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.entity.HisRefundConsumables;
import com.ahsj.hiscore.entity.HisRefundProject;
import com.ahsj.hiscore.services.HisRecordConsumablesService;
import com.ahsj.hiscore.services.HisRefundConsumablesService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : HisRefundConsumablesServiceImpl
 * @Description :
 * @Author : XJP
 * @Date: 2020-04-28 10:31
 */

@Service
public class HisRefundConsumablesServiceImpl implements HisRefundConsumablesService {

    private Logger log = LoggerFactory.getLogger(HisRefundProjectServiceImpl.class.getSimpleName());

    @Autowired
    HisRefundConsumablesMapper hisRefundConsumablesMapper;

    @Autowired
    HisRecordConsumablesService hisRecordConsumablesService;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundConsumables>
     * @Description
     * @MethodName list
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/28
     * @Time 10:34
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRefundConsumables> list(PageBean<HisRefundConsumables> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRefundConsumablesMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @Description
     * @MethodName saveForAppEdit
     * @Params [hisRefundConsumables, nums, ids, userName]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 11:14
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveForAppEdit(HisRefundConsumables hisRefundConsumables, Integer[] nums, Long[] ids, String userName) throws Exception {

        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundConsumables.getPatientName()) || EmptyUtil.Companion.isNullOrEmpty(nums)
                || EmptyUtil.Companion.isNullOrEmpty(ids)
                || EmptyUtil.Companion.isNullOrEmpty(hisRefundConsumables.getRemarks())) {
            log.info("提交申请失败! 申请人不能为空 或 申请退耗材数量不能为空 或  原因不能为空");
            return MessageUtil.createMessage(false, "提交申请失败! 申请人不能为空 或 申请退耗材数量不能为空 或  原因不能为空");
        } else if (EmptyUtil.Companion.isNullOrEmpty(hisRefundConsumables.getRecordNumber())) {
            log.info("提交申请失败! 就诊记录编号 或交易流水号 住院编号 不能为空");
            return MessageUtil.createMessage(false, "提交申请失败!  就诊记录编号 或交易流水号 住院编号 不能为空");
        } else if (!StringUtils.equals(userName, hisRefundConsumables.getPatientName())) {
            log.info("提交申请失败! 申请人和当前用户名不符合");
            return MessageUtil.createMessage(false, "提交申请失败! 申请人和当前用户名不符合");
        } else {
            //判断申请耗材数据是否超过购买数量
            for (int i = 0; i < ids.length; i++) {
                if (nums[i] == null || nums[i] <= 0) {
                    return MessageUtil.createMessage(false, "提交申请失败! 申请退耗材数量填写有误");
                } else {
                    HisRecordConsumables hisRecordConsumables = hisRecordConsumablesService.selectByPrimaryKey(ids[i]);

                    HisRecordConsumables recordConsumables = hisRecordConsumablesService.queryByConsumablesCodeAndMedicalRecordNumber(hisRecordConsumables);
                    if (nums[i] > recordConsumables.getComsumablesNum()) {
                        log.info("提交申请失败! 申请退耗材数量不能大于已预约项目的数量");
                        return MessageUtil.createMessage(false, "提交申请失败! " + recordConsumables.getName() + "   申请退耗材数量不能大于已预约项目的数量");
                    }
                }
            }

            SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fat.parse(fat.format(new Date()));
            List<HisRefundConsumables> consumablesList = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                HisRecordConsumables hisRecordConsumables = hisRecordConsumablesService.selectByPrimaryKey(ids[i]);
                HisRefundConsumables refundConsumables = new HisRefundConsumables();
                refundConsumables.setRecordNumber(hisRecordConsumables.getMedicalRecordNumber());
                refundConsumables.setVoucher(DateNumber.getNumbenr(Constants.HIS_HCSQV));
                refundConsumables.setPatientName(userName);
                refundConsumables.setRefundNum(nums[i]);
                refundConsumables.setRecordConsumablesCode(hisRecordConsumables.getRecordConsumablesCode());
                refundConsumables.setApplicationTime(date);
                refundConsumables.setRemarks(hisRefundConsumables.getRemarks());
                refundConsumables.setIsDelete(Constants.HIS_DELETE_FALSE);
                refundConsumables.setIsBack(Constants.HC_BACK_FALSE);
                refundConsumables.setIsReview(3);
                consumablesList.add(refundConsumables);
            }
            hisRefundConsumablesMapper.insertList(consumablesList);
            return MessageUtil.createMessage(true, "保存成功。");
        }
    }

    /**
     *@Description
     *@MethodName queryHisHisRefundConsumablesVoucher
     *@Params [hisRefundConsumables]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:20
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRefundConsumables> queryHisHisRefundConsumablesVoucher(HisRefundConsumables hisRefundConsumables) throws Exception {
        return hisRefundConsumablesMapper.queryHisHisRefundConsumablesVoucherList(hisRefundConsumables);
    }

    /**
     *@Description
     *@MethodName reviewSuccess
     *@Params [hisRefundConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:30
    **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewSuccess(HisRefundConsumables hisRefundConsumables) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundConsumables)) {
            return MessageUtil.createMessage(false, "审核失败!");
        } else {
            List<HisRefundConsumables> hisRefundConsumablesList = hisRefundConsumablesMapper.queryHisHisRefundConsumablesVoucher(hisRefundConsumables);

            //伪删
            hisRefundConsumablesMapper.updateByIsDelete(hisRefundConsumablesList);
            //新增
            for (HisRefundConsumables refundConsumables : hisRefundConsumablesList) {
                refundConsumables.setIsReview(1);
                refundConsumables.setId(null);
            }
            hisRefundConsumablesMapper.insertList(hisRefundConsumablesList);
            return MessageUtil.createMessage(true, "审核成功! 审核通过");
        }
    }

    /**
     *@Description
     *@MethodName reviewFail
     *@Params [hisRefundConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:30
    **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewFail(HisRefundConsumables hisRefundConsumables) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRefundConsumables)) {
            return MessageUtil.createMessage(false, "审核失败!");
        } else {
            List<HisRefundConsumables> hisRefundConsumablesList = hisRefundConsumablesMapper.queryHisHisRefundConsumablesVoucher(hisRefundConsumables);
            //伪删
            hisRefundConsumablesMapper.updateByIsDelete(hisRefundConsumablesList);
            //新增
            for (HisRefundConsumables refundConsumables : hisRefundConsumablesList) {
                refundConsumables.setIsReview(2);
                refundConsumables.setId(null);
            }
            hisRefundConsumablesMapper.insertList(hisRefundConsumablesList);
            return MessageUtil.createMessage(true, "审核失败! 审核不通过");
        }
    }

    /**
     *@Description 
     *@MethodName saveHisRefundConsumablesInfo
     *@Params [hisRefundConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/30
     *@Time 10:26
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveHisRefundConsumablesInfo(HisRefundConsumables hisRefundConsumables) {

        return null;
    }

    /**
     *@Description
     *@MethodName queryByNotBack
     *@Params [recordNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
     *@Author XJP
     *@Date 2020/4/30
     *@Time 12:39
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisRefundConsumables> queryByNotBack(String recordNumber) throws Exception {
        return hisRefundConsumablesMapper.queryByNotBack(recordNumber);
    }

    /**
     *@Description
     *@MethodName updateByIsDelete
     *@Params [hisRefundConsumablesList]
     *@return int
     *@Author XJP
     *@Date 2020/4/30
     *@Time 12:44
    **/
    @Override
    @Transactional(readOnly = false)
    public int updateByIsDelete(List<HisRefundConsumables> hisRefundConsumablesList) throws Exception {
        return hisRefundConsumablesMapper.updateByIsDelete(hisRefundConsumablesList);
    }

    /**
     *@Description
     *@MethodName insertList
     *@Params [consumablesList]
     *@return int
     *@Author XJP
     *@Date 2020/4/30
     *@Time 12:48
    **/
    @Override
    @Transactional(readOnly = false)
    public int insertList(List<HisRefundConsumables> consumablesList) throws Exception {
        return hisRefundConsumablesMapper.insertList(consumablesList);
    }
}
