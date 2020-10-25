# customer-account-service

##### To run the project, use the following command in the project root(The project requires Maven and Java 1.8) - 

mvn spring-boot:run 

##### or as a jar (run mvn package to create the jar before running) - 

java -jar target/customer-account-service-0.0.1-SNAPSHOT.jar

##### The default port is 8080. To switch ports use the following command - (8085 for instance)

mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085

java -jar target/customer-account-service-0.0.1-SNAPSHOT.jar --server.port=8085

##### When the project starts, 5 customer IDs are added to the database -

001, 002, 003, 004, 005

##### Endpoints (Default port of 8080 used) - 

http://localhost:8080/customerAction/getCustomerInformation/{customerID} - View customer information. The endpoint returns a custom model containing all the details.

http://localhost:8080/customerAction/createNewAccount/{customerID}/{initialCredit} - Creates a new account. The endpoint returns a success message or an error message. 

http://localhost:8080/index - View the UI

http://localhost:8080/h2-console - H2 Database(U - sa P - password)