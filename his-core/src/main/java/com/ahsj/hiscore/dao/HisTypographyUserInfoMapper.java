package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisTypographyUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HisTypographyUserInfoMapper {
    /**
     * @return int
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:46
     **/
    int deleteByPrimaryKey(Integer id) throws Exception;

    /**
     * @return int
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:46
     **/
    int insert(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * @return int
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:46
     **/
    int insertSelective(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisTypographyUserInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:46
     **/
    HisTypographyUserInfo selectByPrimaryKey(Integer id) throws Exception;

    /**
     * @return int
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:45
     **/
    int updateByPrimaryKeySelective(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * @return int
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:45
     **/
    int updateByPrimaryKey(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * 查看一定时间范围值班人员信息
     *
     * @param hisTypographyUserInfo
     * @return
     */
    List<HisTypographyUserInfo> getHisTypographyUserInfoByStartDateAndEndDate(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * 查看当前时间的值班人员
     *
     * @param startDate
     * @return
     */
    List<HisTypographyUserInfo> getHisTypographyUserInfoByNowDate(@Param("starDate") String startDate) throws Exception;

    /**
     * 查看历史时间的值班人员
     *
     * @param endDate
     * @return
     */
    List<HisTypographyUserInfo> getHisTypographyUserInfoByHistoryDate(@Param("endDate") String endDate) throws Exception;

    /**
     * 批量新增
     *
     * @param hisTypographyUserInfoList
     * @return
     */
    void insertList(List<HisTypographyUserInfo> hisTypographyUserInfoList) throws Exception;

    /**
     * @return java.lang.Integer
     * @功能说明
     * @Params [list]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:45
     **/
    Integer updateByBatch(@Param("list") List<HisTypographyUserInfo> list) throws Exception;

    /**
     * @return java.lang.Integer
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:45
     **/
    Integer deletebatchHisTypographyUserInfo(@Param("ids") Integer[] ids) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:45
     **/
    List<HisTypographyUserInfo> queryHisTypographyUserInfoList(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTypographyUserInfo>
     * @功能说明
     * @Params [hisTypographyUserInfo]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:45
     **/
    List<HisTypographyUserInfo> getQueryHisTypographyUserInfo(HisTypographyUserInfo hisTypographyUserInfo) throws Exception;
}