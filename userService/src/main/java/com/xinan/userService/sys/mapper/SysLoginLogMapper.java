package com.xinan.userService.sys.mapper;

import java.util.List;

import com.xinan.userService.sys.entity.SysLoginLogEntity;

/**
 * <ol>
 * date:2020-04-13 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户登录日志表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysLoginLogMapper {
	/**
	 * 增加用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
	public int insertSysLoginLog(SysLoginLogEntity sysLoginLogEntity);
	
	/**
	 * 删除用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
	public int deleteSysLoginLog(SysLoginLogEntity sysLoginLogEntity);
	
	/**
	 * 修改用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
	public int updateSysLoginLog(SysLoginLogEntity sysLoginLogEntity);
	/**
	 * 查询用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
	 * @return List<SysLoginLogEntity>返回符合条件的用户登录日志表实体对象结果集
 	 */
	public	List<SysLoginLogEntity> selectSysLoginLog(SysLoginLogEntity sysLoginLogEntity);
	
	/**
	 * 查询用户登录日志表记录个数
	 * @param SysLoginLogEntity 用户登录日志表实体对象
	 * @return int返回符合条件的用户登录日志表实体对象个数
 	 */
	public	int selectSysLoginLogCount(SysLoginLogEntity sysLoginLogEntity);
}