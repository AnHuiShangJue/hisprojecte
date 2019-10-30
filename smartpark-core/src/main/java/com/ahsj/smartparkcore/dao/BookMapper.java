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
    List<Book> ConferenceRoomInfovisitByOpenId(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [bookDTO]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 16:00
     **/
    List<Book> stationVisitByOpenId(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [bookDTO]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 16:00
     **/
    List<Book> siteVisitByOpenId(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 10:16
     **/
    List<Book> ConferenceRoomInfovisitByOpenIdAndOrderAndNoPay(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 10:18
     **/
    List<Book> ConferenceRoomInfovisitByOpenIdAndOrderCancel(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 10:51
     **/
    List<Book> ConferenceRoomInfovisitByOpenIdAndOrderAndPay(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 10:58
     **/
    List<Book> SitevisitByOpenIdAndOrderCancel(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 10:58
     **/
    List<Book> SitesitevisitByOpenIdAndOrderAndPay(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 11:13
     **/
    List<Book> SitevisitByOpenIdAndOrderAndNoPay(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 11:16
     **/
    List<Book> sitevisitByOpenIdAndOrderAndPay(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 11:17
     **/
    List<Book> stationvisitByOpenIdAndOrderCancel(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [book]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 11:17
     **/
    List<Book> stationvisitByOpenIdAndOrderAndNoPay(Book book);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Book>
     * @功能说明
     * @Params [bookDTO]
     * @Author XJP
     * @Date 2019/10/30
     * @Time 11:23
     **/
    List<Book> visitByOpenIdAndOrder(BookDTO bookDTO);
}