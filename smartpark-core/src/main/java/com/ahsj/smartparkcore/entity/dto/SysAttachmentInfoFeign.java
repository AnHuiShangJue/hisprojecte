package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import core.entity.BaseEntity;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysAttachmentInfoFeign
 * <p>
 * Date:     2019/7/22 12:30
 *
 * @author XJP
 * @create 2019/7/22
 * @since 1.0.0
 */

public class SysAttachmentInfoFeign extends BaseEntity {

    List<SysAttachmentInfo> attachmentInfoList;

    public SysAttachmentInfoFeign() {
    }

    public List<SysAttachmentInfo> getAttachmentInfoList() {
        return attachmentInfoList;
    }

    public void setAttachmentInfoList(List<SysAttachmentInfo> attachmentInfoList) {
        this.attachmentInfoList = attachmentInfoList;
    }
}
