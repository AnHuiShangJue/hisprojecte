package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisVitalSignsMapper;
import com.ahsj.hiscore.entity.HisVitalSigns;
import com.ahsj.hiscore.services.HisVitalSignsService;
import core.entity.PageBean;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.message.Message;
import utils.EmptyUtil;

@Service
public class HisVitalSignsServiceImpl implements HisVitalSignsService {

    @Autowired
    HisVitalSignsService hisVitalSignsService;

    @Autowired
    HisVitalSignsMapper hisVitalSignsMapper;

    /**
     * @Description 新增或更新
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 9:02
     * @Return sun.plugin2.message.Message
     * @Params [hisVitalSigns]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisVitalSigns hisVitalSigns) throws Exception {
        int a1 = 0,a2 = 0,a3 = 0,a4 = 0,a5 = 0;
        if (EmptyUtil.Companion.isNullOrEmpty(hisVitalSigns.getId())){
            if (Integer.valueOf(hisVitalSigns.getBloodPressure())<= 70) a1 = 3;
            else if (Integer.valueOf(hisVitalSigns.getBloodPressure())>70&&Integer.valueOf(hisVitalSigns.getBloodPressure())<=80) a1 = 2;
            else if (Integer.valueOf(hisVitalSigns.getBloodPressure())>=81&&Integer.valueOf(hisVitalSigns.getBloodPressure())<=100) a1 = 1;
            else if (Integer.valueOf(hisVitalSigns.getBloodPressure())>=101&&Integer.valueOf(hisVitalSigns.getBloodPressure())<=199) a1 = 0;
            else if (Integer.valueOf(hisVitalSigns.getBloodPressure())>=200)a1 = 2;
            if (hisVitalSigns.getBodyTemperature()<=35.0)a2 = 2;
            else if (hisVitalSigns.getBodyTemperature()>=35.1&&hisVitalSigns.getBodyTemperature()<=36)a2 = 1;
            else if (hisVitalSigns.getBodyTemperature()>=36.1&&hisVitalSigns.getBodyTemperature()<=38)a2 = 0;
            else if (hisVitalSigns.getBodyTemperature()>=38.1&&hisVitalSigns.getBodyTemperature()<=38.5)a2 = 1;
            else if (hisVitalSigns.getBodyTemperature()>=38.6)a2 = 2;
            if (hisVitalSigns.getBreathe()<=8)a3 = 2;
            else if (hisVitalSigns.getBreathe()>=9&&hisVitalSigns.getBreathe()<=14)a3 = 0;
            else if (hisVitalSigns.getBreathe()>=15&&hisVitalSigns.getBreathe()<=20)a3 = 1;
            else if (hisVitalSigns.getBreathe()>=21&&hisVitalSigns.getBreathe()<=29)a3 = 2;
            else if (hisVitalSigns.getBreathe()>=30)a3 = 3;
            if (hisVitalSigns.getHeartRate()<=40)a4 = 2;
            else if (hisVitalSigns.getHeartRate()>=41&&hisVitalSigns.getHeartRate()<=50)a4 = 1;
            else if (hisVitalSigns.getHeartRate()>=51&&hisVitalSigns.getHeartRate()<=100)a4 = 0;
            else if (hisVitalSigns.getHeartRate()>=101&&hisVitalSigns.getHeartRate()<=110)a4 = 1;
            else if (hisVitalSigns.getHeartRate()>=111&&hisVitalSigns.getHeartRate()<=130)a4 = 2;
            else if (hisVitalSigns.getHeartRate()>130)a4 = 3;
            if (hisVitalSigns.getConsciousnessLevel()==0)a5 = 0;
            else if (hisVitalSigns.getConsciousnessLevel()==1)a5 =1;
            else if (hisVitalSigns.getConsciousnessLevel()==2)a5 =2;
            else if (hisVitalSigns.getConsciousnessLevel()==3)a5 =3;
            int m = a1+a2+a3+a4+a5;
            hisVitalSigns.setEarlyWarningScore(String.valueOf(m));
        hisVitalSignsMapper.insert(hisVitalSigns);
            return MessageUtil.createMessage(true,"新增成功！");
        }else {
            HisVitalSigns check = hisVitalSignsMapper.selectByPrimaryKey(hisVitalSigns.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(check)){
                hisVitalSigns.setEarlyWarningScore(check.getEarlyWarningScore());
                hisVitalSigns.setHospitalManageId(check.getHospitalManageId());
            hisVitalSignsMapper.updateByPrimaryKey(hisVitalSigns);
            return MessageUtil.createMessage(true,"更新成功！");
            }else {
                return MessageUtil.createMessage(false,"更新失败，该信息不存在！");
            }
        }
    }


    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 9:02
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisVitalSigns>
     * @Params [pageBean]
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisVitalSigns> list(PageBean<HisVitalSigns> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisVitalSignsMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 18:00
     * @Return
     * @Params
    **/

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids){
            hisVitalSignsMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功！");
    }
}
