package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.ActivityPersonnelInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import com.ahsj.smartparkcore.entity.vo.ActivityPersonnelInfoVO;
import core.entity.PageBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActivityPersonnelInfoService {

    /**
     * @Description list
     * @Author muxu
     * @Date 2019/10/14
     * @Time 13:21
     * @Return
     * @Params
     **/
    PageBean<ActivityPersonnelInfo> list(PageBean<ActivityPersonnelInfo> pageBean) throws Exception;

    /**
     * @Description save
     * @Author muxu
     * @Date 2019/9/10
     * @Time 9:50
     * @Return
     * @Params
     **/
    ResponseEntity<ResultModel> save(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception;

    /**
     * @Description update
     * @Author muxu
     * @Date 2019/9/10
     * @Time 9:51
     * @Return
     * @Params
     **/

    ResponseEntity<ResultModel> update(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception;

    /**
     * @Description delete
     * @Author muxu
     * @Date 2019/9/10
     * @Time 9:52
     * @Return
     * @Params
     **/
    ResponseEntity<ResultModel> delete(Long[] ids) throws Exception;

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/10
     * @Time 10:56
     * @Return
     * @Params
     **/
    ActivityPersonnelInfo selectById(Long id) throws Exception;

    /**
     * @Description 活动申请审核
     * @Author muxu
     * @Date 2019/9/11
     * @Time 14:43
     * @Return
     * @Params
     **/

    ResponseEntity<ResultModel> reviewSuccess(Long id, String remarks) throws Exception;

    /**
     * @Description
     * @Author muxu
     * @Date 2019/9/11
     * @Time 15:09
     * @Return
     * @Params
     **/

    ResponseEntity<ResultModel> reviewError(Long id, String remarks) throws Exception;

    /**
     * @Description
     * @Author muxu
     * @Date 2019/10/29
     * @Time 11:31
     * @Return
     * @Params
     **/

    PageBean<ActivityPersonnelInfo> listMyActivity(PageBean<ActivityPersonnelInfo> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.ActivityPersonnelInfoVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/30
     * @Time 13:45
     **/
    List<ActivityPersonnelInfoVO> listForView(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception;
}
