package com.ahsj.smartparkcore.controller.book;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.entity.dto.BookDTO;
import com.ahsj.smartparkcore.services.BookService;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.EmptyUtil;

@Controller
@RequestMapping("/api/book/")
public class BookController extends BaseController {
    @Autowired
    BookService bookService;

    /**
     *@Description 预定   会议室/场地/工位   预定表的新增
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:46
    **/
    public ResponseEntity<ResultModel> save(BookDTO bookDTO)throws Exception{
        //判断必要信息不为空
        if(EmptyUtil.Companion.isNullOrEmpty(bookDTO.getStartTime())|| EmptyUtil.Companion.isNullOrEmpty(bookDTO.getEndTime()) ||
                EmptyUtil.Companion.isNullOrEmpty(bookDTO.getSubscriberName())||EmptyUtil.Companion.isNullOrEmpty(bookDTO.getPhoneNumber())){
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);
        }
        return bookService.save(bookDTO);
    }
}
