# Task-Management-App

- Microservices with RESTFull backend API and a PostgresSQL database. The app able to: show a list of tasks; create,
  delete, and modify them with using different end points project for Spring Boot
  -We have enabled below API endpoints for operation.

    -POST /task/createTasks  
        The endpoint used for create multiple tasks.  
  -POST /task/createTask  
  The endpoint used for create single Task .
  -GET /task  
  The endpoint used for get All tasks and details
  -GET /task/{id}   
  The endpoint used for get a specific task by using its task id.
  -DELETE /task/{id}
  The endpoint used for delete a task by using its task id.
  -PATCH /task/{id}
  The endpoint used for updated a task with its ID

**setup**
-please update src/main/resources/application.yml file with all properties.

    example PostgresSQL database username and password. 

-Run Application from  **TaskManagementAppApplication**

    The application will run on http://localhost:8080/

-Swagger UI enabled So we can test APIs with below link.

http://localhost:8080/swagger-ui/index.html#/task-controller

-Or else Test APIs with external tools (example : POST-MAN)

    in project attached collection file (TaskManagment.postman_collection.json) All API for  testing via post man. 


   

