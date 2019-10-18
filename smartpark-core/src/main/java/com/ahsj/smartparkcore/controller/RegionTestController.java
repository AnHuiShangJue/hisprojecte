package com.ahsj.smartparkcore.controller;

import com.ahsj.smartparkcore.common.utils.LocalUtil;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.entity.po.Region;
import com.ahsj.smartparkcore.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: Test
 * <p>
 * Date:     2019/10/16 15:06
 *
 * @author XJP
 * @create 2019/10/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/test")
public class RegionTestController {

    @Autowired
    RegionService regionService;

   @PostMapping("/h.ahsj")
    public void update() throws Exception {
       for (int i = 3; i <= 11; i++) {
           if (i==9){
               continue;
           }
           Region region = new Region();
           Integer a = i ;
           region.setParentId(a.longValue());
           List<Region> regionList = regionService.queryRegion(region);
           for (Region region1 : regionList) {
               region1.setName(region1.getName()+"市");
               regionService.updateByPrimaryKeySelective(region1);
           }
       }
    }

    @PostMapping("/hio.ahsj")
    public List<String> hi() throws Exception {
        LocalUtil lu = LocalUtil.getInstance();
        List<String> provinces = lu.getProvinces("中国");
        List<Region> regionList = new ArrayList<>();
        for (String province : provinces) {
            Region region = new Region();
            region.setName(province);
            regionList.add(region);
        }
        regionService.addRegionList(regionList);
        return provinces;
    }


    @PostMapping("/hi.ahsj")
    public List<String> ah(String name) throws Exception {
        LocalUtil lu = LocalUtil.getInstance();
        List<String> provinces = lu.getCities("中国",name);
        Region region1 = regionService.queryRegionName(name);
        List<Region> regionList = new ArrayList<>();
        for (String province : provinces) {
            Region region = new Region();
            region.setName(province);
            region.setParentId(region1.getId());
            regionList.add(region);
        }
        regionService.addRegionList(regionList);
        return provinces;
    }
    @PostMapping("/hiahwuhu.ahsj")
    public List<String> ahwuhu(String name ,Long id) throws Exception {
        LocalUtil lu = LocalUtil.getInstance();
        List<String> provinces = lu.getcounty("中国","安徽",name);
        List<Region> regionList = new ArrayList<>();
       for (String province : provinces) {
            Region region = new Region();
            region.setName(province);
            region.setParentId(id);
            regionList.add(region);
        }
      /*  for (int i = 0; i < 3; i++) {
            Region region = new Region();
            if (i==0){
                region.setName("13层06号");
            }else if (i==1) {
                region.setName("12层06号");
            }else {
                region.setName("11层06号");
            }
            region.setParentId(63L);
            regionList.add(region);
        }*/
        regionService.addRegionList(regionList);
        return provinces;
    }
}
