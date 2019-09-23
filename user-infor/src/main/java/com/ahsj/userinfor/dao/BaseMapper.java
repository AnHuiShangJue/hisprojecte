package com.ahsj.userinfor.dao;

public interface BaseMapper<T> {
    int deleteByPrimaryKey(Long var1);

    int insert(T var1);

    int insertSelective(T var1);

    T selectByPrimaryKey(Long var1);

    int updateByPrimaryKeySelective(T var1);

    int updateByPrimaryKey(T var1);
}
