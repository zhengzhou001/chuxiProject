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
import com.xinan.userService.sys.service.ISysRoleMenuService;
import com.xinan.userService.sys.entity.SysRoleMenuEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色菜单表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "角色菜单表")
@RequestMapping(value="/sys")
@Slf4j
public class SysRoleMenuController{
 	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	
	/**
	 * 增加角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
  	@ApiOperation(value = "增加角色菜单表记录", notes="根据sysRoleMenu实体对象增加角色菜单表")
	@RequestMapping(value={"/insertSysRoleMenu"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysRoleMenu(@RequestBody SysRoleMenuEntity sysRoleMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysRoleMenuService.insertSysRoleMenu(sysRoleMenuEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
  	@ApiOperation(value = "删除角色菜单表记录", notes="根据sysRoleMenu实体对象删除角色菜单表")
	@RequestMapping(value={"/deleteSysRoleMenu"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteSysRoleMenu(@RequestBody SysRoleMenuEntity sysRoleMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysRoleMenuService.deleteSysRoleMenu(sysRoleMenuEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
  	@ApiOperation(value = "修改角色菜单表记录", notes="根据sysRoleMenu实体对象修改角色菜单表")
	@RequestMapping(value={"/updateSysRoleMenu"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysRoleMenu(@RequestBody SysRoleMenuEntity sysRoleMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysRoleMenuService.updateSysRoleMenu(sysRoleMenuEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return List<SysRoleMenuEntity>返回符合条件的角色菜单表实体对象结果集
 	 */
 	@ApiOperation(value = "查询角色菜单表记录", notes="根据sysRoleMenu实体对象查询角色菜单表")
	@RequestMapping(value={"/selectSysRoleMenu"}, method={RequestMethod.POST})
	public	BaseResult<List<SysRoleMenuEntity>> selectSysRoleMenu(@RequestBody SysRoleMenuEntity sysRoleMenuEntity){
        BaseResult<List<SysRoleMenuEntity>> baseResult = new BaseResult<List<SysRoleMenuEntity>>();
        try{
            baseResult.setData(sysRoleMenuService.selectSysRoleMenu(sysRoleMenuEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询角色菜单表记录数
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return int返回符合条件的角色菜单表实体对象个数
 	 */
 	@ApiOperation(value = "查询角色菜单表记录个数", notes="根据sysRoleMenu实体对象查询角色菜单表记录个数")
	@RequestMapping(value={"/selectSysRoleMenuCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSysRoleMenuCount(@RequestBody SysRoleMenuEntity sysRoleMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(sysRoleMenuService.selectSysRoleMenuCount(sysRoleMenuEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}