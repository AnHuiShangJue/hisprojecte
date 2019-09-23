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
    void insertBatch(@NotNull List<HisEnterConsumablesDetails> hisConsumablesBuyplanList);


    int insertSelective(HisEnterConsumablesDetails record);

    HisEnterConsumablesDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisEnterConsumablesDetails record);

    int updateByPrimaryKey(HisEnterConsumablesDetails record);

    int selectValidityPeriodById(Date validityPeriod);

    List<HisEnterConsumablesDetails> selectByConsumablesId(Long consumablesId);
    List<HisEnterConsumablesDetails> onelist(Long consumablesId);
    List<HisEnterConsumablesDetails> selectByConsumablesIdOrder(Long consumablesId);


    List<? extends HisEnterConsumablesDetails> list(PageBean<HisEnterConsumablesDetails> pageBean);



    HisEnterConsumablesDetails selectByConsumablesAndValidityPeriod(Long consumablesId,Date validityPeriod);


}