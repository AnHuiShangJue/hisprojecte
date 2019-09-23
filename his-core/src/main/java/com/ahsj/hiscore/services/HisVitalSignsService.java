package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisVitalSigns;
import core.entity.PageBean;
import core.message.Message;
public interface HisVitalSignsService {

    /**
     * @Description 新增或更新
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 8:57
     * @Return
     * @Params
    **/
    Message saveOrUpdate(HisVitalSigns hisVitalSigns)throws Exception;

    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 8:59
     * @Return
     * @Params
    **/

    PageBean<HisVitalSigns> list(PageBean<HisVitalSigns> pageBean)throws Exception;

    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 18:00
     * @Return core.message.Message
     * @Params [hisVitalSigns]
    **/
    Message delete (Long[] ids)throws Exception;

}
