package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.dto.JoinEnterpriseReviewDTO;
import core.message.Message;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: JoinEnterpriseReviewService
 * <p>
 * Date:     2019/10/22 15:06
 *
 * @author XJP
 * @create 2019/10/22
 * @since 1.0.0
 */

public interface JoinEnterpriseReviewService {
    /**
     * @return core.message.Message
     * @功能说明
     * @Params [joinEnterpriseReviewDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/22
     * @Time 16:22
     **/
    Message addJoinEnterpriseReview(JoinEnterpriseReviewDTO joinEnterpriseReviewDTO, MultipartFile file, String relateKet, String relatePage) throws Exception;
}
