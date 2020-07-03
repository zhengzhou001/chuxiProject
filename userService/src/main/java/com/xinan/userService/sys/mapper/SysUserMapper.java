package com.xinan.userService.sys.mapper;

import com.xinan.userService.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <ol>
 * date:2020-04-13 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>系统用户表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysUserMapper {
	/**
	 * 增加系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int insertSysUser(SysUserEntity sysUserEntity);
	
	/**
	 * 删除系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int deleteSysUser(SysUserEntity sysUserEntity);
	
	/**
	 * 修改系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int updateSysUser(SysUserEntity sysUserEntity);
	/**
	 * 查询系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的系统用户表实体对象结果集
 	 */
	public	List<SysUserEntity> selectSysUser(SysUserEntity sysUserEntity);
	
	/**
	 * 查询系统用户表记录个数
	 * @param SysUserEntity 系统用户表实体对象
	 * @return int返回符合条件的系统用户表实体对象个数
 	 */
	public	int selectSysUserCount(SysUserEntity sysUserEntity);

	/**
	 *批量删除系统用户表记录
	 * @param array int类型的数组，用于传递用户的多个id
	 */
	public int multiDeleteSysUser(@Param("array") Integer [] array);

}