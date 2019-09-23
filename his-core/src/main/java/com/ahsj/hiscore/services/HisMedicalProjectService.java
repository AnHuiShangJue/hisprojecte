package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.HisRecordProject;

import java.util.List;

/**
 * @program: his
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-17-16-22
 **/
public interface HisMedicalProjectService {

    /**
     * @param detailsList
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 根据用药明细的id查询药房药品
     * @Params [detailsList]
     * @Author chenzhicai
     * @Date 2019-07-11
     * @Time 17:39
     */
    List<HisProject> selectForTreatPlan(List<HisRecordProject> detailsList) throws Exception;
}
