# RestAPI-JAVA-MySQL
Here are the solutions to three differentes problems from a code challenge. This includes technologies such as Rest API in Java MySQL in a Web APP. The projects were developed in the Eclipse IDE.

**** PROBLEM 1 ****

Description
    Make a Java command line program that receives as input a path to a text file. The program should
    parse the content of the file, extract all the numbers (in digits including decimals), add them and
    output the result to the console.

Example of input file:
    Yesterday I bought 45 candies, 20 cars and 5 chocolates.
    It's been 11 days since the last rainfall.
    Today’s temperature is 43.2 degrees Celsius.
    
Expected result:
    45 + 20 + 5 + 11 + 43.2 = 124.2

**** PROBLEM 2 ****

Description:
    Enhance the previous program, so that it also saves in a new record of a table using plain JDBC in a
    SQL database (like HyperSQL, ORACLE, MySQL, etc.):
    - the file path
    - the result of the addition
    - the timestamp at program execution

Table format:
    FILENAME VARCHAR (128)
    FILEVALUE FLOAT
    PROCESSDATE TIMESTAMP
    
Important notes: 
    the program must create the table automatically if it does not exist in its
    first run. The program code must use standard ANSI-SQL (not propietary SQL code) to
    perform SQL operations. The connection string, user name and password to connect to
    database must be configurable in an external text file (.properties or .xml ).

**** PROBLEM 3 ****

Descripcion:
    Enhance program of Problem 2, to convert it into a REST API that uses a connection pool to connect to
    the database and exposes 2 verbs, one to upload a file to process and another that returns the
    content of the table in JSON format. Make a web application that contains a page with a form to load
    the file and use the REST API to load it and show the contents of the table using the second verb.

Important notes: 
    a .war file deployable on Tomcat 8.5 must be generated as result of build
    process. This .war file must contain both the REST API and the web pages of the web
    application and all required libraries and files. The use of a connection pool to manage
    database connections is mandatory. All the requirements of problem 2 must be filled also.
