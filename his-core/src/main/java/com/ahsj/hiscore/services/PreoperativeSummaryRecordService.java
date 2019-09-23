package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.PreoperativeSummaryRecord;
import core.message.Message;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: PreoperativeSummaryRecordService
 * <p>
 * Date:     2019/9/17 13:30
 *
 * @author XJP
 * @create 2019/9/17
 * @since 1.0.0
 */

public interface PreoperativeSummaryRecordService {

    /**
     *@功能说明
     *@Params [id]
     *@return com.ahsj.hiscore.entity.PreoperativeSummaryRecord
     *@Author XJP
     *@Date 2019/9/17
     *@Time 13:42
    **/
    PreoperativeSummaryRecord selectByPrimaryKey(Long id) throws Exception;

    /**
     *@功能说明
     *@Params [preoperativeSummaryRecord]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/9/17
     *@Time 13:44
    **/
    Message addOrUpdatePreoperativeSummaryRecord(PreoperativeSummaryRecord preoperativeSummaryRecord) throws Exception;
}
