package com.ahsj.hiscore.error;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

@ControllerAdvice(basePackages = "com.ahsj.hiscore")
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [request, exception, response]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:51
     **/
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ModelAndView errorHandler(HttpServletRequest request, NoHandlerFoundException exception, HttpServletResponse response) throws IOException {
        log.error("异常信息如下！！！！！！！！！！！！");
        exception.printStackTrace();
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print("exception");
            //response.getWriter().print("nopemission");
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView("backend/common/page_404");
            return modelAndView;
        }
    }
/**
 *@功能说明 
 *@Params [ex, reqest, response]
 *@return org.springframework.web.servlet.ModelAndView
 *@Author XJP
 *@Date 2019/8/16
 *@Time 9:52
**/
    //其他错误
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ModelAndView exception(Exception ex, HttpServletRequest reqest,
                                  HttpServletResponse response) throws Exception {

        log.error("异常信息如下！！！！！！！！！！！！");
        ex.printStackTrace();
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
 *@功能说明 
 *@Params [code, ex]
 *@return java.lang.String
 *@Author XJP
 *@Date 2019/8/16
 *@Time 13:11
**/
    private <T extends Throwable> String resultFormat(Integer code, T ex) {
        log.error(JsonResult.failed(code, ex.getMessage()));
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return JsonResult.failed(code, ex.getMessage());
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
