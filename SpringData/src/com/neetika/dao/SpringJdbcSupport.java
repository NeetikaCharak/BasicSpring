package com.neetika.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.neetika.model.Circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcSupport {
	
	
	/**
	 * Spring's JDBC Support is with JDBCTemplate, NamedParameterJdbcTemplate, SimpleJdbcTemplate and DAO Support classes
	 * 
	 */
	
	/**
	 * JDBCTemplate - 
	 * It is provided by spring that handles execution of preQuerystuff(conn), actualQueryStuff(rs.executeQuery), postQueryStuff.
	 * It avoids lot of repeatative code while making a query.It takes care of all the things that are common across multiple methods.
	 * It takes datasource and query which is only dynamic thing in any dao method
	 * 
	 * By Using JBDC template "bean"(spring xml config) we make sure that datasource is initialised in the template in the beginning only
	 * 
	 * DDL - queries to change database structure like table creation etc
	 * DML - queries to manipulate data like get, insert etc
	 * 
	 * Limitations of JDBCTemplate:
	 * 
	 * 	1- It only allows ?, not the names placeholders like ':id', So with ? we have to maintain the sequence while providing the 
	 *    argumemts to the query.To fix this limitation, Spring provides another class NamedParameterJdbcTemplate class. This Named..
	 *    class actually uses JDBCTemplte class internally and adds Named Param functionality at the top.
	 *  2- We need to set datasource in every DAO class, lets say we have 10 dao classes in a project, using JDBCtemplate we have to set datasource in all 10 dao classes.
	 *  That means same piece of code 10 times. One way is to have 1 common (generic) dao class and rest 10 dao classes will extend it. 
	 *  This is solved by spring, it provides a generic class that has datasource and jdbcTemplate as member variable, once set we can use it everywhere and without
	 *   repetation of code, By just extending the generic dao class.
	 * Since we have 3 jdbcTemplates; there are 3 generic/daosupport classes : JdbcDaoSupport, NamedParameterJdbcDaoSupport, SimpleJdbcDaoSupport
	 *  
	 * 
	 * SimpleJdbcTemplate is some of JDBCTemplate and some of NamedParameterJdbcTemplate; actually has the methods that are most commonly used
	 */
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplateBean;

	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Circle getCircleUsingTraditionalJdbc(Integer id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Circle circle = null;
		try {
			//Step 1 - Making connection
			//Advantage - We got rid from boilerplate code to make the connection, plus configuration part is in xml now and not in class file
			//Disadvantage - spring's datasource - DriverManagerDataSource: Every time it makes new connection and doesn't implement connection pooling.
			//Instead use dbcp for connection pooling
			conn = dataSource.getConnection();
			
			//Step2 - PreparedStatement
			ps = conn.prepareStatement("select * from circle where id = ?");
			ps.setInt(1, id);
			
			//Step3- Executing Query
			rs = ps.executeQuery();
			
			//Step4 - Iterating and returning result 
			if(rs.next()){
				circle = new Circle(id, rs.getString("name"));
			}
			System.out.println("\n");
			System.out.println("SpringJdbcSupport :: getCircleBeforeJdbcTemplate() :: CircleName - "+rs.getString("name"));
			return circle;
		} catch (Exception ex) {
			System.err.println(ex);
			throw new RuntimeException();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	 
	
	public void getCircleUsingJdbcTemplate() {
		String sql = "Select count(*) from circle";
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(this.dataSource);
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("\n");
		System.out.println("SpringJdbcSupport :: getCircleUsingJdbcTemplate() :: Total Circles - "+count);
	}
	
	/**
	 * Fetch with JDBC Template
	 * @param id
	 */
	public void getCircleUsingJdbcTemplateBean(int id) {
		System.out.println("\n");
		int count = jdbcTemplateBean.queryForObject("Select count(*) from circle", Integer.class);
		System.out.println("SpringJdbcSupport :: getCircleUsingJdbcTemplateBean() :: Total Circles - "+count);
		
		//When query needs argument and result as String/int
		String name = jdbcTemplateBean.queryForObject("Select name from circle where id = ?", new Object[] {id}, String.class);//String.class for the return type
		System.out.println("SpringJdbcSupport :: getCircleUsingJdbcTemplateBean() :: Name of "+id+" is "+name);
		
		
		//When query needs arg and we need mapped Object
		System.out.println("SpringJdbcSupport :: getCircleUsingJdbcTemplateBean() :: ");
		Circle circle = jdbcTemplateBean.queryForObject("Select * from circle where id = ?", new Object[] {id}, new RowMapperImpl());
		System.out.println("\t Circle Object with id "+id+" is "+circle.getName());
		
		//When query needs arg and we need mapped Object LIST
		System.out.println("SpringJdbcSupport :: getCircleUsingJdbcTemplateBean() :: List Objects are :");
		List<Circle> circles = jdbcTemplateBean.query("Select * from circle", new RowMapperImpl());
		for(Circle c :circles){
			System.out.println("\t Circle Object with id "+c.getId()+" is "+c.getName());
		}
	}
	
	/**
	 * JDBCTemplate's update mehod is for cretion, removal and update
	 * @param circle
	 */
	public void createCircleUsingJdbcTemplateBean(Circle circle){
		System.out.println("\n");
		String sql = "insert into circle values(?,?)";
		int rowsUpdated = jdbcTemplateBean.update(sql, new Object[] {circle.getId(), circle.getName()});
		System.out.println("SpringJdbcSupport :: createCircleUsingJdbcTemplateBean() :: Updated "+rowsUpdated+" row(s).");
		getCircleUsingJdbcTemplateBean(circle.getId()); 
	}
	
	/**
	 * Using NamedParameterJDBCTemplate
	 * @param circle
	 */
	public void createCircleUsingNamedParamJdbcTemplateBean(Circle circle){
		System.out.println("\n");
		String sql = "insert into circle values(:id, :name)";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", circle.getId()).addValue("name", circle.getName());
		int rowsUpdated = namedParameterJdbcTemplate.update(sql, sqlParameterSource);
		System.out.println("SpringJdbcSupport :: createCircleUsingNamedParamJdbcTemplateBean() :: Updated "+rowsUpdated+" row(s).");		
		getCircleUsingJdbcTemplateBean(circle.getId()); 
	}
	
	/**
	 * This method is called for every row returned by result set
	 * @author Neetika
	 *
	 */
	private static final class RowMapperImpl implements RowMapper<Circle>{

		@Override
		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("\t RowMappper : rowNum"+rowNum);
			Circle circle = new Circle();
			circle.setId(rs.getInt("ID"));
			circle.setName(rs.getString("NAME"));
			return circle;
		}
		
	}
	
	


}
