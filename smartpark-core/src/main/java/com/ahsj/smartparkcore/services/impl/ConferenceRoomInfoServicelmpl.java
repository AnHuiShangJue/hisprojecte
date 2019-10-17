package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ConferenceRoomInfoMapper;
import com.ahsj.smartparkcore.entity.dto.ConferenceRoomInfoDTO;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO;
import com.ahsj.smartparkcore.services.ConferenceRoomInfoService;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConferenceRoomInfoServicelmpl implements ConferenceRoomInfoService {
    @Autowired
    ConferenceRoomInfoMapper conferenceRoomInfoMapper;

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

        //补充PO中需要但是DTO不足的数据，新增会议室默认为已启用和未租赁状态
        conferenceRoomInfo.setIsEnable(1);
        conferenceRoomInfo.setIsLease(2);
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
    public ConferenceRoomInfo selectById(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(conferenceRoomInfoMapper.selectByPrimaryKey(id));
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
        return conferenceRoomInfoMapper.listForView();
    }
}
