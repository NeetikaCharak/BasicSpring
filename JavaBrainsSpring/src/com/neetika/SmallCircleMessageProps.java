package com.neetika;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class SmallCircleMessageProps implements Shape {

	private Point smallCenterPoint;

	private MessageSource messageSource;

	@Resource
	public void setSmallCenterPoint(Point o) {
		this.smallCenterPoint = o;
	}

	@Resource // Using implementation of MessageSource Interface :
				// ResourceBundleMessageSource, it has getMessage method
				// obviously (from parent)
	public void setMessageSource(MessageSource ms) {
		this.messageSource = ms;
	}

	@Override
	public void draw() {
		String message = this.messageSource.getMessage("small.circle.draw",
				new Object[] { this.smallCenterPoint.getX(), this.smallCenterPoint.getY() },
				"Msg When Key not available-Drawing SMALL Circle", Locale.FRENCH);
		System.out.println("---------MessageResource using DI---------------------");
		System.out.println(message);

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
