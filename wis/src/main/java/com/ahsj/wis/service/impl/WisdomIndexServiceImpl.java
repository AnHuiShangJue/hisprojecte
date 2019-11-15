package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.WisdomIndexMapper;
import com.ahsj.wis.entity.WisdomIndex;
import com.ahsj.wis.service.WisdomIndexService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/11/4
 * @Time 16:09
 **/
@Service
public class WisdomIndexServiceImpl implements WisdomIndexService {

    @Autowired
    WisdomIndexMapper wisdomIndexMapper;

    /**
     * className WisdomIndexServiceImpl
     *
     * @author dingli
     * @date 2019/11/4 16:09
     */
    @Override
    @Transactional(readOnly = true)
    public List<WisdomIndex> selectAll() throws Exception {
        return wisdomIndexMapper.selectAll();
    }

    /**
     * @Description 根据name修改value
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/11/4
     * @Time 16:44
     **/
    @Override
    @Transactional(readOnly = false)
    public int updateByName(WisdomIndex record) throws Exception {
        return wisdomIndexMapper.updateByName(record);
    }

    /**
     * @Description 修改智慧湾首页
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/11/6
     * @Time 10:22
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(WisdomIndex record) {
        record.setUpdateDate(new Date());
        WisdomIndex wisdomIndex = wisdomIndexMapper.selectByPrimaryKey(record.getId());
        wisdomIndex.setId(null);
        wisdomIndex.setCreateDate(new Date());
        wisdomIndex.setUpdateDate(new Date());
        wisdomIndexMapper.insert(wisdomIndex);  //保留之前的
        int i = wisdomIndexMapper.updateByPrimaryKeySelective(record); //修改现在的
        if (i > 0) {
            return MessageUtil.createMessage(true, "修改成功");
        }
        return MessageUtil.createMessage(false, "修改失败");
    }
}
