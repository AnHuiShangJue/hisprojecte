package com.ahsj.hiscore.fallback;

import com.ahsj.hiscore.core.BoolMessage;
import com.ahsj.hiscore.entity.UserInfo;
import com.ahsj.hiscore.feign.IUserService;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IUserServiceFallback implements IUserService {

    Logger logger = LoggerFactory.getLogger(ICodeServiceFallback.class.getSimpleName());
    @Override
    public com.ahsj.hiscore.core.BoolMessage saveOrUpdateUserInfo(UserInfo user) throws Exception {
        logger.error("saveOrUpdateUserInfo,服务获取异常,不能够访问用户服务");
        return (BoolMessage) MessageUtil.createMessage(false,"saveOrUpdateUserInfo,服务获取异常,不能够访问用户服务");
    }

    @Override
    public UserInfo selectByName(String userId) throws Exception {
        logger.error("selectByName,服务获取异常,不能够访问用户服务");
        UserInfo userInfo =new UserInfo();
        userInfo.setId(-1L);
        return  userInfo;
    }

    @Override
    public UserInfo getUserLoginId(String userId) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new UserInfo();
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new UserInfo();
    }

    @Override
    public UserInfo getUserId(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new UserInfo();
    }

    @Override
    public BoolMessage saveOrUpdateUserInfo(String userId, String password, String userName, String sex, String deptId, String delFlag, String isInitData, String telPhone) {
        logger.error("saveOrUpdateUserInfo,服务获取异常,不能够访问用户服务");
        return (BoolMessage) MessageUtil.createMessage(false,"saveOrUpdateUserInfo,服务获取异常,不能够访问用户服务");
    }
}
