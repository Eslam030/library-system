# library-system
- Simple Notes
  - application.properties is in src/main/resources/application.properties
  - Library_System.postman_collection is in the root directory
To Run the application 
- First The application is connected to MySql Database so 
  - If you are using xampp You don't need to do anything just create a database named `library_system` or any other name but you have to change it in the application.properties
  - If you are not using xampp and use any other MySql server you have to change the username and password which are in the application.properties file and also make a database named `library_system` or any other name but you have to change it in the application.properties file
- If you don't have Redis download it follow this article [Article](https://medium.com/@binary10111010/redis-cli-installation-on-windows-684fb6b6ac6b)

- To run the application
  - In case you are using IntelliJ go to src/main/java/com/example/library_system/LibrarySystemApplication and run from the run button
  - Otherwise, you can run from the terminal navigate to the root (the parent of the src directory ) then run `mvn spring-boot:run`

The endpoint you can test using Postman or any other tools
There is a file attached with Postman collection named (`Library_System.postman_collection`)
Those are the endpoints :
- GET /api/books
- GET /api/books/{id}
- POST /api/books
- PUT /api/books/{id}
- DELETE /api/books/{id}
- GET /api/patrons
- GET /api/patrons/{id}
- POST /api/patrons
- PUT /api/patrons/{id}
- DELETE /api/patrons/{id}
- POST /api/borrow/{bookId}/patron/{patronId}
- PUT /api/return/{bookId}/patron/{patronId}
And this is PostMan published API documentation [Documentation](https://documenter.getpostman.com/view/31551699/2sA3kd9cuY)


Technologies Used :
- Java SpringBoot
- Hibernate for database connection
  
Developed by `Eslam Sayed`
