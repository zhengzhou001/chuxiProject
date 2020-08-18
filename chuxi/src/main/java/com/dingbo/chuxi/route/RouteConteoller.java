package com.dingbo.chuxi.route;

import com.dingbo.chuxi.common.ChuXiConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

//页面路由
@Controller
@RequestMapping(value = "/route")
@ApiIgnore
public class RouteConteoller {

    @GetMapping("/index")
    public ModelAndView index() {
        //首页
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/index/index");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-首页");
        model.put("foot", ChuXiConstants.FOOT);
        model.put("gsjj", "www.abaidu.com");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        //登录
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/user/login");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-登陆");
        model.put("foot", ChuXiConstants.FOOT);
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        //登录
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/home/home");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-个人主页");
        model.put("foot", ChuXiConstants.FOOT);
        return modelAndView;
    }

    @GetMapping("/personInfo")
    public ModelAndView personInfo() {
        //登录
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/info/personInfo");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-个人信息");
        model.put("foot", ChuXiConstants.FOOT);
        return modelAndView;
    }

    @GetMapping("/personInfoA")
    public ModelAndView personInfoA() {
        //登录
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/info/personInfo1");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-个人信息");
        model.put("foot", ChuXiConstants.FOOT);
        return modelAndView;
    }


    @GetMapping("/test")
    public ModelAndView test() {
        //首页
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/test/test");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-测试");
        model.put("foot", ChuXiConstants.FOOT);
        model.put("gsjj", "www.abaidu.com");
        return modelAndView;
    }
    @GetMapping("/testA")
    public ModelAndView testA() {
        //首页
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/test/AA");
        Map<String, Object> model = modelAndView.getModel();
        model.put("title", ChuXiConstants.TITLE +"-测试");
        model.put("foot", ChuXiConstants.FOOT);
        model.put("gsjj", "www.abaidu.com");
        return modelAndView;
    }

}
