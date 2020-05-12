<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<title>COMVIVA - Problem3</title>

<script type="text/javascript" src="js/functions.js"></script>

</head>
<body onload="displayTableData()">

	<h3>Select the file to calculate the sum of its numbers</h3>

	<label for="myfile">Select a file:</label>
	<input type="file" id="filePath" name="filePath">
	<label id="message" class="error"></label>
	</br>
	<input type="button" value="Process" onClick="validate()" />

	<h3> Previous processed files </h3>
	<p id="showData"></p>

</body>
</html>
