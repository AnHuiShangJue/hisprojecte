package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.ActivityInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.ahsj.smartparkcore.entity.vo.ActivityInfoVO;
import core.entity.PageBean;
import org.springframework.http.ResponseEntity;

public interface ActivityInfoService {

    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/5
     * @Time 10:41
     * @Return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.ActivityInfo>
     * @Params [activityInfoPageBean]
    **/
    PageBean<ActivityInfo> list(PageBean<ActivityInfo> pageBean)throws Exception;

    /**
     * @Description 新增
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 9:59
     * @Return
     * @Params
    **/

    ResponseEntity<ResultModel> save (ActivityInfoDTO activityInfoDTO)throws Exception;

    /**
     * @Description 更新
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 10:08
     * @Return
     * @Params
    **/

    ResponseEntity<ResultModel> update(ActivityInfoDTO activityInfoDTO)throws Exception;

    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 14:10
     * @Return
     * @Params
    **/

    ResponseEntity<ResultModel>delete(Long[] ids)throws Exception;

    /**
     * @Description 修改启用状态
     * @Author  muxu
     * @Date  2019/10/14
     * @Time 17:26
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [ids]
    **/
    ResponseEntity<ResultModel>enable(Long[] ids)throws Exception;

    /**
     * @Description 
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 16:18
     * @Return 
     * @Params 
    **/
    ActivityInfoVO selectById(Long id)throws Exception;


    /**
     * @Description 活动申请审核成功
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:42
     * @Return
     * @Params
     **/
    ResponseEntity<ResultModel> reviewSuccess(Long id,String remarks)throws Exception;


    /**
     * @Description 活动申请审核失败
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:42
     * @Return
     * @Params
     **/
    ResponseEntity<ResultModel> reviewError(Long id,String remarks)throws Exception;

}
