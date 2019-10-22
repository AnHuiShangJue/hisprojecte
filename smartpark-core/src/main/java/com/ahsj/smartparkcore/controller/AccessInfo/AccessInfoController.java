package com.ahsj.smartparkcore.controller.AccessInfo;

import com.ahsj.smartparkcore.entity.dto.AccessInfoDTO;
import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.po.AccessInfo;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.vo.AccessInfoVO;
import com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO;
import com.ahsj.smartparkcore.services.AccessInfoService;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: AccessInfoController
 * <p>
 * Date:     2019/10/14 15:29
 *
 * @author XJP
 * @create 2019/10/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/accessinfo")
public class AccessInfoController extends BaseController {

    @Autowired
    AccessInfoService accessInfoService;

    @Autowired
    EnterpriseInfoService enterpriseInfoService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [token]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:05
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView EnterpriseList(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/accessinfo/list");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [token]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:43
     **/
    @GetMapping("/addAccessinfo.ahsj")
    public ModelAndView addAccessinfo(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/accessinfo/add");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @GetMapping("/updateAccessinfo.ahsj")
    public ModelAndView addEnterprise(String token, @RequestParam("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/accessinfo/update");
        AccessInfo accessInfo = accessInfoService.selectById(id);
        AccessInfoVO accessInfoVO = new AccessInfoVO();
        BeanUtils.copyProperties(accessInfo, accessInfoVO);
        modelAndView.addObject("token", token);
        modelAndView.addObject("accessInfoVO", accessInfoVO);
        return modelAndView;
    }
    @GetMapping("/audit.ahsj")
    public ModelAndView audit(String token, @RequestParam("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/accessinfo/audit");
        AccessInfo accessInfo = accessInfoService.selectById(id);
        AccessInfoVO accessInfoVO = new AccessInfoVO();
        BeanUtils.copyProperties(accessInfo, accessInfoVO);
        modelAndView.addObject("token", token);
        modelAndView.addObject("accessInfoVO", accessInfoVO);
        return modelAndView;
    }

    /**
     * @return org.springframework.http.ResponseEntity<core.message.Message>
     * @功能说明
     * @Params [accessInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:04
     **/
    @PostMapping("/add/accessinfo.ahsj")
    public ResponseEntity<Message> addAccessinfo(AccessInfoDTO accessInfoDTO) throws Exception {
        Message message = accessInfoService.addAccessinfo(accessInfoDTO);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }
    @PostMapping("/update/accessinfo.ahsj")
    public ResponseEntity<Message> updateAccessinfo(AccessInfoDTO accessInfoDTO) throws Exception {
        Message message = accessInfoService.updateAccessinfo(accessInfoDTO);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }

    /**
     * @return org.springframework.http.ResponseEntity<java.util.List < com.ahsj.smartparkcore.entity.po.EnterpriseInfo>>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/15
     * @Time 16:41
     **/
    @PostMapping("/enterpriseInfoAll/index.ahsj")
    public ResponseEntity<List<EnterpriseInfo>> enterpriseInfoAll() throws Exception {
        List<EnterpriseInfo> enterpriseInfos = enterpriseInfoService.enterpriseInfoAll();
        return new ResponseEntity<>((enterpriseInfos), HttpStatus.OK);

    }

    /**
     * @return org.springframework.http.ResponseEntity<java.lang.String>
     * @功能说明
     * @Params [token]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 16:41
     **/
    @GetMapping("/enterpriseInfoAllSelect/index.ahsj")
    public ResponseEntity<String> enterpriseInfoAllSelect(String token) throws Exception {
        List<EnterpriseInfo> enterpriseInfos = enterpriseInfoService.enterpriseInfoAll();
        String toJSONString = JSON.toJSONString(enterpriseInfos);
        return new ResponseEntity<>((toJSONString), HttpStatus.OK);
    }

    /**
     * @return org.springframework.http.ResponseEntity<core.entity.PageBean < com.ahsj.smartparkcore.entity.po.AccessInfo>>
     * @功能说明
     * @Params [accessInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:04
     **/
    @PostMapping("/weixin/list.ahsj")
    public ResponseEntity<PageBean<AccessInfo>> weixinQueryList(AccessInfoDTO accessInfoDTO) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        AccessInfo map = mapper.map(accessInfoDTO, AccessInfo.class);
        PageBean<AccessInfo> pageBean = new PageBean<>();
        pageBean.setParameter(map);
        return ResponseEntity.ok(accessInfoService.queryList(pageBean));
    }

    /**
     * @return org.springframework.http.ResponseEntity<core.entity.PageBean < com.ahsj.smartparkcore.entity.po.AccessInfo>>
     * @功能说明
     * @Params [accessInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:28
     **/
    @PostMapping("/list.ahsj")
    public ResponseEntity<PageBean<AccessInfo>> queryList(AccessInfoDTO accessInfoDTO) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        AccessInfo map = mapper.map(accessInfoDTO, AccessInfo.class);
        PageBean<AccessInfo> pageBean = new PageBean<>();
        pageBean.setParameter(map);
        return ResponseEntity.ok(accessInfoService.queryList(pageBean));
    }

    /**
     *@功能说明
     *@Params [id]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/10/22
     *@Time 18:20
    **/
    @PostMapping("/reviewSuccess.ahsj")
    public Message reviewSuccess(@RequestParam("id") Long id) throws Exception {
        return accessInfoService.reviewSuccess(id);
    }

    /**
     *@功能说明
     *@Params [id]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/10/22
     *@Time 18:20
    **/
    @PostMapping("/reviewFail.ahsj")
    public Message reviewFail(@RequestParam("id") Long id) throws Exception {
        return accessInfoService.reviewFail(id);
    }



  /*  @PostMapping("/update/enterpriseInfo.ahsj")
    public ResponseEntity<Message> updateEnterpriseInfo(EnterpriseInfoDTO enterpriseInfoDTO, @RequestParam(value = "file", required = false) MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        Message message = enterpriseInfoService.updateEnterpriseInfo(enterpriseInfoDTO, file, relateKet, relatePage);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }*/
}
