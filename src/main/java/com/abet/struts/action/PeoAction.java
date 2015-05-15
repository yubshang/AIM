package com.abet.struts.action;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.abet.ORM.Peo;
import com.abet.ORM.PeoSemesterTarget;
import com.abet.service.PeoService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PeoAction extends ActionSupport implements ModelDriven<Peo>{
	private List<String> semesters;
	private List<Peo> peoList;
	private Map<String, PeoSemesterTarget> targets;
	private Peo model = new Peo();
	private PeoService service;
	
	public String addPeoPre(){
		setSemesters(generateSemestersByCurrentTime());
		return "toAddPeo";
	}
	
	public String addPeo(){
		service.savePeo(model);
		removeEmptyKey();
		for(String key : targets.keySet()){
			PeoSemesterTarget pst = targets.get(key);
			pst.setPeo(model);
			service.addTarget(pst);
		}
		return "toListPeos";
	}
	
	private void removeEmptyKey(){
		targets.remove("-1");
	}
	
	public String listPeo(){
		return SUCCESS;
	}
	
	public String listPeoJ(){
		peoList = service.allPeo();
		return SUCCESS;
	}
	
	public String peoDetailJ(){
		model = service.loadPeo(model.getPeoId());
		Collections.sort(model.getPeoSemesterTargets());
		return SUCCESS;
	}
	
	public void validateAddPeo(){
		String[] args = null;
		Set<String> s = new HashSet<String>();
		if (model.getIdentifier() == null || model.getIdentifier().trim().length()<1){
			args = new String[1];
			args[0] = "Identifier";	
			addFieldError("identifier", getText("errors.empty",args));
		}
		if (model.getShortName() == null || model.getShortName().trim().length()<1){
			args = new String[1];
			args[0] = "Short name";				
			addFieldError("shortName", getText("errors.empty",args));
		}
		for(String str: targets.keySet()){
			PeoSemesterTarget pst = targets.get(str);
			if(!"-1".equals(pst.getSemester())){
				if(!s.add(pst.getSemester())){
					addFieldError("target", "Please remove duplicates in semester values.");
				}
				if(pst.getAttainmentLevel() < 0 || pst.getAttainmentLevel() > 100){
					addFieldError(str, "Target must be a number between 0 and 100");
				}
			}
			setSemesters(generateSemestersByCurrentTime());
		}
	}
	
	private List<String> generateSemestersByCurrentTime(){
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		List<String> sems = new ArrayList<String>();
		int inc = 0;
		if(currentMonth >= 6){
			sems.add("Fall " + currentYear);
			inc++;
			for(int i = 0; i < 9; i++){
				if(i % 2 == 0)
					sems.add("Spring " + (currentYear + inc));
				else{
					sems.add("Fall " + (currentYear + inc));
					inc++;
				}
			}
		}else{
			sems.add("Spring " + currentYear);
			for(int i = 0; i < 9; i++){
				if(i % 2 == 0){
					sems.add("Fall " + (currentYear + inc));
					inc++;
				}else
					sems.add("Spring " + (currentYear + inc));
			}
		}
		return sems;
	}
	public List<String> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
	}
	
	public Map<String, PeoSemesterTarget> getTargets(){
		return targets;
	}
	
	public void setTargets(Map<String, PeoSemesterTarget> targets){
		this.targets = targets;
	}

	public Peo getModel() {
		return model;
	}
	
	public void setService(PeoService service){
		this.service = service;
	}
	
	public PeoService getService(){
		return service;
	}
	
	public void setPeoList(List<Peo> peoList){
		this.peoList = peoList;
	}
	
	public List<Peo> getPeoList(){
		return peoList;
	}
}
