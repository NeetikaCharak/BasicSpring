package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PetAnimal_TablePerClass extends Animal_TablePerClass {

	@Column(name="PETANIMAL_NAME")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public PetAnimal_TablePerClass() {
		super();
	}
	
	public PetAnimal_TablePerClass(String name, String species) {
		this.name = name;
		this.setSpecies(species);
	}
}
