package com.xinan.userService.sys.mapper;

import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysRoleMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色菜单表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysRoleMenuMapper {
	/**
	 * 增加角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int insertSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity);
	
	/**
	 * 删除角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int deleteSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity);
	
	/**
	 * 修改角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int updateSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity);
	/**
	 * 查询角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return List<SysRoleMenuEntity>返回符合条件的角色菜单表实体对象结果集
 	 */
	public	List<SysRoleMenuEntity> selectSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity);
	
	/**
	 * 查询角色菜单表记录个数
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return int返回符合条件的角色菜单表实体对象个数
 	 */
	public	int selectSysRoleMenuCount(SysRoleMenuEntity sysRoleMenuEntity);

	//删除子角色的多余菜单
	public int deleteSysRoleMenuByPidChildrens(Map map);

	//添加基础角色赋权
	public	int insertBaseRoleMenu(SysRoleEntity sysRoleEntity);
}