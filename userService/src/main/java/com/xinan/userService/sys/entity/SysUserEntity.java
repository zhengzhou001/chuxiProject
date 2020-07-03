package com.xinan.userService.sys.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-04-13 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>系统用户表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="系统用户表")
@Data
public class SysUserEntity extends BaseEntity{
    @ApiModelProperty(hidden = true)
    private Integer  flag;
    @ApiModelProperty(hidden = true)
    private String roleName;
    @ApiModelProperty(hidden = true)
    private Integer useridOther ;
	@ApiModelProperty(value="备注：用户id，类型：int(11)")
    private Integer id ;
    @ApiModelProperty(hidden = true)
    private Integer id_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer id_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer id_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer id_NEW ;
	@ApiModelProperty(value="备注：登录账号，类型：varchar(20)")
    private String account ;
    @ApiModelProperty(hidden = true)
    private String account_LIKE ;
    @ApiModelProperty(hidden = true)
    private String account_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String account_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String account_NEW ;
	@ApiModelProperty(value="备注：登录密码，类型：varchar(255)")
    private String pwd ;
    @ApiModelProperty(hidden = true)
    private String pwd_LIKE ;
    @ApiModelProperty(hidden = true)
    private String pwd_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String pwd_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String pwd_NEW ;
	@ApiModelProperty(value="备注：状态：1启用 0禁用，类型：int(2)")
    private Integer state ;
    @ApiModelProperty(hidden = true)
    private Integer state_LIKE ;
    @ApiModelProperty(hidden = true)
    private Integer state_LLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer state_RLIKE ;
    @ApiModelProperty(hidden = true)
    private Integer state_NEW ;
	@ApiModelProperty(value="备注：姓名，类型：varchar(20)")
    private String name ;
    @ApiModelProperty(hidden = true)
    private String name_LIKE ;
    @ApiModelProperty(hidden = true)
    private String name_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String name_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String name_NEW ;
	@ApiModelProperty(value="备注：手机号码，类型：varchar(11)")
    private String phone ;
    @ApiModelProperty(hidden = true)
    private String phone_LIKE ;
    @ApiModelProperty(hidden = true)
    private String phone_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String phone_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String phone_NEW ;
	@ApiModelProperty(value="备注：电子邮箱，类型：varchar(50)")
    private String email ;
    @ApiModelProperty(hidden = true)
    private String email_LIKE ;
    @ApiModelProperty(hidden = true)
    private String email_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String email_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String email_NEW ;
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
	@ApiModelProperty(value="备注：最后登录时间，类型：datetime")
    private String loginDate ;
    @ApiModelProperty(hidden = true)
	private String loginDate_MIN ;
    @ApiModelProperty(hidden = true)
    private String loginDate_MAX ;
    @ApiModelProperty(hidden = true)
    private String loginDate_NEW ;

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

    @ApiModelProperty(value="备注：登录ip，类型：varchar(50)")
    private String ip ;
    @ApiModelProperty(value="备注：登录地址，类型：varchar(50)")
    private String addr ;
}