package com.comviva.problem2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

	String result = "";
	InputStream inputStream;

	// Returns a String array with 4 property values:
	// [0]: user
	// [1]: password
	// [2]: data base name
	// [3]: table name
	// If there is a problem accessing to property file, returns null.
	public String[] getPropValues() throws IOException {
		String[] result = null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
		
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			// Get the property values.
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String db_name = prop.getProperty("db_name");
			String table_name = prop.getProperty("table_name");

			// A String array is created with the property values from the configuration file.
			result = new String [] {user, password, db_name, table_name};
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}
