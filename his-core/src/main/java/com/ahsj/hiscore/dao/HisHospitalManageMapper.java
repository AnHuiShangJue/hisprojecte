package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisHospitalManage;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface HisHospitalManageMapper extends BaseMapper<HisHospitalManage> {
    int deleteByPrimaryKey(Long id);

    int insert(HisHospitalManage record);

    int insertSelective(HisHospitalManage record);

    HisHospitalManage selectByPrimaryKey(Long id);

    HisHospitalManage selectByIds(Long id);

    int updateByPrimaryKeySelective(HisHospitalManage record);

    int updateByPrimaryKey(HisHospitalManage record);

    /**
     * @Description list
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:28
     * @Return java.util.List<? extends com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params [pageBean]
     **/
    List<? extends HisHospitalManage> list(PageBean<HisHospitalManage> pageBean);

    /**
     * @Description 根据挂号编号查询
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:28
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [id]
     **/
    HisHospitalManage selectByHosptalRegistId(Long id);

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:29
     * @Return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params [hisNurse]
     **/
    List<HisHospitalManage> selectForInsert(HisHospitalManage hisNurse);

    /**
     * @Description 根据病人id查询
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:31
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [id]
     **/
    HisHospitalManage selectByPatientId(Long id);

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:31
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [patientId]
     **/
    HisHospitalManage selectIsDischarged(Long patientId);

    /**
     * @Description 根据住院编号查询
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:31
     * @Return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params [patientId]
     **/
    List<HisHospitalManage> selectByHosOrder(Long patientId);

    /**
     * @Description 查看正在医院住院的病人信息
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:32
     * @Return java.util.List<? extends com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params [pageBean]
     **/
    List<? extends HisHospitalManage> listForDetails(PageBean<HisHospitalManage> pageBean);

    /**
     * @Description 根据就诊编号查询
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:32
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [medicalNumber]
     **/
    HisHospitalManage selectByNumber(String medicalNumber);

    /**
     * @Description 根据日期查询
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:32
     * @Return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params []
     **/
    List<HisHospitalManage> selectByDate();


    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:33
     * @Return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params [ids]
     **/
    List<HisHospitalManage> selectForDaily(Long[] ids);

    /**
     * @Description 批量更新
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:33
     * @Return void
     * @Params [hisHospitalManageList]
     **/
    void updateBatchForDaily(List<HisHospitalManage> hisHospitalManageList);


    /**
     * @Description 批量跟新押金
     * @Author muxu
     * @Date 2019/9/24
     * @Time 20:25
     * @Return void
     * @Params [hisHospitalManageList]
     **/
    void updateBatchForDaily1(List<HisHospitalManage> hisHospitalManageList);


    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据住院编号查询
     * @Params [number]
     * @Author zhushixiang
     * @Date 2019-07-24
     * @Time 9:48
     **/
    HisHospitalManage selectByHospNumber(String number);

    /**
     * @Description 时间段内住院统计
     * @Author muxu
     * @Date 2019/7/28
     * @Time 21:58
     * @Return com.ahsj.hiscore.entity.HisHospitalManage
     * @Params [hisHospitalManage]
     **/
    HisHospitalManage selectHospitalManageDataCount(HisHospitalManage hisHospitalManage) throws Exception;

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:28
     * @Return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params []
     **/
    List<HisHospitalManage> selectManageDataCount() throws Exception;

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:40
     * @Return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Params [createDate, endDate]
     **/

    List<HisHospitalManage> selectForCalculateDate(@Param(value = "createDate") Date createDate, @Param(value = "endDate") Date endDate);

    /**
     * @param
     * @Description 所有住院病床数
     * @Author: dingli
     * @return: int
     * @Date 2019/8/13
     * @Time 17:25
     **/
    int countUseBed() throws Exception;

    /**
     * @param
     * @Description 获取所有未出院的信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Date 2019/8/13
     * @Time 17:52
     **/
    List<HisHospitalManage> getHisHospitalManageAll(HisHospitalManage pageBean);

    /**
     * @param number
     * @Description 根据病床号获取信息
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/8/14
     * @Time 9:34
     **/
    HisHospitalManage getHisHospitalManageByNumber(String number);

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @功能说明 查询新生儿的病床号
     * @Params []
     * @Author XJP
     * @Date 2019/9/11
     * @Time 13:21
     **/
    List<HisHospitalManage> getHisHospitalManageBed();

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据patientId查看病人当前是否正在住院
     * @Params [patientId]
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 15:18
     **/
    HisHospitalManage selectByPatientIdAndInpatient(Long patientId);

    /**
     * @Description 根据住院编号获取病人信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/9/14
     * @Time 15:29
     **/
    HisHospitalManage selectNumber(String number);

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalManage
     * @Description 根据交易流水号核对是否为住院
     * @Params [tollNumber]
     * @Author zhushixiang
     * @Date 2019-09-26
     * @Time 22:09
     **/
    HisHospitalManage checkIsInpatient(String tollNumber);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Description 查询所有在住院病人且具有长期医嘱且未停嘱的用药与项目医嘱相关信息
     * @Params []
     * @Author zhushixiang
     * @Date 2019-10-04
     * @Time 23:54
     **/
    List<HisHospitalManage> selectInpatientAndHaveLongTermMedicalAdvice();

    /**
     * @Description 设置病床
     * @Params: [hisHospitalManage]
     * @Author: dingli
     * @Return: int
     * @Date 2019/11/16
     * @Time 16:28
     **/
    int setBed(HisHospitalManage hisHospitalManage) throws Exception;

    /**
     * @Description 根据交易流水号查押金
     * @Params: [tollNumber]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/11/19
     * @Time 10:31
     **/
    HisHospitalManage selectByTollNumber(String number);

    HisHospitalManage selectByMedicalNumber(String number);

    //jin
    //根据病人id查询就诊记录
    List<HisHospitalManage> selectAllByPatientId(PageBean<HisHospitalManage> pageBean);
}