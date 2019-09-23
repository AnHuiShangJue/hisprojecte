//package com.ahsj.hiseurekasecurityauthorizationserverone.service;
//
//import com.ahsj.hiseurekasecurityauthorizationserverone.entity.Menu;
//import com.ahsj.hiseurekasecurityauthorizationserverone.entity.MenuOperate;
//import com.ahsj.hiseurekasecurityauthorizationserverone.entity.Principal;
//import com.ahsj.hiseurekasecurityauthorizationserverone.entity.UserInfo;
//import com.ahsj.hiseurekasecurityauthorizationserverone.feign.sys.ISysMenuService;
//import com.ahsj.hiseurekasecurityauthorizationserverone.feign.sys.ISysUserInfoService;
//import com.ahsj.hiseurekasecurityauthorizationserverone.utils.EmptyUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Copyright (C), 2019-2019, 安徽商角有限公司
// * FileName: SysUserInfoService
// * <p>
// * Date:     2019/9/7 13:12
// *
// * @author XJP
// * @create 2019/9/7
// * @since 1.0.0
// */
//@Service
//@Transactional(readOnly = true)
//public class SysUserInfoService implements UserDetailsService {
//
//    private Logger logger = LoggerFactory.getLogger(SysUserInfoService.class.getSimpleName());
//
//    @Autowired
//    ISysMenuService iSysMenuService;
//
//    @Autowired
//    ISysUserInfoService iSysUserInfoService;
//
//    /**
//     * @return org.springframework.security.core.userdetails.UserDetails
//     * @功能说明
//     * @Params [username]
//     * @Author XJP
//     * @Date 2019/9/7
//     * @Time 13:13
//     **/
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserInfo user = iSysUserInfoService.selectByName(username);
//        if (EmptyUtil.Companion.isNullOrEmpty(user)) {
//            throw new UsernameNotFoundException("用户名" + username + "不存在");
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //添加菜单权限
//        List<Menu> menus = iSysMenuService.listSysMenu(user.getId());
//        if (!EmptyUtil.Companion.isNullOrEmpty(menus) && !EmptyUtil.Companion.isNullOrEmpty(menus.size()) && menus.size() != 0) {
//            for (Menu menu : menus) {
//                if (StringUtils.isNotBlank(menu.getPermission())) {
//                    // 添加基于Permission的权限信息
//                    for (String permission : StringUtils.split(menu.getPermission(), ",")) {
//                        authorities.add(new SimpleGrantedAuthority(permission));
//                        System.out.println("login user permission is:" + permission);
//                        logger.debug("login user permission is:" + permission);
//                    }
//                }
//            }
//        }
//        List<MenuOperate> menuOperates = iSysMenuService.listSysMenuOperate(user.getId());
//        // 按钮权限
//        if (!EmptyUtil.Companion.isNullOrEmpty(menuOperates) && !EmptyUtil.Companion.isNullOrEmpty(menuOperates.size()) && menuOperates.size() != 0) {
//
//            for (MenuOperate sysMenuOperate : menuOperates) {
//                if (StringUtils.isNotBlank(sysMenuOperate.getPermission())) {
//                    authorities.add(new SimpleGrantedAuthority(sysMenuOperate.getPermission()));
//                    System.out.println("login user Operate permission is:" + sysMenuOperate.getPermission());
//                    logger.debug("login user  Operate permission is:" + sysMenuOperate.getPermission());
//                }
//            }
//        }
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String finalPassword = "{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword());
//        return new Principal(user.getId(), user.getUserId(), user.getUserName(), user.getCompanyId(), user.getDeptId()
//                , user.getType(), user.getIsLeader(), finalPassword, authorities);
////        return new org.springframework.security.core.userdetails.User(user.getUserName(),
////                finalPassword, authorities);
//    }
//}
