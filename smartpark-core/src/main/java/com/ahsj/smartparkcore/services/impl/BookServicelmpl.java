package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.BookMapper;
import com.ahsj.smartparkcore.entity.dto.BookDTO;
import com.ahsj.smartparkcore.entity.po.Book;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.services.BookService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServicelmpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    /**
     *@Description 预定   会议室/场地/工位   预定表的新增
     *@Params [bookDTO]
     *@return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:56
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(BookDTO bookDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        Book book =mapper.map(bookDTO,Book.class);

        //预留 新增订单 对接付钱接口


        //订单增加后  设置状态为已付未取消
        book.setIsPay(1);
        book.setIsCancel(2);
        //预留设置支付金额
//        book.setPaymentAmount(支付接口支付金额);
        int flag = bookMapper.insert(book);
        if(flag != 0){
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT), HttpStatus.OK);
        }
    }
}
