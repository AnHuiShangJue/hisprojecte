package com.ahsj.hiscore.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description 跳转 404 ，500 页面
 * @Author dingli
 * @Date 2019/7/31
 * @Time 14:37
 **/
@RequestMapping("api/error")
@Controller
public class errorController {
    /**
     * className errorController
     *
     * @author dingli
     * @date 2019/7/31 14:37
     */
    /**
         *@Description  跳转404
          * @Param token:
     * @Param text:
         *@Author: dingli
         * @return: org.springframework.web.servlet.ModelAndView
         *@Date 2019/7/31
         *@Time 16:05
        **/
    @RequestMapping("/notFound/index.ahsj")
    ModelAndView notFound(String token, String text) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/common/page_404");
        modelAndView.addObject("token", token);
        modelAndView.addObject("text", "测试错误信息");
        return modelAndView;
    }
/**
     *@Description  跳转500
      * @Param token:
 * @Param text:
     *@Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     *@Date 2019/7/31
     *@Time 16:05
    **/
    @RequestMapping("/serverError/index.ahsj")
    ModelAndView serverError(String token, String text) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/common/page_500");
        modelAndView.addObject("token", token);
        modelAndView.addObject("text", "测试错误信息");
        return modelAndView;
    }

}
