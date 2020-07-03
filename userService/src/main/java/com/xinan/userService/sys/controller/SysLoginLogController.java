package com.xinan.userService.sys.controller;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.userService.sys.entity.SysLoginLogEntity;
import com.xinan.userService.sys.entity.SysUserEntity;
import com.xinan.userService.sys.service.ISysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <ol>
 * date:2020-04-13 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户登录日志表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "用户登录日志表")
@RequestMapping(value="/sys")
@Slf4j
public class SysLoginLogController{
 	@Autowired
	private ISysLoginLogService sysLoginLogService;
	
	/**
	 * 增加用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
  	@ApiOperation(value = "增加用户登录日志表记录", notes="根据sysLoginLog实体对象增加用户登录日志表")
	@RequestMapping(value={"/insertSysLoginLog"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysLoginLog(@RequestBody SysLoginLogEntity sysLoginLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysLoginLogService.insertSysLoginLog(sysLoginLogEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
  	@ApiOperation(value = "删除用户登录日志表记录", notes="根据sysLoginLog实体对象删除用户登录日志表")
	@RequestMapping(value={"/deleteSysLoginLog"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteSysLoginLog(@RequestBody SysLoginLogEntity sysLoginLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysLoginLogService.deleteSysLoginLog(sysLoginLogEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
  	@ApiOperation(value = "修改用户登录日志表记录", notes="根据sysLoginLog实体对象修改用户登录日志表")
	@RequestMapping(value={"/updateSysLoginLog"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysLoginLog(@RequestBody SysLoginLogEntity sysLoginLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysLoginLogService.updateSysLoginLog(sysLoginLogEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
	 * @return List<SysLoginLogEntity>返回符合条件的用户登录日志表实体对象结果集
 	 */
 	@ApiOperation(value = "查询用户登录日志表记录", notes="根据sysLoginLog实体对象查询用户登录日志表")
	@RequestMapping(value={"/selectSysLoginLog"}, method={RequestMethod.POST})
	public	BaseResult<List<SysLoginLogEntity>> selectSysLoginLog(@RequestBody SysLoginLogEntity sysLoginLogEntity){
        BaseResult<List<SysLoginLogEntity>> baseResult = new BaseResult<List<SysLoginLogEntity>>();
        try{
            baseResult.setData(sysLoginLogService.selectSysLoginLog(sysLoginLogEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询用户登录日志表记录数
	 * @param SysLoginLogEntity 用户登录日志表实体对象
	 * @return int返回符合条件的用户登录日志表实体对象个数
 	 */
 	@ApiOperation(value = "查询用户登录日志表记录个数", notes="根据sysLoginLog实体对象查询用户登录日志表记录个数")
	@RequestMapping(value={"/selectSysLoginLogCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSysLoginLogCount(@RequestBody SysLoginLogEntity sysLoginLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(sysLoginLogService.selectSysLoginLogCount(sysLoginLogEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}


}