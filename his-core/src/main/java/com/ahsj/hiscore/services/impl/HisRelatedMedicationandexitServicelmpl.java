package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisRelatedMedicationandexitMapper;
import com.ahsj.hiscore.entity.HisRelatedMedicationandexit;
import com.ahsj.hiscore.services.HisRelatedMedicationandexitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HisRelatedMedicationandexitServicelmpl implements HisRelatedMedicationandexitService {
    @Autowired
    HisRelatedMedicationandexitMapper hisRelatedMedicationandexitMapper;
    /**
     *@Description 批量新增用药明细与出库关联表
     *@Params [hisRelatedMedicationandexitList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-25
     *@Time 17:05
    **/
    @Override
    @Transactional(readOnly = false)
    public void saveForMultiple(List<HisRelatedMedicationandexit> hisRelatedMedicationandexitList) throws Exception {
        hisRelatedMedicationandexitMapper.insertBatch(hisRelatedMedicationandexitList);
    }
}
