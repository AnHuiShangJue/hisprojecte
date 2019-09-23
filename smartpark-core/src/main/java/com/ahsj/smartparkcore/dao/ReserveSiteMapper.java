package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.dto.ReserveSiteDTO;
import com.ahsj.smartparkcore.entity.po.ReserveSite;
import core.entity.PageBean;

import java.util.List;

public interface ReserveSiteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReserveSite record);

    int insertSelective(ReserveSite record);

    ReserveSite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReserveSite record);

    int updateByPrimaryKey(ReserveSite record);

   List<ReserveSite>  selectTime(ReserveSiteDTO reserveSiteDTO);

    List<ReserveSiteDTO> list(PageBean<ReserveSiteDTO> pageBean);
}