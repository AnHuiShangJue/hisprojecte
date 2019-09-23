package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedirecordProject;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/18
 * @Time 9:11
 **/
public interface HisMedirecordProjectService {
/** className HisMedirecordProjectService
 *@author dingli
 *@date 2019/7/18 9:11
 */
/**
     *@Description 根据id将未付改成已付
       * @param record
     *@Author: dingli
     *@return
     *@Date 2019/7/18
     *@Time 9:14
    **/
int updateHisMedirecordProject(HisMedirecordProject record) throws Exception;
}
