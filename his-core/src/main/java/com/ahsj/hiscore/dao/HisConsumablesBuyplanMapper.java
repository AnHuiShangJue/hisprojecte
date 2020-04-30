package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisConsumablesBuyplan;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisConsumablesBuyplanMapper extends BaseMapper<HisConsumablesBuyplan>{
    int deleteByPrimaryKey(Long id);

    int insert(HisConsumablesBuyplan record);

    void insertBatch(@NotNull List<HisConsumablesBuyplan> hisConsumablesBuyplanList);


    int insertSelective(HisConsumablesBuyplan record);

    HisConsumablesBuyplan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisConsumablesBuyplan record);

    int updateByPrimaryKey(HisConsumablesBuyplan record);

    /**
     *@Description 
     *@MethodName list
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplan>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 12:59
    **/
    List<HisConsumablesBuyplan> list(PageBean<HisConsumablesBuyplan> pageBean);

    /**
     *@Description 
     *@MethodName selectBybuyplanCode
     *@Params [buyplanCode]
     *@return com.ahsj.hiscore.entity.HisConsumablesBuyplan
     *@Author XJP
     *@Date 2020/4/24
     *@Time 14:43
    **/
    HisConsumablesBuyplan selectBybuyplanCode(String buyplanCode);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplan>
     * @功能说明
     * @Params [hisConsumablesBuyplan]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:22
     **/
    List<HisConsumablesBuyplan> queryTranslateInfoByDate(HisConsumablesBuyplan hisConsumablesBuyplan);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplan>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 20:17
     **/
    List<HisConsumablesBuyplan> queryAll();

    /**
     *@Description
     *@MethodName updateByIsDelete
     *@Params [hisConsumablesBuyplan]
     *@return int
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:21
    **/
    int updateByIsDelete(HisConsumablesBuyplan hisConsumablesBuyplan);
}