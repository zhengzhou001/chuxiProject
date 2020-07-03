package com.xinan.userService.sys.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>菜单表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="菜单表")
@Data
public class SysMenuEntity extends BaseEntity{
    @ApiModelProperty(value="备注：是否在用户中心展示 1 展示 0 不展示，类型：int(2)")
    private Integer  flag;
    @ApiModelProperty(hidden = true)
    private Integer  flag_NEW;
    @ApiModelProperty(hidden = true)
    private Integer  pidAgain;
    @ApiModelProperty(hidden = true)
    private boolean  checked;
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
	@ApiModelProperty(value="备注：父id，类型：int(11)")
    private Integer pid ;
    @ApiModelProperty(hidden = true)
    private Integer pid_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer pid_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer pid_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer pid_NEW ;
	@ApiModelProperty(value="备注：状态 0禁用 1启用，类型：int(11)")
    private Integer state ;
    @ApiModelProperty(hidden = true)
    private Integer state_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer state_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer state_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer state_NEW ;
	@ApiModelProperty(value="备注：链接地址，类型：varchar(255)")
    private String url ;
    @ApiModelProperty(hidden = true)
    private String url_LIKE ;
    @ApiModelProperty(hidden = true)
    private String url_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String url_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String url_NEW ;
	@ApiModelProperty(value="备注：排序id，类型：int(11)")
    private Integer orderid ;
    @ApiModelProperty(hidden = true)
    private Integer orderid_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer orderid_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer orderid_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer orderid_NEW ;
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
    private Integer appid ;
    @ApiModelProperty(hidden = true)
    private Integer appid_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer appid_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer appid_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer appid_NEW ;
}