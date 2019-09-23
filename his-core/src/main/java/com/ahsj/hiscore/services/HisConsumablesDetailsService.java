package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsumablesDetails;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisConsumablesDetailsService {
    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/6/28
     * @Time 20:53
     */
    PageBean<HisConsumablesDetails> list(PageBean<HisConsumablesDetails> pageBean) throws Exception;

    PageBean<HisConsumablesDetails> onelist(PageBean<HisConsumablesDetails> pageBean) throws Exception;


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 根据ids 查询药库集合
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    List<HisConsumablesDetails> getHisConsumablesDetailsByIds(Long[] ids);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 根据ids  nums  申请并保存耗材集合
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    Message getHisConsumablesDetailsByIdsAndNum(Long[] ids, Integer[] nums, Long id,String medicalRecordId) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明 审核耗材出库
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 12:39
     **/
    PageBean<HisConsumablesDetails> getHisConsumablesDetailsInfo(PageBean<HisConsumablesDetails> pageBean);


    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:24
     **/
    List<HisConsumablesDetails> queryTranslateInfoByDate(HisConsumablesDetails hisConsumablesDetails) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 16:32
     **/
    List<HisConsumablesDetails> queryAll() throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesDetails>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/14
     * @Time 9:45
     **/
    PageBean<HisConsumablesDetails> lists(PageBean<HisConsumablesDetails> pageBean) throws Exception;
/**
 *@功能说明
 *@Params [pageBean]
 *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesDetails>
 *@Author XJP
 *@Date 2019/8/15
 *@Time 19:05
**/
    PageBean<HisConsumablesDetails> getHisConsumablesDetailsInfos(PageBean<HisConsumablesDetails> pageBean);
}
