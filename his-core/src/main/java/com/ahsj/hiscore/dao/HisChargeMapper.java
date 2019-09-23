package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisCharge;
import core.entity.PageBean;

import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/13
 * @Time 20:13
 **/
public interface HisChargeMapper {
   // List<HisCharge> getAllHisCharge(String medicalRecordId);
    /**  
         *@Description 
           * @param null
         *@Author: dingli
         *@return    
         *@Date 2019/7/15
         *@Time 9:29
        **/
    List<HisCharge> getAllHisCharge(PageBean<HisCharge> pageBean) throws Exception;
}
