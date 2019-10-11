package com.ahsj.smartparkcore.controller.conferenceroominfo;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.core.ResultStatus;
import com.ahsj.smartparkcore.entity.dto.ConferenceRoomInfoDTO;
import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import com.ahsj.smartparkcore.services.ConferenceRoomInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/conferenceRoomInfo/")
public class ConferenceRoomInfoController extends BaseController {
    private String rootPath ="/api/conferenceRoomInfo";
    @Autowired
    ConferenceRoomInfoService conferenceRoomInfoService;


    /**
     *@Description 新增会议室
     *@Params 
     *@return 
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 10:29
    **/
    @RequestMapping("save.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> save(Map<String, Object> model, HttpServletRequest request, ConferenceRoomInfoDTO conferenceRoomInfoDTO)throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getLocation())||EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getArea()) ||
                EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getCapacity())||EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getName())||
                EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getPhoneNumber())||EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getEnterpriseId())||
                EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getConferenceName())){
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);
        }
        return conferenceRoomInfoService.save(conferenceRoomInfoDTO);

    }

    /**
     *@Description 更新会议室信息
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 14:32
    **/
    @RequestMapping("update.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> update(Map<String, Object> model, HttpServletRequest request, ConferenceRoomInfoDTO conferenceRoomInfoDTO)throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getLocation())||EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getArea()) ||
                EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getCapacity())||EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getName())||
                EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getPhoneNumber())||EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getEnterpriseId())||
                EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getConferenceName())||EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfoDTO.getId())){
            return new ResponseEntity<>(new ResultModel(ResultStatus.ERROR_INFO_NOTCOMPELETE), HttpStatus.OK);
        }
        return conferenceRoomInfoService.update(conferenceRoomInfoDTO);

    }

    /**
     *@Description 删除,删除单条记录
     *@Params [model, request, conferenceRoomInfoDTO]
     *@return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:19
    **/
    @RequestMapping("delete.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> delete(Map<String, Object> model, HttpServletRequest request, @RequestParam(value="ids", required=true) Long[] ids)throws Exception{
        for (int i = 0; i <ids.length ; i++) {
            ConferenceRoomInfo conferenceRoomInfo = conferenceRoomInfoService.selectById(ids[i]);
            if (EmptyUtil.Companion.isNullOrEmpty(conferenceRoomInfo)) {
                return new ResponseEntity<>(new ResultModel(ResultStatus.DATA_NULL,ids[i]), HttpStatus.OK);
            }
        }
        return conferenceRoomInfoService.delete(ids);
    }


    /**
     *@Description 分页查询
     *@Params [model, request, ids]
     *@return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     *@Author zhushixiang
     *@Date 2019-09-05
     *@Time 15:47
    **/
    @RequestMapping("list.ahsj")
    @ResponseBody
    public ResponseEntity<ResultModel> list(Map<String, Object> model, HttpServletRequest request, ConferenceRoomInfoDTO conferenceRoomInfoDTO)throws Exception{
        DozerBeanMapper mapper = new DozerBeanMapper();
        ConferenceRoomInfo conferenceRoomInfo =mapper.map(conferenceRoomInfoDTO,ConferenceRoomInfo.class);
        PageBean<ConferenceRoomInfo> pageBean = new PageBean<ConferenceRoomInfo>();
        pageBean.setParameter(conferenceRoomInfo);
        return conferenceRoomInfoService.list(pageBean);
    }

    @RequestMapping("list/index.ahsj")
    ModelAndView list(String token)throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/ConferenceRoomInfo/list");
        modelAndView.addObject("token",token);
        modelAndView.addObject("title","会议室信息管理");
        return modelAndView;
    }
}
