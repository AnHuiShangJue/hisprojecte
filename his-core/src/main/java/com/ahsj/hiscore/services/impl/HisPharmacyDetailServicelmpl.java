package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisMediEnterDetailsService;
import com.ahsj.hiscore.services.HisMedicineInfoService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
import com.ahsj.hiscore.services.MedicinePurchasingPlanService;
import core.entity.PageBean;
import core.message.BoolMessage;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HisPharmacyDetailServicelmpl implements HisPharmacyDetailService {
    private Logger log = LoggerFactory.getLogger(HisPharmacyDetailServicelmpl.class.getSimpleName());
    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;

    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;

    @Autowired
    HisTollRecordMapper hisTollRecordMapper;

    @Autowired
    HisHospitalManageMapper hisHospitalManageMapper;

    @Autowired

    ITranslateService iTranslateService;

    @Autowired
    HisMedicineInfoMapper hisMedicineInfoMapper;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    MedicinePurchasingPlanService medicinePurchasingPlanService;

    @Autowired
    HisMediEnterDetailsService hisMediEnterDetailsService;

    @Autowired
    MedicinePurchasingPlanMapper medicinePurchasingPlanMapper;

    @Autowired
    HisMedicinePurchasingPlanRecordMapper hisMedicinePurchasingPlanRecordMapper;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-06-22
     * @Time 20:55
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisPharmacyDetail> list(PageBean<HisPharmacyDetail> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.list(pageBean)));
        return pageBean;
    }


    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-07-12
     * @Time 13:37
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisPharmacyDetail hisPharmacyDetail) throws Exception {
        //药库无新增功能，增加信息通过药库入库实现
        if (EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getId())) {
            hisPharmacyDetailMapper.insert(hisPharmacyDetail);
            return MessageUtil.createMessage(true, "新增成功！");
            //更新（修改）
        } else {
            if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getMedicineId())){
                HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByPrimaryKey(hisPharmacyDetail.getMedicineId());
                hisMedicineInfo.setDepartmentId(hisPharmacyDetail.getDepartmentId());
                hisMedicineInfoService.updateDepartmentId(hisMedicineInfo);
            }
            hisPharmacyDetailMapper.updateByPrimaryKeySelective(hisPharmacyDetail);
            return MessageUtil.createMessage(true, "更新成功");
        }
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-06-25
     * @Time 11:31
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisPharmacyDetailMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisPharmacyDetail
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-06-25
     * @Time 11:29
     **/
    @Override
    @Transactional(readOnly = true)
    public HisPharmacyDetail selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.selectByPrimaryKey(id));
    }


    /**
     * @return core.message.Message
     * @Description 设置药库下架状态
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-06-29
     * @Time 14:05
     **/
    @Override
    @Transactional(readOnly = false)
    public Message setObtained(Long[] ids) throws Exception {
        for (Long id : ids) {
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPrimaryKey(id);
            if (hisPharmacyDetail.getIsObtained() == 1) {
                hisPharmacyDetail.setIsObtained(2);
                hisPharmacyDetail.setDisable(2);
            } else if (hisPharmacyDetail.getIsObtained() == 2) {
                hisPharmacyDetail.setIsObtained(1);
                hisPharmacyDetail.setDisable(1);
            }
            saveOrUpdate(hisPharmacyDetail);
        }
        return MessageUtil.createMessage(true, "是否下架状态修改成功!");
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 查询出药库中所有库存小于预警值的药库
     * @Params []
     * @Author zhushixiang
     * @Date 2019-07-08
     * @Time 14:51
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisPharmacyDetail> selectForStock() throws Exception {
        return hisPharmacyDetailMapper.selectForStock();
    }

    /**
     * @return com.ahsj.hiscore.entity.HisPharmacyDetail
     * @Description 根据药库基本表中药库ID查找
     * @Params [pharmacyId]
     * @Author zhushixiang
     * @Date 2019-07-09
     * @Time 21:20
     **/
    @Override
    @Transactional(readOnly = true)
    public HisPharmacyDetail selectByPharmacyId(Long pharmacyId) {
        return CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.selectByPharmacyId(pharmacyId));
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 根据用药明细的id查询药房药库
     * @Params [detailsList]
     * @Author chenzhicai
     * @Date 2019-07-11
     * @Time 17:38
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisPharmacyDetail> selectForTreatPlan(List<HisMedicationDetails> detailsList) {
        if (!EmptyUtil.Companion.isNullOrEmpty(detailsList) && !(detailsList.size() == 0)) {
            Long[] ids = new Long[detailsList.size()];
            int i = 0;
            for (HisMedicationDetails tmp :
                    detailsList) {
                ids[i] = tmp.getId();
                i++;
            }
            ;
            return hisPharmacyDetailMapper.selectForTreatPlan(ids);
        } else return null;
    }

    /**
     * @return core.message.Message
     * @Description 批量新增
     * @Params [hisPharmacyDetailList]
     * @Author zhushixiang
     * @Date 2019-07-12
     * @Time 11:13
     **/
    @Override
    @Transactional(readOnly = false)
    public void saveForMultiple(List<HisPharmacyDetail> hisPharmacyDetailList) throws Exception {
        hisPharmacyDetailMapper.insertBatch(hisPharmacyDetailList);
    }

    /**
     * @return void
     * @Description 部分信息修改（如仅在出入库时候修改药库的价格，等少量信息其他不修改）
     * @Params [hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-07-12
     * @Time 13:38
     **/
    @Override
    @Transactional(readOnly = false)
    public void UpdateForPart(HisPharmacyDetail hisPharmacyDetail) throws Exception {
        HisPharmacyDetail checkId = hisPharmacyDetailMapper.selectByPrimaryKey(hisPharmacyDetail.getId());
        if (!EmptyUtil.Companion.isNullOrEmpty(checkId))
            hisPharmacyDetailMapper.updateByPrimaryKeySelective(hisPharmacyDetail);
    }

    /**
     * @return core.message.Message
     * @Description 查询出对应就诊编号/住院编号/交易流水号的用药明细
     * @Params [medicationRecordId]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 20:48
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicationDetails> listForMedicationDetailsByNumber(String number) throws Exception {
        String numberForExit = "";
        if (number.substring(0, 3).equals("HHM") || number.substring(0, 2).equals("HM")) {
            numberForExit = number;
        } else if (number.substring(0, 3).equals("HTR")) {
            HisTollRecord hisTollRecord = hisTollRecordMapper.selectByNumber(number);
            if (EmptyUtil.Companion.isNullOrEmpty(hisTollRecord))
                return new ArrayList<HisMedicationDetails>();
            numberForExit = hisTollRecord.getMedicalRecordId();
        } else if (number.substring(0, 2).equals("HR")) {
            HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByHospNumber(number);
            if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage))
                return new ArrayList<HisMedicationDetails>();
            numberForExit = hisHospitalManage.getMedicalNumber();
        } else
            return null;
        List<HisMedicationDetails> hisMedicationDetailsList = hisMedicationDetailsMapper.listForMedicationDetailsByNumber(numberForExit);

            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList) && hisMedicationDetailsList.size() == 0)
            return hisMedicationDetailsList;

        List<HisMedicationDetails> sendDatas = new ArrayList<HisMedicationDetails>();
        int continueCounts = 0;
        Outer:
        for (int i = 0; i < hisMedicationDetailsList.size(); i++) {
            if (continueCounts > 0) {
                continueCounts--;
                continue Outer;
            }
            List<HisMedicationDetails> tmps = new ArrayList<HisMedicationDetails>();
            inner:
            for (int j = i; j < hisMedicationDetailsList.size(); j++) {
                if (hisMedicationDetailsList.get(i).getDrugsNumb().equals(hisMedicationDetailsList.get(j).getDrugsNumb())) {
                    tmps.add(hisMedicationDetailsList.get(j));
                    if (i != j)
                        continueCounts++;
                } else {
                    break inner;
                }
            }
            int restCounts = tmps.get(0).getCount();
            secondInner:
            for (int k = 0; k < tmps.size(); k++) {
                Long s = tmps.get(k).getSurplus();
                HisMedicationDetails hisMedicationDetails = tmps.get(k);
                //如果第一条数据符合要求，则返回第一条，以后的数据不做处理
                if (s.intValue() >= restCounts) {
                    hisMedicationDetails.setActualCount(restCounts);
                    sendDatas.add(hisMedicationDetails);
                    break secondInner;
                } else {
                    //如果库存值比需要出的值要少，则将需要出库的数目设置为库存值
                    hisMedicationDetails.setActualCount(hisMedicationDetails.getSurplus().intValue());
                    //剩余出库数目减少
                    restCounts -= hisMedicationDetails.getActualCount();
                    sendDatas.add(hisMedicationDetails);
                }
            }
        }
        return CodeHelper.getInstance().setCodeValue(sendDatas);
    }

    /**
     * @return void
     * @Description 批量更新价格与销售数目
     * @Params [hisPharmacyDetailList]
     * @Author zhushixiang
     * @Date 2019-07-23
     * @Time 19:51
     **/
    @Override
    @Transactional(readOnly = true)
    public void updateBatch(List<HisPharmacyDetail> hisPharmacyDetailList) {
        hisPharmacyDetailMapper.updateBatch(hisPharmacyDetailList);
    }

    /**
     * @return core.message.Message
     * @Description 设置药库停用状态
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-06-29
     * @Time 14:04
     **/
    @Override
    //数据库事务
    @Transactional(readOnly = false)
    public Message setDisable(Long[] ids) throws Exception {
        for (Long id : ids) {
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPrimaryKey(id);
            if (hisPharmacyDetail.getDisable() == 1)
                hisPharmacyDetail.setDisable(2);
            else if (hisPharmacyDetail.getDisable() == 2)
                hisPharmacyDetail.setDisable(1);
            saveOrUpdate(hisPharmacyDetail);
        }
        return MessageUtil.createMessage(true, "药库停用状态设置成功");
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 分页查询为医生开药
     * @Params [pharmacyDetailPageBean]
     * @Author zhushixiang
     * @Date 2019-08-14
     * @Time 11:17
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisPharmacyDetail> listForMedication(PageBean<HisPharmacyDetail> pharmacyDetailPageBean) {
        pharmacyDetailPageBean.setData(CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.listForMedication(pharmacyDetailPageBean)));
        return pharmacyDetailPageBean;
    }

    /**
     *@Description 导出excel
     *@Params [ids, request, response, session]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-28
     *@Time 17:27
    **/
    @Override
    @Transactional(readOnly = true)
    public void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session,String param)throws Exception{
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        if (StringUtils.equals(param,Constants.HIS_CH)) {
            psth = this.getClass().getClassLoader().getResource("templates/excel/export/hisPharmacy_CH.xlsx").getPath();
            psth = Constants.HIS_SYS_EXCEL_PHARMACY_CH_FILE_URL;
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisPharmacyDetail> hisPharmacyDetailList = CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.queryListExportAll());
                beans.put("hisPharmacyDetailList", hisPharmacyDetailList);
                JxlsUtil.export(request, response, psth, "药库基本信息记录", beans);
                return;
            }
            List<HisPharmacyDetail> list = new ArrayList<>();
            for (Long id : ids) {
                HisPharmacyDetail hisPharmacyDetail = new HisPharmacyDetail();
                hisPharmacyDetail.setId(id);
                list.add(hisPharmacyDetail);
            }
            List<HisPharmacyDetail> hisPharmacyDetailList = CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.queryListExportAndByIdsAll(list));
            if (EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetailList)) {
                return;
            } else {
                beans.put("hisPharmacyDetailList", hisPharmacyDetailList);
                JxlsUtil.export(request, response, psth, "药库基本信息记录", beans);
                return;
            }
        }else if(StringUtils.equals(param,Constants.HIS_KM)){
           // psth = this.getClass().getClassLoader().getResource("templates/excel/export/hisPharmacy_EN.xlsx").getPath();
            psth = Constants.HIS_SYS_EXCEL_PHARMACY_KM_FILE_URL;
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisPharmacyDetail> hisPharmacyDetailList = CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.queryListExportAll());
                for (HisPharmacyDetail hisPharmacyDetail : hisPharmacyDetailList) {
                    Translate translate = new Translate();
                    //字典
                    translate.setTranslateId(hisPharmacyDetail.getPrescriptionId());
                    translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translates) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getPrecriptionName())){
                            hisPharmacyDetail.setPrecriptionName(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate2 = new Translate();
                    translate2.setTranslateId(hisPharmacyDetail.getMentalMedicineId());
                    translate2.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates1 = iTranslateService.queryTranslate(translate2);
                    for (Translate translate1 : translates1) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getMentalmedicine())){
                            hisPharmacyDetail.setMentalmedicine(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate3 = new Translate();
                    translate3.setTranslateId(hisPharmacyDetail.getLevelId());
                    translate3.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates2 = iTranslateService.queryTranslate(translate3);
                    for (Translate translate1 : translates2) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getLeveld())){
                            hisPharmacyDetail.setLeveld(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate4 = new Translate();
                    translate4.setTranslateId(hisPharmacyDetail.getMedicalInsuranceSignId());
                    translate4.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates3 = iTranslateService.queryTranslate(translate4);
                    for (Translate translate1 : translates3) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getMedicalinsurancesign())){
                            hisPharmacyDetail.setMedicalinsurancesign(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate5 = new Translate();
                    translate5.setTranslateId(hisPharmacyDetail.getPlaceoriginId());
                    translate5.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates4 = iTranslateService.queryTranslate(translate5);
                    for (Translate translate1 : translates4) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getPlaceoriginName())){
                            hisPharmacyDetail.setPlaceoriginName(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate6 = new Translate();
                    translate6.setTranslateId(hisPharmacyDetail.getBaseMedicineId());
                    translate6.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates5 = iTranslateService.queryTranslate(translate6);
                    for (Translate translate1 : translates5) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getBasemedicine())){
                            hisPharmacyDetail.setBasemedicine(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate7 = new Translate();
                    translate7.setTranslateId(hisPharmacyDetail.getNarcoticDrugsId());
                    translate7.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates6 = iTranslateService.queryTranslate(translate7);
                    for (Translate translate1 : translates6) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getNarcoticdrugs())){
                            hisPharmacyDetail.setNarcoticdrugs(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate8 = new Translate();
                    translate8.setTranslateId(hisPharmacyDetail.getDisableId());
                    translate8.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates7 = iTranslateService.queryTranslate(translate8);
                    for (Translate translate1 : translates7) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getDisabled())){
                            hisPharmacyDetail.setDisabled(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate9 = new Translate();
                    translate9.setTranslateId(hisPharmacyDetail.getIsObtainedId());
                    translate9.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates8 = iTranslateService.queryTranslate(translate9);
                    for (Translate translate1 : translates8) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getObtained())){
                            hisPharmacyDetail.setObtained(translate1.getTranslateKhmer());
                        }
                    }

                    //药库
                    HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                    translate.setTranslateId(hisMedicineInfo.getId());
                    translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                    List<Translate> translateHisPharmacyDetail = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translateHisPharmacyDetail) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getDrugsName())){
                            hisPharmacyDetail.setDrugsName(translate1.getTranslateKhmer());
                        }
                        if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getDrugsSpec())){
                            hisPharmacyDetail.setDrugsSpec(translate1.getTranslateKhmer());
                        }
                        if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getLargeUnit())) {
                            if (StringUtils.equals(translate1.getTranslateChina(), hisPharmacyDetail.getLargeUnit())) {
                                hisPharmacyDetail.setLargeUnit(translate1.getTranslateKhmer());
                            }
                        }
                        if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSmallUnit())) {
                            if (StringUtils.equals(translate1.getTranslateChina(), hisPharmacyDetail.getSmallUnit())) {
                                hisPharmacyDetail.setSmallUnit(translate1.getTranslateKhmer());
                            }
                        }
                    }


                }
                beans.put("hisPharmacyDetailList", hisPharmacyDetailList);
                JxlsUtil.export(request, response, psth, "Drug store medicine basic information", beans);
                return;
            }
            List<HisPharmacyDetail> list = new ArrayList<>();
            for (Long id : ids) {
                HisPharmacyDetail hisPharmacyDetail = new HisPharmacyDetail();
                hisPharmacyDetail.setId(id);
                list.add(hisPharmacyDetail);
            }
            List<HisPharmacyDetail> hisPharmacyDetailList = CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.queryListExportAndByIdsAll(list));
            for (HisPharmacyDetail hisPharmacyDetail : hisPharmacyDetailList) {
                Translate translate = new Translate();
                //字典
                translate.setTranslateId(hisPharmacyDetail.getPrescriptionId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translates) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getPrecriptionName())){
                        hisPharmacyDetail.setPrecriptionName(translate1.getTranslateKhmer());
                    }
                }
                Translate translate2 = new Translate();
                translate2.setTranslateId(hisPharmacyDetail.getMentalMedicineId());
                translate2.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates1 = iTranslateService.queryTranslate(translate2);
                for (Translate translate1 : translates1) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getMentalmedicine())){
                        hisPharmacyDetail.setMentalmedicine(translate1.getTranslateKhmer());
                    }
                }
                Translate translate3 = new Translate();
                translate3.setTranslateId(hisPharmacyDetail.getLevelId());
                translate3.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates2 = iTranslateService.queryTranslate(translate3);
                for (Translate translate1 : translates2) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getLeveld())){
                        hisPharmacyDetail.setLeveld(translate1.getTranslateKhmer());
                    }
                }
                Translate translate4 = new Translate();
                translate4.setTranslateId(hisPharmacyDetail.getMedicalInsuranceSignId());
                translate4.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates3 = iTranslateService.queryTranslate(translate4);
                for (Translate translate1 : translates3) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getMedicalinsurancesign())){
                        hisPharmacyDetail.setMedicalinsurancesign(translate1.getTranslateKhmer());
                    }
                }
                Translate translate5 = new Translate();
                translate5.setTranslateId(hisPharmacyDetail.getPlaceoriginId());
                translate5.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates4 = iTranslateService.queryTranslate(translate5);
                for (Translate translate1 : translates4) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getPlaceoriginName())){
                        hisPharmacyDetail.setPlaceoriginName(translate1.getTranslateKhmer());
                    }
                }
                Translate translate6 = new Translate();
                translate6.setTranslateId(hisPharmacyDetail.getBaseMedicineId());
                translate6.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates5 = iTranslateService.queryTranslate(translate6);
                for (Translate translate1 : translates5) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getBasemedicine())){
                        hisPharmacyDetail.setBasemedicine(translate1.getTranslateKhmer());
                    }
                }
                Translate translate7 = new Translate();
                translate7.setTranslateId(hisPharmacyDetail.getNarcoticDrugsId());
                translate7.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates6 = iTranslateService.queryTranslate(translate7);
                for (Translate translate1 : translates6) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getNarcoticdrugs())){
                        hisPharmacyDetail.setNarcoticdrugs(translate1.getTranslateKhmer());
                    }
                }
                Translate translate8 = new Translate();
                translate8.setTranslateId(hisPharmacyDetail.getDisableId());
                translate8.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates7 = iTranslateService.queryTranslate(translate8);
                for (Translate translate1 : translates7) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getDisabled())){
                        hisPharmacyDetail.setDisabled(translate1.getTranslateKhmer());
                    }
                }
                Translate translate9 = new Translate();
                translate9.setTranslateId(hisPharmacyDetail.getIsObtainedId());
                translate9.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates8 = iTranslateService.queryTranslate(translate9);
                for (Translate translate1 : translates8) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getObtained())){
                        hisPharmacyDetail.setObtained(translate1.getTranslateKhmer());
                    }
                }

                //药库
                HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                translate.setTranslateId(hisMedicineInfo.getId());
                translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                List<Translate> translateHisPharmacyDetail = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translateHisPharmacyDetail) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getDrugsName())){
                        hisPharmacyDetail.setDrugsName(translate1.getTranslateKhmer());
                    }
                    if (StringUtils.equals(translate1.getTranslateChina(),hisPharmacyDetail.getDrugsSpec())){
                        hisPharmacyDetail.setDrugsSpec(translate1.getTranslateKhmer());
                    }
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getLargeUnit())) {
                        if (StringUtils.equals(translate1.getTranslateChina(), hisPharmacyDetail.getLargeUnit())) {
                            hisPharmacyDetail.setLargeUnit(translate1.getTranslateKhmer());
                        }
                    }
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSmallUnit())) {
                        if (StringUtils.equals(translate1.getTranslateChina(), hisPharmacyDetail.getSmallUnit())) {
                            hisPharmacyDetail.setSmallUnit(translate1.getTranslateKhmer());
                        }
                    }
                }
            }
            if (EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetailList)) {
                return;
            } else {
                beans.put("hisPharmacyDetailList", hisPharmacyDetailList);
                JxlsUtil.export(request, response, psth, "药库基本信息记录", beans);
                return;
            }
        }else if(StringUtils.equals(param,Constants.HIS_EN)){
            log.info("这里是预留接口，进行翻译英文");
        }
    }

    /**
     *@Description 药库信息excel导入
     *@Params [list]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-30
     *@Time 15:37
    **/
    @Override
    @Transactional(readOnly = false)
    public Map importExcel(List<HisPharmacyDetail> excelData)throws Exception {
        List<String> errorData = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer failNum = 0;
        // 循环]
        //从库中将所有的HisPharmacyDetail信息全部取出来做数据对比处理
        List<HisPharmacyDetail> tempList = hisPharmacyDetailMapper.queryPharmacyAll();
        //插入的list
        List<HisPharmacyDetail> pharmacyInsertList = new ArrayList<>();
        //更新的list
        List<HisPharmacyDetail> pharmacyUpdateList = new ArrayList<>();
        //这个List是为了判断在新增里的药品到底是新增还是更新   比如导入时输入了3个编号为003的药品，且药库中没有，则第一个应为插入，后两个应为更新
        List<String> DrugsNumbInsertList = new ArrayList<>();
        for (HisPharmacyDetail hisPharmacyDetail : excelData) {
            boolean updateflag = false;
            for (HisPharmacyDetail pharmacyDetail : tempList) {
                if (pharmacyDetail.getDrugsNumb().equals(hisPharmacyDetail.getDrugsNumb())) {
                    updateflag = true;
                    break;
                }
                if (pharmacyDetail.getDrugsName().equals(hisPharmacyDetail.getDrugsName()) && pharmacyDetail.getDrugsSpec().equals(hisPharmacyDetail.getDrugsSpec()) ) {
                    updateflag = true;
                    break;
                }
            }
            if (!updateflag &&!DrugsNumbInsertList.contains(hisPharmacyDetail.getDrugsNumb())) {
                pharmacyInsertList.add(hisPharmacyDetail);
                DrugsNumbInsertList.add(hisPharmacyDetail.getDrugsNumb());
            } else {
                pharmacyUpdateList.add(hisPharmacyDetail);
            }
        }
        if (pharmacyInsertList != null && pharmacyInsertList.size() > 0) {
            for (int i = 0; i < pharmacyInsertList.size(); i++) {
                pharmacyInsertList.get(i).setId(null);
                HisMedicineInfo hisMedicineInfo = new HisMedicineInfo();
                if(!EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getDrugsNumb())) {
                    hisMedicineInfo = hisMedicineInfoMapper.selectByDrugsNumb(pharmacyInsertList.get(i).getDrugsNumb());
                }else {
                    HisMedicineInfo checkDrugsNameAndSpec = new HisMedicineInfo();
                    checkDrugsNameAndSpec.setDrugsName(pharmacyInsertList.get(i).getDrugsName());
                    checkDrugsNameAndSpec.setDrugsSpec(pharmacyInsertList.get(i).getDrugsSpec());
                    hisMedicineInfo = hisMedicineInfoMapper.selectByDrugsNameAndSpec(checkDrugsNameAndSpec);
                }
                if (EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getSalePrice()))
                    pharmacyInsertList.get(i).setSalePrice(new BigDecimal("0"));
                if (EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getStock()))
                    pharmacyInsertList.get(i).setStock(0);
                if (EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getStockWarn()))
                    pharmacyInsertList.get(i).setStockWarn(0);
                if (EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getNormalStock()))
                    pharmacyInsertList.get(i).setNormalStock(0);
                if (EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getSaleCounts()))
                    pharmacyInsertList.get(i).setSaleCounts(0);
                pharmacyInsertList.get(i).setDrugsNumb(hisMedicineInfo.getDrugsNumb());
                pharmacyInsertList.get(i).setMedicineId(hisMedicineInfo.getId());
                pharmacyInsertList.get(i).setDrugsName(hisMedicineInfo.getDrugsName());
                pharmacyInsertList.get(i).setDrugsAlias(hisMedicineInfo.getDrugsAlias());
                pharmacyInsertList.get(i).setDrugsSpec(hisMedicineInfo.getDrugsSpec());
                pharmacyInsertList.get(i).setStock(pharmacyInsertList.get(i).getStock());
                pharmacyInsertList.get(i).setSalePrice(pharmacyInsertList.get(i).getSalePrice());
                pharmacyInsertList.get(i).setStockWarn(pharmacyInsertList.get(i).getStockWarn());
                pharmacyInsertList.get(i).setSaleCounts(pharmacyInsertList.get(i).getSaleCounts());
                pharmacyInsertList.get(i).setNormalStock(pharmacyInsertList.get(i).getNormalStock());
                pharmacyInsertList.get(i).setPrescription(hisMedicineInfo.getPrescription());
                pharmacyInsertList.get(i).setMentalMedicine(hisMedicineInfo.getMentalMedicine());
                pharmacyInsertList.get(i).setLevel(hisMedicineInfo.getLevel());
                pharmacyInsertList.get(i).setLargeUnit(hisMedicineInfo.getLargeUnit());
                pharmacyInsertList.get(i).setSmallUnit(hisMedicineInfo.getSmallUnit());
                pharmacyInsertList.get(i).setConversionRate(hisMedicineInfo.getConversionRate());
                pharmacyInsertList.get(i).setMedicalInsuranceSign(hisMedicineInfo.getMedicalInsuranceSign());
                pharmacyInsertList.get(i).setPlaceorigin(hisMedicineInfo.getPlaceorigin());
                pharmacyInsertList.get(i).setBaseMedicine(hisMedicineInfo.getBaseMedicine());
                pharmacyInsertList.get(i).setNarcoticDrugs(hisMedicineInfo.getNarcoticDrugs());
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getEnterPrice()))
                    pharmacyInsertList.get(i).setEnterPrice(BigDecimal.valueOf(hisMedicineInfo.getEnterPrice()));
                else
                    pharmacyInsertList.get(i).setEnterPrice(pharmacyInsertList.get(i).getEnterPrice());

                //设置字典的值
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getDisabled()))
                    pharmacyInsertList.get(i).setDisable(2);
                else {
                    String disabled = pharmacyInsertList.get(i).getDisabled();
                    SysCodeDetail detail = new SysCodeDetail();
                    detail.setValue(disabled);
                    detail.setSysCodeType(Constants.HIS_SYS_CODE_DISABLE);
                    String code = iCodeService.getSysCodeName(detail).getCode();
                    Integer disable = Integer.parseInt(code);
                    pharmacyInsertList.get(i).setDisable(disable);
                }
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyInsertList.get(i).getObtained()))
                    pharmacyInsertList.get(i).setIsObtained(2);
                else {
                    String obtained = pharmacyInsertList.get(i).getObtained();
                    SysCodeDetail detail2 = new SysCodeDetail();
                    detail2.setValue(obtained);
                    detail2.setSysCodeType(Constants.HIS_SYS_CODE_IS_OBTAINED);
                    String code2 = iCodeService.getSysCodeName(detail2).getCode();
                    Integer isObtained = Integer.parseInt(code2);
                    pharmacyInsertList.get(i).setIsObtained(isObtained);
                }

            }
            hisPharmacyDetailMapper.importHisPharmacyDetail(pharmacyInsertList);
        }

        if (pharmacyUpdateList != null && pharmacyUpdateList.size() > 0) {
            for (int i = 0; i <pharmacyUpdateList.size() ; i++) {
                HisPharmacyDetail hisPharmacyDetail = new HisPharmacyDetail();
                if(!EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getDrugsNumb())) {
                    hisPharmacyDetail = hisPharmacyDetailMapper.selectByDrugsNumb(pharmacyUpdateList.get(i).getDrugsNumb());

                }else {
                     hisPharmacyDetail = hisPharmacyDetailMapper.selectByDrugsNameAndSpec(pharmacyUpdateList.get(i));

                }
                HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                Long hid = hisPharmacyDetail.getId();
                pharmacyUpdateList.get(i).setId(hid);
                pharmacyUpdateList.get(i).setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                pharmacyUpdateList.get(i).setMedicineId(hisPharmacyDetail.getMedicineId());
                pharmacyUpdateList.get(i).setDrugsName(hisPharmacyDetail.getDrugsName());
                pharmacyUpdateList.get(i).setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                pharmacyUpdateList.get(i).setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getStock()) )
                    pharmacyUpdateList.get(i).setStock(hisPharmacyDetail.getStock());
                else
                    pharmacyUpdateList.get(i).setStock(hisPharmacyDetail.getStock()+pharmacyUpdateList.get(i).getStock());
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getSalePrice()))
                    pharmacyUpdateList.get(i).setSalePrice(hisPharmacyDetail.getSalePrice());
                else
                    pharmacyUpdateList.get(i).setSalePrice(pharmacyUpdateList.get(i).getSalePrice());
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getEnterPrice()) )
                    pharmacyUpdateList.get(i).setSalePrice(BigDecimal.valueOf(hisMedicineInfo.getEnterPrice()));
                else
                    pharmacyUpdateList.get(i).setEnterPrice(pharmacyUpdateList.get(i).getEnterPrice());
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getStockWarn()) )
                    pharmacyUpdateList.get(i).setStockWarn(hisPharmacyDetail.getStockWarn());
                else
                    pharmacyUpdateList.get(i).setStockWarn(pharmacyUpdateList.get(i).getStockWarn());
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getSaleCounts()) )
                    pharmacyUpdateList.get(i).setSaleCounts(hisPharmacyDetail.getSaleCounts());
                else
                    pharmacyUpdateList.get(i).setSaleCounts(hisPharmacyDetail.getSaleCounts()+pharmacyUpdateList.get(i).getSaleCounts());
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getNormalStock()) )
                    pharmacyUpdateList.get(i).setNormalStock(hisPharmacyDetail.getNormalStock());
                else
                    pharmacyUpdateList.get(i).setNormalStock(pharmacyUpdateList.get(i).getNormalStock());
                pharmacyUpdateList.get(i).setPrescription(hisPharmacyDetail.getPrescription());
                pharmacyUpdateList.get(i).setMentalMedicine(hisPharmacyDetail.getMentalMedicine());
                pharmacyUpdateList.get(i).setLevel(hisPharmacyDetail.getLevel());
                pharmacyUpdateList.get(i).setMedicalInsuranceSign(hisPharmacyDetail.getMedicalInsuranceSign());
                pharmacyUpdateList.get(i).setPlaceorigin(hisPharmacyDetail.getPlaceorigin());
                pharmacyUpdateList.get(i).setBaseMedicine(hisPharmacyDetail.getBaseMedicine());
                pharmacyUpdateList.get(i).setNarcoticDrugs(hisPharmacyDetail.getNarcoticDrugs());
                pharmacyUpdateList.get(i).setLargeUnit(hisPharmacyDetail.getLargeUnit());
                pharmacyUpdateList.get(i).setSmallUnit(hisPharmacyDetail.getSmallUnit());
                pharmacyUpdateList.get(i).setConversionRate(hisPharmacyDetail.getConversionRate());

                //设置字典的值
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getDisabled()))
                    pharmacyUpdateList.get(i).setDisable(hisPharmacyDetail.getDisable());
                else {
                    String disabled = pharmacyUpdateList.get(i).getDisabled();
                    SysCodeDetail detail = new SysCodeDetail();
                    detail.setValue(disabled);
                    detail.setSysCodeType(Constants.HIS_SYS_CODE_DISABLE);
                    String code = iCodeService.getSysCodeName(detail).getCode();
                    Integer disable = Integer.parseInt(code);
                    pharmacyUpdateList.get(i).setDisable(disable);
                }
                if(EmptyUtil.Companion.isNullOrEmpty(pharmacyUpdateList.get(i).getObtained()))
                    pharmacyUpdateList.get(i).setIsObtained(hisPharmacyDetail.getIsObtained());
                else {
                    String obtained = pharmacyUpdateList.get(i).getObtained();
                    SysCodeDetail detail2 = new SysCodeDetail();
                    detail2.setValue(obtained);
                    detail2.setSysCodeType(Constants.HIS_SYS_CODE_IS_OBTAINED);
                    String code2 = iCodeService.getSysCodeName(detail2).getCode();
                    Integer isObtained = Integer.parseInt(code2);
                    pharmacyUpdateList.get(i).setIsObtained(isObtained);
                }
                hisPharmacyDetailMapper.updateByPrimaryKeySelective(pharmacyUpdateList.get(i));
            }
            //此处不能使用批量更新，因为每次都要更新数目，以免后续相同药品不能正确获取药品数量
        }

        //提交采购计划申请
        Long[] ids = new Long[excelData.size()];//ids数组
        Long[] numbers = new Long[excelData.size()];//导入数量数组
        Double[] prices = new Double[excelData.size()];//进价数组
        String personInCharge = "EXCEL导入";
        String expectedTime =new Date().toString();
        String[] validityPeriods=new String[excelData.size()];
        for (int i = 0; i <excelData.size() ; i++) {
            HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByDrugsNumb(excelData.get(i).getDrugsNumb());
            ids[i]=hisMedicineInfo.getId();
            if(EmptyUtil.Companion.isNullOrEmpty(excelData.get(i).getStock()))
                excelData.get(i).setStock(0);
            numbers[i]=excelData.get(i).getStock().longValue();
            if(EmptyUtil.Companion.isNullOrEmpty(excelData.get(i).getEnterPrice()))
                excelData.get(i).setEnterPrice(BigDecimal.valueOf(hisMedicineInfo.getEnterPrice()));
            prices[i]=excelData.get(i).getEnterPrice().doubleValue();
            validityPeriods[i]=excelData.get(i).getValidityPeriod().toString();
        }
        //与采购计划相同方式生成采购计划编号以便获取相同的采购计划编号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = medicinePurchasingPlanMapper.selectNumbCount(createdate) + 1;
        boolean flag = false;
        String planNumber = "";
        while(true){
            planNumber = "MPP"+createdate + String.format("%05d", count);
            HisMedicinePurchasingPlanRecord checkPlanNumber = hisMedicinePurchasingPlanRecordMapper.selectByPlanNumber(planNumber);
            if(EmptyUtil.Companion.isNullOrEmpty(checkPlanNumber))
                break;
            else
                count++;
        }
        if(!((BoolMessage)  medicinePurchasingPlanService.saveOrUpdate(ids,numbers,personInCharge,expectedTime,prices)).isSuccess()){
            return null;
        }else {
            //药品入库确认
            if(!((BoolMessage)  hisMediEnterDetailsService.mediEnter(ids,numbers,prices,planNumber,validityPeriods)).isSuccess())
                return null;
        }
        return map;
    }

    /**
     *@Description
     *@Params [hisPharmacyDetail]
     *@return com.ahsj.hiscore.entity.HisPharmacyDetail
     *@Author zhushixiang
     *@Date 2019-08-30
     *@Time 17:12
    **/
    @Override
    @Transactional(readOnly = true)
    public HisPharmacyDetail selectByDrugsNameAndSpec(HisPharmacyDetail hisPharmacyDetail) {
        return hisPharmacyDetailMapper.selectByDrugsNameAndSpec(hisPharmacyDetail);
    }

    /**
     *@Description 根据导入时传来的条件搜索出对应的药库药品信息
     *@Params [hisPharmacyDetail]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-09-02
     *@Time 16:35
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisPharmacyDetail> selectByExportExcel(HisPharmacyDetail hisPharmacyDetail) {
        return hisPharmacyDetailMapper.selectByExportExcel(hisPharmacyDetail);
    }

    /**
     *@Description
     *@Params [drugsNumb]
     *@return com.ahsj.hiscore.entity.HisPharmacyDetail
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 10:35
    **/
    @Override
    @Transactional(readOnly = true)
    public HisPharmacyDetail selectByDrugsNumb(String drugsNumb) throws Exception {
        return hisPharmacyDetailMapper.selectByDrugsNumb(drugsNumb);
    }

    /**
     *@Description 查询出未停用未下架的药品
     *@Params [pharmacyDetailPageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 18:16
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisPharmacyDetail> listForIsDisableAndObtained(PageBean<HisPharmacyDetail> pharmacyDetailPageBean) throws Exception {
        pharmacyDetailPageBean.setData(CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.listForIsDisableAndObtained(pharmacyDetailPageBean)));
        return pharmacyDetailPageBean;
    }

    /**
     *@Description 根据IDs查询药库药品信息
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-10-05
     *@Time 14:58
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisPharmacyDetail> selectForListForMedicationByIds(Long[] ids) throws Exception {
        List<HisPharmacyDetail> hisPharmacyDetailList =hisPharmacyDetailMapper.selectForListForMedicationByIds(ids);
        return hisPharmacyDetailMapper.selectForListForMedicationByIds(ids);
    }

    /**
     *@Description 为门诊用药增加输液单
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-10-05
     *@Time 16:32
    **/
    @Override
    @Transactional(readOnly = false)
    public Message addCombinationMedicine(Long[] ids) throws Exception {

        return null;
    }
    //模糊查询（or）
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisPharmacyDetail> listForAll(PageBean<HisPharmacyDetail> pharmacyDetailPageBean) throws Exception {
        pharmacyDetailPageBean.setData(CodeHelper.getInstance().setCodeValue(hisPharmacyDetailMapper.listForAll(pharmacyDetailPageBean)));
        return pharmacyDetailPageBean;
    }
}
