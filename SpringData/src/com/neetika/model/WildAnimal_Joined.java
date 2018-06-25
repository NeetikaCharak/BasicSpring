package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WildAnimal_Joined extends Animal_Joined {

	@Column(name="WILDANIMAL_NAME")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public WildAnimal_Joined() {
		super();
	}
	
	public WildAnimal_Joined(String name, String species) {
		this.name = name;
		this.setSpecies(species);
	}
}
