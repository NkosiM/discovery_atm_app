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



Application is configured to run on http://localhost:7070

#Postgres GUI : 

   http://localhost:7070/postgres 
   
   

#Swagger ui
 has been configured in the application Which will be our method of interaction with the application.

To access swagger: http://localhost:7070/swagger-ui.html#/atm-system-impl/



