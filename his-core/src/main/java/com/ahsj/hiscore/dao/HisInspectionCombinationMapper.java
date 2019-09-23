package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisInspectionCombination;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisInspectionCombinationMapper extends BaseMapper<HisInspectionCombination>{

    int deleteByPrimaryKey(Long id);

    int insert(HisInspectionCombination hisInspectionCombination);

    int insertSelective(HisInspectionCombination hisInspectionCombination);

    HisInspectionCombination selectByPrimaryKey(Long id);

    HisInspectionCombination selectByNumber(String number);

    int updateByPrimaryKeySelective(HisInspectionCombination hisInspectionCombination);

    int updateByPrimaryKey(HisInspectionCombination hisInspectionCombination);

    List<HisInspectionCombination> queryList(PageBean<HisInspectionCombination> pageBean);

    HisInspectionCombination selectByhisInspectionCombinationName(String name);

    /**
     *@Description 查询所有项目组合
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisInspectionCombination>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 16:46
     **/
    List<HisInspectionCombination> selectCombineProject();

    /**
     *@Description 查看门诊病历模板中的组合项目明细
     *@Params [pageEntity]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisInspectionCombination>
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 21:27
    **/
    List<? extends HisInspectionCombination> viewDetailInMedicalTemplate(PageBean<HisInspectionCombination> pageEntity);
}