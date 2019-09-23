package com.ahsj.hiseurekazuulouterone.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: hisprojecte
 * @description:错误拦截器
 * @author: chenzhicai
 * @create: 2019-09-09-10-39
 **/
//@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Value("${ahsj.invalidtokenurl}")
    private String invalidtokenurl;
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("---------------Response401Filter--------------------------");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();
        HttpServletRequest request = currentContext.getRequest();
        int status = response.getStatus();
        log.info(String.valueOf(status));
        if (status==401) {
            try {
                response.sendRedirect(invalidtokenurl);
            } catch (IOException e) {
                log.error("自动跳转失败，请检查loginUrl是否正确");
            }
        }
        return null;

    }
}

    