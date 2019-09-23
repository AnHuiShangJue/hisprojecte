package com.ahsj.smartparkcore.controller.site;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.services.SiteServices;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import utils.EmptyUtil;

@RestController
@RequestMapping("/api/Site")
public class SiteController extends BaseController {

    @Autowired
    SiteServices siteServices;

    /**
     * @Description 条件查询场地信息
     * @Params: [site, pageBean]
     * @Author: dinglishangjuelogo.png
     * @Return: core.entity.PageBean<com.ahsj.smartparkcore.entity.Site>
     * @Date 2019/9/5
     * @Time 10:23
     **/
    @RequestMapping("/list.index.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> list(SiteDTO siteDTO, PageBean<SiteDTO> pageBean) throws Exception {
        pageBean.setParameter(siteDTO);
        return siteServices.selectSite(pageBean);
    }

    /**
     * @Description 新增场地
     * @Params: [site]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/5
     * @Time 11:26
     **/
    @RequestMapping("/save.index.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> save(SiteDTO siteDTO) throws Exception {
        return siteServices.save(siteDTO);
    }

    /**
     * @Description
     * @Params: [site]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/5
     * @Time 11:26
     **/
    @RequestMapping("/update.index.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> update(SiteDTO siteDTO) throws Exception {
        return siteServices.update(siteDTO);
    }

    /**
     * @Description 设置场地启用状态
     * @Params: [ids]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/5
     * @Time 11:29
     **/
    @ResponseBody
    @RequestMapping(value = "/setEnable.ahsj")
    public ResponseEntity<ResultModel> setEnable(@RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
        return siteServices.setEnable(ids);
    }

    /**
     * @Description 删除场地
     * @Params: [ids]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/5
     * @Time 17:11
     **/
    @ResponseBody
    @RequestMapping(value = "/delete.ahsj")
    public ResponseEntity<ResultModel> deleteSite(@RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_PARAM), HttpStatus.OK);
        }
        return siteServices.delete(ids);
    }

    /**
     * @Description 场地验证
     * @Params: [siteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/6
     * @Time 11:06
     **/
    public ResponseEntity<ResultModel> verification(SiteDTO siteDTO) throws Exception {
        return null;
    }
}











