package com.ahsj.smartparkcore.controller.enterprise;

import com.ahsj.smartparkcore.common.utils.FileOperateUtil;
import com.ahsj.smartparkcore.common.utils.ZipUtils;
import com.ahsj.smartparkcore.dao.EnterpriseInfoMapper;
import com.ahsj.smartparkcore.entity.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.feign.IOrganizationService;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
 * FileName: EnterpriseInfoController
 * <p>
 * Date:     2019/9/2 15:38
 *
 * @author XJP
 * @create 2019/9/2
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseInfoController extends BaseController {

    @Autowired
    EnterpriseInfoService enterpriseInfoService;

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
     * @Time 15:52
     **/
    @PostMapping("/addOrupdateEnterpriseInfo.ahsj")
    public Message addOrUpdateEnterpriseInfo(EnterpriseInfo enterpriseInfo,@RequestParam(value = "file") MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        return enterpriseInfoService.addOrUpdateEnterpriseInfo(enterpriseInfo,file,relateKet,relatePage);
    }


    @GetMapping("/index.ahsj")
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/enterprise/index");
        return modelAndView;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.EnterpriseInfo>
     * @功能说明 查询企业信息并进行分页处理
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 16:44
     **/
    @PostMapping("/list.ahsj")
    public PageBean<EnterpriseInfo> queryList(EnterpriseInfo enterpriseInfo) {
        PageBean<EnterpriseInfo> pageBean = new PageBean<>();
        pageBean.setParameter(enterpriseInfo);
        return enterpriseInfoService.queryList(pageBean);
    }

    /**
     * @return core.message.Message
     * @功能说明 审核企业信息
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 17:40
     **/
    @PostMapping("/audit.ahsj")
    public Message auditEnterpriseInfo(EnterpriseInfo enterpriseInfo, @RequestParam("audit") String audit) {
        return enterpriseInfoService.auditEnterpriseInfo(enterpriseInfo,audit);
    }
    @PostMapping("/uploadFile.ahsj")
    public void uploadFile(@RequestParam("file") MultipartFile[] file, @RequestParam("relateKet")String relateKet,@RequestParam("relatePage") String relatePage,@RequestParam("relateId")Long relateId) {
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
