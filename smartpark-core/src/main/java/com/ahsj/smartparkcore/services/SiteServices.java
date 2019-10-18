package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.entity.po.Site;
import com.ahsj.smartparkcore.entity.vo.SiteVo;
import core.entity.PageBean;
import org.springframework.http.ResponseEntity;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/5
 * @Time 9:19
 **/

public interface SiteServices {

    /**
     * @Description 条件查询场地信息
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.smartparkcore.entity.Site>
     * @Date 2019/9/5
     * @Time 9:26
     **/
    PageBean<SiteDTO> selectSite(PageBean<SiteDTO> pageBean) throws Exception;

    /**
     * @Description 新增场地
     * @Params: [site]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/5
     * @Time 16:18
     **/
    ResponseEntity<ResultModel> save(SiteDTO siteDTO) throws Exception;

    /**
     * @Description 设置启用状态
     * @Params: [ids]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/5
     * @Time 13:45
     **/
    ResponseEntity<ResultModel> setEnable(Long[] ids) throws Exception;

    /**
     * @Description 修改场地
     * @Params: [siteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/5
     * @Time 16:30
     **/
    ResponseEntity<ResultModel> update(SiteDTO siteDTO) throws Exception;

    /**
     * @Description 删除场地
     * @Params: [ids]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/5
     * @Time 17:12
     **/
    ResponseEntity<ResultModel> delete(Long[] ids) throws Exception;

    /**
     * @Description 根据id查询场地
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.smartparkcore.entity.dto.SiteDTO
     * @Date 2019/10/12
     * @Time 10:37
     **/
    SiteVo selectByPrimaryKey(Long id) throws Exception;

}
