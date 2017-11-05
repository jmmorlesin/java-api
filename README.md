# Sample Java API using Spring Boot and JWT

This project shows a sample of an API in Java using Spring Boot with Jersey 2 integration and JWT to implement the security.
  
See more about JWT in [https://jwt.io](https://jwt.io)  

Before you start
----------------

In the folders src/main/resources and src/test/resources you can find an application.properties.template, you should do a copy of this file in the same folder and with the same name without the .template extension. With that you can specify the secret for the JWT security and the username and password to validate the login.

Endpoints
---------

GET  
/health  
Response:
```
{
  application: {
    name: "Java-API",
    version: "1.0.0",
    build: "1",
    time: 1509880048254
  },
  request: {
    requestedUrl: "http://localhost:8080/health",
    id: "42d03dc7-5144-474c-98e1-c92cd489d7c9"
  },
  response: {
    version: "1",
    environment: "DEVELOPMENT",
    startTime: 1509879993715,
    upTime: 54608
  }
}
```
In this case, for example, the build.properties file contains information about the environment, so can be used by the Continuous Integration tool to generate and replace it by the correct value.  

How to get a valid token
------------------------

Use the login endpoint with the valid credentials. In this sample, the unique user and password valid to get a token is defined in the *sample.json* in the credentials object.  

POST  
/login  
JSON Body content:  
```
{
  "username": "username",
  "password": "cGFzc3dvcmQ="
}
```

Response:
```
{
  "application" : {
    "name" : "Java-API",
    "version" : "1.0.0",
    "build" : "1",
    "time" : 1509880298855
  },
  "request" : {
    "requestedUrl" : "http://localhost:8080/login?expiration=1",
    "id" : "1dfd5c04-d8aa-4ef4-aa12-6cfdaa79cc15"
  },
  "response" : {
    "username" : "username",
    "token" : "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTUwOTk2NjY5M30.aw6aBC0gAoLL5Hx22cDVC20udDs6wFODSDdYo9a6YkFBTUVzHVBvxYmtKKkduVOqC9zb93elSJhjGbh-wn74eA",
    "expiration" : 1509966693584
  }
}

```

The password has to be encoded in Base64.

Now you have a valid token that will be valid 1 day.
With this valid token, you can access the endpoint in /authenticated adding the following header in the call: 

Key: Authorization  
Value: Bearer yourToken

License
-------

May be freely distributed under the [MIT license](https://github.com/jmmorlesin/node-api/blob/master/LICENSE).

Copyright (c) 2017 Jose M. Morlesín 