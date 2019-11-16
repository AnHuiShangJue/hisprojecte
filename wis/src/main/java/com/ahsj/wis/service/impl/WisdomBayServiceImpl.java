package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.WisdomBayMapper;
import com.ahsj.wis.entity.WisdomBay;
import com.ahsj.wis.service.WisdomBayService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class WisdomBayServiceImpl implements WisdomBayService {
    @Autowired
    WisdomBayMapper wisdomBayMapper;



    @Override
    @Transactional(readOnly = true)
    public List<WisdomBay> selectAll() throws Exception {
        return wisdomBayMapper.selectAll();
    }



    @Override
    @Transactional(readOnly = false)
    public Message updateWisdomBay(WisdomBay record){
        record.setUpdateDate(new Date());
        WisdomBay wisdomBay = wisdomBayMapper.selectByPrimaryKey(record.getId());
        wisdomBay.setId(null);
        wisdomBay.setCreateDate(new Date());
        wisdomBay.setUpdateDate(new Date());
        wisdomBayMapper.insert(wisdomBay);
        int i = wisdomBayMapper.updateByPrimaryKeySelective(record);
        if (i>0){
            return MessageUtil.createMessage(true,"修改成功！");
        }else {
            return MessageUtil.createMessage(false,"修改失败！");
        }
    }
}
