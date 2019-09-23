package com.ahsj.hiseurekasecurityauthorizationserverone.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ControllerAdvice
 * <p>
 * Date:     2019/8/3 14:32
 *
 * @author XJP
 * @create 2019/8/3
 * @since 1.0.0
 */

@ControllerAdvice(basePackages = "com.ahsj.hiseurekasecurityauthorizationserverone")
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //其他错误
    @ResponseBody
    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})//AuthenticationServiceException
    public ModelAndView exception(Exception e,HttpServletRequest reqest,
                                  HttpServletResponse response) throws IOException {
        log.error("异常信息如下！！！！！！！！！！！！");
        e.printStackTrace();
        if (isAjax(reqest)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print("exception");
            //response.getWriter().print("nopemission");
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView("backend/common/page_500");
            return modelAndView;
        }
    }

    /**
     * 判断是否是ajax请求
     */
    public static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals(httpRequest.getHeader("X-Requested-With").toString()));
    }

}
