package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisNewbornMapper;
import com.ahsj.hiscore.entity.HisNewborn;
import com.ahsj.hiscore.services.HisNewbornService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisNewbornServiceImpl
 * <p>
 * Date:     2019/9/10 13:14
 *
 * @author XJP
 * @create 2019/9/10
 * @since 1.0.0
 */

@Service
public class HisNewbornServiceImpl implements HisNewbornService {

    @Autowired
    HisNewbornMapper hisNewbornMapper;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisNewborn>
     * @功能说明 分页查询新生儿信息
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:32
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisNewborn> queryList(PageBean<HisNewborn> pageBean) throws Exception {
        List<HisNewborn> hisNewbornList = hisNewbornMapper.queryList(pageBean);
        for (HisNewborn hisNewborn : hisNewbornList) {
            Integer health = hisNewborn.getActivity() +
                    hisNewborn.getApprarance() +
                    hisNewborn.getGrimaceResponse() +
                    hisNewborn.getPulse() +
                    hisNewborn.getRespiration();
            String gethealth = gethealth(health);
            hisNewborn.setHealthStandards(gethealth);
        }
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisNewbornList));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明 生儿信息新增 and 修改
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:44
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insertSelective(HisNewborn hisNewborn) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisNewborn)) {
            return MessageUtil.createMessage(false, "新增失败 ！！！！！");
        } else {
            Message checkHisNewborn = getCheckHisNewborn(hisNewborn, Constants.HIS_NEW_BORN_ADD);
            if (checkHisNewborn != null) {
                return checkHisNewborn;
            }
            hisNewborn.setIsEnable(1);
            Integer health = hisNewborn.getActivity() +
                    hisNewborn.getApprarance() +
                    hisNewborn.getGrimaceResponse() +
                    hisNewborn.getPulse() +
                    hisNewborn.getRespiration();
            String gethealth = gethealth(health);
            hisNewborn.setHealthStandards(gethealth);

            if (EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getId())){
                hisNewbornMapper.insert(hisNewborn);
                return MessageUtil.createMessage(true, "新增成功 ！！！！！");
            }else {
                 checkHisNewborn = getCheckHisNewborn(hisNewborn, Constants.HIS_NEW_BORN_UPDARTE);
                if (checkHisNewborn != null) {
                    return checkHisNewborn;
                }
                hisNewbornMapper.updateByPrimaryKeySelective(hisNewborn);
                return MessageUtil.createMessage(true, "修改成功 ！！！！！");
            }

        }
    }

    /**
     * @return core.message.Message
     * @功能说明 新生儿信息修改
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 13:47
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(HisNewborn hisNewborn) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisNewborn)) {
            return MessageUtil.createMessage(false, "修改失败 ！！！！！");
        } else {
            Message checkHisNewborn = getCheckHisNewborn(hisNewborn, Constants.HIS_NEW_BORN_UPDARTE);
            if (checkHisNewborn != null) {
                return checkHisNewborn;
            }
            hisNewbornMapper.updateByPrimaryKeySelective(hisNewborn);
            return MessageUtil.createMessage(true, "修改成功 ！！！！！");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 通过病床编号修改婴儿的信息停用状态
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 15:17
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateIsEnable(HisNewborn hisNewborn) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getBedNumber())) {
            return MessageUtil.createMessage(false, "修改失败 ！ ！！！！");
        } else {
            List<HisNewborn> hisNewbornList = hisNewbornMapper.selectHisNewborn(hisNewborn);
            if (EmptyUtil.Companion.isNullOrEmpty(hisNewbornList)) {
                return MessageUtil.createMessage(false, "修改失败 ！ ！！！！");
            } else {
                for (HisNewborn newborn : hisNewbornList) {
                    newborn.setIsEnable(Constants.HIS_TWO);
                }
                hisNewbornMapper.updateIsEnable(hisNewbornList);
                return MessageUtil.createMessage(true, "修改成功 ！！！！！");
            }
        }
    }

    /**
     * @return com.ahsj.hiscore.entity.HisNewborn
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/11
     * @Time 10:13
     **/
    @Override
    @Transactional(readOnly = true)
    public HisNewborn selectByPrimaryKey(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            return new HisNewborn();
        }else {
            HisNewborn hisNewborn = hisNewbornMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(hisNewborn)){
                return new HisNewborn();
            }else {
                Integer health = hisNewborn.getActivity() +
                        hisNewborn.getApprarance() +
                        hisNewborn.getGrimaceResponse() +
                        hisNewborn.getPulse() +
                        hisNewborn.getRespiration();
                String gethealth = gethealth(health);
                hisNewborn.setHealthStandards(gethealth);
                return hisNewborn;
            }
        }
    }

    /**
     * @return java.lang.String
     * @功能说明
     * @Params [health]
     * @Author XJP
     * @Date 2019/9/11
     * @Time 9:45
     **/
    public String gethealth(Integer health) {
        if (health >= 0 && health <= 3) {
            return Constants.HIS_HEALTH_ONE;
        }
        if (health > 3 && health <= 7) {
            return Constants.HIS_HEALTH_TWO;
        }
        if (health > 7 && health <= 10) {
            return Constants.HIS_HEALTH_THREE;
        } else {
            return null;
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 表单验证
     * @Params [hisNewborn, param]
     * @Author XJP
     * @Date 2019/9/11
     * @Time 9:45
     **/
    public Message getCheckHisNewborn(HisNewborn hisNewborn, String param) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getBedNumber()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getActivity()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getPulse()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getRespiration()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getBornName()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getParentAddress()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getBornAddress()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getApprarance()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getNational()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getIdCard()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getpName()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getHeadCircumference()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getBornSex()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getWeight()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getHeight()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getGrimaceResponse()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getBornSex()) || EmptyUtil.Companion.isNullOrEmpty(hisNewborn.getWeight())
        ) {
            return MessageUtil.createMessage(false, param + "失败 ！填写新生儿信息存在空值 ， 请完善  ！！！！");
        } else if ((hisNewborn.getActivity() > 2 || hisNewborn.getActivity() < 0)) {
            return MessageUtil.createMessage(false, param + "失败 ！填写新生儿肌肉张力信息存在填写错误 ， 请完善  ！！！！");
        } else if ((hisNewborn.getApprarance() > 2 || hisNewborn.getApprarance() < 0)) {
            return MessageUtil.createMessage(false, param + "失败 ！填写新生儿肤色信息存在填写错误 ， 请完善  ！！！！");
        } else if ((hisNewborn.getGrimaceResponse() > 2 || hisNewborn.getGrimaceResponse() < 0)) {
            return MessageUtil.createMessage(false, param + "失败 ！填写新生儿刺激反射信息存在填写错误 ， 请完善  ！！！！");
        } else if ((hisNewborn.getPulse() > 2 || hisNewborn.getPulse() < 0)) {
            return MessageUtil.createMessage(false, param + "失败 ！填写新生儿脉搏信息存在填写错误 ， 请完善  ！！！！");
        } else if ((hisNewborn.getRespiration() > 2 || hisNewborn.getRespiration() < 0)) {
            return MessageUtil.createMessage(false, param + "失败 ！填写新生儿呼吸信息存在填写错误 ， 请完善  ！！！！");
        }
        return null;
    }
}
