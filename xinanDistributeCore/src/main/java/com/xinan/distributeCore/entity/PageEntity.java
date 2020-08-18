package com.xinan.distributeCore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "分页对象")
@Data
public class PageEntity<T> {
    @ApiModelProperty(value = "返回总数")
    public  int  total =0;
    @ApiModelProperty(value = "当前页数据")
    public List<T> rows;
}
