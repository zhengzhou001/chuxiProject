package com.xinan.distributeCore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseEntity {
	@ApiModelProperty(hidden = true)
	public int total;
	@ApiModelProperty(hidden = true)
	public int rowno;
	@ApiModelProperty(hidden = true)
	public String sortName;
	@ApiModelProperty(hidden = true)
	public String sortOrder="DESC";
	@ApiModelProperty(hidden = true)
	public int curPage=1;
	@ApiModelProperty(hidden = true)
	public int pageSize=1000000;

	 

}
