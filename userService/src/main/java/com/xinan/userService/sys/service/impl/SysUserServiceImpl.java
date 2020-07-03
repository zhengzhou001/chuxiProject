package com.xinan.userService.sys.service.impl;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.tools.EncryptTools;
import com.xinan.userService.sys.entity.*;
import com.xinan.userService.sys.mapper.*;
import com.xinan.userService.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <ol>
 * date:2020-04-10 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>系统用户表Service实现类</li>
 * </ol>
 * <ol>
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
	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private  SysRoleMenuMapper sysRoleMenuMapper;
	/**
	 * 增加系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int insertSysUser(SysUserEntity sysUserEntity) throws Exception{
		return sysUserMapper.insertSysUser(sysUserEntity);
	}
	
	/**
	 * 删除系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int deleteSysUser(SysUserEntity sysUserEntity) throws Exception{
    	return sysUserMapper.deleteSysUser(sysUserEntity);
	}
	
	/**
	 * 修改系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
  	 */
	public int updateSysUser(SysUserEntity sysUserEntity) throws Exception{
    	return sysUserMapper.updateSysUser(sysUserEntity);
	}
	/**
	 * 查询系统用户表记录
	 * @param SysUserEntity 系统用户表实体对象
	 * @return List<SysUserEntity>返回符合条件的系统用户表实体对象结果集
 	 */
	public	List<SysUserEntity> selectSysUser(SysUserEntity sysUserEntity) throws Exception{
		return sysUserMapper.selectSysUser(sysUserEntity);
	}
	
	/**
	 * 查询系统用户表记录数
	 * @param SysUserEntity 系统用户表实体对象
	 * @return int返回符合条件的系统用户表实体对象个数
 	 */
	public	int selectSysUserCount(SysUserEntity sysUserEntity){
		return sysUserMapper.selectSysUserCount(sysUserEntity);
	}

	//登录接口
	public BaseResult<SysUserEntity> updateLogin(SysUserEntity sysUserEntity) {
		BaseResult<SysUserEntity> result = new BaseResult<SysUserEntity>();
		//数据校验
		String account = sysUserEntity.getAccount();
		String pwd = sysUserEntity.getPwd();
		if (sysUserEntity.getAppid()==null){
			result.code=100;
			result.msg="appid不能为空";
			return result;
		}
		int appid  = sysUserEntity.getAppid();
		if (StringUtils.isEmpty(account)||StringUtils.isEmpty(pwd)){
			result.code=101;
			result.msg="账号或密码不能为空";
			return result;
		}
		if (appid==0){
			result.code=101;
			result.msg="appid不能为空";
			return result;
		}
		//根据账号查询用户表
		SysUserEntity sysUserEntity_account = new SysUserEntity();
		sysUserEntity_account.setAppid(appid);
		sysUserEntity_account.setAccount(account);

		List<SysUserEntity> list= sysUserMapper.selectSysUser(sysUserEntity_account);
		if (list==null||list.size()==0){
			result.code=102;
			result.msg="账号不存在";
			return result;
		}
		if(list.size()>1){
			result.code=103;
			result.msg="账号数据异常，该账号"+account+"对应"+list.size()+"条数据，请联系管理员解决";
			return result;
		}
		sysUserEntity_account=list.get(0);
		int state = sysUserEntity_account.getState();
		if (state!=1){
			result.code=104;
			result.msg="账号状态异常，请联系管理员解决";
			return result;
		}
		if (!StringUtils.equalsIgnoreCase(EncryptTools.encodeMD5String(pwd),sysUserEntity_account.getPwd())){
			result.code=105;
			result.msg="密码不正确";
			return result;
		}
		//用户数据正常，账号密码正确
		//更新用户最后登录时间，记录登录日志

		SysLoginLogEntity sysLoginLogEntity = new SysLoginLogEntity();
		sysLoginLogEntity.setAddr(sysUserEntity.getAddr());
		sysLoginLogEntity.setIp(sysUserEntity.getIp());
		sysLoginLogEntity.setUserid(sysUserEntity_account.getId());
		//记录日志
		sysLoginLogMapper.insertSysLoginLog(sysLoginLogEntity);

		SysUserEntity sysUserEntity_update = new SysUserEntity();
		sysUserEntity_update.setId(sysUserEntity_account.getId());
		sysUserEntity_update.setLoginDate_NEW(BaseTools.getCurStrDate(1));
		//修改登录时间
		sysUserMapper.updateSysUser(sysUserEntity_update);

		sysUserEntity_update.setName(sysUserEntity_account.getName());
		sysUserEntity_update.setAppid(sysUserEntity_account.getAppid());
		result.setData(sysUserEntity_update);
		return result;
	}


	//获取用户角色权限
	public BaseResult<List<SysMenuEntity>> getUserRoleMenu(SysUserEntity sysUserEntity){
		BaseResult<List<SysMenuEntity>> result = new BaseResult<List<SysMenuEntity>>();
		int id = sysUserEntity.getId();
		if (id==0){
			result.code=101;
			result.msg="用户id不能为空";
			return result;
		}
		//根据用户id查询对应的权限
		List<SysMenuEntity> menuList  = sysMenuMapper.getUserRoleMenu(sysUserEntity);
		result.data = menuList;
		return result;
	}

	@Override
	public int  multiDeleteSysUser(Integer [] array) {
		return sysUserMapper.multiDeleteSysUser(array);
	}

	//获取用户角色
	public BaseResult<List<SysRoleEntity>> getUserRole(SysUserEntity sysUserEntity){
		BaseResult<List<SysRoleEntity>> result = new BaseResult<List<SysRoleEntity>>();

		if (sysUserEntity.getId()==null){
			result.code=101;
			result.msg="用户id不能为空";
			return result;
		}
		int id = sysUserEntity.getId();
		//根据用户id查询对应的权限
		List<SysRoleEntity> roleList  = new ArrayList<>();
		if (sysUserEntity.getUseridOther()!=null){
			roleList=sysRoleMapper.getUserRoleOther(sysUserEntity);
		}else{
			roleList=sysRoleMapper.getUserRole(sysUserEntity);
		}
		//获取用户关联的角色
		//遍历上一步的角色，查询出对应的子角色
		List<SysRoleEntity> allRoleList = new ArrayList<>();
		for (int i=0;i<roleList.size();i++){
			allRoleList.add(roleList.get(i));

		}
		for (int i=0;i<roleList.size();i++){
			int rolePid = roleList.get(i).getId();
			SysRoleEntity sysRoleEntity_pid = new SysRoleEntity();
			sysRoleEntity_pid.setPid(rolePid);
			getChildren(sysRoleEntity_pid,allRoleList);
		}

		if (sysUserEntity.getUseridOther()!=null){
			//遍历结果，将对应用户已有的角色打钩
			for(int i=0;i<allRoleList.size();i++){
				SysRoleEntity tmpEntity =allRoleList.get(i);
				SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
				sysUserRoleEntity.setUserid(sysUserEntity.getUseridOther());
				sysUserRoleEntity.setRoleid(tmpEntity.getId());
				int count = sysUserRoleMapper.selectSysUserRoleCount(sysUserRoleEntity);
				if (count>0){
					tmpEntity.setChecked(true);
				}else{
					tmpEntity.setChecked(false);
				}
			}
		}else{

		}
		result.data = allRoleList;
		return result;
	}

	//递归查找子角色
	public List<SysRoleEntity> getChildren(SysRoleEntity sysRoleEntity_pid, List<SysRoleEntity> allRoleList){
		List<SysRoleEntity> roleList = sysRoleMapper.selectSysRole(sysRoleEntity_pid);
		for (int i=0;i<roleList.size();i++){
			boolean exits =false;
			 for (int j=0;j<allRoleList.size();j++){
			 	if (allRoleList.get(j).getId()==roleList.get(i).getId()){
					exits=true;
			 		break;
				}
			 }
			if (exits){

			}else {
				allRoleList.add(roleList.get(i));
			}
			int rolePid = roleList.get(i).getId();
			SysRoleEntity sysRoleEntity_pid_t = new SysRoleEntity();
			sysRoleEntity_pid_t.setPid(rolePid);
			getChildren(sysRoleEntity_pid_t,allRoleList);
		}
		return allRoleList ;
	}

	//用户中心添加管理员用户
	public BaseResult<Integer> insertSysUserAdmin(SysUserEntity sysUserEntity) throws Exception{
		BaseResult<Integer> ret = new BaseResult<>();
		//首先判断该应用是否第一次创建用户
		SysMenuEntity sysMenuEntity = new SysMenuEntity();
		sysMenuEntity.setAppid(sysUserEntity.getAppid());

		if(sysMenuMapper.selectSysMenuCount(sysMenuEntity)>0){
			//该应用下已有菜单，
			ret.setCode(-1);
			ret.setMsg("该应用不是第一次创建");
			return  ret;
		}
		//为新应用创建基础菜单
		SysMenuEntity sysMenuEntityParent = new SysMenuEntity();
		sysMenuEntityParent.setAppid(sysUserEntity.getAppid());
		sysMenuEntityParent.setPid(-1);
		sysMenuEntityParent.setName("系统管理");
		sysMenuEntityParent.setOrderid(1);
		sysMenuEntityParent.setState(1);
		sysMenuEntityParent.setFlag(1);
		sysMenuMapper.insertSysMenu(sysMenuEntityParent);
		sysMenuEntity.setPid(sysMenuEntityParent.getId());
		sysMenuEntity.setFlag(1);
		sysMenuMapper.insertBaseMenu(sysMenuEntity);

		//创建一个管理员角色
		SysRoleEntity sysRoleEntity = new SysRoleEntity();
		sysRoleEntity.setPid(-1);
		sysRoleEntity.setAppid(sysUserEntity.getAppid());
		sysRoleEntity.setName("系统管理员");
		sysRoleEntity.setState(1);
		sysRoleMapper.insertSysRole(sysRoleEntity);
		//角色赋权
		sysRoleMenuMapper.insertBaseRoleMenu(sysRoleEntity);
		//添加用户
		sysUserEntity.setFlag(1);
		sysUserMapper.insertSysUser(sysUserEntity);
		//为用户赋角色
		SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
		sysUserRoleEntity.setUserid(sysUserEntity.getId());
		sysUserRoleEntity.setRoleid(sysRoleEntity.getId());
		sysUserRoleMapper.insertSysUserRole(sysUserRoleEntity);
		ret.setCode(0);
		ret.setMsg("添加成功");
		return ret;
	}
}

