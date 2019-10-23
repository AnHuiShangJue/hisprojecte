package com.ahsj.smartparkcore.controller.lease;

import com.ahsj.smartparkcore.entity.dto.LeaseDTO;
import com.ahsj.smartparkcore.entity.po.Lease;
import com.ahsj.smartparkcore.entity.vo.LeaseVO;
import com.ahsj.smartparkcore.services.ConferenceRoomInfoService;
import com.ahsj.smartparkcore.services.LeaseService;
import com.ahsj.smartparkcore.services.SiteServices;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: LeaseController
 * <p>
 * Date:     2019/10/19 15:04
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/lease")
public class LeaseController extends BaseController {

    @Autowired
    LeaseService leaseService;

    @Autowired
    SiteServices siteServices;

    @Autowired
    ConferenceRoomInfoService conferenceRoomInfoService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [token]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:40
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView index(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/lease/list");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.http.ResponseEntity<core.entity.PageBean < com.ahsj.smartparkcore.entity.vo.LeaseVO>>
     * @功能说明
     * @Params [leaseVO]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:39
     **/
    @PostMapping("/list.ahsj")
    public ResponseEntity<PageBean<LeaseDTO>> queryList(LeaseDTO leaseDTO) throws Exception {
        PageBean<LeaseDTO> pageBean = new PageBean<>();
        pageBean.setParameter(leaseDTO);
        return ResponseEntity.ok(leaseService.queryList(pageBean));
    }


    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明 APP
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:29
     **/
    @PostMapping("/allList.ahsj")
    public List<LeaseVO> allList() throws Exception {
        List<LeaseVO> list = leaseService.List();
        return list;
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明 APP
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:29
     **/
    @PostMapping("/isLeaseList.ahsj")
    public List<LeaseVO> isLeaseList() throws Exception {
        List<LeaseVO> list = leaseService.isLeaseList();
        return list;
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.LeaseVO>
     * @功能说明 APP
     * @Params []
     * @Author XJP
     * @Date 2019/10/19
     * @Time 17:29
     **/
    @PostMapping("/noLeaseList.ahsj")
    public List<LeaseVO> noLeaseList() throws Exception {
        List<LeaseVO> list = leaseService.noLeaseList();
        return list;
    }

    /**
     * @Description targetType  targetId 查信息
     * @Params: [lease]
     * @Author: dingli
     * @Return: com.ahsj.smartparkcore.entity.po.Lease
     * @Date 2019/10/21
     * @Time 15:23
     **/
    @RequestMapping("/getLease.ahsj")
    public LeaseDTO selectLease(LeaseVO leaseVO) throws Exception {
        return leaseService.selectLease(leaseVO);
    }

}
