package com.ahsj.translate.feign;

import com.ahsj.translate.entity.Organization;
import com.ahsj.translate.entity.TreeEntity;
import com.ahsj.translate.fallback.factory.IOrganizationServiceFallbackFactory;
import com.ahsj.translate.feign.config.FeignConfiguration;
import core.entity.PageBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    List<Organization> lstAllSysOrganization();

    @PostMapping("/api/organization/treeCode.ahsj")
    @LoadBalanced
     List<TreeEntity> treeCode(@RequestParam("lv") Integer lv, @RequestParam("id") String id);

    @RequestMapping("/api/organization/selectUserByOrgId.ahsj")
    @LoadBalanced
     Organization  selectUserByOrgId(@RequestParam("id") Long id);

/*    @PostMapping("/api/organization/selectUserByOrgId.ahsj")
    @LoadBalanced
     ArrayList<UserDept> selectUserByOrgId(@RequestParam("id") String id) throws Exception;*/

    @GetMapping("/api/organization/select/organization.ahsj")
    @LoadBalanced
    Organization getOrganizationById(@RequestParam("id") Long id) throws Exception;

    @GetMapping("/api/organization/info/inner/id.ahsj")
    @LoadBalanced
    Organization getOrganizationByIds(@RequestParam("id") Long id) throws Exception;

    @GetMapping("/api/organization/query/selects/organizations.ahsj")
    @LoadBalanced
    PageBean<Organization>queryList(Organization organization) throws Exception;

    @GetMapping("/api/organization/query/selects/queryOrganizationList.ahsj")
    @LoadBalanced
    List<Organization> queryOrganizationList(@RequestParam("id") Long id);

}
