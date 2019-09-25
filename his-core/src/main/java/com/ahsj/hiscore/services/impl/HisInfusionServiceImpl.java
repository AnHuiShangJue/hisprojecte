package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisInfusionMapper;
import com.ahsj.hiscore.dao.HisMedicationDetailsMapper;
import com.ahsj.hiscore.entity.HisInfusion;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.services.HisInfusionService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;


@Service
public class HisInfusionServiceImpl implements HisInfusionService {

    @Autowired
    HisInfusionMapper hisInfusionMapper;

    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;
    /**
     * @Description 新增或更新
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:26
     * @Return core.message.Message
     * @Params [HisInfusionController, request]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisInfusion hisInfusion) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisInfusion.getId())){
            HisInfusion check = hisInfusionMapper.selectByPrimaryKey(hisInfusion.getId());
            if (!EmptyUtil.Companion.isNullOrEmpty(check)){
            hisInfusionMapper.updateByPrimaryKey(hisInfusion);
            return MessageUtil.createMessage(true,"更新成功！");}
            else {
                return MessageUtil.createMessage(false,"更新失败，数据异常请联系管理员！");
            }
        }else {
        hisInfusionMapper.insert(hisInfusion);
        return MessageUtil.createMessage(true,"新增成功！");
        }
    }


    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:27
     * @Return core.message.Message
     * @Params [ids]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id:ids){
            hisInfusionMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功！");
    }

    @Override
    @Transactional(readOnly = false)
    public Message deleteByNumber(String number) throws Exception {
        hisInfusionMapper.deleteByNumber(number);
        return MessageUtil.createMessage(true,"删除成功！");
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> selectByRecordIdList(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.selectByRecordId(pageBean)));
        return pageBean;
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/8/31
     * @Time 16:33
     * @Return core.message.Message
     * @Params [hisInfusionList]
    **/

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> infusionDrugDetailsList(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.infusionDrugDetailsList(pageBean)));
        return pageBean;
    }



    @Override
    @Transactional(readOnly = false)
    public Message save(List<HisInfusion> hisInfusionList) throws Exception {
        if (hisInfusionList.size() > 0){

            for (int i = 0; i < hisInfusionList.size(); i++) {
                HisMedicationDetails hisMedicationDetails =hisMedicationDetailsMapper.selectByPrimaryKey(Long.valueOf(hisInfusionList.get(i).getDrugsNumb()));

                //取到药品信息后set到hisInfustion中
                hisInfusionList.get(i).setDrugname(hisMedicationDetails.getDrugsName());
                hisInfusionList.get(i).setUnit(hisMedicationDetails.getSmallUnit());
                hisInfusionList.get(i).setDosage(hisMedicationDetails.getCount().toString());
                hisInfusionList.get(i).setPrice(hisMedicationDetails.getPrice());

                //更新药品信息中的alreadyout
                int a = 0;
                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails.getRemarks())){
                    a = hisMedicationDetails.getAlreadyout();
                }
                int single = Integer.valueOf(hisInfusionList.get(i).getSingleDose());
                hisMedicationDetails.setAlreadyout(a+ single);
                hisMedicationDetailsMapper.updateRemarks(hisMedicationDetails);

            }
            hisInfusionMapper.insertBatch(hisInfusionList);

            return MessageUtil.createMessage(true,"保存成功");



        }else {
            return MessageUtil.createMessage(false,"未选择药品");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> listByHM(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByHM(pageBean)));
        return pageBean;
    }


    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInfusion> listAllByHM(PageBean<HisInfusion> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listAllByHM(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> listByHMForPrint(String Hm) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByHMForPrint(Hm));
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/3
     * @Time 17:12
     * @Return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     * @Params [Hm]
    **/
    @Override
    public List<HisInfusion> listByRecordForPrint(String recordId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisInfusionMapper.listByRecordForPrint(recordId));
    }

    /**
     *@Description 根据输液单编号查找
     *@Params [infusionNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 16:28
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisInfusion> selectByNumber(String infusionNumber) throws Exception {
        List<HisInfusion> hisInfusionList = hisInfusionMapper.selectByNumber(infusionNumber);
        return hisInfusionMapper.selectByNumber(infusionNumber);
    }
}
