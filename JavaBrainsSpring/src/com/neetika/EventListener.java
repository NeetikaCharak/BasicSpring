package com.neetika;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		System.out.println("ApplicationListener : onApplicationEvent() :: Listening now");
		System.out.println("ApplicationListener : onApplicationEvent() :: Event = "+applicationEvent.toString());
		
	}

}
