package com.mycompany.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	
	// fields
	private Connection conn = null;
	private String dbURL;
	private String user;
	private String pass;
	
	
	// constructor
	public Controller(String dbURL, String user, String pass) {
		this.dbURL = dbURL;
		this.user = user;
		this.pass = pass;
	}  // constructor
	
	
	// getCustomerCount
	public String getCustomerCount() {
		String count = null;
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if(conn != null) {
				String countQuery = "select count(*) as oranges from customers;";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(countQuery);
				while(rs.next()) {
					count = rs.getString("oranges");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	} // getCustomerCount
	
	
	// getCustomerNames
	public String getCustomerNames() {
		String result = "";
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			if(conn != null) {
				String countQuery = "select companyname from customers;";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(countQuery);
				while(rs.next()) {
					result += rs.getString("companyname");
					result += "\n";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	} // getCustomerNames
	
	
} // Controller
