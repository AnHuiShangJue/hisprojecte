package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisAnkleDetailMapper;
import com.ahsj.hiscore.dao.HisMedicalOrderDetailMapper;
import com.ahsj.hiscore.dao.HisMedicalOrderMapper;
import com.ahsj.hiscore.entity.HisAnkleDetail;
import com.ahsj.hiscore.entity.HisMedicalOrder;
import com.ahsj.hiscore.entity.HisMedicalOrderDetail;
import com.ahsj.hiscore.services.HisAnkleDetailService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

@Service
public class HisAnkleDetailServicelmpl implements HisAnkleDetailService {
    @Autowired
    HisAnkleDetailMapper hisAnkleDetailMapper;

    @Autowired
    HisMedicalOrderDetailMapper hisMedicalOrderDetailMapper;
    /**
     *@Description 根据护嘱编号查看护嘱明细
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisAnkleDetail>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 14:48
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisAnkleDetail> listByNumber(PageBean<HisAnkleDetail> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisAnkleDetailMapper.listByNumber(pageBean)));
        return pageBean;
    }


    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisAnkleDetail hisAnkleDetail) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisAnkleDetail.getId())){
            //id为空则新增
            //查找该就诊记录对应的记录list
            List<HisAnkleDetail> hisAnkleDetailList = hisAnkleDetailMapper.selectListByNumber(hisAnkleDetail.getNumber());
            if (hisAnkleDetail.getOrderNum() == 0){
                return MessageUtil.createMessage(false,"序号请从1开始新增");
            }else if (hisAnkleDetail.getOrderNum()> hisAnkleDetailList.size()+1){
                return MessageUtil.createMessage(false,"请按序号新增");

            }else if (hisAnkleDetail.getOrderNum() > hisAnkleDetailList.size()){
                //直接插入
                hisAnkleDetail.setIsProofreading(2);
                hisAnkleDetail.setIsApproved(2);
                hisAnkleDetailMapper.insert(hisAnkleDetail);
                return MessageUtil.createMessage(true,"新增成功");

            }else {
                if (hisAnkleDetail.getOrderNum() == 1){
                    for (int i=0;i<hisAnkleDetailList.size();++i){
                        hisAnkleDetailList.get(i).setOrderNum(i+2);

                    }
                    hisAnkleDetailMapper.updateBatch(hisAnkleDetailList);

                }else {
                    for (int i= hisAnkleDetail.getOrderNum()-1;i<hisAnkleDetailList.size();++i){
                        hisAnkleDetailList.get(i).setOrderNum(i+2);
                    }
                    hisAnkleDetailMapper.updateBatch(hisAnkleDetailList);
                }

                hisAnkleDetail.setIsProofreading(2);
                hisAnkleDetail.setIsApproved(2);
                hisAnkleDetailMapper.insert(hisAnkleDetail);
                return MessageUtil.createMessage(true,"插入成功");

            }

        }else {
            //id不为空则更新
            //根据主键查找

            //更新
            HisAnkleDetail check = hisAnkleDetailMapper.selectByPrimaryKey(hisAnkleDetail.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(check)){
                //不为空---更新

                hisAnkleDetailMapper.updateByPrimaryKeySelective(hisAnkleDetail);
                return MessageUtil.createMessage(true,"更新成功!");
            }else {
                return MessageUtil.createMessage(false,"更新失败!");
            }
        }

    }

    @Override
    @Transactional(readOnly = false)
    public HisAnkleDetail selectByPrimaryId(Long id) throws Exception {
        return hisAnkleDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public HisAnkleDetail selectWithMumber(String number) throws Exception {
        return hisAnkleDetailMapper.selectWithNumber(number);
    }

    @Override
    public List<HisAnkleDetail> selectByNumber(String number) throws Exception {
        return null;
    }

    @Override
    public Message delete(Long[] ids, String recordId) throws Exception {
        for (Long id:ids) {
            hisAnkleDetailMapper.deleteByPrimaryKey(id);
        }
        //重新排序
        List<HisAnkleDetail> hisAnkleList = hisAnkleDetailMapper.selectListByNumber(recordId);
        for (int i = 0;i < hisAnkleList.size();++i){
            hisAnkleList.get(i).setOrderNum(i+1);
        }
        hisAnkleDetailMapper.updateBatch(hisAnkleList);
        return MessageUtil.createMessage(true,"删除成功!");
    }



    /**
     *@Description 校对
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/24
     *@Time 19:06
     */
    @Override
    @Transactional(readOnly = false)
    public Message proofreading(Long[] ids,Long nurseId) throws Exception {
        HisAnkleDetail hisAnkle = hisAnkleDetailMapper.selectByPrimaryKey(ids[0]);
        hisAnkle.setIsProofreading(1);
        hisAnkle.setProofreadingNurseId(nurseId);
        hisAnkleDetailMapper.updateByPrimaryKeySelective(hisAnkle);
        return MessageUtil.createMessage(true,"校对成功!");

    }

    /**
     *@Description 停嘱校对
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/29
     *@Time 0:31
    */
    @Override
    @Transactional(readOnly = false)
    public Message stopProofreading(Long[] ids,Long nurseId) throws Exception {
        HisAnkleDetail hisAnkle = hisAnkleDetailMapper.selectByPrimaryKey(ids[0]);
        hisAnkle.setIsStopProofreading(2);
        hisAnkle.setStopPrfingNurseId(nurseId);
        hisAnkleDetailMapper.updateByPrimaryKeySelective(hisAnkle);
        return MessageUtil.createMessage(true,"停嘱校对成功!");
    }


    /**
     *@Description 停嘱核准
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/29
     *@Time 0:45
    */
    @Override
    @Transactional(readOnly = false)
    public Message stopApproved(Long[] ids,Long nurseId) throws Exception {
        HisAnkleDetail hisAnkle = hisAnkleDetailMapper.selectByPrimaryKey(ids[0]);
//        hisAnkle.setIsStop(1);
        hisAnkle.setIsstopapproved(2);
        hisAnkle.setStopApdNurseId(nurseId);
        hisAnkleDetailMapper.updateByPrimaryKeySelective(hisAnkle);
        return MessageUtil.createMessage(true,"停嘱核准成功!");
    }


    /**
     *@Description 核准
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/29
     *@Time 0:45
    */

    @Override
    @Transactional(readOnly = false)
    public Message isApproved(Long[] ids,Long nurseId) throws Exception {
        HisAnkleDetail hisAnkle = hisAnkleDetailMapper.selectByPrimaryKey(ids[0]);
        hisAnkle.setIsApproved(1);
        hisAnkle.setApprovedNurseId(nurseId);
        hisAnkleDetailMapper.updateByPrimaryKeySelective(hisAnkle);
        return MessageUtil.createMessage(true,"核准成功!");

    }
    /**
     *@Description ting停嘱
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/21
     *@Time 22:02
    */

    @Override
    @Transactional(readOnly = false)
    public Message stop(Long[] ids) throws Exception {
        HisAnkleDetail hisAnkle = hisAnkleDetailMapper.selectByPrimaryKey(ids[0]);
        hisAnkle.setIsStop(1);
        hisAnkleDetailMapper.updateByPrimaryKeySelective(hisAnkle);
        return MessageUtil.createMessage(true,"停嘱成功!");

    }


    /**
     *@Description 医嘱校对核准
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/21
     *@Time 22:55
    */

    @Override
    @Transactional(readOnly = false)
    public Message proofreadingD(Long[] ids,Long nurseId) throws Exception {
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(ids[0]);
        hisMedicalOrderDetail.setIsProofreading(1);
        hisMedicalOrderDetail.setProofreadingNurseId(nurseId);
        hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
        return MessageUtil.createMessage(true,"校对成功!");

    }


    @Override
    @Transactional(readOnly = false)
    public Message stopProofreadingD(Long[] ids,Long nurseId) throws Exception {
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(ids[0]);
        hisMedicalOrderDetail.setIsStopProofreading(2);
        hisMedicalOrderDetail.setStopPrfingNurseId(nurseId);
        hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
        return MessageUtil.createMessage(true,"停嘱校对成功!");
    }


    @Override
    @Transactional(readOnly = false)
    public Message stopApprovedD(Long[] ids,Long nurseId) throws Exception {
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(ids[0]);
        hisMedicalOrderDetail.setIsstopapproved(2);
        hisMedicalOrderDetail.setStopApdNurseId(nurseId);
        hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
        return MessageUtil.createMessage(true,"停嘱核准成功!");
    }


    @Override
    @Transactional(readOnly = false)
    public Message isApprovedD(Long[] ids,Long nurseId) throws Exception {
        HisMedicalOrderDetail hisMedicalOrderDetail = hisMedicalOrderDetailMapper.selectByPrimaryKey(ids[0]);
        hisMedicalOrderDetail.setIsApproved(1);
        hisMedicalOrderDetail.setApprovedNurseId(nurseId);
        hisMedicalOrderDetailMapper.updateByPrimaryKeySelective(hisMedicalOrderDetail);
        return MessageUtil.createMessage(true,"核准成功!");

    }

    //操作顺序1221 stop2
}
