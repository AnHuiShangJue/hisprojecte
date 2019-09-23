package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisProjectMapper;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.services.HisMedicalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.EmptyUtil;

import java.util.List;

/**
 * @program: his
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-17-16-59
 **/
@Service
public class HisMedicalProjectServiceImpl implements HisMedicalProjectService {


    @Autowired
    HisProjectMapper hisProjectMapper;


    @Override
    public List<HisProject> selectForTreatPlan(List<HisRecordProject> detailsList) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(detailsList) && !(detailsList.size() == 0)) {
            Long[] ids = new Long[detailsList.size()];
            int i = 0;
            for (HisRecordProject tmp:
                    detailsList) {
                ids[i] = tmp.getId();
                i++;
            };
            return hisProjectMapper.selectForTreatPlan(ids);
        }else return null;
    }
}

    