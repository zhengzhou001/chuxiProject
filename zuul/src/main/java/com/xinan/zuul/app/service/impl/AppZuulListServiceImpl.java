package com.xinan.zuul.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.xinan.zuul.app.service.IAppZuulListService;
import com.xinan.zuul.app.mapper.AppZuulListMapper;
import com.xinan.zuul.app.entity.AppZuulListEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>应用列表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class AppZuulListServiceImpl extends BaseServiceImpl implements IAppZuulListService {
	@Autowired
	private AppZuulListMapper appZuulListMapper;
	/**
	 * 增加应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
	public int insertAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception{
		return appZuulListMapper.insertAppZuulList(appZuulListEntity);
	}
	
	/**
	 * 删除应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
	public int deleteAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception{
    	return appZuulListMapper.deleteAppZuulList(appZuulListEntity);
	}
	
	/**
	 * 修改应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
  	 */
	public int updateAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception{
    	return appZuulListMapper.updateAppZuulList(appZuulListEntity);
	}
	/**
	 * 查询应用列表记录
	 * @param AppZuulListEntity 应用列表实体对象
	 * @return List<AppZuulListEntity>返回符合条件的应用列表实体对象结果集
 	 */
	public	List<AppZuulListEntity> selectAppZuulList(AppZuulListEntity appZuulListEntity) throws Exception{
		return appZuulListMapper.selectAppZuulList(appZuulListEntity);
	}
	
	/**
	 * 查询应用列表记录数
	 * @param AppZuulListEntity 应用列表实体对象
	 * @return int返回符合条件的应用列表实体对象个数
 	 */
	public	int selectAppZuulListCount(AppZuulListEntity appZuulListEntity){
		return appZuulListMapper.selectAppZuulListCount(appZuulListEntity);
	}
}