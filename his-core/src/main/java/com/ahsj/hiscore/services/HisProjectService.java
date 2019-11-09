/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisProjectService
 * Author:   anhuishangjue
 * Date:     2019/7/10 14:01
 * Description: HisProjectService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.dto.HisProjectRes;
import core.entity.PageBean;
import core.message.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈HisProjectService〉
 *
 * @author anhuishangjue
 * @create 2019/7/10
 * @since 1.0.0
 */

public interface HisProjectService {

    /**
     * @param ids
     * @return
     * @throws Exception
     */
    Message deleteByPrimaryKey(Long[] ids) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:15
     **/
    Message insertSelective(HisProject hisProject) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:16
     **/
    HisProject selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:16
     **/
    Message updateByPrimaryKeySelective(HisProject hisProject) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:16
     **/
    PageBean<HisProject> queryList(PageBean<HisProject> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:16
     **/
    Message updateSetDisable(Long[] ids) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [combinationId]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:16
     **/
    List<HisProject> queryCombinationId(Long combinationId) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:16
     **/
    PageBean<HisProject> queryCombinationIds(PageBean<HisProject> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:17
     **/
    List<HisProject> queryTranslateInfoByDate(HisProject hisProject) throws Exception;


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 16:55
     **/
    List<HisProject> queryAll() throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:35
     **/
    PageBean<HisProject> queryLists(PageBean<HisProject> pageBean) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 17:39
     **/
    PageBean<HisProject> queryAddList(PageBean<HisProject> pageBean);

    /**
     * @return com.ahsj.hiscore.entity.HisProject
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:10
     **/
    HisProject getHisProject(HisProject hisProject);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisProject>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 10:18
     **/
    List<HisProject> queryHisProjectByIds(Long[] ids);

    /**
     * @return java.util.Map
     * @功能说明
     * @Params [excelData]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:01
     **/
    Map importExcel(List<HisProject> excelData) throws Exception;

    /**
     * @return void
     * @功能说明
     * @Params [ids, request, response, session]
     * @Author XJP
     * @Date 2019/8/28
     * @Time 9:45
     **/
    void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session,String param,HisProject hisProject) throws Exception;

    /**
     * @return java.util.List<java.lang.Long>
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/28
     * @Time 16:06
     **/
    List<Long> search(HisProjectRes hisProjectRes);

    /**
     *@Description 根据IDs查询项目信息
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisProject>
     *@Author zhushixiang
     *@Date 2019-10-08
     *@Time 13:50
    **/
    List<HisProject> selectForListForProjectByIds(Long[] ids)throws Exception;

    //根据项目编号查询项目信息
    HisProject selectByNumber(String number)throws Exception;
}
