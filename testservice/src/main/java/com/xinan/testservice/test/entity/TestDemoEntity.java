package com.xinan.testservice.test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>示例表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="示例表")
@Data
public class TestDemoEntity extends BaseEntity{
	@ApiModelProperty(value="备注：主键，类型：{DATA_TYPE:NUMBER,DATA_LENGTH:22,DATA_PRECISION:18,DATA_SCALE:0}")
    private String id ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id_LIKE ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id_LLIKE ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id_RLIKE ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id_NEW ;
	@ApiModelProperty(value="备注：名字，类型：{DATA_TYPE:VARCHAR2,DATA_LENGTH:15,DATA_PRECISION:,DATA_SCALE:}")
    private String name ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name_LIKE ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name_LLIKE ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name_RLIKE ;
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name_NEW ;
	@ApiModelProperty(value="备注：创建时间，类型：{DATA_TYPE:DATE,DATA_LENGTH:7,DATA_PRECISION:,DATA_SCALE:}")
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
}