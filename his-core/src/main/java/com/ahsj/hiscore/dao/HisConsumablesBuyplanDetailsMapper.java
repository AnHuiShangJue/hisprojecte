package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisConsumablesBuyplanDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisConsumablesBuyplanDetails record);

    void insertBatch(@NotNull List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetailsList);


    int insertSelective(HisConsumablesBuyplanDetails record);

    HisConsumablesBuyplanDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisConsumablesBuyplanDetails record);

    int updateByPrimaryKey(HisConsumablesBuyplanDetails record);

    List<HisConsumablesBuyplanDetails> selectByBuyplanId(Long buyplanId);

    List<HisConsumablesBuyplanDetails> details(PageBean<HisConsumablesBuyplanDetails> pageBean);

    List<HisConsumablesBuyplanDetails> selectByBuyplanforList(Long buyplanId);

    int deleteByBuyplanId(Long buyplanId);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     * @功能说明
     * @Params [hisConsumablesBuyplanDetails]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:20
     **/
    List<HisConsumablesBuyplanDetails> queryTranslateInfoByDate(HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 17:02
     **/
    List<HisConsumablesBuyplanDetails> queryAll();
}