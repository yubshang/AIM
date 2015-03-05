package com.abet.service.impl;

import java.util.List;

import com.abet.DAO.BaseDAO;
// import com.abet.DAO.DegreeDAOImpl;
import com.abet.ORM.Degree;
import com.abet.service.DegreeService;

public class DegreeServiceImpl implements DegreeService{

	private BaseDAO dao;

	public void saveOrUpdateDegree(Degree degree) {
		dao.saveOrUpdate(degree);
	}

	@SuppressWarnings("unchecked")
	public List<Degree> allDegree() {
		List<Degree> degrees = dao.listAll();
		return degrees;
	}

	public void delDegree(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Degree loadDegree(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void modiDegree(Degree degree) {
		// TODO Auto-generated method stub
	}
	
	public void setDao(BaseDAO dao) {this.dao = dao;}
}
