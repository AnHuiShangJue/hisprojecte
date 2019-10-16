package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisPatientInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisPatientInfoMapper  extends BaseMapper<HisPatientInfo>{
    int deleteByPrimaryKey(Long id);

    int insert(HisPatientInfo record);

    int insertSelective(HisPatientInfo record);

    HisPatientInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisPatientInfo record);

    int updateByPrimaryKey(HisPatientInfo record);

    /**
     *@Description 根据身份证号码查找
     *@Params [idcard]
     *@return com.ahsj.hiscore.entity.HisPatientInfo
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:36
    **/
    HisPatientInfo selectByIdcard(String idcard);

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisPatientInfo>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:36
    **/
    List< ? extends HisPatientInfo> list(PageBean<HisPatientInfo> pageBean);

    /**
     *@Description 根据就诊编号查找
     *@Params [medicalRecordId]
     *@return com.ahsj.hiscore.entity.HisPatientInfo
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    HisPatientInfo selectByMedicalRecordId( String medicalRecordId);

    /**
     *@Description recordId 功能同上
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/17
     *@Time 18:21
    */
    HisPatientInfo selectByRecordId(String recordId);

    /**
     *@Description 根据护嘱编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisPatientInfo
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    HisPatientInfo selectByAnkleDetailNumber(String number);

    /**
     *@Description
     *@Params [hisPatientInfo]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPatientInfo>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    List<HisPatientInfo> hisPatientInfoList (HisPatientInfo hisPatientInfo);

    /**
     *@Description
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPatientInfo>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    List<HisPatientInfo> selectByIds (Long[] ids);

    /**
     *@Description
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisPatientInfo>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    List<HisPatientInfo> hisPatientInfoAll ();

    /**
     *@Description
     *@Params [hisWardList]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:37
    **/
    int importHisPatientInfo(List<HisPatientInfo> hisWardList);

    /**
     *@Description 根据就诊记录编号  查询相关信息（为HHM编号服务）
     *@Params [hospitalManageId]
     *@return com.ahsj.hiscore.entity.HisPatientInfo
     *@Author zhushixiang
     *@Date 2019-09-26
     *@Time 0:58
    **/
    HisPatientInfo selectByMedicalRecordIdForInhospital(String hospitalManageId);


    List<HisPatientInfo> hisPatientInfoAllOrder(HisPatientInfo  hisPatientInfo);
}