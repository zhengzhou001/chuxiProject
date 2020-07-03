package com.xinan.distributeCore.service.impl;

import com.xinan.distributeCore.dao.IBaseDao;
import com.xinan.distributeCore.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="baseService")
public class BaseServiceImpl implements IBaseService {
	@Autowired
	private IBaseDao baseDao;
	public int insert(String sqlid,Object object){
		return baseDao.insert(sqlid, object);
	}
	
	public int update(String sqlid,Object object){
		return baseDao.insert(sqlid, object);
	}
	
	public int delete(String sqlid,Object object){
		return baseDao.insert(sqlid, object);
	}
	public List select(String sqlid,Object object){
		return baseDao.select(sqlid, object);
	}
	public Object selectOne(String sqlid,Object object){
		return baseDao.selectOne(sqlid, object);
	}
}
