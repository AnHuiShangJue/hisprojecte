package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisHosptalregist;
import core.message.Message;

public interface HisHosptalregistService {
    /**
         *@Description
           * @param hisHosptalregist
         *@Author: dingli
         *@return
         *@Date 2019/7/13
         *@Time 15:42
        **/

    Message saveOrUpdate(HisHosptalregist hisHosptalregist) throws Exception;


    /**
     * @Description select
     * @Author   muxu
     * @Date 2019/7/11
     * @Time 9:41
     * @Return
     * @Params
    **/


    HisHosptalregist selectById(Long id) throws Exception ;
    
    /**
     * @Description isfail
     * @Author   muxu
     * @Date 2019/7/17
     * @Time 18:27
     * @Return void
     * @Params [id]
    **/
    HisHosptalregist insertIsFail(Long id) throws Exception;
}
