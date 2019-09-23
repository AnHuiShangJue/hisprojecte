package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRefundProject;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HisRefundProjectMapper extends BaseMapper<HisRefundProject> {

    int deleteByPrimaryKey(Long id);

    int insert(HisRefundProject hisRefundProject);

    int insertSelective(HisRefundProject hisRefundProject);

    HisRefundProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRefundProject hisRefundProject);

    int updateByPrimaryKey(HisRefundProject hisRefundProject);

    /**
     * @return int
     * @功能说明
     * @Params [hisRefundProjectList]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 19:26
     **/
    int saveForAppEdit(List<HisRefundProject> hisRefundProjectList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询申请退项目的列表记录
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 20:32
     **/
    List<HisRefundProject> list(PageBean<HisRefundProject> pageBean);

    /**
     * @return int
     * @功能说明 批量删除申请退款项目
     * @Params [recordNumbers]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 12:27
     **/
    int delete(@Param("vouchers") String[] vouchers);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [recordNumber]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 14:02
     **/
    List<HisRefundProject> queryRecordNumber(String recordNumber);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:06
     **/
    List<HisRefundProject> queryHisRefundProject(HisRefundProject hisRefundProject);


    List<HisRefundProject> queryHisRefundProjectVoucher(HisRefundProject hisRefundProject);

    /**
     * @return int
     * @功能说明 退项目申请审核通过
     * @Params [refundProjectList]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 16:15
     **/
    int updateByHisRefundProjectList(List<HisRefundProject> refundProjectList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 16:30
     **/
    List<HisRefundProject> lists(PageBean<HisRefundProject> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 16:30
     **/
    List<HisRefundProject> pricelists(PageBean<HisRefundProject> pageBean);

    /**
     * @return int
     * @功能说明
     * @Params [refundProjectList]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 16:33
     **/
    int updateByHisRefundProjectListBack(List<HisRefundProject> refundProjectList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/24
     * @Time 17:57
     **/
    List<HisRefundProject> queryHisRecordProject(HisRefundProject hisRefundProject);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [voucher]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 16:30
     **/


}