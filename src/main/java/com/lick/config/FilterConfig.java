package com.lick.config;

import com.lick.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description： 过滤器注册
 * @author: lick
 * @date: 2017年12月21日 13:40
 * @copyright: 版权归所有
 */
@Configuration
public class FilterConfig {

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
