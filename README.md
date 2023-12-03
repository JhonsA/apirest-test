# APIREST JAVA

## Como usar
Ejecutar comando
```
mvn clean install spring-boot:run
```

## Versiones
```
Java 17
Apache maven 3.9.2
```

## BD
H2 bd en memoria, al levantar el proyecto las tablas y relaciones se generan automaticamente.

Para revisar la consola en local
```
http://localhost:8090/h2-ui/

JDBC URL: jdbc:h2:mem:nttdb
Uername: sa
Password: N/A
```

## Swagger
Para ingresar a Swagger
```
http://localhost:8090/doc/swagger-ui/index.html
```

## Peticiones

### http://localhost:8090/auth/register | POST

body
```
{
  "nombre": "Jhons Albornoz",
  "correo": "jhons.albornoz.araya@gmail.cl",
  "password": "123456789",
  "telefonos": [
    {
      "numero": "12345678",
      "codigoCiudad": "123",
      "codigoPais": "123"
    }
  ]
}
```

Responses:

200
```
{
    "id": 1,
    "creado": "2023-12-03T15:54:11.757946",
    "modificado": "2023-12-03T15:54:11.757946",
    "ultimoLogin": "2023-12-03T15:54:11.757946",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaG9ucy5hbGJvcm5vei5hcmF5YUBnbWFpbC5jbCIsImlhdCI6MTcwMTYyOTcyNiwiZXhwIjoxNzAxNjMxMTY2fQ.4kiEH1w1GBGstcQG61QgM0Os_WKIx73RPIS7Un0iCiM",
    "activo": true
}
```

### http://localhost:8090/auth/login | POST

body
```
{
  "correo": "jhons.albornoz.araya@gmail.cl",
  "password": "123456789"
}
```

reponse:

200
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaG9ucy5hbGJvcm5vei5hcmF5YUBnbWFpbC5jbCIsImlhdCI6MTcwMTYzMTQzNywiZXhwIjoxNzAxNjMyODc3fQ.xoELAiuOfKMrh5HMzsbyriJGP9FpvnQ37j0kaMojEOY"
}
```

### http://localhost:8090/users/ | GET

Authorization
```
Bearer token
```

body
```
```

Responses:

200
```
[
    {
        "id": 1,
        "nombre": "Jhons Albornoz",
        "correo": "jhons.albornoz.araya@gmail.cl",
        "password": "$2a$10$z7GHJmdRW6CeB7ptER6sWegXYo0UHveRdOm9oz47a/ehDcEOJZIyO",
        "telefonos": [
            {
                "id": 1,
                "numero": "12345678",
                "codigoCiudad": "123",
                "codigoPais": "123",
                "creado": "2023-12-03T15:54:11.757946",
                "modificado": "2023-12-03T15:54:11.757946",
                "activo": true
            }
        ],
        "creado": "2023-12-03T15:54:11.757946",
        "modificado": "2023-12-03T15:54:11.757946",
        "ultimoLogin": "2023-12-03T15:55:32.204185",
        "activo": true,
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaG9ucy5hbGJvcm5vei5hcmF5YUBnbWFpbC5jbCIsImlhdCI6MTcwMTYyOTczMiwiZXhwIjoxNzAxNjMxMTcyfQ.ZYOs-bnt9sycfUBufULqLnzfNoGofD85Uo88eUJ0k7k",
        "role": "USER"
    }
]
```

### http://localhost:8090/users/{id} | GET

Authorization
```
Bearer token
```

body
```
```

Responses:

200
```
{
    "id": 1,
    "nombre": "Jhons Update",
    "correo": "hola.hola22@hola.cl",
    "password": "$2a$10$z7GHJmdRW6CeB7ptER6sWegXYo0UHveRdOm9oz47a/ehDcEOJZIyO",
    "telefonos": [
        {
            "id": 1,
            "numero": "12345678",
            "codigoCiudad": "123",
            "codigoPais": "123",
            "creado": "2023-12-03T15:54:11.757946",
            "modificado": "2023-12-03T15:54:11.757946",
            "activo": true
        }
    ],
    "creado": "2023-12-03T15:54:11.757946",
    "modificado": "2023-12-03T15:58:21.516293",
    "ultimoLogin": "2023-12-03T15:58:35.931188",
    "activo": true,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhLmhvbGEyMkBob2xhLmNsIiwiaWF0IjoxNzAxNjI5OTE1LCJleHAiOjE3MDE2MzEzNTV9.8LaO4z9j1E3EJktrhubwj2757gyUhwmO9n9_SXBsobg",
    "role": "USER"
}
```

### http://localhost:8090/users/ | POST

Authorization
```
Bearer token
```

body
```
{
  "nombre": "string",
  "correo": "string@string.cl",
  "password": "123456789AAAA",
  "telefonos": [
    {
      "numero": "string",
      "codigoCiudad": "string",
      "codigoPais": "string"
    }
  ]
}
```

Responses:

200
```
{
    "id": 2,
    "creado": "2023-12-03T15:54:11.760559",
    "modificado": "2023-12-03T15:54:11.760559",
    "ultimoLogin": "2023-12-03T15:54:11.760559",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmdAc3RyaW5nLmNsIiwiaWF0IjoxNzAxNjI5NzQ3LCJleHAiOjE3MDE2MzExODd9.RxkiZfAJY9LKYR8QZntKn04MLYroTbBW2uBYUXOIH84",
    "activo": true
}
```

### http://localhost:8090/users/{id} | PUT

Authorization
```
Bearer token
```

body
```
{
  "nombre": "string update 1",
  "correo": "string@update.cl",
  "password": "123456789AAAA"
}
```

Responses:

200
```
{
    "id": 2,
    "nombre": "string update 1",
    "correo": "string@update.cl",
    "password": "$2a$10$qu0Uzt.P7D0gcOknNPGsaunfcNWNpRinSA6Nn3S0nyn8D9cVlG56u",
    "telefonos": [
        {
            "id": 2,
            "numero": "string",
            "codigoCiudad": "string",
            "codigoPais": "string",
            "creado": "2023-12-03T15:54:11.760559",
            "modificado": "2023-12-03T15:54:11.760559",
            "activo": true
        }
    ],
    "creado": "2023-12-03T15:54:11.760559",
    "modificado": "2023-12-03T15:56:03.017293",
    "ultimoLogin": "2023-12-03T15:54:11.760559",
    "activo": true,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmdAc3RyaW5nLmNsIiwiaWF0IjoxNzAxNjI5NzQ3LCJleHAiOjE3MDE2MzExODd9.RxkiZfAJY9LKYR8QZntKn04MLYroTbBW2uBYUXOIH84",
    "role": "USER"
}
```

### http://localhost:8090/users/{id} | DELETE

Authorization
```
Bearer token
```

body
```
```

Responses:

200
```
```

### http://localhost:8090/users/{id} | PATCH

Authorization
```
Bearer token
```

body
```
{
  "correo": "hola.hola22@hola.cl"
}
```

Responses:

200
```
{
    "id": 2,
    "nombre": "Jhons Albornoz",
    "correo": "hola.hola22@hola.cl",
    "password": "$2a$10$lp.7UyNej1l.FFVnrQGMXORGBBLl0eifOM/gKEtG3kouJPNr8NUY.",
    "telefonos": [
        {
            "id": 1,
            "numero": "12345678",
            "codigoCiudad": "123",
            "codigoPais": "123",
            "creado": "2023-12-03T16:17:44.76912",
            "modificado": "2023-12-03T16:17:44.76912",
            "activo": true
        }
    ],
    "creado": "2023-12-03T16:17:44.76912",
    "modificado": "2023-12-03T16:24:14.431568",
    "ultimoLogin": "2023-12-03T16:23:57.819523",
    "activo": true,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaG9ucy5hbGJvcm5vei5hcmF5YUBnbWFpbC5jbCIsImlhdCI6MTcwMTYzMTQzNywiZXhwIjoxNzAxNjMyODc3fQ.xoELAiuOfKMrh5HMzsbyriJGP9FpvnQ37j0kaMojEOY",
    "role": "USER"
}
```

## Errores
400
```
{
    "mensaje": "msg"
}
```

401
```
{
    "mensaje": "msg"
}
```