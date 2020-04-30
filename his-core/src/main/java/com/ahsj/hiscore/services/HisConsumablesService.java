package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsumables;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface HisConsumablesService {
    /**
     *@Description 
     *@MethodName saveOrUpdate
     *@Params [hisConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/24
     *@Time 10:52
    **/
    Message saveOrUpdate(HisConsumables hisConsumables) throws Exception;

    Message delete(Long[] ids) throws Exception;

    Message setDisable(Long[] ids) throws Exception;

    PageBean<HisConsumables> list(PageBean<HisConsumables> pageBean) throws Exception;

    HisConsumables updateInit(Long id);

    HisConsumables selectByNameandSpec(HisConsumables hisConsumables) throws Exception;

    Map importExcel(List<HisConsumables> list)throws Exception;


    /**
     * @return com.ahsj.hiscore.entity.HisConsumables
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 16:00
     **/
    HisConsumables selectByPrimaryKey(Long id);

    /**
     * @return List<HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:26
     **/
    List<HisConsumables> queryTranslateInfoByDate(HisConsumables hisConsumables) throws Exception;

    /**
     *@Description 
     *@MethodName listEnable
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumables>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:18
    **/
    PageBean<HisConsumables> listEnable(PageBean<HisConsumables> pageBean) throws Exception;


//    HisConsumables selectByPrices(@NotNull Double price1,@NotNull Double price2);



    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumables>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 17:02
     **/
    List<HisConsumables> queryAll() throws Exception;

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/4
     *@Time 15:37
    */
    List<HisConsumables> selectByExportExcel(HisConsumables hisConsumables)throws Exception;

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/4
     *@Time 15:42
    */

    void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session, String param)throws Exception;

    /**
     *@Description 
     *@MethodName selectByConsumablesCode
     *@Params [consumablesCode]
     *@return com.ahsj.hiscore.entity.HisConsumables
     *@Author XJP
     *@Date 2020/4/26
     *@Time 11:06
    **/
    HisConsumables selectByConsumablesCode(String consumablesCode);
}
