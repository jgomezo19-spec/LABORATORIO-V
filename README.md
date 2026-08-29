# Laboratorio V — APIs REST con Spring y Maven

## Presentación

En este laboratorio se desarrollaron diferentes **APIs REST independientes utilizando Spring Boot y Maven**, aplicando conceptos fundamentales de desarrollo de servicios web, HTTP, JSON, controladores REST y operaciones CRUD.

El laboratorio consiste en la creación de **10 APIs REST** para administrar diferentes tipos de información mediante **listas en memoria**, sin utilizar una base de datos.

Las APIs desarrolladas corresponden a:

1. Productos
2. Estudiantes
3. Libros
4. Empleados
5. Películas
6. Cursos
7. Vehículos
8. Tareas
9. Clientes
10. Pedidos

Cada API implementa operaciones para **consultar, crear, actualizar y eliminar información**, utilizando los métodos HTTP:

* `GET` — Consultar información
* `POST` — Crear nuevos registros
* `PUT` — Actualizar un registro completo
* `PATCH` — Actualizar parcialmente un registro
* `DELETE` — Eliminar un registro

Además, las APIs fueron probadas utilizando **Postman**, verificando el funcionamiento de los diferentes endpoints y el intercambio de información mediante JSON.

## Objetivos

* Desarrollar APIs REST utilizando Spring Boot.
* Aplicar los conceptos de HTTP y JSON.
* Crear controladores REST mediante Spring.
* Implementar operaciones CRUD.
* Trabajar con listas en memoria.
* Probar y verificar APIs utilizando Postman.
* Diferenciar el funcionamiento de los métodos GET, POST, PUT, PATCH y DELETE.
* Utilizar Git y GitHub para llevar un control de versiones del proyecto.

## Tecnologías utilizadas

* Java
* Spring Boot
* Maven
* Visual Studio Code
* Postman
* Git
* GitHub

## Estructura general

El proyecto está organizado utilizando modelos (`model`) para representar los datos y controladores (`controller`) para administrar los diferentes endpoints de cada API.

```text
spring-apis-lab/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── lab/
                    └── apis/
                        ├── ApisApplication.java
                        ├── controller/
                        └── model/
```

## Pruebas

Las diferentes operaciones de cada API fueron probadas mediante Postman, utilizando solicitudes `GET`, `POST`, `PUT`, `PATCH` y `DELETE`.

Los datos utilizados inicialmente se almacenan en listas en memoria, por lo que la información se reinicia al volver a ejecutar la aplicación.

## Autor

**Jairo Gomez B.**

Laboratorio V — APIs REST con Spring y Maven
