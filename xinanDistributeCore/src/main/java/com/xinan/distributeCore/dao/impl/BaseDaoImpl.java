package com.xinan.distributeCore.dao.impl;

import com.xinan.distributeCore.dao.IBaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="baseDao")
public class BaseDaoImpl implements IBaseDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate ;
	public int insert(String sqlid,Object object){
		return sqlSessionTemplate.insert(sqlid,  object);
	}
	
	public int update(String sqlid,Object object){
		return sqlSessionTemplate.update(sqlid,  object);
	}
	
	public int delete(String sqlid,Object object){
		return sqlSessionTemplate.delete(sqlid, object);
	}
	public List select(String sqlid,Object object){
		return sqlSessionTemplate.selectList(sqlid,  object);
	}
	public Object selectOne(String sqlid,Object object){
		return sqlSessionTemplate.selectOne(sqlid,  object);
	}
}
