package com.xinan.zuul.app.service;

import java.util.List;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.zuul.app.entity.AppZuulRoleEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用权限表Service接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IAppZuulRoleService extends IBaseService{
	/**
	 * 增加应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int insertAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception;
	
	/**
	 * 删除应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int deleteAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception;
	
	/**
	 * 修改应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int updateAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception;
	/**
	 * 查询应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return List<AppZuulRoleEntity>返回符合条件的应用权限表实体对象结果集
 	 */
	public	List<AppZuulRoleEntity> selectAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception;
	
	/**
	 * 查询应用权限表记录数
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return int返回符合条件的应用权限表实体对象个数
 	 */
	public	int selectAppZuulRoleCount(AppZuulRoleEntity appZuulRoleEntity);
}