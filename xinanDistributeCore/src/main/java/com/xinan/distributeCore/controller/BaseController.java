package com.xinan.distributeCore.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author <a href="mailto:2449709309@qq.com">丁双波</a>
 *         2020/3/19 8:50
 */
@Data
public class BaseController<T> {
    @ApiModelProperty(value = "返回代码")
    public int code =0;
    @ApiModelProperty(value = "返回信息")
    public String msg="操作成功";
    @ApiModelProperty(value = "返回数据")
    public T data;
}
