/*
 * Validates that a file has been chosen before of trying its processing.
 */
function validate() {
	var path = document.getElementById("filePath").value;
	if (path == "") {
		// Any file was selected.
		// A message near by the file select button is shown.
		document.getElementById("message").innerHTML = "Please, select a file.";
	} else {
		// A file has been chosen, thus the input and message are cleared.
		document.getElementById("message").innerHTML = "";
		document.getElementById("filePath").innerHTML = "";

		// The file is ready for its processing.
		calculateSum();
	}
}

/*
 * 
 */
function calculateSum() {
	// The method escape() formats the file path to send it as parameter in an URL.
	var filePath = escape(document.getElementById("filePath").value);
	// The service URL is set for its call.
	var URL = "http://localhost:8080/Problem03/rest/main/calculateSumFileAndSave?path=" + filePath;

	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("PUT", URL, false); // false for synchronous request
	xmlHttp.send(null);
	var response = xmlHttp.responseText;
	if (response == "DONE") {
		// The connection with the service was successful.
		// Displays a message about the operation.
		document.getElementById("message").innerHTML = "File processed!";
		document.getElementById("message").className = "ok";
		// the input for the file path is cleared.
		document.getElementById("filePath").innerHTML = "";

		// the html table is updated.
		displayTableData();

	} else {
		// An error occurred.
		// a message about it is displayed.
		document.getElementById("message").innerHTML = "Error processing file: "
				+ response;
		document.getElementById("message").className = "error";
	}	
}


/*
 * Use a service for obtaining all the data from the database table and displays
 * its content in a html table. 
 */
function displayTableData() {
	// visto en https://stackoverflow.com/questions/247483/http-get-request-in-javascript
	var URL = "http://localhost:8080/Problem03/rest/main/getTableContentJSON";
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", URL, false); // false for synchronous request
	xmlHttp.send(null);
	
	createTableFromJSON(xmlHttp.responseText);
}
/*
 * Given a string in format json, generates a HTML table with its content.
 */
function createTableFromJSON(jsonData) {
	jsonData = JSON.parse(jsonData);
	var col = [ "id", "filename", "filevalue", "processdate" ]; // these are the names in the json string.
	var colHeaders = [ "ID", "File path", "Sum value", "Date" ]; // these are the header names in the table HTML.
	
	// Crate dynamic table.
	var table = document.createElement("table");

	// Create HTML tale header row using the above array header.
	var tr = table.insertRow(-1); // Table row.

	for (var i = 0; i < colHeaders.length; i++) {
		var th = document.createElement("th"); // Table header.
		th.innerHTML = colHeaders[i];
		tr.appendChild(th);
	}

	// Add json data to the table as rows.
	for (var i = 0; i < jsonData.length; i++) {
		tr = table.insertRow(-1);

		for (var j = 0; j < col.length; j++) {
			var tabCell = tr.insertCell(-1);
			tabCell.innerHTML = jsonData[i][col[j]];
		}
	}

	// Finally add the newly created table with json data to a container.
	var divContainer = document.getElementById("showData");
	divContainer.innerHTML = "";
	divContainer.appendChild(table);
}
