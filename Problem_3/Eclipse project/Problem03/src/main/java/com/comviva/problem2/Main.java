package com.comviva.problem2;
import java.io.IOException;

import com.comviva.problem1.Problem1;


public class Main {

	public static void main(String[] args) throws IOException {
		// It is checked if there is at least one argument.
		try {
			String pathFile = args[0]; // Trying to get the first argument.
			Problem1 objProblem1 = new Problem1();
			
			MySQLAccess myDB = new MySQLAccess();
			myDB.connect();
			myDB.createTable();
			myDB.insertIntoTable(pathFile, objProblem1.getSumFromTextPath(pathFile));
			myDB.closeConnection();
			
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: There is no argument in command line. " + e.toString());
		}
		
		
		
	}
}
