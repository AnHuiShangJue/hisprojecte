package com.ahsj.smartparkcore.controller.activityinfo;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.dao.ActivityInfoMapper;
import com.ahsj.smartparkcore.entity.dto.ActivityInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.ahsj.smartparkcore.entity.vo.ActivityInfoVO;
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
    public ResponseEntity<PageBean<ActivityInfo>> list(ActivityInfoDTO activityInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ActivityInfo activityInfo =mapper.map(activityInfoDTO,ActivityInfo.class);
        PageBean<ActivityInfo> pageBean = new PageBean<ActivityInfo>();
        pageBean.setParameter(activityInfo);
        return ResponseEntity.ok(activityInfoService.list(pageBean));
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
     * @Description 修改启用状态
     * @Author  muxu
     * @Date  2019/10/14
     * @Time 17:29
     * @Return
     * @Params
    **/
    @RequestMapping(value = "/enable.ahsj",method = {RequestMethod.POST})
    public ResponseEntity<ResultModel> enable(Map<String, Object> model, HttpServletRequest request, @RequestParam(value="ids", required=true) Long[] ids)throws Exception{
        for (int i = 0; i <ids.length ; i++) {
            ActivityInfo activityInfo = activityInfoService.selectById(ids[i]);
            if (EmptyUtil.Companion.isNullOrEmpty(activityInfo)) {
                return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,ids[i]), HttpStatus.OK);
            }
        }
        return activityInfoService.enable(ids);
    }

    /**
     * @Description 审核通过
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 15:21
     * @Return
     * @Params
     **/
    @RequestMapping(value = "/reviewSuccess.ahsj",method = {RequestMethod.POST})
    public ResponseEntity<ResultModel>reviewSuccess(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id
            , @RequestParam(value="remarks", required=true) String remarks)throws Exception{
        ActivityInfo activityInfo = activityInfoService.selectById(id);
        if (EmptyUtil.Companion.isNullOrEmpty(activityInfo)){
            return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,id), HttpStatus.OK);
        }else {
            return activityInfoService.reviewSuccess(id,remarks);
        }
    }

    /**
     * @Description 审核未通过
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 15:21
     * @Return
     * @Params
     **/
    @RequestMapping(value = "/reviewError.ahsj",method = {RequestMethod.POST})
    public ResponseEntity<ResultModel>reviewError(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id
            , @RequestParam(value="remarks", required=true) String remarks)throws Exception{
        ActivityInfo activityInfo = activityInfoService.selectById(id);
        if (EmptyUtil.Companion.isNullOrEmpty(activityInfo)){
            return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,id), HttpStatus.OK);
        }else {
            return activityInfoService.reviewError(id,remarks);
        }
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
    ModelAndView Update(String token,Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/activity/update");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("activityInfoVO",activityInfoMapper.selectByPrimaryKey(id));
        return modelAndView;
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/10/14
     * @Time 13:07
     * @Return
     * @Params
    **/
    @RequestMapping("/save/index.ahsj")
    ModelAndView save(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/activity/add");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        ActivityInfoVO activityInfoVO = new ActivityInfoVO();
        modelAndView.addObject("activityInfoVO", activityInfoVO);
        return modelAndView;
    }

    /**
     * @Description review审核
     * @Author  muxu
     * @Date  2019/10/14
     * @Time 14:17
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token, id]
     **/
    @RequestMapping("/review/index.ahsj")
    ModelAndView review(String token,Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/activity/review");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("activityInfo", activityInfoService.selectById(id));
        return modelAndView;
    }
}
