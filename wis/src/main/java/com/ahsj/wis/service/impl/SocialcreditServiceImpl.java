package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.SocialcreditMapper;
import com.ahsj.wis.entity.Socialcredit;
import com.ahsj.wis.service.SocialcreditService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SocialcreditServiceImpl implements SocialcreditService {

    @Autowired
    SocialcreditMapper socialcreditMapper;



    @Override
    @Transactional(readOnly = true)
    public List<Socialcredit> selectAll() throws Exception {
        return socialcreditMapper.selectAll();
    }



    @Override
    @Transactional(readOnly = false)
    public Message updateSocialcredit(Socialcredit record){
        record.setUpdateDate(new Date());
        Socialcredit socialcredit = socialcreditMapper.selectByPrimaryKey(record.getId());
        socialcredit.setId(null);
        socialcredit.setCreateDate(new Date());
        socialcredit.setUpdateDate(new Date());
        socialcreditMapper.insert(socialcredit);
        int i = socialcreditMapper.updateByPrimaryKeySelective(record);
        if (i>0){
            return MessageUtil.createMessage(true,"修改成功！");
        }else {
            return MessageUtil.createMessage(false,"修改失败！");
        }
    }
}
