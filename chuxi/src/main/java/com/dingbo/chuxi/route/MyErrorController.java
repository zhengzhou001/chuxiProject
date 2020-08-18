package com.dingbo.chuxi.route;

import com.xinan.distributeCore.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//页面路由
@Controller
@RequestMapping(value = "/")
@ApiIgnore
public class MyErrorController  implements ErrorController {
    /**
     * 错误路径
     */
    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    @Autowired
    public MyErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * web页面错误处理
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response) {

        //获取响应状态
        int status = response.getStatus();
        switch (status) {
            case 403:
                return "common/error/403";
            case 404:
                return "common/error/404";
            case 500:
                return "common/error/500";
        }
        return "front/index/index";
    }

    /**
     * 除web页面外的错误处理，比如JSON/XML等
     * @param request 请求
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public BaseResult<String> errorApiHandler(HttpServletRequest request) {
        BaseResult<String> baseResult = new BaseResult<>();
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        // 获取错误属性不包含错误栈
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);

        int status = getStatus(request);
        baseResult.setCode(status);
        baseResult.setMsg(attr.getOrDefault("message", "error").toString());
        return baseResult;
    }

    /**
     * 获取状态
     *
     * @param request 请求
     * @return
     */
    private int getStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            return status;
        }
        // 默认返回500
        return 500;
    }

}
