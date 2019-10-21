package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConferenceRoomInfoMapper extends BaseMapper<ConferenceRoomInfo>{
    int deleteByPrimaryKey(Long id);

    int insert(ConferenceRoomInfo record);

    int insertSelective(ConferenceRoomInfo record);

    ConferenceRoomInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConferenceRoomInfo record);

    int updateByPrimaryKey(ConferenceRoomInfo record);

    List<?extends ConferenceRoomInfoVO> list(PageBean<ConferenceRoomInfo> pageBean);

    /**
     *@Description 分页查询（前端对接）
     *@Params []
     *@return java.util.List<com.ahsj.smartparkcore.entity.vo.ConferenceRoomInfoVO>
     *@Author zhushixiang
     *@Date 2019-10-17
     *@Time 13:48
    **/
    List<ConferenceRoomInfoVO> listForView();

    List<ConferenceRoomInfo> selectAll();
}