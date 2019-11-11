package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicalOrderTemplateDetailMapper;
import com.ahsj.hiscore.dao.HisMedicalOrderTemplateMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisMedicalOrderTemplateDetailService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
import com.ahsj.hiscore.services.HisProjectService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class HisMedicalOrderTemplateDetailServicelmpl implements HisMedicalOrderTemplateDetailService {
    @Autowired
    HisMedicalOrderTemplateDetailMapper hisMedicalOrderTemplateDetailMapper;

    @Autowired
    HisMedicalOrderTemplateMapper hisMedicalOrderTemplateMapper;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisProjectService hisProjectService;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail>
     * @Description 医嘱模板明细分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-28
     * @Time 18:26
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalOrderTemplateDetail> list(PageBean<HisMedicalOrderTemplateDetail> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalOrderTemplateDetailMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-07-28
     * @Time 19:06
     **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalOrderTemplateDetail selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderTemplateDetailMapper.selectByPrimaryKey(id));
    }

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisMedicalOrderTemplateDetail]
     * @Author zhushixiang
     * @Date 2019-07-28
     * @Time 19:13
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail) throws Exception {
        HisMedicalOrderTemplateDetail checkId = hisMedicalOrderTemplateDetailMapper.selectByPrimaryKey(hisMedicalOrderTemplateDetail.getId());
        List<HisMedicalOrderTemplateDetail> hisMedicalOrderTemplateDetailList = hisMedicalOrderTemplateDetailMapper.selectByTemplateNumberOrderByOrderNum(hisMedicalOrderTemplateDetail.getTemplateNumber());
        //新增医嘱模板明细（要有序号）
        if (EmptyUtil.Companion.isNullOrEmpty(checkId)) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetail.getOrderNum())) {
                if (hisMedicalOrderTemplateDetailList.size() == 0)
                    hisMedicalOrderTemplateDetail.setOrderNum(1);
                else
                    hisMedicalOrderTemplateDetail.setOrderNum(hisMedicalOrderTemplateDetailList.get(0).getOrderNum() + 1);
                hisMedicalOrderTemplateDetailMapper.insert(hisMedicalOrderTemplateDetail);
                return MessageUtil.createMessage(true, "新增成功");
            } else {
                if (hisMedicalOrderTemplateDetail.getOrderNum() > hisMedicalOrderTemplateDetailList.size())
                    return MessageUtil.createMessage(false, "输入序号大于当前总数，请重新输入");
                else {
                    hisMedicalOrderTemplateDetailList.add(hisMedicalOrderTemplateDetailList.size() - (hisMedicalOrderTemplateDetail.getOrderNum() - 1), hisMedicalOrderTemplateDetail);
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetailList))
                        return MessageUtil.createMessage(false, "无医嘱明细可保存");
                    arrayToLinked(hisMedicalOrderTemplateDetailList);
                    return MessageUtil.createMessage(true, "新增成功");
                }
            }

            //更新医嘱（）
        } else {
            hisMedicalOrderTemplateDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderTemplateDetail);
            return MessageUtil.createMessage(true, "更新成功");
        }
    }

    /**
     * @return void
     * @Description 为操作序号排序
     * @Params [hisMedicalOrderDetailList]
     * @Author zhushixiang
     * @Date 2019-07-28
     * @Time 19:22
     **/
    public void arrayToLinked(List<HisMedicalOrderTemplateDetail> hisMedicalOrderTemplateDetailList) throws Exception {
        LinkedList<HisMedicalOrderTemplateDetail> hisMedicalOrderDetailLinkedList = new LinkedList<>();
        for (int i = hisMedicalOrderTemplateDetailList.size() - 1; i >= 0; i--) {
            hisMedicalOrderTemplateDetailList.get(i).setOrderNum(hisMedicalOrderTemplateDetailList.size() - i);
            hisMedicalOrderDetailLinkedList.add(hisMedicalOrderTemplateDetailList.get(i));
        }
        if (hisMedicalOrderDetailLinkedList.size() != 0) {
            hisMedicalOrderTemplateDetailMapper.deleteByTemplateNumber(hisMedicalOrderDetailLinkedList.get(0).getTemplateNumber());
            hisMedicalOrderTemplateDetailMapper.insertBatch(hisMedicalOrderDetailLinkedList);
        }
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-07-28
     * @Time 20:12
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail = hisMedicalOrderTemplateDetailMapper.selectByPrimaryKey(ids[0]);
        List<HisMedicalOrderTemplateDetail> hisMedicalOrderTemplateDetailList = hisMedicalOrderTemplateDetailMapper.selectByTemplateNumberOrderByOrderNum(hisMedicalOrderTemplateDetail.getTemplateNumber());
        if (hisMedicalOrderTemplateDetailList.size() == 1) {
            hisMedicalOrderTemplateDetailMapper.deleteByPrimaryKey(ids[0]);
            return MessageUtil.createMessage(true, "删除成功!");
        }
        for (Long id : ids) {
            HisMedicalOrderTemplateDetail forRemove = hisMedicalOrderTemplateDetailMapper.selectByPrimaryKey(id);
            for (int i = 0; i < hisMedicalOrderTemplateDetailList.size(); i++) {
                if (forRemove.getId() == hisMedicalOrderTemplateDetailList.get(i).getId()) {
                    hisMedicalOrderTemplateDetailList.remove(i);
                    break;
                }
            }
        }
        if(hisMedicalOrderTemplateDetailList.size() == 0){
            hisMedicalOrderTemplateDetailMapper.deleteByTemplateNumber(hisMedicalOrderTemplateDetail.getTemplateNumber());
        }else
            arrayToLinked(hisMedicalOrderTemplateDetailList);
        return MessageUtil.createMessage(true, "删除成功!");

    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail>
     * @Description 根据模板编号搜索对应模板明细
     * @Params [templateNumber]
     * @Author zhushixiang
     * @Date 2019-07-29
     * @Time 9:37
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicalOrderTemplateDetail> selectByTemplateNumber(String templateNumber) {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderTemplateDetailMapper.selectByTemplateNumberOrderByOrderNum(templateNumber));
    }

    @Override
    @Transactional(readOnly = false)
    public Message saveMedicineOrder(String[] drugsNumbs, Integer[] nums, String[] usages, String[] intervals, String templateNumber) throws Exception{
        for (int i = 0; i <drugsNumbs.length ; i++) {
            if(EmptyUtil.Companion.isNullOrEmpty(usages[i])){
                usages[i]="";
            }
            if(EmptyUtil.Companion.isNullOrEmpty(intervals[i])){
                intervals[i]="";
            }
            HisMedicalOrderTemplate hisMedicalOrderTemplate = hisMedicalOrderTemplateMapper.selectByTemplateNumber(templateNumber);
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectByDrugsNumb(drugsNumbs[i]);
            HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail =new HisMedicalOrderTemplateDetail();
            hisMedicalOrderTemplateDetail.setTemplateNumber(templateNumber);
            hisMedicalOrderTemplateDetail.setTemplateName(hisMedicalOrderTemplate.getTemplateName());
            hisMedicalOrderTemplateDetail.setName(hisPharmacyDetail.getDrugsName()+"  "+hisPharmacyDetail.getDrugsSpec());
            hisMedicalOrderTemplateDetail.setIsSkinTest(1);
            hisMedicalOrderTemplateDetail.setUsages(usages[i]);
            hisMedicalOrderTemplateDetail.setIntervals(intervals[i]);
            hisMedicalOrderTemplateDetail.setTotalAmount(nums[i].doubleValue());
            hisMedicalOrderTemplateDetail.setRemarks("2");
            hisMedicalOrderTemplateDetail.setStopUserId(hisPharmacyDetail.getId());
            saveOrUpdate(hisMedicalOrderTemplateDetail);
        }
        return MessageUtil.createMessage(true, "新增成功（Add Success）");
    }

    //新增项目医嘱模板

    @Override
    @Transactional(readOnly = false)
    public Message saveProjectOrder(String[] numbers, Integer[] nums, String templateNumber) throws Exception {
        HisMedicalOrderTemplate hisMedicalOrderTemplate = hisMedicalOrderTemplateMapper.selectByTemplateNumber(templateNumber);
        for (int i = 0; i <numbers.length ; i++) {
            HisProject hisProject = hisProjectService.selectByNumber(numbers[i]);
            HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail =new HisMedicalOrderTemplateDetail();
            hisMedicalOrderTemplateDetail.setTemplateNumber(templateNumber);
            hisMedicalOrderTemplateDetail.setTemplateName(hisMedicalOrderTemplate.getTemplateName());
            hisMedicalOrderTemplateDetail.setName(hisProject.getName());
            hisMedicalOrderTemplateDetail.setIsSkinTest(1);
            hisMedicalOrderTemplateDetail.setTotalAmount(nums[i].doubleValue());
            hisMedicalOrderTemplateDetail.setRemarks("3");
            hisMedicalOrderTemplateDetail.setStopUserId(hisProject.getId());
            saveOrUpdate(hisMedicalOrderTemplateDetail);
        }
        return MessageUtil.createMessage(true, "新增成功（Add Success）");
    }
}
