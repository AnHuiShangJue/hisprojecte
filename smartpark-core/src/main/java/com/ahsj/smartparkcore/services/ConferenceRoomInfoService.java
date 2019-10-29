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
     * @return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     * @Description 新增会议室
     * @Params [conferenceRoomInfoDTO]
     * @Author zhushixiang
     * @Date 2019-09-05
     * @Time 11:30
     **/
    ResponseEntity<ResultModel> save(ConferenceRoomInfoDTO conferenceRoomInfoDTO) throws Exception;

    /**
     * @return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     * @Description 更新会议室信息
     * @Params [conferenceRoomInfoDTO]
     * @Author zhushixiang
     * @Date 2019-09-05
     * @Time 14:34
     **/
    ResponseEntity<ResultModel> update(ConferenceRoomInfoDTO conferenceRoomInfoDTO) throws Exception;

    /**
     * @return com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-09-05
     * @Time 15:24
     **/
    ConferenceRoomInfoVO selectById(Long id) throws Exception;

    /**
     * @return int
     * @Description 删除
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-09-05
     * @Time 15:26
     **/
    ResponseEntity<ResultModel> delete(Long[] ids) throws Exception;

    /**
     * @return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Description 分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-09-05
     * @Time 15:50
     **/
    PageBean<ConferenceRoomInfoVO> list(PageBean<ConferenceRoomInfo> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO>
     * @Description 分页查询（前端对接）
     * @Params []
     * @Author zhushixiang
     * @Date 2019-10-17
     * @Time 13:47
     **/
    List<ConferenceRoomInfoVO> listForView() throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/10/29
     * @Time 13:36
     **/
    List<ConferenceRoomInfo> selectByIds(List<Long> ids) throws Exception;
}
