package com.ahsj.smartparkcore.controller.activityinfo;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ActivityInfoMapper;
import com.ahsj.smartparkcore.entity.dto.ActivityInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.ahsj.smartparkcore.services.ActivityInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/api/activityInfo")
public class ActivityInfoController extends BaseController {

    @Autowired
    ActivityInfoService activityInfoService;

    @Autowired
    ActivityInfoMapper activityInfoMapper;

    
    /**
     * @Description 活动信息list
     * @Author  muxu
     * @Date  2019/9/5
     * @Time 11:01
     * @Return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.ActivityInfo>
     * @Params [model, activityInfo]
    **/
    @RequestMapping(value = "/list.ahsj", method = {RequestMethod.POST})
    public ResponseEntity<ResultModel> list(Map<String, Object> model, ActivityInfoDTO activityInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ActivityInfo activityInfo =mapper.map(activityInfoDTO,ActivityInfo.class);
        PageBean<ActivityInfo> pageBean = new PageBean<ActivityInfo>();
        pageBean.setParameter(activityInfo);
        return activityInfoService.list(pageBean);
    }

    /**
     * @Description 新增
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 16:13
     * @Return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @Params [activityInfoDTO]
    **/
    @RequestMapping(value = "/save.ahsj")
    public ResponseEntity<ResultModel>save(ActivityInfoDTO activityInfoDTO)throws Exception{
        if (EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getEndTime())|| EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getStartTime())||
            EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getDescription())|| EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getEventLocation())||
            EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getMaximumNumber())|| EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getName())||
            EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getRegistrationDeadline())||EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getIsPublic()))
        {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);

        }
        return activityInfoService.save(activityInfoDTO);
    }

    /**
     * @Description 修改
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 16:13
     * @Return
     * @Params
    **/

    @RequestMapping(value = "/update.ahsj",method = {RequestMethod.POST})
    public ResponseEntity<ResultModel>update(ActivityInfoDTO activityInfoDTO)throws Exception{
        if (EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getId())||EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getEndTime())||
                EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getStartTime())||EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getDescription())||
                EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getEventLocation())||EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getMaximumNumber())||
                EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getName())||EmptyUtil.Companion.isNullOrEmpty(activityInfoDTO.getRegistrationDeadline()))
        {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);
        }
        return activityInfoService.update(activityInfoDTO);
    }
    
    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/9/9
     * @Time 16:16
     * @Return 
     * @Params 
    **/

    @RequestMapping(value = "/delete.ahsj",method = {RequestMethod.POST})
    public ResponseEntity<ResultModel> delete(Map<String, Object> model, HttpServletRequest request, @RequestParam(value="ids", required=true) Long[] ids)throws Exception{
        for (int i = 0; i <ids.length ; i++) {
            ActivityInfo activityInfo = activityInfoService.selectById(ids[i]);
            if (EmptyUtil.Companion.isNullOrEmpty(activityInfo)) {
                return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,ids[i]), HttpStatus.OK);
            }
        }
        return activityInfoService.delete(ids);
    }


    /**
     * @Description list M&V
     * @Author  muxu
     * @Date  2019/10/11
     * @Time 10:16
     * @Return
     * @Params
    **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/activity/list");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @Description saveOrUpdate M&V
     * @Author  muxu
     * @Date  2019/10/11
     * @Time 10:19
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token]
    **/
    @RequestMapping("/update/index.ahsj")
    ModelAndView saveOrUpdate(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/activity/update");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }
}
