package com.xinan.zipkin;
 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
 public class Start {
	public static void main(String[] args) {

		new SpringApplicationBuilder(Start.class).web(true).run(args);
	}
	@LoadBalanced //使用负载均衡机制
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	 

}
