package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisConsumables;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Mapper
public interface HisConsumablesMapper extends BaseMapper<HisConsumables> {

    HisConsumables selectForInsert(HisConsumables hisConsumables);

    HisConsumables selectNameAndSpecById(HisConsumables hisConsumables);

    void setDisable(@Param("list") List<HisConsumables> list, @Param("isEnable") int isEnable);

    List<HisConsumables> selectByIds(Long[] ids);

    int deleteByPrimaryKey(Long id);

    int insert(HisConsumables hisConsumables);

    int insertSelective(HisConsumables hisConsumables);

    HisConsumables selectByPrimaryKey(Long id);


    HisConsumables selectByNameandSpec(HisConsumables hisConsumables);


    int updateByPrimaryKeySelective(HisConsumables hisConsumables);

    int updateByPrimaryKey(HisConsumables hisConsumables);


//    void updateStockById(Long id);

    List<HisConsumables> list(PageBean<HisConsumables> pageBean);

    List<HisConsumables> listAll();

    int importConsumables(List<HisConsumables> hisConsumablesList);

    int updateConsumables(List<HisConsumables> hisConsumablesList);


    List<HisConsumables> listEnable(PageBean<HisConsumables> pageBean);

    Double selectPriceById(Long id);

    Double selectSpecById(Long id);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumables>
     * @功能说明
     * @Params [hisConsumables]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:28
     **/
    List<HisConsumables> queryTranslateInfoByDate(HisConsumables hisConsumables);


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumables>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 17:03
     **/
    List<HisConsumables> queryAll();


    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/9/4
     * @Time 15:39
     */
    List<HisConsumables> selectByExportExcel(HisConsumables hisMedicineInfo);

    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/9/4
     * @Time 15:45
     */
    List<HisConsumables> queryListExportAll();

    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/9/4
     * @Time 15:57
     */
    List<HisConsumables> queryListExportAndByIdsAll(List<HisConsumables> list);


}