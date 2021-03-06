package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ActivityPersonnelInfoMapper;
import com.ahsj.smartparkcore.entity.dto.ActivityPersonnelInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.entity.vo.ActivityPersonnelInfoVO;
import com.ahsj.smartparkcore.entity.vo.StationInfoVO;
import com.ahsj.smartparkcore.feign.IUserService;
import com.ahsj.smartparkcore.services.ActivityPersonnelInfoService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

@Service
public class ActivityPersonnelInfoServiceImpl implements ActivityPersonnelInfoService {
    @Autowired
    ActivityPersonnelInfoMapper activityPersonnelInfoMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    SysAttachmentInfoService sysAttachmentInfoService;


    /**
     * @Description list
     * @Author muxu
     * @Date 2019/10/14
     * @Time 13:22
     * @Return
     * @Params
     **/
    @Override
    public PageBean<ActivityPersonnelInfo> list(PageBean<ActivityPersonnelInfo> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(activityPersonnelInfoMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @Description save
     * @Author muxu
     * @Date 2019/9/10
     * @Time 9:53
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [activityPersonnelInfoDTO]
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ActivityPersonnelInfo activityPersonnelInfo = mapper.map(activityPersonnelInfoDTO, ActivityPersonnelInfo.class);
        activityPersonnelInfo.setIsReview(3);
        int check = activityPersonnelInfoMapper.insert(activityPersonnelInfo);
        if (check != 0) {
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT), HttpStatus.OK);
        }
    }


    /**
     * @Description update
     * @Author muxu
     * @Date 2019/9/10
     * @Time 9:53
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [activityPersonnelInfoDTO]
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> update(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ActivityPersonnelInfo activityPersonnelInfo = mapper.map(activityPersonnelInfoDTO, ActivityPersonnelInfo.class);
        int check = activityPersonnelInfoMapper.updateByPrimaryKey(activityPersonnelInfo);
        if (check != 0) {
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE), HttpStatus.OK);
        }
    }


    /**
     * @Description delete
     * @Author muxu
     * @Date 2019/9/10
     * @Time 9:53
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [ids]
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            activityPersonnelInfoMapper.deleteByPrimaryKey(id);
        }
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_DELETE), HttpStatus.OK);
    }

    /**
     * @Description select
     * @Author muxu
     * @Date 2019/9/10
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
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:42
     * @Return
     * @Params
     **/

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> reviewSuccess(Long id, String remarks) throws Exception {
        ActivityPersonnelInfo activityPersonnelInfo = activityPersonnelInfoMapper.selectByPrimaryKey(id);
        activityPersonnelInfo.setIsReview(1);
        activityPersonnelInfo.setRemarks(remarks);
        activityPersonnelInfoMapper.updateByPrimaryKey(activityPersonnelInfo);

        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_REVIEW), HttpStatus.OK);
    }

    /**
     * @Description 活动审核失败
     * @Author muxu
     * @Date 2019/9/11
     * @Time 15:10
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [id]
     **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> reviewError(Long id, String remarks) throws Exception {
        ActivityPersonnelInfo activityPersonnelInfo = activityPersonnelInfoMapper.selectByPrimaryKey(id);
        activityPersonnelInfo.setIsReview(2);
        activityPersonnelInfo.setRemarks(remarks);
        activityPersonnelInfoMapper.updateByPrimaryKey(activityPersonnelInfo);
        return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_REVIEW), HttpStatus.OK);
    }

    /**
     * @Description
     * @Author muxu
     * @Date 2019/10/29
     * @Time 11:32
     * @Return
     * @Params
     **/

    @Override
    @Transactional(readOnly = true)
    public PageBean<ActivityPersonnelInfo> listMyActivity(PageBean<ActivityPersonnelInfo> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(activityPersonnelInfoMapper.listMyActivity(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.ActivityPersonnelInfoVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/30
     * @Time 13:45
     **/
    @Override
    @Transactional(readOnly = true)
    public List<ActivityPersonnelInfoVO> listForView(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception {
        ActivityPersonnelInfo activityPersonnelInfo = new ActivityPersonnelInfo();
        BeanUtils.copyProperties(activityPersonnelInfoDTO,activityPersonnelInfo);
        List<ActivityPersonnelInfoVO> activityPersonnelInfoVOS = activityPersonnelInfoMapper.listForView(activityPersonnelInfo);
        for (ActivityPersonnelInfoVO activityPersonnelInfoVO : activityPersonnelInfoVOS) {
            if (activityPersonnelInfoVO.getIsReview()==1){
                activityPersonnelInfoVO.setIsReviewName(Constants.EXAMINATION_PASSED);
            }
            if (activityPersonnelInfoVO.getIsReview()==2){
                activityPersonnelInfoVO.setIsReviewName(Constants.AUDIT_FAILED);
            }
            if (activityPersonnelInfoVO.getIsReview()==3){
                activityPersonnelInfoVO.setIsReviewName(Constants.UNREVIEWED);
            }
            SysAttachmentInfo sysAttachmentInfo = new SysAttachmentInfo();
            sysAttachmentInfo.setRelateId(activityPersonnelInfoVO.getActivityId());
            sysAttachmentInfo.setRelateKey(Constants.STATIONINFO);
            sysAttachmentInfo.setRelatePage(Constants.LIST);
            List<SysAttachmentInfo> sysAttachmentInfos = sysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfos)) {
                continue;
            }
            SysAttachmentInfo sysAttachmentInfo1 = sysAttachmentInfos.get(0);
            String replace = Constants.LOCALHOST + sysAttachmentInfo1.getLocation().replace(Constants.STATIC, Constants.SMARTPARKCORE);
            activityPersonnelInfoVO.setFilePath(replace);
        }
        return activityPersonnelInfoVOS;
    }
}
