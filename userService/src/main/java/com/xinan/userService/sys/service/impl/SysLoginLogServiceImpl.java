package com.xinan.userService.sys.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.xinan.userService.sys.service.ISysLoginLogService;
import com.xinan.userService.sys.mapper.SysLoginLogMapper;
import com.xinan.userService.sys.entity.SysLoginLogEntity;

/**
 * <ol>
 * date:2020-04-13 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户登录日志表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class SysLoginLogServiceImpl extends BaseServiceImpl implements ISysLoginLogService {
	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;
	/**
	 * 增加用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
	public int insertSysLoginLog(SysLoginLogEntity sysLoginLogEntity) throws Exception{
		return sysLoginLogMapper.insertSysLoginLog(sysLoginLogEntity);
	}
	
	/**
	 * 删除用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
	public int deleteSysLoginLog(SysLoginLogEntity sysLoginLogEntity) throws Exception{
    	return sysLoginLogMapper.deleteSysLoginLog(sysLoginLogEntity);
	}
	
	/**
	 * 修改用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
  	 */
	public int updateSysLoginLog(SysLoginLogEntity sysLoginLogEntity) throws Exception{
    	return sysLoginLogMapper.updateSysLoginLog(sysLoginLogEntity);
	}
	/**
	 * 查询用户登录日志表记录
	 * @param SysLoginLogEntity 用户登录日志表实体对象
	 * @return List<SysLoginLogEntity>返回符合条件的用户登录日志表实体对象结果集
 	 */
	public	List<SysLoginLogEntity> selectSysLoginLog(SysLoginLogEntity sysLoginLogEntity) throws Exception{
		return sysLoginLogMapper.selectSysLoginLog(sysLoginLogEntity);
	}
	
	/**
	 * 查询用户登录日志表记录数
	 * @param SysLoginLogEntity 用户登录日志表实体对象
	 * @return int返回符合条件的用户登录日志表实体对象个数
 	 */
	public	int selectSysLoginLogCount(SysLoginLogEntity sysLoginLogEntity){
		return sysLoginLogMapper.selectSysLoginLogCount(sysLoginLogEntity);
	}
}