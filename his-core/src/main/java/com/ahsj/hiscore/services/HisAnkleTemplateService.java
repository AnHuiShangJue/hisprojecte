package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisAnkleTemplate;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisAnkleTemplateService {


    PageBean<HisAnkleTemplate> listWithName(PageBean<HisAnkleTemplate> pageBean) throws Exception;

    PageBean<HisAnkleTemplate> list(String templateNumber) throws Exception;

    Message saveOrUpdate(HisAnkleTemplate hisAnkleTemplate) throws Exception;


    Message deletetemp(String[] tempNumber) throws Exception;
    Message deletetempdetails(Long[] id) throws Exception;

    HisAnkleTemplate selectByPrimaryId(Long id) throws Exception;

    List<HisAnkleTemplate> selectTemplate() throws Exception;

    List<HisAnkleTemplate> selectAllTemplate(String templateNumber) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisAnkleTemplate>
     * @功能说明
     * @Params [hisAnkleTemplate]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:06
     **/
    List<HisAnkleTemplate> queryTranslateInfoByDate(HisAnkleTemplate hisAnkleTemplate) throws Exception;

    /**
     * @param
     * @Description 查询HisAnkleTemplate所有信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisAnkleTemplate>
     * @Date 2019/8/11
     * @Time 15:44
     **/
    List<HisAnkleTemplate> queryAll() throws Exception;

}
