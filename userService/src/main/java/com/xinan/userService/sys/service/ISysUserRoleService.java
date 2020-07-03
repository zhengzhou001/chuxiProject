package com.xinan.userService.sys.service;

import java.util.List;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.userService.sys.entity.SysUserRoleEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户角色表Service接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface ISysUserRoleService extends IBaseService{
	/**
	 * 增加用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
	public int insertSysUserRole(SysUserRoleEntity sysUserRoleEntity) throws Exception;
	
	/**
	 * 删除用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
	public int deleteSysUserRole(SysUserRoleEntity sysUserRoleEntity) throws Exception;
	
	/**
	 * 修改用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
  	 */
	public int updateSysUserRole(SysUserRoleEntity sysUserRoleEntity) throws Exception;
	/**
	 * 查询用户角色表记录
	 * @param SysUserRoleEntity 用户角色表实体对象
	 * @return List<SysUserRoleEntity>返回符合条件的用户角色表实体对象结果集
 	 */
	public	List<SysUserRoleEntity> selectSysUserRole(SysUserRoleEntity sysUserRoleEntity) throws Exception;
	
	/**
	 * 查询用户角色表记录数
	 * @param SysUserRoleEntity 用户角色表实体对象
	 * @return int返回符合条件的用户角色表实体对象个数
 	 */
	public	int selectSysUserRoleCount(SysUserRoleEntity sysUserRoleEntity);
}