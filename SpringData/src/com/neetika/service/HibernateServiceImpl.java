package com.neetika.service;

import com.neetika.dao.HibernateDaoImpl;

public class HibernateServiceImpl {

	HibernateDaoImpl dao = new HibernateDaoImpl();
	
	public HibernateServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public HibernateServiceImpl(HibernateDaoImpl dao) {
		this.dao = dao;
	}
	
	public Object getCircleCount(){
		try{
			return dao.getCircleCount();
		}catch(Exception ex){
			return -1;
		}
	}
}
