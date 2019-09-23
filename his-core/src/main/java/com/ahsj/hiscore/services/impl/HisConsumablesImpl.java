package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisConsumablesDetailsMapper;
import com.ahsj.hiscore.dao.HisConsumablesMapper;
import com.ahsj.hiscore.entity.HisConsumables;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.TranslateModel.HisConsumablesTranslate;
import com.ahsj.hiscore.entity.TranslateModel.HisProjectTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateDelete;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisConsumablesService;
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
public class HisConsumablesImpl implements HisConsumablesService {

    private Logger log = LoggerFactory.getLogger(HisConsumablesImpl.class.getSimpleName());


    /**
     * @Description CRUD
     * @Params
     * @return
     * @Author jin
     * @Date 2019/6/19
     * @Time 15:18
     */
    @Autowired
    HisConsumablesMapper hisConsumablesMapper;

    @Autowired
    HisConsumablesDetailsMapper hisConsumablesDetailsMapper;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    ITranslateService iTranslateService;

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisConsumables hisConsumables) throws Exception {
        //id是null就是新增，否则是更新
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getId())) {
            HisConsumables check = hisConsumablesMapper.selectByPrimaryKey(hisConsumables.getId());
            //检查名称规格是否相同
            if (EmptyUtil.Companion.isNullOrEmpty(check)) {
                //是null就插入
                hisConsumablesMapper.insert(hisConsumables);
                log.info("--------------------耗材新增翻译发送开始--------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisConsumablesTranslate translate = new HisConsumablesTranslate();
                BeanUtils.copyProperties(hisConsumables, translate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisConsumablesTranslate(translate);
                amqpTemplat.convertAndSend("com.ahsj.addHisConsumables", JsonUtils.serialize(translateModels));
                log.info(JsonUtils.serialize(translateModels));
                log.info("--------------------耗材新增翻译发送结束--------------------------");

                return MessageUtil.createMessage(true, "插入成功!");
            } else {
                return MessageUtil.createMessage(false, "插入失败!");
            }
        } else {
            HisConsumables check = hisConsumablesMapper.selectByPrimaryKey(hisConsumables.getId());
            //先检测id是否存在
            if (!EmptyUtil.Companion.isNullOrEmpty(check)) {
                //在检测被更新的耗材名称和规格是否相同
                HisConsumables checkForOne = hisConsumablesMapper.selectNameAndSpecById(hisConsumables);
                if (checkForOne.getStock() != hisConsumables.getStock()) {
                    //不相同则表示修改了该项值---同时去detail表里修改stock_warn
                    hisConsumablesDetailsMapper.stockwarnupdate(hisConsumables.getStock(),hisConsumables.getId());
                }
                if (EmptyUtil.Companion.isNullOrEmpty(checkForOne)) {




                    hisConsumablesMapper.updateByPrimaryKeySelective(hisConsumables);
                    log.info("--------------------耗材修改翻译发送开始--------------------------");
                    BaseLoginUser loginUser = new BaseLoginUser();
                    TranslateModels translateModels = new TranslateModels();
                    HisConsumablesTranslate translate = new HisConsumablesTranslate();
                    BeanUtils.copyProperties(hisConsumables, translate);
                    translateModels.setUserId(loginUser.getId());
                    translateModels.setHisConsumablesTranslate(translate);
                    amqpTemplat.convertAndSend("com.ahsj.updateHisConsumables", JsonUtils.serialize(translateModels));
                    log.info(JsonUtils.serialize(translateModels));
                    log.info("--------------------耗材修改翻译发送结束--------------------------");
                    return MessageUtil.createMessage(true, "更新成功!");
                }
                if (EmptyUtil.Companion.isNullOrEmpty(checkForOne)) {
                    hisConsumablesMapper.updateByPrimaryKeySelective(hisConsumables);
                    log.info("--------------------耗材修改翻译发送开始--------------------------");
                    BaseLoginUser loginUser = new BaseLoginUser();
                    TranslateModels translateModels = new TranslateModels();
                    HisConsumablesTranslate translate = new HisConsumablesTranslate();
                    BeanUtils.copyProperties(hisConsumables, translate);
                    translateModels.setUserId(loginUser.getId());
                    translateModels.setHisConsumablesTranslate(translate);
                    amqpTemplat.convertAndSend("com.ahsj.updateHisConsumables", JsonUtils.serialize(translateModels));
                    log.info(JsonUtils.serialize(translateModels));
                    log.info("--------------------耗材修改翻译发送结束--------------------------");
                    return MessageUtil.createMessage(true, "更新成功!");
                }
                if (checkForOne.getId() == hisConsumables.getId()) {
                    hisConsumablesMapper.updateByPrimaryKey(hisConsumables);
                    log.info("--------------------耗材修改翻译发送开始--------------------------");
                    BaseLoginUser loginUser = new BaseLoginUser();
                    TranslateModels translateModels = new TranslateModels();
                    HisConsumablesTranslate translate = new HisConsumablesTranslate();
                    BeanUtils.copyProperties(hisConsumables, translate);
                    translateModels.setUserId(loginUser.getId());
                    translateModels.setHisConsumablesTranslate(translate);
                    amqpTemplat.convertAndSend("com.ahsj.updateHisConsumables", JsonUtils.serialize(translateModels));
                    log.info(JsonUtils.serialize(translateModels));
                    log.info("--------------------耗材修改翻译发送结束--------------------------");
                    return MessageUtil.createMessage(true, "更新成功!");
                } else if (noSameNameAndSepc(checkForOne, hisConsumables)) {
                    return MessageUtil.createMessage(true, "更新成功!");
                } else {
                    return MessageUtil.createMessage(false, "更新失败!");
                }
            } else {
                return MessageUtil.createMessage(false, "更新失败!");
            }
        }
    }

    /**
     * @return
     * @Description 删除
     * @Params
     * @Author jin
     * @Date 2019/6/19
     * @Time 16:10
     */

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisConsumablesMapper.deleteByPrimaryKey(id);
            log.info("--------------------耗材删除翻译发送开始--------------------------");
            TranslateDelete translateDelete = new TranslateDelete();
            translateDelete.setId(id);
            translateDelete.setModel(Constants.TRANSLATE_HIS_HISCONSUMABLES);
            amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
            log.info(JsonUtils.serialize(translateDelete));
            log.info("--------------------耗材删除翻译发送结束--------------------------");
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return
     * @Description 设置不可用
     * @Params
     * @Author jin
     * @Date 2019/6/19
     * @Time 16:11
     */
    @Override
    @Transactional(readOnly = false)
    public Message setDisable(Long[] ids) throws Exception {
        List<HisConsumables> hisConsumablesList = hisConsumablesMapper.selectByIds(ids);
        List<HisConsumables> enableList = new ArrayList<HisConsumables>();//可用
        List<HisConsumables> unEnableList = new ArrayList<HisConsumables>();//不可用
        for (HisConsumables hisConsumables : hisConsumablesList) {
            //1可用，2不可用
            if (hisConsumables.getIsEnable().equals(2)) {
                hisConsumables.setIsEnable(1);//放入可用list
                enableList.add(hisConsumables);
            } else if (hisConsumables.getIsEnable().equals(1)) {
                hisConsumables.setIsEnable(2);//放入不可用list
                unEnableList.add(hisConsumables);
            }
        }
        if (enableList.size() != 0) {
            hisConsumablesMapper.setDisable(enableList, 1);
        }
        if (unEnableList.size() != 0) {
            hisConsumablesMapper.setDisable(unEnableList, 2);
        }
        return MessageUtil.createMessage(true, "设置成功!");
    }


    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumables> list(PageBean<HisConsumables> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesMapper.list(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisConsumables> listEnable(PageBean<HisConsumables> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesMapper.listEnable(pageBean)));
        return pageBean;
    }



    @Override
    @Transactional(readOnly = true)
    public List<HisConsumables> queryAll() throws Exception {
        List<HisConsumables>  hisConsumables =  hisConsumablesMapper.queryAll();
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumables)){
            log.info("查询失败");
            return new ArrayList<>();
        }else {
            return hisConsumables;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public List<HisConsumables> selectByExportExcel(HisConsumables hisConsumables) throws Exception {
        return hisConsumablesMapper.selectByExportExcel(hisConsumables);
    }

    @Override
    @Transactional(readOnly = true)
    public void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session, String param) throws Exception {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        if (StringUtils.equals(param,Constants.HIS_CH)) {
           // psth = this.getClass().getClassLoader().getResource("templates/excel/export/hisconsumables_CH.xlsx").getPath();//导出表格
            psth =Constants.HIS_SYS_EXCEL_CONSUMABLES_CH_FILE_URL;
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisConsumables> hisConsumablesList = CodeHelper.getInstance().setCodeValue(hisConsumablesMapper.queryListExportAll());
                beans.put("hisConsumablesList", hisConsumablesList);
                JxlsUtil.export(request, response, psth, "耗材基本信息记录", beans);
                return;
            }
            List<HisConsumables> list = new ArrayList<>();
            for (Long id : ids) {
                HisConsumables hisConsumables = new HisConsumables();
                hisConsumables.setId(id);
                list.add(hisConsumables);
            }
            List<HisConsumables> hisConsumablesList = CodeHelper.getInstance().setCodeValue(hisConsumablesMapper.queryListExportAndByIdsAll(list));
            if (EmptyUtil.Companion.isNullOrEmpty(hisConsumablesList)) {
                return;
            } else {
                beans.put("hisConsumablesList", hisConsumablesList);
                JxlsUtil.export(request, response, psth, "耗材基本信息记录", beans);
                return;
            }
        }else if(StringUtils.equals(param,Constants.HIS_KM)){
           // psth = this.getClass().getClassLoader().getResource("templates/excel/export/hisconsumables_KM.xlsx").getPath();//导出表格
            psth = Constants.HIS_SYS_EXCEL_CONSUMABLES_KM_FILE_URL;
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisConsumables> hisConsumablesList = CodeHelper.getInstance().setCodeValue(hisConsumablesMapper.queryListExportAll());
                for (HisConsumables hisConsumables : hisConsumablesList) {
                    Translate translate = new Translate();
                    //字典
                    translate.setTranslateId(hisConsumables.getTypeId());
                    translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translates) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getTypeName())){
                            hisConsumables.setTypeName(translate1.getTranslateKhmer());
                        }
                    }
                    Translate translate2 = new Translate();
                    translate2.setTranslateId(hisConsumables.getEnableId());
                    translate2.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates1 = iTranslateService.queryTranslate(translate2);
                    for (Translate translate1 : translates1) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getEnable())){
                            hisConsumables.setEnable(translate1.getTranslateKhmer());
                        }
                    }


                    //耗材
                    translate.setTranslateId(hisConsumables.getId());
                    translate.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES);
                    List<Translate> translateHisMedicineInfo = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translateHisMedicineInfo) {
                        if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getName())){
                            hisConsumables.setName(translate1.getTranslateKhmer());
                        }
                        if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getSpec())){
                            hisConsumables.setSpec(translate1.getTranslateKhmer());
                        }

                    }


                }
                beans.put("hisConsumablesList", hisConsumablesList);
                JxlsUtil.export(request, response, psth, "Basic information table of consumables", beans);
                return;
            }
            List<HisConsumables> list = new ArrayList<>();
            for (Long id : ids) {
                HisConsumables hisConsumables = new HisConsumables();
                hisConsumables.setId(id);
                list.add(hisConsumables);
            }
            List<HisConsumables> hisConsumablesList = CodeHelper.getInstance().setCodeValue(hisConsumablesMapper.queryListExportAndByIdsAll(list));
            for (HisConsumables hisConsumables : hisConsumablesList) {
                Translate translate = new Translate();
                //字典
                translate.setTranslateId(hisConsumables.getTypeId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translates) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getTypeName())){
                        hisConsumables.setTypeName(translate1.getTranslateKhmer());
                    }
                }
                Translate translate2 = new Translate();
                translate2.setTranslateId(hisConsumables.getEnableId());
                translate2.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates1 = iTranslateService.queryTranslate(translate2);
                for (Translate translate1 : translates1) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getEnable())){
                        hisConsumables.setEnable(translate1.getTranslateKhmer());
                    }
                }

                //药品
                translate.setTranslateId(hisConsumables.getId());
                translate.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES);
                List<Translate> translateHisMedicineInfo = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translateHisMedicineInfo) {
                    if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getName())){
                        hisConsumables.setName(translate1.getTranslateKhmer());
                    }
                    if (StringUtils.equals(translate1.getTranslateChina(),hisConsumables.getSpec())){
                        hisConsumables.setSpec(translate1.getTranslateKhmer());
                    }

                }
            }
            if (EmptyUtil.Companion.isNullOrEmpty(hisConsumablesList)) {
                return;
            } else {
                beans.put("hisConsumablesList", hisConsumablesList);
                JxlsUtil.export(request, response, psth, "Basic information table of consumables", beans);
                return;
            }
        }else if(StringUtils.equals(param,Constants.HIS_EN)){
            log.info("这里是预留接口，进行翻译英文");
        }
    }


    /**
     * @return
     * @Description 检测耗材名称和规格是否相同
     * @Params
     * @Author jin
     * @Date 2019/6/19
     * @Time 16:09
     */
    private boolean noSameNameAndSepc(HisConsumables one, HisConsumables two) {
        if (!one.getName().equals(two.getName()) && !one.getSpec().equals(two.getSpec())) {
            return true;
        } else return false;
    }

    @Override
    public HisConsumables updateInit(Long id) {
        return hisConsumablesMapper.selectByPrimaryKey(id);
    }

    @Override
    public HisConsumables selectByNameandSpec(HisConsumables hisConsumables) throws Exception {
        return hisConsumablesMapper.selectByNameandSpec(hisConsumables);
    }

    @Override
    @Transactional(readOnly = false)
    public Map importExcel(List<HisConsumables> excelData) throws Exception {
        List<String> errorData = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer failNum = 0;
        // 循环]
        //从库中将所有的HisMedicine信息全部取出来做数据对比处理
        List<HisConsumables> tempList = hisConsumablesMapper.listAll();
        //插入的list
        List<HisConsumables> hisConsumablesArrayListInsert = new ArrayList<>();
        //更新的list
        List<HisConsumables> hisConsumablesArrayListUpdate = new ArrayList<>();
        for (HisConsumables hisConsumables : excelData) {
            boolean updateflag = false;
            for (HisConsumables consumables : tempList) {
                if (consumables.getName().equals(hisConsumables.getName())) {
                    updateflag = true;
                    break;
                }

            }
            if (!updateflag) {
                hisConsumablesArrayListInsert.add(hisConsumables);
            } else {
                hisConsumablesArrayListUpdate.add(hisConsumables);
            }
        }
        if (hisConsumablesArrayListInsert != null && hisConsumablesArrayListInsert.size() > 0) {
            for (int i = 0; i < hisConsumablesArrayListInsert.size(); i++) {
                //设置字典的值
                String precriptionName = hisConsumablesArrayListInsert.get(i).getTypeName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(precriptionName);
                detail.setSysCodeType("consumable_type");
                String code = iCodeService.getSysCodeName(detail).getCode();
                Integer type = Integer.parseInt(code);
                hisConsumablesArrayListInsert.get(i).setType(type);

                String mentalmedicine = hisConsumablesArrayListInsert.get(i).getEnable();
                SysCodeDetail detail2 = new SysCodeDetail();
                detail2.setValue(mentalmedicine);
                detail2.setSysCodeType("is_enable");
                String code2 = iCodeService.getSysCodeName(detail2).getCode();
                Integer enable = Integer.parseInt(code2);
                hisConsumablesArrayListInsert.get(i).setIsEnable(enable);

            }
            hisConsumablesMapper.importConsumables(hisConsumablesArrayListInsert);



            TranslateModels translateModels = new TranslateModels();
            BaseLoginUser loginUser = new BaseLoginUser();
            List<HisConsumablesTranslate> infoTranslates = new ArrayList<>();
            for (HisConsumables hisConsumables : hisConsumablesArrayListInsert) {
                log.info("--------------------耗材基本信息新增翻译发送开始---------------------------");
                HisConsumablesTranslate translate = new HisConsumablesTranslate();
                BeanUtils.copyProperties(hisConsumables, translate);
                infoTranslates.add(translate);
                log.info("--------------------耗材基本信息新增翻译发送结束---------------------------");
            }
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisConsumablesTranslates(infoTranslates);
            amqpTemplat.convertAndSend("com.ahsj.addHisConsumablesList", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));





          /*  for (HisConsumables hisConsumables : hisConsumablesArrayListInsert) {
                log.info("--------------------耗材基本信息新增翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisConsumablesTranslate hisConsumablesTranslate = new HisConsumablesTranslate();
                BeanUtils.copyProperties(hisConsumables, hisConsumablesTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisConsumablesTranslate(hisConsumablesTranslate);
                amqpTemplat.convertAndSend("com.ahsj.addHisMedicineInfo", JsonUtils.serialize(translateModels));
                log.info(JsonUtils.serialize(translateModels));
                log.info("--------------------耗材基本信息新增翻译发送结束---------------------------");
            }*/
        }

        if (hisConsumablesArrayListUpdate != null && hisConsumablesArrayListUpdate.size() > 0) {
            for (int i = 0; i <hisConsumablesArrayListUpdate.size() ; i++) {
                Long hid = hisConsumablesMapper.selectNameAndSpecById(hisConsumablesArrayListUpdate.get(i)).getId();
                //设置字典的值
                String precriptionName = hisConsumablesArrayListUpdate.get(i).getTypeName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(precriptionName);
                detail.setSysCodeType("consumable_type");
                String code = iCodeService.getSysCodeName(detail).getCode();
                Integer type = Integer.parseInt(code);
                hisConsumablesArrayListUpdate.get(i).setType(type);

                String mentalmedicine = hisConsumablesArrayListUpdate.get(i).getEnable();
                SysCodeDetail detail2 = new SysCodeDetail();
                detail2.setValue(mentalmedicine);
                detail2.setSysCodeType(Constants.HIS_SYS_IS_ENABLE);
                String code2 = iCodeService.getSysCodeName(detail2).getCode();
                Integer enable = Integer.parseInt(code2);
                hisConsumablesArrayListUpdate.get(i).setIsEnable(enable);

                hisConsumablesArrayListUpdate.get(i).setId(hid);

            }
            hisConsumablesMapper.updateConsumables(hisConsumablesArrayListUpdate);

            TranslateModels translateModels = new TranslateModels();
            BaseLoginUser loginUser = new BaseLoginUser();
            List<HisConsumablesTranslate> infoTranslates = new ArrayList<>();
            for (HisConsumables hisConsumables : hisConsumablesArrayListUpdate) {
                log.info("--------------------耗材基本信息修改翻译发送开始---------------------------");
                HisConsumablesTranslate translate = new HisConsumablesTranslate();
                BeanUtils.copyProperties(hisConsumables, translate);
                infoTranslates.add(translate);
                log.info("--------------------耗材基本信息修改翻译发送结束---------------------------");
            }
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisConsumablesTranslates(infoTranslates);
            amqpTemplat.convertAndSend("com.ahsj.updateHisConsumablesListList", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));


         /*   for (HisConsumables hisConsumables : hisConsumablesArrayListUpdate) {
                log.info("--------------------耗材基本信息修改翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisConsumablesTranslate hisConsumablesTranslate = new HisConsumablesTranslate();
                BeanUtils.copyProperties(hisConsumables, hisConsumablesTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisConsumablesTranslate(hisConsumablesTranslate);
                amqpTemplat.convertAndSend("com.ahsj.updateHisMedicineInfo", JsonUtils.serialize(translateModels));
                log.info(JsonUtils.serialize(translateModels));
                log.info("--------------------耗材基本信息修改翻译发送结束---------------------------");
            }*/
        }
        return map;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisConsumables
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 16:00
     **/
    @Override
    @Transactional(readOnly = true)
    public HisConsumables selectByPrimaryKey(Long id) {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            log.info("查询参数id 不能为空");
            return null;
        } else {
            HisConsumables hisConsumables = hisConsumablesMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(hisConsumables)) {
                log.info("无此数据");
                return null;
            }
            return hisConsumables;
        }

    }
/**
 *@功能说明
 *@Params [hisConsumables]
 *@return java.util.List<com.ahsj.hiscore.entity.HisConsumables>
 *@Author XJP
 *@Date 2019/8/11
 *@Time 11:09
**/
    @Override
    @Transactional(readOnly = true)
    public List<HisConsumables> queryTranslateInfoByDate(HisConsumables hisConsumables) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsumables)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<HisConsumables> translateList = hisConsumablesMapper.queryTranslateInfoByDate(hisConsumables);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }
}
