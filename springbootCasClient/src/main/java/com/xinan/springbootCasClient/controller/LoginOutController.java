package com.xinan.springbootCasClient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
 public class LoginOutController {


    @RequestMapping("/logout")
    public String loginOut2(HttpSession session) {
        session.invalidate();
        // 退出登录后，跳转到退成成功的页面，不走默认页面
        return "redirect:http://192.168.9.100:8080/cas/logout?service=http://192.168.0.193:8080/springbootCasClient/logout/success";
    }

    @RequestMapping("/logout/success")
     public String loginOut2() {
        return "logout";
    }

}
