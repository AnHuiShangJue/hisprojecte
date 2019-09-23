package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedicineInfo;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface HisMedicineInfoService {


    /**
     * @return
     * @Description 新增或更新
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-12
     * @Time 21:36
     **/
    Message saveOrUpdate(HisMedicineInfo hisMedicineInfo) throws Exception;

    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-12
     * @Time 22:05
     **/
    Message delete(Long[] ids) throws Exception;


    /**
     * @return
     * @Description list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 0:23
     **/
    PageBean<HisMedicineInfo> list(PageBean<HisMedicineInfo> pageBean) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisMedicineInfo
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-06-27
     * @Time 21:31
     **/
    HisMedicineInfo selectById(Long id);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 17:28
     **/
    List<HisMedicineInfo> queryTranslateInfoByDate(HisMedicineInfo hisMedicineInfo) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 17:07
     **/
    List<HisMedicineInfo> queryAll()throws Exception;

    /**
     *@Description 搜索所有信息
     *@Params [hisMedicineInfo]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     *@Author zhushixiang
     *@Date 2019-08-27
     *@Time 11:08
    **/
    List<HisMedicineInfo> selectAll(HisMedicineInfo hisMedicineInfo);

    /**
     *@Description 导出Excel
     *@Params [ids, request, response, session]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-28
     *@Time 14:46
    **/
    void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session,String param)throws Exception;

    /**
     *@Description Excel导入药品信息
     *@Params [list]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-29
     *@Time 15:09
    **/
    Map importExcel(List<HisMedicineInfo> list)throws Exception;

    /**
     *@Description 查询相关字典ID
     *@Params []
     *@return com.ahsj.hiscore.entity.HisMedicineInfo
     *@Author zhushixiang
     *@Date 2019-08-29
     *@Time 16:49
    **/
    HisMedicineInfo queryForCode()throws Exception;

    /**
     *@Description
     *@Params [hisMedicineInfo]
     *@return com.ahsj.hiscore.entity.HisPharmacyDetail
     *@Author zhushixiang
     *@Date 2019-08-30
     *@Time 17:51
    **/
    HisMedicineInfo selectByDrugsNameAndSpec(HisMedicineInfo hisMedicineInfo);

    HisMedicineInfo selectByDrugsNumb(String drugsNumb);
    /**
     *@Description 根据导入时传来的条件搜索出对应的药品信息
     *@Params [hisMedicineInfo]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     *@Author zhushixiang
     *@Date 2019-09-02
     *@Time 14:56
    **/
    List<HisMedicineInfo> selectByExportExcel(HisMedicineInfo hisMedicineInfo);
}
