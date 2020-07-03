package com.xinan.zuul.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.xinan.zuul.app.service.IAppZuulLogService;
import com.xinan.zuul.app.mapper.AppZuulLogMapper;
import com.xinan.zuul.app.entity.AppZuulLogEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>接口调用日志表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class AppZuulLogServiceImpl extends BaseServiceImpl implements IAppZuulLogService {
	@Autowired
	private AppZuulLogMapper appZuulLogMapper;
	/**
	 * 增加接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
	public int insertAppZuulLog(AppZuulLogEntity appZuulLogEntity) throws Exception{
		return appZuulLogMapper.insertAppZuulLog(appZuulLogEntity);
	}
	
	/**
	 * 删除接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
	public int deleteAppZuulLog(AppZuulLogEntity appZuulLogEntity) throws Exception{
    	return appZuulLogMapper.deleteAppZuulLog(appZuulLogEntity);
	}
	
	/**
	 * 修改接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
  	 */
	public int updateAppZuulLog(AppZuulLogEntity appZuulLogEntity) throws Exception{
    	return appZuulLogMapper.updateAppZuulLog(appZuulLogEntity);
	}
	/**
	 * 查询接口调用日志表记录
	 * @param AppZuulLogEntity 接口调用日志表实体对象
	 * @return List<AppZuulLogEntity>返回符合条件的接口调用日志表实体对象结果集
 	 */
	public	List<AppZuulLogEntity> selectAppZuulLog(AppZuulLogEntity appZuulLogEntity) throws Exception{
		return appZuulLogMapper.selectAppZuulLog(appZuulLogEntity);
	}
	
	/**
	 * 查询接口调用日志表记录数
	 * @param AppZuulLogEntity 接口调用日志表实体对象
	 * @return int返回符合条件的接口调用日志表实体对象个数
 	 */
	public	int selectAppZuulLogCount(AppZuulLogEntity appZuulLogEntity){
		return appZuulLogMapper.selectAppZuulLogCount(appZuulLogEntity);
	}
}