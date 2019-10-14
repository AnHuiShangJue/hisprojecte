package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.dto.BookDTO;
import com.ahsj.smartparkcore.entity.dto.ReserveSiteDTO;
import com.ahsj.smartparkcore.entity.po.Book;
import com.ahsj.smartparkcore.entity.po.ReserveSite;
import core.entity.PageBean;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectTime(BookDTO bookDTO);

    List<BookDTO> list(PageBean<BookDTO> pageBean);
}