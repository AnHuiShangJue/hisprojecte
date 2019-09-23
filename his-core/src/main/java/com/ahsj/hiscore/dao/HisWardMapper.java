package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisWard;
import core.entity.PageBean;

import java.util.List;

public interface HisWardMapper {

    /**
     * @param id
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
     * @param record
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/
    int insert(HisWard record) throws Exception;

    /**
     * @param record
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/

    int insertSelective(HisWard record) throws Exception;

    /**
     * @param id
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/

    HisWard selectByPrimaryKey(Long id) throws Exception;

    /**
     * @param record
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/

    int updateByPrimaryKeySelective(HisWard record) throws Exception;

    /**
     * @param record
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/

    int updateByPrimaryKey(HisWard record) throws Exception;

    /**
     * @param pageBean
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/

    List<HisWard> getHisWardAll(PageBean<HisWard> pageBean) throws Exception;

    /**
     * @param number
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:25
     **/
    HisWard gethisWardByNumber(Integer number) throws Exception;//根据病房号查询是否存在

    /**
     * @param id
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:26
     **/
    String getUserName(Long id) throws Exception;

    /**
     * @param
     * @Description
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisWard>
     * @Date 2019/8/17
     * @Time 16:55
     **/
    List<HisWard> selectHisWard() throws Exception;

    /**
     * @Description
     * @Params: [hisWard]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisWard>
     * @Date 2019/9/11
     * @Time 14:41
     **/
    List<HisWard> getHisWardList(HisWard hisWard) throws Exception;

    /**
     * @Description
     * @Params: [hisWardList]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/11
     * @Time 14:41
     **/

    int importHisWard(List<HisWard> hisWardList);

    /**
     * @Description
     * @Params: [ids]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisWard>
     * @Date 2019/9/11
     * @Time 14:41
     **/
    List<HisWard> selectByIds(Long[] ids) throws Exception;


}