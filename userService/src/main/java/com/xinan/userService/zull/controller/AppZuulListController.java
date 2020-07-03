package com.xinan.userService.zull.controller;

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
import com.xinan.userService.zull.service.IAppZuulListService;
import com.xinan.userService.zull.entity.AppZuulListEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用列表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "应用列表")
@RequestMapping(value="/zull")
@Slf4j
public class AppZuulListController{
 	@Autowired
	private IAppZuulListService appZuulListService;
	
	/**
	 * 增加应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
  	@ApiOperation(value = "增加应用列表记录", notes="根据appZuulList实体对象增加应用列表")
	@RequestMapping(value={"/insertAppZuulList"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertAppZuulList(@RequestBody AppZuulListEntity appZuulListEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulListService.insertAppZuulList(appZuulListEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
  	@ApiOperation(value = "删除应用列表记录", notes="根据appZuulList实体对象删除应用列表")
	@RequestMapping(value={"/deleteAppZuulList"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteAppZuulList(@RequestBody AppZuulListEntity appZuulListEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulListService.deleteAppZuulList(appZuulListEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
  	@ApiOperation(value = "修改应用列表记录", notes="根据appZuulList实体对象修改应用列表")
	@RequestMapping(value={"/updateAppZuulList"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateAppZuulList(@RequestBody AppZuulListEntity appZuulListEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(appZuulListService.updateAppZuulList(appZuulListEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
	 * @return List<AppZuulListEntity>返回符合条件的应用列表实体对象结果集
 	 */
 	@ApiOperation(value = "查询应用列表记录", notes="根据appZuulList实体对象查询应用列表")
	@RequestMapping(value={"/selectAppZuulList"}, method={RequestMethod.POST})
	public	BaseResult<List<AppZuulListEntity>> selectAppZuulList(@RequestBody AppZuulListEntity appZuulListEntity){
        BaseResult<List<AppZuulListEntity>> baseResult = new BaseResult<List<AppZuulListEntity>>();
        try{
            baseResult.setData(appZuulListService.selectAppZuulList(appZuulListEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询应用列表记录数
	 * @param AppZuulListEntity 应用列表实体对象
	 * @return int返回符合条件的应用列表实体对象个数
 	 */
 	@ApiOperation(value = "查询应用列表记录个数", notes="根据appZuulList实体对象查询应用列表记录个数")
	@RequestMapping(value={"/selectAppZuulListCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectAppZuulListCount(@RequestBody AppZuulListEntity appZuulListEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(appZuulListService.selectAppZuulListCount(appZuulListEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}