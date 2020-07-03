package com.xinan.userService.sys.controller;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.userService.sys.entity.SysMenuEntity;
import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysUserEntity;
import com.xinan.userService.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <ol>
 * date:2020-04-13 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>系统用户表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "系统用户表")
@RequestMapping(value="/sys")
@Slf4j
public class SysUserController{
 	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 增加系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
  	@ApiOperation(value = "增加系统用户表记录", notes="根据sysUser实体对象增加系统用户表")
	@RequestMapping(value={"/insertSysUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysUser(@RequestBody SysUserEntity sysUserEntity){

        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserService.insertSysUser(sysUserEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
  	@ApiOperation(value = "删除系统用户表记录", notes="根据sysUser实体对象删除系统用户表")
	@RequestMapping(value={"/deleteSysUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteSysUser(@RequestBody SysUserEntity sysUserEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserService.deleteSysUser(sysUserEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
  	@ApiOperation(value = "修改系统用户表记录", notes="根据sysUser实体对象修改系统用户表")
	@RequestMapping(value={"/updateSysUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysUser(@RequestBody SysUserEntity sysUserEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
		SysUserEntity sysUserEntity1=new SysUserEntity();
		sysUserEntity1.setName(sysUserEntity.getName_NEW());
        try{
			int sqlId=0;
			//通过页面传参name去数据库查询数据
			List<SysUserEntity> sysUserEntityList = sysUserService.selectSysUser(sysUserEntity1);
			//若返回值为空代表数据库无此菜单名称，可执行修改操作
			if (sysUserEntityList.isEmpty()){
				baseResult.setData(sysUserService.updateSysUser(sysUserEntity));
			}else{
				for (SysUserEntity sysUserEntity2:sysUserEntityList
				) {
					sqlId=sysUserEntity2.getId();
				}
				//若查询得到的id和页面传来的id相同，代表正是要修改的这个，同意修改操作
				if (sqlId==sysUserEntity.getId()){
					baseResult.setData(sysUserService.updateSysUser(sysUserEntity));
				}else{
					//查到数据，但id不同，代表数据库另有一个相同的菜单名，不允许修改
					baseResult.code=-1;
					baseResult.msg="菜单名称已存在，请重新填写";
				}
			}
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的系统用户表实体对象结果集
 	 */
 	@ApiOperation(value = "查询系统用户表记录", notes="根据sysUser实体对象查询系统用户表")
	@RequestMapping(value={"/selectSysUser"}, method={RequestMethod.POST})
	public	BaseResult<List<SysUserEntity>> selectSysUser(@RequestBody SysUserEntity sysUserEntity){


        BaseResult<List<SysUserEntity>> baseResult = new BaseResult<List<SysUserEntity>>();
        try{
            baseResult.setData(sysUserService.selectSysUser(sysUserEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询系统用户表记录数
	 * @param SysUserEntity 系统用户表实体对象
	 * @return int返回符合条件的系统用户表实体对象个数
 	 */
 	@ApiOperation(value = "查询系统用户表记录个数", notes="根据sysUser实体对象查询系统用户表记录个数")
	@RequestMapping(value={"/selectSysUserCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSysUserCount(@RequestBody SysUserEntity sysUserEntity){


        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(sysUserService.selectSysUserCount(sysUserEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

	//登录接口
	@ApiOperation(value = "登录接口", notes="根据sysUser实体对象登录")
	@RequestMapping(value={"/login"}, method={RequestMethod.POST})
	public BaseResult<SysUserEntity> login(@RequestBody SysUserEntity sysUserEntity){
		BaseResult<SysUserEntity> baseResult = new BaseResult<SysUserEntity>();
		try{
			baseResult = sysUserService.updateLogin(sysUserEntity);
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

	@ApiOperation(value = "获取角色权限", notes="根据sysUser实体对象获取角色权限")
	@RequestMapping(value={"/getUserRoleMenu"}, method={RequestMethod.POST})
	public BaseResult<List<SysMenuEntity>> getUserRoleMenu(@RequestBody SysUserEntity sysUserEntity){
		BaseResult<List<SysMenuEntity>> baseResult = new BaseResult<List<SysMenuEntity>>();
		try{
			baseResult = sysUserService.getUserRoleMenu(sysUserEntity);
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

	@ApiOperation(value = "获取用户角色", notes="根据sysUser实体对象获取角色数据")
	@RequestMapping(value={"/getUserRole"}, method={RequestMethod.POST})
	public BaseResult<List<SysRoleEntity>> getUserRole(@RequestBody SysUserEntity sysUserEntity){
		BaseResult<List<SysRoleEntity>> baseResult = new BaseResult<List<SysRoleEntity>>();
		try{
			baseResult = sysUserService.getUserRole(sysUserEntity);
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	/**
	 * 批量删除系统用户表记录
	 * @param map 用map从前台接收数组信息，在转出来
	 * @return
	 */
	@ApiOperation(value = "批量删除系统用户表记录", notes="根据传递Integer数组批量删除系统用户表")
	@RequestMapping(value={"/multiDeleteSysUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> multiDeleteSysUser(@RequestBody Map map ){
		Integer[] ids = ((ArrayList<Integer>) map.get("ids")).toArray(new Integer[0]);
		BaseResult<Integer> baseResult = new BaseResult<Integer>();
		try{
			baseResult.setData(sysUserService.multiDeleteSysUser(ids));
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

	//用户中心添加管理员用户
	@RequestMapping(value={"/insertSysUserAdmin"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysUserAdmin(@RequestBody SysUserEntity sysUserEntity){

		BaseResult<Integer> baseResult = new BaseResult<Integer>();
		try{
			baseResult=sysUserService.insertSysUserAdmin(sysUserEntity);
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

}