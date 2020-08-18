package com.dingbo.chuxi.log.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户日志实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="用户日志")
@Data
public class LogUserEntity extends BaseEntity{
	@ApiModelProperty(value="备注：日志id，类型：int(11)")
    private Integer id ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id_LIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id_LLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id_RLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id_NEW ;
	@ApiModelProperty(value="备注：用户id，类型：int(11)")
    private Integer userid ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userid_LIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userid_LLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userid_RLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userid_NEW ;
	@ApiModelProperty(value="备注：日志时间，类型：datetime")
    private String createDate ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String createDate_MIN ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createDate_MAX ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createDate_NEW ;
	@ApiModelProperty(value="备注：内容，类型：varchar(4000)")
    private String content ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String content_LIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String content_LLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String content_RLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String content_NEW ;

    @ApiModelProperty(value="备注：ip，类型：varchar(255)")
    private String ip ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String ip_LIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String ip_LLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String ip_RLIKE ;
    @ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String ip_NEW ;
}