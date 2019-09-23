package com.ahsj.hiscore.dao;

        import com.ahsj.hiscore.entity.HisMedicalRecord;
import core.entity.PageBean;

import java.util.List;

public interface HisMedicalRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisMedicalRecord record);

    int insertSelective(HisMedicalRecord record);

    HisMedicalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedicalRecord record);

    int updateByPrimaryKey(HisMedicalRecord record);

    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:35
     * @Return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
    **/
    List<? extends HisMedicalRecord> list(PageBean<HisMedicalRecord> pageBean);

    /**
     * @Description 接诊记录list
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:35
     * @Return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
    **/
    List<? extends HisMedicalRecord> medicalrecordlist(PageBean<HisMedicalRecord> pageBean);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:35
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [patientId]
    **/
    HisMedicalRecord selectByPatientId(Long patientId);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:35
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [registeredId]
    **/
    HisMedicalRecord selectCase (Long registeredId);

    /**
     * @Description 查询数量
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:36
     * @Return int
     * @Params [number]
    **/
    int selectMedicalCount(String number);

    /**
     * @Description 根据病人id查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:36
     * @Return java.util.List<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [patientId]
    **/
    List<HisMedicalRecord> selectByMedicalRecordOrder(Long patientId);


    /**
     * @Description 根据挂号id查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:36
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [id]
    **/
    HisMedicalRecord selectByRegisterId(Long id);

    /**
     * @Description 根据就诊记录编号查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:37
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [medicalRecordId]
    **/
    HisMedicalRecord selectByMedicalRecordId( String medicalRecordId);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:38
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [id]
    **/
    HisMedicalRecord selectByUserId(Long id);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:38
     * @Return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
    **/
    List<? extends HisMedicalRecord> listForMedicalHistoryByPatientId(PageBean<HisMedicalRecord> pageBean);

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:38
     * @Return java.util.List<? extends com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
    **/
    List<? extends HisMedicalRecord> listForAllMedicalHistoryByPatientId(PageBean<HisMedicalRecord> pageBean);

    /**
     * @Description 查询打印信息
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:38
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [number]
    **/
    HisMedicalRecord selectPrint(String number);

    /**
     *@Description 根据病人ID查询出最近一条的病人就诊编号
     *@Params [patientId]
     *@return com.ahsj.hiscore.entity.HisMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:21
    **/
    HisMedicalRecord selectTheLastRecordByPatientId(Long patientId);
}