package com.neetika.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;


/**
 * Showing Many to Many association
 * 
 * InverseJoinColumn - The foreign key columns of the join table(STUDENTS_COURSES) which reference the primary table(STUDENTS) of the 
 * entity that does not own the association.If you don't specify joinColumns and inverseJoinColumns on the @JoinTable annotation, the persistence provider assumes a primary key to primary key join relationship and still store the equivalent ID columns for two related entities in the table by default.
 * 
 * @author Neetika
 *
 */
@Entity(name="COURSES")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stuCourseSeqId") 
	@SequenceGenerator(name="stuCourseSeqId", sequenceName ="stu_course_custom_id", allocationSize = 2)//Created stu_course_custom_id in Derby first
	@Column(name="COURSE_ID")
	private int courseId;
	
	@Column(name="COURSE_NAME")
	private String courseName;
	
	/*
	 * If joincolumn / inverseJoinColumns is not used
	 * 
	 * ij> describe STUDENTS_COURSES;
	COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
	------------------------------------------------------------------------------
	COURSES_COURSE_ID   |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
	STUDENTS_STUDENT_ID |INTEGER  |0   |10  |10    |NULL      |NULL      |NO
	 */
	@ManyToMany
	@JoinTable(name="STUDENTS_COURSES", 
				joinColumns=@JoinColumn(name="courseId"),
				inverseJoinColumns=@JoinColumn(name="studentId"))
	private Collection<Student> students = new ArrayList<>();

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Course() {
		super();
	}
	
	public Course(String courseName) {
		this.courseName = courseName;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

}
