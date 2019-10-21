package com.ahsj.smartparkcore.controller.StationInfo;

import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.dto.StationInfoDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.StationInfo;
import com.ahsj.smartparkcore.entity.vo.StationInfoVO;
import com.ahsj.smartparkcore.services.StationInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<PageBean<StationInfo>> queryList(StationInfoDTO stationInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        StationInfo map = mapper.map(stationInfoDTO, StationInfo.class);
        PageBean<StationInfo> pageBean = new PageBean<>();
        pageBean.setParameter(map);
        return ResponseEntity.ok(stationInfoService.queryList(pageBean));
    }


    @GetMapping("/addStationinfo.ahsj")
    public ModelAndView addStationinfo(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/stationinfo/add");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @GetMapping("/updateStationinfo.ahsj")
    public ModelAndView addStationinfo(String token, @RequestParam("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/stationinfo/update");
        StationInfoVO stationInfoVO = stationInfoService.selectById(id);
        modelAndView.addObject("token", token);
        modelAndView.addObject("stationInfoVO", stationInfoVO);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 15:52
     **/
    @PostMapping("/add/stationinfo.ahsj")
    public ResponseEntity<Message> addStationinfo(StationInfoDTO infoDTO, @RequestParam(value = "file", required = false) MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        Message message = stationInfoService.addStationinfo(infoDTO, file, relateKet, relatePage);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 15:52
     **/
    @PostMapping("/update/stationinfo.ahsj")
    public ResponseEntity<Message> updateStationinfo(StationInfoDTO stationInfoDTO, @RequestParam(value = "file", required = false) MultipartFile[] file, String relateKet, String relatePage) throws Exception {
        Message message = stationInfoService.updateStationinfo(stationInfoDTO, file, relateKet, relatePage);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/10/21
     * @Time 15:18
     **/
    @ResponseBody
    @PostMapping("/setEnable.ahsj")
    public ResponseEntity<Message> updateSetDisable(@RequestParam("ids[]") Long[] ids) throws Exception {
        return new ResponseEntity<>((stationInfoService.updateSetDisable(ids)), HttpStatus.OK);
    }
}
