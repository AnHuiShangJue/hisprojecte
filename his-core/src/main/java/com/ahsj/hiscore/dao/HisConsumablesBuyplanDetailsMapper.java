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

    /**
     *@Description
     *@MethodName selectByBuyplanId
     *@Params [buyplanId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:36
    **/
    List<HisConsumablesBuyplanDetails> selectByBuyplanCode(String buyplanCode);

    /**
     *@Description 
     *@MethodName details
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:00
    **/
    List<HisConsumablesBuyplanDetails> details(PageBean<HisConsumablesBuyplanDetails> pageBean);

    /**
     *@Description
     *@MethodName selectByBuyplanforList
     *@Params [buyplanId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 17:41
    **/
    List<HisConsumablesBuyplanDetails> selectByBuyplanforList(String buyplanCode);

    int deleteByBuyplanCode(String buyplanCode);

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

    /**
     *@Description
     *@MethodName updateByIsDelete
     *@Params [id]
     *@return int
     *@Author XJP
     *@Date 2020/4/24
     *@Time 16:41
    **/
    int updateByIsDelete(Long id);

    /**
     *@Description
     *@MethodName updateByIsDeleteCode
     *@Params [buyplanCode]
     *@return int
     *@Author XJP
     *@Date 2020/4/24
     *@Time 17:32
    **/
    int updateByIsDeleteCode(String buyplanCode);
}