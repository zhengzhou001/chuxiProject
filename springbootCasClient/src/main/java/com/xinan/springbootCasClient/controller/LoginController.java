package com.xinan.springbootCasClient.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController {


    @RequestMapping("/loginOut2")
    public String loginOut2(HttpSession session) {
        session.invalidate();
        // 退出登录后，跳转到退成成功的页面，不走默认页面
        return "redirect:http://yellowcong.com:9000/logout?service=http://yellowcong.com:8888/user/loginOut/success";
    }

    @RequestMapping("/loginOut/success")
    @ResponseBody
    public String loginOut2() {
        return "注销成功";
    }

}
