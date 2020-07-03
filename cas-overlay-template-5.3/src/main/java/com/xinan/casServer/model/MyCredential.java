package com.xinan.casServer.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

public class MyCredential extends RememberMeUsernamePasswordCredential {
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.captcha)
                .toHashCode();
    }
}
