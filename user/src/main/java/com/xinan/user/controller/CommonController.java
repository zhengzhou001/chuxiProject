package com.xinan.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
@Slf4j
public class CommonController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/html/login/login.html?appid=2";
    }


}
