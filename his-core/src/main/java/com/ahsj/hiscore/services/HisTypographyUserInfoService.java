/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisTypographyUserInfoService
 * Author:   anhuishangjue
 * Date:     2019/7/4 11:51
 * Description: HisTypographyUserInfoServcie
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.hiscore.services;

import com.ahsj.hiscore.common.dto.HisTypographyUserInfoRep;
import com.ahsj.hiscore.entity.HisTypographyUserInfo;
import core.message.Message;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈HisTypographyUserInfoServcie〉
 *
 * @author anhuishangjue
 * @create 2019/7/4
 * @since 1.0.0
 */

public interface HisTypographyUserInfoService {

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:42
     **/
    Message deleteByPrimaryKey(Integer id) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisTypographyUserInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:42
     **/
    HisTypographyUserInfo selectByPrimaryKey(Integer id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:42
     **/
    Message updateByPrimaryKeySelective(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:42
     **/
    List<HisTypographyUserInfo> getHisTypographyUserInfoByStartDateAndEndDate(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [startDate]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:42
     **/
    List<HisTypographyUserInfo> getHisTypographyUserInfoByNowDate(String startDate) throws Exception;

    /**
     * 查看历史时间的值班人员
     *
     * @param endDate
     * @return
     */
    List<HisTypographyUserInfo> getHisTypographyUserInfoByHistoryDate(String endDate) throws Exception;

    /**
     * @return java.lang.Integer
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:42
     **/
    Integer deletebatchHisTypographyUserInfo(Integer[] ids) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisTypographyUserInfoRep, deptId]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:42
     **/
    Message saveHisTypographyUserInfo(List<HisTypographyUserInfoRep> hisTypographyUserInfoRep, Long deptId) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:41
     **/
    List<HisTypographyUserInfo> selectHisTypographyUserInfoDateAll(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [typographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:41
     **/
    Message saveHisTypographyUserInfos(List<HisTypographyUserInfo> typographyUserInfo) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [typographyUserInfoList, deptId]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:41
     **/
    List<HisTypographyUserInfo> queryHisTypographyUserInfoList(List<HisTypographyUserInfo> typographyUserInfoList, Long deptId) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [typographyUserInfoListdate, deptId]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:41
     **/
    List<HisTypographyUserInfo> getTypographyUserInfoListdate(String[] typographyUserInfoListdate, Long deptId) throws Exception;
}
