# ToDoMicroService
Simple Spring Boot Micro Service for user specific todo lists

### In order to obtain Authorization Token send User crendentials

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

### Read ToDos (Authorization Token has to be included in HTTP Header)

localhost:8080/todo/api/v1/todos - GET

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTdGV2ZSJ9.Er6MxeZtN_I051Cq1WG3VLztGyE12f6rVRUcgMdfvVQ

```

### Write ToDo(s) (Authorization Token has to be included in HTTP Header)

localhost:8080/todo/api/v1/todo - POST

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTdGV2ZSJ9.Er6MxeZtN_I051Cq1WG3VLztGyE12f6rVRUcgMdfvVQ

```
```
{
	"todo": "ToDo message"
}
```
