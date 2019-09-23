package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisAnkleTemplate;
import core.entity.PageBean;

import java.util.List;

public interface HisAnkleTemplateMapper extends BaseMapper<HisAnkleTemplate>{
    int deleteByPrimaryKey(Long id);

    int insert(HisAnkleTemplate record);

    int insertSelective(HisAnkleTemplate record);

    HisAnkleTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisAnkleTemplate record);

    int updateByPrimaryKey(HisAnkleTemplate record);




    List<? extends HisAnkleTemplate> listWithName(PageBean<HisAnkleTemplate> pageBean);
    List<? extends HisAnkleTemplate> list(String templateNumber);
    List<HisAnkleTemplate> selectListByNumber(String templateNumber);
    List<HisAnkleTemplate> selectTemplate();

    void updateBatch(List<HisAnkleTemplate> hisAnkleTemplateList);


    void deletetemp(String tempNumber);
    void deletetempdetails(Long id);


    /**
     *@功能说明 
     *@Params [hisAnkleTemplate]
     *@return java.util.List<com.ahsj.hiscore.entity.HisAnkleTemplate>
     *@Author XJP
     *@Date 2019/8/8
     *@Time 18:08
    **/
    List<HisAnkleTemplate> queryTranslateInfoByDate(HisAnkleTemplate hisAnkleTemplate);


    /**
     * @param
     * @Description 查询HisAnkleTemplate的所有信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisAnkleTemplate>
     * @Date 2019/8/11
     * @Time 15:42
     **/
    List<HisAnkleTemplate> queryHisAnkleTemplateAll();
}