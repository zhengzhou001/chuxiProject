package com.dingbo.chuxi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 1、 extends WebMvcConfigurationSupport
     * 2、重写下面方法;
     * setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配；
     * setUseTrailingSlashMatch : 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //开启路径后缀匹配
        configurer.setUseRegisteredSuffixPatternMatch(false)
        .setUseTrailingSlashMatch(true);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设定首页为index
        registry.addViewController("/").setViewName("forward:/route/index");
        //设定匹配的优先级
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        //添加视图控制类
        super.addViewControllers(registry);
    }



}