package com.dingbo.chuxi.sys.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:2020-07-10 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户头像表实体对象</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="用户头像表")
@Data
public class SysUserHeadEntity extends BaseEntity{
	@ApiModelProperty(value="备注：，类型：int(11)")
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
	@ApiModelProperty(value="备注：创建时间，类型：datetime")
    private String ceeateDate ;
    @ApiModelProperty(hidden = true)
	private String ceeateDate_MIN ;
    @ApiModelProperty(hidden = true)
    private String ceeateDate_MAX ;
    @ApiModelProperty(hidden = true)
    private String ceeateDate_NEW ;
	@ApiModelProperty(value="备注：用户文件名字，类型：varchar(255)")
    private String userFileName ;
    @ApiModelProperty(hidden = true)
    private String userFileName_LIKE ;
    @ApiModelProperty(hidden = true)
    private String userFileName_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String userFileName_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String userFileName_NEW ;
	@ApiModelProperty(value="备注：文件后缀，类型：varchar(255)")
    private String fileExt ;
    @ApiModelProperty(hidden = true)
    private String fileExt_LIKE ;
    @ApiModelProperty(hidden = true)
    private String fileExt_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String fileExt_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String fileExt_NEW ;
	@ApiModelProperty(value="备注：上传后文件名字，类型：varchar(255)")
    private String fileName ;
    @ApiModelProperty(hidden = true)
    private String fileName_LIKE ;
    @ApiModelProperty(hidden = true)
    private String fileName_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String fileName_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String fileName_NEW ;
	@ApiModelProperty(value="备注：文件路径，类型：varchar(255)")
    private String filePath ;
    @ApiModelProperty(hidden = true)
    private String filePath_LIKE ;
    @ApiModelProperty(hidden = true)
    private String filePath_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String filePath_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String filePath_NEW ;
	@ApiModelProperty(value="备注：是否展示，类型：varchar(255)")
    private String isShow ;
    @ApiModelProperty(hidden = true)
    private String isShow_LIKE ;
    @ApiModelProperty(hidden = true)
    private String isShow_LLIKE ;
    @ApiModelProperty(hidden = true)
    private String isShow_RLIKE ;
    @ApiModelProperty(hidden = true)
    private String isShow_NEW ;
}