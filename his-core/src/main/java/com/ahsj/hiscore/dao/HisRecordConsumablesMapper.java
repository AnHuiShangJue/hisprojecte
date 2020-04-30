package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.HisRecordConsumables;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisRecordConsumablesMapper extends BaseMapper<HisRecordConsumables> {
    int deleteByPrimaryKey(Long id);

    int insert(HisRecordConsumables record);

    int insertSelective(HisRecordConsumables record);

    HisRecordConsumables selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRecordConsumables record);

    int updateByPrimaryKey(HisRecordConsumables record);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName selectByHisRecordConsumables
     * @Params [hisRecordConsumables]
     * @Author XJP
     * @Date 2020/4/27
     * @Time 16:51
     **/
    List<HisRecordConsumables> selectByHisRecordConsumables(HisRecordConsumables hisRecordConsumables);

    /**
     * @return int
     * @Description
     * @MethodName updateByIsDelete
     * @Params [hisRecordConsumablesList]
     * @Author XJP
     * @Date 2020/4/27
     * @Time 17:00
     **/
    int updateByIsDelete(List<HisRecordConsumables> hisRecordConsumablesList);

    /**
     * @return int
     * @Description
     * @MethodName insertList
     * @Params [hisRecordConsumablesList]
     * @Author XJP
     * @Date 2020/4/27
     * @Time 17:03
     **/
    int insertList(List<HisRecordConsumables> hisRecordConsumablesList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName queryList
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 9:24
     **/
    List<HisRecordConsumables> queryList(PageBean<HisRecordConsumables> pageBean);

    /**
     * @return com.ahsj.hiscore.entity.HisRecordConsumables
     * @Description
     * @MethodName queryByConsumablesCodeAndMedicalRecordNumber
     * @Params [hisRecordConsumables]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 11:31
     **/
    HisRecordConsumables queryByConsumablesCodeAndMedicalRecordNumber(HisRecordConsumables hisRecordConsumables);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName consumablesInfo
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 14:56
     **/
    List<HisRecordConsumables> consumablesInfo(PageBean<HisRecordConsumables> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName consumablesInfo
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 16:05
     **/
    List<HisRecordConsumables> consumablesInfoList(HisRecordConsumables hisRecordConsumables);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName queryByRecordConsumablesList
     * @Params [hisRecordConsumables]
     * @Author XJP
     * @Date 2020/4/30
     * @Time 11:17
     **/
    List<HisRecordConsumables> queryByRecordConsumablesList(HisRecordConsumables hisRecordConsumables);


    List<HisRecordConsumables> queryByRecordConsumablesLists(PageBean<HisRecordConsumables> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName pageBeanList
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 17:15
     **/
    List<HisRecordConsumables> pageBeanList(PageBean<HisRecordConsumables> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordConsumables>
     * @Description
     * @MethodName queryByNotBack
     * @Params [recordNumber]
     * @Author XJP
     * @Date 2020/4/30
     * @Time 12:53
     **/
    List<HisRecordConsumables> queryByNotBack(HisRecordConsumables hisRecordConsumables);
}