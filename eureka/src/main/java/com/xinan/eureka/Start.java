package com.xinan.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author <a href="mailto:2449709309@qq.com">丁双波</a>
 * 2020/3/18 15:42
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class,args);
    }
}
