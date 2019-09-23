package com.ahsj.baseuserinfo.dao;

import com.ahsj.baseuserinfo.entity.Operate;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Operate record);

    int insertSelective(Operate record);

    Operate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Operate record);

    int updateByPrimaryKey(Operate record);


    public List<Operate> list(PageBean<Operate> pageEntity);


    public Operate selectByCode(String code);


    public Operate selectByName(String name);


    public void update(Operate sysOperate);


    public List<Operate> listMenuOperate(Integer menuId);


}