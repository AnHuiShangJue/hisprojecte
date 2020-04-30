package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisConsumablesDetails;
import com.ahsj.hiscore.entity.HisEnterConsumablesDetails;
import com.ahsj.hiscore.entity.HisExitConsumablesDetails;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisConsumablesDetailsMapper extends BaseMapper<HisConsumablesDetails> {
    int deleteByPrimaryKey(Long id);

    int insert(HisConsumablesDetails record);

    void insertBatch(@NotNull List<HisConsumablesDetails> HisConsumablesDetailsList);


    int insertSelective(HisConsumablesDetails record);

    HisConsumablesDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisConsumablesDetails record);

    int updateByPrimaryKey(HisConsumablesDetails record);


    int stockwarnupdate(Integer stockWarn, Long consumablesId);

    /**
     * @return com.ahsj.hiscore.entity.HisConsumablesDetails
     * @Description
     * @MethodName selectConsumablesCode
     * @Params [consumablesCode]
     * @Author XJP
     * @Date 2020/4/25
     * @Time 11:31
     **/
    HisConsumablesDetails selectConsumablesCode(String consumablesCode);

    void updateAddStockById(HisEnterConsumablesDetails hisEnterConsumablesDetails);

    int selectStockById(Long id);


    List<HisConsumablesDetails> list(PageBean<HisConsumablesDetails> pageBean);

    List<HisConsumablesDetails> onelist(PageBean<HisConsumablesDetails> pageBean);

    void updateReduceStockById(Long id);

    List<HisConsumablesDetails> selectByConsumablesId(Long id);

    HisConsumablesDetails selectByConsumablesIdAndVali(HisExitConsumablesDetails hisExitConsumablesDetails);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 18:04
     **/
    List<HisConsumablesDetails> getHisConsumablesDetailsInfo(PageBean<HisConsumablesDetails> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/15
     * @Time 20:59
     **/
    List<HisConsumablesDetails> getHisConsumablesDetailsGroupBy(PageBean<HisConsumablesDetails> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [hisConsumablesDetails]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:25
     **/
    List<HisConsumablesDetails> queryTranslateInfoByDate(HisConsumablesDetails hisConsumablesDetails);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 16:32
     **/
    List<HisConsumablesDetails> queryAll();

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/14
     * @Time 9:56
     **/
    List<HisConsumablesDetails> lists(PageBean<HisConsumablesDetails> pageBean);
}