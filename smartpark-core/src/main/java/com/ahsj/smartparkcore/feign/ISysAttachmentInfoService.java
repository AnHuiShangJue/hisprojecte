package com.ahsj.smartparkcore.feign;

import com.ahsj.smartparkcore.entity.dto.SysAttachmentInfoFeign;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.fallback.factory.ISysAttachmentInfoServiceFallnackFactotory;
import com.ahsj.smartparkcore.feign.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ISysAttachmentInfoService
 * <p>
 * Date:     2019/7/21 9:45
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */
@FeignClient(name = "eureka-userinfo", fallbackFactory = ISysAttachmentInfoServiceFallnackFactotory.class, configuration = FeignConfiguration.class)
public interface ISysAttachmentInfoService {
    /**
     * @return void
     * @功能说明
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:59
     **/
    @PostMapping("/api/attachment/add/sysAttachmentInfo.ahsj")
    @LoadBalanced
    void addSaveSysAttachmentInfo(@RequestBody SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     * @return void
     * @功能说明
     * @Params [sysAttachmentInfoFeign]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:59
     **/
    @PostMapping("/api/attachment/add/sysAttachmentInfoList.ahsj")
    @LoadBalanced
    void addSaveSysAttachmentInfoList(@RequestBody SysAttachmentInfoFeign sysAttachmentInfoFeign) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.sys.SysAttachmentInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:59
     **/
    @GetMapping("/api/attachment/query/SysAttachmentInfo.ahsj")
    @LoadBalanced
    SysAttachmentInfo selectByPrimaryKey(@RequestParam("id") Long id) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysAttachmentInfo>
     * @功能说明
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:59
     **/
    @PostMapping("/api/attachment/query/querySysAttachmentInfo.ahsj")
    @LoadBalanced
    List<SysAttachmentInfo> querySysAttachmentInfo(@RequestBody SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     * @return void
     * @功能说明
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:59
     **/
    @PostMapping("/api/attachment/update/SysAttachmentInfo.ahsj")
    @LoadBalanced
    void updateSysAttachmentInfo(@RequestBody SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     * @return void
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:59
     **/
    @PostMapping("/api/attachment/delete/sysAttachmentInfo/id.ahsj")
    @LoadBalanced
    void deleteSysAttachmentInfoById(@RequestParam("id") Long id) throws Exception;
}
