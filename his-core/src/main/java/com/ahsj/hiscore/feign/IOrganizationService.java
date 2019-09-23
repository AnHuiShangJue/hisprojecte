package com.ahsj.hiscore.feign;

import com.ahsj.hiscore.entity.TreeEntity;
import com.ahsj.hiscore.entity.UserInfo;
import com.ahsj.hiscore.entity.dto.UserDept;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysAttachmentInfo;
import com.ahsj.hiscore.fallback.factory.IOrganizationServiceFallbackFactory;
import com.ahsj.hiscore.feign.config.FeignConfiguration;
import core.entity.PageBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: his
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-01-17-44
 **/
@FeignClient(name = "eureka-userinfo", fallbackFactory = IOrganizationServiceFallbackFactory.class, configuration = FeignConfiguration.class)
public interface IOrganizationService {


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.Organization>
     * @Description 查询所有的组织
     * @Params []
     * @Author chenzhicai
     * @Date 2019/7/1
     * @Time 5:52 PM
     **/
    @PostMapping("/api/organization/info/inner/lstAllSysOrganization.ahsj")
    @LoadBalanced
    List<Organization> lstAllSysOrganization() throws Exception;

    @PostMapping("/api/organization/treeCode.ahsj")
    @LoadBalanced
    List<TreeEntity> treeCode(@RequestParam("lv") Integer lv, @RequestParam("id") String id) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.sys.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:56
     **/
    @RequestMapping("/api/organization/selectUserByOrgId.ahsj")
    @LoadBalanced
    Organization selectUserByOrgId(@RequestParam("id") Long id) throws Exception;

    /**
     * @return java.util.ArrayList<com.ahsj.hiscore.entity.dto.UserDept>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:56
     **/
    @PostMapping("/api/organization/selectUserByOrgId.ahsj")
    @LoadBalanced
    ArrayList<UserDept> selectUserByOrgId(@RequestParam("id") String id) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.sys.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:56
     **/
    @GetMapping("/api/organization/select/organization.ahsj")
    @LoadBalanced
    Organization getOrganizationById(@RequestParam("id") Long id) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.sys.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:56
     **/
    @GetMapping("/api/organization/info/inner/id.ahsj")
    @LoadBalanced
    Organization getOrganizationByIds(@RequestParam("id") Long id) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:57
     **/
    @GetMapping("/api/organization/query/selects/organizations.ahsj")
    @LoadBalanced
    PageBean<Organization> queryList(@RequestBody Organization organization) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:57
     **/
    @GetMapping("/api/organization/query/selects/queryOrganizationList.ahsj")
    @LoadBalanced
    List<Organization> queryOrganizationList(@RequestParam("id") Long id) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:57
     **/
    @GetMapping("/api/organization/query/selects/queryOrganizationListDate.ahsj")
    @LoadBalanced
    List<Organization> queryTranslateInfoByDate(@RequestBody Organization organization) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 15:08
     **/
    @PostMapping("/api/organization/query/selects/getOrganizationAll.ahsj")
    @LoadBalanced
    List<Organization> getOrganizationAll() throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.sys.Organization
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:09
     **/
    @PostMapping("/api/organization/query/selects/getOrganizationName.ahsj")
    @LoadBalanced
    Organization getOrganizationName(@RequestBody Organization organization) throws Exception;

}
