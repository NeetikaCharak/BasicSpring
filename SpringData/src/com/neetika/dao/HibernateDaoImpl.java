package com.neetika.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void getCircle(int id) {
		System.out.println("\n");
		

		String hql = "select count(*) from Circle";//HQL operates only on entity names
		Query query = this.sessionFactory.openSession().createQuery(hql);		//openSession - get new session
		
		System.out.println("HibernateDaoImpl :: getCircle() :: Total Circles - " + query.uniqueResult());

	}

}
