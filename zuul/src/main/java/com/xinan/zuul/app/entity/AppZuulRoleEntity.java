package com.xinan.zuul.app.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用权限表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="应用权限表")
@Data
public class AppZuulRoleEntity extends BaseEntity{
	@ApiModelProperty(value="备注：主键，类型：int(11)")
    private int id ;
    @ApiModelProperty(hidden = true)
    private int id_LIKE ;
    @ApiModelProperty(hidden = true)
    private int id_LLIKE ;
    @ApiModelProperty(hidden = true)
    private int id_RLIKE ;
    @ApiModelProperty(hidden = true)
    private int id_NEW ;
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
	@ApiModelProperty(value="备注：权限，类型：varchar(255)")
    private String role ;
    @ApiModelProperty(hidden = true)
    private String role_LIKE ;
    @ApiModelProperty(hidden = true)
    private String role_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String role_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String role_NEW ;
	@ApiModelProperty(value="备注：状态 0禁用，1启用，类型：int(1)")
    private int state ;
    @ApiModelProperty(hidden = true)
    private int state_LIKE ;
    @ApiModelProperty(hidden = true)
    private int state_LLIKE ;
    @ApiModelProperty(hidden = true)
    private int state_RLIKE ;
    @ApiModelProperty(hidden = true)
    private int state_NEW ;
	@ApiModelProperty(value="备注：备注，类型：varchar(255)")
    private String note ;
    @ApiModelProperty(hidden = true)
    private String note_LIKE ;
    @ApiModelProperty(hidden = true)
    private String note_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String note_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String note_NEW ;
}