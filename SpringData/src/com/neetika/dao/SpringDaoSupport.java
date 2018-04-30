package com.neetika.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * USing NamedParameterJdbcDaoSupport which uses NamedParameterJdbcTemplate; so
 * it has datasource and namedparameterjdbctempplate object already
 * 
 * datasource for this is defined in spring xml class; just mentioning a bean
 * with id 'springDaoSupport' is enough, there is no need to do any autowiring
 * here in this class; nthing else is  required
 * 
 * @author Neetika
 *
 */
//@Repository - not using this as defined springDaoSupport bean in xml file; which points to datasource
public class SpringDaoSupport extends NamedParameterJdbcDaoSupport {

	public void getCircle(int id) {
		System.out.println("\n");
		int count = this.getJdbcTemplate().queryForObject("Select count(*) from circle",
				Integer.class);
		System.out.println("SpringDaoSupport :: getCircle() :: Total Circles - " + count);

	}
}
