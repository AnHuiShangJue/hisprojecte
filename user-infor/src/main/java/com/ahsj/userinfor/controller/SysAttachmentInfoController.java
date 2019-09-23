package com.ahsj.userinfor.controller;


import com.ahsj.userinfor.entity.SysAttachmentInfo;
import com.ahsj.userinfor.entity.dto.SysAttachmentInfoFeign;
import com.ahsj.userinfor.services.SysAttachmentInfoService;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/attachment")
public class SysAttachmentInfoController extends BaseController {

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;


    /**
     * @return void
     * @Description 单一保存文件
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:07
     **/
    @PostMapping("/add/sysAttachmentInfo.ahsj")
    @ResponseBody
    public void addSaveSysAttachmentInfo(@RequestBody SysAttachmentInfo sysAttachmentInfo) throws Exception {
        sysAttachmentInfoService.insert(sysAttachmentInfo);
    }

    /**
     * @return void
     * @Description 批量保存文件
     * @Params [sysAttachmentInfoFeign]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:07
     **/
    @PostMapping("/add/sysAttachmentInfoList.ahsj")
    @ResponseBody
    public void addSaveSysAttachmentInfoList(@RequestBody SysAttachmentInfoFeign sysAttachmentInfoFeign) throws Exception {
        List<SysAttachmentInfo> attachmentInfoList = sysAttachmentInfoFeign.getAttachmentInfoList();
        sysAttachmentInfoService.insertList(attachmentInfoList);
    }

    /**
     * @return com.ahsj.userinfor.entity.SysAttachmentInfo
     * @Description 通过主键查询
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:08
     **/
    @GetMapping("/query/SysAttachmentInfo.ahsj")
    @ResponseBody
    public SysAttachmentInfo selectByPrimaryKey(@RequestParam("id") Long id) throws Exception {
        return sysAttachmentInfoService.selectByPrimaryKey(id);
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysAttachmentInfo>
     * @Description 通过对象查询集合
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:09
     **/
    @PostMapping("/query/querySysAttachmentInfo.ahsj")
    @ResponseBody
    public List<SysAttachmentInfo> querySysAttachmentInfo(@RequestBody SysAttachmentInfo sysAttachmentInfo) {
        return sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
    }


    /**
     * @return void
     * @功能说明 更新操作
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:34
     **/
    @PostMapping("/update/SysAttachmentInfo.ahsj")
    @ResponseBody
    public void updateSysAttachmentInfo(@RequestBody SysAttachmentInfo sysAttachmentInfo) {
        sysAttachmentInfoService.updateSysAttachmentInfo(sysAttachmentInfo);
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
    public void deleteSysAttachmentInfoById(@RequestParam("id") Long id) {
        sysAttachmentInfoService.deleteSysAttachmentInfoById(id);
    }

}
