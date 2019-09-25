package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisInfusion;
import core.entity.PageBean;
import core.message.Message;
import java.util.List;

public interface HisInfusionService {

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:12
     * @Return core.message.Message
     * @Params [HisInfusionController, request]
    **/
    Message saveOrUpdate(HisInfusion hisInfusion) throws Exception;

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:26
     * @Return
     * @Params
    **/

    Message delete(Long[] ids) throws Exception;
    
    /**
     *@Description
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/9/2
     *@Time 17:25
    */
    Message deleteByNumber(String number) throws Exception;


    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:30
     * @Return
     * @Params
    **/

    PageBean<HisInfusion> selectByRecordIdList(PageBean<HisInfusion> pageBean)throws Exception;

    /**
     *@Description save 保存输液单信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/29
     *@Time 14:56
    */
    Message save(List<HisInfusion> hisInfusionList) throws Exception;


    /**
     *@Description listByHM
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/30
     *@Time 10:48
    */

    PageBean<HisInfusion> listByHM(PageBean<HisInfusion> pageBean)throws Exception;

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/8/31
     * @Time 16:32
     * @Return
     * @Params
    **/

    PageBean<HisInfusion> infusionDrugDetailsList(PageBean<HisInfusion> pageBean)throws Exception;


    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/30
     *@Time 16:15
    */
    PageBean<HisInfusion> listAllByNumber(PageBean<HisInfusion> pageBean)throws Exception;

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/2
     *@Time 14:10
    */
    List<HisInfusion> listByHMForPrint(String Hm)throws Exception;

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/3
     * @Time 17:11
     * @Return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     * @Params [Hm]
    **/
    List<HisInfusion> listByRecordForPrint(String recordId)throws Exception;

    /**
     *@Description 根据输液单编号查找
     *@Params [infusionNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 16:35
    **/
    List<HisInfusion> selectByNumber(String infusionNumber)throws Exception;
}
