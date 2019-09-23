package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.ActivityInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityInfo;
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
    ResponseEntity<ResultModel> list (PageBean<ActivityInfo> pageBean)throws Exception;

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
     * @Description 
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 16:18
     * @Return 
     * @Params 
    **/
    ActivityInfo selectById(Long id)throws Exception;

}
