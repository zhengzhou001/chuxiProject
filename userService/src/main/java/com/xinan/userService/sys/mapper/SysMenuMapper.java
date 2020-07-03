package com.xinan.userService.sys.mapper;

import com.xinan.userService.sys.entity.SysMenuEntity;
import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysUserEntity;

import java.util.List;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>菜单表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysMenuMapper {
	/**
	 * 增加菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
	public int insertSysMenu(SysMenuEntity sysMenuEntity);
	
	/**
	 * 删除菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
	public int deleteSysMenu(SysMenuEntity sysMenuEntity);
	
	/**
	 * 修改菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
	public int updateSysMenu(SysMenuEntity sysMenuEntity);
	/**
	 * 查询菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
	 * @return List<SysMenuEntity>返回符合条件的菜单表实体对象结果集
 	 */
	public	List<SysMenuEntity> selectSysMenu(SysMenuEntity sysMenuEntity);
	
	/**
	 * 查询菜单表记录个数
	 * @param SysMenuEntity 菜单表实体对象
	 * @return int返回符合条件的菜单表实体对象个数
 	 */
	public	int selectSysMenuCount(SysMenuEntity sysMenuEntity);

	//根据用户id查询对应权限
	public List<SysMenuEntity> getUserRoleMenu(SysUserEntity sysUserEntity);
	//根据角色查询菜单
	public List<SysMenuEntity> selectMenuByRole(SysRoleEntity sysRoleEntity);

	//根据超管菜单
	public List<SysMenuEntity> selectMenuByAdminRole(SysRoleEntity sysRoleEntity);

	//为新应用创建基础菜单
	public	int insertBaseMenu(SysMenuEntity sysMenuEntity);
}