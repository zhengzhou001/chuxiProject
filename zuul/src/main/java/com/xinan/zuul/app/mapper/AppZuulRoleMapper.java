package com.xinan.zuul.app.mapper;

import java.util.List;

import com.xinan.zuul.app.entity.AppZuulRoleEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用权限表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface AppZuulRoleMapper {
	/**
	 * 增加应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int insertAppZuulRole(AppZuulRoleEntity appZuulRoleEntity);
	
	/**
	 * 删除应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int deleteAppZuulRole(AppZuulRoleEntity appZuulRoleEntity);
	
	/**
	 * 修改应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int updateAppZuulRole(AppZuulRoleEntity appZuulRoleEntity);
	/**
	 * 查询应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return List<AppZuulRoleEntity>返回符合条件的应用权限表实体对象结果集
 	 */
	public	List<AppZuulRoleEntity> selectAppZuulRole(AppZuulRoleEntity appZuulRoleEntity);
	
	/**
	 * 查询应用权限表记录个数
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return int返回符合条件的应用权限表实体对象个数
 	 */
	public	int selectAppZuulRoleCount(AppZuulRoleEntity appZuulRoleEntity);
}