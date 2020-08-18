package com.dingbo.chuxi.sys.mapper;

import com.dingbo.chuxi.sys.entity.SysUserHeadEntity;

import java.util.List;

/**
 * <ol>
 * date:2020-08-14 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户头像表Mapper接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SysUserHeadMapper {
	/**
	 * 增加用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
	public int insertSysUserHead(SysUserHeadEntity sysUserHeadEntity);
	
	/**
	 * 删除用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
	public int deleteSysUserHead(SysUserHeadEntity sysUserHeadEntity);
	
	/**
	 * 修改用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
	public int updateSysUserHead(SysUserHeadEntity sysUserHeadEntity);
	/**
	 * 查询用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
	 * @return List<SysUserHeadEntity>返回符合条件的用户头像表实体对象结果集
 	 */
	public	List<SysUserHeadEntity> selectSysUserHead(SysUserHeadEntity sysUserHeadEntity);
	
	/**
	 * 查询用户头像表记录个数
	 * @param SysUserHeadEntity 用户头像表实体对象
	 * @return int返回符合条件的用户头像表实体对象个数
 	 */
	public	int selectSysUserHeadCount(SysUserHeadEntity sysUserHeadEntity);
}