package com.ahsj.hiscore.controller.hisvitalsigns;

import com.ahsj.hiscore.dao.HisVitalSignsMapper;
import com.ahsj.hiscore.entity.HisVitalSigns;
import com.ahsj.hiscore.services.HisVitalSignsService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/hisvitalsigns/")
public class HisVitalSignsController extends BaseController {
    @Autowired
    HisVitalSignsService hisVitalSignsService;

    @Autowired
    HisVitalSignsMapper hisVitalSignsMapper;

    /**
     * @Description 新增或更新
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 9:20
     * @Return
     * @Params
    **/

    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HisVitalSigns hisVitalSigns) throws Exception {
        return  hisVitalSignsService.saveOrUpdate(hisVitalSigns);
    }

    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 10:56
     * @Return
     * @Params
    **/

    @RequestMapping(value = "list.ahsj")
    @ResponseBody
    public PageBean<HisVitalSigns> list(Map<String,Object>model,HisVitalSigns hisVitalSigns)throws Exception{
        PageBean<HisVitalSigns> pageBean = new PageBean<HisVitalSigns>();
        pageBean.setParameter(hisVitalSigns);
        return hisVitalSignsService.list(pageBean);
    }


    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 17:59
     * @Return core.message.Message
     * @Params [model, request, ids]
    **/
    @RequestMapping(value = "delete.ahsj")
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisVitalSignsService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }


    /**
     * @Description modelAndView
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 9:22
     * @Return
     * @Params
    **/
    @RequestMapping("update/index.ahsj")
    ModelAndView UpdateIndex(String token,HisVitalSigns hisVitalSigns,Long hospitalManageId){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisVitalSigns/update");
        modelAndView.addObject("title","生命体征信息添加");
        modelAndView.addObject("token", token);
        modelAndView.addObject("token", token);
        modelAndView.addObject("hospitalManageId", hospitalManageId);
        if (EmptyUtil.Companion.isNullOrEmpty(hisVitalSigns.getId())){
        modelAndView.addObject("hisVitalSigns", hisVitalSigns);
        }else{
            modelAndView.addObject("hisVitalSigns",hisVitalSignsMapper.selectByPrimaryKey(hisVitalSigns.getId()));
        }
        return modelAndView;
    }

    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 11:00
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token, hisVitalSigns]
    **/
    @RequestMapping("list/index.ahsj")
    ModelAndView list(String token,Long id){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisVitalSigns/list");
        modelAndView.addObject("title","生命体征信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hospitalManageId", id);
        return modelAndView;
    }

}
