package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ReserveSiteMapper;
import com.ahsj.smartparkcore.entity.dto.ReserveSiteDTO;
import com.ahsj.smartparkcore.entity.po.ReserveSite;
import com.ahsj.smartparkcore.services.ReserveSiteService;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/11
 * @Time 17:12
 **/
@Service
public class ReserveSiteServiceImpl implements ReserveSiteService {

    @Autowired
    ReserveSiteMapper reserveSiteMapper;
    /**
     * className ReserveSiteServiceImpl
     *
     * @author dingli
     * @date 2019/9/11 17:12
     */

    /**
     * @Description 场地预约新增
     * @Params: [reserveSite]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/11
     * @Time 17:13
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(ReserveSiteDTO reserveSiteDTO) throws Exception {
        List<ReserveSite> list = reserveSiteMapper.selectTime(reserveSiteDTO);//查询是否有时间冲突
        DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
        ReserveSite reserveSite = mapper.map(reserveSiteDTO, ReserveSite.class);
        if (EmptyUtil.Companion.isNullOrEmpty(list)) {
            reserveSite.setIsCancel(1);//预定
            reserveSite.setIsPay(1);//付费
            reserveSite.setIsCancel(2);//未取消
            reserveSite.setIsRefund(2);//未退款
            reserveSite.setIsAudit(0);//未审核
            reserveSiteMapper.insert(reserveSite);
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT, "活动时间冲突"), HttpStatus.OK);
        }
    }

    /**
     * @Description 场地预约修改
     * @Params: [reserveSiteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 9:42
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> update(ReserveSiteDTO reserveSiteDTO) throws Exception {
        ReserveSite rs = reserveSiteMapper.selectByPrimaryKey(reserveSiteDTO.getId()); //查询库里的当前预定场地信息
        List<ReserveSite> list = reserveSiteMapper.selectTime(reserveSiteDTO);//查询是否有时间冲突
        DozerBeanMapper mapper = new DozerBeanMapper(); //对象转换组件
        ReserveSite reserveSite = mapper.map(reserveSiteDTO, ReserveSite.class);
        if (reserveSiteDTO.getSiteId() == rs.getSiteId()) {//没有修改场地
            if (EmptyUtil.Companion.isNullOrEmpty(list) || (list.size() == 1 && list.get(0).getId() == rs.getId())) { //活动时间不冲突，或者是跟库里本身的冲突
                reserveSiteMapper.updateByPrimaryKey(reserveSite);
                return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE, "活动时间冲突"), HttpStatus.OK);
            }
        } else { //修改了场地
            if (EmptyUtil.Companion.isNullOrEmpty(list)) {
                reserveSiteMapper.updateByPrimaryKey(reserveSite);
                return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE, "活动时间冲突"), HttpStatus.OK);
            }
        }
    }

    /**
     * @Description 删除场地
     * @Params: [reserveSiteDTO]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 10:14
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> delete(ReserveSiteDTO reserveSiteDTO) throws Exception {
        reserveSiteMapper.deleteByPrimaryKey(reserveSiteDTO.getId());
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_DELETE), HttpStatus.OK);
    }

    /**
     * @Description 分页查询场地预订信息
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Date 2019/9/12
     * @Time 10:20
     **/

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResultModel> list(PageBean<ReserveSiteDTO> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(reserveSiteMapper.list(pageBean)));
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_SELECT, pageBean), HttpStatus.OK);
    }


}
