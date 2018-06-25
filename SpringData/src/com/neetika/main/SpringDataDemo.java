package com.neetika.main;

import com.neetika.dao.BasicJdbc;
import com.neetika.dao.HibernateDaoImpl;
import com.neetika.dao.SpringDaoSupport;
import com.neetika.dao.SpringJdbcSupport;
import com.neetika.model.CircleDetails;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

public class SpringDataDemo {
	
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("myconfig.xml");
		context.registerShutdownHook();
		
	    //Traditional JDBC connection 
		BasicJdbc dao = context.getBean("basicJdbc", BasicJdbc.class);//@Repository tag with this class; no bean in xml
		dao.getCircle(2);
		
		//Using Spring in Traditional JDBC connection 
		SpringJdbcSupport basicJdbcSupport = context.getBean("springJdbcSupport", SpringJdbcSupport.class);//@Repository tag with this class; no bean in xml
		basicJdbcSupport.getCircleUsingTraditionalJdbc(2);
		
		//Using Spring's JDBC TEMPLATE
		basicJdbcSupport.getCircleUsingJdbcTemplate();
		
		//Using Spring's JDBC TEMPLATE BEAN
		basicJdbcSupport.getCircleUsingJdbcTemplateBean(1);
		
		/*//Writing Data 
		Circle circle = new Circle(6, "Sixth Circle");
		//springDao.createCircleUsingJdbcTemplateBean(circle);
		springDao.createCircleUsingNamedParamJdbcTemplateBean(circle);*/
		
		//Using DAO Support classes
		SpringDaoSupport springDaoSupport = 
				context.getBean("springDaoSupport", SpringDaoSupport.class);//@Repository tag with this class; no bean in xml; NamedParameterJdbcTemplate is parent class
		springDaoSupport.getCircle(5);
		
		//Hibernate
		HibernateDaoImpl hibernateDaoImpl = context.getBean("hibernateDaoImpl", HibernateDaoImpl.class);//@Repository tag with this class; no bean in xml
		hibernateDaoImpl.getCircleCount();

		context.close();
	}

}
