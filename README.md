# PARCEL DELIVERY APPLICATION


This application is a simple parcel delivery application created to demonstrate Microservice Architecture Model using Spring Boot, Spring Cloud, Rabbitmq and Docker. The project is intended as a tutorial, but you can fork it and turn it into something else!

While designing the architecture, the CQRS pattern was applied. Data is divided into two as command and query. Rabbitmq is used for inter-service communication

## Tech stack
- java 11
- Maven
- Spring Boot
- Spring Security with JWT Token
- Spring Cloud Gateway
- Rabbitmq
- Docker
- Swagger
- CQRS Pattern

## Infrastructure

![Overview](https://user-images.githubusercontent.com/2388153/172686507-8b32a088-76ed-478d-a4d0-e213a0e899f3.png)

### Parcel Delivery Application
We can think of this service as a frontend application. All interfaces are in this application. Some interfaces are defined more than once, as user stories are desired to be separated completely. For example, each user story has separate signin interfaces. **Postman** can also be used instead of application. **Swagger** was used to achieve this in the project.

### Auth Service
Authorization responsibilities are extracted to a separate server, which grants **JWT** tokens for the backend resource services. Auth Server is used for user authorization as well as for secure service-to-service communication inside the perimeter.

### Gateway Service
**Spring Cloud Gateway** is a single entry point into the system, used to handle requests and routing them to the appropriate backend service 

### Order Service
Parcel delivery order is created with this service. Likewise, if the parcel has not departed, updates can be made regarding the request. The service stores incoming requests in its own database. Apart from that, it throws events to **Query** and **Shipment** services.

### Shipment Service
It is the service that ensures the shipment of incoming requests. It stores the requests in its own database and notifies the **Query service** of the changes.

### Query Service
The **Query service** aggregates the data of the other two services. It corresponds to the query in the **CQRS** pattern and prepares the necessary queries for the application.



## Information about the application

### Users
Users defined as ready in the application are specified in the table. Apart from these, users can define new user and admin can define new courier user.

| username | password |  role |
| :---: | :---: | :---: | 
| user@gmail.com | 12345678 | ROLE_USER | 
| admin@gmail.com | 12345678 | ROLE_ADMIN |  
| courier@gmail.com | 12345678 | ROLE_COURIER |  


### Important endpoint
Parcel delivery application is accessed via http://localhost:8080/parcel-delivery 

### Database information
| Database | Host(in docker) | Host(in machine) |  Port |
| :---: | :---: | :---: | :---: |  
| orderdb | POSTGRES | localhost | 5432 | 
| querydb | POSTGRES | localhost | 5432 |  
| shipmentdb | POSTGRES | localhost | 5432 | 
| authdb | POSTGRES | localhost | 5432 | 

### Rabbitmq information
Host(in docker) | Host(in machine) |  Port |
| :---: | :---: | :---: |  
| rabbitmq3 | localhost | 15672 | 


## Let's try it out
The application can be run in 2 ways.
1) The application is copied to the local machine. Postgres and rabbitmq are installed and run with the configurations defined above. Spring boot services are run.
2) Run compose.yml with Docker (It may take up to 1 minute for the services to be ready).
``` docker compose up```


After any of these steps, http://localhost:8080/parcel-delivery address is opened with the browser.


![swagger](https://user-images.githubusercontent.com/2388153/172691146-cfa4a148-eafa-4b9f-9249-81edf8ec402b.png)


