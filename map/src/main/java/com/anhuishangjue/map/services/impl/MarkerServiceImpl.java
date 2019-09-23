package com.anhuishangjue.map.services.impl;

import com.anhuishangjue.map.dao.MarkerMapper;
import com.anhuishangjue.map.entity.Marker;
import com.anhuishangjue.map.services.MarkerService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkerServiceImpl implements MarkerService {


    @Autowired
    MarkerMapper markerMapper;

    @Override
    public Message saveOrUpdate(Marker marker) {
        //标记的id为空--->添加新标记
        if (EmptyUtil.Companion.isNullOrEmpty(marker.getId())) {
            //根据ID获取到标记对象
            Marker check = markerMapper.selectByPrimaryKey(marker.getId());
            //标记的对象为空时添加
            if (EmptyUtil.Companion.isNullOrEmpty(check)) {
                markerMapper.insertSelective(marker);
                return MessageUtil.createMessage(true, "添加标记成功!");
            } else {
                return MessageUtil.createMessage(true, "添加标记失败!");

            }
        }else{
                //标记的ID不为空则进行更新
                markerMapper.updateByPrimaryKeySelective(marker);
                return MessageUtil.createMessage(true, "已更新该标记!");
            }

    }



    @Override
    public Message delete(int[] ids) {
       for (int i =0;i<ids.length;i++){
           markerMapper.deleteByPrimaryKey((long) ids[i]);
       }
       return MessageUtil.createMessage(true,"删除标记成功!");
    }

    @Override
    public List<Marker> list(Long id) {
        Marker marker = markerMapper.selectByPrimaryKey(id);
        List<Marker> list = new ArrayList();
        list.add(marker);
        System.out.println(list);
        return list;
    }

}
