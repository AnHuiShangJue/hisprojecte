package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisCharge;
import core.entity.PageBean;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/15
 * @Time 10:55
 **/
public interface HisChargeService {
/** className hisChargeService
 *@author dingli
 *@date 2019/7/15 10:55
 * @return
 */
PageBean<HisCharge> getAllHisCharge(PageBean<HisCharge> pageBean) throws Exception;
/**
     *@Description
       * @param null
     *@Author: dingli
     *@return
     *@Date 2019/7/15
     *@Time 16:36
    **/




}
