package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.entity.HisRefundProject;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisRecordProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisRecordProject record);

    int insertSelective(HisRecordProject record);

    HisRecordProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRecordProject record);

    int updateByPrimaryKey(HisRecordProject record);

    /**
     * @return void
     * @Description 批量插入
     * @Params [detailsList]
     * @Author chenzhicai
     * @Date 2019-07-19
     * @Time 10:15
     **/
    void insertBatch(List<HisRecordProject> detailsList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据医疗记录查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-19
     * @Time 10:14
     **/
    List<HisRecordProject> selectByMedicalRecordId(Long id);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据就诊记录删除诊疗项目
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-07-19
     * @Time 10:14
     **/
    void deleteByRecordId(Long recordId);

    /**
     * @return void
     * @Description 批量修改付费状态
     * @Params [hisRecordProjects]
     * @Author zhushixiang
     * @Date 2019-09-11
     * @Time 10:10
     **/
    void setPayBatch(List<HisRecordProject> hisRecordProjects);


    /**
     * @Description 诊疗项目病人清单
     * @Author muxu
     * @Date 2019/8/17
     * @Time 10:23
     * @Return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Params [pageBean]
     **/
    List<HisRecordProject> ItemPatientList(PageBean<HisRecordProject> pageBean);

    /**
     * @Description 病人诊疗项目审核
     * @Author muxu
     * @Date 2019/8/17
     * @Time 10:24
     * @Return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Params [pageBean]
     **/
    List<HisRecordProject> selectByMedicalRecordIdList(PageBean<HisRecordProject> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 17:40
     **/
    List<HisRecordProject> queryList(PageBean<HisRecordProject> pageBean);

    List<HisRecordProject> queryPriceList(PageBean<HisRecordProject> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明 查询申请退项目的列表记录明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 10:44
     **/
    List<HisRecordProject> projectInfo(PageBean<HisRecordProject> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据就诊记录ID搜索出未付费且未检查的项目
     * @Params [recordId]
     * @Author zhushixiang
     * @Date 2019-08-16
     * @Time 9:49
     **/
    List<HisRecordProject> selectByRecordIdNotIsCheckdOrIsPayed(Long recordId);

    /**
     * @return void
     * @Description 根据就诊记录ID批量删除未付费且未检查的项目
     * @Params [recordId]
     * @Author zhushixiang
     * @Date 2019-08-16
     * @Time 9:53
     **/
    void deleteByRecordIdNotIsCheckdOrIsPayed(Long recordId);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明 查询退款明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 20:05
     **/
    List<HisRecordProject> pageBeanList(PageBean<HisRecordProject> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/21
     * @Time 18:50
     **/
    List<HisRecordProject> pricelistsBytollRecordNumber(HisRecordProject hisRecordProject);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 15:10
     **/
    List<HisRecordProject> HisRecordProjectLists(HisRecordProject hisRecordProject);
    // List< HisRecordProject> pricelists(PageBean< HisRecordProject> pageBean);

    /**
     * @Description 获取项目打印信息
     * @Author muxu
     * @Date 2019/8/19
     * @Time 10:35
     * @Return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Params [id]
     **/
    HisRecordProject getProjectById(Long id);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [project]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 9:50
     **/
    List<HisRecordProject> queryLists(HisRecordProject project);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 17:47
     **/
    List<HisRecordProject> projectInfoList(HisRecordProject hisRecordProject);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 17:55
     **/
    List<HisRefundProject> queryHisRecordProject(HisRefundProject hisRefundProject);

    /**
     * @return int
     * @功能说明
     * @Params [refundProjectList]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 20:32
     **/
    int updateByHisRefundProjectListBack(List<HisRecordProject> refundProjectList);

    List<HisRecordProject> selectPrint(String number);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 查询出当前就诊编号未付费的项目列表
     * @Params [medicalRecordId]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 17:30
     **/
    List<HisRecordProject> selectByMedicalRecordIdNotIspayed(Long medicalRecordId);

    /**
     * @Description 根据就诊编号查询历史项目
     * @Params: [medicalNumber]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Date 2019/9/24
     * @Time 14:05
     **/
    List<HisRecordProject> selectByMedicalNumber(PageBean<HisRecordProject> pageBean);
}