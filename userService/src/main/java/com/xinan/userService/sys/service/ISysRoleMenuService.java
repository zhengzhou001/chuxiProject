package com.xinan.userService.sys.service;

import java.util.List;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.userService.sys.entity.SysRoleMenuEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色菜单表Service接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface ISysRoleMenuService extends IBaseService{
	/**
	 * 增加角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int insertSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception;
	
	/**
	 * 删除角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int deleteSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception;
	
	/**
	 * 修改角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int updateSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception;
	/**
	 * 查询角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return List<SysRoleMenuEntity>返回符合条件的角色菜单表实体对象结果集
 	 */
	public	List<SysRoleMenuEntity> selectSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception;
	
	/**
	 * 查询角色菜单表记录数
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return int返回符合条件的角色菜单表实体对象个数
 	 */
	public	int selectSysRoleMenuCount(SysRoleMenuEntity sysRoleMenuEntity);
}