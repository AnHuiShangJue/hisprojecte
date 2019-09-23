package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisAnkleDetail;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisAnkleDetailService {
    /**
     *@Description 根据护嘱编号查看护嘱明细
     *@Params [pageBean]
     *@return core.entity.PageBean<HisAnkle>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 14:46
    **/
    PageBean<HisAnkleDetail> listByNumber(PageBean<HisAnkleDetail> pageBean) throws Exception;

    Message saveOrUpdate(HisAnkleDetail hisAnkleDetail) throws Exception;

    HisAnkleDetail selectByPrimaryId(Long id)throws Exception;
    HisAnkleDetail selectWithMumber(String number)throws Exception;

    List<HisAnkleDetail> selectByNumber(String number)throws Exception;


    Message delete(Long[] ids, String recordId)throws Exception;

    Message proofreading(Long[] ids,Long nurseIds) throws Exception;
    Message stopProofreading(Long[] ids,Long nurseIds) throws Exception;

    Message isApproved(Long[] ids,Long nurseId) throws Exception;
    Message stopApproved(Long[] ids,Long nurseId) throws Exception;

    Message stop(Long[] ids) throws Exception;

    Message proofreadingD(Long[] ids,Long nurseIds) throws Exception;
    Message stopProofreadingD(Long[] ids,Long nurseIds) throws Exception;

    Message isApprovedD(Long[] ids,Long nurseId) throws Exception;
    Message stopApprovedD(Long[] ids,Long nurseId) throws Exception;


}
