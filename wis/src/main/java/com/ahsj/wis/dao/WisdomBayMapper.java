package com.ahsj.wis.dao;

import com.ahsj.wis.entity.WisdomBay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WisdomBayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WisdomBay record);

    int insertSelective(WisdomBay record);

    WisdomBay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WisdomBay record);

    int updateByPrimaryKey(WisdomBay record);

    List<WisdomBay> selectAll();

}