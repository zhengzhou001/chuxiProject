package com.xinan.userService.sys.controller;

import com.alibaba.fastjson.JSON;
import com.xinan.distributeCore.result.BaseResult;
import com.xinan.userService.security.entity.UserEntity;
import com.xinan.userService.sys.entity.SysMenuEntity;
import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysRoleMenuEntity;
import com.xinan.userService.sys.service.ISysMenuService;
import com.xinan.userService.sys.service.ISysRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * <li>菜单表Controller</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Api(value = "菜单表")
@RequestMapping(value="/sys")
@Slf4j
public class SysMenuController{
 	@Autowired
	private ISysMenuService sysMenuService;
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;

	
	/**
	 * 增加菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
  	@ApiOperation(value = "增加菜单表记录", notes="根据sysMenu实体对象增加菜单表")
	@RequestMapping(value={"/insertSysMenu"}, method={RequestMethod.POST})
	public BaseResult<Integer> insertSysMenu(@RequestBody SysMenuEntity sysMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
    		baseResult.setData(sysMenuService.insertSysMenu(sysMenuEntity));
		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 删除菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
  	@ApiOperation(value = "删除菜单表记录", notes="根据sysMenu实体对象删除菜单表")
	@RequestMapping(value={"/deleteSysMenu"}, method={RequestMethod.POST})
	public BaseResult<Integer> deleteSysMenu(@RequestBody SysMenuEntity sysMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
		SysRoleMenuEntity sysRoleMenuEntity=new SysRoleMenuEntity();

        try{
				//根据菜单表id set到菜单角色表中的menuid
				sysRoleMenuEntity.setMenuid(sysMenuEntity.getId());
			    //再根据menuid删除菜单角色表相关信息
				baseResult.setData(sysRoleMenuService.deleteSysRoleMenu(sysRoleMenuEntity));
				baseResult.setData(sysMenuService.deleteSysMenu(sysMenuEntity));

		} catch (Exception e) {
    		baseResult.code=-1;
    		baseResult.msg=e.getMessage();
    		log.error(baseResult.msg);
		}
    	return baseResult;
	}
	
	/**
	 * 修改菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
  	@ApiOperation(value = "修改菜单表记录", notes="根据sysMenu实体对象修改菜单表")
	@RequestMapping(value={"/updateSysMenu"}, method={RequestMethod.POST})
	public BaseResult<Integer> updateSysMenu(@RequestBody SysMenuEntity sysMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
		SysMenuEntity sysMenuEntity1=new SysMenuEntity();
		sysMenuEntity1.setPid(sysMenuEntity.getPid());
		sysMenuEntity1.setName(sysMenuEntity.getName_NEW());
        try{
            int sqlId=0;
            //通过页面传参appid和name去数据库查询数据
            List<SysMenuEntity> sysMenuEntities = sysMenuService.selectSysMenu(sysMenuEntity1);
			System.out.println(sysMenuEntities);
            //若返回值为空代表数据库无此菜单名称，可执行修改操作
            if (sysMenuEntities.isEmpty()){
                baseResult.setData(sysMenuService.updateSysMenu(sysMenuEntity));
            }else{
                for (SysMenuEntity sysMenuEntity2:sysMenuEntities
                ) {
                    sqlId=sysMenuEntity2.getId();
                }
                //若查询得到的id和页面传来的id相同，代表正是要修改的这个，同意修改操作
                if (sqlId==sysMenuEntity.getId()){
                    baseResult.setData(sysMenuService.updateSysMenu(sysMenuEntity));
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
	 * 查询菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
	 * @return List<SysMenuEntity>返回符合条件的菜单表实体对象结果集
 	 */
 	@ApiOperation(value = "查询菜单表记录", notes="根据sysMenu实体对象查询菜单表")
	//@RequestMapping(value={"/selectSysMenu"}, method={RequestMethod.POST})
	@RequestMapping(value={"/selectSysMenu"})
	@PreAuthorize("hasAuthority('getmenu')")//权限控制
 	public	BaseResult<List<SysMenuEntity>> selectSysMenu(@RequestBody SysMenuEntity sysMenuEntity){
		UserEntity user =  JSON.parseObject(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), UserEntity.class);
		BaseResult<List<SysMenuEntity>> baseResult = new BaseResult<List<SysMenuEntity>>();
		baseResult.setMsg("------selectSysMenu--------"+user.getName());
		System.out.println("------selectSysMenu--------" + user.getName());
		try{
            baseResult.setData(sysMenuService.selectSysMenu(sysMenuEntity));
 		} catch (Exception e) {
            baseResult.code=-1;
            baseResult.msg=e.getMessage();
            log.error(baseResult.msg);
		}
		return baseResult;
	}
	
	/**
	 * 查询菜单表记录数
	 * @param SysMenuEntity 菜单表实体对象
	 * @return int返回符合条件的菜单表实体对象个数
 	 */
 	@ApiOperation(value = "查询菜单表记录个数", notes="根据sysMenu实体对象查询菜单表记录个数")
	@RequestMapping(value={"/selectSysMenuCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSysMenuCount(@RequestBody SysMenuEntity sysMenuEntity){
        BaseResult<Integer> baseResult = new BaseResult<Integer>();
        try{
			baseResult.setData(sysMenuService.selectSysMenuCount(sysMenuEntity));
 		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

	//根据角色查询菜单
	/**
	 * 查询菜单表记录数
	 * @param SysMenuEntity 菜单表实体对象
	 * @return int返回符合条件的菜单表实体对象个数
	 */
	@ApiOperation(value = "查询菜单表记录个数", notes="根据sysMenu实体对象查询菜单表记录个数")
	@RequestMapping(value={"/selectMenuByRole"}, method={RequestMethod.POST})
	public	BaseResult<List<SysMenuEntity>> selectMenuByRole(@RequestBody SysRoleEntity sysRoleEntity){
		BaseResult<List<SysMenuEntity>> baseResult = new BaseResult<List<SysMenuEntity>>();
		try{
			baseResult.setData(sysMenuService.selectMenuByRole(sysRoleEntity));
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}
	@ApiOperation(value = "保存角色权限")
	@RequestMapping(value={"/insertMenuByRole"}, method={RequestMethod.POST})
	public	BaseResult<Integer> insertMenuByRole(@RequestBody Map map){
		BaseResult<Integer> baseResult = new BaseResult<Integer>();
		String menuids = MapUtils.getString(map,"menuids","");
		String roleid = MapUtils.getString(map,"roleid","");
		try{
			baseResult.setData(sysMenuService.insertMenuByRole(menuids,roleid));
		} catch (Exception e) {
			baseResult.code=-1;
			baseResult.msg=e.getMessage();
			log.error(baseResult.msg);
		}
		return baseResult;
	}

}