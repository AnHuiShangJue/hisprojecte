package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.BookDTO;
import com.ahsj.smartparkcore.entity.po.Book;
import core.entity.PageBean;
import org.springframework.http.ResponseEntity;

public interface BookService {
    /**
     * @return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Description 预定   会议室/场地/工位   预定表的新增
     * @Params [bookDTO]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 9:55
     **/
    ResponseEntity<ResultModel> save(BookDTO bookDTO) throws Exception;

    /**
     * @Description 预约分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/14
     * @Time 10:59
     **/
    PageBean<BookDTO> list(PageBean<BookDTO> pageBean) throws Exception;

    /**
     * @Description 预约删除
     * @Params: [bookDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/14
     * @Time 11:10
     **/
    ResponseEntity<ResultModel> delete(BookDTO bookDTO) throws Exception;

    /**
     * @Description 预约修改
     * @Params: [bookDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/10/14
     * @Time 11:17
     **/
    ResponseEntity<ResultModel> update(BookDTO bookDTO) throws Exception;

    /**
     * @Description 查询预约
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.smartparkcore.entity.po.Book
     * @Date 2019/10/14
     * @Time 14:40
     **/
    Book selectByPrimaryKey(Long id) throws Exception;

}
