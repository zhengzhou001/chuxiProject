package com.dingbo.chuxi.sys.mapper;

import com.dingbo.chuxi.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysUserMapper {
	/**
	 * 增加用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int insertSysUser(SysUserEntity sysUserEntity);
	
	/**
	 * 删除用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int deleteSysUser(SysUserEntity sysUserEntity);
	
	/**
	 * 修改用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int updateSysUser(SysUserEntity sysUserEntity);
	/**
	 * 查询用户表记录
	 * @param SysUserEntity 用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的用户表实体对象结果集
 	 */
	public	List<SysUserEntity> selectSysUser(SysUserEntity sysUserEntity);
	
	/**
	 * 查询用户表记录个数
	 * @param SysUserEntity 用户表实体对象
	 * @return int返回符合条件的用户表实体对象个数
 	 */
	public	int selectSysUserCount(SysUserEntity sysUserEntity);

	//获取用户详细信息
	public List<Map> getPersonInfo(Map map);
}