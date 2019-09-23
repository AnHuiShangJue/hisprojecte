package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.ReserveSiteDTO;
import core.entity.PageBean;
import org.springframework.http.ResponseEntity;


/**
 * @Description
 * @Author dingli
 * @Date 2019/9/11
 * @Time 17:08
 **/
public interface ReserveSiteService {
    /**
     * className ReserveSiteService
     *
     * @author dingli
     * @date 2019/9/11 17:08
     */

    /**
     * @Description 新增场地预约
     * @Params: [reserveSite]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/11
     * @Time 17:11
     **/
    ResponseEntity<ResultModel> save(ReserveSiteDTO reserveSiteDTO) throws Exception;

    /**
     * @Description 修改场地预约
     * @Params: [reserveSite]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/11
     * @Time 17:12
     **/
    ResponseEntity<ResultModel> update(ReserveSiteDTO reserveSiteDTO) throws Exception;

    /**
     * @Description 删除预定
     * @Params: [reserveSiteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 10:13
     **/
    ResponseEntity<ResultModel> delete(ReserveSiteDTO reserveSiteDTO) throws Exception;

    /**
     * @Description 分页查询预定信息
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 10:19
     **/
    ResponseEntity<ResultModel> list(PageBean<ReserveSiteDTO> pageBean) throws Exception;

}
