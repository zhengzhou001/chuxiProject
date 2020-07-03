package com.xinan.zuul;

import com.xinan.zuul.filter.PreRequest;
import com.xinan.zuul.filter.PreResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.xinan"})
public class Start {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Start.class).web(true).run(args);
    }

    @LoadBalanced //使用负载均衡机制
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public PreRequest preRequest() {
        return new PreRequest();
    }

    @Bean
    public PreResponse preResponse() {
        return new PreResponse();
    }
}
