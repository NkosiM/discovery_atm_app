# Discovery Bank : Bank Balance and Dispensing System

The goal of the Bank Balance and Dispensing System is to calculate and display the financial position to a client on an ATM screen.  In addition, a client must also be able to make a request for a cash withdrawal.

### Prerequisites
#####You will need java 8 installed on local machine
##### Docker
##### Docker-compose


### Getting Started

1. #Running Db service 

Ensure that you are on the root directory of the project, than execute the following command :
 
`docker-compose -f docker-compose.yml up -d`

The above command will startup the db service , which in this case is Postgresql. With the following set variables

db name : postgres

username : postgres
 
password: P@ssw0rd

Connecting to postgres through the cmd line

`psql -h localhost -U postgres -d postgres -p 6543`

#Running the Application

These are the commands needed to run the project

mvn clean install

mvn spring-boot:run

#Upon succesful startup
navigate and open the application.properties file (using your ide or via command line)
`touch discovery_atm_app/src/main/resources/application.properties`
Within the properties file, look for a variable named `spring.datasource.initialization-mode` and change its value from ALWAYS to NEVER. This ensure that on your next startup, the db tables data is not reinitialized but maintains current state

Application is configured to run on http://localhost:7070

   

#Swagger ui
 has been configured in the application Which will be our method of interaction with the application.

To access swagger: http://localhost:7070/swagger-ui.html#/atm-system-impl/


Swagger provides documentation for all APIs in the application, as well as defines the request and response object for the API


