/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisPprojectServiceImpl
 * Author:   anhuishangjue
 * Date:     2019/7/10 11:55
 * Description: HisPprojectServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.PinyinUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisProjectMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.TranslateModel.HisMedicineInfoTranslate;
import com.ahsj.hiscore.entity.TranslateModel.HisProjectTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateDelete;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.entity.dto.HisProjectRes;
import com.ahsj.hiscore.entity.model.TranslateModel;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisInspecProjectService;
import com.ahsj.hiscore.services.HisInspectionCombinationService;
import com.ahsj.hiscore.services.HisProjectService;
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
import org.springframework.util.CollectionUtils;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 * 〈HisPprojectServiceImpl〉
 *
 * @author XJP
 * @create 2019/7/10
 * @since 1.0.0
 */
@Service
public class HisProjectServiceImpl implements HisProjectService {


    private Logger log = LoggerFactory.getLogger(HisProjectServiceImpl.class.getSimpleName());


    private static final long NUMBER_NUM = 1L;
    private static final Integer TDISABLE_NUM_ONE = 1;
    private static final Integer TDISABLE_NUM_TWO = 2;

    @Autowired
    HisProjectMapper hisProjectMapper;
    @Autowired
    HisInspecProjectService hisInspecProjectService;

    @Autowired
    HisInspectionCombinationService hisInspectionCombinationService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    AmqpTemplate amqpTemplat;

    private static final String AHSJ_CODE = "123456";

    /**
     * @return core.message.Message
     * @功能说明 批量删除
     * @Params [ids]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:01
     **/
    @Override
    @Transactional(readOnly = false)
    public Message deleteByPrimaryKey(Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            return MessageUtil.createMessage(false, "删除失败。");
        } else {
            for (Long id : ids) {
                HisProject hisProject = hisProjectMapper.selectByPrimaryKey(id);
                if (EmptyUtil.Companion.isNullOrEmpty(hisProject)) {
                    //log.info("查询失败 ！！！  无对应数据");
                    return MessageUtil.createMessage(false, "删除失败。");
                }
                if (!EmptyUtil.Companion.isNullOrEmpty(hisProject.getNumber())) {
                    hisProjectMapper.deleteByPrimaryKey(id);
                    //  log.info("--------------------收费项目删除翻译开始---------------------------");
                    TranslateDelete translateDelete = new TranslateDelete();
                    translateDelete.setId(id);
                    translateDelete.setModel(Constants.TRANSLATE_HIS_PROJECT);
                    amqpTemplat.convertAndSend(Constants.TRANSLATE_HIS_DELETE, JsonUtils.serialize(translateDelete));
                    //   log.info(JsonUtils.serialize(translateDelete));
                    //    log.info("--------------------收费项目删除翻译结束---------------------------");
                    continue;
                } else {
                    return MessageUtil.createMessage(false, "删除失败。");
                }
            }
            return MessageUtil.createMessage(true, "删除成功。");
        }

    }


    /**
     * @return core.message.Message
     * @功能说明 新增
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:02
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insertSelective(HisProject hisProject) throws Exception {

        if (!EmptyUtil.Companion.isNullOrEmpty(hisProject.getId())) {
            return MessageUtil.createMessage(false, "新增失败 ！！！！！");
        } else if (EmptyUtil.Companion.isNullOrEmpty(hisProject.getPrice()) || EmptyUtil.Companion.isNullOrEmpty(hisProject.getName()) || EmptyUtil.Companion.isNullOrEmpty(hisProject.getUnit())
                || EmptyUtil.Companion.isNullOrEmpty(hisProject.getNumber())) {
            return MessageUtil.createMessage(false, "新增失败! 收费标准或收费单位或收费名称或拼音码或项目编码不能为空");
        } else {
       /*     HisProject project1 = hisProjectMapper.queryHisProjectByName(hisProject.getName());
            if (!EmptyUtil.Companion.isNullOrEmpty(project1)) {
                return MessageUtil.createMessage(false, "新增失败! 项目名称不能重复");
            } else {*/
            HisProject project1 = hisProjectMapper.queryHisProjectByNumber(hisProject.getNumber());
            if (!EmptyUtil.Companion.isNullOrEmpty(project1)) {
                return MessageUtil.createMessage(false, "新增失败! 项目编码不能重复");
            } else {

       /*             List<HisProject> projectList = hisProjectMapper.queryProjectAll();
                    if (projectList.size() == 0) {
                        hisProject.setNumber(1L);*/
                if(EmptyUtil.Companion.isNullOrEmpty(hisProject.getPinyinCode())){
                    /*String toFirstChar = PinyinUtils.ToFirstChar(hisProject.getName());
                    hisProject.setPinyinCode(toFirstChar);*/
                }
                hisProjectMapper.insert(hisProject);
                //   log.info("--------------------收费项目新增翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisProjectTranslate hisProjectTranslate = new HisProjectTranslate();
                BeanUtils.copyProperties(hisProject, hisProjectTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisProjectTranslate(hisProjectTranslate);
                amqpTemplat.convertAndSend("com.ahsj.addProject", JsonUtils.serialize(translateModels));
                //  log.info(JsonUtils.serialize(translateModels));
                //  log.info("--------------------收费项目新增翻译发送结束---------------------------");

                return MessageUtil.createMessage(true, "保存成功。");
                 /*   } else {
                        List<HisProject> projectDateList = hisProjectMapper.queryProjectDescIdAll();
                        HisProject project = projectDateList.get(0);
                        Long number = project.getNumber() + NUMBER_NUM;
                        hisProject.setNumber(number);
                        hisProjectMapper.insert(hisProject);
                        log.info("--------------------收费项目新增翻译发送开始---------------------------");
                        BaseLoginUser loginUser = new BaseLoginUser();
                        TranslateModels translateModels = new TranslateModels();
                        HisProjectTranslate hisProjectTranslate = new HisProjectTranslate();
                        BeanUtils.copyProperties(hisProject, hisProjectTranslate);
                        translateModels.setUserId(loginUser.getId());
                        translateModels.setHisProjectTranslate(hisProjectTranslate);
                        amqpTemplat.convertAndSend("com.ahsj.addProject", JsonUtils.serialize(translateModels));
                        log.info(JsonUtils.serialize(translateModels));
                        log.info("--------------------收费项目新增翻译发送结束---------------------------");
                        return MessageUtil.createMessage(true, "保存成功。");
                    }*/
            }


        }
    }


    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:05
     **/
    @Override
    @Transactional(readOnly = true)
    public HisProject selectByPrimaryKey(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            log.info("查询失败 ！！！  请求参数不能为空");
            return new HisProject();
        } else {
            HisProject hisProject = hisProjectMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(hisProject)) {
                //  log.info("查询失败 ！！！  无对应数据");
                return new HisProject();
            } else {
                return hisProject;
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 修改
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:05
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(HisProject hisProject) throws Exception {

        hisProject.setUpdateDate(new Date());
        if (!EmptyUtil.Companion.isNullOrEmpty(hisProject.getId())) {
            HisProject project = hisProjectMapper.selectByPrimaryKey(hisProject.getId());
            if (EmptyUtil.Companion.isNullOrEmpty(project)) {
                //  log.info("查询失败 ！！！  无对应数据");
                return MessageUtil.createMessage(false, "修改失败。");
            }
            if (!EmptyUtil.Companion.isNullOrEmpty(project.getNumber())) {
                /*String toFirstChar = PinyinUtils.ToFirstChar(hisProject.getName());
                hisProject.setPinyinCode(toFirstChar);*/
                hisProjectMapper.updateByPrimaryKeySelective(hisProject);
                // log.info("--------------------收费项目修改翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisProjectTranslate hisProjectTranslate = new HisProjectTranslate();
                BeanUtils.copyProperties(hisProject, hisProjectTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisProjectTranslate(hisProjectTranslate);
                amqpTemplat.convertAndSend("com.ahsj.updateProject", JsonUtils.serialize(translateModels));
                //  log.info(JsonUtils.serialize(translateModels));
                //   log.info("--------------------收费项目修改翻译发送结束---------------------------");
                return MessageUtil.createMessage(true, "修改成功。");
            } else {
                return MessageUtil.createMessage(false, "修改失败。");
            }
        } else {
            return MessageUtil.createMessage(false, "修改失败。");
        }
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:06
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisProject> queryList(PageBean<HisProject> pageBean) throws Exception {
        List<HisProject> hisProjectList = hisProjectMapper.queryList(pageBean);
        for (HisProject hisProject : hisProjectList) {
            hisProject.setName(hisProject.getName()+"("+hisProject.getTranslateKhmer()+")");
        }
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisProjectList));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明 批量修改状态
     * @Params [ids]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:06
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateSetDisable(Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            log.info("修改收费停用状态失败!!! 参数不能为空 ！！");
            return MessageUtil.createMessage(false, "修改收费停用状态失败。");
        } else {
            for (Long id : ids) {
                HisProject hisProject = hisProjectMapper.selectByPrimaryKey(id);
                if (EmptyUtil.Companion.isNullOrEmpty(hisProject)) {
                    log.info("查询失败 ！！！  无对应数据");
                    return MessageUtil.createMessage(false, "修改收费停用状态失败。");
                }
                if (hisProject.getIsEnable() == 1) {
                    hisProject.setIsEnable(TDISABLE_NUM_TWO);
                    hisProjectMapper.updateByPrimaryKeySelective(hisProject);
                } else {
                    hisProject.setIsEnable(TDISABLE_NUM_ONE);
                    hisProjectMapper.updateByPrimaryKeySelective(hisProject);
                }
            }
            for (Long id : ids) {
                List<HisInspecProject> hisInspecProjectList = hisInspecProjectService.queryByProjectId(id);

                if (EmptyUtil.Companion.isNullOrEmpty(hisInspecProjectList)) {
                    log.info("查询失败 ！！！  无对应数据");
                    break;
                } else {
                    List<Long> list = hisInspecProjectList.stream().map(HisInspecProject::getInspectionId).collect(Collectors.toList());
                    for (Long aLong : list) {
                        List<HisInspecProject> hisInspecProjectList1 = hisInspecProjectService.queryByInspectionId(aLong);
                        if (EmptyUtil.Companion.isNullOrEmpty(hisInspecProjectList1)) {
                            log.info("查询失败 ！！！  无对应数据");
                            return MessageUtil.createMessage(false, "修改收费停用状态失败");
                        }
                        boolean b = hisInspecProjectList1.stream().noneMatch((e) -> e.getIsEnable().equals(2));
                        if (b) {
                            HisInspectionCombination combination = hisInspectionCombinationService.selectByPrimaryKey(aLong);
                            if (EmptyUtil.Companion.isNullOrEmpty(combination)) {
                                log.info("查询失败 ！！！  无对应数据");
                                return MessageUtil.createMessage(false, "修改收费停用状态失败");
                            }
                            combination.setIsEnable(1);
                            hisInspectionCombinationService.updateByPrimaryKeySelective(combination);
                        } else {
                            HisInspectionCombination combination = hisInspectionCombinationService.selectByPrimaryKey(aLong);
                            if (EmptyUtil.Companion.isNullOrEmpty(combination)) {
                                log.info("查询失败 ！！！  无对应数据");
                                return MessageUtil.createMessage(false, "修改收费停用状态失败");
                            }
                            combination.setIsEnable(2);
                            hisInspectionCombinationService.updateByPrimaryKeySelective(combination);
                        }
                    }
                }
            }

            return MessageUtil.createMessage(true, "修改收费停用状态成功。");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:07
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisProject> queryCombinationId(Long id) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            List<HisProject> hisProjects = hisProjectMapper.queryCombinationId(id);
            if (!CollectionUtils.isEmpty(hisProjects)) {
                return hisProjects;
            } else {
                log.info("查询失败  ！！！ 无对应数据");
                return new ArrayList<>();
            }
        } else {
            log.info("查询失败  ！！！ 无对应数据");
            return new ArrayList<>();
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/7/26
     * @Time 20:06
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisProject> queryCombinationIds(PageBean<HisProject> pageBean) throws Exception {
        List<HisProject> hisProjectList = hisProjectMapper.queryCombinationIds(pageBean);
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisProjectMapper.queryCombinationIds(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisProject> queryTranslateInfoByDate(HisProject hisProject) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisProject)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<HisProject> projectList = hisProjectMapper.queryTranslateInfoByDate(hisProject);
            if (EmptyUtil.Companion.isNullOrEmpty(projectList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return projectList;
            }
        }
    }


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 16:57
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisProject> queryAll() throws Exception {
        List<HisProject> projectList = hisProjectMapper.queryAll();
        if (EmptyUtil.Companion.isNullOrEmpty(projectList)) {
            log.info("查询失败");
            return new ArrayList<>();
        } else {
            return projectList;
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:36
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisProject> queryLists(PageBean<HisProject> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisProjectMapper.queryLists(pageBean)));
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 17:28
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisProject> queryAddList(PageBean<HisProject> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisProjectMapper.queryAddList(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:12
     **/
    @Override
    @Transactional(readOnly = true)
    public HisProject getHisProject(HisProject hisProject) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisProject)) {
            return new HisProject();
        } else {
            hisProject = hisProjectMapper.getHisProject(hisProject);
            if (EmptyUtil.Companion.isNullOrEmpty(hisProject)) {
                return new HisProject();
            } else {
                return hisProject;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 10:19
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisProject> queryHisProjectByIds(Long[] ids) {

        List<HisProject> list = new ArrayList<>();
        for (Long id : ids) {
            HisProject hisProject = new HisProject();
            hisProject.setId(id);
            list.add(hisProject);
        }
        List<HisProject> hisProjectList = hisProjectMapper.queryListExportAndByIdsAll(list);
        if (EmptyUtil.Companion.isNullOrEmpty(hisProjectList)) {
            return new ArrayList<>();
        } else {
            return hisProjectList;
        }
    }

    /**
     * @return java.util.Map
     * @功能说明 项目导入 格式 excel表格
     * @Params [excelData]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:01
     **/
    @Override
    @Transactional(readOnly = false)
    public Map importExcel(List<HisProject> excelData) throws Exception, NumberFormatException {
        List<String> errorData = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer failNum = 0;
        // 循环]
        //从库中将所有的HisProject信息全部取出来做数据对比处理
        List<HisProject> tempList = hisProjectMapper.queryProjectAll();
        //插入的list
        List<HisProject> projectInsertList = new ArrayList<>();
        //更新的list
        List<HisProject> projectUpdateList = new ArrayList<>();
        for (HisProject hisProject : excelData) {
            boolean updateflag = false;
            for (HisProject project : tempList) {
                if (project.getNumber().equals(hisProject.getNumber())) {
                    updateflag = true;
                    break;
                }

            }
            if (!updateflag) {
                projectInsertList.add(hisProject);
            } else {
                projectUpdateList.add(hisProject);
            }
        }
        if (projectInsertList != null && projectInsertList.size() > 0) {
            for (int i = 0; i < projectInsertList.size(); i++) {
                String assitTypeName = projectInsertList.get(i).getAssitTypeName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(assitTypeName);
                detail.setSysCodeType(Constants.HIS_SYS_ASSIT_PROJECT_TYPE);
                String code = iCodeService.getSysCodeName(detail).getCode();
                int a = Integer.parseInt(code);
                short type = (short) a;
                projectInsertList.get(i).setType(type);
                String deptName = projectInsertList.get(i).getDeptName();
                Organization organization = new Organization();
                organization.setName(deptName);
                Long id = iOrganizationService.getOrganizationName(organization).getId();
                projectInsertList.get(i).setDepartmentId(id);
                projectInsertList.get(i).setId(null);
                projectInsertList.get(i).setIsEnable(1);
                //判断数据库是否为空


                //  List<HisProject> projectList = hisProjectMapper.queryProjectDescIdAll();
               /* if (EmptyUtil.Companion.isNullOrEmpty(projectList)) {
                    Integer number = i + 1;
                    projectInsertList.get(i).setNumber(number.longValue());
                } else {
                    List<HisProject> projectDateList = hisProjectMapper.queryProjectDescIdAll();
                    HisProject project = projectDateList.get(0);
                    Integer num = i + 1;
                    Long number = project.getNumber() + num.longValue();
                    projectInsertList.get(i).setNumber(number);
                }*/
            }

            hisProjectMapper.importHisProject(projectInsertList);


            TranslateModels translateModels = new TranslateModels();
            BaseLoginUser loginUser = new BaseLoginUser();
            List<HisProjectTranslate> infoTranslates = new ArrayList<>();
            for (HisProject hisProject : projectInsertList) {
                //  log.info("--------------------收费项目新增翻译发送开始---------------------------");
                HisProjectTranslate translate = new HisProjectTranslate();
                BeanUtils.copyProperties(hisProject, translate);
                infoTranslates.add(translate);
                //  log.info("--------------------收费项目新增翻译发送结束---------------------------");
            }
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisProjectTranslates(infoTranslates);
            amqpTemplat.convertAndSend("com.ahsj.addProjectList", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));




          /*  for (HisProject project : projectInsertList) {
                log.info("--------------------收费项目新增翻译发送开始---------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisProjectTranslate hisProjectTranslate = new HisProjectTranslate();
                BeanUtils.copyProperties(project, hisProjectTranslate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisProjectTranslate(hisProjectTranslate);
                amqpTemplat.convertAndSend("com.ahsj.addProject", JsonUtils.serialize(translateModels));
                log.info(JsonUtils.serialize(translateModels));
                log.info("--------------------收费项目新增翻译发送结束---------------------------");
            }*/
            // }
        }

        if (projectUpdateList != null && projectUpdateList.size() > 0) {
            for (HisProject project : projectUpdateList) {
                Long hid = hisProjectMapper.queryHisProjectByNumber(project.getNumber()).getId();
                String assitTypeName = project.getAssitTypeName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(assitTypeName);
                detail.setSysCodeType(Constants.HIS_SYS_ASSIT_PROJECT_TYPE);
                String code = iCodeService.getSysCodeName(detail).getCode();
                int a = Integer.parseInt(code);
                short type = (short) a;
                project.setType(type);
                String deptName = project.getDeptName();
                Organization organization = new Organization();
                organization.setName(deptName);
                Long id = iOrganizationService.getOrganizationName(organization).getId();
                project.setDepartmentId(id);
                project.setId(hid);
            }


            hisProjectMapper.updateHisProject(projectUpdateList);

            TranslateModels translateModels = new TranslateModels();
            BaseLoginUser loginUser = new BaseLoginUser();
            List<HisProjectTranslate> infoTranslates = new ArrayList<>();
            for (HisProject hisProject : projectUpdateList) {
                //  log.info("--------------------收费项目修改翻译发送开始---------------------------");
                HisProjectTranslate translate = new HisProjectTranslate();
                BeanUtils.copyProperties(hisProject, translate);
                infoTranslates.add(translate);
                //  log.info("--------------------收费项目修改翻译发送结束---------------------------");
            }
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisProjectTranslates(infoTranslates);
            amqpTemplat.convertAndSend("com.ahsj.updateProjectList", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));


        }

        return map;
    }

    /**
     * @return void
     * @功能说明 项目的导出 格式 excel表格
     * @Params [ids, request, response, session]
     * @Author XJP
     * @Date 2019/8/28
     * @Time 9:46
     **/
    @Override
    @Transactional(readOnly = true)
    public void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session, String param, HisProject hisp) throws Exception {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        psth = Constants.HIS_SYS_EXCEL_PROJECT_CH_FILE_URL;
        String psthkm = Constants.HIS_SYS_EXCEL_PROJECT_KM_FILE_URL;
        if (StringUtils.equals(param, Constants.HIS_CH)) {
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisProject> projectList = hisProjectMapper.queryListExportAll(hisp);
                beans.put("projectList", projectList);
                JxlsUtil.export(request, response, psth, "项目信息记录", beans);
                return;
            }
            List<HisProject> list = new ArrayList<>();
            for (Long id : ids) {
                HisProject hisProject = new HisProject();
                hisProject.setId(id);
                list.add(hisProject);
            }
            List<HisProject> projectList = hisProjectMapper.queryListExportAndByIdsAll(list);
            if (EmptyUtil.Companion.isNullOrEmpty(projectList)) {
                return;
            } else {
                beans.put("projectList", projectList);
                JxlsUtil.export(request, response, psth, "项目信息记录", beans);
                return;
            }
        } else if (StringUtils.equals(param, Constants.HIS_EN)) {
            log.info("这里是预留接口，进行翻译英文");
            return;
        } else {
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisProject> projectList = hisProjectMapper.queryListExportAll(hisp);
                for (HisProject project : projectList) {
                    Translate translate = new Translate();
                    //字典
                    translate.setTranslateId(project.getSysCodeId());
                    translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translates) {
                        if (StringUtils.equals(translate1.getTranslateChina(), project.getAssitTypeName())) {
                            project.setAssitTypeName(translate1.getTranslateKhmer());
                        }
                    }
                    //部门
                    translate.setTranslateId(project.getDepartmentId());
                    translate.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
                    List<Translate> translateOrganization = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translateOrganization) {
                        if (StringUtils.equals(translate1.getTranslateChina(), project.getDeptName())) {
                            project.setDeptName(translate1.getTranslateKhmer());
                        }
                    }
                    //项目
                    translate.setTranslateId(project.getId());
                    translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                    List<Translate> translateHisProject = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translateHisProject) {
                        if (StringUtils.equals(translate1.getTranslateChina(), project.getName())) {
                            project.setName(translate1.getTranslateKhmer());
                        }
                        if (StringUtils.equals(translate1.getTranslateChina(), project.getUnit())) {
                            project.setUnit(translate1.getTranslateKhmer());
                        }
                        if (StringUtils.equals(translate1.getTranslateChina(), project.getDescription())) {
                            project.setDescription(translate1.getTranslateKhmer());
                        }
                    }

                }
                beans.put("projectList", projectList);
                JxlsUtil.export(request, response, psthkm, "Project information record", beans);
                return;
            }
            List<HisProject> list = new ArrayList<>();
            for (Long id : ids) {
                HisProject hisProject = new HisProject();
                hisProject.setId(id);
                list.add(hisProject);
            }
            List<HisProject> projectList = hisProjectMapper.queryListExportAndByIdsAll(list);
            for (HisProject project : projectList) {
                Translate translate = new Translate();
                //字典
                translate.setTranslateId(project.getSysCodeId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translates) {
                    if (StringUtils.equals(translate1.getTranslateChina(), project.getAssitTypeName())) {
                        project.setAssitTypeName(translate1.getTranslateKhmer());
                    }
                }
                //部门
                translate.setTranslateId(project.getDepartmentId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
                List<Translate> translateOrganization = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translateOrganization) {
                    if (StringUtils.equals(translate1.getTranslateChina(), project.getDeptName())) {
                        project.setDeptName(translate1.getTranslateKhmer());
                    }

                }
                //项目
                translate.setTranslateId(project.getId());
                translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                List<Translate> translateHisProject = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translateHisProject) {
                    if (StringUtils.equals(translate1.getTranslateChina(), project.getName())) {
                        project.setName(translate1.getTranslateKhmer());
                    }
                    if (StringUtils.equals(translate1.getTranslateChina(), project.getUnit())) {
                        project.setUnit(translate1.getTranslateKhmer());
                    }
                    if (StringUtils.equals(translate1.getTranslateChina(), project.getDescription())) {
                        project.setDescription(translate1.getTranslateKhmer());
                    }
                }

            }
            if (EmptyUtil.Companion.isNullOrEmpty(projectList)) {
                return;
            } else {
                beans.put("projectList", projectList);
                JxlsUtil.export(request, response, psthkm, "Project information record", beans);
                return;
            }
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> search(HisProjectRes hisProjectRes) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisProjectRes)) {
            return new ArrayList<>();
        } else {
            HisProject project = new HisProject();
            project.setPinyinCode(hisProjectRes.getPinyinCode());
            project.setIsEnable(hisProjectRes.getIsEnable());
            List<HisProject> projectList = hisProjectMapper.search(project);
            List<Long> collect = projectList.stream().map(HisProject::getId).collect(Collectors.toList());
            if (EmptyUtil.Companion.isNullOrEmpty(collect)) {
                return new ArrayList<>();
            } else {
                return collect;
            }
        }
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
     *@Description 根据IDs查询项目信息
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisProject>
     *@Author zhushixiang
     *@Date 2019-10-08
     *@Time 13:46
    **/
    @Override
    @Transactional(readOnly = false)
    public List<HisProject> selectForListForProjectByIds(Long[] ids) throws Exception {
        return hisProjectMapper.selectForListForProjectByIds(ids);
    }
}
