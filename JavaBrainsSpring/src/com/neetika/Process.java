package com.neetika;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Process {

	public static void main(String[] args) {

		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("myconfig1.xml");
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("myconfig1.xml");
		//ctx.registerShutdownHook();

		String message = ctx.getMessage("greeting", null, "Msg When Key not available", null);
		System.out.println("--------Hey there-----" + message);// One way to get
																// message from
																// context;
																// other way is
																// via DI.
																// Context also
																// implements
																// MessageSource
																// interface and
																// hence it has
																// the method
																// getMessage();Other
																// method to get
																// message is in
																// SmallCircleMessageProps.java

		// Triangle shape = ctx.getBean("triangle", Triangle.class);
		// Shape shape = ctx.getBean("circle", Circle.class);
		//Shape shape = ctx.getBean("bigCircle", BigCircle.class);
		//Shape shape = ctx.getBean("smallCircleEventPub", SmallCircleEventPub.class);

		//shape.draw();
		
		
		/**
		 * AOP : When we use AOP, spring will replace the bean with name circle with a proxy object that calls a method on another object 
		 * before passing the call on to the original object. Whenever you ask for the bean with name circle, you'll get the proxy object instead of the real object.
		 * Proxy classes will always implement all interfaces they were created with. Spring will notice that Circle implements Shape, so the proxy class 
		 * it creates will also implement Shape.
		 * 
		 * A proxy Object is created after applying advice to the target object
		 * 
		 * If we use Circle circle = ctx.getBean("circle", Circle.class);
		 * we will get  org.springframework.beans.factory.BeanNotOfRequiredTypeException
		 */
		Shape sh = ctx.getBean("circle", Shape.class);
		sh.testLoggingAspect("ASPECTTEST");
		sh.testLoggingAspectError("ErrorTesting");
		sh.testingCustomAnnotation();
		sh.draw();
		 
		
		//ctx.close();
	}
}
