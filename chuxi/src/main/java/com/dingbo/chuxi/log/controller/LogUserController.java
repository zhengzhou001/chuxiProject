package com.dingbo.chuxi.log.controller;

import com.dingbo.chuxi.log.entity.LogUserEntity;
import com.dingbo.chuxi.log.service.ILogUserService;
import com.xinan.distributeCore.entity.PageEntity;
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

/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户日志Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "用户日志")
@RequestMapping(value="/log")
@Slf4j
public class LogUserController{
 	@Autowired
	private ILogUserService logUserService;
	
	/**
	 * 增加用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
  	@ApiOperation(value = "增加用户日志记录", notes="根据logUser实体对象增加用户日志",hidden = true)
	@RequestMapping(value={"/insertLogUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertLogUser(@RequestBody LogUserEntity logUserEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(logUserService.insertLogUser(logUserEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
  	@ApiOperation(value = "删除用户日志记录", notes="根据logUser实体对象删除用户日志",hidden = true)
	@RequestMapping(value={"/deleteLogUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteLogUser(@RequestBody LogUserEntity logUserEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(logUserService.deleteLogUser(logUserEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
  	@ApiOperation(value = "修改用户日志记录", notes="根据logUser实体对象修改用户日志",hidden = true)
	@RequestMapping(value={"/updateLogUser"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateLogUser(@RequestBody LogUserEntity logUserEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(logUserService.updateLogUser(logUserEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询用户日志记录
	 * @param LogUserEntity 用户日志实体对象
	 * @return List<LogUserEntity>返回符合条件的用户日志分页对象
	 */
	@ApiOperation(value = "查询用户日志记录", notes="根据logUser实体对象查询用户日志",hidden = true)
	@RequestMapping(value={"/selectLogUser"}, method={RequestMethod.POST})
	public	BaseResult<PageEntity<LogUserEntity>> selectLogUser(@RequestBody LogUserEntity logUserEntity){
		BaseResult<PageEntity<LogUserEntity>> baseResult = new BaseResult<PageEntity<LogUserEntity>>();
		try{
			PageEntity<LogUserEntity> pageEntity = new PageEntity<LogUserEntity>();
			List<LogUserEntity> rows = logUserService.selectLogUser(logUserEntity);
			pageEntity.setRows(rows);
			if(rows != null && rows.size() > 0){
				LogUserEntity entity = rows.get(0);
				pageEntity.setTotal(entity.getTotal());
			}else{
				pageEntity.setTotal(0);
			}
			baseResult.setData(pageEntity);
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询用户日志记录数
	 * @param LogUserEntity 用户日志实体对象
	 * @return int返回符合条件的用户日志实体对象个数
 	 */
 	@ApiOperation(value = "查询用户日志记录个数", notes="根据logUser实体对象查询用户日志记录个数",hidden = true)
	@RequestMapping(value={"/selectLogUserCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectLogUserCount(@RequestBody LogUserEntity logUserEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(logUserService.selectLogUserCount(logUserEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}