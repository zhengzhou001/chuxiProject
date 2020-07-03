package com.xinan.userService.sys.controller;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "角色表")
@RequestMapping(value="/sys")
@Slf4j
public class SysRoleController{
 	@Autowired
	private ISysRoleService sysRoleService;

	/**
	 * 增加角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
  	@ApiOperation(value = "增加角色表记录", notes="根据sysRole实体对象增加角色表")
	@RequestMapping(value={"/insertSysRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysRole(@RequestBody SysRoleEntity sysRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysRoleService.insertSysRole(sysRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
  	@ApiOperation(value = "删除角色表记录", notes="根据sysRole实体对象删除角色表")
	@RequestMapping(value={"/deleteSysRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteSysRole(@RequestBody SysRoleEntity sysRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysRoleService.deleteSysRole(sysRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
  	@ApiOperation(value = "修改角色表记录", notes="根据sysRole实体对象修改角色表")
	@RequestMapping(value={"/updateSysRole"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysRole(@RequestBody SysRoleEntity sysRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysRoleService.updateSysRole(sysRoleEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询角色表记录
	 * @param SysRoleEntity 角色表实体对象
	 * @return List<SysRoleEntity>返回符合条件的角色表实体对象结果集
 	 */
 	@ApiOperation(value = "查询角色表记录", notes="根据sysRole实体对象查询角色表")
	@RequestMapping(value={"/selectSysRole"}, method={RequestMethod.POST})
	public	BaseResult<List<SysRoleEntity>> selectSysRole(@RequestBody SysRoleEntity sysRoleEntity){
        BaseResult<List<SysRoleEntity>> baseResult = new BaseResult<List<SysRoleEntity>>();
        try{
            baseResult.setData(sysRoleService.selectSysRole(sysRoleEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询角色表记录数
	 * @param SysRoleEntity 角色表实体对象
	 * @return int返回符合条件的角色表实体对象个数
 	 */
 	@ApiOperation(value = "查询角色表记录个数", notes="根据sysRole实体对象查询角色表记录个数")
	@RequestMapping(value={"/selectSysRoleCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSysRoleCount(@RequestBody SysRoleEntity sysRoleEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(sysRoleService.selectSysRoleCount(sysRoleEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

	@ApiOperation(value = "保存用户角色")
	@RequestMapping(value={"/insertRoleByUser"}, method={RequestMethod.POST})
	public	BaseResult<Integer> insertRoleByUser(@RequestBody Map map){
		BaseResult<Integer> baseResult = new BaseResult<Integer>();
		String roleids = MapUtils.getString(map,"roleids","");
		String useridOther = MapUtils.getString(map,"useridOther","");
		try{
			baseResult.setData(sysRoleService.insertRoleByUser(roleids,useridOther));
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}