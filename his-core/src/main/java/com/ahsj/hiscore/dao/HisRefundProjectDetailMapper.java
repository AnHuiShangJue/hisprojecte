package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRefundProjectDetail;
import core.entity.PageBean;

import java.util.List;

public interface HisRefundProjectDetailMapper extends BaseMapper<HisRefundProjectDetail> {

    int deleteByPrimaryKey(Long id);

    int insert(HisRefundProjectDetail hisRefundProjectDetail);

    int insertSelective(HisRefundProjectDetail hisRefundProjectDetail);

    HisRefundProjectDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRefundProjectDetail hisRefundProjectDetail);

    int updateByPrimaryKey(HisRefundProjectDetail hisRefundProjectDetail);

    /**
     * @return int
     * @功能说明
     * @Params [hisRefundProjectDetails]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 16:46
     **/
    int insertHisRefundProjectDetailList(List<HisRefundProjectDetail> hisRefundProjectDetails);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProjectDetail>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:02
     **/
    List<HisRefundProjectDetail> projectInfoDetail(PageBean<HisRefundProjectDetail> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundProjectDetail>
     * @功能说明
     * @Params [hisRefundProjectDetail]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:02
     **/
    List<HisRefundProjectDetail> HisRecordProjectLists(HisRefundProjectDetail hisRefundProjectDetail);
}