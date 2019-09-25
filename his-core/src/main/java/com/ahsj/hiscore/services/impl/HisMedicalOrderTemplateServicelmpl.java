package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicalOrderTemplateDetailMapper;
import com.ahsj.hiscore.dao.HisMedicalOrderTemplateMapper;
import com.ahsj.hiscore.entity.HisMedicalOrderTemplate;
import com.ahsj.hiscore.entity.HisMedicalTemplate;
import com.ahsj.hiscore.services.HisMedicalOrderTemplateService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HisMedicalOrderTemplateServicelmpl implements HisMedicalOrderTemplateService {
    @Autowired
    HisMedicalOrderTemplateMapper hisMedicalOrderTemplateMapper;

    @Autowired
    HisMedicalOrderTemplateDetailMapper hisMedicalOrderTemplateDetailMapper;
    /**
     *@Description 医嘱模板分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrderTemplate>
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 17:00
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalOrderTemplate> list(PageBean<HisMedicalOrderTemplate> pageBean) throws Exception {

       pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalOrderTemplateMapper.list(pageBean)));
       return pageBean;
    }

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderTemplate
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 17:15
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalOrderTemplate selectById(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderTemplateMapper.selectByPrimaryKey(id));
    }

    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrderTemplate]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 17:28
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedicalOrderTemplate hisMedicalOrderTemplate) throws Exception {
        HisMedicalOrderTemplate checkId = hisMedicalOrderTemplateMapper.selectByPrimaryKey(hisMedicalOrderTemplate.getId());
        //核对医嘱模板名
        HisMedicalOrderTemplate checkName = hisMedicalOrderTemplateMapper.selectByTemplateName(hisMedicalOrderTemplate.getTemplateName());
        if(!EmptyUtil.Companion.isNullOrEmpty(checkName))
            return MessageUtil.createMessage(false,"此医嘱名已有，请重新输入");
        //如果checkId为空则为新增
        if(EmptyUtil.Companion.isNullOrEmpty(checkId)){

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String createdate = sdf.format(date);
            int count = hisMedicalOrderTemplateMapper.selectNumbCount(createdate) + 1;
            //编号
            String templateNumber = createdate + String.format("%05d", count);
            templateNumber = "MOT" + templateNumber;//MOT：medicalOrderTemplate:医嘱模板
            hisMedicalOrderTemplate.setTemplateNumber(templateNumber);
            hisMedicalOrderTemplateMapper.insert(hisMedicalOrderTemplate);
            return MessageUtil.createMessage(true,"新增成功");
        }else {
            hisMedicalOrderTemplateMapper.updateByPrimaryKeySelective(hisMedicalOrderTemplate);
            return MessageUtil.createMessage(true,"更新成功");
        }
    }

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 18:12
    **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (int i = 0; i <ids.length ; i++) {
            HisMedicalOrderTemplate hisMedicalOrderTemplate = hisMedicalOrderTemplateMapper.selectByPrimaryKey(ids[i]);
            hisMedicalOrderTemplateDetailMapper.deleteByTemplateNumber(hisMedicalOrderTemplate.getTemplateNumber());
            hisMedicalOrderTemplateMapper.deleteByPrimaryKey(ids[i]);
        }
        return MessageUtil.createMessage(true,"删除成功");
    }

    /**
     *@Description 获取医嘱模板名到select中
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderTemplate>
     *@Author zhushixiang
     *@Date 2019-07-29
     *@Time 9:02
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicalOrderTemplate> selectTemplate(Long createUserId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderTemplateMapper.selectTemplate(createUserId));
    }

    /**
     *@Description
     *@Params [templateNumber]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderTemplate
     *@Author zhushixiang
     *@Date 2019-07-29
     *@Time 9:55
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalOrderTemplate selectByTemplateNumber(String templateNumber) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderTemplateMapper.selectByTemplateNumber(templateNumber));
    }
}
