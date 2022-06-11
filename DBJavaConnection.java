package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBJavaConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/schedules";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Yjzl013019";
	private static Connection connection;
	private static DBJavaConnection instance;
	
	private DBJavaConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static Connection getConnection() {
		
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DBJavaConnection(connection);
				System.out.println("Connection Successfull");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DBJavaConnection.connection;
	}
	
}
