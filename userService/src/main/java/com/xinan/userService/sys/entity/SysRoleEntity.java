package com.xinan.userService.sys.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="角色表")
@Data
public class SysRoleEntity extends BaseEntity{
    @ApiModelProperty(hidden = true)
    private Integer  pidAgain;
    @ApiModelProperty(hidden = true)
    private boolean  checked;
    @ApiModelProperty(hidden = true)
    private String ids ;
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
	@ApiModelProperty(value="备注：名字，类型：varchar(255)")
    private String name ;
    @ApiModelProperty(hidden = true)
    private String name_LIKE ;
    @ApiModelProperty(hidden = true)
    private String name_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String name_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String name_NEW ;
	@ApiModelProperty(value="备注：状态 0禁用1启用，类型：int(2)")
    private int state ;
    @ApiModelProperty(hidden = true)
    private int state_LIKE ;
    @ApiModelProperty(hidden = true)
    private int state_LLIKE ;
    @ApiModelProperty(hidden = true)
    private int state_RLIKE ;
    @ApiModelProperty(hidden = true)
    private int state_NEW ;
	@ApiModelProperty(value="备注：父id，类型：int(11)")
    private int pid ;
    @ApiModelProperty(hidden = true)
    private int pid_LIKE ;
    @ApiModelProperty(hidden = true)
    private int pid_LLIKE ;
    @ApiModelProperty(hidden = true)
    private int pid_RLIKE ;
    @ApiModelProperty(hidden = true)
    private int pid_NEW ;
	@ApiModelProperty(value="备注：创建时间，类型：datetime")
    private String createDate ;
    @ApiModelProperty(hidden = true)
	private String createDate_MIN ;
    @ApiModelProperty(hidden = true)
    private String createDate_MAX ;
    @ApiModelProperty(hidden = true)
    private String createDate_NEW ;
	@ApiModelProperty(value="备注：修改时间，类型：datetime")
    private String updateDate ;
    @ApiModelProperty(hidden = true)
	private String updateDate_MIN ;
    @ApiModelProperty(hidden = true)
    private String updateDate_MAX ;
    @ApiModelProperty(hidden = true)
    private String updateDate_NEW ;
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
}