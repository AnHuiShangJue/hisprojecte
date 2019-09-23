package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisAnkle;
import core.entity.PageBean;
import core.message.Message;
import jnr.ffi.annotations.In;

import java.util.List;

public interface HisAnkleService {
    /**
     *@Description 根据就诊记录编号查看护嘱
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisAnkle>
     *@Author zhushixiang
     *@Date 2019-07-16
     *@Time 11:16
    **/
    PageBean<HisAnkle> listByRecordId(PageBean<HisAnkle> pageBean) throws Exception;
    PageBean<HisAnkle> listByMedicalRecordId(PageBean<HisAnkle> pageBean) throws Exception;

    HisAnkle selectByMedicalRecordId(Long medicalRecordId)throws Exception;
    HisAnkle selectByPrimaryId(Long id)throws Exception;


    Message saveOrUpdate(HisAnkle hisAnkle) throws Exception;
    Message save(HisAnkle hisAnkle) throws Exception;

    Message delete(Long[] ids,String recordId) throws Exception;


}
