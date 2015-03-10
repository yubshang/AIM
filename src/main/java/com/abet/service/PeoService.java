package com.abet.service;

import com.abet.ORM.Peo;
import com.abet.ORM.PeoSemesterTarget;

public interface PeoService {
	public void deletePeo(Integer id);
	public void deletePeoSemesterTarget(PeoSemesterTarget pst);
	public void savePeo(Peo peo);
	public void updatePeo(Peo peo);
	public void addTarget(PeoSemesterTarget pst);
	public Peo loadPeo(Integer id);
}
