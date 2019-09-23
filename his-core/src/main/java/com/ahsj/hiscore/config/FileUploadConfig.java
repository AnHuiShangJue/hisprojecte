package com.ahsj.hiscore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-19-22-14
 **/
//@Configuration
public class FileUploadConfig {

    @Bean
    public MultipartResolver custom() {
        return new CommonsMultipartResolver();
    }
}

    