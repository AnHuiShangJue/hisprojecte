package com.ahsj.wxapplet.dao;

import com.ahsj.wxapplet.entity.SelectEntity;
import com.ahsj.wxapplet.entity.UserInfo;
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

    UserInfo selectByUserId(String userId)throws Exception;

    @Nullable
    UserInfo selectByUserName(String userName);

    public List<UserInfo> listUserByOrgId(PageBean<UserInfo> pageEntity);

    public List<UserInfo> listNotSelectUserByOrgId(PageBean<UserInfo> pageEntity);

    @NotNull
    List<UserInfo> list(@NotNull PageBean<UserInfo> pageBean);


    List<SelectEntity> listUserInfo(@NotNull UserInfo sysUserInfo);

    List<UserInfo> selectByDeptId(String deptId);

    UserInfo getUserLoginId(String userId);

    UserInfo getUserId(Long id);
}