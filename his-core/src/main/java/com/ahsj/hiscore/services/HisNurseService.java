package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisNurse;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface HisNurseService {

    /**
     *@Description  新增或更新
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    Message saveOrUpdate(HisNurse hisNurse, HttpServletRequest request) throws Exception;
    /**
     *@Description  删除
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    Message delete(Long[] ids)throws Exception;
    /**
     *@Description  list
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    PageBean<HisNurse> list(PageBean<HisNurse> pageBean)throws Exception;

    /**
     *@Description  初始化
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    HisNurse updateInit(Long id)throws Exception;

    /**
     *@Description 查询
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisNurse
     *@Author chenzhicai
     *@Date 2019/6/21
     *@Time 4:08 PM
    **/
    HisNurse selectById(Long id);

    /**
     *
     * @return
     */
    List<HisNurse> selectAll(Integer pageNum, Integer pageSize);

    /**
     *
     * @return
     */
   // Integer queryNurseCount();
    /**
     *@Description 查询
     *@Params [departmentId]
     *@return com.ahsj.hiscore.entity.HisNurse
     *@Author dingli
     *@Date 2019/7/10
     *@Time 17:00 PM
     **/
    List<HisNurse> getHisNurseByDepartmentId(Long departmentId );
    
    /**
     * @Description 
     * @Author   muxu
     * @Date 2019/7/11
     * @Time 17:03
     * @Return java.util.List<com.ahsj.hiscore.entity.HisNurse>
     * @Params []
    **/
    List<HisNurse> selectNurse()throws Exception;
    
}
