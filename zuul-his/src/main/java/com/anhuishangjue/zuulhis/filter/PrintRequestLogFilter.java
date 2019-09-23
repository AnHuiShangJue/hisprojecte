package com.anhuishangjue.zuulhis.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: his
 * @description:网关日志
 * @author: chenzhicai
 * @create: 2019-06-19-00-01
 **/


@Component
public class PrintRequestLogFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PrintRequestLogFilter.class);

    String contextPath;
    //访问协议+访问主机号+访问端口号
    String basePath ;
    //远程地址
    String remoteAddress;
    //访问方法路由
    String servletPath;
    //请求的URL
    String requestURL;
    //请求的路由
    String requestURI;
    //附带的请求参数
    String queryString;
    //远程用户
    String remoteUser;
    //token
    String token;

    @Value("${security.oauth2.client.accessTokenUri:#{null}}")
    private String host;

    @Override
    public String filterType() {
        return "pre";
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
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        initData(request);
        printData();
        return null;
    }

    /**
     *@Description 初始化路由相关信息
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-12
     *@Time 16:02
     **/
    private void initData(HttpServletRequest request) {
        contextPath = request.getContextPath();
        basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";  //
        remoteAddress=request.getRemoteAddr();
        servletPath =request.getServletPath();
//        String getServletContext_getRealPath = request.getServletContext.getRealPath("/");
        requestURL =request.getRequestURL().toString();
        requestURI =request.getRequestURI();
        queryString =request.getQueryString();
        remoteUser =request.getRemoteUser();
        token = request.getHeader("Authorization");
    }

    private void printData() {
        log.info("contextPath is:\t"+contextPath+"\r");
        log.info("basePath is:\t"+basePath+"\r");
        log.info("remoteAddress is:\t"+remoteAddress+"\r");
        log.info("servletPath is:\t"+servletPath+"\r");
        log.info("requestURL is:\t"+requestURL+"\r");
        log.info("requestURI is:\t"+requestURI+"\r");
        log.info("queryString is:\t"+queryString+"\r");
        log.info("remoteUser is:\t"+remoteUser+"\r");
        log.info("token is:\t"+token+"\r");
    }


}