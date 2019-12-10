package com.ahsj.smartparkcore.controller.region;

import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.Region;
import com.ahsj.smartparkcore.services.RegionService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * @return org.springframework.http.ResponseEntity<core.message.Message>
     * @功能说明
     * @Params [region]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 9:25
     **/
    @RequestMapping("/add/accessinfo.ahsj")
    public ResponseEntity<Message> addRegion(Region region) throws Exception {
        Message message = regionService.addRegion(region);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }

    /**
     * @return org.springframework.http.ResponseEntity<core.message.Message>
     * @功能说明
     * @Params [region]
     * @Author XJP
     * @Date 2019/10/17
     * @Time 10:34
     **/
    @RequestMapping("/queryRegion.ahsj")
    public ResponseEntity<List<Region>> queryRegion(Region region) throws Exception {
        List<Region> list = regionService.queryRegion(region);
        return new ResponseEntity<>((list), HttpStatus.OK);
    }

    @RequestMapping("/queryRegions.ahsj")
    public ResponseEntity<String> enterpriseInfoAllSelect(Region region, String token) throws Exception {
        List<Region> list = regionService.queryRegion(region);
        String toJSONString = JSON.toJSONString(list);
        return new ResponseEntity<>((toJSONString), HttpStatus.OK);
    }

    @RequestMapping("/getDataByParentId.ahsj")
    @ResponseBody
    public String getDataByParentId(Long param) throws Exception {
        Region region = new Region();
        region.setParentId(param);
        List<Region> list = regionService.queryRegion(region);
        Map<String, String> map = new HashMap<>();
        list.forEach(item -> {
            map.put(item.getId() + "", item.getName());
        });
        return JSON.toJSONString(map);
    }

}
