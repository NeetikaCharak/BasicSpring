/**
 * 
 */
package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

/**
 * 
 * 
 * 
 * This class is to understand 2. Table-per-class Inheritence in hibernate.
 * 
 * 
 * Adv over Single Table
 * - No need of discriminator
 * Normalized way as no null in rows 
 * 
 * Disadv - Dupicate data (species)
 * 
 * 
 * 3 tables will be created - Animal_TablePerClass, WildAnimal_TablePerClass, petAnimal_TablePerClass
 * 
Hibernate: insert into Animal_TablePerClass (SPECIES, ANIMAL_ID) values (?, ?)
Hibernate: insert into PetAnimal_TablePerClass (SPECIES, PETANIMAL_NAME, ANIMAL_ID) values (?, ?, ?)
Hibernate: insert into WildAnimal_TablePerClass (SPECIES, WILDANIMAL_NAME, ANIMAL_ID) values (?, ?, ?)

 *
 *ij> select * from Animal_TablePerClass;
ANIMAL_ID  |SPECIES
--------------------------------------------------------------------------------------------------------------------------------------------
5          |Cat

1 row selected
ij> select * from petAnimal_TablePerClass;
ANIMAL_ID  |SPECIES                                                                                                                         |PETANIMAL_NAME        
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
6          |Cat                                                                                                                           |cat_pet                   

1 row selected
ij> select * from wildAnimal_TablePerClass;
ANIMAL_ID  |SPECIES                                                                                                                         |WILDANIMAL_NAME       
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
7          |Cat                                                                                                                           |cat_wild              

1 row selected
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Animal_TablePerClass {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animalSeqId") 
	@SequenceGenerator(name="animalSeqId", sequenceName ="animal_custom_id", allocationSize = 5)//Created product_custom_id in Derby first
	@Column(name="ANIMAL_ID")
	private int animalId;
	
	@Column(name="SPECIES")
	private String species;

	public int getAnimalId() {
		return animalId;
	}

	public String getSpeciess() {
		return species;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
	
	public Animal_TablePerClass() {
		super();
	}
	
	public Animal_TablePerClass(String species) {
		this.species = species;
	}
}
