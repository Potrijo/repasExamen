package ch.makery.address.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private String connectionURL =  "jdbc:mysql://localhost:3306/companyPOST";
	private	String user = "root";
	private String password = "root";

	public Connection connection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionURL, user, password);
		return conn;
	}
}
