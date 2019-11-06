package com.ahsj.wisdom.service.impl;

import com.ahsj.wisdom.dao.WisdomIndexMapper;
import com.ahsj.wisdom.entity.WisdomIndex;
import com.ahsj.wisdom.service.WisdomIndexService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.MessageUtils;

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
        int i = wisdomIndexMapper.updateByPrimaryKeySelective(record);
        if(i>0){
            return MessageUtil.createMessage(true,"修改成功");
        }
        return MessageUtil.createMessage(false,"修改失败");
    }
}
