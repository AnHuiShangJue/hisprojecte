package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.core.BoolMessage;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisDoctorInfoMapper;
import com.ahsj.hiscore.dao.HisUserDoctorMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.feign.IUserService;
import com.ahsj.hiscore.services.HisDoctorInfoService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
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
public class HisDoctorInfoServiceImpl implements HisDoctorInfoService {


    @Autowired
    HisDoctorInfoMapper hisDoctorInfoMapper;
    @Autowired

    HisUserDoctorMapper hisUserDoctorMapperr;

    @Autowired
    IUserService iUserService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisDoctorInfoService hisDoctorInfoService;


    /**
     * @return
     * @Description 新增或更新
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisDoctorInfo hisDoctorInfo) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisDoctorInfo.getId())) {
            //check out id 是否存在
            HisDoctorInfo checkIfExit = hisDoctorInfoMapper.selectByPrimaryKey(hisDoctorInfo.getId());
            //check out 身份证和工号是否唯一
            List<HisDoctorInfo> checkForUpdate = hisDoctorInfoMapper.selectForInsert(hisDoctorInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(checkIfExit)) {
                return MessageUtil.createMessage(false, "更新失败!该信息不存在!");
            }
            // 如果返回俩条数据则意味着工号和身份证已经存在
            if (checkForUpdate.size() > 1) {
                return MessageUtil.createMessage(false, "更新失败!所变更的身份证或工号已存在");
            } else if (checkForUpdate.size() == 1) {
                //如果id相同则意味着身份证和工号唯一
                if (hisDoctorInfo.getId().equals(checkForUpdate.get(0).getId())) {
                    hisDoctorInfoMapper.updateByPrimaryKey(hisDoctorInfo);
                    return MessageUtil.createMessage(true, "更新成功");
                } else {
                    return MessageUtil.createMessage(false, "更新失败!所变更的身份证或工号已存在");
                }
            } else {
                //如果唯一检查时空的则说明身份证和工号唯一
                hisDoctorInfoMapper.updateByPrimaryKey(hisDoctorInfo);
                return MessageUtil.createMessage(true, "更新成功");
            }
        } else {
            int doctorNum = hisDoctorInfoMapper.selectNumberCount() + 1;
            boolean flag = false;
            String doctorNumber = "";
            while (true) {
                doctorNumber = "D" + String.format("%08d", doctorNum);
                UserInfo checkNumber = iUserService.selectByName(doctorNumber);
                if (EmptyUtil.Companion.isNullOrEmpty(checkNumber.getUserId()))
                    break;
                else
                    doctorNum++;
            }
            hisDoctorInfo.setNumb(doctorNumber);
            List<HisDoctorInfo> check = hisDoctorInfoMapper.selectForInsert(hisDoctorInfo);
            if (check.size() > 0) {
                return MessageUtil.createMessage(false, "新增失败!身份证或工号已存在！");
            } else {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(hisDoctorInfo.getNumb());
                userInfo.setPassword(hisDoctorInfo.getIdcard().substring(hisDoctorInfo.getIdcard().length() - 6, hisDoctorInfo.getIdcard().length()));
                userInfo.setUserName(hisDoctorInfo.getName());
                userInfo.setSex(hisDoctorInfo.getSex().toString());
                userInfo.setDelFlag("1");
                userInfo.setIsInitData("1");
                userInfo.setTelPhone(hisDoctorInfo.getPhone());
                userInfo.setDeptId(String.valueOf(hisDoctorInfo.getDepartment_id()));
                UserInfo userCheck = iUserService.selectByName(userInfo.getUserId());
                if (!EmptyUtil.Companion.isNullOrEmpty(userCheck.getId())) {
                    //说明访问用户服务失败
                    if (userCheck.getId().equals(-1)) {
                        return MessageUtil.createMessage(false, "新增失败!用户模块服务访问失败!请联系管理员!");
                    }
                    return MessageUtil.createMessage(false, "新增失败!" + userInfo.getUserId() + "该工号对应的账号已经存在了!请修改工号!");
                }
                BoolMessage message = iUserService.saveOrUpdateUserInfo(userInfo);
                if (message.isSuccess()) {
                    hisDoctorInfoMapper.insert(hisDoctorInfo);
                    UserInfo successUserInfo = iUserService.selectByName(userInfo.getUserId());
                    HisUserDoctor hisUserDoctor = new HisUserDoctor();
                    hisUserDoctor.setUserId(successUserInfo.getId());
                    hisUserDoctor.setDoctorId(hisDoctorInfo.getId());
                    hisUserDoctorMapperr.insert(hisUserDoctor);
                    return MessageUtil.createMessage(true, "新增成功");
                } else {
                    return MessageUtil.createMessage(false, "新增失败!" + message.getMessage());
                }
            }
        }
    }


    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            HisUserDoctor hisUserDoctor = hisUserDoctorMapperr.selectByDoctorId(id);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisUserDoctor)) {
                hisUserDoctorMapperr.deleteByPrimaryKey(hisUserDoctor.getId());
                hisDoctorInfoMapper.deleteByPrimaryKey(id);
            } else {
                hisDoctorInfoMapper.deleteByPrimaryKey(id);
            }
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return
     * @Description list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisDoctorInfo> list(PageBean<HisDoctorInfo> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisDoctorInfoMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return
     * @Description 初始化
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    @Override
    @Transactional(readOnly = true)
    public HisDoctorInfo updateInit(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisDoctorInfoMapper.selectByPrimaryKey(id));
    }

    /**
     * @return com.ahsj.hiscore.entity.HisNurse
     * @Description select
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019/6/21
     * @Time 4:08 PM
     **/
    @Override
    public HisDoctorInfo selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisDoctorInfoMapper.selectByPrimaryKey(id));
    }

    /**
     * @Author dingli
     * @Date 2019/7/9
     * @Time 17:08
     * @Return List<HisDoctorInfo>
     * @Params departmentId
     **/


    @Override
    public List<HisDoctorInfo> getHisDoctorInfoBydepartmentId(Long departmentId) {
        return hisDoctorInfoMapper.getHisDoctorInfoBydepartmentId(departmentId);
    }

    /**
     * @Description excel 导入
     * @Author muxu
     * @Date 2019/8/27
     * @Time 10:07
     * @Return
     * @Params
     **/

    @Override
    @Transactional(readOnly = false)
    public Message saveHisDoctorInfo(List<HisDoctorInfo> hisDoctorInfoList) throws Exception {

        return null;
    }


    /**
     * @Description excel导出
     * @Author muxu
     * @Date 2019/8/28
     * @Time 15:12
     * @Return
     * @Params
     **/

    @Override
    @Transactional(readOnly = true)
    public void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, String param, HttpSession session, HisDoctorInfo hisd) throws Exception {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        psth = this.getClass().getClassLoader().getResource("templates/excel/export/hisdoctor.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_DOCTOR_CH_FILE_URL;






        String psthkm = this.getClass().getClassLoader().getResource("templates/excel/export/hisdoctor_KM.xlsx").getPath();
        psthkm =Constants.HIS_SYS_EXCEL_DOCTOR_KM_FILE_URL;
        // psth = request.getSession().getServletContext().getRealPath("/") + "templates\\excel\\export\\hisproject.xlsx";
        if (StringUtils.equals(param, Constants.HIS_CH)) {
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisDoctorInfo> hisDoctorInfoList = hisDoctorInfoMapper.queryListExportAll(hisd);
                beans.put("doctorInfoList", hisDoctorInfoList);
                JxlsUtil.export(request, response, psth, "医生信息记录", beans);
                return;
            }
            List<HisDoctorInfo> list = new ArrayList<>();
            for (Long id : ids) {
                HisDoctorInfo hisDoctorInfo = new HisDoctorInfo();
                hisDoctorInfo.setId(id);
                list.add(hisDoctorInfo);
            }
            List<HisDoctorInfo> hisDoctorInfoList = hisDoctorInfoMapper.queryListExportAndByIdsAll(list);
            if (EmptyUtil.Companion.isNullOrEmpty(hisDoctorInfoList)) {
                return;
            } else {
                beans.put("doctorInfoList", hisDoctorInfoList);
                JxlsUtil.export(request, response, psth, "医生信息记录", beans);
                return;
            }
        } else {
            if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
                List<HisDoctorInfo> doctorInfoList = hisDoctorInfoMapper.queryListExportAll(hisd);
                for (HisDoctorInfo doctorInfo : doctorInfoList) {
                    Translate translate = new Translate();
                    //字典
                    translate.setTranslateId(doctorInfo.getSysCodeId());
                    translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translates) {
                        if (StringUtils.equals(translate1.getTranslateChina(), doctorInfo.getSexName())) {
                            doctorInfo.setSexName(translate1.getTranslateKhmer());
                        }
                    }
                    //部门
                    translate.setTranslateId(doctorInfo.getDepartment_id());
                    translate.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
                    List<Translate> translateOrganization = iTranslateService.queryTranslate(translate);
                    for (Translate translate1 : translateOrganization) {
                        if (StringUtils.equals(translate1.getTranslateChina(), doctorInfo.getDeptName())) {
                            doctorInfo.setDeptName(translate1.getTranslateKhmer());
                        }
                    }
                }
                beans.put("doctorInfoList", doctorInfoList);
                JxlsUtil.export(request, response, psthkm, "Doctor information record", beans);
                return;
            }
            List<HisDoctorInfo> list = new ArrayList<>();
            for (Long id : ids) {
                HisDoctorInfo hisDoctorInfo = new HisDoctorInfo();
                hisDoctorInfo.setId(id);
                list.add(hisDoctorInfo);
            }
            List<HisDoctorInfo> doctorInfoList = hisDoctorInfoMapper.queryListExportAndByIdsAll(list);
            for (HisDoctorInfo doctorInfo : doctorInfoList) {
                Translate translate = new Translate();
                //字典
                translate.setTranslateId(doctorInfo.getSysCodeId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translates) {
                    if (StringUtils.equals(translate1.getTranslateChina(), doctorInfo.getSexName())) {
                        doctorInfo.setSexName(translate1.getTranslateKhmer());
                    }
                }
                //部门
                translate.setTranslateId(doctorInfo.getDepartment_id());
                translate.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
                List<Translate> translateOrganization = iTranslateService.queryTranslate(translate);
                for (Translate translate1 : translateOrganization) {
                    if (StringUtils.equals(translate1.getTranslateChina(), doctorInfo.getDeptName())) {
                        doctorInfo.setDeptName(translate1.getTranslateKhmer());
                    }

                }
            }
            if (EmptyUtil.Companion.isNullOrEmpty(doctorInfoList)) {
                return;
            } else {
                beans.put("doctorInfoList", doctorInfoList);
                JxlsUtil.export(request, response, psthkm, "Doctor information record", beans);
                return;
            }
        }
    }

    /**
     * @Description excel导入
     * @Author muxu
     * @Date 2019/9/2
     * @Time 18:05
     * @Return java.util.Map
     * @Params [excelData]
     **/
    @Override
    @Transactional(readOnly = false)
    public Map importExcel(List<HisDoctorInfo> excelData) throws Exception, NumberFormatException {
        List<String> errorData = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer failNum = 0;
        // 循环]
        //从库中将所有的HisProject信息全部取出来做数据对比处理
        List<HisDoctorInfo> tempList = hisDoctorInfoMapper.queryDoctorAll();
        //插入的list
        List<HisDoctorInfo> doctorInsertList = new ArrayList<>();
        //更新的list
        List<HisDoctorInfo> doctorUpdateList = new ArrayList<>();
        for (HisDoctorInfo hisDoctorInfo : excelData) {
            boolean updateflag = false;
            for (HisDoctorInfo doctorInfo : tempList) {
                if (doctorInfo.getName().equals(hisDoctorInfo.getName())) {
                    updateflag = true;
                    break;
                }
            }
            if (!updateflag) {
                doctorInsertList.add(hisDoctorInfo);
            } else {
                doctorUpdateList.add(hisDoctorInfo);
            }
        }
        if (doctorInsertList != null && doctorInsertList.size() > 0) {
            for (int i = 0; i < doctorInsertList.size(); i++) {
                String sexName = doctorInsertList.get(i).getSexName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(sexName);
                String code = iCodeService.getSysCodeName(detail).getCode();
                Integer type = Integer.parseInt(code);
                doctorInsertList.get(i).setSex(type);
                String deptName = doctorInsertList.get(i).getDeptName();
                Organization organization = new Organization();
                organization.setName(deptName);
                Long id = iOrganizationService.getOrganizationName(organization).getId();
                doctorInsertList.get(i).setDepartment_id(id);
                doctorInsertList.get(i).setId(null);
                //判断数据库是否为空
                List<HisDoctorInfo> doctorList = hisDoctorInfoMapper.queryDoctorDescIdAll();
                int doctorNum = hisDoctorInfoMapper.selectNumberCount() + 1;
                String doctorNumber = new String();
                while (true) {
                    doctorNumber = "D" + String.format("%08d", doctorNum);
                    HisDoctorInfo checkNumber = hisDoctorInfoMapper.selectByNumber(doctorNumber);
                    if (EmptyUtil.Companion.isNullOrEmpty(checkNumber))
                        break;
                    else
                        doctorNum++;
                }
                doctorInsertList.get(i).setNumb(doctorNumber);
            }
            hisDoctorInfoService.importDoctorInfo(doctorInsertList);
        }

        if (doctorUpdateList != null && doctorUpdateList.size() > 0) {
            for (HisDoctorInfo doctorInfo : doctorUpdateList) {
                Long hid = hisDoctorInfoMapper.queryHisDoctorByIdCard(doctorInfo.getIdcard()).getId();
                String sexName = doctorInfo.getSexName();
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(sexName);
                String code = iCodeService.getSysCodeName(detail).getCode();
                Integer type = Integer.valueOf(code);
                doctorInfo.setSex(type);
                String deptName = doctorInfo.getDeptName();
                Organization organization = new Organization();
                organization.setName(deptName);
                Long id = iOrganizationService.getOrganizationName(organization).getId();
                doctorInfo.setDepartment_id(id);
                doctorInfo.setId(hid);
            }
            hisDoctorInfoMapper.updateHisDoctor(doctorUpdateList);
        }
        return map;
    }

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/3
     * @Time 13:55
     * @Return int
     * @Params [doctorInfoInsertList]
     **/

    @Override
    public void importDoctorInfo(List<HisDoctorInfo> doctorInsertList) throws Exception {
        for (HisDoctorInfo hisDoctorInfo : doctorInsertList) {
            hisDoctorInfoService.saveOrUpdate(hisDoctorInfo);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisDoctorInfo> getAllDoctor() throws Exception {
        return hisDoctorInfoMapper.getAllDoctor();
    }
}
