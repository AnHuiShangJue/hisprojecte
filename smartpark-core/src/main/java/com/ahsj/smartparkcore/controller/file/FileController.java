package com.ahsj.smartparkcore.controller.file;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.common.utils.FileOperateUtil;
import com.ahsj.smartparkcore.common.utils.ZipUtils;
import com.ahsj.smartparkcore.controller.BaseLoginUser;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.feign.IOrganizationService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
 * FileName: FileController
 * <p>
 * Date:     2019/10/21 15:58
 *
 * @author XJP
 * @create 2019/10/21
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/file")
public class FileController extends BaseController {

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    private static final Long FILE_SIZE = 10485760L;

    private static final String SUB_FILEPATH = Constants.FILE_PATHS_LOCAL;

    @Value("${file.paths}")
    private String filePath;

    @PostMapping("/uploadFile.ahsj")
    public void uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("relateKet") String relateKet, @RequestParam("relatePage") String relatePage, @RequestParam("relateId") Long relateId) {
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

    @PostMapping("/getFile.ahsj")
    public List<SysAttachmentInfo> getFile(@RequestParam("relateKet") String relateKet, @RequestParam("relatePage") String relatePage, @RequestParam("relateId") Long relateId) throws Exception {
        SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
        sysAttachmentInfo.setRelateId(relateId);
        sysAttachmentInfo.setRelateKey(relateKet);
        sysAttachmentInfo.setRelatePage(relatePage);
        List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
        return sysAttachmentInfos;
    }
}