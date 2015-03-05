package com.abet.struts.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.abet.ORM.Degree;
import com.abet.service.DegreeService;

@SuppressWarnings("serial")
public class DegreeAction extends ActionSupport implements ModelDriven<Degree>{
	private Degree model = new Degree();
	private DegreeService service;
	List<Degree> degreeList;
	
	public Degree getModel() {
		return model;
	}
	
	public String addDegree(){
		service.saveOrUpdateDegree(model);
		return "toListDegrees";
	}
	
	public String listDegree(){
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
	public void setService(DegreeService service){this.service = service;}
}
