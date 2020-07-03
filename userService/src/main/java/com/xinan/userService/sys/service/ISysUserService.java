package com.xinan.userService.sys.service;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.userService.sys.entity.SysMenuEntity;
import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysUserEntity;

import java.util.List;

/**
 * <ol>
 * date:2020-04-10 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>系统用户表Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface ISysUserService extends IBaseService{
	/**
	 * 增加系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int insertSysUser(SysUserEntity sysUserEntity) throws Exception;
	
	/**
	 * 删除系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int deleteSysUser(SysUserEntity sysUserEntity) throws Exception;
	
	/**
	 * 修改系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int updateSysUser(SysUserEntity sysUserEntity) throws Exception;
	/**
	 * 查询系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的系统用户表实体对象结果集
 	 */
	public	List<SysUserEntity> selectSysUser(SysUserEntity sysUserEntity) throws Exception;
	
	/**
	 * 查询系统用户表记录数
	 * @param SysUserEntity 系统用户表实体对象
	 * @return int返回符合条件的系统用户表实体对象个数
 	 */
	public	int selectSysUserCount(SysUserEntity sysUserEntity);


	//登录接口
	public BaseResult<SysUserEntity> updateLogin(SysUserEntity sysUserEntity);

	//获取用户角色权限
	public BaseResult<List<SysMenuEntity>> getUserRoleMenu(SysUserEntity sysUserEntity);

	//批量删除用户
	public int multiDeleteSysUser(Integer [] array);
	//获取用户角色
	public BaseResult<List<SysRoleEntity>> getUserRole(SysUserEntity sysUserEntity);

	//用户中心添加管理员用户
	public BaseResult<Integer> insertSysUserAdmin(SysUserEntity sysUserEntity) throws Exception;

}