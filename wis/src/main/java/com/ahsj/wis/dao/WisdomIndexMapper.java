package com.ahsj.wis.dao;

import com.ahsj.wis.entity.WisdomIndex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WisdomIndexMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WisdomIndex record);

    int insertSelective(WisdomIndex record);

    WisdomIndex selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WisdomIndex record);

    int updateByPrimaryKey(WisdomIndex record);
    /**
     * @Description 获取所有的值
     * @Params: []
     * @Author: dingli
     * @Return: com.ahsj.wisdom.entity.WisdomIndex
     * @Date 2019/11/4
     * @Time 16:09
     **/
    List<WisdomIndex> selectAll();

    /**
     * @Description 根据名字修改值
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/11/4
     * @Time 16:39
     **/
    int updateByName(WisdomIndex record);
}
