package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.BookDTO;
import org.springframework.http.ResponseEntity;

public interface BookService {
    /**
     *@Description 预定   会议室/场地/工位   预定表的新增
     *@Params [bookDTO]
     *@return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:55
    **/
    ResponseEntity<ResultModel> save(BookDTO bookDTO)throws Exception;
}
