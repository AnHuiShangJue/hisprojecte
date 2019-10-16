package com.ahsj.smartparkcore.controller.region;

import com.ahsj.smartparkcore.services.RegionService;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RegionController
 * <p>
 * Date:     2019/10/16 18:06
 *
 * @author XJP
 * @create 2019/10/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/region")
public class RegionController extends BaseController {

    @Autowired
    RegionService regionService;
}
