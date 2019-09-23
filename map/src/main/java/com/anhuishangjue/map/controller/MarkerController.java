package com.anhuishangjue.map.controller;


import com.anhuishangjue.map.entity.Marker;
import com.anhuishangjue.map.services.MarkerService;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/map/")
public class MarkerController {

    @Autowired
    MarkerService markerService;

       @RequestMapping("/saveOrUpdate")
       Message saveOrUpdate(Marker marker){
           return markerService.saveOrUpdate(marker);
      }

    @RequestMapping("/delete")
    Message delete(int[] ids){
           return markerService.delete(ids);
    }

    @RequestMapping("/selectById")
    List<Marker> list(Long id){
           return markerService.list(id);
    }



}
