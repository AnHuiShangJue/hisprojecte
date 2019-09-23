package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisInspecProject;
import com.ahsj.hiscore.entity.HisProject;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisInspecProjectService
 * <p>
 * Date:     2019/7/12 15:42
 *
 * @author XJP
 * @create 2019/7/12
 * @since 1.0.0
 */

public interface HisInspecProjectService {

    void deleteByPrimaryKey(Long id);

    void insert(HisInspecProject hisInspecProject);

    void insertSelective(HisInspecProject hisInspecProject);

    HisInspecProject selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(HisInspecProject hisInspecProject);

    int updateByPrimaryKey(HisInspecProject hisInspecProject);

    void deleteByInspectionId(Long inspectionId);

    List<HisInspecProject> selectInspecProjectList(Long id);

    Message deleteByIds(Long[] ids, Long id) throws Exception;

    HisInspecProject queryCombinationByProjectAndInspectionId(HisInspecProject hisInspecProject);

    List<HisInspecProject> selectByHisInspecProjectInspectionId(Long inspectionId);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisInspecProject>
     * @功能说明
     * @Params [longValue]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 12:54
     **/
    List<HisInspecProject> queryByProjectId(long longValue);

    List<HisInspecProject> queryByInspectionId(Long inspectionId);
}

