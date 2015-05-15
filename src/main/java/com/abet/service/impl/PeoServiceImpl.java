package com.abet.service.impl;

import java.util.List;
import java.util.Set;

import com.abet.DAO.BaseDAO;
import com.abet.ORM.Peo;
import com.abet.ORM.PeoSemesterTarget;
import com.abet.service.PeoService;


public class PeoServiceImpl implements PeoService{
    @SuppressWarnings("rawtypes")
    private BaseDAO dao;
    
    @SuppressWarnings("unchecked")
	public List<Peo> allPeo(){
        return dao.listAll();
    }
    
    @SuppressWarnings("unchecked")
    public Peo loadPeo(Integer id) {
        return (Peo) dao.load(id);
    }
    
    public void savePeo(Peo peo) {
        dao.saveOrUpdate(peo);
    }

    public void updatePeo(Peo peo) {
        dao.saveOrUpdate(peo);
    }
    
    public void addTarget(PeoSemesterTarget pst){
        dao.saveOrUpdate(pst);
    }
    
    @SuppressWarnings("unchecked")
    public void deletePeo(Integer id) {
        dao.delete(id);
    }
    
    @SuppressWarnings("unchecked")
    public void deletePeoSemesterTarget(PeoSemesterTarget pst) {
        dao.deleteObject(pst);        
    }
    
    @SuppressWarnings("rawtypes")
    public void setDao(BaseDAO dao){
        this.dao = dao;
    }
}
