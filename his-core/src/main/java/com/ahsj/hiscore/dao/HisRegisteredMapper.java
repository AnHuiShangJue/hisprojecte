package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRegistered;
import com.ahsj.hiscore.entity.RegisterdCount;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HisRegisteredMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisRegistered record);

    int insertSelective(HisRegistered record);

    HisRegistered selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRegistered record);

    int updateByPrimaryKey(HisRegistered record);

    int selectNumbCount(String number);

    HisRegistered selectBynumber(String number);

    List<? extends HisRegistered> list(PageBean<HisRegistered> pageBean);

    RegisterdCount countForToday(Long id);

    HisRegistered selectBypatientid(Long patientId );

    List<HisRegistered> selectForObsolete(Long[] id);

    void updateForObsolete(List<HisRegistered> hisRegisteredList);

    HisRegistered detailInit(Long id);

    List<HisRegistered> selectForDaily(Long[] ids);

    void updateBatchForDaily(List<HisRegistered> hisRegistereds);

    /**
     * @Description 挂号时间段统计
     * @Author   muxu
     * @Date 2019/7/28
     * @Time 21:30
     * @Return com.ahsj.hiscore.entity.HisRegistered
     * @Params [hisRegistered]
    **/
    List<HisRegistered> selectByReistDataCount(@Param(value = "createDate") Date createDate, @Param(value = "endDate") Date endDate);

}