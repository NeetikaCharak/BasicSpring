package com.neetika.main;

import java.util.Date;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neetika.dao.HibernateDaoImpl;
import com.neetika.model.CircleDetails;

public class HibernateDemo {
	
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("myconfig.xml");
	
	    HibernateDaoImpl hibernateDaoImpl = context.getBean("hibernateDaoImpl", HibernateDaoImpl.class);//@Repository tag with this class; no bean in xml
	    
/*	    hibernateDaoImpl.getCircleCount();
	    
		hibernateDaoImpl.getCircle(2);
		
		hibernateDaoImpl.saveCircle(new CircleDetails(3, "Thirds Circle", new Date()));
		
		hibernateDaoImpl.saveCircle(new CircleDetails("New Circle", "This is new circle", new Date()));
		
		hibernateDaoImpl.getCircleCount();
	    
	    hibernateDaoImpl.saveCircleBatch();
	    
	    hibernateDaoImpl.saveAndReadUser();
	    
	    hibernateDaoImpl.fetchTypeConcept();
	    
	    hibernateDaoImpl.getAndLoadConcept();
	    
	    hibernateDaoImpl.relationshipsBetweenTables();
	    
	    hibernateDaoImpl.inheritenceInHibernate();
	    hibernateDaoImpl.objectStates();
	    hibernateDaoImpl.practiceHQL();
	    hibernateDaoImpl.hibernateFirstLevelCache();*/
	    hibernateDaoImpl.hibernateSecondLevelCache();
	    
	}

}
