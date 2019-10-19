package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.ConferenceRoomInfoDTO;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO;
import core.entity.PageBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConferenceRoomInfoService {
    /**
     *@Description 新增会议室
     *@Params [conferenceRoomInfoDTO]
     *@return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 11:30
    **/
    ResponseEntity<ResultModel> save(ConferenceRoomInfoDTO conferenceRoomInfoDTO)throws Exception;

    /**
     *@Description 更新会议室信息
     *@Params [conferenceRoomInfoDTO]
     *@return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 14:34
    **/
    ResponseEntity<ResultModel> update(ConferenceRoomInfoDTO conferenceRoomInfoDTO)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:24
    **/
    ConferenceRoomInfoVO selectById(Long id)throws Exception;

    /**
     *@Description 删除
     *@Params [id]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:26
    **/
    ResponseEntity<ResultModel> delete(Long[] ids)throws Exception;

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:50
    **/
    PageBean<ConferenceRoomInfoVO> list(PageBean<ConferenceRoomInfo> pageBean)throws Exception;

    /**
     *@Description  分页查询（前端对接）
     *@Params []
     *@return java.util.List<com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO>
     *@Author zhushixiang
     *@Date 2019-10-17
     *@Time 13:47
    **/
    List<ConferenceRoomInfoVO> listForView()throws Exception;
}
