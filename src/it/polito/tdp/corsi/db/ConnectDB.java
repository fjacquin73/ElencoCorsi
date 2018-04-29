package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static Connection conn;
	private final static String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?useSSL=false&serverTimezone=UTC";
	
	public static Connection getConnection() {
		
		try {
			if (conn == null || conn.isClosed()) {
				
				conn = DriverManager.getConnection(jdbcURL, "root", "12021973");
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println("Errore di connessione al DB");
			throw new RuntimeException(e);
			
		}
		
		return conn;
		
	}
	
}
