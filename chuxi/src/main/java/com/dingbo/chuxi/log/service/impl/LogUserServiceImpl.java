package com.dingbo.chuxi.log.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.dingbo.chuxi.log.service.ILogUserService;
import com.dingbo.chuxi.log.mapper.LogUserMapper;
import com.dingbo.chuxi.log.entity.LogUserEntity;

/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户日志Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class LogUserServiceImpl extends BaseServiceImpl implements ILogUserService {
	@Autowired
	private LogUserMapper logUserMapper;
	/**
	 * 增加用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
	public int insertLogUser(LogUserEntity logUserEntity) throws Exception{
		return logUserMapper.insertLogUser(logUserEntity);
	}
	
	/**
	 * 删除用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
	public int deleteLogUser(LogUserEntity logUserEntity) throws Exception{
    	return logUserMapper.deleteLogUser(logUserEntity);
	}
	
	/**
	 * 修改用户日志记录
	 * @param LogUserEntity 用户日志实体对象
  	 */
	public int updateLogUser(LogUserEntity logUserEntity) throws Exception{
    	return logUserMapper.updateLogUser(logUserEntity);
	}
	/**
	 * 查询用户日志记录
	 * @param LogUserEntity 用户日志实体对象
	 * @return List<LogUserEntity>返回符合条件的用户日志实体对象结果集
 	 */
	public	List<LogUserEntity> selectLogUser(LogUserEntity logUserEntity) throws Exception{
		return logUserMapper.selectLogUser(logUserEntity);
	}
	
	/**
	 * 查询用户日志记录数
	 * @param LogUserEntity 用户日志实体对象
	 * @return int返回符合条件的用户日志实体对象个数
 	 */
	public	int selectLogUserCount(LogUserEntity logUserEntity){
		return logUserMapper.selectLogUserCount(logUserEntity);
	}
}