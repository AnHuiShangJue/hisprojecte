package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.ContactUsMapper;
import com.ahsj.wis.entity.ContactUs;
import com.ahsj.wis.service.ContactUsUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactUsUpdateServiceImpl implements ContactUsUpdateService {

    @Autowired
    ContactUsMapper contactUsMapper;


    @Override
    @Transactional(readOnly = false)
    public int insert(ContactUs record) throws Exception {
        return contactUsMapper.insert(record);
    }

    @Override
    @Transactional(readOnly = false)
    public ContactUs select() throws Exception {
        return contactUsMapper.select().get(0);
    }
}
