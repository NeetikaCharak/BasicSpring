package com.neetika;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class SmallCircleEventPub implements Shape, ApplicationEventPublisherAware {// this
																					// class
																					// tells
																					// spring
																					// that
																					// it
																					// is
																					// going
																					// to
																					// publish
																					// event;
																					// so
																					// spring
																					// provides
																					// "PUBLISHER"
																					// object
																					// to
																					// it, which is actually the Application context

	ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void draw() {
		System.out.println("SmallCircleEventPub : draw() :: Publishing event now");
		applicationEventPublisher.publishEvent(new CustomEvent(this));
		System.out.println("SmallCircleEventPub : draw() :: Published successfully");

	}

	@Override // This method will populate Publisher in our class - spring
				// provides it which is actually the Application context
	//ApplicationContext is an Object that implements 'ApplicationEventPublisher', we can use ApplicaionContext too,
	//However we are using I/f ApplicationEventPublisherAware as best practicee
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		System.out.println("SmallCircleEventPub : setApplicationEventPublisher");
		this.applicationEventPublisher = applicationEventPublisher;
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
