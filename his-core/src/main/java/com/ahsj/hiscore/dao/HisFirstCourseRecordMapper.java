package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisFirstCourseRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HisFirstCourseRecordMapper extends BaseMapper<HisFirstCourseRecord>{
    int deleteByPrimaryKey(Long id);

    int insert(HisFirstCourseRecord record);

    int insertSelective(HisFirstCourseRecord record);

    HisFirstCourseRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisFirstCourseRecord record);

    int updateByPrimaryKey(HisFirstCourseRecord record);

    /**
     *@Description 根据ID查询出更多信息 如医生姓名
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisFirstCourseRecord
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 8:51
    **/
    HisFirstCourseRecord selectById(Long id);
}