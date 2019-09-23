package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRelatedMedicationandexit;

import java.util.List;

public interface HisRelatedMedicationandexitService {
    /**
     *@Description 批量新增用药明细与出库关联表
     *@Params [hisRelatedMedicationandexitList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-25
     *@Time 17:04
    **/
    void saveForMultiple(List<HisRelatedMedicationandexit> hisRelatedMedicationandexitList)throws Exception;
}
