package com.xinan.zuul.app.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>接口调用日志表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="接口调用日志表")
@Data
public class AppZuulLogEntity extends BaseEntity{
	@ApiModelProperty(value="备注：主键，类型：decimal(18,0)")
    private String id ;
    @ApiModelProperty(hidden = true)
    private String id_LIKE ;
    @ApiModelProperty(hidden = true)
    private String id_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String id_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String id_NEW ;
	@ApiModelProperty(value="备注：应用id，类型：int(11)")
    private int appid ;
    @ApiModelProperty(hidden = true)
    private int appid_LIKE ;
    @ApiModelProperty(hidden = true)
    private int appid_LLIKE ;
    @ApiModelProperty(hidden = true)
    private int appid_RLIKE ;
    @ApiModelProperty(hidden = true)
    private int appid_NEW ;
	@ApiModelProperty(value="备注：应用名字，类型：varchar(255)")
    private String appname ;
    @ApiModelProperty(hidden = true)
    private String appname_LIKE ;
    @ApiModelProperty(hidden = true)
    private String appname_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String appname_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String appname_NEW ;
	@ApiModelProperty(value="备注：请求地址，类型：varchar(255)")
    private String reqAddr ;
    @ApiModelProperty(hidden = true)
    private String reqAddr_LIKE ;
    @ApiModelProperty(hidden = true)
    private String reqAddr_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String reqAddr_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String reqAddr_NEW ;
	@ApiModelProperty(value="备注：创建时间，类型：datetime")
    private String createDate ;
    @ApiModelProperty(hidden = true)
	private String createDate_MIN ;
    @ApiModelProperty(hidden = true)
    private String createDate_MAX ;
    @ApiModelProperty(hidden = true)
    private String createDate_NEW ;
	@ApiModelProperty(value="备注：结束时间，类型：datetime")
    private String endDate ;
    @ApiModelProperty(hidden = true)
	private String endDate_MIN ;
    @ApiModelProperty(hidden = true)
    private String endDate_MAX ;
    @ApiModelProperty(hidden = true)
    private String endDate_NEW ;
	@ApiModelProperty(value="备注：请求参数，类型：varchar(4000)")
    private String reqParam ;
    @ApiModelProperty(hidden = true)
    private String reqParam_LIKE ;
    @ApiModelProperty(hidden = true)
    private String reqParam_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String reqParam_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String reqParam_NEW ;
	@ApiModelProperty(value="备注：返回数据，类型：varchar(4000)")
    private String retBody ;
    @ApiModelProperty(hidden = true)
    private String retBody_LIKE ;
    @ApiModelProperty(hidden = true)
    private String retBody_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String retBody_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String retBody_NEW ;
	@ApiModelProperty(value="备注：执行间隔时间，类型：int(11)")
    private int time ;
    @ApiModelProperty(hidden = true)
    private int time_LIKE ;
    @ApiModelProperty(hidden = true)
    private int time_LLIKE ;
    @ApiModelProperty(hidden = true)
    private int time_RLIKE ;
    @ApiModelProperty(hidden = true)
    private int time_NEW ;
}