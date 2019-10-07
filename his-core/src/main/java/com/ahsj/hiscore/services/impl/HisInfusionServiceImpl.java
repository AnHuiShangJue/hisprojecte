package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisInfusionMapper;
import com.ahsj.hiscore.dao.HisMedicationDetailsMapper;
import com.ahsj.hiscore.entity.HisInfusion;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.services.HisInfusionService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class HisInfusionServiceImpl implements HisInfusionService {
    private static Logger log = LoggerFactory.getLogger(HisInfusionService.class);
    @Autowired
    HisInfusionMapper hisInfusionMapper;

    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;
    /**
     * @Description 新增或更新
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:26
     * @Return core.message.Message
     * @Params [HisInfusionController, request]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisInfusion hisInfusion) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisInfusion.getId())){
            HisInfusion check = hisInfusionMapper.selectByPrimaryKey(hisInfusion.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(check)){
            hisInfusionMapper.updateByPrimaryKey(hisInfusion);
            return MessageUtil.createMessage(true,"更新成功！");}
            else {
                return MessageUtil.createMessage(false,"更新失败，数据异常请联系管理员！");
            }
        }else {
        hisInfusionMapper.insert(hisInfusion);
        return MessageUtil.createMessage(true,"新增成功！");
        }
    }


    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:27
     * @Return core.message.Message
     * @Params [ids]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id:ids){
            hisInfusionMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功！");
    }

    @Override
    @Transactional(readOnly = false)
    public Message deleteByNumber(String number) throws Exception {
        hisInfusionMapper.deleteByNumber(number);
        return MessageUtil.createMessage(true,"删除成功！");
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> selectByRecordIdList(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.selectByRecordId(pageBean)));
        return pageBean;
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/8/31
     * @Time 16:33
     * @Return core.message.Message
     * @Params [hisInfusionList]
    **/

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> infusionDrugDetailsList(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.infusionDrugDetailsList(pageBean)));
        return pageBean;
    }



    @Override
    @Transactional(readOnly = false)
    public Message save(List<HisInfusion> hisInfusionList) throws Exception {
        if (hisInfusionList.size() > 0){

            for (int i = 0; i < hisInfusionList.size(); i++) {
                HisMedicationDetails hisMedicationDetails =hisMedicationDetailsMapper.selectByPrimaryKey(Long.valueOf(hisInfusionList.get(i).getDrugsNumb()));

                //取到药品信息后set到hisInfustion中
                hisInfusionList.get(i).setDrugname(hisMedicationDetails.getDrugsName());
                hisInfusionList.get(i).setUnit(hisMedicationDetails.getSmallUnit());
                hisInfusionList.get(i).setDosage(hisMedicationDetails.getCount().toString());
                hisInfusionList.get(i).setPrice(hisMedicationDetails.getPrice());

                //更新药品信息中的alreadyout
//                int a = 0;
////                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails.getAlreadyout())){
////                    a = hisMedicationDetails.getAlreadyout();
////                }
////                int single = Integer.valueOf(hisInfusionList.get(i).getSingleDose());
////                hisMedicationDetails.setAlreadyout(a+ single);
////                hisMedicationDetailsMapper.updateAlreadyout(hisMedicationDetails);

            }
            hisInfusionMapper.insertBatch(hisInfusionList);

            return MessageUtil.createMessage(true,"保存成功");



        }else {
            return MessageUtil.createMessage(false,"未选择药品");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> listByHM(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByHM(pageBean)));
        return pageBean;
    }


    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> listAllByNumber(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listAllByNumber(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> listByNumbers(String[] ids) throws Exception {
        List<HisInfusion> hisInfusionList = new ArrayList<HisInfusion>();
        for (int i = 0; i < ids.length; i++) {
            hisInfusionList.addAll(hisInfusionMapper.selectByNumber(ids[i]));
        }
        return CodeHelper.getInstance().setCodeValue(hisInfusionList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> listByHMForPrint(String Hm) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByHMForPrint(Hm));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> listByRemarkForPrint(String remark) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByRemarkForPrint(remark));
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/3
     * @Time 17:12
     * @Return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     * @Params [Hm]
    **/
    @Override
    public List<HisInfusion> listByRecordForPrint(String recordId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByRecordForPrint(recordId));
    }

    /**
     *@Description 根据输液单编号查找
     *@Params [infusionNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 16:28
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> selectByNumber(String infusionNumber) throws Exception {
        List<HisInfusion> hisInfusionList = hisInfusionMapper.selectByNumber(infusionNumber);
        return hisInfusionMapper.selectByNumber(infusionNumber);
    }

    /**
     *@Description 根据输液单编号查找（为住院输液单服务）
     *@Params [number]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 23:39
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> listByHMForHospitalPrint(String number) {
        if(EmptyUtil.Companion.isNullOrEmpty(hisInfusionMapper.listByHMForHospitalPrint(number)))
            return new ArrayList<>();
        return CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByHMForHospitalPrint(number));
    }

    /**
     *@Description 保存门诊所开的输液单
     *@Params [ids, dosages, usages, startTime]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 8:32
    **/
    @Override
    @Transactional(readOnly = false)
    public Message addInfusionMedicine(List<HisInfusion> hisInfusionList,Long recordId) throws Exception {
        String name="SYD";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String createdate = sdf.format(date);
        List<HisInfusion> saveList = new ArrayList<>();
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectById(recordId);
        List<HisInfusion> checkInfusionList = hisInfusionMapper.selectByRecordNumberAndNotPay(hisMedicalRecord.getMedicalRecordId());
        if(!EmptyUtil.Companion.isNullOrEmpty(checkInfusionList) && checkInfusionList.size() > 0) {
            for (HisInfusion hisInfusion : checkInfusionList) {
                hisInfusionMapper.deleteByPrimaryKey(hisInfusion.getId());
            }
        }
        for (int i = 0; i <hisInfusionList.size() ; i++) {
            //传来的ID若大于0为输液单表ID
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectByDrugsNumb(hisInfusionList.get(i).getDrugsNumb());
            HisInfusion hisInfusion = new HisInfusion();
            hisInfusion.setRecordId(recordId.toString());
            hisInfusion.setHosptalregistNumber(hisMedicalRecord.getMedicalRecordId());
            hisInfusion.setStartTimeVarchar(hisInfusionList.get(i).getStartTimeVarchar());
            hisInfusion.setPatientId(hisMedicalRecord.getPatientId());
            hisInfusion.setUsages(hisInfusionList.get(i).getUsages());
            hisInfusion.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
            hisInfusion.setDrugname(hisPharmacyDetail.getDrugsName());
            hisInfusion.setDosage(hisInfusionList.get(i).getDosage());
            hisInfusion.setType(1);
            hisInfusion.setNumber(name+createdate);
            saveList.add(hisInfusion);
        }
        hisInfusionMapper.insertBatch(saveList);
        return MessageUtil.createMessage(true,"添加输液单成功（Adding an infusion order successfully）");
    }

    /**
     *@Description 根据就诊编号查询对应未付款的输液单
     *@Params [medicalRecordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 12:53
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> selectByRecordNumberAndNotPay(String medicalRecordId) throws Exception {
        return hisInfusionMapper.selectByRecordNumberAndNotPay(medicalRecordId);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRemarks(HisInfusion hisInfusion) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisInfusion)){
            hisInfusionMapper.updateRemarks(hisInfusion);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> listByRemark(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByRemark(pageBean)));
        return pageBean;
    }
}
