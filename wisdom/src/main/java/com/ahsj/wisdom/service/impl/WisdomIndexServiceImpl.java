package com.ahsj.wisdom.service.impl;

import com.ahsj.wisdom.dao.WisdomIndexMapper;
import com.ahsj.wisdom.entity.WisdomIndex;
import com.ahsj.wisdom.service.WisdomIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
