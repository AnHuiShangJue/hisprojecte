package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicineInfoMapper;
import com.ahsj.hiscore.dao.HisPharmacyDetailMapper;
import com.ahsj.hiscore.dao.HisPrescriptionMapper;
import com.ahsj.hiscore.dao.HisPrescriptionMedicineMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.TranslateModel.HisPrescriptionMedicineTranslate;
import com.ahsj.hiscore.entity.TranslateModel.HisPrescriptionTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.services.HisPrescriptionService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class HisPrescriptionServiceImpl implements HisPrescriptionService {

    private Logger log = LoggerFactory.getLogger(HisPrescriptionServiceImpl.class.getSimpleName());

    @Autowired
    HisPrescriptionMapper hisPrescriptionMapper;
    @Autowired
    HisPrescriptionMedicineMapper hisPrescriptionMedicineMapper;
    @Autowired
    HisMedicineInfoMapper hisMedicineInfoMapper;
    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Override
    /**
     *
     *功能说明：新增药方
     *@author Eric
     *@data 2019-06-19 15:21
     *@param [hisPrescription]
     *@return core.message.Message
     */

    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisPrescription hisPrescription) {
        //如果id为空则是新增不然就是更新
        if (EmptyUtil.Companion.isNullOrEmpty(hisPrescription.getId())) {
            HisPrescription checkName = hisPrescriptionMapper.selectByName(hisPrescription);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkName) && !EmptyUtil.Companion.isNullOrEmpty(checkName.getId())) {
                return MessageUtil.createMessage(false, "此药方名称已存在！");
            }
            //查询出此ParentId下最大的treeId
            HisPrescription maxTreeId = hisPrescriptionMapper.selectMaxTreeId(hisPrescription.getParentId());

            String treeId = null;
            if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                treeId = maxTreeId.getTreeId();
            }
            /*此步是封装好的操作
            如果上一步if判断中 treeId获得了值那么就使其最后一位+1  如1001001变为1001002
            如果if判断中 treeId没有获得值 那么就令其ParentId后加001 如1001变为1001001
            通俗来说，此步的作用也就是给当前对象的treeId赋值
             */
            treeId = TreeHelper.getInstance().getTreeId(treeId, hisPrescription.getParentId());
            hisPrescription.setTreeId(treeId);

             hisPrescriptionMapper.insert(hisPrescription);
            searchIsAvailable();
            log.info("--------------------药方基本新增翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisPrescriptionTranslate hisPrescriptionTranslate = new HisPrescriptionTranslate();
            BeanUtils.copyProperties(hisPrescription, hisPrescriptionTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisPrescriptionTranslate(hisPrescriptionTranslate);
            System.out.println(hisPrescriptionTranslate.toString()+"<--------------------");
            amqpTemplat.convertAndSend("com.ahsj.add.hisPrescription", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------药方基本新增翻译发送结束---------------------------");
           /* if (flag == 0) {
                return MessageUtil.createMessage(false, "保存失败。");
            }*/
            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            HisPrescription checkName = hisPrescriptionMapper.selectByName(hisPrescription);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkName) && !EmptyUtil.Companion.isNullOrEmpty(checkName.getId())) {
                return MessageUtil.createMessage(false, "此药方名称已存在！");
            }
            int flag = hisPrescriptionMapper.updateByPrimaryKeySelective(hisPrescription);
            searchIsAvailable();

            log.info("--------------------药方基本修改翻译发送开始---------------------------");
            BaseLoginUser loginUser = new BaseLoginUser();
            TranslateModels translateModels = new TranslateModels();
            HisPrescriptionTranslate hisPrescriptionTranslate = new HisPrescriptionTranslate();
            BeanUtils.copyProperties(hisPrescription, hisPrescriptionTranslate);
            translateModels.setUserId(loginUser.getId());
            translateModels.setHisPrescriptionTranslate(hisPrescriptionTranslate);
            amqpTemplat.convertAndSend("com.ahsj.update.hisPrescription", JsonUtils.serialize(translateModels));
            log.info(JsonUtils.serialize(translateModels));
            log.info("--------------------药方基本修改翻译发送结束---------------------------");
            return MessageUtil.createMessage(true, "更新成功。");

        }
    }

    @Override
    public List<HisPrescription> selectAll() {
        List<HisPrescription> list = hisPrescriptionMapper.selectAll();
        return list;
    }

    @Override
    public HisPrescription selectByPrescriptionId(Long id) {
        return hisPrescriptionMapper.selectByPrimaryKey(id);
    }

    /**
     * @return core.message.Message
     * @Description 删除当前节点(级联删除其子节点以及子节点以下的数据)
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-07-30
     * @Time 14:58
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] id) {
        for (int i = 0; i < id.length; i++) {
            recursionDelete(id[i].toString());
        }
        searchIsAvailable();
        return MessageUtil.createMessage(true, "删除成功。");
    }

    /**
     * @return void
     * @Description 递归删除父节点下的所有子节点
     * @Params [treeId]
     * @Author zhushixiang
     * @Date 2019-07-30
     * @Time 15:28
     **/
    @Transactional(readOnly = false)
    public void recursionDelete(String id) {
        while (true) {
            List<HisPrescription> hisPrescriptionList = hisPrescriptionMapper.selectByParentId(id);
            hisPrescriptionMapper.deleteByTreeId(id);//删除当前节点
            hisPrescriptionMapper.deleteByParentId(id);//首先删除当前节点下的所有子节点
            if (hisPrescriptionList.size() == 0) {
                List<HisPrescriptionMedicine> hisPrescriptionMedicineList = hisPrescriptionMedicineMapper.selectByPrescriptionId(id);
                if (hisPrescriptionMedicineList.size() != 0)
                    hisPrescriptionMedicineMapper.deleteByPrescriptionId(id);
                break;
            } else {
                for (int i = 0; i < hisPrescriptionList.size(); i++) {
                    recursionDelete(hisPrescriptionList.get(i).getTreeId());
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Message addMedical(Long prescriptionId, HisPrescriptionMedicine[] hisPrescriptionMedicines) {
        HisPrescription hisPrescription = hisPrescriptionMapper.selectByPrimaryKey(prescriptionId);
        if (EmptyUtil.Companion.isNullOrEmpty(hisPrescription)) {
            return MessageUtil.createMessage(false, "该药方不存在。");
        }
        for (int i = 0; i < hisPrescriptionMedicines.length; i++) {
            HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByPrimaryKey(hisPrescriptionMedicines[i].getMedicineId());
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo)) {
                return MessageUtil.createMessage(false, hisPrescriptionMedicines[i].getDrugsName() + "不存在");
            }
        }
        for (int i = 0; i < hisPrescriptionMedicines.length; i++) {
            hisPrescriptionMedicineMapper.insertSelective(hisPrescriptionMedicines[i]);
        }


        log.info("--------------------药方基本明细新增翻译发送开始---------------------------");
        List<HisPrescriptionMedicineTranslate> hisPrescriptionMedicineTranslateArrayList = new ArrayList<>();
        for (HisPrescriptionMedicine hisPrescriptionMedicine : hisPrescriptionMedicines) {
            HisPrescriptionMedicineTranslate hisPrescriptionMedicineTranslate = new HisPrescriptionMedicineTranslate();
            BeanUtils.copyProperties(hisPrescriptionMedicine, hisPrescriptionMedicineTranslate);
            hisPrescriptionMedicineTranslateArrayList.add(hisPrescriptionMedicineTranslate);
        }
        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisPrescriptionMedicineTranslateList(hisPrescriptionMedicineTranslateArrayList);
        amqpTemplat.convertAndSend("com.ahsj.add.hisPrescriptionMedicine", JsonUtils.serialize(translateModels));
        log.info(JsonUtils.serialize(translateModels));
        log.info("--------------------药方基本明细新增翻译发送结束---------------------------");
        searchIsAvailable();
        return MessageUtil.createMessage(true, "药品添加成功！");
    }

    /**
     * @return core.message.Message
     * @Description 删除药方中的组成药品信息
     * @Params [medicineId]
     * @Author zhushixiang
     * @Date 2019-07-30
     * @Time 15:08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message deleteMedicine(Long[] medicineId) {
        for (int i = 0; i < medicineId.length; i++) {
            hisPrescriptionMedicineMapper.deleteByPrimaryKey(medicineId[i]);
        }
        searchIsAvailable();
        return MessageUtil.createMessage(true, "删除成功。");
    }

    @Override
    public List<TreeEntity> selectTrreCode() {
        return hisPrescriptionMapper.selectTreeCode();
    }

    @Override
    public PageBean<HisPrescription> list(PageBean<HisPrescription> pageEntity) {
        pageEntity.setData(hisPrescriptionMapper.list(pageEntity));
        return pageEntity;
    }

    @Override
    public HisPrescription updateInit(String id) {
        return hisPrescriptionMapper.selectByTreeId(id);
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Description 查询药方
     * @Params [prescriptionPageBean]
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:55
     **/
    @Override
    public PageBean<HisPrescription> listForMedicalRecord(PageBean<HisPrescription> prescriptionPageBean) {
        prescriptionPageBean.setData(CodeHelper.getInstance().setCodeValue(hisPrescriptionMapper.listForMedicalRecord(prescriptionPageBean)));
        return prescriptionPageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Description 查询可用药方（药方中的药品医院有且数量充足，药方处于启用状态）
     * @Params [prescriptionPageBean]
     * @Author zhushixiang
     * @Date 2019-08-27
     * @Time 9:48
     **/
    @Override
    @Transactional(readOnly = false)
    public PageBean<HisPrescription> listByTreatmentPlan(PageBean<HisPrescription> prescriptionPageBean) {
        prescriptionPageBean.setData(CodeHelper.getInstance().setCodeValue(hisPrescriptionMapper.listByTreatmentPlan(prescriptionPageBean)));
        return prescriptionPageBean;
    }

    /**
     * @param prescriptionPageBean
     * @Description 查询可用药方（药方中的药品医院有且数量充足，药方处于启用状态）
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Date 2019/8/28
     * @Time 9:32
     **/
    @Override
    @Transactional(readOnly = false)
    public PageBean<HisPrescription> listByTreatmentPlans(PageBean<HisPrescription> prescriptionPageBean) {
        prescriptionPageBean.setData(CodeHelper.getInstance().setCodeValue(hisPrescriptionMapper.listByTreatmentPlans(prescriptionPageBean)));
        return prescriptionPageBean;
    }

    /**
     * @return void
     * @Description 筛选可用药方，把可用药方的是否可用状态设置为1：可用
     * @Params []
     * @Author zhushixiang
     * @Date 2019-08-27
     * @Time 10:08
     **/
    @Transactional(readOnly = false)
    public void searchIsAvailable() {
        //搜索所有药方
        List<HisPrescription> hisPrescriptionList = hisPrescriptionMapper.selectAll();
        for (int i = 0; i < hisPrescriptionList.size(); i++) {
            //搜索出药方菜单将其设置为不可用
            HisPrescription checkIsParent = hisPrescriptionMapper.selectByTreeId(hisPrescriptionList.get(i).getParentId());
            if (!EmptyUtil.Companion.isNullOrEmpty(checkIsParent))
                checkIsParent.setIsAvailable(2);
            //查询当前药方是否为最后一个药方如果不是，设置为不可用
            List<HisPrescription> checkIsLast = hisPrescriptionMapper.selectByParentId(hisPrescriptionList.get(i).getTreeId());
            if (checkIsLast.size() != 0)
                hisPrescriptionList.get(i).setIsAvailable(2);
            else {
                List<HisPrescriptionMedicine> hisPrescriptionMedicine = hisPrescriptionMedicineMapper.selectByPrescriptionId(hisPrescriptionList.get(i).getTreeId());
                boolean flag = true;//定义标记变量 若药品中有一个不符合要求即跳出终止循环
                if (hisPrescriptionMedicine.size() == 0)
                    hisPrescriptionList.get(i).setIsAvailable(2);
                else {
                    for (int j = 0; j < hisPrescriptionMedicine.size(); j++) {
                        HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPrimaryKey(hisPrescriptionMedicine.get(j).getPharmacyId());
                        if (hisPharmacyDetail.getDisable() == 1 || hisPharmacyDetail.getIsObtained() == 1 || hisPharmacyDetail.getStock() <= 0) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag)
                        hisPrescriptionList.get(i).setIsAvailable(2);
                    else
                        hisPrescriptionList.get(i).setIsAvailable(1);
                }
            }
            hisPrescriptionMapper.updateByPrimaryKeySelective(hisPrescriptionList.get(i));
        }
    }

    /**
     *@Description 批量新增药方
     *@Params [insertParentMenu]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 11:40
    **/
    @Override
    @Transactional(readOnly = false)
    public void saveForMultiple(List<HisPrescription> insertParentMenu) {
        if(insertParentMenu.size()!=0)
            hisPrescriptionMapper.insertBatch(insertParentMenu);
        searchIsAvailable();
    }

    /**
     *@Description 批量更新药方
     *@Params [updatePrescriptionList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 11:55
    **/
    @Override
    @Transactional(readOnly = false)
    public void updateForMultiple(List<HisPrescription> updatePrescriptionList) {
        if(updatePrescriptionList.size()!=0)
            hisPrescriptionMapper.updateBatch(updatePrescriptionList);
        searchIsAvailable();
    }

    /**
     *@Description 查询所有药方
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 14:10
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisPrescription> selectPrescription() {
        return hisPrescriptionMapper.selectPrescription();
    }

    /**
     *@Description
     *@Params [name]
     *@return com.ahsj.hiscore.entity.HisPrescription
     *@Author zhushixiang
     *@Date 2019-09-20
     *@Time 13:25
    **/
    @Override
    @Transactional(readOnly = true)
    public HisPrescription selectByName(HisPrescription hisPrescription)throws Exception {
        return hisPrescriptionMapper.selectByName(hisPrescription);
    }
}
