package com.neetika.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Features {
	private int radius;
	private String colorNew;

	@Column(name="RADIUS_CRITERIA")
	public int getRadius() {
		return radius;
	}

	@Column(name="COLOR_CRITERIA")
	public String getColorNew() {
		return colorNew;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setColorNew(String colorNew) {
		this.colorNew = colorNew;
	}

	public Features() {
		super();
	}
	
	public Features(int radius, String color) {
		this.radius = radius;
		this.colorNew = color;
	}

}
