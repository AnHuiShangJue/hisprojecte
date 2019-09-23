package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisBed;
import core.entity.PageBean;

import java.util.List;


public interface HisBedMapper {

    /**
     * @Description
     * @Params: [id]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/9
     * @Time 19:04
     **/
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
     * @Description
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/9
     * @Time 19:04
     **/
    int insert(HisBed record) throws Exception;

    /**
     * @Description
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/9
     * @Time 19:05
     **/

    int insertSelective(HisBed record) throws Exception;

    /**
     * @Description
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisBed
     * @Date 2019/9/9
     * @Time 19:05
     **/
    HisBed selectByPrimaryKey(Long id) throws Exception;

    /**
     * @Description
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/9
     * @Time 19:05
     **/
    int updateByPrimaryKeySelective(HisBed record) throws Exception;

    /**
     * @Description
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/9
     * @Time 19:05
     **/

    int updateByPrimaryKey(HisBed record) throws Exception;

    /**
     * @Description
     * @Params: [record]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisBed
     * @Date 2019/9/9
     * @Time 19:05
     **/

    HisBed getBed(HisBed record) throws Exception;//根据病床号查询是否存在

    /**
     * @Description
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/9/9
     * @Time 19:05
     **/

    List<HisBed> getHisBedAll(PageBean<HisBed> pageBean) throws Exception;

    /**
     * @Description
     * @Params: [wardId]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/9/9
     * @Time 19:06
     **/
    List<HisBed> getHisBed(Long wardId) throws Exception;

    /**
     * @param wardId
     * @return
     * @Description 根据病房ID查病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 14:50
     **/
    int countBedByWardId(Long wardId) throws Exception;

    /**
     * @param wardId
     * @return
     * @Description 根据病房id查当前病床数
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 14:50
     **/
    int deleteByWardId(Long wardId) throws Exception;

    /**
     * @Description
     * @Params: []
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/9
     * @Time 19:06
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
}