package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ConferenceRoomInfoMapper;
import com.ahsj.smartparkcore.entity.dto.ConferenceRoomInfoDTO;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.entity.po.Region;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO;
import com.ahsj.smartparkcore.entity.vo.SiteVo;
import com.ahsj.smartparkcore.services.ConferenceRoomInfoService;
import com.ahsj.smartparkcore.services.EnterpriseInfoService;
import com.ahsj.smartparkcore.services.RegionService;
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

import java.util.List;

@Service
public class ConferenceRoomInfoServicelmpl implements ConferenceRoomInfoService {
    @Autowired
    ConferenceRoomInfoMapper conferenceRoomInfoMapper;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;

    @Autowired
    EnterpriseInfoService enterpriseInfoService;

    private static final String filePath = Constants.FILE_PATHS;

    private static final String SUB_FILEPATH = Constants.FILE_PATHS_LOCAL;

    @Autowired
    RegionService regionService;

    /**
     *@Description 新增会议室
     *@Params [conferenceRoomInfoDTO]
     *@return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 11:31
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(ConferenceRoomInfoDTO conferenceRoomInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        ConferenceRoomInfo conferenceRoomInfo =mapper.map(conferenceRoomInfoDTO,ConferenceRoomInfo.class);
        Region region1 = regionService.selectById(conferenceRoomInfoDTO.getProvinceId());
        Region region2 = regionService.selectById(conferenceRoomInfoDTO.getCityId());
        Region region3 = regionService.selectById(conferenceRoomInfoDTO.getAreaId());
        conferenceRoomInfo.setLocation(region1.getName() + region2.getName() + region3.getName() + conferenceRoomInfoDTO.getLocation());
        //补充PO中需要但是DTO不足的数据，新增会议室默认为已启用和未租赁状态
        conferenceRoomInfo.setIsEnable(1);
        conferenceRoomInfo.setIsLease(2);
        conferenceRoomInfo.setBookType(1);
        int flag = conferenceRoomInfoMapper.insert(conferenceRoomInfo);
        if(flag != 0){
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT), HttpStatus.OK);
        }
    }

    /**
     *@Description 更新会议室信息
     *@Params [conferenceRoomInfoDTO]
     *@return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 14:34
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> update(ConferenceRoomInfoDTO conferenceRoomInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        ConferenceRoomInfo conferenceRoomInfo =mapper.map(conferenceRoomInfoDTO,ConferenceRoomInfo.class);
        Region region1 = regionService.selectById(conferenceRoomInfoDTO.getProvinceId());
        Region region2 = regionService.selectById(conferenceRoomInfoDTO.getCityId());
        Region region3 = regionService.selectById(conferenceRoomInfoDTO.getAreaId());
        conferenceRoomInfo.setLocation(region1.getName() + region2.getName() + region3.getName() + conferenceRoomInfoDTO.getLocation());

        int flag = conferenceRoomInfoMapper.updateByPrimaryKeySelective(conferenceRoomInfo);
        if(flag !=0){
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE), HttpStatus.OK);
        }

    }

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:24
    **/
    @Override
    @Transactional(readOnly = true)
    public ConferenceRoomInfoVO selectById(Long id) throws Exception {

        ConferenceRoomInfo conferenceRoomInfo = conferenceRoomInfoMapper.selectByPrimaryKey(id);

        ConferenceRoomInfoVO conferenceRoomInfoVO = new ConferenceRoomInfoVO();
        BeanUtils.copyProperties(conferenceRoomInfo, conferenceRoomInfoVO);
        String substring = StringUtils.substring(conferenceRoomInfo.getLocation(), 0, 3);
        String substring1 = StringUtils.substring(conferenceRoomInfo.getLocation(), 3, 6);
        String substring2 = StringUtils.substring(conferenceRoomInfo.getLocation(), 6, 9);
        String addressName = StringUtils.substring(conferenceRoomInfo.getLocation(), 9,conferenceRoomInfo.getLocation().length());
        Region region = regionService.queryRegionName(substring);
        Region region1 = regionService.queryRegionName(substring1);
        Region region2 = regionService.queryRegionName(substring2);
        conferenceRoomInfoVO.setProvinceId(region.getId());
        conferenceRoomInfoVO.setCityId(region1.getId());
        conferenceRoomInfoVO.setAreaId(region2.getId());
        conferenceRoomInfoVO.setLocation(addressName);
        return conferenceRoomInfoVO;
    }

    /**
     *@Description 删除
     *@Params [id]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:27
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> delete(Long[] ids) throws Exception {
        for (int i = 0; i <ids.length ; i++) {
            conferenceRoomInfoMapper.deleteByPrimaryKey(ids[i]);
        }
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_DELETE), HttpStatus.OK);
    }

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:50
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<ConferenceRoomInfoVO> list(PageBean<ConferenceRoomInfo> pageBean) throws Exception {
        PageBean<ConferenceRoomInfoVO> pageBeanVO = new PageBean<>();
        pageBeanVO.setData(CodeHelper.getInstance().setCodeValue(conferenceRoomInfoMapper.list(pageBean)));
        return pageBeanVO;
    }

    /**
     *@Description  分页查询（前端对接）
     *@Params []
     *@return java.util.List<com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO>
     *@Author zhushixiang
     *@Date 2019-10-17
     *@Time 13:48
    **/
    @Override
    @Transactional(readOnly = true)
    public List<ConferenceRoomInfoVO> listForView() throws Exception {
        List<ConferenceRoomInfoVO> conferenceRoomInfoVOS = conferenceRoomInfoMapper.listForView();
        for (ConferenceRoomInfoVO conferenceRoomInfoVO : conferenceRoomInfoVOS) {
            SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            sysAttachmentInfo.setRelateId(conferenceRoomInfoVO.getId());
            sysAttachmentInfo.setRelateKey("conferenceRoomInfo");
            sysAttachmentInfo.setRelatePage("list");
            List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfos)){
                continue;
            }
            SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
            String replace = sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
            conferenceRoomInfoVO.setFilePath(replace);
        }
        return conferenceRoomInfoVOS;
    }
}
