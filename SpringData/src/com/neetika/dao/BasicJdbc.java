package com.neetika.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neetika.model.CircleDetails;

import org.springframework.stereotype.Repository;

@Repository
public class BasicJdbc {

	public CircleDetails getCircle(Integer id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		CircleDetails circle = null;
		try {
			//Step 1 - Making connection
			String driver = "org.apache.derby.jdbc.ClientDriver";
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db");
			
			//Step2 - PreparedStatement
			ps = conn.prepareStatement("select * from circle where id = ?");
			ps.setInt(1, id);
			
			//Step3- Executing Query
			rs = ps.executeQuery();
			
			//Step4 - Iterating and returning result 
			if(rs.next()){
				circle = new CircleDetails(id, rs.getString("name"));
			}
			System.out.println("\n");
			System.out.println("BasicJdbc :: getCircle() :: CircleName - "+rs.getString("name"));
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

}
