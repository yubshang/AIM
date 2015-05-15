package com.abet.service;

import java.util.List;
import net.sf.json.JSONArray;
import com.abet.ORM.Degree;

public interface DegreeService {
	public void saveOrUpdateDegree(Degree degree);
	public List<Degree> allDegree();
//	public JSONArray allDegreeJ();
	public void delDegree(Integer id);
	public Degree loadDegree(Integer id);
	public void modiDegree(Degree degree);
}
