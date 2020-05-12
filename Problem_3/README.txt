This document contains the main details of the Problem 3 resolution.
- The solution was implemented in an Eclipse JAVA Maven project with archetype 'maven-archetype-webapp'.
- Apache Tomcat was set to port 8080.
- The config.properties file contains the MySQL database configuration access.
- The project includes two packages for reusing the source code of problems 1 and 2.

************************
		REST API
************************
There are defined two different services in the RESTful.java file (calculateSumFileAndSave and getTableContentJSON).
Then, these services are consumed in javascript using XMLHttpRequest objects (in functions.js file).

************************
		.war file
************************
The .war file is in the Eclipse folder project \Problem03\target\.