package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.SurgicalRecord;
import core.message.Message;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SurgicalRecordService
 * <p>
 * Date:     2019/9/17 16:07
 *
 * @author XJP
 * @create 2019/9/17
 * @since 1.0.0
 */

public interface SurgicalRecordService {
    /**
     * @return com.ahsj.hiscore.entity.SurgicalRecord
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 16:12
     **/
    SurgicalRecord selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [surgicalRecord]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 16:12
     **/
    Message addOrUpdateSurgicalRecord(SurgicalRecord surgicalRecord) throws Exception;
}
