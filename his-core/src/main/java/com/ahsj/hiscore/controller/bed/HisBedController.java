package com.ahsj.hiscore.controller.bed;
import com.ahsj.hiscore.entity.HisBed;
import com.ahsj.hiscore.services.HisBedService;
import com.ahsj.hiscore.services.HisWradService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/hisbeds")
public class HisBedController extends BaseController {

    @Autowired
    HisBedService hisBedService;

    @Autowired
    HisWradService hisWradService;

    /**
     * @return modelAndView
     * @Description 添加病床
     * @Params [id]
     * @Author dingli
     * @Date 2019-07-05
     * @Time 14:00
     **/
    @RequestMapping("/addbed/index.ahsj")
    ModelAndView addHisBed(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/bed/add");
        modelAndView.addObject("wardId", id);//病房的id
        modelAndView.addObject("title", "添加病床");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 添加病床
     * @Params [hisbed]
     * @Author dingli
     * @Date 2019-07-5
     * @Time 14：52
     **/
    @RequestMapping("/toaddHisbed/index.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisBed hisBed, String token) throws Exception {
        return hisBedService.saveOrUpdate(hisBed);
    }


    /**
     * @param numbers
     * @param hisBed
     * @return
     * @Description 根据病房id分页出该id下的所有病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:16
     **/
    @RequestMapping("/getBed/index.ahsj")
    @ResponseBody
    public PageBean<HisBed> list(Integer numbers, HisBed hisBed) throws Exception {
        PageBean<HisBed> pageBean = new PageBean<HisBed>();
        if (numbers != null && !EmptyUtil.Companion.isNullOrEmpty(hisWradService.gethisWardByNumber(numbers).getId())) {//查找
            hisBed.setWardId(hisWradService.gethisWardByNumber(numbers).getId());
            pageBean.setParameter(hisBed);
            pageBean = hisBedService.getHisBedAll(pageBean);
        } else {
            pageBean.setData(new ArrayList<HisBed>());
        }
        return pageBean;
    }

    /**
     * @return modelAndView
     * @Description 病床修改
     * @Params [id]
     * @Author dingli
     * @Date 2019-07-5
     * @Time 20：54
     **/
    @RequestMapping("/showHisBed/index.ahsj")
    ModelAndView showHisWard(Long id, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/bed/update");
        modelAndView.addObject("title", "修改病床");
        modelAndView.addObject("token", token);
        modelAndView.addObject("wardId", id);
        modelAndView.addObject("hisBed", hisBedService.selectByPrimaryKey(id));
        HisBed hisBed = hisBedService.selectByPrimaryKey(id);
        modelAndView.addObject("createName", hisWradService.getUserName(new Long(hisBed.getCreateUserId())));
        modelAndView.addObject("updateName", hisWradService.getUserName(new Long(hisBed.getUpdateUserId())));
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 批量删除
     * @Params [ids]
     * @Author dingli
     * @Date 2019-07-5
     * @Time 22：03
     **/
    @ResponseBody
    @RequestMapping(value = "/delete.ahsj", method = {RequestMethod.POST})
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisBedService.deleteByPrimaryKey(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return core.message.Message
     * @Description 设置启用状态
     * @Params [model, request, ids]
     * @Author dingli
     * @Date 2019-07-06
     * @Time 9:28
     **/
    @ResponseBody
    @RequestMapping(value = "/setEnable.ahsj")
    public Message setEnable(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisBedService.setEnable(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @param wardNumber
     * @param token
     * @return
     * @Description 根据病房号获取病床
     * @Author: dingli
     * @Date 2019/7/13
     * @Time 15:17
     **/
    @RequestMapping(value = "/getBed.ahsj", method = {RequestMethod.GET})
    @ResponseBody
    public String getDoctor(Integer wardNumber, String token) throws Exception {
        return JSON.toJSONString(hisBedService.getHisBed(hisWradService.gethisWardByNumber(wardNumber).getId()));
    }

    /**
     * @param
     * @Description 查询所有的病床
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisBed>
     * @Date 2019/8/17
     * @Time 16:42
     **/
    @RequestMapping("/getBedAll.ahsj")
    @ResponseBody
    public List<HisBed> getBedAll() throws Exception {
        return hisBedService.selectHisBedAll();
    }


}