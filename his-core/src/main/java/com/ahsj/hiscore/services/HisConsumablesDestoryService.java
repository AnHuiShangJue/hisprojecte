package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsumablesDestory;
import core.entity.PageBean;
import core.message.Message;

public interface HisConsumablesDestoryService {

    Message index(Long[] ids, Integer[] numbers, String[] reasons, String[] destoryTypes);
    PageBean<HisConsumablesDestory> list(PageBean<HisConsumablesDestory> pageBean)throws Exception;

    Message review(Long[] ids);
    Message unreview(Long[] ids);

    HisConsumablesDestory selectByPrimaryId(Long id);

}
