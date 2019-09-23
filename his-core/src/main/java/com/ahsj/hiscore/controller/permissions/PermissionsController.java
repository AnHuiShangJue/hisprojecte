package com.ahsj.hiscore.controller.permissions;

import com.ahsj.hiscore.entity.HisRescue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: PermissionsController
 * <p>
 * Date:     2019/9/16 16:45
 *
 * @author XJP
 * @create 2019/9/16
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/permissions")
public class PermissionsController {

    @GetMapping("/index.ahsj")
    public ModelAndView permissions(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/common/page_401");
        return modelAndView;
    }
}