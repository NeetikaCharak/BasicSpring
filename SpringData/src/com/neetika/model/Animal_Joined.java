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
 * This class is to understand 3. Joined Inheritence in hibernate.
 * 
 * 
 * It is more normalized than Table per class, now to get complete information abut pet animal, user has to do Join between animal_joined and petanimal_joined
 * 
 * 
Hibernate: insert into Animal_Joined (SPECIES, ANIMAL_ID) values (?, ?)

Hibernate: insert into Animal_Joined (SPECIES, ANIMAL_ID) values (?, ?)
Hibernate: insert into PetAnimal_Joined (PETANIMAL_NAME, ANIMAL_ID) values (?, ?)

Hibernate: insert into Animal_Joined (SPECIES, ANIMAL_ID) values (?, ?)
Hibernate: insert into WildAnimal_Joined (WILDANIMAL_NAME, ANIMAL_ID) values (?, ?)

 *
ij> select * from Animal_Joined;
ANIMAL_ID  |SPECIES
--------------------------------------------------------------------------------------------------------------------------------------------
10         |Horse


3 rows selected
ij> select * from PetAnimal_Joined
> ;
PETANIMAL_NAME                                                                                                                  |ANIMAL_ID
--------------------------------------------------------------------------------------------------------------------------------------------
horse_pet                                                                                                                             |10

1 row selected
ij> select * from wildanimal_joined;
WILDANIMAL_NAME                                                                                                                 |ANIMAL_ID
--------------------------------------------------------------------------------------------------------------------------------------------
horse_wild                                                                                                                             |10

1 row selected
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Animal_Joined {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animalSeqId") 
	@SequenceGenerator(name="animalSeqId", sequenceName ="animal_custom_id", allocationSize = 5)
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
	
	public Animal_Joined() {
		super();
	}
	
	public Animal_Joined(String species) {
		this.species = species;
	}
}
