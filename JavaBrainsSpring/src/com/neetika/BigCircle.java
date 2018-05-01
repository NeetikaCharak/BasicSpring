package com.neetika;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component//Stereotype Anotations - Telling spring that it is a bean Automatically use lowerletter for first letter in Bean name(value="bigCircle") 
//depending upon role of this class we an have @Controller, @Repository, @Services; all are same. There is no definition for them in spring xml file
public class BigCircle implements Shape {
	
	private Point centerPoint;
	
	@Resource
	public void setCenterPoint(Point pointE){
		this.centerPoint = pointE;
	}
	
	@Override
	public void draw() {
		System.out.println("Drawing BIG Circle, points are "+this.centerPoint.getX()+", "+this.centerPoint.getY());
	}

	@Override
	public void testLoggingAspect(String myName) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String testLoggingAspectError(String myName) {
		return null;
		
	}

	@Override
	public void testingCustomAnnotation() {
		// TODO Auto-generated method stub
		
	}

}

