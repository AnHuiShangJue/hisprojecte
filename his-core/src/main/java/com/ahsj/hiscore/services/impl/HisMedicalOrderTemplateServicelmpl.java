package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicalOrderDetailMapper;
import com.ahsj.hiscore.dao.HisMedicalOrderTemplateDetailMapper;
import com.ahsj.hiscore.dao.HisMedicalOrderTemplateMapper;
import com.ahsj.hiscore.entity.HisMedicalOrder;
import com.ahsj.hiscore.entity.HisMedicalOrderDetail;
import com.ahsj.hiscore.entity.HisMedicalOrderTemplate;
import com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail;
import com.ahsj.hiscore.services.HisMedicalOrderDetailService;
import com.ahsj.hiscore.services.HisMedicalOrderTemplateDetailService;
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

    @Autowired
    HisMedicalOrderDetailService hisMedicalOrderDetailService;

    @Autowired
    HisMedicalOrderTemplateDetailService hisMedicalOrderTemplateDetailService;

    @Autowired
    HisMedicalOrderDetailMapper hisMedicalOrderDetailMapper;
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

    // //保存当前医嘱为模板

    @Override
    @Transactional(readOnly = false)
    public Message saveForTemlate(Long[] ids, String orderNumber, Integer type, String templateName) throws Exception{
        //ids为空数组说明保存所有医嘱模板
        HisMedicalOrderTemplate hisMedicalOrderTemplate = new HisMedicalOrderTemplate();
        hisMedicalOrderTemplate.setTemplateName(templateName);
        if(type == 1){
            hisMedicalOrderTemplate.setRemarks("1");
        }else {
            hisMedicalOrderTemplate.setRemarks("0");
        }
        saveOrUpdate(hisMedicalOrderTemplate);
        if(EmptyUtil.Companion.isNullOrEmpty(ids) || ids.length ==  0){
            List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailService.selectByOrderNumber(orderNumber);
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetailList) || hisMedicalOrderDetailList.size() == 0){
                return MessageUtil.createMessage(false,"不能保存空医嘱信息为模板(Can not Save null information)");
            }else {
                for (HisMedicalOrderDetail hisMedicalOrderDetail : hisMedicalOrderDetailList) {
                    HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail = new HisMedicalOrderTemplateDetail();
                    hisMedicalOrderTemplateDetail.setTemplateNumber(hisMedicalOrderTemplate.getTemplateNumber());
                    hisMedicalOrderTemplateDetail.setTemplateName(hisMedicalOrderTemplate.getTemplateName());
                    hisMedicalOrderTemplateDetail.setName(hisMedicalOrderDetail.getName());
                    hisMedicalOrderTemplateDetail.setIsSkinTest(hisMedicalOrderDetail.getIsSkinTest());
                    hisMedicalOrderTemplateDetail.setName(hisMedicalOrderDetail.getName());
                    hisMedicalOrderTemplateDetail.setUsages(hisMedicalOrderDetail.getUsages());
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount()))
                        hisMedicalOrderTemplateDetail.setTotalAmount(hisMedicalOrderDetail.getTotalAmount().doubleValue());
                    //remarks代表的是对应的医嘱类型
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getMedicalOrderType()))
                        hisMedicalOrderTemplateDetail.setRemarks(hisMedicalOrderDetail.getMedicalOrderType().toString());
                    hisMedicalOrderTemplateDetail.setIntervals(hisMedicalOrderDetail.getIntervals());
                    //stopUserId代表的是对应的项目表ID或者药库表ID
                    hisMedicalOrderTemplateDetail.setStopUserId(hisMedicalOrderDetail.getTargetId());

                    hisMedicalOrderTemplateDetailService.saveOrUpdate(hisMedicalOrderTemplateDetail);
                }

            }
        }
        //否则说明保存的是选中的医嘱
        else {
            for (int i = 0; i <ids.length ; i++) {
                HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailService.selectById(ids[i]);
                HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail = new HisMedicalOrderTemplateDetail();
                hisMedicalOrderTemplateDetail.setTemplateNumber(hisMedicalOrderTemplate.getTemplateNumber());
                hisMedicalOrderTemplateDetail.setTemplateName(hisMedicalOrderTemplate.getTemplateName());
                hisMedicalOrderTemplateDetail.setName(hisMedicalOrderDetail.getName());
                hisMedicalOrderTemplateDetail.setIsSkinTest(hisMedicalOrderDetail.getIsSkinTest());
                hisMedicalOrderTemplateDetail.setName(hisMedicalOrderDetail.getName());
                hisMedicalOrderTemplateDetail.setUsages(hisMedicalOrderDetail.getUsages());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount()))
                    hisMedicalOrderTemplateDetail.setTotalAmount(hisMedicalOrderDetail.getTotalAmount().doubleValue());
                //remarks代表的是对应的医嘱类型
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getMedicalOrderType()))
                    hisMedicalOrderTemplateDetail.setRemarks(hisMedicalOrderDetail.getMedicalOrderType().toString());
                hisMedicalOrderTemplateDetail.setIntervals(hisMedicalOrderDetail.getIntervals());
                //stopUserId代表的是对应的项目表ID或者药库表ID
                hisMedicalOrderTemplateDetail.setStopUserId(hisMedicalOrderDetail.getTargetId());
                hisMedicalOrderTemplateDetailService.saveOrUpdate(hisMedicalOrderTemplateDetail);
            }
        }
        return MessageUtil.createMessage(true, "保存成功(Save Success)");
    }
//一键设置医嘱开始时间
    @Override
    @Transactional(readOnly = false)
    public Message setTime(String orderNumber, String orderStartTime) throws Exception {
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = hisMedicalOrderDetailService.selectByOrderNumber(orderNumber);
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetailList)&&hisMedicalOrderDetailList.size()!=0){
            for (HisMedicalOrderDetail hisMedicalOrderDetail : hisMedicalOrderDetailList) {
                hisMedicalOrderDetail.setStartTime(orderStartTime);
                hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
            }
            return MessageUtil.createMessage(true, "设置成功（Set Success）");
        }else {
            return MessageUtil.createMessage(false, "没有可以设置的医嘱");
        }
    }
}
