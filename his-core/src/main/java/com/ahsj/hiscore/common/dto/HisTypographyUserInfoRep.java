/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisTypographyUserInfoRep
 * Author:   anhuishangjue
 * Date:     2019/7/9 10:25
 * Description: HisTypographyUserInfoRep
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.hiscore.common.dto;

import com.ahsj.hiscore.entity.HisTypographyUserInfo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈HisTypographyUserInfoRep〉
 *
 * @author anhuishangjue
 * @create 2019/7/9
 * @since 1.0.0
 */

public class HisTypographyUserInfoRep extends HisTypographyUserInfo {

    private Long deptId;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HisTypographyUserInfoRep() {
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
