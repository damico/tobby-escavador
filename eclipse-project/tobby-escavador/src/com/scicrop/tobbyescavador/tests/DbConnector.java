package com.scicrop.tobbyescavador.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DbConnector {
	
	
	
	public Connection getConnection() {
	     try {
	         return DriverManager.getConnection(
	 "jdbc:mysql://localhost/solos", "root", "");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
	
	public void close(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException{
	
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(con!=null) con.close();

	}
	
	public void close(PreparedStatement ps, Connection con) throws SQLException{
		
		if(ps!=null) ps.close();
		if(con!=null) con.close();

	}
}
