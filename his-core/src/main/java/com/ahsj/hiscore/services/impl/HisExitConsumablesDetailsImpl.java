package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisConsumablesDetailsMapper;
import com.ahsj.hiscore.dao.HisEnterConsumablesDetailsMapper;
import com.ahsj.hiscore.dao.HisExitConsumablesDetailsMapper;
import com.ahsj.hiscore.dao.HisHospitalConsumablesDetailsMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisExitConsumablesDetailsService;
import com.ahsj.hiscore.services.HisHospitalConsumablesDetailsService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class HisExitConsumablesDetailsImpl implements HisExitConsumablesDetailsService {

    private static final Integer HISHOSPITAL_CONSUMABLESDETAILS_IS_OUTBOUND = 1;


    @Autowired
    HisExitConsumablesDetailsMapper hisExitConsumablesDetailsMapper;

    @Autowired
    HisConsumablesDetailsMapper hisConsumablesDetailsMapper;

    @Autowired
    HisHospitalConsumablesDetailsService hisHospitalConsumablesDetailsService;

    @Autowired
    HisEnterConsumablesDetailsMapper hisEnterConsumablesDetailsMapper;

    @Autowired
    HisHospitalConsumablesDetailsMapper hisHospitalConsumablesDetailsMapper;

    @Autowired
    ITranslateService iTranslateService;


    @Override
    @Transactional(readOnly = false)
    public Message exitConsumablesSmart(Long[] ids, Integer[] numbers, String personInCharge, Date expectedTime) throws Exception {
        //生成耗材出库记录编号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String createdate = sdf.format(date);
        Arrays.stream(ids).forEach(System.out::println);
        if (ids.length == 0) {
            return MessageUtil.createMessage(false, "请选择要出库的耗材");
        } else {
            //循环ids
            for (int i = 0; i < ids.length; ++i) {
                //根据 主键id，查找 库存表 的记录,保存在 库存对象 中
                HisConsumablesDetails hisConsumablesDetails = hisConsumablesDetailsMapper.selectByPrimaryKey(ids[i]);
                //判断 要出库数量（numbers[i]) 与 当前库存量 的大小
                if (numbers[i] > hisConsumablesDetails.getStock()) {
                    //出库量 > 库存量时，出库失败
                    return MessageUtil.createMessage(false, "库存不足");
                } else {
                    //库存量足够时
                    hisConsumablesDetails.setStock(hisConsumablesDetails.getStock() - numbers[i]);
                    //更新库存表的数据
                    hisConsumablesDetailsMapper.updateByPrimaryKey(hisConsumablesDetails);

                    //当前要出库的数量
                    int remain = numbers[i];

                    //new 一个 出库详情 对象
                    HisExitConsumablesDetails hisExitConsumablesDetails = new HisExitConsumablesDetails();

                    //添加数据到出库表
                    //循环出库，优先出库有效期较早的耗材
                    //取出该耗材对应的入库表的记录
                    List<HisEnterConsumablesDetails> hisEnterConsumablesDetailsList = hisEnterConsumablesDetailsMapper.selectByConsumablesIdOrder(hisConsumablesDetails.getConsumablesId());

                    for (int j = 0; remain > 0; ++j) {
                        //给出库记录添加数据
                        hisExitConsumablesDetails.setConsumablesId(hisEnterConsumablesDetailsList.get(j).getConsumablesId());
                        hisExitConsumablesDetails.setName(hisEnterConsumablesDetailsList.get(j).getName());
                        hisExitConsumablesDetails.setType(hisEnterConsumablesDetailsList.get(j).getType());
                        hisExitConsumablesDetails.setSpec(hisEnterConsumablesDetailsList.get(j).getSpec());
                        hisExitConsumablesDetails.setPrice(hisEnterConsumablesDetailsList.get(j).getPrice());

                        //判断要出库的量在当前 入库记录 的数量的大小，如果出库较大
                        if (remain > hisEnterConsumablesDetailsList.get(j).getSurplus()) {

                            //surplus不为零时才进行一下操作
                            if (hisEnterConsumablesDetailsList.get(j).getSurplus() != 0) {
                                //插入出库记录到出库表
                                hisExitConsumablesDetails.setValidityPeriod(hisEnterConsumablesDetailsList.get(j).getValidityPeriod());
                                hisExitConsumablesDetails.setExitCount(hisEnterConsumablesDetailsList.get(j).getSurplus());
                                //更新出库量
                                remain = remain - hisEnterConsumablesDetailsList.get(j).getSurplus();
                                //设置该条记录的可用余量为0
                                hisEnterConsumablesDetailsList.get(j).setSurplus(0);
                                //更新入库表
                                hisEnterConsumablesDetailsMapper.updateByPrimaryKey(hisEnterConsumablesDetailsList.get(j));
                                hisExitConsumablesDetails.setExpectedTime(expectedTime);
                                hisExitConsumablesDetails.setPersonInCharge(personInCharge);
                                hisExitConsumablesDetails.setExitNumber("CKJL" + createdate);
                                hisExitConsumablesDetailsMapper.insert(hisExitConsumablesDetails);
                            }

                        } else {//只需要一次出库
                            //更新入库记录的可用余额
                            hisEnterConsumablesDetailsList.get(j).setSurplus(hisEnterConsumablesDetailsList.get(j).getSurplus() - remain);
                            hisEnterConsumablesDetailsMapper.updateByPrimaryKey(hisEnterConsumablesDetailsList.get(j));
                            //插入出库记录到出库表
                            hisExitConsumablesDetails.setValidityPeriod(hisEnterConsumablesDetailsList.get(j).getValidityPeriod());
                            hisExitConsumablesDetails.setExitCount(remain);
                            remain = 0;
                            hisExitConsumablesDetails.setExpectedTime(expectedTime);
                            hisExitConsumablesDetails.setPersonInCharge(personInCharge);
                            hisExitConsumablesDetails.setExitNumber("CKJL" + createdate);
                            hisExitConsumablesDetailsMapper.insert(hisExitConsumablesDetails);
                        }
                    }
                }

            }

            return MessageUtil.createMessage(true, "出库成功");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids, numbers, personInCharge, expectedTime]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 10:23
     **/
    @Override
    @Transactional(readOnly = false)
    public Message exitConsumablesSmarts(Long[] ids, Integer[] numbers, String personInCharge, Date expectedTime) throws Exception {
        //生成耗材出库记录编号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String createdate = sdf.format(date);

        Arrays.stream(ids).forEach(System.out::println);
        if (ids.length == 0) {
            return MessageUtil.createMessage(false, "请选择要出库的耗材");
        } else {
            //循环ids
            for (int i = 0; i < ids.length; ++i) {
                //根据 主键id，查找 库存表 的记录,保存在 库存对象 中
                HisHospitalConsumablesDetails hisHospitalConsumablesDetails = hisHospitalConsumablesDetailsService.selectByPrimaryKey(ids[i]);

                HisConsumablesDetails hisConsumablesDetails = hisConsumablesDetailsMapper.selectByPrimaryKey(hisHospitalConsumablesDetails.getHisConsumablesDetailsId());
                if (hisHospitalConsumablesDetails.getIsOutbound() == 1) {
                    return MessageUtil.createMessage(false, "耗材名为 : " + hisConsumablesDetails.getName() + ", 已出库，请重新选择出库耗材");
                }
                //判断 要出库数量（numbers[i]) 与 当前库存量 的大小
                if (numbers[i] > hisConsumablesDetails.getStock()) {
                    //出库量 > 库存量时，出库失败
                    return MessageUtil.createMessage(false, "耗材名为 : " + hisConsumablesDetails.getName() + ", 库存不足");
                } else {
                    //库存量足够时
                    hisConsumablesDetails.setStock(hisConsumablesDetails.getStock() - numbers[i]);
                    //更新库存表的数据
                    hisConsumablesDetailsMapper.updateByPrimaryKey(hisConsumablesDetails);

                    //当前要出库的数量
                    int remain = numbers[i];

                    //new 一个 出库详情 对象
                    HisExitConsumablesDetails hisExitConsumablesDetails = new HisExitConsumablesDetails();

                    //添加数据到出库表
                    //循环出库，优先出库有效期较早的耗材
                    //取出该耗材对应的入库表的记录
                    List<HisEnterConsumablesDetails> hisEnterConsumablesDetailsList = hisEnterConsumablesDetailsMapper.selectByConsumablesIdOrder(hisConsumablesDetails.getConsumablesId());

                    for (int j = 0; remain > 0; ++j) {
                        //给出库记录添加数据
                        hisExitConsumablesDetails.setConsumablesId(hisEnterConsumablesDetailsList.get(j).getConsumablesId());
                        hisExitConsumablesDetails.setName(hisEnterConsumablesDetailsList.get(j).getName());
                        hisExitConsumablesDetails.setType(hisEnterConsumablesDetailsList.get(j).getType());
                        hisExitConsumablesDetails.setSpec(hisEnterConsumablesDetailsList.get(j).getSpec());
                        hisExitConsumablesDetails.setPrice(hisEnterConsumablesDetailsList.get(j).getPrice());

                        //判断要出库的量在当前 入库记录 的数量的大小，如果出库较大
                        if (remain > hisEnterConsumablesDetailsList.get(j).getSurplus()) {

                            //surplus不为零时才进行一下操作
                            if (hisEnterConsumablesDetailsList.get(j).getSurplus() != 0) {

                                //插入出库记录到出库表
                                hisExitConsumablesDetails.setValidityPeriod(hisEnterConsumablesDetailsList.get(j).getValidityPeriod());
                                hisExitConsumablesDetails.setExitCount(hisEnterConsumablesDetailsList.get(j).getSurplus());
                                //更新出库量
                                remain = remain - hisEnterConsumablesDetailsList.get(j).getSurplus();
                                //设置该条记录的可用余量为0
                                hisEnterConsumablesDetailsList.get(j).setSurplus(0);
                                //更新入库表
                                hisEnterConsumablesDetailsMapper.updateByPrimaryKey(hisEnterConsumablesDetailsList.get(j));
                                hisExitConsumablesDetails.setExpectedTime(expectedTime);
                                hisExitConsumablesDetails.setPersonInCharge(personInCharge);
                                hisExitConsumablesDetails.setExitNumber("CKJL" + createdate);
                                hisExitConsumablesDetails.setHisHospitalManagerId(hisHospitalConsumablesDetails.getMedicalRecordNumber());
                                hisExitConsumablesDetailsMapper.insert(hisExitConsumablesDetails);
                            }

                        } else {//只需要一次出库
                            //更新入库记录的可用余额
                            hisEnterConsumablesDetailsList.get(j).setSurplus(hisEnterConsumablesDetailsList.get(j).getSurplus() - remain);
                            hisEnterConsumablesDetailsMapper.updateByPrimaryKey(hisEnterConsumablesDetailsList.get(j));
                            //插入出库记录到出库表
                            hisExitConsumablesDetails.setValidityPeriod(hisEnterConsumablesDetailsList.get(j).getValidityPeriod());
                            hisExitConsumablesDetails.setExitCount(remain);
                            remain = 0;
                            hisExitConsumablesDetails.setExpectedTime(expectedTime);
                            hisExitConsumablesDetails.setPersonInCharge(personInCharge);
                            hisExitConsumablesDetails.setExitNumber("CKJL" + createdate);
                            hisExitConsumablesDetails.setHisHospitalManagerId(hisHospitalConsumablesDetails.getMedicalRecordNumber());
                            hisExitConsumablesDetailsMapper.insert(hisExitConsumablesDetails);
                        }
                    }
                }

            }
            List<HisHospitalConsumablesDetails> list = new ArrayList<>();
            for (Long id : ids) {
                HisHospitalConsumablesDetails hisHospitalConsumablesDetails = hisHospitalConsumablesDetailsMapper.selectByPrimaryKey(id);
                list.add(hisHospitalConsumablesDetails);
            }

            hisHospitalConsumablesDetailsService.updateHisHospitalConsumablesDetails(list);
            return MessageUtil.createMessage(true, "出库成功");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public List<HisExitConsumablesDetails> getExitConsumabes(PageBean<HisExitConsumablesDetails> pageBean) throws Exception {
        List<HisExitConsumablesDetails> hisExitConsumablesDetailsList = hisExitConsumablesDetailsMapper.listByNumber(pageBean);

        for (HisExitConsumablesDetails h : hisExitConsumablesDetailsList) {
            Translate translate = new Translate();
            translate.setTranslateId(h.getConsumablesId());
            translate.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                for (Translate t : translates) {
                    if (t.getTranslateChina().equals(h.getName())) {
                        h.settName(t.getTranslateKhmer()); //赋翻译字段                        }
                    }
                    if (t.getTranslateChina().equals(h.getSpec())) {
                        h.settSpec(t.getTranslateKhmer());
                    }
                }
            }

        }
        return hisExitConsumablesDetailsList;
    }


    @Override
    @Transactional(readOnly = false)
    public Message exitConsumables(Long[] ids, Integer[] numbers, Double[] prices, String personInCharge, Date expectedTime) throws Exception {
        //生成耗材出库记录编号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String createdate = sdf.format(date);

        //此出库操作默认同一药品在入库记录表中有效期不会相同，若需要改动需要多加几次判断
        for (int i = 0; i < ids.length; i++) {
            double unitSalePrice = 0;
            //根据入库表id获得此次要出库药品的相关信息(从入库表中获得)
            HisEnterConsumablesDetails hisEnterConsumablesDetails = hisEnterConsumablesDetailsMapper.selectByPrimaryKey(ids[i]);
            //单条记录的价格，此次出库的该药品的总价
            unitSalePrice = numbers[i] * prices[i];
            //向出库记录中设置一些从入库表中获得的基本信息
            HisExitConsumablesDetails hisExitConsumablesDetails = new HisExitConsumablesDetails();
            hisExitConsumablesDetails.setConsumablesId(hisEnterConsumablesDetails.getConsumablesId());
            hisExitConsumablesDetails.setName(hisEnterConsumablesDetails.getName());
            hisExitConsumablesDetails.setSpec(hisEnterConsumablesDetails.getSpec());
            hisExitConsumablesDetails.setType(hisEnterConsumablesDetails.getType());
            hisExitConsumablesDetails.setValidityPeriod(hisEnterConsumablesDetails.getValidityPeriod());
            //向出库记录设置出库数目与药品项总价
            hisExitConsumablesDetails.setPrice(unitSalePrice);
            //防止药品数量不足以出库所做的判断，此步在前端应已经设置过一次拦截,intValue()将Long型转化为integer型数据
            if (numbers[i] > hisEnterConsumablesDetails.getSurplus())
                return MessageUtil.createMessage(false, "药品余量不足，请重新输入");
            hisExitConsumablesDetails.setExitCount(numbers[i]);
            //当设置药品出库数量后，入库的余量以及药库库存需要级联更改 同时增加药库中药品的售出数目
            hisEnterConsumablesDetails.setSurplus(hisEnterConsumablesDetails.getSurplus() - numbers[i]);
            List<HisConsumablesDetails> hisConsumablesDetailsList = hisConsumablesDetailsMapper.selectByConsumablesId(hisEnterConsumablesDetails.getConsumablesId());
            hisConsumablesDetailsList.get(0).setStock(hisConsumablesDetailsList.get(0).getStock() - numbers[i]);
            //保存出库记录，更新入库表中本次库存剩余数目以及药库中的库存
            hisExitConsumablesDetails.setExpectedTime(expectedTime);
            hisExitConsumablesDetails.setPersonInCharge(personInCharge);
            hisExitConsumablesDetails.setExitNumber("CKJL" + createdate);
            hisExitConsumablesDetailsMapper.insert(hisExitConsumablesDetails);
            hisEnterConsumablesDetailsMapper.updateByPrimaryKeySelective(hisEnterConsumablesDetails);
            hisConsumablesDetailsMapper.updateByPrimaryKeySelective(hisConsumablesDetailsList.get(0));
        }
        return MessageUtil.createMessage(true, "药品出库成功");
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisExitConsumablesDetails> list(PageBean<HisExitConsumablesDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisExitConsumablesDetailsMapper.list(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisExitConsumablesDetails> listByNumber(PageBean<HisExitConsumablesDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisExitConsumablesDetailsMapper.listByNumber(pageBean)));
        return pageBean;
    }

    /**
     * @Description 耗材盘点
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisExitConsumablesDetails>
     * @Date 2019/9/19
     * @Time 18:05
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisExitConsumablesDetails> consumableInventory(PageBean<HisExitConsumablesDetails> pageBean) throws Exception {
        return null;
    }

}
