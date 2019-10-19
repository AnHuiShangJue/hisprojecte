package com.ahsj.smartparkcore.controller.StationInfo;

import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.dto.StationInfoDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.services.StationInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: StationInfoController
 * <p>
 * Date:     2019/10/19 17:56
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/stationinfo")
public class StationInfoController extends BaseController {

    @Autowired
    StationInfoService stationInfoService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [token]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 18:04
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView index(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/stationinfo/list");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.http.ResponseEntity<core.entity.PageBean < com.ahsj.smartparkcore.entity.po.StationInfo>>
     * @功能说明
     * @Params [stationInfoDTO]
     * @Author XJP
     * @Date 2019/10/19
     * @Time 18:15
     **/
    @PostMapping("/list.ahsj")
    public ResponseEntity<PageBean<StationInfo>> queryList(StationInfoDTO stationInfoDTO) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        StationInfo map = mapper.map(stationInfoDTO, StationInfo.class);
        PageBean<StationInfo> pageBean = new PageBean<>();
        pageBean.setParameter(map);
        return ResponseEntity.ok(stationInfoService.queryList(pageBean));
    }
}
