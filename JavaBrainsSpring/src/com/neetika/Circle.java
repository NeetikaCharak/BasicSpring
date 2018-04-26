package com.neetika;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class Circle implements Shape {

	private Point pointA;
	
	//Spring annotations
/*	@Required
	@Autowired //THis one DI by parameter name and not by member variable name; needs beanpostprocessor name in xml file
	@Qualifier("circleRelated")
	public void setPointA(Point centre) {
		this.pointA = centre;
	}*/
	
	//JSR 250 Annotations; no setting needed in xml file
	//@Resource(name="pointD")
	@Resource //DI by NAme (of the member variable)
	public void setPointA(Point centre) {
		this.pointA = centre;
	}

	@Override
	public void draw() {
		System.out.println("Circle : draw(): Drawing Circle, points are "+this.pointA.getX()+", "+this.pointA.getY());
	}
	

	@PostConstruct
	private void initialize(){
		System.out.println("---JSR 250 --- After initializing bean");
	}
	
	@PreDestroy
	private void destroy(){
		System.out.println("---JSR 250 --- Before destroying bean");
	}
	
	public void testLoggingAspect(String myName) {
		System.out.println("Circle : testLoggingAspect(): Method with Argument as STring "+myName);
	}
	
	public String testLoggingAspectError(String myName) {
		System.out.println("Circle : testLoggingAspectError(): Method with Argument as STring "+myName);
		return "Testing If value is returned to Aspect?";
		//throw(new RuntimeException());
	}
	
	@LoggableAnnotation()
	public void testingCustomAnnotation() {
		System.out.println("Circle : testingCustomAnnotation(): This Custon Annotation testing");
	}
	
}
