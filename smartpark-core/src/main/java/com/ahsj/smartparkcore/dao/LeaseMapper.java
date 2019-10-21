package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.Lease;
import com.ahsj.smartparkcore.entity.vo.LeaseVO;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaseMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Lease lease);

    int insertSelective(Lease lease);

    Lease selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Lease lease);

    int updateByPrimaryKey(Lease lease);

    List<LeaseVO> queryList(PageBean<LeaseVO> pageBean);

    List<LeaseVO> List();

    List<LeaseVO> isLeaseList();

    List<LeaseVO> noLeaseList();
}