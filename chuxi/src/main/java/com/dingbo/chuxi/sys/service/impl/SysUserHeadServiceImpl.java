package com.dingbo.chuxi.sys.service.impl;

import com.dingbo.chuxi.log.entity.LogUserEntity;
import com.dingbo.chuxi.log.mapper.LogUserMapper;
import com.dingbo.chuxi.sys.entity.SysUserHeadEntity;
import com.dingbo.chuxi.sys.mapper.SysUserHeadMapper;
import com.dingbo.chuxi.sys.service.ISysUserHeadService;
import com.xinan.distributeCore.service.impl.BaseServiceImpl;
import com.xinan.distributeCore.tools.BaseTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <ol>
 * date:2020-07-10 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户头像表Service实现类</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class SysUserHeadServiceImpl extends BaseServiceImpl implements ISysUserHeadService {
	@Autowired
	private SysUserHeadMapper sysUserHeadMapper;
	@Autowired
	private LogUserMapper logUserMapper;
	/**
	 * 增加用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
	public int insertSysUserHead(SysUserHeadEntity sysUserHeadEntity) throws Exception{
		if (sysUserHeadEntity.getIsShow().equals("1")){
			SysUserHeadEntity sysUserHeadEntity1 = new SysUserHeadEntity();
			sysUserHeadEntity1.setUserid(sysUserHeadEntity.getUserid());
			sysUserHeadEntity1.setIsShow_NEW("0");
			sysUserHeadMapper.updateSysUserHead(sysUserHeadEntity1);
		}
		//记录日志
		LogUserEntity logUserEntity = new LogUserEntity();
		logUserEntity.setUserid(sysUserHeadEntity.getUserid());
		String  file = sysUserHeadEntity.getFilePath()+sysUserHeadEntity.getFileName()+"."+sysUserHeadEntity.getFileExt();
		String fileName = sysUserHeadEntity.getUserFileName()+"."+sysUserHeadEntity.getFileExt();
		logUserEntity.setContent("<a href=\"/file/getFile?realFile="+file+"&fileName="+fileName+"\" target=\"_blank\">新增头像 "+fileName+"</a>");
		logUserEntity.setIp(BaseTools.getIPAddress());
		logUserMapper.insertLogUser(logUserEntity);
		return sysUserHeadMapper.insertSysUserHead(sysUserHeadEntity);
	}
	
	/**
	 * 删除用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
	public int deleteSysUserHead(SysUserHeadEntity sysUserHeadEntity) throws Exception{
    	return sysUserHeadMapper.deleteSysUserHead(sysUserHeadEntity);
	}
	
	/**
	 * 修改用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
  	 */
	public int updateSysUserHead(SysUserHeadEntity sysUserHeadEntity) throws Exception{
    	return sysUserHeadMapper.updateSysUserHead(sysUserHeadEntity);
	}
	/**
	 * 查询用户头像表记录
	 * @param SysUserHeadEntity 用户头像表实体对象
	 * @return List<SysUserHeadEntity>返回符合条件的用户头像表实体对象结果集
 	 */
	public	List<SysUserHeadEntity> selectSysUserHead(SysUserHeadEntity sysUserHeadEntity) throws Exception{
		return sysUserHeadMapper.selectSysUserHead(sysUserHeadEntity);
	}
	
	/**
	 * 查询用户头像表记录数
	 * @param SysUserHeadEntity 用户头像表实体对象
	 * @return int返回符合条件的用户头像表实体对象个数
 	 */
	public	int selectSysUserHeadCount(SysUserHeadEntity sysUserHeadEntity){
		return sysUserHeadMapper.selectSysUserHeadCount(sysUserHeadEntity);
	}
}