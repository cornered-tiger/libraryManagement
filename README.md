# Library Management System

This project is a demo application designed to showcase various technologies and methodologies in building a Java-based web application using modern frameworks.

## Technologies Used

- **Java 17**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **Maven**
- **Git**
- **Flyway**

## Getting Started

### Prerequisites

To run this application, you will need:

- **Java 17** installed
- **Docker** and **Docker Compose** for setting up the database

### Running the Database

This application relies on a PostgreSQL database, which is set up to run in a Docker container. To start the database, use the following command in your terminal from project root folder:

```bash
cd contrib
docker compose up &
```

### Building and Running the Application

Once the database is running, you can start the application by following these steps:

```bash
git clone https://github.com/cornered-tiger/librarymanagement.git
```
Navigate into the project directory:
```bash
cd <project-directory>
```
Build and run the application in _intellij_ or from commandline using _Maven_:
```bash
mvn spring-boot:run
```
The application should now be up and running.

### Authentication and Authorization

This application uses JWT (JSON Web Token) based authentication and authorization.

- Upon successful login, a JWT token is generated and returned to the user. You need to call the _**Authenticate User**_ endpoint to obtain the access token.
- For subsequent requests, include the access token in the Authorization header (e.g., Authorization: Bearer <your-access-token>).

### Test Users

Two test user accounts have been created with the following credentials:
- **Client User** (role: CLIENT)
  - Username: `client`
  - Password: `client`
- **Owner User** (role: OWNER)
  - Username: `owner`
  - Password: `owner`

### Postman Collection

To make testing easier, a Postman collection has been provided in postman folder. This collection includes predefined requests to help you interact with the API, including:
- A request to call the `rest/authenticate (Authenticate User)` endpoint to get the JWT token.
- Sample requests that include the access token in the Authorization header.

### Automatic Variable Population in Postman

When running the Postman collection, you only need to call the authenticate endpoint (found in the collection). After successfully calling the authenticate endpoint, the access token will be automatically populated in the Authorization header for all subsequent requests.

You do not need to manually change any variables in the Postman collection—the token will be handled for you automatically, and all requests will be authenticated using the token retrieved from the authenticate endpoint.

You can import the Postman collection into your Postman app for easy testing.

### Project Purpose and Limitations

This project was intended as a demo, so some aspects have been simplified or only partially implemented to highlight certain concepts.

### Intentional Limitations;

- **Cross-Cutting Concerns:**  Logging and user input validation are implemented only in select places to demonstrate the approach, rather than as a comprehensive solution.
- **Database Constraints:**  Constraints are applied to only one database table, and column-level constraints are limited in scope.
- **Paging for Searching Books:**  Pagination for the book search feature is not implemented.
- **API Documentation:** OpenAPI/Swagger was not used for documenting API endpoints. Instead, a Postman collection has been provided for testing and exploring the API. This collection can be found in the root folder of the project.
- **Git Commit Messages:** Descriptive Git commit messages were not consistently added, as the project was focused on demonstrating functionality rather than best practices in version control.

