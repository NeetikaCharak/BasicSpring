package com.neetika.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Circle {
@Id
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Circle(){
		super();
	}
	
	public Circle(Integer id, String name){
		this.id = id;
		this.name = name;
	}
}
