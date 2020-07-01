package com.dingbo.chuxi.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

//页面路由
@Controller
@RequestMapping(value = "/route")
public class RouteConteoller {

    @RequestMapping("/index")
    public ModelAndView index() {
        //首页
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/index/index");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", "除夕网络");
        model.put("gsjj", "www.abaidu.com");
        model.put("foot", "© 2020 除夕网络by丁波");

        return modelAndView;
    }
}
