package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisEnterConsumablesDetails;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Mapper
public interface HisEnterConsumablesDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisEnterConsumablesDetails record);

    int insertBatch(@NotNull List<HisEnterConsumablesDetails> hisConsumablesBuyplanList);


    int insertSelective(HisEnterConsumablesDetails record);

    HisEnterConsumablesDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisEnterConsumablesDetails record);

    int updateByPrimaryKey(HisEnterConsumablesDetails record);

    int selectValidityPeriodById(Date validityPeriod);

    List<HisEnterConsumablesDetails> selectByConsumablesId(Long consumablesId);

    List<HisEnterConsumablesDetails> selectByConsuma(Long consumablesId);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisEnterConsumablesDetails>
     * @Description
     * @MethodName selectByEnterConsumablesDetailsCode
     * @Params [enterCdoe]
     * @Author XJP
     * @Date 2020/4/25
     * @Time 11:36
     **/
    HisEnterConsumablesDetails selectByEnterConsumablesDetailsCode(String enterCdoe);

    List<HisEnterConsumablesDetails> selectByConsumablesIdOrder(String consumablesId);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisEnterConsumablesDetails>
     * @Description
     * @MethodName list
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/27
     * @Time 10:36
     **/
    List<HisEnterConsumablesDetails> list(PageBean<HisEnterConsumablesDetails> pageBean);


    HisEnterConsumablesDetails selectByConsumablesAndValidityPeriod(Long consumablesId, Date validityPeriod);

    /**
     * @return com.ahsj.hiscore.entity.HisEnterConsumablesDetails
     * @Description
     * @MethodName selectByConsumablesCode
     * @Params [consumablesCode]
     * @Author XJP
     * @Date 2020/4/26
     * @Time 11:10
     **/
    List<HisEnterConsumablesDetails> selectByConsumablesCode(String consumablesCode);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisEnterConsumablesDetails>
     * @Description 获取耗材信息和单种耗材数量
     * @MethodName queryByconsumablesCode
     * @Params [consumablesCode]
     * @Author XJP
     * @Date 2020/4/26
     * @Time 17:52
     **/
    HisEnterConsumablesDetails queryByconsumablesCode(String consumablesCode);

    /**
     * @return int
     * @Description
     * @MethodName updateByIsDelete
     * @Params [hisEnterConsumablesDetailsList]
     * @Author XJP
     * @Date 2020/4/26
     * @Time 12:33
     **/
    int updateByIsDelete(String consumablesCode);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisEnterConsumablesDetails>
     * @Description
     * @MethodName onelist
     * @Params [consumablesCode]
     * @Author XJP
     * @Date 2020/4/26
     * @Time 15:54
     **/
    List<HisEnterConsumablesDetails> onelist(String consumablesCode);
}