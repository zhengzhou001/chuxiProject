package com.xinan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xinan.zuul.app.service.IAppZuulListService;
import com.xinan.zuul.app.service.IAppZuulLogService;
import com.xinan.zuul.app.service.IAppZuulRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:2449709309@qq.com">丁双波</a>
 * 2020/3/18 17:03
 */
@Slf4j
public class PreRequest extends ZuulFilter {
    @Autowired
    private IAppZuulListService appZuulListService;
    @Autowired
    private IAppZuulRoleService appZuulRoleService;
    @Autowired
    private IAppZuulLogService appZuulLogService;

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }


    /**
     * 判断拦截
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        if(requestURI.contains("/auth-service/")){
            return false;
        }
        return true;
    }


    @Override
    public Object run() {
        return null;
    }
}
