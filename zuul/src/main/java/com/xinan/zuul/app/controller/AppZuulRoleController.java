package com.xinan.zuul.app.controller;

import com.xinan.distributeCore.result.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xinan.zuul.app.service.IAppZuulRoleService;
import com.xinan.zuul.app.entity.AppZuulRoleEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用权限表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "应用权限表")
@RequestMapping(value="/app")
@Slf4j
public class AppZuulRoleController{
 	@Autowired
	private IAppZuulRoleService appZuulRoleService;
	
	/**
	 * 增加应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
  	@ApiOperation(value = "增加应用权限表记录", notes="根据appZuulRole实体对象增加应用权限表")
	@RequestMapping(value={"/insertAppZuulRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertAppZuulRole(@RequestBody AppZuulRoleEntity appZuulRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulRoleService.insertAppZuulRole(appZuulRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
  	@ApiOperation(value = "删除应用权限表记录", notes="根据appZuulRole实体对象删除应用权限表")
	@RequestMapping(value={"/deleteAppZuulRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteAppZuulRole(@RequestBody AppZuulRoleEntity appZuulRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulRoleService.deleteAppZuulRole(appZuulRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
  	@ApiOperation(value = "修改应用权限表记录", notes="根据appZuulRole实体对象修改应用权限表")
	@RequestMapping(value={"/updateAppZuulRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateAppZuulRole(@RequestBody AppZuulRoleEntity appZuulRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulRoleService.updateAppZuulRole(appZuulRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return List<AppZuulRoleEntity>返回符合条件的应用权限表实体对象结果集
 	 */
 	@ApiOperation(value = "查询应用权限表记录", notes="根据appZuulRole实体对象查询应用权限表")
	@RequestMapping(value={"/selectAppZuulRole"}, method={RequestMethod.POST})
	public	BaseResult<List<AppZuulRoleEntity>> selectAppZuulRole(@RequestBody AppZuulRoleEntity appZuulRoleEntity){
        BaseResult<List<AppZuulRoleEntity>> baseResult = new BaseResult<List<AppZuulRoleEntity>>();
        try{
            baseResult.setData(appZuulRoleService.selectAppZuulRole(appZuulRoleEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询应用权限表记录数
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return int返回符合条件的应用权限表实体对象个数
 	 */
 	@ApiOperation(value = "查询应用权限表记录个数", notes="根据appZuulRole实体对象查询应用权限表记录个数")
	@RequestMapping(value={"/selectAppZuulRoleCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectAppZuulRoleCount(@RequestBody AppZuulRoleEntity appZuulRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(appZuulRoleService.selectAppZuulRoleCount(appZuulRoleEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}