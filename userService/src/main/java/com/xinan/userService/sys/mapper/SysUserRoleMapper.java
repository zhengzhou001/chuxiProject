package com.xinan.userService.sys.mapper;

import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户角色表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysUserRoleMapper {
	/**
	 * 增加用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
	public int insertSysUserRole(SysUserRoleEntity sysUserRoleEntity);
	
	/**
	 * 删除用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
	public int deleteSysUserRole(SysUserRoleEntity sysUserRoleEntity);
	
	/**
	 * 修改用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
	public int updateSysUserRole(SysUserRoleEntity sysUserRoleEntity);
	/**
	 * 查询用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
	 * @return List<SysUserRoleEntity>返回符合条件的用户角色表实体对象结果集
 	 */
	public	List<SysUserRoleEntity> selectSysUserRole(SysUserRoleEntity sysUserRoleEntity);
	
	/**
	 * 查询用户角色表记录个数
	 * @param SysUserRoleEntity 用户角色表实体对象
	 * @return int返回符合条件的用户角色表实体对象个数
 	 */
	public	int selectSysUserRoleCount(SysUserRoleEntity sysUserRoleEntity);

	//批量删除角色
	public	int deleteSysRoleByIds(SysRoleEntity sysRoleEntity);
}