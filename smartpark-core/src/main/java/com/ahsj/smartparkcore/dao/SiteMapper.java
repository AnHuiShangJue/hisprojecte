package com.ahsj.smartparkcore.dao;


import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.entity.po.Site;
import com.ahsj.smartparkcore.entity.vo.SiteVo;
import core.entity.PageBean;

import java.util.List;

public interface SiteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);

    List<SiteDTO> selectSite(PageBean<SiteDTO> pageBean) throws Exception;

    Site selectBySiteName(String siteName)throws Exception;

    int setEnable(Site record)throws Exception;

    List<Site> selectAll()throws Exception;
}