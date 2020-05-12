package com.problem01;
import java.io.IOException;

/**
 * @author Fabio
 */
public class Main {	
	/*
	 * TEST CASES:
	 * 1- Text with negative numbers.
	 * 2- Just text without numbers.
	 * 3- Two numbers one by side. 
	 * 4- Empty file.
	 * 5- A file path invalid.
	 * 6- Text with more than one space after another.	
	 */	
	
	public static void main(String[] args) throws IOException {
		// It is checked if there is at least one argument.
		try {
			String pathFile = args[0]; // Trying to get the first argument.
			Problem1 objProblem1 = new Problem1();
			System.out.println("The summ of the values in file " + pathFile + "\nis: " + objProblem1.getSumFromTextPath(pathFile));
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: There is no argument in command line. " + e.toString());
		}
	}
}
