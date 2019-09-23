/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: UserService
 * Author:   anhuishangjue
 * Date:     2019/7/8 18:31
 * Description: UserService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.sysuserinfo.services;


import com.ahsj.sysuserinfo.entity.UserInfo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈UserService〉
 *
 * @author anhuishangjue
 * @create 2019/7/8
 * @since 1.0.0
 */

public interface UserService {

    List<UserInfo> getUserInfoDeptById(String deptId);

    UserInfo selectByPrimaryKey(Long id);

    UserInfo getUserLoginId(String userId);

    UserInfo getUserId(Long id);

    UserInfo selectByUserName(String username) throws Exception;
}
