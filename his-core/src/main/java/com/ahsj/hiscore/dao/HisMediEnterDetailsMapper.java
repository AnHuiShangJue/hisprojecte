package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisMediEnterDetailsMapper extends BaseMapper<HisMediEnterDetails> {
    int deleteByPrimaryKey(Long id);

    int insert(HisMediEnterDetails record);

    int insertSelective(HisMediEnterDetails record);

    HisMediEnterDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMediEnterDetails record);

    int updateByPrimaryKey(HisMediEnterDetails record);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMediEnterDetails>
     * @Description 根据药品ID查找
     * @Params [pharmacyId]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:35
     **/
    List<HisMediEnterDetails> selectByPharmacyId(Long pharmacyId);

    /**
     * @return java.util.List<? extends com.ahsj.hiscore.entity.HisMediEnterDetails>
     * @Description 分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:35
     **/
    List<? extends HisMediEnterDetails> list(PageBean<HisMediEnterDetails> pageBean);


    List<? extends HisMediEnterDetails> listOverdate(PageBean<HisMediEnterDetails> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMediEnterDetails>
     * @Description 根据药品ID查找且排序
     * @Params [pharmacyId]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:35
     **/
    List<HisMediEnterDetails> selectByPharmacyIdOrder(Long pharmacyId);

    /**
     * @return java.util.List<? extends com.ahsj.hiscore.entity.HisMediEnterDetails>
     * @Description 根据药品ID查找
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:35
     **/
    List<? extends HisMediEnterDetails> listByPharmacyId(PageBean<HisMediEnterDetails> pageBean);

    /**
     * @return void
     * @Description 批量插入
     * @Params [hisMediEnterDetailsList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:35
     **/
    void insertBatch(@NotNull List<HisMediEnterDetails> hisMediEnterDetailsList);

    List<HisMediEnterDetails> selectByDrugsNumbAndValidity(HisMediEnterDetails hisMediEnterDetails);

    /**
     * @return void
     * @Description 批量更新
     * @Params [hisMediEnterDetailsList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:37
     **/
    void updateBatch(List<HisMediEnterDetails> hisMediEnterDetailsList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMediEnterDetails>
     * @Description 搜索出已过期的药品
     * @Params []
     * @Author zhushixiang
     * @Date 2019-08-02
     * @Time 10:55
     **/
    List<HisMediEnterDetails> selectByDate();


}
