package com.xinan.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 本文件由周涛创建,位于com.tao.spring.config包下
 * 创建时间2018/1/26 20:42
 * 邮箱:zhoutao@xiaodouwangluo.com
 * 作用:创建ES的client对象，方便后期使用
 */

@Configuration
public class ElasticSearchConfig {

    @Bean
    public TransportClient client() throws UnknownHostException{
		//注意这里的端口是TCP端口9300,而非HTTP接口9200
        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("192.168.0.176"),9300);
		//机器名称，可以首页查询，这个不能出现错误，否则无法连接ES
        Settings settings=Settings.builder().put("cluster.name","my-application").build();
        TransportClient client = TransportClient.builder().settings(settings).build();
         client.addTransportAddress(node);
         return client;
    }
}