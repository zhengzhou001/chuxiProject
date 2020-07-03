package com.xinan.userService.sys.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.xinan.userService.sys.service.ISysRoleMenuService;
import com.xinan.userService.sys.mapper.SysRoleMenuMapper;
import com.xinan.userService.sys.entity.SysRoleMenuEntity;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色菜单表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class SysRoleMenuServiceImpl extends BaseServiceImpl implements ISysRoleMenuService {
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	/**
	 * 增加角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int insertSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception{
		return sysRoleMenuMapper.insertSysRoleMenu(sysRoleMenuEntity);
	}
	
	/**
	 * 删除角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int deleteSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception{
    	return sysRoleMenuMapper.deleteSysRoleMenu(sysRoleMenuEntity);
	}
	
	/**
	 * 修改角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
  	 */
	public int updateSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception{
    	return sysRoleMenuMapper.updateSysRoleMenu(sysRoleMenuEntity);
	}
	/**
	 * 查询角色菜单表记录
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return List<SysRoleMenuEntity>返回符合条件的角色菜单表实体对象结果集
 	 */
	public	List<SysRoleMenuEntity> selectSysRoleMenu(SysRoleMenuEntity sysRoleMenuEntity) throws Exception{
		return sysRoleMenuMapper.selectSysRoleMenu(sysRoleMenuEntity);
	}
	
	/**
	 * 查询角色菜单表记录数
	 * @param SysRoleMenuEntity 角色菜单表实体对象
	 * @return int返回符合条件的角色菜单表实体对象个数
 	 */
	public	int selectSysRoleMenuCount(SysRoleMenuEntity sysRoleMenuEntity){
		return sysRoleMenuMapper.selectSysRoleMenuCount(sysRoleMenuEntity);
	}
}