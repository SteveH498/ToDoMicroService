# ToDoMicroService
Simple Spring Boot Micro Service for user specific todo lists

localhost:8080/login - POST
```
{
	"username": "Steve",
	"password": "Password"
}
```

```
{
  "Token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTdGV2ZSJ9.Er6MxeZtN_I051Cq1WG3VLztGyE12f6rVRUcgMdfvVQ"
}
```


localhost:8080/todo/api/v1/todos - GET

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTdGV2ZSJ9.Er6MxeZtN_I051Cq1WG3VLztGyE12f6rVRUcgMdfvVQ

```
