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
}