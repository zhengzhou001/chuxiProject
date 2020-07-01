package com.dingbo.chuxi.sys.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.dingbo.chuxi.sys.service.ISysUserService;
import com.dingbo.chuxi.sys.mapper.SysUserMapper;
import com.dingbo.chuxi.sys.entity.SysUserEntity;

/**
 * <ol>
 * date:2020-07-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl implements ISysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	/**
	 * 增加用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int insertSysUser(SysUserEntity sysUserEntity) throws Exception{
		return sysUserMapper.insertSysUser(sysUserEntity);
	}
	
	/**
	 * 删除用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int deleteSysUser(SysUserEntity sysUserEntity) throws Exception{
    	return sysUserMapper.deleteSysUser(sysUserEntity);
	}
	
	/**
	 * 修改用户表记录
	 * @param SysUserEntity 用户表实体对象
  	 */
	public int updateSysUser(SysUserEntity sysUserEntity) throws Exception{
    	return sysUserMapper.updateSysUser(sysUserEntity);
	}
	/**
	 * 查询用户表记录
	 * @param SysUserEntity 用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的用户表实体对象结果集
 	 */
	public	List<SysUserEntity> selectSysUser(SysUserEntity sysUserEntity) throws Exception{
		return sysUserMapper.selectSysUser(sysUserEntity);
	}
	
	/**
	 * 查询用户表记录数
	 * @param SysUserEntity 用户表实体对象
	 * @return int返回符合条件的用户表实体对象个数
 	 */
	public	int selectSysUserCount(SysUserEntity sysUserEntity){
		return sysUserMapper.selectSysUserCount(sysUserEntity);
	}
}