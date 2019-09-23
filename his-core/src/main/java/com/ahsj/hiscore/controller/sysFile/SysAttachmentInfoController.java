package com.ahsj.hiscore.controller.sysFile;

import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.entity.sys.SysAttachmentInfo;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.SysAttachmentInfoService;
import core.controller.BaseController;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysAttachmentInfoController
 * <p>
 * Date:     2019/7/21 11:25
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/file")
public class SysAttachmentInfoController extends BaseController {

    private static final Long FILE_SIZE = 10485760L;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Value("${file.path}")
    private String filePath;


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 进入上传页面
     * @Params [token, url]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 19:15
     **/
    @GetMapping("/list.ahsj")
    public ModelAndView list(String token, String url) {
        //去重空格
        String trim = url.trim();
        String before = StringUtils.substringBefore(trim, ".");
        String[] split = before.split("/");
        String page = split[split.length - 1];
        String pageIndex = split[split.length - 2];
        String countPage = pageIndex + "_" + page;
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/sysFile/list");
        modelAndView.addObject("countPage", countPage);
        return modelAndView;
    }


    /**
     * @return core.message.Message
     * @Description 文件上传
     * @Params [file, countPage]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 18:49
     **/
    @PostMapping("/upload.ahsj")
    public Message uploadFile(@RequestParam("file") MultipartFile[] file, String relateKet, String relatePage) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateDir = now.format(df);
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
                mkDir(new File(dirPath));
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
            return MessageUtil.createMessage(true, "上传成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.createMessage(true, "上传失败!");
        }

    }

    /**
     * @return core.message.Message
     * @Description 文件下载相关代码
     * @Params [request, response, id]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 18:48
     **/
    @GetMapping("/download.ahsj")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
        SysAttachmentInfo sysAttachmentInfo = sysAttachmentInfoService.selectByPrimaryKey(id);
        String fileName = sysAttachmentInfo.getFileName();
        String url = sysAttachmentInfo.getLocation();
        if (fileName != null) {
            //设置文件路径
            File file = new File(url);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysAttachmentInfo>
     * @Description 通过关联页面和关联附件key 查询集合
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/22
     * @Time 18:47
     **/
    @PostMapping("/query/sysAttachmentInfo.ahsj")
    public List<SysAttachmentInfo> querySysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) throws Exception {
        List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
        return sysAttachmentInfos;
    }

    /**
     * @return core.message.Message
     * @功能说明 更新操作
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:34
     **/
    @PostMapping("/update/sysAttachmentInfo.ahsj")
    public Message updateSysAttachmentInfo(@RequestBody SysAttachmentInfo sysAttachmentInfo) throws Exception {
        return sysAttachmentInfoService.updateSysAttachmentInfo(sysAttachmentInfo);
    }

    /**
     * @return core.message.Message
     * @功能说明 对关联附件的key进行修改操作
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:58
     **/
    @PostMapping("/update/sysAttachmentInfo/relatekey.ahsj")
    public Message updateSysAttachmentInfoRelatekey(SysAttachmentInfo sysAttachmentInfo) throws Exception {
        return sysAttachmentInfoService.updateSysAttachmentInfoRelatekey(sysAttachmentInfo);
    }


    /**
     * @return void
     * @功能说明 删除
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 13:57
     **/
    @PostMapping("/delete/sysAttachmentInfo/id.ahsj")
    @ResponseBody
    public void deleteSysAttachmentInfoById(@RequestParam("id") Long id) throws Exception {
        sysAttachmentInfoService.deleteSysAttachmentInfoById(id);
    }


    /**
     * 判断文件夹是否存在
     *
     * @param file
     */
    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 //跳转上传
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/31
     * @Time 10:51
     **/
    @GetMapping("/uloads.ahsj")
    public ModelAndView excelFile(String token,@RequestParam("excelUrl") String excelUrl,@RequestParam("importExcelUrl") String importExcelUrl,@RequestParam("errorExcelUrl") String errorExcelUrl) {//importExcelUrl
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/uloads/uloads");
        modelAndView.addObject("excelUrl", excelUrl);
        modelAndView.addObject("importExcelUrl", importExcelUrl);
        modelAndView.addObject("errorExcelUrl", errorExcelUrl);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

}
