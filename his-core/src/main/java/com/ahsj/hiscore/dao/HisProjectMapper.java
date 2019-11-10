package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisProject;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface HisProjectMapper extends BaseMapper<HisProject> {

    int deleteByPrimaryKey(Long id);

    int insert(HisProject hisProject);

    int insertSelective(HisProject hisProject);

    HisProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisProject hisProject);

    int updateByPrimaryKey(HisProject hisProject);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    List<HisProject> queryList(PageBean<HisProject> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 10:27
     **/
    List<HisProject> queryListAll(List<HisProject> projectList) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    HisProject queryProject() throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [combinationId]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    List<HisProject> queryCombinationId(Long combinationId) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    List<HisProject> queryCombinationIds(PageBean<HisProject> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    List<HisProject> selectForTreatPlan(Long[] ids) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params [name]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    HisProject queryHisProjectByName(@Param("name") String name) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    List<HisProject> queryProjectAll() throws Exception;


    List<HisProject> queryProjectDescIdAll() throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [name]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:37
     **/
    List<HisProject> queryHisProjectByNames(@Param("name") String name) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:21
     **/
    List<HisProject> queryTranslateInfoByDate(HisProject hisProject);



    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 16:59
     **/
    List<HisProject> queryAll();

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:36
     **/
    List<HisProject> queryLists(PageBean<HisProject> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 17:28
     **/
    List<HisProject> queryAddList(PageBean<HisProject> pageBean);

    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:10
     **/
    HisProject getHisProject(HisProject hisProject);

    /**
     * @return int
     * @功能说明
     * @Params [projectInsertList]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:20
     **/
    int importHisProject(List<HisProject> projectInsertList);

    /**
     * @return int
     * @功能说明
     * @Params [projectUpdateList]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:27
     **/
    int updateHisProject(List<HisProject> projectUpdateList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [projectList]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:29
     **/
    List<HisProject> queryListExportAll(HisProject hisProject);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [list]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:33
     **/
    List<HisProject> queryListExportAndByIdsAll(List<HisProject> list);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/28
     * @Time 16:22
     **/
    List<HisProject> search(HisProject hisProject);

    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params [number]
     * @Author XJP
     * @Date 2019/9/19
     * @Time 11:05
     **/
    HisProject queryHisProjectByNumber(@Param("number") String number);

    /**
     *@Description 根据IDs查询项目信息
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisProject>
     *@Author zhushixiang
     *@Date 2019-10-08
     *@Time 13:50
    **/
    List<HisProject> selectForListForProjectByIds(Long[] ids);


    HisProject selectByNumber(String number);

    //模糊查询（or）
    List<? extends HisProject> listForAll(PageBean<HisProject> pageBean);
}