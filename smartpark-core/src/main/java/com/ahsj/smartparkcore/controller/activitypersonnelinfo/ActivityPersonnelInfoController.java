package com.ahsj.smartparkcore.controller.activitypersonnelinfo;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.entity.dto.ActivityPersonnelInfoDTO;
import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import com.ahsj.smartparkcore.services.ActivityPersonnelInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/activitypersonnelinfo/")
public class ActivityPersonnelInfoController extends BaseController {

    @Autowired
    ActivityPersonnelInfoService activityPersonnelInfoService;



    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/10/14
     * @Time 13:20
     * @Return org.springframework.http.ResponseEntity<core.entity.PageBean<ActivityInfo>>
     * @Params [activityInfoDTO]
    **/
    @RequestMapping(value = "/list.ahsj", method = {RequestMethod.POST})
    public ResponseEntity<PageBean<ActivityPersonnelInfo>> list(ActivityPersonnelInfoDTO activityPersonnelInfoDTO) throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ActivityPersonnelInfo activityPersonnelInfo =mapper.map(activityPersonnelInfoDTO,ActivityPersonnelInfo.class);
        PageBean<ActivityPersonnelInfo> pageBean = new PageBean<ActivityPersonnelInfo>();
        pageBean.setParameter(activityPersonnelInfo);
        return ResponseEntity.ok(activityPersonnelInfoService.list(pageBean));
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
    public ResponseEntity<ResultModel> save(ActivityPersonnelInfoDTO activityPersonnelInfoDTO)throws Exception{
        if (EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getAge())|| EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getSex())||
                EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getPhoneNumber())|| EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getIdcard())||
                EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getEmail())|| EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getName()))
        {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);

        }
        return activityPersonnelInfoService.save(activityPersonnelInfoDTO);
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
    public ResponseEntity<ResultModel>update(ActivityPersonnelInfoDTO activityPersonnelInfoDTO)throws Exception{
        if (EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getAge())|| EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getSex())||
                EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getPhoneNumber())|| EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getIdcard())||
                EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getEmail())|| EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getName()) ||
                EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfoDTO.getIsReview()))
        {
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);
        }
        return activityPersonnelInfoService.update(activityPersonnelInfoDTO);
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
            ActivityPersonnelInfo activityPersonnelInfo = activityPersonnelInfoService.selectById(ids[i]);
            if (EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfo)) {
                return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,ids[i]), HttpStatus.OK);
            }
        }
        return activityPersonnelInfoService.delete(ids);
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
        ActivityPersonnelInfo activityPersonnelInfo = activityPersonnelInfoService.selectById(id);
        if (EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfo)){
            return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,id), HttpStatus.OK);
        }else {
            return activityPersonnelInfoService.reviewSuccess(id,remarks);
        }
    }

    /**
     * @Description 审核不通过
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 15:26
     * @Return
     * @Params
    **/
    @RequestMapping(value = "/reviewError.ahsj",method = {RequestMethod.POST})
    public ResponseEntity<ResultModel>reviewError(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id
            , @RequestParam(value="remarks", required=true) String remarks)throws Exception{
        ActivityPersonnelInfo activityPersonnelInfo = activityPersonnelInfoService.selectById(id);
        if (EmptyUtil.Companion.isNullOrEmpty(activityPersonnelInfo)){
            return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,id), HttpStatus.OK);
        }else {
            return activityPersonnelInfoService.reviewError(id,remarks);
        }
    }


    /**
     * @Description list M&V
     * @Author  muxu
     * @Date  2019/10/14
     * @Time 13:24
     * @Return
     * @Params
    **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token,Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/activityPersonnelInfo/list");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("activityId", id);
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
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/activityPersonnelInfo/review");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("activityPersonnelInfoVO", activityPersonnelInfoService.selectById(id));
        return modelAndView;
    }
}
