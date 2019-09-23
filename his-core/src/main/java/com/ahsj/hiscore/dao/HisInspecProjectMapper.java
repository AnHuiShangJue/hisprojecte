package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisInspecProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface HisInspecProjectMapper extends BaseMapper<HisInspecProject> {

    int deleteByPrimaryKey(Long id);


    int deleteByInspectionId(Long inspectionId);

    int insert(HisInspecProject hisInspecProject);

    int insertSelective(HisInspecProject hisInspecProject);

    HisInspecProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisInspecProject hisInspecProject);

    int updateByPrimaryKey(HisInspecProject hisInspecProject);


    List<HisInspecProject> selectInspecProjectByCombinationId(Long id);

    int deleteByInspectionIds(HisInspecProject hisInspecProject);

    HisInspecProject queryCombinationByProjectAndInspectionId(HisInspecProject hisInspecProject);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisInspecProject>
     * @功能说明
     * @Params [inspectionId]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 12:58
     **/
    List<HisInspecProject> selectByHisInspecProjectInspectionId(Long inspectionId);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisInspecProject>
     * @功能说明
     * @Params [longValue]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 12:58
     **/
    List<HisInspecProject> queryByProjectId(long longValue);

    List<HisInspecProject> queryByInspectionId(Long inspectionId);
}