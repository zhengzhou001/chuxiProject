package com.xinan.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.zuul.app.entity.AppZuulListEntity;
import com.xinan.zuul.app.entity.AppZuulLogEntity;
import com.xinan.zuul.app.entity.AppZuulRoleEntity;
import com.xinan.zuul.app.service.IAppZuulListService;
import com.xinan.zuul.app.service.IAppZuulLogService;
import com.xinan.zuul.app.service.IAppZuulRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:2449709309@qq.com">丁双波</a>
 * 2020/3/18 17:03
 */
@Slf4j
public class PreRequestBAK extends ZuulFilter {
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

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String appid = request.getHeader("appid");
        String appname = request.getHeader("appname");
        String role = request.getRequestURI();

        //记录请求日志
        AppZuulLogEntity appZuulLogEntity = new AppZuulLogEntity();
        String id = BaseTools.getNextSeq();
        appZuulLogEntity.setId(id);
        String beginDate = BaseTools.getCurStrDate(1);
        appZuulLogEntity.setCreateDate(beginDate);
        ctx.set("beginDate", beginDate);//开始时间
        appZuulLogEntity.setReqAddr(role);
        appZuulLogEntity.setReqParam(StringUtils.substring(request.getParameterMap().toString(), 0, 2000));
        // 获取请求的输入流
        try {
            InputStream in = request.getInputStream();
            String reqBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            appZuulLogEntity.setReqParam(StringUtils.substring(reqBody, 0, 2000));
        } catch (Exception e) {

        }
        ctx.set("appZuulLogId", id);//日志ID
        if (StringUtils.isNotEmpty(appid)) {
            try {
                appZuulLogEntity.setAppid(Integer.parseInt(appid));
            } catch (Exception e) {

            }
        }
        appZuulLogEntity.setAppname(appname);

        try {
            //记录日志
            appZuulLogService.insertAppZuulLog(appZuulLogEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (StringUtils.isEmpty(appid)) {
            ctx.setSendZuulResponse(false); //不对其进行路由
            JSONObject json = new JSONObject();
            json.put("code", -1);
            json.put("msg", String.format("appid[%s]为空", appid));
            json.put("data", "");
            ctx.setResponseBody(json.toJSONString());
            ctx.set("isSuccess", false);

            return null;
        }
        if (StringUtils.isEmpty(appname)) {
            ctx.setSendZuulResponse(false); //不对其进行路由
            JSONObject json = new JSONObject();
            json.put("code", -1);
            json.put("msg", String.format("appname[%s]为空", appname));
            json.put("data", "");
            ctx.setResponseBody(json.toJSONString());
            ctx.set("isSuccess", false);
            return null;
        }
        //校验appid，appname是否存在
        AppZuulListEntity appListEntity = new AppZuulListEntity();
        appListEntity.setId(Integer.parseInt(appid));
        appListEntity.setAppname(appname);
        appListEntity.setState(1);
        int appCount = appZuulListService.selectAppZuulListCount(appListEntity);
        if (appCount != 1) {
            ctx.setSendZuulResponse(false); //不对其进行路由
            JSONObject json = new JSONObject();
            json.put("code", -1);
            json.put("msg", String.format("appid[%s],appname[%s]不存在", appid, appname));
            json.put("data", "");
            ctx.setResponseBody(json.toJSONString());
            ctx.set("isSuccess", false);
            return null;
        }
        //校验权限
        if (StringUtils.containsIgnoreCase(role, "login")||
                StringUtils.containsIgnoreCase(role, "getUserRoleMenu")) {

        } else {
            AppZuulRoleEntity appZuulRoleEntity = new AppZuulRoleEntity();
            appZuulRoleEntity.setAppid(Integer.parseInt(appid));
            appZuulRoleEntity.setState(1);
            appZuulRoleEntity.setRole(role);
            appCount = appZuulRoleService.selectAppZuulRoleCount(appZuulRoleEntity);
            if (appCount == 0) {
                ctx.setSendZuulResponse(false); //不对其进行路由
                JSONObject json = new JSONObject();
                json.put("code", -1);
                json.put("msg", String.format("appid[%s]没有[%s]权限，联系管理员授权", appid, role));
                json.put("data", "");
                ctx.setResponseBody(json.toJSONString());
                ctx.set("isSuccess", false);
                return null;
            }
        }


        return null;
    }
}
