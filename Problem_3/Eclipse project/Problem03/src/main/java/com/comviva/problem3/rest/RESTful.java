package com.comviva.problem3.rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.comviva.problem1.Problem1;
import com.comviva.problem2.*;

@Path("main")
public class RESTful {

	/*
	 * Given a full file path, calculate the sum of file values and insert a row on
	 * the database table. Part of the response includes a string 'DONE' if success,
	 * otherwise returns a string describing the error.
	 */
	@PUT
	@Produces("text/html")
	@Path("calculateSumFileAndSave")
	public Response calculateSumFile(@QueryParam("path") String filePath) throws IOException, SQLException {
		String message = "";

		try {

			// The class defined for Problem 1 is reused for calculating the sum of the file
			// content.
			Problem1 objProblem1 = new Problem1();

			// The connection to the database is made by the class defined for Problem 2.
			MySQLAccess myDB = new MySQLAccess();
			if (myDB.connect()) {
				myDB.createTable();
				myDB.insertIntoTable(filePath, objProblem1.getSumFromTextPath(filePath));
				System.out.println("JSON as string: \n" + myDB.getAllTableRowsJSONasString());
				myDB.closeConnection();

				message = "DONE";
			} else {
				message = "Error in database connection.";
			}
		} catch (JSONException e) {
			message += e.toString();
		}

		return Response.status(200).entity(message).build();
	}

	/*
	 * This service returns a string containing every row in the database table in json format.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getTableContentJSON")
	public Response getTableContentJSON() throws IOException, SQLException {
		String message = "";
		try {

			// The table content is accessed via the methods defined for the Problem 2.
			MySQLAccess myDB = new MySQLAccess();
			if (myDB.connect()) {
				myDB.createTable();
				message = myDB.getAllTableRowsJSONasString();
				myDB.closeConnection();
			} else {
				message = "Error database connection.";
			}
		} catch (JSONException e) {
			message += e.toString();
		}

		return Response.status(200).entity(message).build();
	}
}