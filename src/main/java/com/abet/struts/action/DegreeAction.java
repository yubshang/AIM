package com.abet.struts.action;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.abet.ORM.Degree;
import com.abet.service.DegreeService;

@SuppressWarnings("serial")
public class DegreeAction extends ActionSupport implements ModelDriven<Degree>{
	private List<Degree> degreeList;
	private Degree model = new Degree();
	private DegreeService service;
	private JSONArray resultObj = null;
		
	public String addDegree(){
		service.saveOrUpdateDegree(model);
		return "toListDegrees";
	}
	
	public String listDegree(){
//		this.setResultObj(service.allDegreeJ());
		return SUCCESS;
	}
	
	public String listDegreeJSON(){
		degreeList = service.allDegree();
		return SUCCESS;
	}
	
	public void validateAddDegree(){
		String[] args = null;
		if (model.getIdentifier() == null || model.getIdentifier().trim().length()<1){
			args = new String[1];
			args[0] = "Identifier";				
			addFieldError("identifier", getText("errors.empty",args));
		}
		if (model.getDepartment() == null || model.getDepartment().trim().length()<1){
			args = new String[1];
			args[0] = "Department";				
			addFieldError("department", getText("errors.empty",args));
		}
	}
	
	public DegreeService getService(){return service;}
	public Degree getModel() {return model;}
	public List<Degree> getDegreeList(){return degreeList;}
	public void setService(DegreeService service){this.service = service;}
	public void setResultObj(JSONArray resultObj){this.resultObj = resultObj;}
}
