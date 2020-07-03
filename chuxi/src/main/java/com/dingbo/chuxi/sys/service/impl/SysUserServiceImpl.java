package com.dingbo.chuxi.sys.service.impl;

import com.dingbo.chuxi.log.entity.LogUserEntity;
import com.dingbo.chuxi.log.mapper.LogUserMapper;
import com.dingbo.chuxi.sys.entity.SysUserEntity;
import com.dingbo.chuxi.sys.mapper.SysUserMapper;
import com.dingbo.chuxi.sys.service.ISysUserService;
import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.distributeCore.tools.EncryptTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
	@Autowired
	private LogUserMapper logUserMapper;
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

	//登陆
	public BaseResult<SysUserEntity> updateLogin(SysUserEntity sysUserEntity){
		BaseResult<SysUserEntity> result = new BaseResult<>();

		if (sysUserEntity==null){
			result.setCode(100);
			result.setMsg("传参异常");
			return result;
		}
		if (StringUtils.isEmpty(sysUserEntity.getMobile())){
			result.setCode(101);
			result.setMsg("手机号不能为空");
			return result;
		}
		if (StringUtils.isEmpty(sysUserEntity.getPassword())){
			result.setCode(101);
			result.setMsg("密码不能为空");
			return result;
		}
		SysUserEntity mobileEntity = new SysUserEntity();
		mobileEntity.setMobile(sysUserEntity.getMobile());
		List<SysUserEntity> mobileList = sysUserMapper.selectSysUser(mobileEntity);

		if (mobileList==null||mobileList.size()==0){
			result.setCode(102);
			result.setMsg("账号不存在");
			return result;
		}
		if (mobileList.size()>1){
			result.setCode(102);
			result.setMsg("账号存在多个,请联系客服人员解决");
			return result;
		}
		SysUserEntity mobileDbEntity = mobileList.get(0);
		if (!StringUtils.equals(mobileDbEntity.getState(),"1")){
			result.setCode(103);
			result.setMsg("账号状态异常,请联系客服人员解决");
			return result;
		}
		if (!StringUtils.equals(EncryptTools.encodeMD5String(sysUserEntity.getPassword()),mobileDbEntity.getPassword())){
			result.setCode(104);
			result.setMsg("密码不正确");
			return result;
		}
		//修改登陆时间
		SysUserEntity updateEntity = new SysUserEntity();
		updateEntity.setId(mobileDbEntity.getId());
		updateEntity.setLoginDate_NEW(BaseTools.getCurStrDate(1));
		sysUserMapper.updateSysUser(updateEntity);

		//记录日志
		LogUserEntity logUserEntity = new LogUserEntity();
		logUserEntity.setUserid(mobileDbEntity.getId());
		logUserEntity.setContent("用户登陆");
		logUserEntity.setIp(BaseTools.getIPAddress());
		logUserMapper.insertLogUser(logUserEntity);

		mobileDbEntity.setPassword("");//密码修改为空
		result.setData(mobileDbEntity);
		return  result;
	}

	//注册
	public BaseResult<SysUserEntity> updateRegister(SysUserEntity sysUserEntity){
		BaseResult<SysUserEntity> result = new BaseResult<>();

		if (sysUserEntity==null){
			result.setCode(100);
			result.setMsg("传参异常");
			return result;
		}
		if (StringUtils.isEmpty(sysUserEntity.getName())){
			result.setCode(99);
			result.setMsg("名字不能为空");
			return result;
		}
		if (StringUtils.isEmpty(sysUserEntity.getMobile())){
			result.setCode(101);
			result.setMsg("手机号不能为空");
			return result;
		}
		if (StringUtils.isEmpty(sysUserEntity.getPassword())){
			result.setCode(101);
			result.setMsg("密码不能为空");
			return result;
		}

		SysUserEntity mobileEntity = new SysUserEntity();
		mobileEntity.setMobile(sysUserEntity.getMobile());
		List<SysUserEntity> mobileList = sysUserMapper.selectSysUser(mobileEntity);

		if (mobileList.size()>0){
			result.setCode(102);
			result.setMsg("手机号存在");
			return result;
		}
		//注册用户
		sysUserEntity.setState("1");
		sysUserEntity.setPassword(EncryptTools.encodeMD5String(sysUserEntity.getPassword()));
		sysUserEntity.setCreateDate(BaseTools.getCurStrDate(1));
		sysUserEntity.setLoginDate(BaseTools.getCurStrDate(1));
		sysUserMapper.insertSysUser(sysUserEntity);

		//记录日志
		LogUserEntity logUserEntity = new LogUserEntity();
		logUserEntity.setUserid(sysUserEntity.getId());
		logUserEntity.setContent("注册用户");
		logUserEntity.setIp(BaseTools.getIPAddress());
		logUserMapper.insertLogUser(logUserEntity);

		sysUserEntity.setPassword("");//密码修改为空
		result.setData(sysUserEntity);
		return  result;
	}

	//退出登录
	public void updateLogout(Map map){
		int userid = MapUtils.getIntValue(map,"userid",-1);
		if (userid==-1)
			return;
		LogUserEntity logUserEntity = new LogUserEntity();
		logUserEntity.setUserid(userid);
		logUserEntity.setContent("用户退出");
		logUserEntity.setIp(BaseTools.getIPAddress());
		logUserMapper.insertLogUser(logUserEntity);
	}
}