package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.PostoperativeRecord;
import core.message.Message;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: PostoperativeRecordService
 * <p>
 * Date:     2019/9/18 10:17
 *
 * @author XJP
 * @create 2019/9/18
 * @since 1.0.0
 */

public interface PostoperativeRecordService {
    /**
     * @return com.ahsj.hiscore.entity.PostoperativeRecord
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/18
     * @Time 10:28
     **/
    PostoperativeRecord selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [postoperativeRecord]
     * @Author XJP
     * @Date 2019/9/18
     * @Time 10:30
     **/
    Message addOrUpdatePostoperativeRecord(PostoperativeRecord postoperativeRecord) throws Exception;
}
