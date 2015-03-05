package com.abet.service;

import java.util.List;
import com.abet.ORM.Degree;

public interface DegreeService {
	public void saveOrUpdateDegree(Degree degree);
	public List<Degree> allDegree();
	public void delDegree(Integer id);
	public Degree loadDegree(Integer id);
	public void modiDegree(Degree degree);
}
