package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicalOrderDetailMapper;
import com.ahsj.hiscore.dao.HisMedicalOrderMapper;
import com.ahsj.hiscore.entity.HisMedicalOrder;
import com.ahsj.hiscore.services.HisMedicalOrderService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class HisMedicalOrderServicelmpl implements HisMedicalOrderService {
    @Autowired
    HisMedicalOrderMapper hisMedicalOrderMapper;

    @Autowired
    HisMedicalOrderDetailMapper hisMedicalOrderDetailMapper;
    /**
     *@Description 根据就诊记录编号查询对应的医嘱信息
     *@Params [hospitalManageId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-07-16
     *@Time 19:06
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicalOrder> selectByRecordId(String recordId) {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderMapper.selectByRecordId(recordId));
    }

    /**
     *@Description 根据就诊记录编号查找医嘱及其详细数据
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 13:36
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalOrder> listByRecordId(PageBean<HisMedicalOrder> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalOrderMapper.listByRecordId(pageBean)));
        return pageBean;
    }

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 17:31
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception{
        HisMedicalOrder hisMedicalOrder = hisMedicalOrderMapper.selectByPrimaryKey(ids[0]);
        List<HisMedicalOrder> hisMedicalOrderList = hisMedicalOrderMapper.selectByRecordIdOrderByOrderNum(hisMedicalOrder.getRecordId());
        if (hisMedicalOrderList.size() == 1) {
            hisMedicalOrderDetailMapper.deleteByNumber(hisMedicalOrder.getNumber());
            hisMedicalOrderMapper.deleteByPrimaryKey(ids[0]);
            return MessageUtil.createMessage(true, "删除成功!");
        }
        for (Long id:ids) {
            HisMedicalOrder forRemove = hisMedicalOrderMapper.selectByPrimaryKey(id);
            for (int i = 0; i <hisMedicalOrderList.size() ; i++) {
                if(forRemove.getId() == hisMedicalOrderList.get(i).getId()){
                    hisMedicalOrderList.remove(i);
                    break;
                }
            }
        }
        if(hisMedicalOrderList.size() == 0){
            hisMedicalOrderMapper.deleteByRecordId(hisMedicalOrder.getRecordId());
            for(Long id:ids){
                HisMedicalOrder forDelete = hisMedicalOrderMapper.selectByPrimaryKey(id);
                hisMedicalOrderDetailMapper.deleteByNumber(forDelete.getNumber());
            }
        }else
            arrayToLinked(hisMedicalOrderList);
        return MessageUtil.createMessage(true,"删除成功!");
    }

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicalOrder
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 17:36
     **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalOrder selectById(Long id)throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisMedicalOrderMapper.selectByPrimaryKey(id));
    }

    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrder]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 20:58
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedicalOrder hisMedicalOrder) throws Exception {
        HisMedicalOrder checkId = hisMedicalOrderMapper.selectByPrimaryKey(hisMedicalOrder.getId());
        List<HisMedicalOrder> hisMedicalOrderList = hisMedicalOrderMapper.selectByRecordIdOrderByOrderNum(hisMedicalOrder.getRecordId());
        //新增医嘱（要有序号）
        if(EmptyUtil.Companion.isNullOrEmpty(checkId)){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String createdate = sdf.format(date);
            int count = hisMedicalOrderMapper.selectNumbCount(createdate) + 1;
            //编号
            String number = createdate + String.format("%05d", count);
            number = "MO" + number;//MO：medicalOrder:医嘱
            hisMedicalOrder.setNumber(number);
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrder.getOrderNum())){
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderList))
                    hisMedicalOrder.setOrderNum(1);
                else
                    hisMedicalOrder.setOrderNum(hisMedicalOrderList.get(0).getOrderNum() + 1);
                hisMedicalOrderMapper.insert(hisMedicalOrder);
                return MessageUtil.createMessage(true, "新增成功");
            }else {
                if (hisMedicalOrder.getOrderNum() > hisMedicalOrderList.size())
                    return MessageUtil.createMessage(false, "输入序号大于当前医嘱总数，请重新输入");
                else {
                    hisMedicalOrderList.add(hisMedicalOrderList.size()-(hisMedicalOrder.getOrderNum()-1),hisMedicalOrder);
                    arrayToLinked(hisMedicalOrderList);
                    return MessageUtil.createMessage(true, "新增成功");
                }
            }

            //更新医嘱（）
        }else {
            hisMedicalOrderMapper.updateByPrimaryKeySelective(hisMedicalOrder);
            return MessageUtil.createMessage(true, "更新成功");

        }
    }

    public void arrayToLinked(List<HisMedicalOrder> hisMedicalOrderList)throws Exception{
        LinkedList<HisMedicalOrder> hisMedicalOrderLinkedList = new LinkedList<HisMedicalOrder>();
        for (int i = hisMedicalOrderList.size()-1; i >= 0 ; i--) {
            hisMedicalOrderList.get(i).setOrderNum(hisMedicalOrderList.size()-i);
            hisMedicalOrderLinkedList.add(hisMedicalOrderList.get(i));
        }
        if(hisMedicalOrderList.size()!=0)
            hisMedicalOrderMapper.deleteByRecordId(hisMedicalOrderList.get(0).getRecordId());
        if(hisMedicalOrderLinkedList.size()!=0)
            hisMedicalOrderMapper.insertBatch(hisMedicalOrderLinkedList);
    }

    /**
     *@Description 根据医嘱编号查询
     *@Params [medicalOrderNumber]
     *@return com.ahsj.hiscore.entity.HisMedicalOrder
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 18:31
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalOrder selectByNumber(String medicalOrderNumber) throws Exception {
        return hisMedicalOrderMapper.selectByNumber(medicalOrderNumber);
    }
}
