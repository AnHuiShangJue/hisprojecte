package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.ConferenceRoomInfoDTO;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import core.entity.PageBean;
import org.springframework.http.ResponseEntity;

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
    ConferenceRoomInfo selectById(Long id)throws Exception;

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
    ResponseEntity<ResultModel> list(PageBean<ConferenceRoomInfo> pageBean)throws Exception;
}
