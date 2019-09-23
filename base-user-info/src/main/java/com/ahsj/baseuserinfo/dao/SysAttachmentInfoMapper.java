package com.ahsj.baseuserinfo.dao;


import com.ahsj.baseuserinfo.entity.SysAttachmentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAttachmentInfoMapper extends BaseMapper<SysAttachmentInfo>{
    int deleteByPrimaryKey(Long id);

    int insert(SysAttachmentInfo sysAttachmentInfo);

    int insertSelective(SysAttachmentInfo sysAttachmentInfo);

    SysAttachmentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAttachmentInfo sysAttachmentInfo);

    int updateByPrimaryKey(SysAttachmentInfo sysAttachmentInfo);

    void insertList(List<SysAttachmentInfo> attachmentInfoList);

    List<SysAttachmentInfo> querySysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo);
}