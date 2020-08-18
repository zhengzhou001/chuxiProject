package com.xinan.distributeCore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseEntity {
	@ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public int total;
	@ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public int rowno;
	@ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String sortName;
	@ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String sortOrder="DESC";
	@ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public int curPage=1;
	@ApiModelProperty(hidden = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public int pageSize=1000000;

	 

}
