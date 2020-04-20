package org.xupt.cat.proxy.api.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xupt.cat.proxy.api.controller.aop.HttpSessionInterceptor;

/**
 * @author lining
 * @data 2020-04-11 下午6:56
 */
@Configuration
public class WebFilterConfig implements WebMvcConfigurer {

    @Autowired
    private HttpSessionInterceptor httpSessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpSessionInterceptor);
    }
}
