package com.problem02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
	// JDBC driver name and database URL
	private String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	private String USER;
	private String PASS;
	private String DB_NAME;
	private String TABLE_NAME;

	Connection conn = null;
	Statement stmt = null;

	/*
	 * The constructor initializes the database credentials with values saved in file the 'config.properties'. 
	 */
	public MySQLAccess() throws IOException {
		GetPropertyValues objGetProp = new GetPropertyValues();
		String[] prop = objGetProp.getPropValues();

		USER = prop[0];
		PASS = prop[1];
		DB_NAME = prop[2];
		TABLE_NAME = prop[3];

	}

	/*
	 * Initialize the object 'conn' according to the credentials and creates a database 
	 * (if it does not exist). Returns 'true' if success, otherwise returns 'false'.
	 */
	public boolean connect() {
		boolean response = false;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating database if not exists...");
			stmt = conn.createStatement();

			String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
			stmt.executeUpdate(sql);

			System.out.println("Database connected/created successfully...");

			conn.setCatalog(DB_NAME); // to select the database for its use.
			response = true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return response;
	}

	/*
	 * Creates a table in the database (if does not exist) for storing the info
	 * associated to the sum of numeric values in files.
	 * Returns 'true' if success, otherwise 'false'.
	 */
	public boolean createTable() {
		boolean response = false;
		try {
			stmt = conn.createStatement();

			// The field 'id' is the primary key.
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id int NOT NULL AUTO_INCREMENT,"
					+ "filename VARCHAR (128) NOT NULL," + " filevalue FLOAT NOT NULL,"
					+ " processdate TIMESTAMP NOT NULL," + " PRIMARY KEY (id))";
			
			stmt.executeUpdate(sql);
			response = true;
			System.out.println("Tabla " + TABLE_NAME + " (created) successfully!");
		} catch (SQLException e) {
			System.out.println("Error creating table " + TABLE_NAME + ": " + e.toString());
		}
		return response;
	}

	/*
	 * Given a file path and the sum of its numeric values, a row is added to the table.
	 * Returns 'true' if success, otherwise 'false'.
	 */
	public boolean insertIntoTable(String filePath, double sum) {
		boolean response = false;
		
		// The slashes in the path are formatted to be stored properly on the database.
		filePath = filePath.replace("\\", "\\\\");

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		try {
			stmt = conn.createStatement();

			String sql = "INSERT INTO " + TABLE_NAME + "(filename, filevalue, processdate)" + " VALUES ('" + filePath
					+ "', '" + sum + "', '" + date + "')";
			stmt.executeUpdate(sql);
			response = true;
			System.out.println("Values inserted in " + TABLE_NAME + " successfully!");
		} catch (SQLException e) {
			System.out.println("Error inserting in table " + TABLE_NAME + ": " + e.toString());
		}

		return response;
	}

	/*
	 * Closes the connection to the database. 
	 */
	public void closeConnection() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} // nothing we can do
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Conexion closed!");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} 

	}
}
