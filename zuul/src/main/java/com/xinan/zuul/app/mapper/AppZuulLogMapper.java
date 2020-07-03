package com.xinan.zuul.app.mapper;

import java.util.List;

import com.xinan.zuul.app.entity.AppZuulLogEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>接口调用日志表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface AppZuulLogMapper {
	/**
	 * 增加接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
	public int insertAppZuulLog(AppZuulLogEntity appZuulLogEntity);
	
	/**
	 * 删除接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
	public int deleteAppZuulLog(AppZuulLogEntity appZuulLogEntity);
	
	/**
	 * 修改接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
	public int updateAppZuulLog(AppZuulLogEntity appZuulLogEntity);
	/**
	 * 查询接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
	 * @return List<AppZuulLogEntity>返回符合条件的接口调用日志表实体对象结果集
 	 */
	public	List<AppZuulLogEntity> selectAppZuulLog(AppZuulLogEntity appZuulLogEntity);
	
	/**
	 * 查询接口调用日志表记录个数
	 * @param AppZuulLogEntity 接口调用日志表实体对象
	 * @return int返回符合条件的接口调用日志表实体对象个数
 	 */
	public	int selectAppZuulLogCount(AppZuulLogEntity appZuulLogEntity);
}