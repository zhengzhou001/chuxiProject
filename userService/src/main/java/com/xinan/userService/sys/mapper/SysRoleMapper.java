package com.xinan.userService.sys.mapper;

import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysRoleMapper {
	/**
	 * 增加角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int insertSysRole(SysRoleEntity sysRoleEntity);
	
	/**
	 * 删除角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int deleteSysRole(SysRoleEntity sysRoleEntity);
	
	/**
	 * 修改角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int updateSysRole(SysRoleEntity sysRoleEntity);
	/**
	 * 查询角色表记录
	 * @param SysRoleEntity 角色表实体对象
	 * @return List<SysRoleEntity>返回符合条件的角色表实体对象结果集
 	 */
	public	List<SysRoleEntity> selectSysRole(SysRoleEntity sysRoleEntity);
	
	/**
	 * 查询角色表记录个数
	 * @param SysRoleEntity 角色表实体对象
	 * @return int返回符合条件的角色表实体对象个数
 	 */
	public	int selectSysRoleCount(SysRoleEntity sysRoleEntity);

	//根据用户id获取角色
	public	List<SysRoleEntity> getUserRole(SysUserEntity sysUserEntity);

	//根据用户id获取角色 设置角色用
	public	List<SysRoleEntity> getUserRoleOther(SysUserEntity sysUserEntity);

	//删除子角色的多余菜单
	public int deleteSysRoleMenuByPidChildrens(Map map);
}