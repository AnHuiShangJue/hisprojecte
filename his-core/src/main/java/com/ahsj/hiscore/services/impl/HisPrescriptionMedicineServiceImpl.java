package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisPharmacyDetailMapper;
import com.ahsj.hiscore.dao.HisPrescriptionMapper;
import com.ahsj.hiscore.dao.HisPrescriptionMedicineMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.TranslateModel.HisPrescriptionMedicineTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisPrescriptionMedicineService;
import com.ahsj.hiscore.services.HisPrescriptionService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HisPrescriptionMedicineServiceImpl implements HisPrescriptionMedicineService {

    private Logger log = LoggerFactory.getLogger(HisPrescriptionMedicineServiceImpl.class.getSimpleName());

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    HisPrescriptionMedicineMapper hisPrescriptionMedicineMapper;

    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    HisPrescriptionService hisPrescriptionService;

    @Autowired
    HisPrescriptionMapper hisPrescriptionMapper;

    private Logger logger = LoggerFactory.getLogger(HisPrescriptionMedicineServiceImpl.class.getSimpleName());

    @Override
    public List<TreeEntity> selectTreeCode() {
        return hisPrescriptionMedicineMapper.selectTreeCode();
    }

    @Override
    public PageBean<HisPrescriptionMedicine> list(PageBean<HisPrescriptionMedicine> pageEntity) {
        pageEntity.setData(hisPrescriptionMedicineMapper.list(pageEntity));
        return pageEntity;
    }

    @Override
    public HisPrescriptionMedicine updateInit(Long id) {
        return hisPrescriptionMedicineMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisPrescriptionMedicine hisPrescriptionMedicine)throws Exception {
        //如果id为空则是新增不然就是更新
        if (EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getId())) {
            HisPrescriptionMedicine checkNumb = hisPrescriptionMedicineMapper.selectByNumb(hisPrescriptionMedicine);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkNumb) && !EmptyUtil.Companion.isNullOrEmpty(checkNumb.getId())) {
                return MessageUtil.createMessage(false, "此药品编号已存在！");
            }
            HisPrescriptionMedicine checkName = hisPrescriptionMedicineMapper.selectByName(hisPrescriptionMedicine);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkName) && !EmptyUtil.Companion.isNullOrEmpty(checkName.getId())) {
                return MessageUtil.createMessage(false, "此药品名称和已存在！");
            }
            HisPrescriptionMedicine maxTreeId = hisPrescriptionMedicineMapper.selectMaxTreeId(hisPrescriptionMedicine.getParentId());

            String treeId = null;
            if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                treeId = maxTreeId.getTreeId();
            }
            treeId = TreeHelper.getInstance().getTreeId(treeId, hisPrescriptionMedicine.getParentId());
            hisPrescriptionMedicine.setTreeId(treeId);
            hisPrescriptionMedicine.setPrescriptionId(hisPrescriptionMedicine.getParentId());

            int flag = hisPrescriptionMedicineMapper.insertSelective(hisPrescriptionMedicine);
            hisPrescriptionService.searchIsAvailable();
            //  log.info("--------------------药方基本明细新增翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            List<HisPrescriptionMedicineTranslate> hisPrescriptionMedicineTranslateArrayList = new ArrayList<>();
            HisPrescriptionMedicineTranslate hisPrescriptionMedicineTranslate = new HisPrescriptionMedicineTranslate();
            BeanUtils.copyProperties(hisPrescriptionMedicine, hisPrescriptionMedicineTranslate);
            translateModels.setUserId(loginUser.getId());
            hisPrescriptionMedicineTranslateArrayList.add(hisPrescriptionMedicineTranslate);
            translateModels.setHisPrescriptionMedicineTranslateList(hisPrescriptionMedicineTranslateArrayList);
            amqpTemplat.convertAndSend("com.ahsj.add.hisPrescriptionMedicine", JsonUtils.serialize(translateModels));
            //log.info(JsonUtils.serialize(translateModels));
            //log.info("--------------------药方基本明细新增翻译发送结束---------------------------");
            if (flag == 0) {
                return MessageUtil.createMessage(false, "保存失败。");
            }
            hisPrescriptionService.searchIsAvailable();
            return MessageUtil.createMessage(true, "保存成功。");
        } else {

            int flag = hisPrescriptionMedicineMapper.updateByPrimaryKeySelective(hisPrescriptionMedicine);
            hisPrescriptionService.searchIsAvailable();

            if (flag == 0) {
                return MessageUtil.createMessage(false, "更新失败。");
            }
            //  log.info("--------------------药方基本明细修改翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            List<HisPrescriptionMedicineTranslate> hisPrescriptionMedicineTranslateArrayList = new ArrayList<>();
            HisPrescriptionMedicineTranslate hisPrescriptionMedicineTranslate = new HisPrescriptionMedicineTranslate();
            BeanUtils.copyProperties(hisPrescriptionMedicine, hisPrescriptionMedicineTranslate);
            translateModels.setUserId(loginUser.getId());
            hisPrescriptionMedicineTranslateArrayList.add(hisPrescriptionMedicineTranslate);
            translateModels.setHisPrescriptionMedicineTranslateList(hisPrescriptionMedicineTranslateArrayList);
            amqpTemplat.convertAndSend("com.ahsj.update.hisPrescriptionMedicine", JsonUtils.serialize(translateModels));
            //  log.info(JsonUtils.serialize(translateModels));
            // log.info("--------------------药方基本明细修改翻译发送结束---------------------------");
            hisPrescriptionService.searchIsAvailable();
            return MessageUtil.createMessage(true, "更新成功。");

        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Description 根据处方ID搜索对应药品信息，但是要将pharmacy_id 取别名为id，为适应自定义表格selfTable
     * @Params [prescriptionId]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 11:10
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisPrescriptionMedicine> selectByPrescriptionIdForTable(String prescriptionId)throws Exception {
        return hisPrescriptionMedicineMapper.selectByPrescriptionIdForTable(prescriptionId);
    }

    /**
     * @return core.message.Message
     * @Description 为药方添加药品信息
     * @Params [hisPrescriptionMedicine]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:10
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdateForDetails(Long[] ids, String prescriptionId, Integer[] nums, String[] descriptions, String[] remark) throws Exception {
        if (ids.length == 0)
            return MessageUtil.createMessage(false, "请先选择药品再提交");
        if(descriptions.length< ids.length || nums.length<ids.length)
            return MessageUtil.createMessage(false, "请将信息填写完全，药品的数量与用法用量必须填写");

        List<HisPrescriptionMedicine> hisPrescriptionMedicineList = new ArrayList<>();
        //确定药品的tree_id
        HisPrescriptionMedicine maxTreeId = hisPrescriptionMedicineMapper.selectMaxTreeId(prescriptionId);
        String treeId = null;
        if (maxTreeId != null && maxTreeId.getTreeId() != null) {
            treeId = maxTreeId.getTreeId();
        }
        treeId = TreeHelper.getInstance().getTreeId(treeId, prescriptionId);
        //设置此变量是为了记录第一次计算出的树节点唯一标识
        Long normalTreeId = Long.valueOf(treeId);
        //删除所有与prescriptionId对应的药方药品明细
        hisPrescriptionMedicineMapper.deleteByPrescriptionId(prescriptionId);
        for (int i = 0; i < ids.length; i++) {
            //根据药库的id查询出对应的药库药品信息
            if(!EmptyUtil.Companion.isNullOrEmpty(ids[i])) {
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPrimaryKey(ids[i]);
                HisPrescriptionMedicine hisPrescriptionMedicine = new HisPrescriptionMedicine();
                normalTreeId += i;
                hisPrescriptionMedicine.setTreeId(normalTreeId.toString());
                hisPrescriptionMedicine.setPrescriptionId(prescriptionId);
                hisPrescriptionMedicine.setMedicineId(hisPharmacyDetail.getMedicineId());
                hisPrescriptionMedicine.setDescription(descriptions[i]);
                hisPrescriptionMedicine.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                hisPrescriptionMedicine.setDrugsName(hisPharmacyDetail.getDrugsName());
                hisPrescriptionMedicine.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsAlias()))
                    hisPrescriptionMedicine.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getPrescription()))
                    hisPrescriptionMedicine.setPrescription(hisPharmacyDetail.getPrescription());
                hisPrescriptionMedicine.setMentalMedicine(hisPharmacyDetail.getMentalMedicine());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getLargeUnit()))
                    hisPrescriptionMedicine.setLargeUnit(hisPharmacyDetail.getLargeUnit());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSmallUnit()))
                    hisPrescriptionMedicine.setSmallUnit(hisPharmacyDetail.getSmallUnit());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getConversionRate()))
                    hisPrescriptionMedicine.setConversionRate(hisPharmacyDetail.getConversionRate());
                hisPrescriptionMedicine.setDisable(hisPharmacyDetail.getDisable());
                hisPrescriptionMedicine.setLevel(hisPharmacyDetail.getLevel());
                hisPrescriptionMedicine.setMedicalInsuranceSign(hisPharmacyDetail.getMedicalInsuranceSign());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getPlaceorigin()))
                    hisPrescriptionMedicine.setPlaceorigin(hisPharmacyDetail.getPlaceorigin());
                hisPrescriptionMedicine.setBaseMedicine(hisPharmacyDetail.getBaseMedicine());
                hisPrescriptionMedicine.setNarcoticDrugs(hisPharmacyDetail.getNarcoticDrugs());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getId()))
                    hisPrescriptionMedicine.setPharmacyId(hisPharmacyDetail.getId());
                hisPrescriptionMedicine.setCount(nums[i]);
                if(i <= remark.length-1)
                    hisPrescriptionMedicine.setRemarks(remark[i]);
                hisPrescriptionMedicineList.add(hisPrescriptionMedicine);
            }
        }
        saveForMultiple(hisPrescriptionMedicineList);
        return MessageUtil.createMessage(true, "药方信息更新成功");

    }

    /**
     * @return void
     * @Description 批量新增
     * @Params [hisPrescriptionMedicineList]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:30
     **/
    @Override
    @Transactional(readOnly = false)
    public void saveForMultiple(List<HisPrescriptionMedicine> hisPrescriptionMedicineList) throws Exception {
        hisPrescriptionMedicineMapper.insertBatch(hisPrescriptionMedicineList);

        // log.info("--------------------药方基本明细新增翻译发送开始---------------------------");
        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        List<HisPrescriptionMedicineTranslate> hisPrescriptionMedicineTranslateArrayList = new ArrayList<>();
        for (HisPrescriptionMedicine hisPrescriptionMedicine : hisPrescriptionMedicineList) {
            System.out.println(hisPrescriptionMedicine.getId() + "<---------------------------------------------------");
            HisPrescriptionMedicineTranslate hisPrescriptionMedicineTranslate = new HisPrescriptionMedicineTranslate();
            BeanUtils.copyProperties(hisPrescriptionMedicine, hisPrescriptionMedicineTranslate);
            hisPrescriptionMedicineTranslateArrayList.add(hisPrescriptionMedicineTranslate);
        }
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisPrescriptionMedicineTranslateList(hisPrescriptionMedicineTranslateArrayList);
        amqpTemplat.convertAndSend("com.ahsj.add.hisPrescriptionMedicine", JsonUtils.serialize(translateModels));
        // log.info(JsonUtils.serialize(translateModels));
        // log.info("--------------------药方基本明细新增翻译发送结束---------------------------");
        hisPrescriptionService.searchIsAvailable();

    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Description 根据药方ID显示对应药品详细信息
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 17:16
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisPrescriptionMedicine> listForDetails(PageBean<HisPrescriptionMedicine> pageBean)throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisPrescriptionMedicineMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @param pageBean
     * @Description 根据药方id查找明细
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Date 2019/8/27
     * @Time 17:46
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisPrescriptionMedicine> listDetail(PageBean<HisPrescriptionMedicine> pageBean)throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisPrescriptionMedicineMapper.listDetail(pageBean)));
        return pageBean;
    }

    /**
     * @param id
     * @Description 添加药方模板
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Date 2019/8/27
     * @Time 18:05
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisPrescriptionMedicine> selectByPrescriptionIdForTables(Long id)throws Exception {
        return hisPrescriptionMedicineMapper.selectByPrescriptionIdForTables(id);
    }

    /**
     * @param  hisPrescriptionMedicine
     * @param request
     * @param response
     * @param session
     * @Description 导出药方
     * @Author: dingli
     * @return: void
     * @Date 2019/9/2
     * @Time 10:39
     **/
    @Override
    @Transactional(readOnly = true)
    public void exportExcels(HisPrescriptionMedicine hisPrescriptionMedicine, String param,
                             HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        List<HisPrescriptionMedicine> hisPrescriptionMedicineAll = CodeHelper.getInstance().setCodeValue(hisPrescriptionMedicineMapper.exportHisPrescriptionMedicine(hisPrescriptionMedicine));
        if (StringUtils.equals(param, Constants.HIS_CH)) {//中文导出
            psth = this.getClass().getClassLoader().getResource("templates/excel/export/PrescriptionMedicine_CH.xlsx").getPath();  //目标路径
            psth =Constants.HIS_SYS_EXCEL_PRESCRIPTION_CH_FILE_URL;
            //   logger.info("------------导出药方表中文版------------");
        }
        if(StringUtils.equals(param, Constants.HIS_EN)){
            //  logger.info("------------导出药方表英文版------------");
        }
        if (StringUtils.equals(param, Constants.HIS_KM)) {//高棉语导出
            psth = this.getClass().getClassLoader().getResource("templates/excel/export/PrescriptionMedicine_EN.xlsx").getPath();  //目标路径
            psth =Constants.HIS_SYS_EXCEL_PRESCRIPTION_KM_FILE_URL;
            for (HisPrescriptionMedicine p : hisPrescriptionMedicineAll) {
                Translate translate = new Translate();//level 药品级别
                translate.setTranslateId(p.getLevelId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                translates.stream().filter(f -> f.getTranslateChina().equals(p.getLevelName())).forEach(e ->p.setLevelName(e.getTranslateKhmer()));

                Translate translate1 = new Translate();//medical_insurance_sign 药品类型
                translate1.setTranslateId(p.getSignId());
                translate1.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates1 = iTranslateService.queryTranslate(translate1);
                translates1.stream().filter(f -> f.getTranslateChina().equals(p.getMedicalInsuranceSignName())).forEach(e ->p.setMedicalInsuranceSignName(e.getTranslateKhmer()));

                Translate translate2 = new Translate();//disable 是否启用
                translate2.setTranslateId(p.getDisableId());
                translate2.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates2 = iTranslateService.queryTranslate(translate2);
                translates2.stream().filter(f -> f.getTranslateChina().equals(p.getDisableName())).forEach(e ->p.setDisableName(e.getTranslateKhmer()));

                Translate translate3 = new Translate();//prescription 处方药
                translate3.setTranslateId(p.getPreId());
                translate3.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates3 = iTranslateService.queryTranslate(translate3);
                translates3.stream().filter(f -> f.getTranslateChina().equals(p.getPrescriptionName())).forEach(e ->p.setPrescriptionName(e.getTranslateKhmer()));

                Translate translate4 = new Translate();//mental_medicine 精神药品
                translate4.setTranslateId(p.getMentalMedicineId());
                translate4.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates4 = iTranslateService.queryTranslate(translate4);
                translates4.stream().filter(f -> f.getTranslateChina().equals(p.getMentalMedicineName())).forEach(e ->p.setMentalMedicineName(e.getTranslateKhmer()));

                Translate translate5 = new Translate();//base_medicine 是否基药
                translate5.setTranslateId(p.getBaseMedicineId());
                translate5.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates5 = iTranslateService.queryTranslate(translate5);
                translates5.stream().filter(f -> f.getTranslateChina().equals(p.getBaseMedicineName())).forEach(e ->p.setBaseMedicineName(e.getTranslateKhmer()));

                Translate translate6 = new Translate();//narcotic_drugs 是否麻醉药品
                translate6.setTranslateId(p.getNarcoticDrugsId());
                translate6.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates6 = iTranslateService.queryTranslate(translate6);
                translates6.stream().filter(f -> f.getTranslateChina().equals(p.getNarcoticDrugsName())).forEach(e ->p.setNarcoticDrugsName(e.getTranslateKhmer()));

                Translate translate7 = new Translate();//药方名称 描述
                translate7.setTranslateId(p.getpId());
                translate7.setTranslateType(Constants.TRANSLATE_HIS_HISPRESCRIPTION);
                List<Translate> translates7 = iTranslateService.queryTranslate(translate7);
                translates7.stream().filter(f -> f.getTranslateChina().equals(p.getName())).forEach(e ->p.setName(e.getTranslateKhmer()));
                translates7.stream().filter(f -> f.getTranslateChina().equals(p.getDescrption())).forEach(e ->p.setDescrption(e.getTranslateKhmer()));

                Translate translate8 = new Translate();//药品翻译
                translate8.setTranslateId(p.getDrugsId());
                translate8.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                List<Translate> translates8 = iTranslateService.queryTranslate(translate8);
                translates8.stream().filter(f -> f.getTranslateChina().equals(p.getDrugsName())).forEach(e ->p.setDrugsName(e.getTranslateKhmer()));
                translates8.stream().filter(f -> f.getTranslateChina().equals(p.getDrugsAlias())).forEach(e ->p.setDrugsAlias(e.getTranslateKhmer()));
                translates8.stream().filter(f -> f.getTranslateChina().equals(p.getDrugsSpec())).forEach(e ->p.setDrugsSpec(e.getTranslateKhmer()));

                Translate translate9 = new Translate();//用法用量  description
                translate9.setTranslateId(p.getId());
                translate9.setTranslateType(Constants.TRANSLATE_HIS_HISPRESCRIPTIONMEDICINE);
                List<Translate> translates9 = iTranslateService.queryTranslate(translate9);
                translates9.stream().filter(f -> f.getTranslateChina().equals(p.getDescription())).forEach(e ->p.setDescription(e.getTranslateKhmer()));
            }
            //   logger.info("------------导出药方表高棉语版------------");
        }
        beans.put("hisPrescriptionMedicine", hisPrescriptionMedicineAll);
        if (StringUtils.equals(param, Constants.HIS_KM)) {//高棉语导出
        JxlsUtil.export(request, response, psth, "Prescription information record", beans);}
        if (StringUtils.equals(param, Constants.HIS_CH)) {//中文导出
            JxlsUtil.export(request, response, psth, "药方信息表", beans);}
        return;
    }

    /**
     *@Description 药方excel导入
     *@Params [list]
     *@return java.util.Map
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 10:13
    **/
    @Override
    @Transactional(readOnly = false)
    public Map importExcel(List<HisPrescriptionMedicine> listForParentMenu, List<HisPrescriptionMedicine> listForName, List<HisPrescriptionMedicine> list)throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        //插入药方父菜单
        if(listForParentMenu.size() > 0){
            for (HisPrescriptionMedicine forParentMenu : listForParentMenu) {
                //根据药方（父菜单）名称搜索
                HisPrescription hisPrescription = new HisPrescription();
                hisPrescription.setName(forParentMenu.getParentMenu());
                HisPrescription checkName = hisPrescriptionMapper.selectByName(hisPrescription);
                if(EmptyUtil.Companion.isNullOrEmpty(checkName)){
                    hisPrescription.setParentId("1");
                    //查询出此ParentId下最大的treeId
                    HisPrescription maxTreeId = hisPrescriptionMapper.selectMaxTreeId(hisPrescription.getParentId());

                    String treeId = null;
                    if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                        treeId = maxTreeId.getTreeId();
                    }
            /*此步是封装好的操作
            如果上一步if判断中 treeId获得了值那么就使其最后一位+1  如1001001变为1001002
            如果if判断中 treeId没有获得值 那么就令其ParentId后加001 如1001变为1001001
            通俗来说，此步的作用也就是给当前对象的treeId赋值
             */
                    treeId = TreeHelper.getInstance().getTreeId(treeId, hisPrescription.getParentId());
                    hisPrescription.setTreeId(treeId);
                    hisPrescription.setName(forParentMenu.getParentMenu());
                    hisPrescription.setDescrption("药方父菜单");
                    hisPrescriptionService.saveOrUpdate(hisPrescription);
                }
            }
            //批量添加

        }
        if(listForName.size() > 0){
            //根据药方名称搜索
            for (HisPrescriptionMedicine hisPrescriptionMedicine : listForName) {
                //根据药方父菜单名称搜索,获取父菜单ID
                HisPrescription check = new HisPrescription();
                HisPrescription hisPrescription = new HisPrescription();
                check.setName(hisPrescriptionMedicine.getParentMenu());
                HisPrescription ForParentId = hisPrescriptionMapper.selectByName(check);

                check.setName(hisPrescriptionMedicine.getName());
                HisPrescription checkName = hisPrescriptionMapper.selectByName(hisPrescription);

                if(EmptyUtil.Companion.isNullOrEmpty(checkName)){
                    hisPrescription.setName(hisPrescriptionMedicine.getName());
                    hisPrescription.setParentId(ForParentId.getTreeId());
                    hisPrescription.setDescrption(hisPrescriptionMedicine.getDescrption());
                    HisPrescription maxTreeId = hisPrescriptionMapper.selectMaxTreeId(hisPrescription.getParentId());

                    String treeId = null;
                    if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                        treeId = maxTreeId.getTreeId();
                    }
            /*此步是封装好的操作
            如果上一步if判断中 treeId获得了值那么就使其最后一位+1  如1001001变为1001002
            如果if判断中 treeId没有获得值 那么就令其ParentId后加001 如1001变为1001001
            通俗来说，此步的作用也就是给当前对象的treeId赋值
             */
                    treeId = TreeHelper.getInstance().getTreeId(treeId, hisPrescription.getParentId());
                    hisPrescription.setTreeId(treeId);
                }else{
                    hisPrescription.setParentId(checkName.getParentId());
                    hisPrescription.setName(checkName.getName());
                    hisPrescription.setDescrption(hisPrescriptionMedicine.getDescrption());
                    hisPrescription.setId(checkName.getId());
                }
                hisPrescriptionService.saveOrUpdate(hisPrescription);
            }
        }
        //插入药方药品明细
        if(list.size() > 0){
            List<Long> ids=new ArrayList<>();
            List<Integer> nums=new ArrayList<>();
            List<String> descriptions=new ArrayList<>();
            String prescriptionId = "";
            for (HisPrescriptionMedicine hisPrescriptionMedicine : list) {
                HisPrescription check = new HisPrescription();
                check.setName(hisPrescriptionMedicine.getName());
                HisPrescription ForPrescriptionId = hisPrescriptionMapper.selectByName(check);
                //比较treeId如果不同说明药方所属名称已经改变
                if(!StringUtils.equals(ForPrescriptionId.getTreeId(),prescriptionId)) {
                    if(!EmptyUtil.Companion.isNullOrEmpty(prescriptionId) &&!StringUtils.equals("",prescriptionId))
                        saveOrUpdateForImport(ids,prescriptionId,nums,descriptions);
                    prescriptionId = ForPrescriptionId.getTreeId();
                    ids.clear();
                    nums.clear();
                    descriptions.clear();
                }
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByDrugsNumb(hisPrescriptionMedicine.getDrugsNumb());
                ids.add(hisPharmacyDetail.getId());
                nums.add(hisPrescriptionMedicine.getCount());
                descriptions.add(hisPrescriptionMedicine.getDescription());
            }
            //处理最后一次药方相关明细
            if(ids.size() > 0)
                saveOrUpdateForImport(ids,prescriptionId,nums,descriptions);
        }
        return map;
    }

    /**
     *@Description excel导入批量插入药方明细
     *@Params [ids, prescriptionId, nums, descriptions]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 15:23
    **/
    private void saveOrUpdateForImport(List<Long> ids, String prescriptionId, List<Integer> nums, List<String> descriptions)throws Exception {
        List<HisPrescriptionMedicine> hisPrescriptionMedicineList = new ArrayList<>();
        //确定药品的tree_id
        HisPrescriptionMedicine maxTreeId = hisPrescriptionMedicineMapper.selectMaxTreeId(prescriptionId);
        String treeId = null;
        if (maxTreeId != null && maxTreeId.getTreeId() != null) {
            treeId = maxTreeId.getTreeId();
        }
        treeId = TreeHelper.getInstance().getTreeId(treeId, prescriptionId);
        //设置此变量是为了记录第一次计算出的树节点唯一标识
        Long normalTreeId = Long.valueOf(treeId);
        //删除所有与prescriptionId对应的药方药品明细
        hisPrescriptionMedicineMapper.deleteByPrescriptionId(prescriptionId);
        for (int i = 0; i < ids.size(); i++) {
            //根据药库的id查询出对应的药库药品信息
            if(!EmptyUtil.Companion.isNullOrEmpty(ids.get(i))) {
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPrimaryKey(ids.get(i));
                HisPrescriptionMedicine hisPrescriptionMedicine = new HisPrescriptionMedicine();
                normalTreeId += i;
                hisPrescriptionMedicine.setTreeId(normalTreeId.toString());
                hisPrescriptionMedicine.setPrescriptionId(prescriptionId);
                hisPrescriptionMedicine.setMedicineId(hisPharmacyDetail.getMedicineId());
                hisPrescriptionMedicine.setDescription(descriptions.get(i));
                hisPrescriptionMedicine.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                hisPrescriptionMedicine.setDrugsName(hisPharmacyDetail.getDrugsName());
                hisPrescriptionMedicine.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsAlias()))
                    hisPrescriptionMedicine.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getPrescription()))
                    hisPrescriptionMedicine.setPrescription(hisPharmacyDetail.getPrescription());
                hisPrescriptionMedicine.setMentalMedicine(hisPharmacyDetail.getMentalMedicine());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getLargeUnit()))
                    hisPrescriptionMedicine.setLargeUnit(hisPharmacyDetail.getLargeUnit());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSmallUnit()))
                    hisPrescriptionMedicine.setSmallUnit(hisPharmacyDetail.getSmallUnit());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getConversionRate()))
                    hisPrescriptionMedicine.setConversionRate(hisPharmacyDetail.getConversionRate());
                hisPrescriptionMedicine.setDisable(hisPharmacyDetail.getDisable());
                hisPrescriptionMedicine.setLevel(hisPharmacyDetail.getLevel());
                hisPrescriptionMedicine.setMedicalInsuranceSign(hisPharmacyDetail.getMedicalInsuranceSign());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getPlaceorigin()))
                    hisPrescriptionMedicine.setPlaceorigin(hisPharmacyDetail.getPlaceorigin());
                hisPrescriptionMedicine.setBaseMedicine(hisPharmacyDetail.getBaseMedicine());
                hisPrescriptionMedicine.setNarcoticDrugs(hisPharmacyDetail.getNarcoticDrugs());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getId()))
                    hisPrescriptionMedicine.setPharmacyId(hisPharmacyDetail.getId());
                hisPrescriptionMedicine.setCount(nums.get(i));
                hisPrescriptionMedicineList.add(hisPrescriptionMedicine);
            }
        }
        saveForMultiple(hisPrescriptionMedicineList);
    }
    
    /**
     *@Description 查看门诊病历模板中的药方明细
     *@Params 
     *@return 
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 17:43
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisPrescriptionMedicine> viewDetailInMedicalTemplate(PageBean<HisPrescriptionMedicine> pageEntity) throws Exception {
        pageEntity.setData(CodeHelper.getInstance().setCodeValue(hisPrescriptionMedicineMapper.viewDetailInMedicalTemplate(pageEntity)));
        return pageEntity;
    }

    /**
     * @return core.message.Message
     * @Description 为药方添加药品信息
     * @Params [hisPrescriptionMedicine]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:10
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdateForDetailsForTemplate(Long[] ids, String prescriptionId, Integer[] nums, String[] descriptions) throws Exception {

        List<HisPrescriptionMedicine> hisPrescriptionMedicineList = new ArrayList<>();
        //确定药品的tree_id
        HisPrescriptionMedicine maxTreeId = hisPrescriptionMedicineMapper.selectMaxTreeId(prescriptionId);
        String treeId = null;
        if (maxTreeId != null && maxTreeId.getTreeId() != null) {
            treeId = maxTreeId.getTreeId();
        }
        treeId = TreeHelper.getInstance().getTreeId(treeId, prescriptionId);
        //设置此变量是为了记录第一次计算出的树节点唯一标识
        Long normalTreeId = Long.valueOf(treeId);
        //删除所有与prescriptionId对应的药方药品明细
        hisPrescriptionMedicineMapper.deleteByPrescriptionId(prescriptionId);
        for (int i = 0; i < ids.length; i++) {
            //根据药库的id查询出对应的药库药品信息
            if(!EmptyUtil.Companion.isNullOrEmpty(ids[i])) {
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPrimaryKey(ids[i]);
                HisPrescriptionMedicine hisPrescriptionMedicine = new HisPrescriptionMedicine();
                normalTreeId += i;
                hisPrescriptionMedicine.setTreeId(normalTreeId.toString());
                hisPrescriptionMedicine.setPrescriptionId(prescriptionId);
                hisPrescriptionMedicine.setMedicineId(hisPharmacyDetail.getMedicineId());
                hisPrescriptionMedicine.setDescription(descriptions[i]);
                hisPrescriptionMedicine.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
                hisPrescriptionMedicine.setDrugsName(hisPharmacyDetail.getDrugsName());
                hisPrescriptionMedicine.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsAlias()))
                    hisPrescriptionMedicine.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getPrescription()))
                    hisPrescriptionMedicine.setPrescription(hisPharmacyDetail.getPrescription());
                hisPrescriptionMedicine.setMentalMedicine(hisPharmacyDetail.getMentalMedicine());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getLargeUnit()))
                    hisPrescriptionMedicine.setLargeUnit(hisPharmacyDetail.getLargeUnit());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSmallUnit()))
                    hisPrescriptionMedicine.setSmallUnit(hisPharmacyDetail.getSmallUnit());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getConversionRate()))
                    hisPrescriptionMedicine.setConversionRate(hisPharmacyDetail.getConversionRate());
                hisPrescriptionMedicine.setDisable(hisPharmacyDetail.getDisable());
                hisPrescriptionMedicine.setLevel(hisPharmacyDetail.getLevel());
                hisPrescriptionMedicine.setMedicalInsuranceSign(hisPharmacyDetail.getMedicalInsuranceSign());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getPlaceorigin()))
                    hisPrescriptionMedicine.setPlaceorigin(hisPharmacyDetail.getPlaceorigin());
                hisPrescriptionMedicine.setBaseMedicine(hisPharmacyDetail.getBaseMedicine());
                hisPrescriptionMedicine.setNarcoticDrugs(hisPharmacyDetail.getNarcoticDrugs());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getId()))
                    hisPrescriptionMedicine.setPharmacyId(hisPharmacyDetail.getId());
                hisPrescriptionMedicine.setCount(nums[i]);
                hisPrescriptionMedicineList.add(hisPrescriptionMedicine);
            }
        }
        saveForMultiple(hisPrescriptionMedicineList);
        return MessageUtil.createMessage(true, "药方信息更新成功");

    }
}
