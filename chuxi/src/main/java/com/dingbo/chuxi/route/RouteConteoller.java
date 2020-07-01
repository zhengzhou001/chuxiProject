package com.dingbo.chuxi.route;

import com.dingbo.chuxi.common.ChuXiConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

//页面路由
@Controller
@RequestMapping(value = "/route")
public class RouteConteoller {

    @GetMapping("/index")
    public ModelAndView index() {
        //首页
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/index/index");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-登陆");
        model.put("foot", ChuXiConstants.FOOT);
        model.put("gsjj", "www.abaidu.com");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        //登录
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/user/login");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-登陆");
        model.put("foot", ChuXiConstants.FOOT);
        return modelAndView;
    }
}
