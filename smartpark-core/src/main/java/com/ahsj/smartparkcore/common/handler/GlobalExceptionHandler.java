package com.ahsj.smartparkcore.common.handler;

import core.message.Message;
import core.message.MessageUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: his
 * @description:GlobalExceptionHandler
 * @author: chenzhicai
 * @create: 2019-06-30-20-34
 **/

//@Slf4j
//@Configuration
//@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public Message grantBadCredentialsError(HttpServletRequest request, HttpServletResponse response, BadCredentialsException e){
//        log.error("grantBadCredentialsError");
//        log.error(e.getMessage());
        return MessageUtil.createMessage(false,"");
    }
}

    