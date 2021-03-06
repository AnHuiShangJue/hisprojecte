package com.ahsj.hiscore.services;

import com.ahsj.hiscore.common.utils.ZipUtils;
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
     *@Description 根据id查询
     *@Params
     *@return
     *@Author jin
     *@Date 2019/10/6
     *@Time 10:34
    */
    List<HisInfusion> listByNumbers(String[] numbers)throws Exception;


    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/2
     *@Time 14:10
    */
    List<HisInfusion> listByHMForPrint(String Hm)throws Exception;
    List<HisInfusion> listByRemarkForPrint(String remark)throws Exception;

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

    /**
     *@Description 根据输液单编号查找（为住院输液单服务）
     *@Params [number]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 23:40
    **/
    List<HisInfusion> listByHMForHospitalPrint(String number);

    /**
     *@Description 保存门诊所开的输液单
     *@Params [ids, dosages, usages, startTime]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 8:32
    **/
    Message addInfusionMedicine(List<HisInfusion> hisInfusionList, Long recordId, List<HisInfusion> checkInfusionList)throws Exception;

    /**
     *@Description 根据就诊编号查询对应未付款的输液单
     *@Params [medicalRecordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 13:15
    **/
    List<HisInfusion> selectByRecordNumberAndNotPay(String medicalRecordId)throws Exception;

    /**
     *@Description 更新remark
     *@Params
     *@return
     *@Author jin
     *@Date 2019/10/6
     *@Time 16:48
    */
    void updateRemarks(HisInfusion hisInfusion)throws Exception;

    /**
     *@Description 根据分组编号remark查询
     *@Params
     *@return
     *@Author jin
     *@Date 2019/10/6
     *@Time 16:58
    */
    PageBean<HisInfusion> listByRemark(PageBean<HisInfusion> pageBean)throws Exception;

    /**
     *@Description 根据用药明细id搜索输液单
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisInfusion
     *@Author zhushixiang
     *@Date 2019-10-07
     *@Time 11:25
    **/
    List<HisInfusion> selectByMedicationId(Long id)throws Exception;

    /**
     *@Description 根据药品编号和就诊记录ID 核查该药品是否存在输液单
     *@Params [recordId, drugsNumb]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-10-07
     *@Time 17:24
    **/
    List<HisInfusion> selectByDrugsNumbAndRecordIdAndNotPay(Long recordId, String drugsNumb)throws Exception;
}
