package com.xinan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.zuul.app.entity.AppZuulLogEntity;
import com.xinan.zuul.app.service.IAppZuulLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:2449709309@qq.com">丁双波</a>
 * @date   2020/3/18 17:11
 */
@Slf4j
public class PreResponse extends ZuulFilter {
    @Autowired
    private IAppZuulLogService appZuulLogService;
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE; // 可以在请求被路由之后调用
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER-2; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
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
        //获取返回数据
        InputStream stream = ctx.getResponseDataStream();
        String body = "";
        try {
            body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
        }catch (Exception e){
            log.error(e.getMessage());
        }
         if (StringUtils.isEmpty(body)){
             body=ctx.getResponseBody();
         }
         ctx.setResponseBody(body);
        ctx.addZuulResponseHeader("Content-Type", "application/json;charset=UTF-8");
        //更新日志
        try {
            String logid =ctx.get("appZuulLogId").toString();
            if (StringUtils.isEmpty(logid)){
                return null;
            }
            AppZuulLogEntity appZuulLogEntity = new AppZuulLogEntity();
            appZuulLogEntity.setId(logid);
            try {
                String beginDate=ctx.get("beginDate").toString();
                String endDate=BaseTools.getCurStrDate(1);
                appZuulLogEntity.setTime_NEW((int) BaseTools.getBetweenTime(beginDate,endDate));
                appZuulLogEntity.setEndDate_NEW(endDate);
            }catch (Exception e){
                log.error(e.getMessage());
            }
            appZuulLogEntity.setRetBody_NEW(StringUtils.substring(body,0,2000));
            appZuulLogService.updateAppZuulLog(appZuulLogEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
