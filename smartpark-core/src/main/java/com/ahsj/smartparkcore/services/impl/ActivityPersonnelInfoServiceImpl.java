package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ActivityPersonnelInfoMapper;
import com.ahsj.smartparkcore.entity.dto.ActivityPersonnelInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import com.ahsj.smartparkcore.services.ActivityPersonnelInfoService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityPersonnelInfoServiceImpl implements ActivityPersonnelInfoService {
    @Autowired
    ActivityPersonnelInfoMapper activityPersonnelInfoMapper;

    /**
     * @Description save
     * @Author  muxu
     * @Date  2019/9/10
     * @Time 9:53
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [activityPersonnelInfoDTO]
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ActivityPersonnelInfo activityPersonnelInfo = mapper.map(activityPersonnelInfoDTO,ActivityPersonnelInfo.class);
        activityPersonnelInfo.setIsReview(1);
        int check = activityPersonnelInfoMapper.insert(activityPersonnelInfo);
        if (check !=0) {
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT),HttpStatus.OK);
        }
    }


    /**
     * @Description update
     * @Author  muxu
     * @Date  2019/9/10
     * @Time 9:53
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [activityPersonnelInfoDTO]
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> update(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ActivityPersonnelInfo activityPersonnelInfo = mapper.map(activityPersonnelInfoDTO,ActivityPersonnelInfo.class);
        int check = activityPersonnelInfoMapper.updateByPrimaryKey(activityPersonnelInfo);
        if (check !=0){
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE),HttpStatus.OK);
        }
    }


    /**
     * @Description delete
     * @Author  muxu
     * @Date  2019/9/10
     * @Time 9:53
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [ids]
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> delete(Long[] ids) throws Exception {
        for (Long id :ids){
            activityPersonnelInfoMapper.deleteByPrimaryKey(id);
        }
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_DELETE),HttpStatus.OK);
    }

    /**
     * @Description select
     * @Author  muxu
     * @Date  2019/9/10
     * @Time 10:57
     * @Return
     * @Params
    **/
    @Override
    @Transactional(readOnly = true)
    public ActivityPersonnelInfo selectById(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(activityPersonnelInfoMapper.selectByPrimaryKey(id));
    }
    
    /**
     * @Description 活动申请审核成功
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:42
     * @Return 
     * @Params 
    **/

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> reviewSuccess(Long id) throws Exception {
        ActivityPersonnelInfo activityPersonnelInfo = activityPersonnelInfoMapper.selectByPrimaryKey(id);
        activityPersonnelInfo.setIsReview(2);
        activityPersonnelInfoMapper.updateByPrimaryKey(activityPersonnelInfo);

        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_REVIEW), HttpStatus.OK);
    }

    /**
     * @Description 活动审核失败
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 15:10
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [id]
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> reviewError(Long id) throws Exception {
        ActivityPersonnelInfo activityPersonnelInfo = activityPersonnelInfoMapper.selectByPrimaryKey(id);
        activityPersonnelInfo.setIsReview(3);
        activityPersonnelInfoMapper.updateByPrimaryKey(activityPersonnelInfo);
        return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_REVIEW), HttpStatus.OK);
    }
}
