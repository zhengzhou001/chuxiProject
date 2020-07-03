package com.xinan.distributeCore.dao;

import java.util.List;

public interface IBaseDao {
	public int insert(String sqlid, Object object);

	public int update(String sqlid, Object object);

	public int delete(String sqlid, Object object);
	public List select(String sqlid, Object object);
	public Object selectOne(String sqlid, Object object);
}
