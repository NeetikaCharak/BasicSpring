package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSeqId") 
	@SequenceGenerator(name="departmentSeqId", sequenceName ="department_custom_id", allocationSize = 10)//Created person_custom_id in Derby first
	@Column(name="DEPARTMENT_ID")
	private int departmentId;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	public int getVehicleId() {
		return departmentId;
	}

	public String getVehicleName() {
		return departmentName;
	}

	public void setVehicleId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setVehicleName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Department() {
		super();
	}
	
	public Department(String name) {
		this.departmentName = name;
	}
}
