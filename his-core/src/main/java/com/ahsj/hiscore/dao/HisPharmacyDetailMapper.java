package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.HisMediExitDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisPharmacyDetailMapper extends BaseMapper<HisPharmacyDetail> {
    int deleteByPrimaryKey(Long id);

    int insert(HisPharmacyDetail record);

    int insertSelective(HisPharmacyDetail record);

    HisPharmacyDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisPharmacyDetail record);

    int updateByPrimaryKey(HisPharmacyDetail record);

    /**
     * @return java.util.List<? extends com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:38
     **/
    List<? extends HisPharmacyDetail> list(PageBean<HisPharmacyDetail> pageBean);

    HisPharmacyDetail selectByDrugsNumb(String drugsNumb);//根据药品编号查找

    HisPharmacyDetail selectByDrugsNameAndSpec(HisPharmacyDetail hisPharmacyDetail);//根据药品名称和规格同时查找（两者在数据库中同时存在才能查到记录）

    void updateAddStockById(HisMediEnterDetails hisMediEnterDetails);

    HisPharmacyDetail selectByPharmacyId(Long pharmacyId);//根据药品ID返回药库中与此药品ID相同的所有药品

    /**
     * @return com.ahsj.hiscore.entity.HisPharmacyDetail
     * @Description 根据药品ID和有效期查找
     * @Params [hisMediExitDetails]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:39
     **/
    HisPharmacyDetail selectByPharmacyIdAndVali(HisMediExitDetails hisMediExitDetails);

    List<? extends HisPharmacyDetail> listDetails(PageBean<HisPharmacyDetail> pageBean);//查看药品详细信息

    List<HisPharmacyDetail> selectForStock();//查询出药库中所有库存小于预警值的药品

    /**
     * @return void
     * @Description 批量插入
     * @Params [hisPharmacyDetailList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:39
     **/
    void insertBatch(@NotNull List<HisPharmacyDetail> hisPharmacyDetailList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:39
     **/
    List<HisPharmacyDetail> selectForTreatPlan(Long[] ids);

    /**
     * @return void
     * @Description 批量更新
     * @Params [hisPharmacyDetailList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:39
     **/
    public void updateBatch(List<HisPharmacyDetail> hisPharmacyDetailList);

    /**
     * @return void
     * @Description 批量更新库存数目
     * @Params [hisPharmacyDetailList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:39
     **/
    void updateBatchForStock(List<HisPharmacyDetail> hisPharmacyDetailList);

    /**
     * @return java.util.List<? extends com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description
     * @Params [pharmacyDetailPageBean]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:39
     **/
    List<? extends HisPharmacyDetail> listForMedication(PageBean<HisPharmacyDetail> pharmacyDetailPageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 查询所有  导出
     * @Params []
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:40
     **/
    List<HisPharmacyDetail> queryListExportAll();

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 根据IDs条件查询 导出
     * @Params [list]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:40
     **/
    List<HisPharmacyDetail> queryListExportAndByIdsAll(List<HisPharmacyDetail> list);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description
     * @Params []
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:40
     **/
    List<HisPharmacyDetail> queryPharmacyAll();

    /**
     * @return int
     * @Description
     * @Params [pharmacyInsertList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:40
     **/
    int importHisPharmacyDetail(List<HisPharmacyDetail> pharmacyInsertList);

    /**
     * @return int
     * @Description
     * @Params [pharmacyUpdateList]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:44
     **/
    int updateHisPharmacyDetail(List<HisPharmacyDetail> pharmacyUpdateList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 根据导入时传来的条件搜索出对应的药库药品信息
     * @Params [hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-09-02
     * @Time 16:36
     **/
    List<HisPharmacyDetail> selectByExportExcel(HisPharmacyDetail hisPharmacyDetail);

    /**
     * @Description  药库盘点
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Date 2019/9/18
     * @Time 17:59
     **/
    List<HisPharmacyDetail> pharmacyInventory(PageBean<HisPharmacyDetail> pageBean);

    /**
     *@Description 查询出未停用未下架的药品
     *@Params [pharmacyDetailPageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 18:18
    **/
    List<?extends HisPharmacyDetail> listForIsDisableAndObtained(PageBean<HisPharmacyDetail> pharmacyDetailPageBean);

    /**
     *@Description 根据IDs查询药库药品信息
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-10-05
     *@Time 14:58
    **/
    List<HisPharmacyDetail> selectForListForMedicationByIds(Long[] ids);

    //模糊查询（or）
    List<? extends HisPharmacyDetail> listForAll(PageBean<HisPharmacyDetail> pharmacyDetailPageBean);
}
