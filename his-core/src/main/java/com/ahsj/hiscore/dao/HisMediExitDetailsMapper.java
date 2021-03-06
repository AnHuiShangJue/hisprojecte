package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.HisMediExitDetails;
import com.ahsj.hiscore.entity.HisWard;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisMediExitDetailsMapper extends BaseMapper<HisMediExitDetails> {
    int deleteByPrimaryKey(Long id);

    int insert(HisMediExitDetails record);

    int insertSelective(HisMediExitDetails record);

    HisMediExitDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMediExitDetails record);

    int updateByPrimaryKey(HisMediExitDetails record);

    /**
     * @return void
     * @Description 批量插入
     * @Params [hisMediExitDetailsList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:36
     **/
    void insertBatch(@NotNull List<HisMediExitDetails> hisMediExitDetailsList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Description 根据就诊编号+有效期+药品编号找回出库时的ID
     * @Params [hisMediExitDetails]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:36
     **/
    List<HisMediExitDetails> selectByRecordNumberAndDrugsnumbAndValidityPeriod(HisMediExitDetails hisMediExitDetails);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Description 根据IDs数组查找
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:37
     **/
    List<HisMediExitDetails> listByIds(Long[] ids);

    /**
     * @Description
     * @Params: [ids]  查询历史出药
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/9/16
     * @Time 9:46
     **/
    List<HisMediExitDetails> listByIdsHistory(Long[] ids);

    /**
     * @Description 药品出库记录
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMediEnterDetails>
     * @Date 2019/10/6
     * @Time 15:15
     **/
    List<HisMediExitDetails> getAll(PageBean<HisMediExitDetails> pageBean) throws Exception;

    /**
     * @Description 药品出库记录明细
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/10/6
     * @Time 16:32
     **/
    List<HisMediExitDetails> getAllMediExitDetail(PageBean<HisMediExitDetails> pageBean) throws Exception;


    List<HisMediExitDetails> getRemovalBytollNumber(String tollNumber);


}