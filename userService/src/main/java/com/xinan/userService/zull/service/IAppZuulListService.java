package com.xinan.userService.zull.service;

import java.util.List;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.userService.zull.entity.AppZuulListEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用列表Service接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IAppZuulListService extends IBaseService{
	/**
	 * 增加应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
	public int insertAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception;
	
	/**
	 * 删除应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
	public int deleteAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception;
	
	/**
	 * 修改应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
	public int updateAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception;
	/**
	 * 查询应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
	 * @return List<AppZuulListEntity>返回符合条件的应用列表实体对象结果集
 	 */
	public	List<AppZuulListEntity> selectAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception;
	
	/**
	 * 查询应用列表记录数
	 * @param AppZuulListEntity 应用列表实体对象
	 * @return int返回符合条件的应用列表实体对象个数
 	 */
	public	int selectAppZuulListCount(AppZuulListEntity appZuulListEntity);
}