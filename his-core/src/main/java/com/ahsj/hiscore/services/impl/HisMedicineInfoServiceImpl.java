package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicineInfoMapper;
import com.ahsj.hiscore.dao.HisPharmacyDetailMapper;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.TranslateModel.HisMedicineInfoTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateDelete;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.entity.model.TranslateModel;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisMedicineInfoService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
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
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HisMedicineInfoServiceImpl implements HisMedicineInfoService {

    private Logger log = LoggerFactory.getLogger(HisMedicineInfoServiceImpl.class.getSimpleName());


    @Autowired
    HisMedicineInfoMapper hisMedicineInfoMapper;

    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    ICodeService iCodeService;

    /**
     * @return
     * @Description 新增或更新
     * @Params
     * @Author zhushixiang
     * @Date 2019-06-12
     * @Time 21:39
     **/
    @Override
    //数据库事务
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedicineInfo hisMedicineInfo) throws Exception {
        HisMedicineInfo checkForDrugsNumb = hisMedicineInfoMapper.selectByDrugsNumb(hisMedicineInfo.getDrugsNumb());

        //id是null就是新增，否则是更新
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getId())) {
//            HisMedicineInfo checkForDrugsNameAndSpec = hisMedicineInfoMapper.selectByDrugsNameAndSpec(hisMedicineInfo);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkForDrugsNumb))
                return MessageUtil.createMessage(false, "新增失败!该药品编号在库中已存在！");
//            if (EmptyUtil.Companion.isNullOrEmpty(checkForDrugsNameAndSpec)) {
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getEnterPrice()))
                    hisMedicineInfo.setEnterPrice(0.00);
                hisMedicineInfoMapper.insert(hisMedicineInfo);
                //   log.info("--------------------药品项目新增翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisMedicineInfoTranslate hisMedicineInfoTranslate = new HisMedicineInfoTranslate();
                BeanUtils.copyProperties(hisMedicineInfo, hisMedicineInfoTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisMedicineInfoTranslate(hisMedicineInfoTranslate);
                amqpTemplat.convertAndSend("com.ahsj.addHisMedicineInfo", JsonUtils.serialize(translateModels));
                //  log.info(JsonUtils.serialize(translateModels));
                //   log.info("--------------------药品项目新增翻译发送结束---------------------------");
                return MessageUtil.createMessage(true, "插入成功!");
//            }
        /*else {
                return MessageUtil.createMessage(false, "插入失败!该药品在库中已存在！(药品名称与规格相同)");
            }*/
        } else {
            HisMedicineInfo checkId = hisMedicineInfoMapper.selectByPrimaryKey(hisMedicineInfo.getId());
//            HisMedicineInfo checkForDrugsNameAndSpec = hisMedicineInfoMapper.selectByDrugsNameAndSpec(hisMedicineInfo);
            //先检测id是否存在
            if (!EmptyUtil.Companion.isNullOrEmpty(checkId)) {
                //修改药品编号时需要确保此药品编号不与药库中其他药品的药品编号相等
                if (!EmptyUtil.Companion.isNullOrEmpty(checkForDrugsNumb)) {
                    if (!checkForDrugsNumb.getId().equals(hisMedicineInfo.getId()))
                        return MessageUtil.createMessage(false, "更新失败!此药品编号在药品中已经存在");
                }
//                if (!EmptyUtil.Companion.isNullOrEmpty(checkForDrugsNameAndSpec)) {
//                    if (!checkForDrugsNameAndSpec.getId().equals(hisMedicineInfo.getId()))
//                        return MessageUtil.createMessage(false, "更新失败!此药品名称与规格在药品中已经存在");
//                }
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByDrugsNumb(hisMedicineInfoMapper.selectByPrimaryKey(hisMedicineInfo.getId()).getDrugsNumb());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail)) {
                    hisPharmacyDetail.setDrugsNumb(hisMedicineInfo.getDrugsNumb());
                    hisPharmacyDetail.setDrugsName(hisMedicineInfo.getDrugsName());
                    hisPharmacyDetail.setDrugsSpec(hisMedicineInfo.getDrugsSpec());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getDrugsAlias()))
                        hisPharmacyDetail.setDrugsAlias(hisMedicineInfo.getDrugsAlias());
                    hisPharmacyDetail.setPrescription(hisMedicineInfo.getPrescription());
                    hisPharmacyDetail.setMentalMedicine(hisMedicineInfo.getMentalMedicine());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getLargeUnit()))
                        hisPharmacyDetail.setLargeUnit(hisMedicineInfo.getLargeUnit());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getSmallUnit()))
                        hisPharmacyDetail.setSmallUnit(hisMedicineInfo.getSmallUnit());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getConversionRate()))
                        hisPharmacyDetail.setConversionRate(hisMedicineInfo.getConversionRate());
                    hisPharmacyDetail.setLevel(hisMedicineInfo.getLevel());
                    hisPharmacyDetail.setMedicalInsuranceSign(hisMedicineInfo.getMedicalInsuranceSign());
                    hisPharmacyDetail.setPlaceorigin(hisMedicineInfo.getPlaceorigin());
                    hisPharmacyDetail.setBaseMedicine(hisMedicineInfo.getBaseMedicine());
                    hisPharmacyDetail.setNarcoticDrugs(hisMedicineInfo.getNarcoticDrugs());
                    if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getEnterPrice()))
                        hisPharmacyDetail.setEnterPrice(new BigDecimal(0));
                    else
                        hisPharmacyDetail.setEnterPrice(BigDecimal.valueOf(hisMedicineInfo.getEnterPrice()));
                    hisPharmacyDetailService.saveOrUpdate(hisPharmacyDetail);
                }
                hisMedicineInfoMapper.updateByPrimaryKeySelective(hisMedicineInfo);
                //   log.info("--------------------药品项目修改翻译发送开始--------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisMedicineInfoTranslate hisMedicineInfoTranslate = new HisMedicineInfoTranslate();
                BeanUtils.copyProperties(hisMedicineInfo, hisMedicineInfoTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisMedicineInfoTranslate(hisMedicineInfoTranslate);
                amqpTemplat.convertAndSend("com.ahsj.updateHisMedicineInfo", JsonUtils.serialize(translateModels));
                //  log.info(JsonUtils.serialize(translateModels));
                //  log.info("--------------------药品项目修改翻译发送结束--------------------------");

                return MessageUtil.createMessage(true, "修改成功");
            } else {
                return MessageUtil.createMessage(false, "更新失败!无此药品信息");
            }
        }
    }

    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-12
     * @Time 22:05
     **/
    @Override
    //数据库事务
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisMedicineInfoMapper.deleteByPrimaryKey(id);
            // log.info("--------------------药品项目删除翻译发送开始--------------------------");
            TranslateDelete translateDelete = new TranslateDelete();
            translateDelete.setId(id);
            translateDelete.setModel(Constants.TRANSLATE_HIS_MEDICINEINFO);
            amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
            //  log.info(JsonUtils.serialize(translateDelete));
            // log.info("--------------------药品项目删除翻译发送结束--------------------------");
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }


    /**
     *@Description list
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicineInfo>
     *@Author zhushixiang
     *@Date 2019-08-12
     *@Time 9:57
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicineInfo> list(PageBean<HisMedicineInfo> pageBean) throws Exception {
        List<HisMedicineInfo> list = hisMedicineInfoMapper.list(pageBean);
        List<HisMedicineInfo> listandenglish = hisMedicineInfoMapper.listandenglish();
        for (HisMedicineInfo hisMedicineInfo : list) {
            hisMedicineInfo.setDrugsName(hisMedicineInfo.getDrugsName()+"("+hisMedicineInfo.getTranslateKhmer()+")");
            for (HisMedicineInfo medicineInfo : listandenglish) {
                if (StringUtils.equals(medicineInfo.getDrugsSpec(),hisMedicineInfo.getDrugsSpec())){
                    if (medicineInfo.getDrugsSpec().indexOf("支") != -1){
                        hisMedicineInfo.setDrugsSpec(medicineInfo.getDrugsSpec()+"("+medicineInfo.getTranslateKhmer()+"bottle)");
                    }else {
                        hisMedicineInfo.setDrugsSpec(medicineInfo.getDrugsSpec()+"("+medicineInfo.getTranslateKhmer()+")");
                    }
                }
            }
        }

        pageBean.setData(CodeHelper.getInstance().setCodeValue(list));
      /*  for (int i = 0; i <pageBean.getData().size() ; i++) {
            Translate translate = new Translate();
            translate.setTranslateId(474L);
            translate.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES_LIST);
            translate.setTranslateColumn("value");
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            pageBean.getData().get(i).setDrugsName(translates.get(0).getTranslateKhmer());
        }*/
        return pageBean;
    }

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicineInfo
     *@Author zhushixiang
     *@Date 2019-08-12
     *@Time 9:55
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicineInfo selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.selectByPrimaryKey(id));
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 11:19
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicineInfo> queryTranslateInfoByDate(HisMedicineInfo hisMedicineInfo) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<HisMedicineInfo> translateList = hisMedicineInfoMapper.queryTranslateInfoByDate(hisMedicineInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 17:08
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicineInfo> queryAll() throws Exception {
        List<HisMedicineInfo> hisMedicineInfos = hisMedicineInfoMapper.queryAll();
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfos)) {
            log.info("查询失败");
            return new ArrayList<>();
        } else {
            return hisMedicineInfos;
        }
    }

    /**
     * @return
     * @Description 检测药品名称和规格是否相同
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-12
     * @Time 21:56
     **/
    private boolean noSameNameAndSepc(HisMedicineInfo one, HisMedicineInfo two) {
        if (!one.getDrugsName().equals(two.getDrugsName()) && !one.getDrugsSpec().equals(two.getDrugsSpec())) {
            return true;
        } else return false;
    }


    public void toTranslate(Object obj, Class<?> clazz, Long id, String constants) {
        if (obj == null) {
            return;
        }
        List<Translate> list = new ArrayList<>();
        TranslateModel model = new TranslateModel();
        BaseLoginUser loginUser = new BaseLoginUser();
        model.setUserId(loginUser.getId());
        Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
        String s = "";
        try {
            for (Field f : fields) {
                Translate translate = new Translate();
                translate.setTranslateId(id);
                translate.setTranslateType(constants);
                translate.setTranslateColumn(f.getName());// 得到此属性的名称
                f.setAccessible(true); // 设置些属性是可以访问的
                Object china = f.get(obj);// 得到此属性的值
                if (china == null) {
                    continue;
                }
                translate.setTranslateChina(china.toString());
                list.add(translate);
            }
            model.setTranslateList(list);
            iTranslateService.addTranslate(model);
        } catch (Exception e) {
        }
        //return s;
    }

    /**
     *@Description 查询所有药品信息
     *@Params [hisMedicineInfo]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     *@Author zhushixiang
     *@Date 2019-08-27
     *@Time 11:06
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicineInfo> selectAll(HisMedicineInfo hisMedicineInfo) {
        return CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.selectAll());
    }

    /**
     *@Description 导出Excel
     *@Params [ids, request, response, session]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-28
     *@Time 14:48
    **/
    @Override
    @Transactional(readOnly = true)
    public void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session,String param)throws Exception{
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        if (StringUtils.equals(param,Constants.HIS_CH)) {
            psth = this.getClass().getClassLoader().getResource("templates/excel/export/hismedicine_CH.xlsx").getPath();
            psth = Constants.HIS_SYS_EXCEL_MEDICINE_CH_FILE_URL;
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisMedicineInfo> hisMedicineInfoList = CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.queryListExportAll());
                beans.put("hisMedicineInfoList", hisMedicineInfoList);
                JxlsUtil.export(request, response, psth, "药品基本信息记录", beans);
                return;
            }
            List<HisMedicineInfo> list = new ArrayList<>();
            for (Long id : ids) {
                HisMedicineInfo hisMedicineInfo = new HisMedicineInfo();
                hisMedicineInfo.setId(id);
                list.add(hisMedicineInfo);
            }
            List<HisMedicineInfo> hisMedicineInfoList = CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.queryListExportAndByIdsAll(list));
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfoList)) {
                return;
            } else {
                beans.put("hisMedicineInfoList", hisMedicineInfoList);
                JxlsUtil.export(request, response, psth, "药品基本信息记录", beans);
                return;
            }
        }else if(StringUtils.equals(param,Constants.HIS_KM)){
          //  psth = this.getClass().getClassLoader().getResource("templates/excel/export/hismedicine_EN.xlsx").getPath();
            psth = Constants.HIS_SYS_EXCEL_MEDICINE_KM_FILE_URL;
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisMedicineInfo> hisMedicineInfoList = CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.queryListExportAll());
                for (HisMedicineInfo hisMedicineInfo : hisMedicineInfoList) {
                    Translate translate = new Translate();
                    //字典
                    translate.setTranslateId(hisMedicineInfo.getPrescriptionId());
                    translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translates) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getPrecriptionName())){
                            hisMedicineInfo.setPrecriptionName(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate2 = new Translate();
                    translate2.setTranslateId(hisMedicineInfo.getMentalMedicineId());
                    translate2.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates1 = iTranslateService.queryTranslate(translate2);
                    for (Translate translate1 : translates1) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getMentalmedicine())){
                            hisMedicineInfo.setMentalmedicine(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate3 = new Translate();
                    translate3.setTranslateId(hisMedicineInfo.getLevelId());
                    translate3.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates2 = iTranslateService.queryTranslate(translate3);
                    for (Translate translate1 : translates2) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getLeveld())){
                            hisMedicineInfo.setLeveld(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate4 = new Translate();
                    translate4.setTranslateId(hisMedicineInfo.getMedicalInsuranceSignId());
                    translate4.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates3 = iTranslateService.queryTranslate(translate4);
                    for (Translate translate1 : translates3) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getMedicalinsurancesign())){
                            hisMedicineInfo.setMedicalinsurancesign(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate5 = new Translate();
                    translate5.setTranslateId(hisMedicineInfo.getPlaceoriginId());
                    translate5.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates4 = iTranslateService.queryTranslate(translate5);
                    for (Translate translate1 : translates4) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getPlaceoriginName())){
                            hisMedicineInfo.setPlaceoriginName(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate6 = new Translate();
                    translate6.setTranslateId(hisMedicineInfo.getBaseMedicineId());
                    translate6.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates5 = iTranslateService.queryTranslate(translate6);
                    for (Translate translate1 : translates5) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getBasemedicine())){
                            hisMedicineInfo.setBasemedicine(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate7 = new Translate();
                    translate7.setTranslateId(hisMedicineInfo.getNarcoticDrugsId());
                    translate7.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates6 = iTranslateService.queryTranslate(translate7);
                    for (Translate translate1 : translates6) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getNarcoticdrugs())){
                            hisMedicineInfo.setNarcoticdrugs(translate1.getTranslateKhmer());
                        }
                    }

                    //药品
                    translate.setTranslateId(hisMedicineInfo.getId());
                    translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                    List<Translate> translateHisMedicineInfo = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translateHisMedicineInfo) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getDrugsName())){
                            hisMedicineInfo.setDrugsName(translate1.getTranslateKhmer());
                        }
                        if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getDrugsSpec())){
                            hisMedicineInfo.setDrugsSpec(translate1.getTranslateKhmer());
                        }
                        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getLargeUnit())) {
                            if (StringUtils.equals(translate1.getTranslateChina(), hisMedicineInfo.getLargeUnit())) {
                                hisMedicineInfo.setLargeUnit(translate1.getTranslateKhmer());
                            }
                        }
                        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getSmallUnit())) {
                            if (StringUtils.equals(translate1.getTranslateChina(), hisMedicineInfo.getSmallUnit())) {
                                hisMedicineInfo.setSmallUnit(translate1.getTranslateKhmer());
                            }
                        }
                        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getRemarks())) {
                            if (StringUtils.equals(translate1.getTranslateChina(), hisMedicineInfo.getRemarks())) {
                                hisMedicineInfo.setRemarks(translate1.getTranslateKhmer());
                            }
                        }
                    }


                }
                beans.put("hisMedicineInfoList", hisMedicineInfoList);
                JxlsUtil.export(request, response, psth, "药品基本信息记录", beans);
                return;
            }
            List<HisMedicineInfo> list = new ArrayList<>();
            for (Long id : ids) {
                HisMedicineInfo hisMedicineInfo = new HisMedicineInfo();
                hisMedicineInfo.setId(id);
                list.add(hisMedicineInfo);
            }
            List<HisMedicineInfo> hisMedicineInfoList = CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.queryListExportAndByIdsAll(list));
            for (HisMedicineInfo hisMedicineInfo : hisMedicineInfoList) {
                Translate translate = new Translate();
                //字典
                translate.setTranslateId(hisMedicineInfo.getPrescriptionId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translates) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getPrecriptionName())){
                        hisMedicineInfo.setPrecriptionName(translate1.getTranslateKhmer());
                    }
                }
                Translate translate2 = new Translate();
                translate2.setTranslateId(hisMedicineInfo.getMentalMedicineId());
                translate2.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates1 = iTranslateService.queryTranslate(translate2);
                for (Translate translate1 : translates1) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getMentalmedicine())){
                        hisMedicineInfo.setMentalmedicine(translate1.getTranslateKhmer());
                    }
                }
                Translate translate3 = new Translate();
                translate3.setTranslateId(hisMedicineInfo.getLevelId());
                translate3.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates2 = iTranslateService.queryTranslate(translate3);
                for (Translate translate1 : translates2) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getLeveld())){
                        hisMedicineInfo.setLeveld(translate1.getTranslateKhmer());
                    }
                }
                Translate translate4 = new Translate();
                translate4.setTranslateId(hisMedicineInfo.getMedicalInsuranceSignId());
                translate4.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates3 = iTranslateService.queryTranslate(translate4);
                for (Translate translate1 : translates3) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getMedicalinsurancesign())){
                        hisMedicineInfo.setMedicalinsurancesign(translate1.getTranslateKhmer());
                    }
                }
                Translate translate5 = new Translate();
                translate5.setTranslateId(hisMedicineInfo.getPlaceoriginId());
                translate5.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates4 = iTranslateService.queryTranslate(translate5);
                for (Translate translate1 : translates4) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getPlaceoriginName())){
                        hisMedicineInfo.setPlaceoriginName(translate1.getTranslateKhmer());
                    }
                }
                Translate translate6 = new Translate();
                translate6.setTranslateId(hisMedicineInfo.getBaseMedicineId());
                translate6.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates5 = iTranslateService.queryTranslate(translate6);
                for (Translate translate1 : translates5) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getBasemedicine())){
                        hisMedicineInfo.setBasemedicine(translate1.getTranslateKhmer());
                    }
                }
                Translate translate7 = new Translate();
                translate7.setTranslateId(hisMedicineInfo.getNarcoticDrugsId());
                translate7.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates6 = iTranslateService.queryTranslate(translate7);
                for (Translate translate1 : translates6) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getNarcoticdrugs())){
                        hisMedicineInfo.setNarcoticdrugs(translate1.getTranslateKhmer());
                    }
                }

                //药品
                translate.setTranslateId(hisMedicineInfo.getId());
                translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                List<Translate> translateHisMedicineInfo = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translateHisMedicineInfo) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getDrugsName())){
                        hisMedicineInfo.setDrugsName(translate1.getTranslateKhmer());
                    }
                    if (StringUtils.equals(translate1.getTranslateChina(),hisMedicineInfo.getDrugsSpec())){
                        hisMedicineInfo.setDrugsSpec(translate1.getTranslateKhmer());
                    }
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getLargeUnit())) {
                        if (StringUtils.equals(translate1.getTranslateChina(), hisMedicineInfo.getLargeUnit())) {
                            hisMedicineInfo.setLargeUnit(translate1.getTranslateKhmer());
                        }
                    }
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getSmallUnit())) {
                        if (StringUtils.equals(translate1.getTranslateChina(), hisMedicineInfo.getSmallUnit())) {
                            hisMedicineInfo.setSmallUnit(translate1.getTranslateKhmer());
                        }
                    }
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getRemarks())) {
                        if (StringUtils.equals(translate1.getTranslateChina(), hisMedicineInfo.getRemarks())) {
                            hisMedicineInfo.setRemarks(translate1.getTranslateKhmer());
                        }
                    }
                }
            }
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfoList)) {
                return;
            } else {
                beans.put("hisMedicineInfoList", hisMedicineInfoList);
                JxlsUtil.export(request, response, psth, "Drug basic information form", beans);
                return;
            }
        }else if(StringUtils.equals(param,Constants.HIS_EN)){
            log.info("这里是预留接口，进行翻译英文");
        }
    }

    /**
     *@Description Excel导入药品信息
     *@Params [list]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-29
     *@Time 15:09
    **/
    @Override
    @Transactional(readOnly = false)
    public Map importExcel(List<HisMedicineInfo> excelData)throws Exception {
        List<String> errorData = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer failNum = 0;
        // 循环]
        //从库中将所有的HisMedicine信息全部取出来做数据对比处理
        List<HisMedicineInfo> tempList = hisMedicineInfoMapper.queryMedicineInfoAll();
        //插入的list
        List<HisMedicineInfo> medicineinfoInsertList = new ArrayList<>();
        //更新的list
        List<HisMedicineInfo> medicineinfoUpdateList = new ArrayList<>();
        for (HisMedicineInfo hisMedicineInfo : excelData) {
            boolean updateflag = false;
            for (HisMedicineInfo medicineinfo : tempList) {
                if (medicineinfo.getDrugsNumb().equals(hisMedicineInfo.getDrugsNumb())) {
                    updateflag = true;
                    break;
                }

            }
            if (!updateflag) {
                medicineinfoInsertList.add(hisMedicineInfo);
            } else {
                medicineinfoUpdateList.add(hisMedicineInfo);
            }
        }
        if (medicineinfoInsertList != null && medicineinfoInsertList.size() > 0) {
            for (int i = 0; i < medicineinfoInsertList.size(); i++) {
                //设置字典的值
                String precriptionName = medicineinfoInsertList.get(i).getPrecriptionName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(precriptionName);
                detail.setSysCodeType(Constants.HIS_SYS_CODE_PRESCRIPTION);
                String code = iCodeService.getSysCodeName(detail).getCode();
                Integer precription = Integer.parseInt(code);
                medicineinfoInsertList.get(i).setPrescription(precription);

                String mentalmedicine = medicineinfoInsertList.get(i).getMentalmedicine();
                SysCodeDetail detail2 = new SysCodeDetail();
                detail2.setValue(mentalmedicine);
                detail2.setSysCodeType(Constants.HIS_SYS_CODE_MENTAL_MEDICINE);
                String code2 = iCodeService.getSysCodeName(detail2).getCode();
                Integer mentalMedicine = Integer.parseInt(code2);
                medicineinfoInsertList.get(i).setMentalMedicine(mentalMedicine);

                String leveld = medicineinfoInsertList.get(i).getLeveld();
                SysCodeDetail detail3 = new SysCodeDetail();
                detail3.setValue(leveld);
                detail3.setSysCodeType(Constants.HIS_SYS_CODE_LEVEL);
                String code3 = iCodeService.getSysCodeName(detail3).getCode();
                Integer level = Integer.parseInt(code3);
                medicineinfoInsertList.get(i).setLevel(level);

                String medicalinsurancesign = medicineinfoInsertList.get(i).getMedicalinsurancesign();
                SysCodeDetail detail4 = new SysCodeDetail();
                detail4.setValue(medicalinsurancesign);
                detail4.setSysCodeType(Constants.HIS_SYS_CODE_MEDICAL_INSURANCE_SIGN);
                String code4 = iCodeService.getSysCodeName(detail4).getCode();
                Integer medicalInsuranceSign = Integer.parseInt(code4);
                medicineinfoInsertList.get(i).setMedicalInsuranceSign(medicalInsuranceSign);

                String placeoriginName = medicineinfoInsertList.get(i).getPlaceoriginName();
                SysCodeDetail detail5 = new SysCodeDetail();
                detail5.setValue(placeoriginName);
                detail5.setSysCodeType(Constants.HIS_SYS_CODE_PLACEORIGIN);
                String code5 = iCodeService.getSysCodeName(detail5).getCode();
                Integer placeorigin = Integer.parseInt(code5);
                medicineinfoInsertList.get(i).setPlaceorigin(placeorigin);

                String basemedicine = medicineinfoInsertList.get(i).getBasemedicine();
                SysCodeDetail detail6 = new SysCodeDetail();
                detail6.setValue(basemedicine);
                detail6.setSysCodeType(Constants.HIS_SYS_CODE_BASE_MEDICINE);
                String code6 = iCodeService.getSysCodeName(detail).getCode();
                Integer baseMedicine = Integer.parseInt(code6);
                medicineinfoInsertList.get(i).setBaseMedicine(baseMedicine);

                String narcoticdrugs = medicineinfoInsertList.get(i).getNarcoticdrugs();
                SysCodeDetail detail7 = new SysCodeDetail();
                detail7.setValue(narcoticdrugs);
                detail7.setSysCodeType(Constants.HIS_SYS_CODE_NARCOTIC_DRUGS);
                String code7 = iCodeService.getSysCodeName(detail7).getCode();
                Integer narcoticDrugs = Integer.parseInt(code7);
                medicineinfoInsertList.get(i).setNarcoticDrugs(narcoticDrugs);
                medicineinfoInsertList.get(i).setDisable(1);

                medicineinfoInsertList.get(i).setId(null);
            }
            hisMedicineInfoMapper.importHisMedicineInfo(medicineinfoInsertList);


            TranslateModels translateModels = new TranslateModels();
            BaseLoginUser loginUser = new BaseLoginUser();
            List<HisMedicineInfoTranslate> infoTranslates = new ArrayList<>();
            for (HisMedicineInfo medicineinfo : medicineinfoInsertList) {
                //  log.info("--------------------药品基本信息新增翻译发送开始---------------------------");
                HisMedicineInfoTranslate HisMedicineInfoTranslate = new HisMedicineInfoTranslate();
                BeanUtils.copyProperties(medicineinfo, HisMedicineInfoTranslate);
                infoTranslates.add(HisMedicineInfoTranslate);
                // log.info("--------------------药品基本信息新增翻译发送结束---------------------------");
            }
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicineInfoTranslates(infoTranslates);
            amqpTemplat.convertAndSend("com.ahsj.addHisMedicineInfoList", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));


        }

        if (medicineinfoUpdateList != null && medicineinfoUpdateList.size() > 0) {
            for (int i = 0; i <medicineinfoUpdateList.size() ; i++) {
                Long hid = hisMedicineInfoMapper.queryHisMedicineInfoByDrugsNumb(medicineinfoUpdateList.get(i).getDrugsNumb()).getId();
                //设置字典的值
                String precriptionName = medicineinfoUpdateList.get(i).getPrecriptionName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(precriptionName);
                detail.setSysCodeType(Constants.HIS_SYS_CODE_PRESCRIPTION);
                String code = iCodeService.getSysCodeName(detail).getCode();
                Integer precription = Integer.parseInt(code);
                medicineinfoUpdateList.get(i).setPrescription(precription);

                String mentalmedicine = medicineinfoUpdateList.get(i).getMentalmedicine();
                SysCodeDetail detail2 = new SysCodeDetail();
                detail2.setValue(mentalmedicine);
                detail2.setSysCodeType(Constants.HIS_SYS_CODE_MENTAL_MEDICINE);
                String code2 = iCodeService.getSysCodeName(detail2).getCode();
                Integer mentalMedicine = Integer.parseInt(code2);
                medicineinfoUpdateList.get(i).setMentalMedicine(mentalMedicine);

                String leveld = medicineinfoUpdateList.get(i).getLeveld();
                SysCodeDetail detail3 = new SysCodeDetail();
                detail3.setValue(leveld);
                detail3.setSysCodeType(Constants.HIS_SYS_CODE_LEVEL);
                String code3 = iCodeService.getSysCodeName(detail3).getCode();
                Integer level = Integer.parseInt(code3);
                medicineinfoUpdateList.get(i).setLevel(level);

                String medicalinsurancesign = medicineinfoUpdateList.get(i).getMedicalinsurancesign();
                SysCodeDetail detail4 = new SysCodeDetail();
                detail4.setValue(medicalinsurancesign);
                detail4.setSysCodeType(Constants.HIS_SYS_CODE_MEDICAL_INSURANCE_SIGN);
                String code4 = iCodeService.getSysCodeName(detail4).getCode();
                Integer medicalInsuranceSign = Integer.parseInt(code4);
                medicineinfoUpdateList.get(i).setMedicalInsuranceSign(medicalInsuranceSign);

                String placeoriginName = medicineinfoUpdateList.get(i).getPlaceoriginName();
                SysCodeDetail detail5 = new SysCodeDetail();
                detail5.setValue(placeoriginName);
                detail5.setSysCodeType(Constants.HIS_SYS_CODE_PLACEORIGIN);
                String code5 = iCodeService.getSysCodeName(detail5).getCode();
                Integer placeorigin = Integer.parseInt(code5);
                medicineinfoUpdateList.get(i).setPlaceorigin(placeorigin);

                String basemedicine = medicineinfoUpdateList.get(i).getBasemedicine();
                SysCodeDetail detail6 = new SysCodeDetail();
                detail6.setValue(basemedicine);
                detail6.setSysCodeType(Constants.HIS_SYS_CODE_BASE_MEDICINE);
                String code6 = iCodeService.getSysCodeName(detail).getCode();
                Integer baseMedicine = Integer.parseInt(code6);
                medicineinfoUpdateList.get(i).setBaseMedicine(baseMedicine);

                String narcoticdrugs = medicineinfoUpdateList.get(i).getNarcoticdrugs();
                SysCodeDetail detail7 = new SysCodeDetail();
                detail7.setValue(narcoticdrugs);
                detail7.setSysCodeType(Constants.HIS_SYS_CODE_NARCOTIC_DRUGS);
                String code7 = iCodeService.getSysCodeName(detail7).getCode();
                Integer narcoticDrugs = Integer.parseInt(code7);
                medicineinfoUpdateList.get(i).setNarcoticDrugs(narcoticDrugs);
                medicineinfoUpdateList.get(i).setDisable(1);

                medicineinfoUpdateList.get(i).setId(hid);
            }
            hisMedicineInfoMapper.updateHisMedicineInfo(medicineinfoUpdateList);


            TranslateModels translateModels = new TranslateModels();
            BaseLoginUser loginUser = new BaseLoginUser();
            List<HisMedicineInfoTranslate> infoTranslates = new ArrayList<>();
            for (HisMedicineInfo medicineinfo : medicineinfoUpdateList) {
                // log.info("--------------------药品基本信息修改翻译发送开始---------------------------");
                HisMedicineInfoTranslate HisMedicineInfoTranslate = new HisMedicineInfoTranslate();
                BeanUtils.copyProperties(medicineinfo, HisMedicineInfoTranslate);
                infoTranslates.add(HisMedicineInfoTranslate);
                // log.info("--------------------药品基本信息修改翻译发送结束---------------------------");
            }
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisMedicineInfoTranslates(infoTranslates);
            amqpTemplat.convertAndSend("com.ahsj.updateHisMedicineInfoList", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));


          /*  for (HisMedicineInfo medicineinfo : medicineinfoUpdateList) {
                log.info("--------------------药品基本信息修改翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisMedicineInfoTranslate HisMedicineInfoTranslate = new HisMedicineInfoTranslate();
                BeanUtils.copyProperties(medicineinfo, HisMedicineInfoTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisMedicineInfoTranslate(HisMedicineInfoTranslate);
                amqpTemplat.convertAndSend("com.ahsj.updateHisMedicineInfo", JsonUtils.serialize(translateModels));
                log.info(JsonUtils.serialize(translateModels));
                log.info("--------------------药品基本信息修改翻译发送结束---------------------------");
            }*/
        }
        return map;
    }

    /**
     *@Description 查询相关字典ID
     *@Params []
     *@return com.ahsj.hiscore.entity.HisMedicineInfo
     *@Author zhushixiang
     *@Date 2019-08-29
     *@Time 16:51
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicineInfo queryForCode()throws Exception{
        return CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.queryForCode());
    }

    /**
     *@Description
     *@Params [hisMedicineInfo]
     *@return com.ahsj.hiscore.entity.HisPharmacyDetail
     *@Author zhushixiang
     *@Date 2019-08-30
     *@Time 17:51
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicineInfo selectByDrugsNameAndSpec(HisMedicineInfo hisMedicineInfo) {
        return CodeHelper.getInstance().setCodeValue(hisMedicineInfoMapper.selectByDrugsNameAndSpec(hisMedicineInfo));
    }

    /**
     *@Description
     *@Params [drugsNumb]
     *@return com.ahsj.hiscore.entity.HisMedicineInfo
     *@Author zhushixiang
     *@Date 2019-08-30
     *@Time 18:02
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicineInfo selectByDrugsNumb(String drugsNumb) {
        return hisMedicineInfoMapper.selectByDrugsNumb(drugsNumb);
    }

    /**
     *@Description 根据导入时传来的条件搜索出对应的药品信息
     *@Params [hisMedicineInfo]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     *@Author zhushixiang
     *@Date 2019-09-02
     *@Time 14:59
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMedicineInfo> selectByExportExcel(HisMedicineInfo hisMedicineInfo) {
        return hisMedicineInfoMapper.selectByExportExcel(hisMedicineInfo);
    }
}
