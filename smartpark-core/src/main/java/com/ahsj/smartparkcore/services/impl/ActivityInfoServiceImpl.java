package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ActivityInfoMapper;
import com.ahsj.smartparkcore.entity.dto.ActivityInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.ahsj.smartparkcore.services.ActivityInfoService;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {
    @Autowired
    ActivityInfoMapper activityInfoMapper;

    
    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/5
     * @Time 10:53
     * @Return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.ActivityInfo>
     * @Params [pageBean]
    **/
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResultModel> list(PageBean<ActivityInfo> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(activityInfoMapper.list(pageBean)));
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_SELECT), HttpStatus.OK);
    }

    /**
     * @Description 新增
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 9:58
     * @Return
     * @Params
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> save(ActivityInfoDTO activityInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        ActivityInfo activityInfo = mapper.map(activityInfoDTO,ActivityInfo.class);
        // mapper.map(source, destinationClass);
        // 构造新的ActivityInfo实例对象，通过source对象中的字段内容.映射到ActivityInfoDTO.class实例对象中，并返回新的ActivityInfoDTO.class实例对象。
        activityInfo.setIsEnable(2);
        int check = activityInfoMapper.insert(activityInfo);
        if (check !=0){
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_INSERT),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INSERT), HttpStatus.OK);
        }
    }

    /**
     * @Description 更新
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 10:09
     * @Return
     * @Params
    **/
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> update(ActivityInfoDTO activityInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //DTO转化为PO 存入数据库
        ActivityInfo activityInfo = mapper.map(activityInfoDTO,ActivityInfo.class);
        // mapper.map(source, destinationClass);
        // 构造新的ActivityInfo实例对象，通过source对象中的字段内容.映射到ActivityInfoDTO.class实例对象中，并返回新的ActivityInfoDTO.class实例对象。
        int check = activityInfoMapper.updateByPrimaryKey(activityInfo);
        if (check !=0){
            return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_UPDATE),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_UPDATE), HttpStatus.OK);
        }
    }

    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 14:12
     * @Return
     * @Params
    **/

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity<ResultModel> delete(Long[] ids) throws Exception {
        for (Long id : ids){
            activityInfoMapper.deleteByPrimaryKey(id);
        }
        return new ResponseEntity<>(new ResultModel(ResultStatus.SUCCESS_DELETE),HttpStatus.OK);
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 16:32
     * @Return
     * @Params
    **/
    @Override
    @Transactional(readOnly = true)
    public ActivityInfo selectById(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(activityInfoMapper.selectByPrimaryKey(id));
    }
}
