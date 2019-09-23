package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisBloodTransfusionRecord;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisBloodTransfusionRecordMapper extends BaseMapper<HisBloodTransfusionRecord>{
    int deleteByPrimaryKey(Long id);

    int insert(HisBloodTransfusionRecord record);

    int insertSelective(HisBloodTransfusionRecord record);

    HisBloodTransfusionRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisBloodTransfusionRecord record);

    int updateByPrimaryKey(HisBloodTransfusionRecord record);

    /**
     *@Description 根据住院ID分页查询
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisBloodTransfusionRecord>
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 10:27
    **/
    List<? extends HisBloodTransfusionRecord> listByHospitalManageId(PageBean<HisBloodTransfusionRecord> pageBean);
}