package com.xinan.testservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {


    public static final String RESOURCE_ID = "r1";

    @Autowired
    TokenStore tokenStore;
    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)//资源 id
                .tokenStore(tokenStore)
                 .authenticationEntryPoint(authExceptionEntryPoint)//认证异常处理类
                .accessDeniedHandler(customAccessDeniedHandler)//权限异常处理类
                 .stateless(true);
     }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
