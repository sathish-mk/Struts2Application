package com.ValidationStruts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	private static ConnectionFactory instance=new ConnectionFactory();
	public static final String Driver_Class="org.postgresql.Driver";
	public static final String URL="jdbc:postgresql://localhost:5432/tb2";
	public static final String User="postgres";
	public static final String Password="s@thish001";
	private ConnectionFactory() {
		try {
			Class.forName(Driver_Class);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private Connection createConnection() {
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(URL,User,Password);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static Connection getConnection() {
		return instance.createConnection();
	}
}
