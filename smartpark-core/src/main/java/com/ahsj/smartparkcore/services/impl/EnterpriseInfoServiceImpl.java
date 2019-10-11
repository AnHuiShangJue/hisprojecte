package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.common.utils.FileOperateUtil;
import com.ahsj.smartparkcore.common.utils.ZipUtils;
import com.ahsj.smartparkcore.controller.BaseLoginUser;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.EnterpriseInfoMapper;
import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO;
import com.ahsj.smartparkcore.feign.IOrganizationService;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import utils.EmptyUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: EnterpriseInfoServiceImpl
 * <p>
 * Date:     2019/9/2 15:42
 *
 * @author XJP
 * @create 2019/9/2
 * @since 1.0.0
 */

@Service
public class EnterpriseInfoServiceImpl extends BaseLoginUser implements EnterpriseInfoService {

    Logger log = LoggerFactory.getLogger(EnterpriseInfoServiceImpl.class.getSimpleName());

    @Autowired
    EnterpriseInfoMapper enterpriseInfoMapper;

    private static final Long FILE_SIZE = 10485760L;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Value("${file.path}")
    private String filePath;

    /**
     * @return core.message.Message
     * @功能说明 新增 and 修改 企业信息
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 16:05
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addEnterpriseInfo(EnterpriseInfoDTO enterpriseInfoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        EnterpriseInfo map = mapper.map(enterpriseInfoDTO, EnterpriseInfo.class);

        if (EmptyUtil.Companion.isNullOrEmpty(enterpriseInfoDTO)) {
            return MessageUtil.createMessage(false, "企业信息新增失败 ！！！");
        } else if (EmptyUtil.Companion.isNullOrEmpty(map.getId())) {
            EnterpriseInfo info = enterpriseInfoMapper.queryEnterpriseInfo(map);
            if (!EmptyUtil.Companion.isNullOrEmpty(info)) {
                return MessageUtil.createMessage(false, "企业信息新增失败 ！ 该企业已存在 ！！");
            }
            map.setIsVerify(Constants.THREE);
            enterpriseInfoMapper.insert(map);
            //上传附件
            uploadFile(file,relateKet,relatePage,map.getId());
            return MessageUtil.createMessage(true, "企业信息新增成功 ！！！");
        } /*else {
            EnterpriseInfo info = enterpriseInfoMapper.queryEnterpriseInfo(map);
            if (EmptyUtil.Companion.isNullOrEmpty(info)) {
                enterpriseInfoMapper.updateByPrimaryKey(map);
                return MessageUtil.createMessage(true, "企业信息修改成功 ！！！");
            } else if (StringUtils.equals(info.getName(), map.getName())) {
                if (info.getId().longValue() == map.getId().longValue()) {
                    enterpriseInfoMapper.updateByPrimaryKey(map);
                    return MessageUtil.createMessage(true, "企业信息修改成功 ！！！");
                } else {
                    return MessageUtil.createMessage(false, "企业信息修改失败 ！ 该企业已存在  ！！");
                }
            }
            return MessageUtil.createMessage(false, "企业信息修改失败 ！ 该企业已存在  ！！");
        }*/
        return MessageUtil.createMessage(false, "企业信息新增失败 ！ ");
    }

    @Override
    @Transactional(readOnly = false)
    public Message updateEnterpriseInfo(EnterpriseInfoDTO enterpriseInfoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception {

        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        EnterpriseInfo map = mapper.map(enterpriseInfoDTO, EnterpriseInfo.class);


        if (EmptyUtil.Companion.isNullOrEmpty(enterpriseInfoDTO)) {
            return MessageUtil.createMessage(false, "企业信息新增失败 ！！！");
        }/* else if (EmptyUtil.Companion.isNullOrEmpty(map.getId())) {
            EnterpriseInfo info = enterpriseInfoMapper.queryEnterpriseInfo(map);
            if (!EmptyUtil.Companion.isNullOrEmpty(info)) {
                return MessageUtil.createMessage(false, "企业信息新增失败 ！ 该企业已存在 ！！");
            }
            map.setIsVerify(Constants.THREE);
            enterpriseInfoMapper.insert(map);
            //上传附件
            uploadFile(file,relateKet,relatePage,map.getId());
            return MessageUtil.createMessage(true, "企业信息新增成功 ！！！");
        }*/ else {
            EnterpriseInfo info = enterpriseInfoMapper.queryEnterpriseInfo(map);
            if (EmptyUtil.Companion.isNullOrEmpty(info)) {
                enterpriseInfoMapper.updateByPrimaryKey(map);
                return MessageUtil.createMessage(true, "企业信息修改成功 ！！！");
            } else if (StringUtils.equals(info.getName(), map.getName())) {
                if (info.getId().longValue() == map.getId().longValue()) {
                    enterpriseInfoMapper.updateByPrimaryKey(map);
                    return MessageUtil.createMessage(true, "企业信息修改成功 ！！！");
                } else {
                    return MessageUtil.createMessage(false, "企业信息修改失败 ！ 该企业已存在  ！！");
                }
            }
            return MessageUtil.createMessage(false, "企业信息修改失败 ！ 该企业已存在  ！！");
        }

    }


    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.EnterpriseInfo>
     * @功能说明 查询企业信息并进行分页处理
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 16:49
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<EnterpriseInfo> queryList(PageBean<EnterpriseInfo> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(enterpriseInfoMapper.queryList(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明 审核企业信息
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 17:42
     **/
    @Override
    @Transactional(readOnly = false)
    public Message auditEnterpriseInfo(EnterpriseInfo enterpriseInfo, String audit) {
        if (EmptyUtil.Companion.isNullOrEmpty(enterpriseInfo)) {
            return MessageUtil.createMessage(false, "企业信息审核失败 ！！！");
        } else if (enterpriseInfo.getIsVerify().equals(Constants.ONE)) {
            return MessageUtil.createMessage(false, "该企业信息审核已经成功！ 无法继续审核 ！！！！！");
        } else {
            if (StringUtils.equals(audit, Constants.SUCCESSFUL)) {
                enterpriseInfo.setIsVerify(Constants.ONE);
                enterpriseInfoMapper.updateByPrimaryKey(enterpriseInfo);
                return MessageUtil.createMessage(true, "企业信息审核成功 ！该企业验证通过 ！！");
            } else if (StringUtils.equals(audit, Constants.ERROR)) {
                enterpriseInfo.setIsVerify(Constants.TWO);
                enterpriseInfoMapper.updateByPrimaryKey(enterpriseInfo);
                return MessageUtil.createMessage(true, "企业信息审核成功 ！该企业验证失败 ！！");
            }
            return MessageUtil.createMessage(false, "企业信息审核失败 ！！！");
        }
    }

    @Override
    @Transactional
    public EnterpriseInfo selectByPrimaryKey(Long id) throws Exception {
        return enterpriseInfoMapper.selectByPrimaryKey(id);
    }


    /**
     * @return core.message.Message
     * @功能说明
     * @Params [file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/9/3
     * @Time 10:27
     **/
    public void uploadFile(MultipartFile[] file, String relateKet, String relatePage,Long relateId) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateDir = now.format(df);
        // SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
        List<SysAttachmentInfo> list = new ArrayList<>();
        try {
            String fileCode = String.valueOf(System.nanoTime());
            for (MultipartFile multipartFile : file) {
                SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
                // 获取文件名
                String oriFileName = multipartFile.getOriginalFilename();
                // 获取文件的后缀名
                String suffix = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
                //获得文件的大小
                String size = ZipUtils.convertFileSize(multipartFile.getSize());

                //文件上传的路径
                String dirPath = filePath + dateDir + "/";
                // 获取文件上传的全路径（防止文件名称相同）
                String fullFilePath = dirPath + fileCode + suffix;
                FileOperateUtil.mkDir(new File(dirPath));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fullFilePath));
                Long aLong = Long.valueOf(multipartFile.getSize());
                if (aLong > FILE_SIZE) {
                    ZipUtils.toZip(fullFilePath, outputStream, true);
                }
                outputStream.write(multipartFile.getBytes());
                outputStream.flush();
                outputStream.close();
                //附件名称
                sysAttachmentInfo.setFileName(fileCode + suffix);
                //附件原始名称
                sysAttachmentInfo.setFileOrgName(oriFileName);
                //附件类型
                sysAttachmentInfo.setFileType(suffix);
                //附件关联主键ID
                sysAttachmentInfo.setRelateId(relateId);
                //附件大小
                sysAttachmentInfo.setFileSize(size);
                //关联附件-key
                sysAttachmentInfo.setRelateKey(relateKet);
                //文件来源
                sysAttachmentInfo.setRelatePage(relatePage);
                //附件上传人ID
                sysAttachmentInfo.setUploadUser(getId());
                //上传时间
                sysAttachmentInfo.setUploadDate(new Date());
                //附件路径
                sysAttachmentInfo.setLocation(fullFilePath);

                list.add(sysAttachmentInfo);
                //保存数据
            }
            //  sysAttachmentInfoService.addSaveSysAttachmentInfo(sysAttachmentInfo);
            sysAttachmentInfoService.addSaveSysAttachmentInfoList(list);//调用addSaveSysAttachmentInfoList保存到数据库的方法
           // return MessageUtil.createMessage(true, "上传成功!");
        } catch (Exception e) {
            e.printStackTrace();
           // return MessageUtil.createMessage(true, "上传失败!");
        }

    }


}

