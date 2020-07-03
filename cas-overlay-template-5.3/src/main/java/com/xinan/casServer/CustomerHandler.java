package com.xinan.casServer;


import com.xinan.casServer.exception.CaptchaException;
import com.xinan.casServer.model.MyCredential;
import com.xinan.casServer.model.User;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
    @Generated
    private final static Logger logger = LoggerFactory.getLogger(CustomerHandler.class);

    private JdbcTemplate jdbcTemplate;

    public CustomerHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order, JdbcTemplate jdbcTemplate) {
        super(name, servicesManager, principalFactory, order);
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {

        //2020-06-02dingshuangbo
        //自定义表单
        MyCredential myCredential = (MyCredential) credential;
        String username = myCredential.getUsername();
        String password = myCredential.getPassword();
        logger.info("认证用户 username = {}", username);
        String requestCaptcha = myCredential.getCaptcha();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object attribute = attributes.getRequest().getSession().getAttribute("captcha");

        String realCaptcha = attribute == null ? null : attribute.toString();

        if (StringUtils.isBlank(requestCaptcha) || !requestCaptcha.equalsIgnoreCase(realCaptcha)) {
            throw new CaptchaException("验证码错误");
        }

        String sql = "select id, account, pwd, name FROM users where account = ?";
        //2020-06-02dingshuangbo
        //验证账号密码
        User info = (User) jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper(User.class));
        if (info == null) {
            logger.info("用户不存在！");
            throw new AccountException("用户不存在!");
        }

        // DigestUtils.md5DigestAsHex  spring自带的md5加密
        if (!StringUtils.equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes()), info.getPwd())) {
            logger.info("密码错误!");
            throw new FailedLoginException("密码错误!");
        } else {
            logger.info("校验成功");
            //可自定义返回给客户端的多个属性信息
            HashMap<String, Object> returnInfo = new HashMap<>();
            returnInfo.put("id", info.getId());
            returnInfo.put("account", info.getAccount());
            returnInfo.put("name", info.getName());

            final List<MessageDescriptor> list = new ArrayList<>();
            return createHandlerResult(credential, this.principalFactory.createPrincipal(username, returnInfo), list);
        }

    }

    // 判断是否支持自定义用户登入凭证
    //2020-06-03dingshuangbo
    //默认返回false,如果返回false则doAuthentication不执行
    @Override
    public boolean supports(Credential credential) {
         return credential instanceof MyCredential;
    }
}
