/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: OrganizationUser
 * Author:   anhuishangjue
 * Date:     2019/7/8 10:44
 * Description: OrganizationUser
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.translate.common.dto;

/**
 * 〈一句话功能简述〉<br> 
 * 〈OrganizationUser〉
 *
 * @author anhuishangjue
 * @create 2019/7/8
 * @since 1.0.0
 */

public class OrganizationUser {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String OrganizationName;

    private String UserName;


    public OrganizationUser() {
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
