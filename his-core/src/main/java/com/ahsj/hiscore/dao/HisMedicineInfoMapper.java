package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedicineInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HisMedicineInfoMapper extends BaseMapper<HisMedicineInfo> {


    HisMedicineInfo selectForInsert(HisMedicineInfo hisMedicineInfo);

    void setDisable(@Param("list") List<HisMedicineInfo> list, @Param("disable") int disable);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @Description 根据IDS查找
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:34
     **/
    List<HisMedicineInfo> selectByIds(Long[] ids);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @Description 分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:34
     **/
    List<HisMedicineInfo> list(PageBean<HisMedicineInfo> pageBean);


    List<HisMedicineInfo> listandenglish();

    /**
     * @return com.ahsj.hiscore.entity.HisMedicineInfo
     * @Description 根据药品编号查找
     * @Params [drugsNumb]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:34
     **/
    HisMedicineInfo selectByDrugsNumb(String drugsNumb);

    /**
     *@Description 根据药品名称和规格同时查找（两者在数据库中同时存在才能查到记录）
     *@Params [hisMedicineInfo]
     *@return com.ahsj.hiscore.entity.HisMedicineInfo
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:34
    **/
    HisMedicineInfo selectByDrugsNameAndSpec(HisMedicineInfo hisMedicineInfo);

    /**
     *@Description 设置停用状态反转
     *@Params [id]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:35
    **/
    void setDisableReversal(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(HisMedicineInfo hisMedicineInfo);

    int insertSelective(HisMedicineInfo hisMedicineInfo);

    HisMedicineInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicineInfo hisMedicineInfo);

    int updateByPrimaryKey(HisMedicineInfo hisMedicineInfo);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:31
     **/
    List<HisMedicineInfo> queryTranslateInfoByDate(HisMedicineInfo hisMedicineInfo);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 17:08
     **/
    List<HisMedicineInfo> queryAll();

    List<HisMedicineInfo> selectAll();

    List<HisMedicineInfo> queryListExportAll();

    List<HisMedicineInfo> queryListExportAndByIdsAll(List<HisMedicineInfo> list);

    List<HisMedicineInfo> queryMedicineInfoAll();

    int importHisMedicineInfo(List<HisMedicineInfo> medicineinfoInsertList);

    HisMedicineInfo queryHisMedicineInfoByDrugsNumb(String drugsNumb);

    int updateHisMedicineInfo(List<HisMedicineInfo> medicineinfoUpdateList);

    HisMedicineInfo queryForCode();

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @Description 根据导入时传来的条件搜索出对应的药品信息
     * @Params [hisMedicineInfo]
     * @Author zhushixiang
     * @Date 2019-09-02
     * @Time 15:11
     **/
    List<HisMedicineInfo> selectByExportExcel(HisMedicineInfo hisMedicineInfo);
}