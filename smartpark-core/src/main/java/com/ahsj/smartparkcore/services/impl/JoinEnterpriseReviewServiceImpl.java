package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.common.utils.FileOperateUtil;
import com.ahsj.smartparkcore.common.utils.ZipUtils;
import com.ahsj.smartparkcore.controller.BaseLoginUser;
import com.ahsj.smartparkcore.dao.JoinEnterpriseReviewMapper;
import com.ahsj.smartparkcore.entity.dto.JoinEnterpriseReviewDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.JoinEnterpriseReview;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.JoinEnterpriseReviewVO;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.ahsj.smartparkcore.services.JoinEnterpriseReviewService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * FileName: JoinEnterpriseReviewServiceImpl
 * <p>
 * Date:     2019/10/22 15:07
 *
 * @author XJP
 * @create 2019/10/22
 * @since 1.0.0
 */

@Service
public class JoinEnterpriseReviewServiceImpl implements JoinEnterpriseReviewService {


    private static final Long FILE_SIZE = 10485760L;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    @Autowired
    EnterpriseInfoService enterpriseInfoService;

    private static final String filePath = Constants.FILE_PATHS;

    private static final String SUB_FILEPATH = Constants.FILE_PATHS_LOCAL;

    @Autowired
    JoinEnterpriseReviewMapper joinEnterpriseReviewMapper;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [joinEnterpriseReviewDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/22
     * @Time 16:12
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addJoinEnterpriseReview(JoinEnterpriseReviewDTO joinEnterpriseReviewDTO, MultipartFile file, String relateKet, String relatePage) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(file)) {
            return MessageUtil.createMessage(false, "申请加入公司失败 ！ 请上传图片！！");
        }
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        JoinEnterpriseReview joinEnterpriseReview = new JoinEnterpriseReview();
        enterpriseInfo.setName(joinEnterpriseReviewDTO.getName());
        BeanUtils.copyProperties(joinEnterpriseReviewDTO, joinEnterpriseReview);
        EnterpriseInfo enterpriseInfo1 = enterpriseInfoService.queryEnterpriseInfo(enterpriseInfo);
        if (EmptyUtil.Companion.isNullOrEmpty(joinEnterpriseReviewDTO.getEmail()) || EmptyUtil.Companion.isNullOrEmpty(enterpriseInfo1) || EmptyUtil.Companion.isNullOrEmpty(joinEnterpriseReviewDTO.getPhoneNumber())) {
            return MessageUtil.createMessage(false, "申请加入公司失败 ！ ！！");
        }
        joinEnterpriseReview.setEnterpriseId(enterpriseInfo1.getId());
        JoinEnterpriseReview review = joinEnterpriseReviewMapper.queryJoinEnterpriseReview(joinEnterpriseReview);
        JoinEnterpriseReview review1 = joinEnterpriseReviewMapper.queryJoinEnterpriseReviewByEmailAndPhone(joinEnterpriseReview);
        if (EmptyUtil.Companion.isNullOrEmpty(review) && EmptyUtil.Companion.isNullOrEmpty(review1)) {
            joinEnterpriseReview.setEnterpriseId(enterpriseInfo1.getId());
            joinEnterpriseReview.setIsReview(2);
            joinEnterpriseReviewMapper.insert(joinEnterpriseReview);
            uploadFile(file, relateKet, relatePage, joinEnterpriseReview.getId());
            return MessageUtil.createMessage(false, "申请加入公司成功 ！ ！！");
        } else {
            return MessageUtil.createMessage(false, "申请加入公司失败 ！该用户已存在 ！！");
        }
    }

    /**
     * @return com.ahsj.smartparkcore.entity.vo.JoinEnterpriseReviewVO
     * @功能说明
     * @Params [relateId, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/22
     * @Time 17:26
     **/
    @Override
    @Transactional(readOnly = true)
    public JoinEnterpriseReviewVO queryJoinEnterpriseReview(Long relateId, String relateKet, String relatePage) throws Exception {
        JoinEnterpriseReview joinEnterpriseReview = joinEnterpriseReviewMapper.selectByPrimaryKey(relateId);
        JoinEnterpriseReviewVO enterpriseReviewVO = new JoinEnterpriseReviewVO();
        BeanUtils.copyProperties(joinEnterpriseReview, enterpriseReviewVO);
        SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
        sysAttachmentInfo.setRelateId(relateId);
        sysAttachmentInfo.setRelateKey(relateKet);
        sysAttachmentInfo.setRelatePage(relatePage);
        List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
        SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
//        String length = Constants.FILE_PATHS_LOCAL;
        //sysAttachmentInfo1.setLocation(StringUtils.substring(sysAttachmentInfo1.getLocation(), length.length(), sysAttachmentInfo1.getLocation().length()));
        String replace = sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
        enterpriseReviewVO.setFilePath(replace);
        return enterpriseReviewVO;
    }


    /**
     * @return void
     * @功能说明
     * @Params [file, relateKet, relatePage, relateId]
     * @Author XJP
     * @Date 2019/10/22
     * @Time 17:26
     **/
    public void uploadFile(MultipartFile file, String relateKet, String relatePage, Long relateId) {
        BaseLoginUser baseLoginUser = new BaseLoginUser();
        LocalDate now = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateDir = now.format(df);
        // SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
        List<SysAttachmentInfo> list = new ArrayList<>();
        try {
            String fileCode = String.valueOf(System.nanoTime());
            SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            // 获取文件名
            String oriFileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffix = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
            //获得文件的大小
            String size = ZipUtils.convertFileSize(file.getSize());
            //文件上传的路径
            String dirPath = filePath + dateDir + "/";
            String afterPath = StringUtils.substringAfter(dirPath, SUB_FILEPATH);
            // 获取文件上传的全路径（防止文件名称相同）
            String fullFilePath = dirPath + fileCode + suffix;

            String fullFilePaths = afterPath + fileCode + suffix;
            FileOperateUtil.mkDir(new File(dirPath));
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fullFilePath));
            Long aLong = Long.valueOf(file.getSize());
            if (aLong > FILE_SIZE) {
                ZipUtils.toZip(fullFilePath, outputStream, true);
            }
            outputStream.write(file.getBytes());
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
            sysAttachmentInfo.setUploadUser(baseLoginUser.getId());
            //上传时间
            sysAttachmentInfo.setUploadDate(new Date());
            //附件路径
            sysAttachmentInfo.setLocation(fullFilePaths);
            list.add(sysAttachmentInfo);
            //保存数据
            //  sysAttachmentInfoService.addSaveSysAttachmentInfo(sysAttachmentInfo);
            sysAttachmentInfoService.addSaveSysAttachmentInfoList(list);//调用addSaveSysAttachmentInfoList保存到数据库的方法
            // return MessageUtil.createMessage(true, "上传成功!");
        } catch (Exception e) {
            e.printStackTrace();
            // return MessageUtil.createMessage(true, "上传失败!");
        }

    }
}
