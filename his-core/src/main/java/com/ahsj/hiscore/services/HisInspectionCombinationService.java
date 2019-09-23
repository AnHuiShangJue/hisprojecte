package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisInspectionCombination;
import com.ahsj.hiscore.entity.HisProject;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisInspectionCombinationService
 * <p>
 * Date:     2019/7/11 16:30
 *
 * @author XJP
 * @create 2019/7/11
 * @since 1.0.0
 */

public interface HisInspectionCombinationService {
    /**
     * @return void
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:26
     **/
    void deleteByPrimaryKey(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisInspectionCombination]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:26
     **/
    Message insertSelective(HisInspectionCombination hisInspectionCombination) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisInspectionCombination
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:26
     **/
    HisInspectionCombination selectByPrimaryKey(Long id);

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisInspectionCombination]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:26
     **/
    Message updateByPrimaryKeySelective(HisInspectionCombination hisInspectionCombination) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisInspectionCombination>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:26
     **/
    PageBean<HisInspectionCombination> queryList(PageBean<HisInspectionCombination> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids, nums, combinationName, combinationNumber, hisProjectOrderNums]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    Message updateSelectiveList(Long[] ids, Integer[] nums, String combinationName, String combinationNumber, Integer[] hisProjectOrderNums) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids, nums, combinationName, combinationNumber, hisProjectOrderNums, combinationId]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    Message addUpdateSelectiveList(Long[] ids, Integer[] nums, String combinationName, String combinationNumber, Integer[] hisProjectOrderNums, Long combinationId) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    Message updateSetDisable(Long[] ids) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    Message deleteHisCombinationByIds(Long[] ids) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    List<HisProject> selectInspecProjectList(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:25
     **/
    Message updateHisProject(HisProject hisProject) throws Exception;

    /**
     *@Description 查询所有项目组合
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisInspectionCombination>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 16:43
    **/
    List<HisInspectionCombination> selectCombineProject()throws Exception;

    /**
     *@Description 查看门诊病历模板中的组合项目明细
     *@Params [pageEntity]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisInspectionCombination>
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 21:23
    **/
    PageBean<HisInspectionCombination> viewDetailInMedicalTemplate(PageBean<HisInspectionCombination> pageEntity)throws Exception;

    Long addUpdateSelectiveReturnId(Long[] ids, Integer[] nums, String combinationName, String combinationNumber, Integer[] hisProjectOrderNums, Long combinationId) throws Exception;
}
