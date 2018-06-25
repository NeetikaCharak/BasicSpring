package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PetAnimal_Joined extends Animal_Joined {

	@Column(name="PETANIMAL_NAME")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public PetAnimal_Joined() {
		super();
	}
	
	public PetAnimal_Joined(String name, String species) {
		this.name = name;
		this.setSpecies(species);
	}
}
