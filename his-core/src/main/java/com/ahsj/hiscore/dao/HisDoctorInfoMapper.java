package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDoctorInfo;
import com.ahsj.hiscore.entity.HisPatientInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisDoctorInfoMapper  {
    int deleteByPrimaryKey(Long id);

    int insert(HisDoctorInfo record);

    int insertSelective(HisDoctorInfo record);

    HisDoctorInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDoctorInfo record);

    int updateByPrimaryKey(HisDoctorInfo record);

    List<? extends HisDoctorInfo> list(PageBean<HisDoctorInfo> pageBean);

    
    /**
     * @Description 根据身份证号或者编号查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:18
     * @Return java.util.List<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params [hisDoctorInfo]
    **/
    List<HisDoctorInfo> selectForInsert(HisDoctorInfo hisDoctorInfo);

    /**
     * @Description 根据departmentId获取医生姓名
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:19
     * @Return java.util.List<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params [departmentId]
    **/
    List<HisDoctorInfo> getHisDoctorInfoBydepartmentId(Long departmentId);


    /**
     * @Description 查询医生总数
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:20
     * @Return int
     * @Params []
    **/
    int selectNumberCount();


    /**
     * @Description 根据编号查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:20
     * @Return com.ahsj.hiscore.entity.HisDoctorInfo
     * @Params [doctorNumber]
    **/
    HisDoctorInfo selectByNumber(String doctorNumber);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:22
     * @Return java.util.List<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params [hisDoctorInfo]
    **/
    List<HisDoctorInfo> queryListExportAll(HisDoctorInfo hisDoctorInfo);


    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:22
     * @Return java.util.List<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params [list]
    **/
    List<HisDoctorInfo> queryListExportAndByIdsAll(List<HisDoctorInfo> list);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:24
     * @Return int
     * @Params [doctorInfoInsertList]
    **/
    int importHisDoctorInfo(List<HisDoctorInfo> doctorInfoInsertList);


    /**
     * @Description 创建时间逆向排序查询医生信息
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:25
     * @Return java.util.List<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params []
    **/
    List<HisDoctorInfo> queryDoctorAll() throws Exception;

    /**
     * @Description 根据id逆向排序查询医生信息
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:25
     * @Return java.util.List<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params []
    **/
    List<HisDoctorInfo> queryDoctorDescIdAll() throws Exception;

    /**
     * @Description 根据身份证号查询医生信息
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:26
     * @Return com.ahsj.hiscore.entity.HisDoctorInfo
     * @Params [idcard]
    **/
    HisDoctorInfo queryHisDoctorByIdCard (String idcard) throws Exception;

    /**
     * @Description 根据身份证号更新医生信息
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:30
     * @Return int
     * @Params [projectUpdateList]
    **/
    int updateHisDoctor(List<HisDoctorInfo> projectUpdateList);


    /**
     *@Description 获取所有医生信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/14
     *@Time 16:32
    */
    List<HisDoctorInfo> getAllDoctor();

}