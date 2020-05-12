package com.problem01;

import java.io.*;

public class Problem1 {

	/*
	 * Given a full path of a text file, returns the sum of the numbers in its
	 * contain.
	 */
	public double getSumFromTextPath(String pathFile) throws IOException {
		double totalSum = 0;
		BufferedReader in;
		try {
			// Open the file.
			in = new BufferedReader(new FileReader(pathFile));

			try {
				String line = in.readLine();
				while (line != null) {
					totalSum = extractNumberInString(line, totalSum);
					line = in.readLine();
				}
			} catch (Exception e) {
				System.out.println("Error in the file path: " + e.toString());
			} finally {
				in.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.toString());
		}

		return totalSum;
	}

	/*
	 * Given a string text and a partial sum, it is added to the latter all the number values from the text.
	 */
	public double extractNumberInString(String text, double sum) {
		// All unnecessary spaces in the input 'text' are deleted.
		text = text.replaceAll("\\s{2,}", " ").trim();

		// The 'text' is split by blank spaces " " and saved in a String array.
		String[] textSplited = text.split(" ");
		for (int i = 0; i < textSplited.length; i++) {
			try {
				sum += Double.parseDouble(textSplited[i]);
			} catch (NumberFormatException e) {
				;// There is no need print a message when fails an attempt a conversion.
			}
		}
		return sum;
	}
}