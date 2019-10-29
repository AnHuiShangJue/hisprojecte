package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysAttachmentInfoMapper
 * <p>
 * Date:     2019/10/28 10:03
 *
 * @author XJP
 * @create 2019/10/28
 * @since 1.0.0
 */

@Mapper
public interface SysAttachmentInfoMapper {
    

    /**
     *@功能说明 
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo>
     *@Author XJP
     *@Date 2019/10/28
     *@Time 10:12
    **/
    List<SysAttachmentInfo> queryList(PageBean<SysAttachmentInfo> pageBean);
}
