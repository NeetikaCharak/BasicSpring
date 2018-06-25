package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Address is valueType class not entity, so to associate it with User table we have to use 
 * @Embeddable
 * @author Neetika
 *
 */
@Embeddable
public class Address {
	@Column(name = "STREET")
	private String street;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	public Address() {
		super();
	}
	
	public Address(String street, String city, String state, String country){
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}

}
