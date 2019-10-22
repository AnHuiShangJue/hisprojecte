package com.ahsj.smartparkcore.controller.joinEnterpriseReview;

import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.dto.JoinEnterpriseReviewDTO;
import com.ahsj.smartparkcore.services.JoinEnterpriseReviewService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: JoinEnterpriseReviewController
 * <p>
 * Date:     2019/10/22 15:09
 *
 * @author XJP
 * @create 2019/10/22
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/joinenterprisereview")
public class JoinEnterpriseReviewController extends BaseController {

    @Autowired
    JoinEnterpriseReviewService joinEnterpriseReviewService;

    /**
     * @return org.springframework.http.ResponseEntity<core.message.Message>
     * @功能说明  APP
     * @Params [joinEnterpriseReviewDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/22
     * @Time 16:13
     **/
    @PostMapping("/add/joinenterprisereview.ahsj")
    public ResponseEntity<Message> addJoinEnterpriseReview(JoinEnterpriseReviewDTO joinEnterpriseReviewDTO, @RequestParam(value = "file", required = false) MultipartFile file, String relateKet, String relatePage) throws Exception {
        Message message = joinEnterpriseReviewService.addJoinEnterpriseReview(joinEnterpriseReviewDTO, file, relateKet, relatePage);
        return new ResponseEntity<>((message), HttpStatus.OK);
    }
}
