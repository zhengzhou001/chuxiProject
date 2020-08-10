package com.xinan.authservice.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //认证管理器
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**", "/oauth/**", "/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
        ;
        //开启表单登录
        http.formLogin().loginPage("/oauth/loginPage")//设置访问登录页面的路径
                .loginProcessingUrl("/oauth/loginAction");//设置执行登录操作的路径
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/loginPage",
                "/oauth/logout","/oauth/loginAction","/oauth/refreshToken","/oauth/getApi","/login.html","/css/**","/data/**","/fonts/**","/imgages/**","/js/**","/favicon.ico");
    }


}