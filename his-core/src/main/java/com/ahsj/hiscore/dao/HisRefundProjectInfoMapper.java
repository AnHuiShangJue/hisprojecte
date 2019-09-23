package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRefundProjectInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisRefundProjectInfoMapper extends BaseMapper<HisRefundProjectInfo> {

    int deleteByPrimaryKey(Long id);

    int insert(HisRefundProjectInfo hisRefundProjectInfo);

    int insertSelective(HisRefundProjectInfo hisRefundProjectInfo);

    HisRefundProjectInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRefundProjectInfo hisRefundProjectInfo);

    int updateByPrimaryKey(HisRefundProjectInfo hisRefundProjectInfo);

    /**
     * @return com.ahsj.hiscore.entity.HisRefundProjectInfo
     * @功能说明
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 14:27
     **/
    HisRefundProjectInfo queryHisRefundProjectInfo(HisRefundProjectInfo hisRefundProjectInfo);

    /**
     * @return java.lang.Object
     * @功能说明 查看退项目明细
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 10:42
     **/
    List<HisRefundProjectInfo> list(PageBean<HisRefundProjectInfo> pageBean);

    /**
     *@功能说明
     *@Params
     *@return
     *@Author XJP
     *@Date 2019/8/26
     *@Time 17:40
    **/
    List<HisRefundProjectInfo> printlylist(HisRefundProjectInfo hisRefundProjectInfo);
}