package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.Book;

public interface BookMapper extends BaseMapper<Book>{
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}