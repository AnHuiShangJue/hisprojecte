package com.anhuishangjue.map.services;

import com.anhuishangjue.map.entity.Marker;
import core.message.Message;

import java.util.List;

public interface MarkerService {

    Message saveOrUpdate(Marker marker);

    Message delete(int[] ids);

    List<Marker> list(Long id);


}
