package com.ahsj.hiseurekasecurityauthorizationserverone.handler;

import com.ahsj.hiseurekasecurityauthorizationserverone.entity.UserInfo;
import com.ahsj.hiseurekasecurityauthorizationserverone.service.UserInfoService;
import com.ahsj.hiseurekasecurityauthorizationserverone.utils.DateUtil;
import core.entity.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @program: his
 * @description:AuthSuccessHandler
 * @author: chenzhicai
 * @create: 2019-06-30-20-54
 **/
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class.getSimpleName());


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Principal userDetails = (Principal) authentication.getPrincipal();
        System.out.println("登陆处理");
//        LogLogin logLogin = new LogLogin();
//        logLogin.setCreateTime(new Date());
//        logLogin.setIp(IpUtil.getIpAddr(request));
//        logLogin.setUsername(userDetails.getUsername());
//        logLogin = logLoginRepository.save(logLogin);
//        logger.info(JSON.toJSONString(logLogin));
        logger.info("authentication.isAuthenticated()=" + authentication.isAuthenticated());

        HttpSession httpSession = request.getSession();
        logger.info("session create time = " + DateUtil.format(httpSession.getCreationTime(), "yyyy-MM-dd HH:mm:ss"));

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

    