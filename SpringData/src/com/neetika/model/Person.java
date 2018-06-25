package com.neetika.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * This is association of one table/entity with another entity, using
 * OneToOne - joincolumn is used to specify the "mapping" column
 * OneToMany and manytoOne : Use MappedBy with OnetoMany and not joincolumn, because joincolumn will create column in foreign table
 * manytomany
 *
 * 
 * In Circle/Features class we saw association of value type(features) with the entity(circle)
 * 
 * Before using JoinColumn on Vehicle
 * ij> describe person;
COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
------------------------------------------------------------------------------
PERSON_ID           |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
PERSON_NAME         |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
VEHICLE_VEHICLE_ID  |INTEGER  |0   |10  |10    |NULL      |NULL      |YES

3 rows selected
ij> describe department;
COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
------------------------------------------------------------------------------
DEPARTMENT_ID       |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
DEPARTMENT_NAME     |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
 *
 *
 *On Save without JoinCOlumn
 *Hibernate: values next value for person_custom_id
Hibernate: values next value for vehicle_custom_id
Hibernate: insert into department (DEPARTMENT_NAME, DEPARTMENT_ID) values (?, ?)
Hibernate: insert into Person (PERSON_NAME, department_DEPARTMENT_ID, PERSON_ID) values (?, ?, ?)

After using JoinCOlumn
Hibernate: values next value for person_custom_id
Hibernate: values next value for vehicle_custom_id
Hibernate: insert into department (DEPARTMENT_NAME, DEPARTMENT_ID) values (?, ?)
Hibernate: insert into Person (PERSON_NAME, MY_DEPT_ID, PERSON_ID) values (?, ?, ?)
 */

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeqId") 
	@SequenceGenerator(name="personSeqId", sequenceName ="person_custom_id", allocationSize = 2)//Created person_custom_id in Derby first
	@Column(name="PERSON_ID")
	private int personId;
	
	@Column(name="PERSON_NAME")
	private String personName;
	
	
	//THis adds as a column in Person Table as MY_DEPT_ID(NO CHANGE IN DEPARTMENT- no info about person in DEPARTMENT table)
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="MY_DEPT_ID")
	private Department department;
	
	/* This is to showcase the ONe to Many relation ship
	 * Joincolumn is incorrect for one to many and vise a versa. Use MappedBy. 
	 * Joincolumn - This adds as a column in Vehicle Table as PER_PERSON_ID (NO CHANGE IN PERSON- no info about vehicle in Person table). This is
	 * not a very good practice to create and maintain a column in another table(Vehicle). It will result in Column described in Person class, but created in Vehicle.
	 * Plus extra set of update queries- update PER_PERSON_ID in VEhicle.
	 * So, We must use Mapped BY With OneToMAny
	 * 
	 *==================================Starts: Using @JoinCOlumn===============================================================
	 * ij> describe person;
		COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
		------------------------------------------------------------------------------
		PERSON_ID           |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
		PERSON_NAME         |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		MY_DEPT_ID          |INTEGER  |0   |10  |10    |NULL      |NULL      |YES

		ij> describe vehicle;
		COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
		------------------------------------------------------------------------------
		VEHICLE_ID          |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
		VEHICLE_NAME        |VARCHAR  |NULL|NULL|255   |NULL      |510       |YES
		VEH_PERSON_ID       |INTEGER  |0   |10  |10    |NULL      |NULL      |YES--Column described in Vehicle, BI-Directional access(Returns One Person)= Forign key in VEHICLE TABLE
		PER_PERSON_ID       |INTEGER  |0   |10  |10    |NULL      |NULL      |YES --Column described in Person class, but created in Vehicle(Returns colection of vehicles for one person)
		
		Hibernate: values next value for person_custom_id
		Hibernate: values next value for department_custom_id
		Hibernate: values next value for vehicle_custom_id
		Hibernate: insert into Department (DEPARTMENT_NAME, DEPARTMENT_ID) values (?, ?)
		Hibernate: insert into Person (MY_DEPT_ID, PERSON_NAME, PERSON_ID) values (?, ?, ?)
		Hibernate: insert into Vehicle (VEH_PERSON_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
		Hibernate: insert into Vehicle (VEH_PERSON_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
		Hibernate: insert into Vehicle (VEH_PERSON_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
		Hibernate: insert into Vehicle (VEH_PERSON_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
		Hibernate: update Vehicle set PER_PERSON_ID=? where VEHICLE_ID=?
		Hibernate: update Vehicle set PER_PERSON_ID=? where VEHICLE_ID=?
		Hibernate: update Vehicle set PER_PERSON_ID=? where VEHICLE_ID=?
		Hibernate: update Vehicle set PER_PERSON_ID=? where VEHICLE_ID=?
		
		ij> select * from person;
		PERSON_ID  |PERSON_NAME                                                                                                                     |MY_DEPT_ID
		--------------------------------------------------------------------------------------------------------------------------------------------------------
		10         |Bob                                                                                                                             |10
		20         |Susy                                                                                                                            |20
		
		2 rows selected
		ij> Select * from Vehicle;
		VEHICLE_ID |VEHICLE_NAME                                                                                                                    |PERSON_ID
		--------------------------------------------------------------------------------------------------------------------------------------------------------
		20         |car                                                                                                                             |20
		21         |bus                                                                                                                             |20
		22         |train                                                                                                                           |20
		23         |truck                                                                                                                           |20
		
		==================================Ends: Using @JoinCOlumn===============================================================
		
		==================================Starts: Using MappedBy===============================================================
		vehicle table owns VEH_PERSON_ID as a foreign key column. Hence, JoinColumn should be put on 
		vehicle class/table in the corresponding ManytoOne, since it owns that column.
		
		This means that although you link 2 tables together, only 1 of those tables has a foreign key constraint to the other one.
		 MappedBy allows you to still link from the table not containing the constraint to the other table.
		
		VEH_PERSON_ID and PER_PERSON_ID are two keys maintained which is wrong as we must have only one foreign key in database.
		So keeping VEH_PERSON_ID a forign key in db and by using 'mappedBY' we are telling hibernate that the key for the 
		relationship is on the other side
		
		Hibernate: values next value for person_custom_id
		Hibernate: values next value for department_custom_id
		Hibernate: values next value for vehicle_custom_id
		Hibernate: insert into Department (DEPARTMENT_NAME, DEPARTMENT_ID) values (?, ?)
		Hibernate: insert into Person (MY_DEPT_ID, PERSON_NAME, PERSON_ID) values (?, ?, ?)
		Hibernate: insert into Vehicle (VEH_PERSON_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
		Hibernate: insert into Vehicle (VEH_PERSON_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
		<<<<<<<<<<<<<<<<<<<<<<<<<<NO Update Statements in Vehicle >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	 */
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy="person1")
	//@JoinColumn(name="PER_PERSON_ID")
	private Collection<Vehicle> vehicles;
	

	

	public int getPersonId() {
		return personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Person() {
		super();
	}
	
	public Person(String name) {
		this.personName = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
