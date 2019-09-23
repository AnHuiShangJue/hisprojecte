package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisPrescriptionMedicine;
import com.ahsj.hiscore.entity.TreeEntity;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface HisPrescriptionMedicineService {
    List<TreeEntity> selectTreeCode();

    PageBean<HisPrescriptionMedicine> list(PageBean<HisPrescriptionMedicine> pageEntity);

    HisPrescriptionMedicine updateInit(Long id);

    Message saveOrUpdate(HisPrescriptionMedicine hisPrescriptionMedicine)throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Description 根据处方ID搜索对应药品信息，但是要将pharmacy_id 取别名为id，为适应自定义表格selfTable
     * @Params [prescriptionId]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 11:15
     **/
    List<HisPrescriptionMedicine> selectByPrescriptionIdForTable(String prescriptionId)throws Exception;

    /**
     * @return core.message.Message
     * @Description 为药方添加药品信息
     * @Params [hisPrescriptionMedicine]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:09
     **/
    Message saveOrUpdateForDetails(Long[] ids, String prescriptionId, Integer[] nums, String[] descriptions, String[] remark) throws Exception;

    /**
     * @return void
     * @Description 批量新增
     * @Params [hisPrescriptionMedicineList]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:29
     **/
    void saveForMultiple(List<HisPrescriptionMedicine> hisPrescriptionMedicineList) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Description 根据药方ID显示对应药品详细信息
     * @Params [pageEntity]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 17:15
     **/
    PageBean<HisPrescriptionMedicine> listForDetails(PageBean<HisPrescriptionMedicine> pageBean)throws Exception;

    /**
     * @param pageBean
     * @Description 根据药房id查找明细
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Date 2019/8/27
     * @Time 17:45
     **/
    PageBean<HisPrescriptionMedicine> listDetail(PageBean<HisPrescriptionMedicine> pageBean)throws Exception;

    /**
     * @param id
     * @Description 添加药方模板
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Date 2019/8/27
     * @Time 18:05
     **/
    List<HisPrescriptionMedicine> selectByPrescriptionIdForTables(Long id)throws Exception;

    /**
     * @param param
     * @param request
     * @param response
     * @param session
     * @Description 导出药方
     * @Author: dingli
     * @return: void
     * @Date 2019/9/2
     * @Time 10:40
     **/
    void exportExcels( HisPrescriptionMedicine hisPrescriptionMedicine, String param,
                      HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;

    /**
     *@Description 药方excel导入
     *@Params [list]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 10:12
     **/
    Map importExcel(List<HisPrescriptionMedicine> listForParentMenu, List<HisPrescriptionMedicine> listForName, List<HisPrescriptionMedicine> list)throws Exception;


    /**
     *@Description 查看门诊病历模板中的药方明细
     *@Params [pageEntity]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 18:34
    **/
    PageBean<HisPrescriptionMedicine> viewDetailInMedicalTemplate(PageBean<HisPrescriptionMedicine> pageEntity)throws Exception;

    /**
     * @return core.message.Message
     * @Description 为药方添加药品信息
     * @Params [hisPrescriptionMedicine]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:09
     **/
    Message saveOrUpdateForDetailsForTemplate(Long[] ids, String prescriptionId, Integer[] nums, String[] descriptions) throws Exception;

}
