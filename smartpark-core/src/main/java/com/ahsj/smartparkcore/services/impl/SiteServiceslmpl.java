package com.ahsj.smartparkcore.services.impl;


import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.SiteMapper;
import com.ahsj.smartparkcore.entity.dto.SiteDTO;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.entity.po.Region;
import com.ahsj.smartparkcore.entity.po.Site;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO;
import com.ahsj.smartparkcore.entity.vo.SiteVo;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.ahsj.smartparkcore.services.RegionService;
import com.ahsj.smartparkcore.services.SiteServices;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.entity.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class SiteServiceslmpl implements SiteServices {

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    RegionService regionService;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    @Autowired
    EnterpriseInfoService enterpriseInfoService;

    private static final String filePath = Constants.FILE_PATHS;

    private static final String SUB_FILEPATH = Constants.FILE_PATHS_LOCAL;

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
        Region region1 = regionService.selectById(siteDTO.getProvinceId());
        Region region2 = regionService.selectById(siteDTO.getCityId());
        Region region3 = regionService.selectById(siteDTO.getAreaId());
        site.setLocation(region1.getName() + region2.getName() + region3.getName() + site.getLocation());
        site.setBookType(2);
        site.setIsEnable((short) 1);
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
        Region region1 = regionService.selectById(siteDTO.getProvinceId());
        Region region2 = regionService.selectById(siteDTO.getCityId());
        Region region3 = regionService.selectById(siteDTO.getAreaId());
        site.setLocation(region1.getName() + region2.getName() + region3.getName() + site.getLocation());

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
    public SiteVo selectByPrimaryKey(Long id) throws Exception {
        Site site = siteMapper.selectByPrimaryKey(id);
        SiteVo siteVo = new SiteVo();
        BeanUtils.copyProperties(site, siteVo);
        String substring = StringUtils.substring(site.getLocation(), 0, 3);
        String substring1 = StringUtils.substring(site.getLocation(), 3, 6);
        String substring2 = StringUtils.substring(site.getLocation(), 6, 9);
        String addressName = StringUtils.substring(site.getLocation(), 9, site.getLocation().length());
        Region region = regionService.queryRegionName(substring);
        Region region1 = regionService.queryRegionName(substring1);
        Region region2 = regionService.queryRegionName(substring2);
        siteVo.setProvinceId(region.getId());
        siteVo.setCityId(region1.getId());
        siteVo.setAreaId(region2.getId());
        siteVo.setLocation(addressName);
        return siteVo;
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.Site>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 15:28
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Site> selectByIds(List<Long> ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            return new ArrayList<>();
        } else {
            List<Site> sites = siteMapper.selectByIds(ids);
            if (EmptyUtil.Companion.isNullOrEmpty(sites)) {
                return new ArrayList<>();
            } else {
                return sites;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.SiteVo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/29
     * @Time 16:18
     **/
    @Override
    @Transactional(readOnly = true)
    public List<SiteVo> listForView() throws Exception {
        List<SiteVo> siteVos = siteMapper.listForView();
        for (SiteVo siteVo : siteVos) {
            SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            sysAttachmentInfo.setRelateId(siteVo.getId());
            sysAttachmentInfo.setRelateKey(Constants.SITE);
            sysAttachmentInfo.setRelatePage(Constants.LIST);
            List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfos)) {
                continue;
            }
            SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
            String replace = Constants.LOCALHOST + sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
            siteVo.setFilePath(replace);
        }
        return siteVos;
    }
}
