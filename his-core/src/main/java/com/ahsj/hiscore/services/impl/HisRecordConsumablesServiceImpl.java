package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRecordConsumablesMapper;
import com.ahsj.hiscore.entity.HisRecordConsumables;
import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.entity.HisRefundConsumables;
import com.ahsj.hiscore.services.HisRecordConsumablesService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName : HisRecordConsumablesServiceImpl
 * @Description :
 * @Author : XJP
 * @Date: 2020-04-27 14:15
 */

@Service
public class HisRecordConsumablesServiceImpl implements HisRecordConsumablesService {

    @Autowired
    HisRecordConsumablesMapper hisRecordConsumablesMapper;

    /**
     * @return core.message.Message
     * @Description
     * @MethodName insert
     * @Params [hisRecordConsumables]
     * @Author XJP
     * @Date 2020/4/27
     * @Time 14:20
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insert(HisRecordConsumables hisRecordConsumables) {
        hisRecordConsumablesMapper.insert(hisRecordConsumables);
        return MessageUtil.createMessage(true, "成功");
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName queryAddList
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 11:30
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordConsumables> queryAddList(PageBean<HisRecordConsumables> pageBean) {
        List<HisRecordConsumables> hisRecordProjects = hisRecordConsumablesMapper.queryList(pageBean);
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordProjects));
        return pageBean;
    }

    /**
     *@Description
     *@MethodName selectByPrimaryKey
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisRefundConsumables
     *@Author XJP
     *@Date 2020/4/29
     *@Time 11:35
    **/
    @Override
    @Transactional(readOnly = true)
    public HisRecordConsumables selectByPrimaryKey(Long id) {
        return hisRecordConsumablesMapper.selectByPrimaryKey(id);
    }

    /**
     * @return com.ahsj.hiscore.entity.HisRecordConsumables
     * @Description
     * @MethodName queryByConsumablesCodeAndMedicalRecordNumber
     * @Params [hisRecordConsumables]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 11:31
     **/
    @Override
    @Transactional(readOnly = true)
    public HisRecordConsumables queryByConsumablesCodeAndMedicalRecordNumber(HisRecordConsumables hisRecordConsumables) {
        return hisRecordConsumablesMapper.queryByConsumablesCodeAndMedicalRecordNumber(hisRecordConsumables);
    }

    /**
     *@Description
     *@MethodName consumablesInfo
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordConsumables>
     *@Author XJP
     *@Date 2020/4/29
     *@Time 14:51
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordConsumables> consumablesInfo(PageBean<HisRecordConsumables> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordConsumablesMapper.consumablesInfo(pageBean)));
        return pageBean;
    }

    /**
     *@Description
     *@MethodName pageBeanList
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordConsumables>
     *@Author XJP
     *@Date 2020/4/29
     *@Time 17:09
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRecordConsumables> pageBeanList(PageBean<HisRecordConsumables> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRecordConsumablesMapper.pageBeanList(pageBean)));
        return pageBean;
    }
}
