package com.xinan.userService.sys.service.impl;

import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysUserRoleEntity;
import com.xinan.userService.sys.mapper.SysRoleMapper;
import com.xinan.userService.sys.mapper.SysUserRoleMapper;
import com.xinan.userService.sys.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>角色表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends BaseServiceImpl implements ISysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	/**
	 * 增加角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int insertSysRole(SysRoleEntity sysRoleEntity) throws Exception{
		return sysRoleMapper.insertSysRole(sysRoleEntity);
	}
	
	/**
	 * 删除角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int deleteSysRole(SysRoleEntity sysRoleEntity) throws Exception{

		if (StringUtils.isNotEmpty(sysRoleEntity.getIds())){
			//批量删除
			sysUserRoleMapper.deleteSysRoleByIds(sysRoleEntity);
			return 0;
		}

    	return sysRoleMapper.deleteSysRole(sysRoleEntity);
	}
	
	/**
	 * 修改角色表记录
	 * @param SysRoleEntity 角色表实体对象
  	 */
	public int updateSysRole(SysRoleEntity sysRoleEntity) throws Exception{
    	return sysRoleMapper.updateSysRole(sysRoleEntity);
	}
	/**
	 * 查询角色表记录
	 * @param SysRoleEntity 角色表实体对象
	 * @return List<SysRoleEntity>返回符合条件的角色表实体对象结果集
 	 */
	public	List<SysRoleEntity> selectSysRole(SysRoleEntity sysRoleEntity) throws Exception{
		return sysRoleMapper.selectSysRole(sysRoleEntity);
	}
	
	/**
	 * 查询角色表记录数
	 * @param SysRoleEntity 角色表实体对象
	 * @return int返回符合条件的角色表实体对象个数
 	 */
	public	int selectSysRoleCount(SysRoleEntity sysRoleEntity){
		return sysRoleMapper.selectSysRoleCount(sysRoleEntity);
	}

	//保存用户角色
	public int insertRoleByUser(String roleids ,String useridOther ){
		//删除之前的角色
		SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
		sysUserRoleEntity.setUserid(Integer.parseInt(useridOther));
		sysUserRoleMapper.deleteSysUserRole(sysUserRoleEntity);
		//保存新的角色
		String[] roleids_Array = roleids.split(",");
		for (int i=0;i<roleids_Array.length;i++){
			SysUserRoleEntity sysUserRoleEntity_Tmp = new SysUserRoleEntity();
			sysUserRoleEntity_Tmp.setUserid(Integer.parseInt(useridOther));
			sysUserRoleEntity_Tmp.setRoleid(Integer.parseInt(roleids_Array[i]));
			sysUserRoleMapper.insertSysUserRole(sysUserRoleEntity_Tmp);
		}
		return 0;
	}
}