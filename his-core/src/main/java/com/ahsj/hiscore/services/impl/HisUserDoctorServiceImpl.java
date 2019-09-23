package com.ahsj.hiscore.services.impl;


import com.ahsj.hiscore.dao.HisUserDoctorMapper;
import com.ahsj.hiscore.entity.HisUserDoctor;
import com.ahsj.hiscore.services.HisUserDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HisUserDoctorServiceImpl implements HisUserDoctorService {

    @Autowired
    HisUserDoctorMapper hisUserDoctorMapper;



    @Override
    public HisUserDoctor selectByDoctorId(Long id) throws Exception {
        return hisUserDoctorMapper.selectByDoctorId(id);
    }

    @Override
    public HisUserDoctor selectByUserId(Long id) throws Exception {
        return hisUserDoctorMapper.selectByUserId(id);
    }
}
