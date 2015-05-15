package com.abet.ORM;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Peo_Semester_Target", uniqueConstraints = 
@UniqueConstraint(columnNames = {"peo_id", "semester"}))
public class PeoSemesterTarget implements Comparable<PeoSemesterTarget>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "attainment_level")
	private float attainmentLevel;
	
	@Column(name = "semester")
	private String semester;
	
	@ManyToOne
	@JoinColumn(name="peo_id")
	private Peo peo;

	public PeoSemesterTarget() {
	}

	public PeoSemesterTarget(int id, float attainmentLevel, String semester) {
		this.id = id;
		this.attainmentLevel = attainmentLevel;
		this.semester = semester;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAttainmentLevel() {
		return this.attainmentLevel;
	}

	public void setAttainmentLevel(float attainmentLevel) {
		this.attainmentLevel = attainmentLevel;
	}
	
	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Peo getPeo() {
		return peo;
	}

	public void setPeo(Peo peo) {
		this.peo = peo;
	}

	public int compareTo(PeoSemesterTarget pst) {
		String[] sem1 = semester.split(" ");
		String[] sem2 = pst.semester.split(" ");
		int yr1 = Integer.parseInt(sem1[1].trim());
		int yr2 = Integer.parseInt(sem2[1].trim());
		String season1 = sem1[0].trim();
		String season2 = sem2[0].trim();
		
		if(yr1 > yr2)
			return 1;
		else if(yr1 < yr2)
			return -1;
		else if(!season1.equals(season2)){
			if("Fall".equals(season1))
				return 1;
			else
				return -1;
		}
		return 0;
	}
}
