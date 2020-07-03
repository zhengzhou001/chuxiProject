package com.xinan.zuul.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.xinan.zuul.app.service.IAppZuulRoleService;
import com.xinan.zuul.app.mapper.AppZuulRoleMapper;
import com.xinan.zuul.app.entity.AppZuulRoleEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用权限表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class AppZuulRoleServiceImpl extends BaseServiceImpl implements IAppZuulRoleService {
	@Autowired
	private AppZuulRoleMapper appZuulRoleMapper;
	/**
	 * 增加应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int insertAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception{
		return appZuulRoleMapper.insertAppZuulRole(appZuulRoleEntity);
	}
	
	/**
	 * 删除应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int deleteAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception{
    	return appZuulRoleMapper.deleteAppZuulRole(appZuulRoleEntity);
	}
	
	/**
	 * 修改应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
  	 */
	public int updateAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception{
    	return appZuulRoleMapper.updateAppZuulRole(appZuulRoleEntity);
	}
	/**
	 * 查询应用权限表记录
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return List<AppZuulRoleEntity>返回符合条件的应用权限表实体对象结果集
 	 */
	public	List<AppZuulRoleEntity> selectAppZuulRole(AppZuulRoleEntity appZuulRoleEntity) throws Exception{
		return appZuulRoleMapper.selectAppZuulRole(appZuulRoleEntity);
	}
	
	/**
	 * 查询应用权限表记录数
	 * @param AppZuulRoleEntity 应用权限表实体对象
	 * @return int返回符合条件的应用权限表实体对象个数
 	 */
	public	int selectAppZuulRoleCount(AppZuulRoleEntity appZuulRoleEntity){
		return appZuulRoleMapper.selectAppZuulRoleCount(appZuulRoleEntity);
	}
}