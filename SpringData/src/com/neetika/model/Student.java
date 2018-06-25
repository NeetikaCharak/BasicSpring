package com.neetika.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 * This class is to show Many to Many relationship - The mapping will be contained in different class
 *  
 *  cascade operation tells hibernate that whenever there is an operation on Student object, and it comes across courses
 *  object , do same operation with courses as well like save. SO we have to explicitly just writte sessionsave(student object);
 *  if some where in session courses are also being set, hibernate saves it wel too.
 * 
 
 * @author Neetika
 *
 */

@Entity(name="STUDENTS")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeqId") 
	@SequenceGenerator(name="studentSeqId", sequenceName ="student_custom_id", allocationSize = 2)//Created student_custom_id in Derby first
	@Column(name="STUDENT_ID")
	private int studentId;
	
	@Column(name="STUDENT_NAME")
	private String studentName;
	
	/*
	 * If we do not use 'mappedBy', hibernate creates a separate table for mapping between both tables:Student_student_course
	 * with two columns, ids of both tables
	 */
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="students")
	private Collection<Course> courses = new ArrayList<>();

	public int getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}
	
	public Student(){
		super();
	}
	
	public Student(String name){
		this.studentName = name;
	}
}
