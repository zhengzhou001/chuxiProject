package com.xinan.userService.sys.service.impl;

import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import com.xinan.userService.sys.entity.SysMenuEntity;
import com.xinan.userService.sys.entity.SysRoleEntity;
import com.xinan.userService.sys.entity.SysRoleMenuEntity;
import com.xinan.userService.sys.mapper.SysMenuMapper;
import com.xinan.userService.sys.mapper.SysRoleMapper;
import com.xinan.userService.sys.mapper.SysRoleMenuMapper;
import com.xinan.userService.sys.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <ol>
 * date:2020-04-16 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>菜单表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends BaseServiceImpl implements ISysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	/**
	 * 增加菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
	public int insertSysMenu(SysMenuEntity sysMenuEntity) throws Exception{
		return sysMenuMapper.insertSysMenu(sysMenuEntity);
	}
	
	/**
	 * 删除菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
	public int deleteSysMenu(SysMenuEntity sysMenuEntity) throws Exception{
    	return sysMenuMapper.deleteSysMenu(sysMenuEntity);
	}
	
	/**
	 * 修改菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
  	 */
	public int updateSysMenu(SysMenuEntity sysMenuEntity) throws Exception{
    	return sysMenuMapper.updateSysMenu(sysMenuEntity);
	}
	/**
	 * 查询菜单表记录
	 * @param SysMenuEntity 菜单表实体对象
	 * @return List<SysMenuEntity>返回符合条件的菜单表实体对象结果集
 	 */
	public	List<SysMenuEntity> selectSysMenu(SysMenuEntity sysMenuEntity) throws Exception{
		return sysMenuMapper.selectSysMenu(sysMenuEntity);
	}
	
	/**
	 * 查询菜单表记录数
	 * @param SysMenuEntity 菜单表实体对象
	 * @return int返回符合条件的菜单表实体对象个数
 	 */
	public	int selectSysMenuCount(SysMenuEntity sysMenuEntity){
		return sysMenuMapper.selectSysMenuCount(sysMenuEntity);
	}

	//根据角色查询菜单
	public List<SysMenuEntity> selectMenuByRole(SysRoleEntity sysRoleEntity){
		if (sysRoleEntity.getPid()==-1){
			return sysMenuMapper.selectMenuByAdminRole(sysRoleEntity);
		}
			return sysMenuMapper.selectMenuByRole(sysRoleEntity);
	}
	//保存角色权限
	public int insertMenuByRole(String menuids ,String roleid ){
		SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
		sysRoleMenuEntity.setRoleid(Integer.parseInt(roleid));
		//删除角色以前数据
		sysRoleMenuMapper.deleteSysRoleMenu(sysRoleMenuEntity);
		//添加角色新数据
		String[] ids = menuids.split(",");
		for (int i=0;i<ids.length;i++){
			SysRoleMenuEntity entity = new SysRoleMenuEntity();
			entity.setRoleid(Integer.parseInt(roleid));
			entity.setMenuid(Integer.parseInt(ids[i]));
			sysRoleMenuMapper.insertSysRoleMenu(entity);
		}
		//删除子角色比父角色多余的菜单权限
		List<Integer> allChildrenIds = new ArrayList<>();
		SysRoleEntity sysRoleEntity = new SysRoleEntity();
		sysRoleEntity.setPid(Integer.parseInt(roleid));
		List<SysRoleEntity> childList=sysRoleMapper.selectSysRole(sysRoleEntity);
		for (int i=0;i<childList.size();i++){
			int rolePid = childList.get(i).getId();
			allChildrenIds.add(rolePid);
			SysRoleEntity sysRoleEntity_pid = new SysRoleEntity();
			sysRoleEntity_pid.setPid(rolePid);
			getChildren(sysRoleEntity_pid,allChildrenIds);
		}
		if (allChildrenIds.size()>0){
			//该角色有子角色，开始处理子角色
			Integer[] roleids = allChildrenIds.toArray(new Integer[0]);
			sysRoleMenuMapper.deleteSysRoleMenuByPidChildrens(ArrayUtils.toMap(
					new  Object[][]{
							{"roleids",StringUtils.join(roleids,",")},
							{"roleid",Integer.parseInt(roleid)},
					}
			));
		}

		return 0;
	}
	//递归查找子角色
	private List<Integer> getChildren(SysRoleEntity sysRoleEntity_pid,List<Integer> allChildrenIds){
		List<SysRoleEntity> roleList = sysRoleMapper.selectSysRole(sysRoleEntity_pid);
		for (int i=0;i<roleList.size();i++){
			int rolePid = roleList.get(i).getId();
			allChildrenIds.add(rolePid);
			SysRoleEntity sysRoleEntity_pid_t = new SysRoleEntity();
			sysRoleEntity_pid_t.setPid(rolePid);
			getChildren(sysRoleEntity_pid_t,allChildrenIds);
		}
		return allChildrenIds ;
	}
}