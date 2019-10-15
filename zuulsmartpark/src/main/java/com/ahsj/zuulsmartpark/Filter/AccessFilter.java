package com.ahsj.zuulsmartpark.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

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
        if(isAccessLogin()){
            return null;
        }else if(GETIndex()){
            if(!EmptyUtil.Companion.isNullOrEmpty(queryString)){
                String token = getToken(queryString);
                ctx.addZuulRequestHeader("Authorization","Bearer "+getToken(queryString));
                ctx.addZuulResponseHeader("Authorization","Bearer "+getToken(queryString));
                Object accessToken = request.getHeader("Authorization");
                log.info("accessToken is"+accessToken);
            }
            return null;
        }else if(GetResource()){
            return null;
        }
        else if(HaveToken()){
            if(!EmptyUtil.Companion.isNullOrEmpty(queryString)){
                String token = getToken(queryString);
                ctx.addZuulRequestHeader("Authorization","Bearer "+token);
                ctx.addZuulResponseHeader("Authorization","Bearer "+token);
                Object accessToken = request.getHeader("Authorization");
                log.info("accessToken is"+accessToken);
            }
            return null;
        }else if(GetPayAlipay()){
            return null;
        }
        else{
            Object accessToken = request.getHeader("Authorization");
            if(accessToken == null) {
                log.warn("Authorization token is empty");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("Authorization token is empty");
                return null;
            }
        }
        log.info("Authorization token ok");
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

    /**
     *@Description  如果用户是访问登陆的，可以直接访问，否则会被检测令牌
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-12
     *@Time 16:02
    **/
    private boolean isAccessLogin(){
//        host.con
//        String AccessURI = host.substring(host.indexOf(":"))
        if(requestURI != null && requestURI.contains("/uaa/oauth/token") ){
            return true;
        }
        return false;
    }

    private boolean GETIndex(){
        if(requestURI != null && requestURI.contains("/index.ahsj") ){
            return true;
        }
        return false;
    }

   private boolean GetResource(){
        if(requestURI!= null){
            if(  requestURI.contains("/accounts/assets")|| requestURI.contains("/smartparkcore/assets") ){
                return true;
            }
            return false;
        }
        return false;
    }
   private boolean GetPayAlipay(){
        if(requestURI!= null){
            if(  requestURI.contains("/payalipay")|| requestURI.contains("/payalipay") ){
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean HaveToken(){
            if(queryString!= null && queryString.contains("token")){
                return true;
            }
        return false;
    }

    private String getToken(String queryString) {
       String[] split = this.queryString.split("&");
       int i = 0;
       while (i <= split.length -1){
           if(split[i].contains("token")){
               return split[i].substring(6);
           }
           i++;
       }
       return "";
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
