[![Spring Boot](https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg)](https://spring.io)

-----------------------------------------------------

# Spring | Fast-Food

This is a fast-food application


## Getting Started

Spring(boot) takes care of everything on the back end | server side.

### Installing

```
1. git clone https://github.com/Evengelius/spring_fastfood.git your_desired_name

2. // Change the Project SDK version to 14 | Intellij
File | Project Structure | Project | Project SDK : 14.
```

## Functionnalities

**CRUD - Create | Read | Update | Delete**<br />
CRUD operations for each tables

**Exception handling**<br />
Errors on the server side are handled by various exceptions returning an HTTP status: 400 - 500 - 404

**Authentication management**<br />
Implementation of the JSON Web Token | JWT

**Data mapping management**<br />
Implementation of the Data Transfer Object or DTO, through MapStruct.

## API Endpoints | Postman

You need to be authenticated in order to use these endpoints.<br />
Also, here the example stands for *commands*.<br />
It can replaced by : drinks, burgers, or users.
<br /><br />
Finally, in order to use these endpoints, you need to be authenticated.

```
GET
       /api/commands
       /api/commands/{id}
POST
       /api/commands
PUT
       /api/commands/{id}
DELETE
       /api/commands/{id}

-----------------------------

Authentication | JWT

POST
       /api/users/register
POST
       /api/users/login
```

Once registered, it generates a JWT, that you need to put, *Authorization | Type : Bearer Token*, in order to be logged in.



## Screenshots | Demo

**Registration**

![Authentication | Register](https://image.noelshack.com/fichiers/2020/29/1/1594596785-jwt-spring.png)

**Hibernate | Query**

![Hibernate | Query](https://www.zupimages.net/up/20/29/9jud.png)

**Login | JWT generated**

![Authentication | Login](https://image.noelshack.com/fichiers/2020/29/1/1594596785-jwt-spring-login.png)

**Authenticated | Show Endpoint **

![Authentication | Authenticated](https://image.noelshack.com/fichiers/2020/29/1/1594596785-jwt-spring-authenticated.png)
