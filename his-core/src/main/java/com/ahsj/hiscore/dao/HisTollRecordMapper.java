package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisTollRecord;
import com.ahsj.hiscore.entity.HisTollRecordDetails;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface HisTollRecordMapper extends BaseMapper<HisTollRecord> {

    int deleteByPrimaryKey(Long id);

    int insert(HisTollRecord record);

    int insertSelective(HisTollRecord record);

    HisTollRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisTollRecord record);

    int updateByPrimaryKey(HisTollRecord record);

    int selectNumCount(String number) throws Exception;

    List<HisTollRecord> selectByMeicalRecordId(String medicalRecordId);

    List<HisTollRecord> selectByDate();

    void updateBatchForDaily(List<HisTollRecord> hisTollDetails);

    HisTollRecordDetails hospitalDetails(String medicalRecordId);

    int selectNumbCount(String createdate);

    HisTollRecordDetails outpatientDetails(String medicalRecordId);

    /**
     * @Description 门诊收费分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 11:04
     **/
    List<HisTollRecord> getAllHisTollRecord(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @param number
     * @return
     * @Description 根据就交易流水号查出详细信息
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 14:14
     **/

    HisTollRecord getHisTollRecord(String number) throws Exception;

    /**
     * @param hisTollRecord
     * @return
     * @Description 获取门诊收费财务统计的价格
     * @Author: dingli
     * @Date 2019/7/22
     * @Time 21:26
     **/
    HisTollRecord getPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecord
     * @Description 根据交易流水号搜索
     * @Params [number]
     * @Author zhushixiang
     * @Date 2019-07-24
     * @Time 9:43
     **/
    HisTollRecord selectByNumber(String number);

    /**
     * @Description 统计各个时间段收费数目
     * @Author muxu
     * @Date 2019/7/28
     * @Time 22:16
     * @Return
     * @Params
     **/
    List<HisTollRecord> selectTollRecordDataCount(@Param(value = "createDate") Date createDate, @Param(value = "endDate") Date endDate) throws Exception;

    HisTollRecord selectTollRecordDataCount(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 根据交易号查询交易信息
     * @Param number:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/7/30
     * @Time 10:49
     **/
    HisTollRecord selectByNumbers(String number);

    HisTollRecord ListByNumber(String number);

    /**
     * @Description 就诊卡的财务统计
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/12
     * @Time 15:10
     **/
    List<HisTollRecord> getVisitCard(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @Description 财务统计获取就诊卡的价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/12
     * @Time 15:31
     **/
    HisTollRecord getPriceVisitCard(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 根据就诊卡号查看详细信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/12
     * @Time 16:58
     **/
    HisTollRecord selectVisitCard(String number) throws Exception;

    /**
     * @Description 财务统计药品的价格
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/12
     * @Time 16:58
     **/
    List<HisTollRecord> getAllDrug(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisTollRecordDetails
     * @Description 根据就诊编号搜索出公共刷卡模块需要的一些信息 如病人信息，医生信息，就诊科室等
     * @Params [medicalNumber]
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 16:05
     **/
    HisTollRecordDetails selectByMedicalNumberForCommonDetails(String medicalNumber);

    /**
     * @Description 财务统计获取所有项目的分页查询
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/14
     * @Time 9:55
     **/
    List<HisTollRecord> getAllProject(PageBean<HisTollRecord> pageBean) throws Exception;

    /**
     * @Description 财务统计获取所有药品的价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 11:08
     **/
    HisTollRecord getDrugPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 财务统计获取所有的项目价格
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/14
     * @Time 11:09
     **/
    HisTollRecord getProjectPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 药库盘点
     * @Params: [hisTollRecord]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/19
     * @Time 10:18
     **/
    List<HisTollRecord> pharmacyInventory(PageBean<HisTollRecord> pageBean) throws Exception;


    /**
     * @Description
     * @Params: `
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/19
     * @Time 11:23List<HisTollRecord> pharmacyInventory(PageBean<HisTollRecord> pageBean) throws Exception;
     **/
    HisTollRecord getPharmacyinventoryPrice(HisTollRecord hisTollRecord) throws Exception;

    /**
     * @Description 药库盘点详细信息
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollRecord>
     * @Date 2019/9/26
     * @Time 11:02
     **/
    List<HisTollRecord> pharmacyInventoryDetail(PageBean<HisTollRecord> pageBean) throws Exception;



    /**
     *@Description 根据就诊编号HHM查询交易流水号
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/9
     *@Time 16:40
    */

    List<String> listNumberByMedicalNumber(String medicalNumber);


    ////打印所有在医院产生的费用信息
    List<HisTollRecordDetails> selectAllInformationByHRNumber(String hrNumber);
}
