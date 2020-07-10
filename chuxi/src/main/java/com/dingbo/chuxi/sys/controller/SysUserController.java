package com.dingbo.chuxi.sys.controller;

import com.dingbo.chuxi.log.mapper.LogUserMapper;
import com.dingbo.chuxi.sys.entity.SysUserEntity;
import com.dingbo.chuxi.sys.service.ISysUserService;
import com.xinan.distributeCore.result.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "用户表")
@RequestMapping(value="/sys")
@Slf4j
public class SysUserController{
 	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private LogUserMapper logUserMapper;
	
	/**
	 * 增加用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
  	@ApiOperation(value = "增加用户表记录", notes="根据sysUser实体对象增加用户表")
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
	 * 删除用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
  	@ApiOperation(value = "删除用户表记录", notes="根据sysUser实体对象删除用户表")
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
	 * 修改用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
  	@ApiOperation(value = "修改用户表记录", notes="根据sysUser实体对象修改用户表")
	@RequestMapping(value={"/updateSysUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysUser(@RequestBody SysUserEntity sysUserEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserService.updateSysUser(sysUserEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询用户表记录
	 * @param SysUserEntity 用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的用户表实体对象结果集
 	 */
 	@ApiOperation(value = "查询用户表记录", notes="根据sysUser实体对象查询用户表")
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
	 * 查询用户表记录数
	 * @param SysUserEntity 用户表实体对象
	 * @return int返回符合条件的用户表实体对象个数
 	 */
 	@ApiOperation(value = "查询用户表记录个数", notes="根据sysUser实体对象查询用户表记录个数")
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


	/**
	 * 登陆
	 */
	@ApiOperation(value = "登陆接口")
	@RequestMapping(value={"/login"}, method={RequestMethod.POST})
	public	BaseResult<SysUserEntity> login(@RequestBody SysUserEntity sysUserEntity){
		BaseResult<SysUserEntity> result;
		try{
			result = sysUserService.updateLogin(sysUserEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
			return BaseResult.getInstance(-1,e.getMessage());
		}
		return result;
	}

	/**
	 * 注册
	 */
	@ApiOperation(value = "注册接口")
	@RequestMapping(value={"/register"}, method={RequestMethod.POST})
	public	BaseResult<SysUserEntity> register(@RequestBody SysUserEntity sysUserEntity){
		BaseResult<SysUserEntity> result;
		try{
			result = sysUserService.updateRegister(sysUserEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
			return BaseResult.getInstance(-1,e.getMessage());
		}
		return result;
	}

	/**
	 * 退出
	 */
	@ApiOperation(value = "退出接口")
	@RequestMapping(value={"/logout"}, method={RequestMethod.POST})
	public	BaseResult logout(@RequestBody Map map){
		try{
			sysUserService.updateLogout(map);
		} catch (Exception e) {
			log.error(e.getMessage());
			return BaseResult.getInstance(-1,e.getMessage());
		}
		return BaseResult.getInstance();
	}

}