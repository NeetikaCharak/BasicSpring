package com.neetika;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public CustomEvent(Object source) {
		super(source);
		System.out.println("CustomEvent : constructor"); 
	}

	public String toString() {
		return "CustomEvent : Event Occurred";
	}

}
