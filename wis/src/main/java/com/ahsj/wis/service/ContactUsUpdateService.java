package com.ahsj.wis.service;


import com.ahsj.wis.entity.ContactUs;

public interface ContactUsUpdateService {

    int insert(ContactUs record)throws Exception;

    ContactUs select()throws Exception;
}
