package ${basePackage}.entity;

import com.xinan.distributeCore.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <ol>
 * date:${date} editor:${editor}
 * <li>创建文档</li>
 * <li>${tabelComments}实体对象</li>
 * </ol>
 *
 * @author <a href="${author}">${editor}</a>
 * @version 1.0
 * @since 1.0
 */
@ApiModel(value="${tabelComments}")
@Data
public class ${className}Entity extends BaseEntity{
    <#list colList  as col>
	<#assign DATA_TYPE='${col.DATA_TYPE}'>
	<#if DATA_TYPE=="DATE">
	@ApiModelProperty(value="备注：${col.COMMENTS}，类型：${col.COLUMN_TYPE}")
    private ${col.javaType} ${col.COLUMN_NAME_HUMP} ;
    @ApiModelProperty(hidden = true)
	private ${col.javaType} ${col.COLUMN_NAME_HUMP}_MIN ;
    @ApiModelProperty(hidden = true)
    private ${col.javaType} ${col.COLUMN_NAME_HUMP}_MAX ;
    @ApiModelProperty(hidden = true)
    private ${col.javaType} ${col.COLUMN_NAME_HUMP}_NEW ;
	<#else>
	@ApiModelProperty(value="备注：${col.COMMENTS}，类型：${col.COLUMN_TYPE}")
    private ${col.javaType} ${col.COLUMN_NAME_HUMP} ;
    @ApiModelProperty(hidden = true)
    private ${col.javaType} ${col.COLUMN_NAME_HUMP}_LIKE ;
    @ApiModelProperty(hidden = true)
    private ${col.javaType} ${col.COLUMN_NAME_HUMP}_LLIKE ;
    @ApiModelProperty(hidden = true)
    private ${col.javaType} ${col.COLUMN_NAME_HUMP}_RLIKE ;
    @ApiModelProperty(hidden = true)
    private ${col.javaType} ${col.COLUMN_NAME_HUMP}_NEW ;
	</#if>
    </#list>
}