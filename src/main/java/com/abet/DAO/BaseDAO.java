package com.abet.DAO;

import java.util.*;
import java.io.Serializable;

public interface BaseDAO {
	public Object loadById(Class clazz,Serializable id);
	
	public Object loadObject(String hql);
	
	public void delById(Class clazz,Serializable id);
	
	public void saveOrUpdate(Object obj);
	
	public List listAll();
	
	public List listAll(String clazz,int pageNo,int pageSize);
	
	public int countAll(String clazz);
	
	public List query(String hql);
	
	public List query(String hql,int pageNo,int pageSize);
	
	public int countQuery(String hql);
	
	public int update(String hql);
	
//	public Connection getConnection();
}

