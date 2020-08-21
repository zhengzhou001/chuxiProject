package com.dingbo.chuxi.sys.service;

import com.dingbo.chuxi.sys.entity.SysUserEntity;
import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户表Service接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface ISysUserService extends IBaseService{
	/**
	 * 增加用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int insertSysUser(SysUserEntity sysUserEntity) throws Exception;
	
	/**
	 * 删除用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int deleteSysUser(SysUserEntity sysUserEntity) throws Exception;
	
	/**
	 * 修改用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int updateSysUser(SysUserEntity sysUserEntity) throws Exception;
	/**
	 * 查询用户表记录
	 * @param SysUserEntity 用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的用户表实体对象结果集
 	 */
	public	List<SysUserEntity> selectSysUser(SysUserEntity sysUserEntity) throws Exception;
	
	/**
	 * 查询用户表记录数
	 * @param SysUserEntity 用户表实体对象
	 * @return int返回符合条件的用户表实体对象个数
 	 */
	public	int selectSysUserCount(SysUserEntity sysUserEntity);

	//登陆
	public BaseResult<SysUserEntity> updateLogin(SysUserEntity sysUserEntity);

	//注册
	public BaseResult<SysUserEntity> updateRegister(SysUserEntity sysUserEntity);

	//退出登录
	public void updateLogout(Map map);

	//用户详细信息
	public BaseResult<Map>  getPersonInfo(Map map);
}