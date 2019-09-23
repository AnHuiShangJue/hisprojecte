/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: UserDept
 * Author:   anhuishangjue
 * Date:     2019/7/8 18:19
 * Description: UserDept
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.userinfor.entity.dto;

import core.entity.BaseEntity;

/**
 * 〈一句话功能简述〉<br>
 * 〈UserDept〉
 *
 * @author anhuishangjue
 * @create 2019/7/8
 * @since 1.0.0
 */

public class UserDept {
    private Long userId;

    private Long deptId;

    private String userName;

    private String userLoginId;

    private String deptName;

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }



    public UserDept() {
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
