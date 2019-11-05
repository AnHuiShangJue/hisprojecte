package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisPatientInfoMapper;
import com.ahsj.hiscore.dao.HisRegisteredMapper;
import com.ahsj.hiscore.dao.HisRegisteredpayMapper;
import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.entity.HisRegistered;
import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.entity.RegisterdCount;
import com.ahsj.hiscore.services.HisPatientService;
import com.ahsj.hiscore.services.HisRegisteredService;
import com.ahsj.hiscore.services.HisRegisteredpayService;
import core.entity.PageBean;
import core.message.BoolMessage;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HisRegisteredServiceImpl implements HisRegisteredService {

    @Autowired
    private HisPatientInfoMapper hisPatientInfoMapper;

    @Autowired
    private HisRegisteredMapper hisRegisteredMapper;

    @Autowired
    private HisPatientService hisPatientService;

    @Autowired
    private HisRegisteredpayMapper hisRegisteredpayMapper;

    @Autowired
    private HisRegisteredpayService hisRegisteredpayService;


    /**
     * @return
     * @Description 患者挂号
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/13
     * @Time 20:27
     **/
    @Override
    @Transactional(readOnly = false)
    public Message registered(HisRegistered hisRegistered, HisPatientInfo hisPatientInfo, HisRegisteredpay hisRegisteredpay) throws Exception {

        //设置挂号类型
        BigDecimal sumPrice = new BigDecimal(hisRegisteredpay.getPaidAmount().toString());
        if(sumPrice.compareTo(hisRegisteredpay.getAmountReceivable()) ==-1){
            return MessageUtil.createMessage(false,"总金额小于应收金额，请重新输入!!!");
        }
        //判断该身份证是否存在，不存在则在患者信息表添加患者信息
        HisPatientInfo tmp = hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard());
        if (EmptyUtil.Companion.isNullOrEmpty(tmp)) {
            BoolMessage message = (BoolMessage) hisPatientService.saveOrUpdate(hisPatientInfo);
            if (!message.isSuccess()) return MessageUtil.createMessage(false, "挂号失败！病人建档发生错误！请检查所填入的信息是否合法或者联系管理员");
        }           //获取当前日期转成string类型的
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String createdate = sdf.format(date);
        int count = hisRegisteredMapper.selectNumbCount(createdate) + 1;
        //编号
        String number = createdate + String.format("%03d", count);
        number = "R" + number;
        //序号
        String serial = String.valueOf(count);
        hisRegistered.setNumber(number);
        hisRegistered.setSerial(Long.valueOf(serial));
        hisRegistered.setIsObsolete(2);
        hisRegistered.setIsPay(2);
        hisRegistered.setIsSettlement(2);
        hisRegistered.setIsPrint(2);
        hisRegistered.setIsReceived(2);
        Long PatientInfoId = hisPatientInfoMapper.selectByIdcard(hisPatientInfo.getIdcard()).getId();
        if (EmptyUtil.Companion.isNullOrEmpty(PatientInfoId))
            return MessageUtil.createMessage(false, "挂号失败！请检查所填入的信息是否合法或者联系管理员");
        hisRegistered.setPatientId(PatientInfoId);
        if(hisRegistered.getRegisteredCategory() == 1){
            hisRegistered.setRegisterType(2);
        }else if(hisRegistered.getRegisteredCategory() == 2){
            hisRegistered.setRegisterType(1);
        }
        hisRegisteredMapper.insert(hisRegistered);
        //如果实收金额不为空，则进行挂号支付逻辑的处理
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpay.getPaidAmount())
                && !EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpay.getRetrieveAmount())
                && !EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpay.getPayType())
        ) {
            hisRegisteredpay.setRegisteredId(hisRegistered.getId());
            hisRegisteredpayService.saveOrUpdate(hisRegisteredpay);
            hisRegistered.setIsPay(1);
            hisRegisteredMapper.updateByPrimaryKey(hisRegistered);
        }
        return MessageUtil.createMessage(true, "挂号成功！");
    }


    /**
     * @return
     * @Description 是否付费
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/14
     * @Time 12:17
     **/
    @Override
    @Transactional(readOnly = false)
    public Message isPay(HisRegistered hisRegistered) throws Exception {
        HisRegistered tmp = hisRegisteredMapper.selectByPrimaryKey(hisRegistered.getId());
        if (EmptyUtil.Companion.isNullOrEmpty(tmp)) return MessageUtil.createMessage(false, "该挂号单不存在！");
        if (tmp.getIsObsolete().equals(1)) return MessageUtil.createMessage(false, "该挂号单已被作废！");
        if (tmp.getIsPay().equals(1)) return MessageUtil.createMessage(false, "该挂号单已经付费过了！");
        tmp.setIsPay(2);
        hisRegisteredMapper.updateByPrimaryKey(tmp);
        return MessageUtil.createMessage(true, "付费成功！");
    }

    /**
     * @return
     * @Description 付费接口的实现
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/14
     * @Time 12:30
     **/
    @Override
    @Transactional(readOnly = false)
    public Message payOperation(HisRegisteredpay hisRegisteredpay) throws Exception {
        HisRegistered hisRegistered = hisRegisteredMapper.selectByPrimaryKey(hisRegisteredpay.getRegisteredId());
        HisRegisteredpay hisRegisteredpay1 = hisRegisteredpayMapper.selectByRegisteredId(hisRegisteredpay.getRegisteredId());
        if (hisRegistered.getIsPay() == 0) {
            hisRegisteredpay1.setPaidAmount(hisRegisteredpay.getPaidAmount());
            hisRegisteredpay1.setAmountReceivable(hisRegisteredpay.getAmountReceivable());
            hisRegisteredpay.setRetrieveAmount(hisRegisteredpay.getPaidAmount().subtract(hisRegisteredpay.getAmountReceivable()));
            hisRegisteredpay1.setRetrieveAmount(hisRegisteredpay.getRetrieveAmount());
            hisRegisteredpayMapper.updateByPrimaryKey(hisRegisteredpay1);
            hisRegistered.setIsPay(1);
            hisRegisteredMapper.updateByPrimaryKey(hisRegistered);
            return MessageUtil.createMessage(true, "付费成功");
        } else {
            return MessageUtil.createMessage(false, "已付费");
        }
    }

    /**
     * @return
     * @Description 作废操作
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/14
     * @Time 15:37
     **/
    @Override
    @Transactional(readOnly = false)
    public Message isObsolete(Long[] id) {
        List<HisRegistered> hisRegisteredList = hisRegisteredMapper.selectForObsolete(id);
        if (EmptyUtil.Companion.isNullOrEmpty(hisRegisteredList) || EmptyUtil.Companion.isNullOrEmpty(hisRegisteredList.size()))
            return MessageUtil.createMessage(false, "作废失败！挂号记录不存在！");

        for (HisRegistered tmp :
                hisRegisteredList) {
            tmp.setIsObsolete(1);
        }
        hisRegisteredMapper.updateForObsolete(hisRegisteredList);
        return MessageUtil.createMessage(true, "作废成功");
    }

    /**
     * @return
     * @Description 打印接口的实现
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/15
     * @Time 15:05
     **/
    @Override
    @Transactional(readOnly = false)
    public Message print(Long id) {
        HisRegistered hisRegistered = hisRegisteredMapper.selectByPrimaryKey(id);
        if (hisRegistered == null) {
            return MessageUtil.createMessage(false, "该用户不存在");
        } else if (hisRegistered != null && hisRegistered.getIsPay() == 0) {
            return MessageUtil.createMessage(false, "请先缴费");
        } else {
            return MessageUtil.createMessage(true, "打印成功");
        }
    }

    /**
     * @return
     * @Description 分页列表查询
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/21
     * @Time 15:41
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRegistered> list(PageBean<HisRegistered> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRegisteredMapper.list(pageBean)));
        return pageBean;
    }

    @Override
    public HisRegistered selectById(Long id) {
        return hisRegisteredMapper.selectByPrimaryKey(id);
    }


    @Override
    public RegisterdCount countForToday(Long id) {
        return hisRegisteredMapper.countForToday(id);
    }

    @Override
    public HisRegistered selectByPatientId(Long patientId) {
        return hisRegisteredMapper.selectBypatientid(patientId);
    }

    /**
     * @return com.ahsj.hiscore.entity.HisRegistered
     * @Description 查看挂号细节
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019/7/1
     * @Time 4:18 PM
     **/
    @Override
    @Transactional(readOnly = true)
    public HisRegistered detailInit(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisRegisteredMapper.detailInit(id));
    }

    /**
     * @Description 时间段挂号数量
     * @Author   muxu
     * @Date 2019/7/28
     * @Time 18:25
     * @Return com.ahsj.hiscore.entity.RegisterdCount
     * @Params [highTime, lowTime]
    **/
    @Override
    public Integer[] selectByReistDataCount(Date createDate)throws Exception{
        Integer[] outs = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (Integer i : outs) {
            i = new Integer(0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(Calendar.DAY_OF_MONTH,1);

        //取到当前日期的数据
        List<HisRegistered> hisRegisteredList = hisRegisteredMapper.selectByReistDataCount(createDate,calendar.getTime());
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRegisteredList) && hisRegisteredList.size() != 0) {
            for (HisRegistered hisRegistered1 : hisRegisteredList) {
                Calendar date = Calendar.getInstance();
                date.setTime(hisRegistered1.getCreateDate());
                int hour = date.get(Calendar.HOUR_OF_DAY);
                switch (hour) {
                    case 0:
                        outs[1]++;
                        break;
                    case 1:
                        outs[2]++;
                        break;
                    case 2:
                        outs[3]++;
                        break;
                    case 3:
                        outs[4]++;
                        break;
                    case 4:
                        outs[5]++;
                        break;
                    case 5:
                        outs[6]++;
                        break;
                    case 6:
                        outs[7]++;
                        break;
                    case 7:
                        outs[8]++;
                        break;
                    case 8:
                        outs[9]++;
                        break;
                    case 9:
                        outs[10]++;
                        break;
                    case 10:
                        outs[11]++;
                        break;
                    case 11:
                        outs[12]++;
                        break;
                    case 12:
                        outs[13]++;
                        break;
                    case 13:
                        outs[14]++;
                        break;
                    case 14:
                        outs[15]++;
                        break;
                    case 15:
                        outs[16]++;
                        break;
                    case 16:
                        outs[17]++;
                        break;
                    case 17:
                        outs[18]++;
                        break;
                    case 18:
                        outs[19]++;
                        break;
                    case 19:
                        outs[20]++;
                        break;
                    case 20:
                        outs[21]++;
                        break;
                    case 21:
                        outs[22]++;
                        break;
                    case 22:
                        outs[23]++;
                        break;
                    case 23:
                        outs[0]++;
                        break;
                    default:
                        break;
                }
            }
        }
        return outs;
    }

    /**
     *@Description 根据挂号编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisRegistered
     *@Author zhushixiang
     *@Date 2019-09-09
     *@Time 17:36
    **/
    @Override
    @Transactional(readOnly = true)
    public HisRegistered selectByNumber(String number) throws Exception {
        return hisRegisteredMapper.selectBynumber(number);
    }
}

