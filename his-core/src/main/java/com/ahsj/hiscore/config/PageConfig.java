/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: PageConfig
 * Author:   anhuishangjue
 * Date:     2019/7/4 21:26
 * Description: PageConfig
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.hiscore.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈PageConfig〉
 *
 * @author anhuishangjue
 * @create 2019/7/4
 * @since 1.0.0
 */

@Configuration
public class PageConfig {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
