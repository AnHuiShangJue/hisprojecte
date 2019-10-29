package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.dto.BookDTO;
import com.ahsj.smartparkcore.entity.po.Book;
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

    List<Book> listByDate(PageBean<BookDTO> pageBean);

    List<Book> visitByOpenId(BookDTO bookDTO);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [bookDTO]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 16:00
     **/
    List<Book> ConferenceRoomInfovisitByOpenId(BookDTO bookDTO);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [bookDTO]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 16:00
     **/
    List<Book> stationVisitByOpenId(BookDTO bookDTO);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [bookDTO]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 16:00
     **/
    List<Book> siteVisitByOpenId(BookDTO bookDTO);
}