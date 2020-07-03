package com.xinan.userService.sys.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户角色表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="用户角色表")
@Data
public class SysUserRoleEntity extends BaseEntity{
	@ApiModelProperty(value="备注：主键，类型：int(11)")
    private Integer id ;
    @ApiModelProperty(hidden = true)
    private Integer id_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer id_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer id_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer id_NEW ;
	@ApiModelProperty(value="备注：用户id，类型：int(11)")
    private Integer userid ;
    @ApiModelProperty(hidden = true)
    private Integer userid_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer userid_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer userid_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer userid_NEW ;
	@ApiModelProperty(value="备注：角色id，类型：int(11)")
    private Integer roleid ;
    @ApiModelProperty(hidden = true)
    private Integer roleid_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer roleid_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer roleid_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer roleid_NEW ;
	@ApiModelProperty(value="备注：创建时间，类型：datetime")
    private String createDate ;
    @ApiModelProperty(hidden = true)
	private String createDate_MIN ;
    @ApiModelProperty(hidden = true)
    private String createDate_MAX ;
    @ApiModelProperty(hidden = true)
    private String createDate_NEW ;
}