package com.xinan.distributeCore.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="mailto:2449709309@qq.com">丁双波</a>
 *         2020/3/23 9:13
 */
@ApiModel(value="通用返回对象")
public class BaseResult<T> {
    @ApiModelProperty(value="返回代码")
    public  int code =0;
    @ApiModelProperty(value="返回信息")
    public  String msg="操作成功";
    @ApiModelProperty(value="返回数据")
    public T data = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static<T> BaseResult<T> getInstance(int code,String msg,T data){
        BaseResult<T> result= new BaseResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return  result;

    }

    public static<T> BaseResult<T> getInstance(int code,String msg){
        BaseResult<T> result= new BaseResult<>();
        result.setCode(code);
        result.setMsg(msg);
         return  result;

    }

    public static<T> BaseResult<T> getInstance(String msg){
        BaseResult<T> result= new BaseResult<>();
        result.setCode(0);
        result.setMsg(msg);
        return  result;

    }

    public static<T> BaseResult<T> getInstance(T data){
        BaseResult<T> result= new BaseResult<>();
        result.setCode(0);
        result.setMsg("操作成功");
        result.setData(data);
        return  result;

    }
    public static<T> BaseResult<T> getInstance(){
        BaseResult<T> result= new BaseResult<>();
        result.setCode(0);
        result.setMsg("操作成功");
        return  result;

    }
}
