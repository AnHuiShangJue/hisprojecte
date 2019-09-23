package com.ahsj.hiscore.controller.adlBarthel;

import com.ahsj.hiscore.entity.HisAdlBarthel;
import com.ahsj.hiscore.services.HisAdlBarthelService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequestMapping("/api/hisAdlBarthel/")
public class HisadlBarthelController extends BaseController {
    @Autowired
    HisAdlBarthelService hisAdlBarthelService;

    /**
     *@Description 跳到页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/18
     *@Time 18:01
    */
    @RequestMapping("saveOrUpdate/index.ahsj")
    ModelAndView saveOrUpdate(String token,String hr,Long id,Integer score) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/adlBarthel/saveOrUpdate");
        modelAndView.addObject("title", "当次护嘱信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hr", hr);
        modelAndView.addObject("score", score);
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            modelAndView.addObject("hisAdlBarthel", new HisAdlBarthel());
        }else {
            modelAndView.addObject("hisAdlBarthel", hisAdlBarthelService.selectById(id));
        }
        return modelAndView;
    }


    /**
     *@Description 保存
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/18
     *@Time 18:04
    */
    @RequestMapping(value = "saveOrUpdate.ahsj",method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request, HisAdlBarthel hisAdlBarthel
//            , @RequestParam(value="id", required=true) Long id

    ) throws Exception {
        return  hisAdlBarthelService.saveOrUpdate(hisAdlBarthel);
    }


    /**
     *@Description 跳到list页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/19
     *@Time 17:40
    */
    @RequestMapping("list/index.ahsj")
    ModelAndView list(String token,String hr) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/adlBarthel/list");
        modelAndView.addObject("title", "当次护嘱信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hr", hr);
        return modelAndView;
    }

    /**
     *@Description 根据hr编号查询
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/19
     *@Time 17:49
    */
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisAdlBarthel> list (Map<String, Object> model, HttpServletRequest request, HisAdlBarthel hisAdlBarthel
            ,@RequestParam(value="hr", required=true) String hr) throws Exception{
        PageBean<HisAdlBarthel> pageBean = new PageBean<HisAdlBarthel>();
        hisAdlBarthel.setHospitalnumber(hr);
        pageBean.setParameter(hisAdlBarthel);
        pageBean = hisAdlBarthelService.list(pageBean);
        return pageBean;
    }
    




}
