package com.xinan.userService.sys.controller;

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
import com.xinan.userService.sys.service.ISysUserRoleService;
import com.xinan.userService.sys.entity.SysUserRoleEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户角色表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "用户角色表")
@RequestMapping(value="/sys")
@Slf4j
public class SysUserRoleController{
 	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	/**
	 * 增加用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
  	@ApiOperation(value = "增加用户角色表记录", notes="根据sysUserRole实体对象增加用户角色表")
	@RequestMapping(value={"/insertSysUserRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysUserRole(@RequestBody SysUserRoleEntity sysUserRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserRoleService.insertSysUserRole(sysUserRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
  	@ApiOperation(value = "删除用户角色表记录", notes="根据sysUserRole实体对象删除用户角色表")
	@RequestMapping(value={"/deleteSysUserRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteSysUserRole(@RequestBody SysUserRoleEntity sysUserRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserRoleService.deleteSysUserRole(sysUserRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
  	@ApiOperation(value = "修改用户角色表记录", notes="根据sysUserRole实体对象修改用户角色表")
	@RequestMapping(value={"/updateSysUserRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysUserRole(@RequestBody SysUserRoleEntity sysUserRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserRoleService.updateSysUserRole(sysUserRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
	 * @return List<SysUserRoleEntity>返回符合条件的用户角色表实体对象结果集
 	 */
 	@ApiOperation(value = "查询用户角色表记录", notes="根据sysUserRole实体对象查询用户角色表")
	@RequestMapping(value={"/selectSysUserRole"}, method={RequestMethod.POST})
	public	BaseResult<List<SysUserRoleEntity>> selectSysUserRole(@RequestBody SysUserRoleEntity sysUserRoleEntity){
        BaseResult<List<SysUserRoleEntity>> baseResult = new BaseResult<List<SysUserRoleEntity>>();
        try{
            baseResult.setData(sysUserRoleService.selectSysUserRole(sysUserRoleEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询用户角色表记录数
	 * @param SysUserRoleEntity 用户角色表实体对象
	 * @return int返回符合条件的用户角色表实体对象个数
 	 */
 	@ApiOperation(value = "查询用户角色表记录个数", notes="根据sysUserRole实体对象查询用户角色表记录个数")
	@RequestMapping(value={"/selectSysUserRoleCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSysUserRoleCount(@RequestBody SysUserRoleEntity sysUserRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(sysUserRoleService.selectSysUserRoleCount(sysUserRoleEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}