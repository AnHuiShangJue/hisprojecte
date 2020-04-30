package com.ahsj.userinfor.dao;

import com.ahsj.userinfor.entity.SelectEntity;
import com.ahsj.userinfor.entity.UserInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    @Nullable
    UserInfo selectByUserName( String userName);

    public List<UserInfo> listUserByOrgId(PageBean<UserInfo> pageEntity);

    public List<UserInfo> listNotSelectUserByOrgId(PageBean<UserInfo> pageEntity);

    @NotNull
    List<UserInfo> list(@NotNull PageBean<UserInfo> pageBean);


    List<SelectEntity> listUserInfo(@NotNull UserInfo sysUserInfo);

    List<UserInfo> selectByDeptId(String deptId);

    UserInfo getUserLoginId(String userId);

    UserInfo getUserId(Long id);

    /**
     *@Description 
     *@MethodName updateByisRestrict
     *@Params [id]
     *@return int
     *@Author XJP
     *@Date 2020/4/30
     *@Time 18:09
    **/
    int updateByisRestrict(Long id);
}