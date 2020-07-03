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
import com.xinan.zuul.app.service.IAppZuulLogService;
import com.xinan.zuul.app.entity.AppZuulLogEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>接口调用日志表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "接口调用日志表")
@RequestMapping(value="/app")
@Slf4j
public class AppZuulLogController{
 	@Autowired
	private IAppZuulLogService appZuulLogService;
	
	/**
	 * 增加接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
  	@ApiOperation(value = "增加接口调用日志表记录", notes="根据appZuulLog实体对象增加接口调用日志表")
	@RequestMapping(value={"/insertAppZuulLog"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertAppZuulLog(@RequestBody AppZuulLogEntity appZuulLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulLogService.insertAppZuulLog(appZuulLogEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
  	@ApiOperation(value = "删除接口调用日志表记录", notes="根据appZuulLog实体对象删除接口调用日志表")
	@RequestMapping(value={"/deleteAppZuulLog"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteAppZuulLog(@RequestBody AppZuulLogEntity appZuulLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulLogService.deleteAppZuulLog(appZuulLogEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
  	@ApiOperation(value = "修改接口调用日志表记录", notes="根据appZuulLog实体对象修改接口调用日志表")
	@RequestMapping(value={"/updateAppZuulLog"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateAppZuulLog(@RequestBody AppZuulLogEntity appZuulLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulLogService.updateAppZuulLog(appZuulLogEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
	 * @return List<AppZuulLogEntity>返回符合条件的接口调用日志表实体对象结果集
 	 */
 	@ApiOperation(value = "查询接口调用日志表记录", notes="根据appZuulLog实体对象查询接口调用日志表")
	@RequestMapping(value={"/selectAppZuulLog"}, method={RequestMethod.POST})
	public	BaseResult<List<AppZuulLogEntity>> selectAppZuulLog(@RequestBody AppZuulLogEntity appZuulLogEntity){
        BaseResult<List<AppZuulLogEntity>> baseResult = new BaseResult<List<AppZuulLogEntity>>();
        try{
            baseResult.setData(appZuulLogService.selectAppZuulLog(appZuulLogEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询接口调用日志表记录数
	 * @param AppZuulLogEntity 接口调用日志表实体对象
	 * @return int返回符合条件的接口调用日志表实体对象个数
 	 */
 	@ApiOperation(value = "查询接口调用日志表记录个数", notes="根据appZuulLog实体对象查询接口调用日志表记录个数")
	@RequestMapping(value={"/selectAppZuulLogCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectAppZuulLogCount(@RequestBody AppZuulLogEntity appZuulLogEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(appZuulLogService.selectAppZuulLogCount(appZuulLogEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}