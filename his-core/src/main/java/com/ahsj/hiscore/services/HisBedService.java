package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisBed;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisBedService {

    /**
     * @param hisBed
     * @Description 根据病床对象新增或修改
     * @Author: dingli
     * @return: core.message.Message
     * @Date 2019/8/17
     * @Time 16:34
     **/
    Message saveOrUpdate(HisBed hisBed) throws Exception;

    /**
     * @param pageBean
     * @Description 分页查询
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/8/17
     * @Time 16:34
     **/
    PageBean<HisBed> getHisBedAll(PageBean<HisBed> pageBean) throws Exception;

    /**
     * @param wardId
     * @Description 根据病房id拿到所有的病床
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/8/17
     * @Time 16:34
     **/
    List<HisBed> getHisBed(Long wardId) throws Exception;

    /**
     * @param id
     * @Description 根据id查所有病床
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisBed
     * @Date 2019/8/17
     * @Time 16:34
     **/
    HisBed selectByPrimaryKey(Long id) throws Exception;

    /**
     * @param ids
     * @Description 批量设置启用状态
     * @Author: dingli
     * @return: core.message.Message
     * @Date 2019/8/17
     * @Time 16:34
     **/
    Message setEnable(Long[] ids) throws Exception;

    /**
     * @param ids
     * @Description 批量删除
     * @Author: dingli
     * @return: core.message.Message
     * @Date 2019/8/17
     * @Time 16:34
     **/
    Message deleteByPrimaryKey(Long[] ids) throws Exception;

    /**
     * @param id
     * @Description 删除
     * @Author: dingli
     * @return: int
     * @Date 2019/8/17
     * @Time 16:34
     **/
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
     * @param wardId
     * @Description 根据病房id删除所有病床
     * @Author: dingli
     * @return: int
     * @Date 2019/8/17
     * @Time 16:35
     **/
    int deleteByWardId(Long wardId) throws Exception;

    /**
     * @Description 根据病房id查病床总数
     * @Param wardId:
     * @Author: dingli
     * @return: int
     * @Date 2019/8/2
     * @Time 11:04
     **/
    int countBedByWardId(Long wardId) throws Exception;

    /**
     * @param
     * @Description 所有病床数目
     * @Author: dingli
     * @return: int
     * @Date 2019/8/13
     * @Time 17:27
     **/
    int countBed() throws Exception;

    /**
     * @param
     * @Description 获取所有的病床
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/8/17
     * @Time 16:31
     **/
    List<HisBed> selectHisBedAll() throws Exception;

    /**
     * @Description 住院管理设置病床
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/11/16
     * @Time 14:32
     **/
    PageBean<HisBed> getHisBedAlls(PageBean<HisBed> pageBean) throws Exception;

    /**
     * @Description 统计床数
     * @Params: []
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisBed
     * @Date 2019/11/16
     * @Time 18:01
     **/
    HisBed getCount() throws Exception;
}
