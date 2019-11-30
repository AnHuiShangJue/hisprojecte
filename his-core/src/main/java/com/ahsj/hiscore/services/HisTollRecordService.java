package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.*;
import core.entity.PageBean;
import core.message.Message;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/17
 * @Time 16:00
 **/
public interface HisTollRecordService {
    /**
     * @param hisTollRecord
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/17
     * @Time 16:01
     **/
    Message addHisTollRecord(List<HisCharge> record, HisTollRecord hisTollRecord) throws Exception;


    HisTollRecordDetails hospitalDetails(String medicalRecordId);

    Message hospitalSave(HisTollHospiModel hisTollHospiModel);


    /**
     * @param Message
     * @return
     * @Description 住院押金充值
     * @Author: czc
     * @Date 2019/11/23
     * @Time 20:49
     **/
    Message hospitalSaveForRestDepo(HisTollHospiModel hisTollHospiModel) throws Exception;

    Message hospitalExit(HisTollHospiModel hisTollHospiModel);

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 根据就诊编号拉去门诊信息
     * @Params [medicalRecordId]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 11:23
     **/
    HisTollRecordDetails outpatientDetails(String medicalRecordId) throws Exception;

    /**
     * @return core.message.Message
     * @Description 保存收费信息（收费记录）
     * @Params [hisTollHospiModel]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 15:48
     **/
    Message outpatientSave(HisTollHospiModel hisTollHospiModel) throws Exception;

    /**
     * @param pageBean
     * @return
     * @Description 查询门诊住院收费信息
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 12:11
     **/

    PageBean<HisTollRecord> getAllHisTollRecord(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @param number
     * @return
     * @Description 根据交易流水号查看详细信息
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 14:15
     **/
    HisTollRecord getHisTollRecord(String number) throws Exception;

    /**
     * @param hisTollRecord
     * @return
     * @Description 条件查询下的总金额
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 21:38
     **/
    HisTollRecord getPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 根据退费凭证生成交易流水号
     * @Param vocher:
     * @Author: dingli
     * @return: core.message.Message
     * @Date 2019/7/25
     * @Time 14:15
     **/
    Message saveHisApplicationForDrugReturn(HisTollHospiModel hisTollHospiModel) throws Exception;


    /**
     * @Description 统计各个时间段收费数目
     * @Author muxu
     * @Date 2019/7/28
     * @Time 22:16
     * @Return
     * @Params
     **/
    Integer[] selectTollRecordDataCount(Date createDate) throws Exception;

    /**
     * @Description 根据交易流水号查询详情（单表查询）
     * @Param number:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/7/30
     * @Time 10:53
     **/
    HisTollRecord selectByNumbers(String number);

    /**
     * @Description 根据交易流水号查询充值卡，退卡详情
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/11
     * @Time 13:47
     **/
    HisTollRecord ListByNumber(String number);

    /**
     * @return core.message.Message
     * @Description 保存收费记录
     * @Params [hisTollRecord]
     * @Author zhushixiang
     * @Date 2019-09-11
     * @Time 10:27
     **/
    String save(HisTollRecord hisTollRecord, List<HisTollDetails> hisTollDetailsList) throws Exception;

    /**
     * @Description 就诊卡的财务统计
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/12
     * @Time 15:10
     **/
    PageBean<HisTollRecord> getVisitCard(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @Description
     * @Params: [hisTollRecord] 条件查询总金额
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/12
     * @Time 15:35
     **/
    HisTollRecord getPriceVisitCard(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 根据就诊卡号查询详细信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/12
     * @Time 16:28
     **/
    HisTollRecord selectVisitCard(String number) throws Exception;

    /**
     * @Description 财务统计获取所有的药品分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 10:56
     **/
    PageBean<HisTollRecord> getAllDrug(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 根据就诊编号搜索出公共刷卡模块需要的一些信息 如病人信息，医生信息，就诊科室等
     * @Params [medicalNumber]
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 16:04
     **/
    HisTollRecordDetails selectByMedicalNumberForCommonDetails(String medicalNumber) throws Exception;

    /**
     * @Description 财务统计药品分页查询价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 10:03
     **/
    HisTollRecord getDrugPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 财务统计项目分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 10:54
     **/
    PageBean<HisTollRecord> getAllProject(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @Description 财务统计获取所有项目的价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 11:11
     **/
    HisTollRecord getProjectPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 药库盘点
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/19
     * @Time 10:19
     **/
    PageBean<HisTollRecord> pharmacyInventory(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @Description 药品盘点价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/19
     * @Time 11:35
     **/
    HisTollRecord getPharmacyinventoryPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 项目退费
     * @Params: [hisTollHospiModel]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/25
     * @Time 10:20
     **/
    Message hisProjectSave(HisRefundProjectInfo hisRefundProjectInfo) throws Exception;

    /**
     * @Description 查看药库盘点明细
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/26
     * @Time 11:06
     **/
    PageBean<HisTollRecord> pharmacyInventoryDetail(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     *@Description 根据交易流水号核对是否为住院
     *@Params [tollNumber]
     *@return com.ahsj.hiscore.entity.HisHospitalManage
     *@Author zhushixiang
     *@Date 2019-09-26
     *@Time 22:03
    **/
    HisHospitalManage checkIsInpatient(String tollNumber)throws Exception;

    //打印所有在医院产生的费用信息
    List<HisTollRecordDetails> selectAllInformationByHRNumber(String hrNumber)throws Exception ;


    /**
     *@Description 根据就诊编号HHM查询交易流水号
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/9
     *@Time 16:38
    */

    List<String> listNumberByMedicalNumber(String medicalNumber)throws Exception;

    /*
    *  为了护士站引入的查询消费明细
    * */
    HisTollRecordDetails   hospitalDetailsForNurse(String medicalNumber) throws Exception;

    //根据住院号搜索出所有与此住院号相关的收费明细且实际收费大于0（即交押金的那条数据的明细）
    List<HisTollRecord> selectByHRNumberForAllDeposit(String number);
}
