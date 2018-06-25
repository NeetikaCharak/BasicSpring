package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleSeqId") 
	@SequenceGenerator(name="vehicleSeqId", sequenceName ="vehicle_custom_id", allocationSize = 4)//Created person_custom_id in Derby first
	@Column(name="VEHICLE_ID")
	private int vehicleId;
	
	@Column(name="VEHICLE_NAME")
	private String vehicleName;
	
	
	/**
	 * BI-DIRECTIONAL Many-One 
	 * Adv - we can access User from vehicle
	 * 
		ij> describe Vehicle;
		COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
		------------------------------------------------------------------------------
		VEHICLE_ID          |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
		VEHICLE_NAME        |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		USERDETAILS_USER_ID |INTEGER  |0   |10  |10    |NULL      |NULL      |YES
		PERSON_ID           |INTEGER  |0   |10  |10    |NULL      |NULL      |YES

	 */
	@ManyToOne
	@JoinColumn(name="VEH_PERSON_ID")
	private Person person1;
	

	public Person getPerson1() {
		return person1;
	}

	public void setPerson1(Person person) {
		this.person1 = person;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	public Vehicle() {
		super();
	}
	
	public Vehicle(String name) {
		this.vehicleName = name;
	}

	
}
