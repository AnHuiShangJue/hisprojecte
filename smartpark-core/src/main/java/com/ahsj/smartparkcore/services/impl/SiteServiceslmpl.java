package com.ahsj.smartparkcore.services.impl;


import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.SiteMapper;
import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.entity.po.Site;
import com.ahsj.smartparkcore.services.SiteServices;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.logging.Logger;

@Service
public class SiteServiceslmpl implements SiteServices {

    @Autowired
    SiteMapper siteMapper;

    private Logger logger = Logger.getLogger(SiteServiceslmpl.class.getSimpleName());

    /**
     * @Description 分页条件查询场地信息
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.smartparkcore.entity.Site>
     * @Date 2019/9/5
     * @Time 9:29
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<SiteDTO> selectSite(PageBean<SiteDTO> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(siteMapper.selectSite(pageBean)));
        return pageBean;
    }

    /**
     * @Description 新增场地信息
     * @Params: [siteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/5
     * @Time 16:29
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(SiteDTO siteDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
        Site site = mapper.map(siteDTO, Site.class);
        if (EmptyUtil.Companion.isNullOrEmpty(siteMapper.selectBySiteName(site.getSiteName()))) {
            siteMapper.insert(site);
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT, site.getSiteName() + "已存在"), HttpStatus.OK);
        }
    }

    /**
     * @Description 设置是否启用
     * @Params: [ids]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/5
     * @Time 13:46
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> setEnable(Long[] ids) throws Exception {
        for (Long id : ids) {
            Site site = siteMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(site)) {
                return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL, "参数异常"), HttpStatus.OK);
            } else {
                if (site.getIsEnable() == 1) {
                    site.setIsEnable((short) 2);
                } else {
                    site.setIsEnable((short) 1);
                }
                siteMapper.updateByPrimaryKey(site);
            }
        }
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_SET), HttpStatus.OK);
    }

    /**
     * @Description 修改场地信息
     * @Params: [siteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/5
     * @Time 16:31
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> update(SiteDTO siteDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
        Site site = mapper.map(siteDTO, Site.class);
        Site sites = siteMapper.selectBySiteName(site.getSiteName());
        if (!EmptyUtil.Companion.isNullOrEmpty(sites) && sites.getId() != site.getId()) {//数据库存在这个名字，并且不是我要修改的对象
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE, site.getSiteName() + "已存在"), HttpStatus.OK);
        }
        siteMapper.updateByPrimaryKeySelective(site);
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE), HttpStatus.OK);
    }

    /**
     * @Description 删除场地
     * @Params: [ids]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/5
     * @Time 17:37
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            Site site = siteMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(site)) {
                return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_PARAM), HttpStatus.OK);
            } else {
                siteMapper.deleteByPrimaryKey(id);
            }
        }
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_DELETE), HttpStatus.OK);
    }

    /**
     * @Description 根据id查询场地
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.smartparkcore.entity.dto.SiteDTO
     * @Date 2019/10/12
     * @Time 10:38
     **/
    @Override
    @Transactional(readOnly = true)
    public Site selectByPrimaryKey(Long id) throws Exception {
        return siteMapper.selectByPrimaryKey(id);
    }
}
