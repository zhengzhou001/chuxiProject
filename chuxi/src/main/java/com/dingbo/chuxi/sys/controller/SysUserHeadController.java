package com.dingbo.chuxi.sys.controller;

import com.dingbo.chuxi.sys.entity.SysUserHeadEntity;
import com.dingbo.chuxi.sys.service.ISysUserHeadService;
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
 * date:2020-08-14 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户头像表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "用户头像表")
@RequestMapping(value="/sys")
@Slf4j
public class SysUserHeadController{
 	@Autowired
	private ISysUserHeadService sysUserHeadService;
	
	/**
	 * 增加用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
  	@ApiOperation(value = "增加用户头像表记录", notes="根据sysUserHead实体对象增加用户头像表",hidden = true)
	@RequestMapping(value={"/insertSysUserHead"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysUserHead(@RequestBody SysUserHeadEntity sysUserHeadEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserHeadService.insertSysUserHead(sysUserHeadEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
  	@ApiOperation(value = "删除用户头像表记录", notes="根据sysUserHead实体对象删除用户头像表",hidden = true)
	@RequestMapping(value={"/deleteSysUserHead"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteSysUserHead(@RequestBody SysUserHeadEntity sysUserHeadEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserHeadService.deleteSysUserHead(sysUserHeadEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
  	@ApiOperation(value = "修改用户头像表记录", notes="根据sysUserHead实体对象修改用户头像表",hidden = true)
	@RequestMapping(value={"/updateSysUserHead"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysUserHead(@RequestBody SysUserHeadEntity sysUserHeadEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysUserHeadService.updateSysUserHead(sysUserHeadEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	/**
	 * 查询用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
	 * @return List<SysUserHeadEntity>返回符合条件的用户头像表实体对象结果集
 	 */
	@ApiOperation(value = "查询用户头像表记录", notes="根据sysUserHead实体对象查询用户头像表",hidden = true)
	@RequestMapping(value={"/selectSysUserHead"}, method={RequestMethod.POST})
	public	BaseResult<PageEntity<SysUserHeadEntity>> selectSysUserHead(@RequestBody SysUserHeadEntity sysUserHeadEntity){
		BaseResult<PageEntity<SysUserHeadEntity>> baseResult = new BaseResult<PageEntity<SysUserHeadEntity>>();
		try{
			PageEntity<SysUserHeadEntity> pageEntity = new PageEntity<SysUserHeadEntity>();
			List<SysUserHeadEntity> rows = sysUserHeadService.selectSysUserHead(sysUserHeadEntity);
			pageEntity.setRows(rows);
			if(rows != null && rows.size() > 0){
				SysUserHeadEntity entity = rows.get(0);
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
	 * 查询用户头像表记录数
	 * @param SysUserHeadEntity 用户头像表实体对象
	 * @return int返回符合条件的用户头像表实体对象个数
 	 */
 	@ApiOperation(value = "查询用户头像表记录个数", notes="根据sysUserHead实体对象查询用户头像表记录个数",hidden = true)
	@RequestMapping(value={"/selectSysUserHeadCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSysUserHeadCount(@RequestBody SysUserHeadEntity sysUserHeadEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(sysUserHeadService.selectSysUserHeadCount(sysUserHeadEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
}