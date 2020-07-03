package com.xinan.userService.sys.service;

import com.xinan.distributeCore.service.IBaseService;
import com.xinan.userService.sys.entity.SysRoleEntity;

import java.util.List;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色表Service接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface ISysRoleService extends IBaseService{
	/**
	 * 增加角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int insertSysRole(SysRoleEntity sysRoleEntity) throws Exception;
	
	/**
	 * 删除角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int deleteSysRole(SysRoleEntity sysRoleEntity) throws Exception;
	
	/**
	 * 修改角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int updateSysRole(SysRoleEntity sysRoleEntity) throws Exception;
	/**
	 * 查询角色表记录
	 * @param SysRoleEntity 角色表实体对象
	 * @return List<SysRoleEntity>返回符合条件的角色表实体对象结果集
 	 */
	public	List<SysRoleEntity> selectSysRole(SysRoleEntity sysRoleEntity) throws Exception;
	
	/**
	 * 查询角色表记录数
	 * @param SysRoleEntity 角色表实体对象
	 * @return int返回符合条件的角色表实体对象个数
 	 */
	public	int selectSysRoleCount(SysRoleEntity sysRoleEntity);

	//保存用户角
	public int insertRoleByUser(String roleids ,String useridOther );
}