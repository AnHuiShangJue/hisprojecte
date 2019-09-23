package com.ahsj.smartparkcore.controller.site;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.ReserveSiteDTO;
import com.ahsj.smartparkcore.services.ReserveSiteService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/12
 * @Time 10:58
 **/
@Controller
@RequestMapping("/reserveSite")
public class ReserveSiteController {
    /**
     * className ReserveSiteController
     *
     * @author dingli
     * @date 2019/9/12 10:58
     */
    @Autowired
    ReserveSiteService reserveSiteService;

    /**
     * @Description 分页查询预定场地
     * @Params: [reserveSiteDTO, pageBean]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 11:03
     **/
    @RequestMapping("/list.index.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> list(ReserveSiteDTO reserveSiteDTO, PageBean<ReserveSiteDTO> pageBean) throws Exception {
        pageBean.setParameter(reserveSiteDTO);
        return reserveSiteService.list(pageBean);
    }

    /**
     * @Description 新增场地预约
     * @Params: [reserveSite]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/11
     * @Time 17:11
     **/
    @RequestMapping("/save.index.ahsj")
    @ResponseBody
    ResponseEntity<ResultModel> save(ReserveSiteDTO reserveSiteDTO) throws Exception{
        return reserveSiteService.save(reserveSiteDTO);
    }

    /**
     * @Description 修改场地预约
     * @Params: [reserveSite]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/11
     * @Time 17:12
     **/
    @RequestMapping("/update.index.ahsj")
    @ResponseBody
    ResponseEntity<ResultModel> update(ReserveSiteDTO reserveSiteDTO) throws Exception{
        return reserveSiteService.update(reserveSiteDTO);
    }

    /**
     * @Description 删除预定
     * @Params: [reserveSiteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 10:13
     **/
    @RequestMapping("/delete.index.ahsj")
    @ResponseBody
    ResponseEntity<ResultModel> delete(ReserveSiteDTO reserveSiteDTO) throws Exception{
        return reserveSiteService.delete(reserveSiteDTO);
    }


}
