package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisDieintwentyfour;
import core.message.Message;

public interface HisDieintwentyfourService {


    /**
     *@Description 24小时内死亡记录
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/15
     *@Time 17:36
    */
    Message save(HisDieintwentyfour hisDieintwentyfour) throws Exception;

}
