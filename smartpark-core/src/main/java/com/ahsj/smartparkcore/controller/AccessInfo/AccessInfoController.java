package com.ahsj.smartparkcore.controller.AccessInfo;

import com.ahsj.smartparkcore.services.AccessInfoService;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/addEnterprise.ahsj")
    public ModelAndView addEnterprise(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/enterprise/add");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

}
