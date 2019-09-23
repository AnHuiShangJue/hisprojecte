package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisAnkleDetailMapper;
import com.ahsj.hiscore.dao.HisAnkleMapper;
import com.ahsj.hiscore.entity.HisAnkle;
import com.ahsj.hiscore.services.HisAnkleService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

@Service
public class HisAnkleServicelmpl implements HisAnkleService {

    @Autowired
    HisAnkleMapper hisAnkleMapper;

    @Autowired
    HisAnkleDetailMapper hisAnkleDetailMapper;
    /**
     *@Description 根据住院ID查看护嘱
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisAnkle>
     *@Author zhushixiang
     *@Date 2019-07-16
     *@Time 11:00
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisAnkle> listByMedicalRecordId(PageBean<HisAnkle> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisAnkleMapper.listByMedicalRecordId(pageBean)));
        return pageBean;
    }

    /**
     *@Description 根据MedicalRecordId查找
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/17
     *@Time 17:58
    */
    @Override
    @Transactional(readOnly = true)
    public HisAnkle selectByMedicalRecordId(Long medicalRecordId) throws Exception {
        return hisAnkleMapper.selectByMedicalRecordId(medicalRecordId);
    }

    @Override
    @Transactional(readOnly = true)
    public HisAnkle selectByPrimaryId(Long id) throws Exception {
        return hisAnkleMapper.selectByPrimaryKey(id);
    }

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/17
     *@Time 16:32
    */

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisAnkle> listByRecordId(PageBean<HisAnkle> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisAnkleMapper.listByRecordId(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisAnkle hisAnkle) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisAnkle.getId())){
            //id为空则新增
            //查找该就诊记录对应的记录list
            List<HisAnkle> hisAnkleList = hisAnkleMapper.selectNumberByRecordId(hisAnkle.getRecordId());
            if (hisAnkle.getOrderNum() == 0){
                return MessageUtil.createMessage(false,"序号请从1开始添加");
            }
            else if (hisAnkle.getOrderNum()> hisAnkleList.size()+1){
                return MessageUtil.createMessage(false,"请按序号新增");

            }else if (hisAnkle.getOrderNum() > hisAnkleList.size()){
                //直接插入
                hisAnkle.setIsApproved(2);
                hisAnkle.setIsProofreading(2);
                hisAnkleMapper.insert(hisAnkle);
                return MessageUtil.createMessage(true,"新增成功");

            }else {
                if (hisAnkle.getOrderNum() == 1){
                    for (int i=0;i<hisAnkleList.size();++i){
                        hisAnkleList.get(i).setOrderNum(i+2);
                    }
                    hisAnkleMapper.updateBatch(hisAnkleList);

                }else {
                    for (int i= hisAnkle.getOrderNum()-1;i<hisAnkleList.size();++i){
                        hisAnkleList.get(i).setOrderNum(i+2);
                    }
                    hisAnkleMapper.updateBatch(hisAnkleList);
                }

                hisAnkle.setIsApproved(2);
                hisAnkle.setIsProofreading(2);
                hisAnkleMapper.insert(hisAnkle);
                return MessageUtil.createMessage(true,"插入成功");

            }

        }else {
            //id不为空则更新
            //根据主键查找

                //更新
                HisAnkle check = hisAnkleMapper.selectByPrimaryKey(hisAnkle.getId());
                if (!EmptyUtil.Companion.isNullOrEmpty(check)){
                    //不为空---更新
                    hisAnkle.setNumber(check.getNumber());
                    hisAnkleMapper.updateByPrimaryKey(hisAnkle);
                    return MessageUtil.createMessage(true,"更新成功!");
                }else {
                    return MessageUtil.createMessage(false,"更新失败!");
                }
            }

        }

    @Override
    @Transactional(readOnly = false)
    public Message save(HisAnkle hisAnkle) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisAnkle)){
            hisAnkleMapper.insert(hisAnkle);
            return MessageUtil.createMessage(true,"新增成功");
        }else {
            return MessageUtil.createMessage(false,"新增失败");
        }
    }

    /**
     *@Description 删除
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/19
     *@Time 9:49
    */
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids,String recordId) throws Exception {
        for (Long id:ids) {
            hisAnkleMapper.deleteByPrimaryKey(id);
        }
        //删除明细表里的相关记录
            hisAnkleDetailMapper.deleteByNumber(recordId);

        //重新排序
        List<HisAnkle> hisAnkleList = hisAnkleMapper.selectNumberByRecordId(recordId);
        for (int i = 0;i < hisAnkleList.size();++i){
         hisAnkleList.get(i).setOrderNum(i+1);
        }
        hisAnkleMapper.updateBatch(hisAnkleList);
        return MessageUtil.createMessage(true,"删除成功!");
    }

    

}
