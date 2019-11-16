package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisWard;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface HisWradService {
    /**
     * @param pageBean
     * @Description
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisWard>
     * @Date 2019/8/17
     * @Time 16:53
     **/
    PageBean<HisWard> getHisWardAll(PageBean<HisWard> pageBean) throws Exception;

    /**
     * @param record
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:28
     **/
    Message saveOrUpdate(HisWard record) throws Exception;

    /**
     * @param ids
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:28
     **/
    Message deleteByPrimaryKey(Long[] ids) throws Exception;

    /**
     * @param id
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:28
     **/
    HisWard selectByPrimaryKey(Long id) throws Exception;

    /**
     * @param ids
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:28
     **/
    Message setEnable(Long[] ids) throws Exception;

    /**
     * @param number
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:28
     **/
    HisWard gethisWardByNumber(Integer number) throws Exception;

    /**
     * @param id
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:28
     **/
    String getUserName(Long id) throws Exception;

    /**
     * @param
     * @Description
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisWard>
     * @Date 2019/8/17
     * @Time 16:54
     **/
    List<HisWard> selectHisWard() throws Exception;

    /**
     * @param record
     * @Description
     * @Author: dingli
     * @return: int
     * @Date 2019/8/17
     * @Time 16:54
     **/
    int updateByPrimaryKey(HisWard record) throws Exception;

    /**
     * @param record
     * @Description
     * @Author: dingli
     * @return: int
     * @Date 2019/8/17
     * @Time 16:54
     **/
    int updateByPrimaryKeySelective(HisWard record) throws Exception;

    /**
     * @param hisWard
     * @param request
     * @param response
     * @param session
     * @Description 导出Excel表格
     * @Author: dingli
     * @return: void
     * @Date 2019/8/28
     * @Time 15:12
     **/
    void exportExcels(Long[] ids, HisWard hisWard, String param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;


    /**
     * @param list
     * @Description 导入Excel表格
     * @Author: dingli
     * @return: java.util.Map
     * @Date 2019/8/29
     * @Time 13:23
     **/
    Map importExcel(List<HisWard> list, String filename) throws Exception;

    /**
     * @Description 病院管理选择病床
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisWard>
     * @Date 2019/11/16
     * @Time 11:40
     **/
    PageBean<HisWard> getHisWardAlls(PageBean<HisWard> pageBean) throws Exception;

}
