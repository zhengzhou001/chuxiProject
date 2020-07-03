package com.xinan.springbootCasClient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
     * @author yellowcong
     * 创建日期:2018/02/05
     * 设定首页
     */
    @Configuration
    public class DefaultView extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            //设定首页为index
            registry.addViewController("/").setViewName("forward:/index");

            //设定匹配的优先级
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

            //添加视图控制类
            super.addViewControllers(registry);
        }
    }