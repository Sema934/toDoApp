# TODO APP

##### Domain Package
Domain package consists of
* MyCouchbaseConfig Class: configuration class for Couchbase
* SpringFoxConfig Class: configuration class for Swagger

##### Domain Package
Domain package consists of
* Task Class : represents a task, contains fields id, title, context, due date, status and user, also toDTO method.
* User Class : represents a user, contains fields id, name, surname, username, password, email and a list of tasks, also toDTO method.

##### DTO Package
Dto package consists of
* TaskDTO Class
* UserDTO Class


#### Unit Tests

Mockito is used.
Run appropriate test class, to run the test suits.

######Note: Lombok plugin is used, especially for generating getters and setter. 