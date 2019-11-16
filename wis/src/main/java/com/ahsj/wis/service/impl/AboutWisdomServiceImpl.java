package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.AboutWisdomMapper;
import com.ahsj.wis.entity.AboutWisdom;
import com.ahsj.wis.service.AboutWisdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AboutWisdomServiceImpl implements AboutWisdomService {
    @Autowired
    AboutWisdomMapper aboutWisdomMapper;
    @Override
    @Transactional(readOnly = false)
    public int insert(AboutWisdom record) throws Exception {
        return aboutWisdomMapper.insert(record);

    }

    @Override
    @Transactional(readOnly = false)
    public AboutWisdom select() throws Exception {
        return aboutWisdomMapper.select().get(0);
    }
}
