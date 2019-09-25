package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.excel.JxlsUtil;
import com.ahsj.hiscore.core.BoolMessage;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisPatientInfoMapper;
import com.ahsj.hiscore.dao.HisUserPaitentMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.feign.IUserService;
import com.ahsj.hiscore.services.HisImportInfoService;
import com.ahsj.hiscore.services.HisPatientService;
import core.entity.PageBean;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PatientServiceImpl implements HisPatientService {

    @Autowired
    HisPatientInfoMapper hisPatientInfoMapper;

    @Autowired
    HisUserPaitentMapper hisUserPaitentMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    private Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class.getSimpleName());


    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisPatientInfo]
     * @Author zhushixiang
     * @Date 2019-06-19
     * @Time 12:01
     **/

    // 数据库事务
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisPatientInfo hisPatientInfo) throws Exception {
        // 如果主健为空 则为新增
        if (EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getId())) {
            HisPatientInfo check = hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard());
            if (!EmptyUtil.Companion.isNullOrEmpty(check)) {
                return MessageUtil.createMessage(false, "新增失败,身份证号已存在！");
            } else {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(hisPatientInfo.getIdcard());
                userInfo.setPassword(hisPatientInfo.getIdcard().substring(hisPatientInfo.getIdcard().length() - 6, hisPatientInfo.getIdcard().length()));
                userInfo.setUserName(hisPatientInfo.getName());
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getSex())) {
                    userInfo.setSex(hisPatientInfo.getSex().toString());
                }
                userInfo.setDeptId(hisPatientInfo.getCompanyId());
                userInfo.setDelFlag("1");
                userInfo.setIsInitData("1");
                if (!EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getPhonenumber())) {
                    userInfo.setTelPhone(hisPatientInfo.getPhonenumber().toString());
                }
                UserInfo userCheck = iUserService.selectByName(userInfo.getUserId());
                if (!EmptyUtil.Companion.isNullOrEmpty(userCheck.getId())) {
                    //说明访问用户服务失败
                    if (userCheck.getId().equals(-1)) {
                        return MessageUtil.createMessage(false, "新增失败!用户模块服务访问失败!请联系管理员!");
                    }
                    return MessageUtil.createMessage(false, "新增失败!" + userCheck.getUserId() + "该用户名对应的账号已经存在了!请修改用户名!");
                }
                BoolMessage message = iUserService.saveOrUpdateUserInfo(userInfo);
                if (message.isSuccess()) {
                    hisPatientInfoMapper.insert(hisPatientInfo);
                    UserInfo successUserInfo = iUserService.selectByName(userInfo.getUserId());
                    HisUserPaitent hisUserPaitent = new HisUserPaitent();
                    hisUserPaitent.setUserId(successUserInfo.getId());
                    hisUserPaitent.setPatientId(hisPatientInfo.getId());
                    hisUserPaitentMapper.insert(hisUserPaitent);
                    return MessageUtil.createMessage(true, "新增成功");
                } else {
                    return MessageUtil.createMessage(false, "新增失败!" + message.getMessage());
                }
            }
        } else {
            HisPatientInfo checkIfExit = hisPatientInfoMapper.selectByPrimaryKey(hisPatientInfo.getId());
            if (EmptyUtil.Companion.isNullOrEmpty(checkIfExit)) {
                return MessageUtil.createMessage(false, "更新失败!该信息不存在!");
            }
            HisPatientInfo checkIdcard = hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard());
            if (EmptyUtil.Companion.isNullOrEmpty(checkIdcard)) {
                hisPatientInfoMapper.updateByPrimaryKeySelective(hisPatientInfo);
                return MessageUtil.createMessage(true, "更新成功");
            } else {
                if (hisPatientInfo.getId().equals(checkIdcard.getId())) {
                    hisPatientInfoMapper.updateByPrimaryKeySelective(hisPatientInfo);
                    return MessageUtil.createMessage(true, "更新成功");
                } else {
                    return MessageUtil.createMessage(false, "更新失败!所变更的身份证号已存在");
                }
            }
        }
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-06-19
     * @Time 12:02
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisPatientInfoMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return
     * @Description list查询
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 14:30
     **/
    @Override
    // 数据库事务
    @Transactional(readOnly = true)
    public PageBean<HisPatientInfo> list(PageBean<HisPatientInfo> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisPatientInfoMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.hiscore.entity.HisPatientInfo
     * @Description 初始化
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-06-19
     * @Time 11:57
     **/
    @Override
    @Transactional(readOnly = true)
    public HisPatientInfo updateInit(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisPatientInfoMapper.selectByPrimaryKey(id));
    }

    /**
     * @return com.ahsj.hiscore.entity.HisPatientInfo
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-06-21
     * @Time 20:44
     **/
    @Override
    public HisPatientInfo selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisPatientInfoMapper.selectByPrimaryKey(id));
    }


    @Override
    @Transactional(readOnly = true)
    public HisPatientInfo selectByMedicalRecordId(String medicalRecordId) throws Exception {
        HisPatientInfo hisPatientInfo = hisPatientInfoMapper.selectByMedicalRecordId(medicalRecordId);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo)) {
            return CodeHelper.getInstance().setCodeValue(hisPatientInfo);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public HisPatientInfo selectByRecordId(String recordId) throws Exception {
        HisPatientInfo hisPatientInfo = hisPatientInfoMapper.selectByRecordId(recordId);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo)) {
            return CodeHelper.getInstance().setCodeValue(hisPatientInfo);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public HisPatientInfo selectByAnkleDetailNumber(String number) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(number)) {
            return null;
        } else {
            return hisPatientInfoMapper.selectByAnkleDetailNumber(number);
        }
    }

    /**
     * @param hisPatientInfo
     * @param request
     * @param response
     * @param session
     * @Description 病人信息导出
     * @Author: dingli
     * @return: void
     * @Date 2019/8/29
     * @Time 16:01
     **/
    @Override
    @Transactional(readOnly = true)
    public void exportExcels(Long[] ids, String param, HisPatientInfo hisPatientInfo, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        HashMap<String, Object> beans = new HashMap<String, Object>();
        String psth = null;
        List<HisPatientInfo> hisPatientInfoList = null;
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            hisPatientInfoList = CodeHelper.getInstance().setCodeValue(hisPatientInfoMapper.hisPatientInfoList(hisPatientInfo));
            logger.info("------根据条件导出病人信息表------");
        } else {
            hisPatientInfoList = CodeHelper.getInstance().setCodeValue(hisPatientInfoMapper.selectByIds(ids));
            logger.info("------根据id导出病人信息表------");
        }
        if (StringUtils.equals(param, Constants.HIS_CH)) {//中文导出
         //   psth = this.getClass().getClassLoader().getResource("templates/excel/export/hisPatientInfo_CH.xlsx").getPath();  //目标路径
            psth =Constants.HIS_SYS_EXCEL_HIS_PATIENT_INFO_CH_FILE_URL;
            logger.info("------导出病人信息表中文版------");
        }
        if (StringUtils.equals(param, Constants.HIS_KM)) {//高棉语导出
         //   psth = this.getClass().getClassLoader().getResource("templates/excel/export/hisPatientInfo_KM.xlsx").getPath();  //目标路径
            psth =Constants.HIS_SYS_EXCEL_HIS_PATIENT_INFO_KM_FILE_URL;
            for (HisPatientInfo patientInfo : hisPatientInfoList) {
                Translate translate = new Translate();
                translate.setTranslateId(patientInfo.getMarriedId());
                translate.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                translates.stream().filter(f -> f.getTranslateChina().equals(patientInfo.getMarried())).forEach(e -> patientInfo.setMarried(e.getTranslateKhmer()));
                Translate translate1 = new Translate();
                translate1.setTranslateId(patientInfo.getSexId());
                translate1.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                List<Translate> translates1 = iTranslateService.queryTranslate(translate1);
                translates1.stream().filter(f -> f.getTranslateChina().equals(patientInfo.getSexName())).forEach(e -> patientInfo.setSexName(e.getTranslateKhmer()));
            }
            logger.info("------导出病人信息表高棉语版------");
        }
        beans.put("hisPatientInfo", hisPatientInfoList);
        if (StringUtils.equals(param, Constants.HIS_CH)) {
            JxlsUtil.export(request, response, psth, "病人信息表", beans);
        }
        if (StringUtils.equals(param, Constants.HIS_KM)) {//高棉语导出
            JxlsUtil.export(request, response, psth, "សំណុំបែបបទព័ត៌មានរបស់អ្នកជម្ងឺ។", beans);
        }
        return;
    }

    /**
     * @param successList
     * @Description 病人信息导入
     * @Author: dingli
     * @return: java.util.Map
     * @Date 2019/8/30
     * @Time 16:41
     **/
    @Override
    @Transactional(readOnly = false)
    public Map importExcel(List<HisPatientInfo> successList, String filename) throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<HisImportInfo> importInfoList = new ArrayList<>();
        if (!EmptyUtil.Companion.isNullOrEmpty(successList) && successList.size() > 0) {
            for (int i = 0; i < successList.size(); i++) {
                SysCodeDetail detail = new SysCodeDetail();
                detail.setValue(successList.get(i).getSexName());
                String code = iCodeService.getSysCodeName(detail).getCode();
                successList.get(i).setSex(Integer.parseInt(code));//设置字典code
                SysCodeDetail details = new SysCodeDetail();
                details.setValue(successList.get(i).getMarried());
                String codes = iCodeService.getSysCodeName(details).getCode();
                successList.get(i).setIsMarried(Integer.parseInt(codes));
            }
            hisPatientInfoMapper.importHisPatientInfo(successList);//批量插入
        }
        map.put("success", successList.size());  //成功的条数
        return map;
    }

    @Override
    public HisPatientInfo selectByPrimaryKey(Long id) throws Exception {
        return hisPatientInfoMapper.selectByPrimaryKey(id);
    }

    /**
     *@Description  根据就诊记录编号  查询相关信息（为HHM编号服务）
     *@Params [hospitalManageId]
     *@return com.ahsj.hiscore.entity.HisPatientInfo
     *@Author zhushixiang
     *@Date 2019-09-26
     *@Time 0:57
    **/
    @Override
    @Transactional(readOnly = true)
    public HisPatientInfo selectByMedicalRecordIdForInhospital(String hospitalManageId) {
        return hisPatientInfoMapper.selectByMedicalRecordIdForInhospital(hospitalManageId);
    }
}
