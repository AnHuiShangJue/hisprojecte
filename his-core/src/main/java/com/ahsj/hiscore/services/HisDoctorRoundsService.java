package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisDoctorRounds;
import core.message.Message;

public interface HisDoctorRoundsService {


    /**
     *@Description saveOrUpdate
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/16
     *@Time 18:08
    */

    Message saveOrUpdate(HisDoctorRounds hisDoctorRounds)throws Exception;


    Message sign(HisDoctorRounds hisDoctorRounds)throws Exception;




}
