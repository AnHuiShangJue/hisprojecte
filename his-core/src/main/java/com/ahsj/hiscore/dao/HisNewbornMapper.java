package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisNewborn;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisNewbornMapper {

    int deleteByPrimaryKey(Long id);

    int insert(HisNewborn record);

    int insertSelective(HisNewborn record);

    HisNewborn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisNewborn record);

    int updateByPrimaryKey(HisNewborn record);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisNewborn>
     * @功能说明 分页查询新生儿信息
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 15:08
     **/
    List<HisNewborn> queryList(PageBean<HisNewborn> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisNewborn>
     * @功能说明 根据病床和出院状态查询新生儿信息
     * @Params [hisNewborn]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 15:24
     **/
    List<HisNewborn> selectHisNewborn(HisNewborn hisNewborn);

    /**
     * @return void
     * @功能说明  批量修改新生儿出院状态
     * @Params [hisNewbornList]
     * @Author XJP
     * @Date 2019/9/10
     * @Time 15:35
     **/
    int updateIsEnable(List<HisNewborn> hisNewbornList);
}