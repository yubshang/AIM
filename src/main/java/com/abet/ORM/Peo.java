package com.abet.ORM;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Peo")
public class Peo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "peo_id")
	private int peoId;
	
	@Column(name = "identifier")
	private String identifier;
	
	@Column(name = "sequence_number")
	private int sequenceNumber;
	
	@Column(name = "short_name")
	private String shortName;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "peo", fetch=FetchType.LAZY)
	private List<PeoSemesterTarget> peoSemesterTargets;
	
	public Peo(){
		
	}
	
	public Peo(int peoId, String identifier, int sequenceNumber,
			String shortName){
		this.peoId = peoId;
		this.identifier = identifier;
		this.sequenceNumber = sequenceNumber;
		this.shortName = shortName;
	}
	
	public Peo(int peoId, String identifier, int sequenceNumber, 
			String shortName, String description){
		this.peoId = peoId;
		this.identifier = identifier;
		this.sequenceNumber = sequenceNumber;
		this.shortName = shortName;
		this.description = description;
	}
	public int getPeoId() {
		return peoId;
	}

	public void setPeoId(int peoId) {
		this.peoId = peoId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<PeoSemesterTarget> getPeoSemesterTargets(){
		return peoSemesterTargets;
	}
	
	public void setPeoSemesterTargets(List<PeoSemesterTarget> peoSemesterTargets){
		this.peoSemesterTargets = peoSemesterTargets;
	}
}
