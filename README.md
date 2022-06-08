# parcel-delivery-app


This application is a simple parcel delivery application created to demonstrate Microservice Architecture Model using Spring Boot, Spring Cloud, Rabbitmq and Docker. The project is intended as a tutorial, but you can fork it and turn it into something else!

While designing the architecture, the CQRS pattern was applied. Data is divided into two as command and query. Rabbitmq is used for inter-service communication

## Infrastructure


![Overview](https://user-images.githubusercontent.com/2388153/172662404-aba35f43-6f9f-46c0-bace-28f7610a2ed1.png)

### Parcel Delivery Application
We can think of this service as a frontend application. All interfaces are in this application. **Postman** can also be used instead of application. **Swagger** was used to achieve this in the project.

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


### Important endpoints
Parcel delivery application is accessed via (http://localhost:8080/parcel-delivery) 

### Database information
| Database | Host(in docker) | Host(in machine) |  Port |
| :---: | :---: | :---: | :---: |  
| orderdb | POSTGRES | localhost | 5432 | 
| querydb | POSTGRES | localhost | 5432 |  
| shipmentdb | POSTGRES | localhost | 5432 | 
| authdb | POSTGRES | localhost | 5432 | 

## Let's try it out

