package com.dingbo.chuxi.log.mapper;

import java.util.List;

import com.dingbo.chuxi.log.entity.LogUserEntity;

/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户日志Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface LogUserMapper {
	/**
	 * 增加用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
	public int insertLogUser(LogUserEntity logUserEntity);
	
	/**
	 * 删除用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
	public int deleteLogUser(LogUserEntity logUserEntity);
	
	/**
	 * 修改用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
	public int updateLogUser(LogUserEntity logUserEntity);
	/**
	 * 查询用户日志记录
	 * @param LogUserEntity 用户日志实体对象
	 * @return List<LogUserEntity>返回符合条件的用户日志实体对象结果集
 	 */
	public	List<LogUserEntity> selectLogUser(LogUserEntity logUserEntity);
	
	/**
	 * 查询用户日志记录个数
	 * @param LogUserEntity 用户日志实体对象
	 * @return int返回符合条件的用户日志实体对象个数
 	 */
	public	int selectLogUserCount(LogUserEntity logUserEntity);
}