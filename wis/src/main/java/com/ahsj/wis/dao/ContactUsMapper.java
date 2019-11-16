package com.ahsj.wis.dao;

import com.ahsj.wis.entity.ContactUs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContactUsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContactUs record);

    int insertSelective(ContactUs record);

    ContactUs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContactUs record);

    int updateByPrimaryKey(ContactUs record);



    /**
     *@Description 查询最新的一条记录
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/16
     *@Time 17:17
    */
    List<ContactUs> select();
}
