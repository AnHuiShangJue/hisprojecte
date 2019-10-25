package com.ahsj.smartparkcore.controller.enterprise;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.common.utils.FileOperateUtil;
import com.ahsj.smartparkcore.common.utils.ZipUtils;
import com.ahsj.smartparkcore.controller.BaseLoginUser;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO;
import com.ahsj.smartparkcore.feign.IOrganizationService;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private static final String SUB_FILEPATH = Constants.FILE_PATHS_LOCAL;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    @Autowired
    IOrganizationService iOrganizationService;


    private static final String filePath = Constants.FILE_PATHS;


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/11
     * @Time 17:02
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView index(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/enterprise/list");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入企业新增页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 11:10
     **/
    @GetMapping("/addEnterprise.ahsj")
    public ModelAndView addEnterprise(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/enterprise/add");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @GetMapping("/updateEnterprise.ahsj")
    public ModelAndView addEnterprise(String token, @RequestParam("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/enterprise/update");
        EnterpriseInfoVO enterpriseInfoVO = enterpriseInfoService.selectById(id);
        SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
        sysAttachmentInfo.setRelateId(888L);
        sysAttachmentInfo.setRelateKey("8");
        sysAttachmentInfo.setRelatePage("8");
        List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
        SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
        String length = Constants.FILE_PATHS_LOCAL;
        sysAttachmentInfo1.setLocation(StringUtils.substring(sysAttachmentInfo1.getLocation(), length.length(), sysAttachmentInfo1.getLocation().length()));
        String replace = sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
        enterpriseInfoVO.setFilePath(replace);
        modelAndView.addObject("token", token);
        modelAndView.addObject("enterpriseInfoVO", enterpriseInfoVO);
        return modelAndView;
    }


    @GetMapping("/audit.ahsj")
    public ModelAndView audit(String token, @RequestParam("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/enterprise/audit");
        EnterpriseInfoVO enterpriseInfoVO = enterpriseInfoService.selectById(id);
        SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
        sysAttachmentInfo.setRelateId(888L);
        sysAttachmentInfo.setRelateKey("8");
        sysAttachmentInfo.setRelatePage("8");
        List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
        SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
        String length = Constants.FILE_PATHS_LOCAL;
        sysAttachmentInfo1.setLocation(StringUtils.substring(sysAttachmentInfo1.getLocation(), length.length(), sysAttachmentInfo1.getLocation().length()));
        String replace = sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
        enterpriseInfoVO.setFilePath(replace);
        modelAndView.addObject("token", token);
        modelAndView.addObject("enterpriseInfoVO", enterpriseInfoVO);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @功能说明 新增 企业信息
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 15:52
     **/
    @PostMapping("/add/enterpriseInfo.ahsj")
    public ResponseEntity<Message> addEnterpriseInfo(EnterpriseInfoDTO enterpriseInfoDTO, @RequestParam(value = "file", required = false) MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        Message message = enterpriseInfoService.addEnterpriseInfo(enterpriseInfoDTO, file, relateKet, relatePage);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }

    /**
     * @return core.message.Message
     * @功能说明 修改 企业信息
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 15:52
     **/
    @PostMapping("/update/enterpriseInfo.ahsj")
    public ResponseEntity<Message> updateEnterpriseInfo(EnterpriseInfoDTO enterpriseInfoDTO, @RequestParam(value = "file", required = false) MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        Message message = enterpriseInfoService.updateEnterpriseInfo(enterpriseInfoDTO, file, relateKet, relatePage);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }

    /**
     * @return org.springframework.http.ResponseEntity<core.message.Message>
     * @功能说明
     * @Params [enterpriseInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 10:59
     **/
    @PostMapping("/update/enterpriseInfo/isEnable.ahsj")
    public ResponseEntity<Message> updateIsEnable(EnterpriseInfoDTO enterpriseInfoDTO) throws Exception {
        Message message = enterpriseInfoService.updateIsEnable(enterpriseInfoDTO);
        return new ResponseEntity<>((message), HttpStatus.OK);
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
    public ResponseEntity<PageBean<EnterpriseInfo>> queryList(EnterpriseInfoDTO enterpriseInfoDTO) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        EnterpriseInfo map = mapper.map(enterpriseInfoDTO, EnterpriseInfo.class);
        PageBean<EnterpriseInfo> pageBean = new PageBean<>();
        pageBean.setParameter(map);
        return ResponseEntity.ok(enterpriseInfoService.queryList(pageBean));
    }

    /**
     * @return core.message.Message
     * @功能说明 审核企业信息
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 17:40
     **/
    @PostMapping("/reviewSuccess.ahsj")
    public Message reviewSuccess(@RequestParam("id") Long id) throws Exception {
        return enterpriseInfoService.auditEnterpriseInfo(id);
    }

    @PostMapping("/reviewFail.ahsj")
    public Message reviewFail(@RequestParam("id") Long id) throws Exception {
        return enterpriseInfoService.reviewFail(id);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.entity.po.EnterpriseInfo>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:08
     **/
    @PostMapping("/enterpriseInfoId.ahsj")
    public ResponseEntity<EnterpriseInfo> queryEnterpriseInfo(@RequestParam("id") Long id) throws Exception {
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByPrimaryKey(id);
        return ResponseEntity.ok(enterpriseInfo);
    }


    //APP

    /**
     * @return org.springframework.http.ResponseEntity<java.util.List < com.ahsj.smartparkcore.entity.po.EnterpriseInfo>>
     * @功能说明
     * @Params [token]
     * @Author XJP
     * @Date 2019/10/23
     * @Time 14:49
     **/
    @RequestMapping("/queryListAll.ahsj")
    public ResponseEntity<List<EnterpriseInfo>> queryEnterpriseInfo() throws Exception {
        List<EnterpriseInfo> enterpriseInfos = enterpriseInfoService.queryListAll();
        return ResponseEntity.ok(enterpriseInfos);
    }

    /**
     * @return void
     * @功能说明
     * @Params [file, relateKet, relatePage, relateId]
     * @Author XJP
     * @Date 2019/10/23
     * @Time 14:49
     **/
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
}
