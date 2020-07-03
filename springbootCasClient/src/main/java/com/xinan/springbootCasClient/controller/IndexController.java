package com.xinan.springbootCasClient.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
         return "index";
    }


    @RequestMapping("/getUser")
    @ResponseBody
    public Assertion getUser(HttpServletRequest request) {
        //session的 key是 _const_cas_assertion_
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);

        return assertion;
    }
}
