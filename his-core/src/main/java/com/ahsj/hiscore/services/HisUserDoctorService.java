package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisUserDoctor;

public interface HisUserDoctorService {
    /**
     * @Description 删除
     * @Author   muxu
     * @Date 2019/8/8
     * @Time 10:55
     * @Return
     * @Params
     **/
    HisUserDoctor selectByDoctorId (Long id) throws Exception;

    /**
     * @Description 删除
     * @Author   muxu
     * @Date 2019/8/8
     * @Time 10:55
     * @Return
     * @Params
     **/
    HisUserDoctor selectByUserId(Long id) throws Exception;
}
