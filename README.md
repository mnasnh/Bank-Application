# Bank-Application
Banking application designed to manage customers and accounts and execute transactions.

## Features
1. **Admin Operations** - To perform super user tasks like adding a new branch in the system.
2. **Customer management** - Activate, deactivate and view customers.
3. **Account management** - Open and close accounts, view accounts.
4. **Transaction management** - Credit and debit accounts, transfer amount between two accounts and view transaction history for an account.

## Getting started
1. Checkout the project from GitHub
git clone https://github.com/mnasnh/Bank-Application.git

2. Open IDE of your choice and Import as existing maven project in your workspace
- Import existing maven project
- Run mvn clean install
- If using STS, Run As Spring Boot App

Default port for the api is 8080

## Prerequisites
- JDK 21
## Maven dependencies
- spring-boot-starter-actuator
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- springdoc-openapi-starter-webmvc-ui
- spring-boot-devtools
- h2 - In memory database
- lombok - to reduce boilerplate code
- spring-boot-starter-test

## Swagger
Please find the Rest API documentation in the below url:
http://localhost:8080/bank-api/swagger-ui/index.html

## H2 In-Memory Database
In memory database console at the following URL: http://localhost:8080/bank-api/h2/

## Testing the Banking App APIs
- Please use the Swagger url to perform all operations.
- Browse to /src/test/resources to find sample requests for customer, accounts and transaction APIs
