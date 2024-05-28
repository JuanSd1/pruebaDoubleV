# Ticket Management API

## Descripción
API para la gestión de tickets con operaciones de creación, eliminación, edición y recuperación con paginación.

## Requisitos
- Java 11 o superior
- Maven

## Configuración

1. Configurar la base de datos en `application.properties` o usar Docker:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/prueba_doubleV
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update
    ```

2. Compilar y ejecutar la aplicación:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

3. Acceder a la API en `http://localhost:8080/api/v1/tickets`.

## Endpoints
- `GET /api/v1/tickets` - Obtener todos los tickets
- `GET /api/v1/tickets/{id}` - Obtener ticket por ID
- `POST /api/v1/tickets` - Crear un nuevo ticket
- `PUT /api/v1/tickets/{id}` - Actualizar un ticket existente
- `DELETE /api/v1/tickets/{id}` - Eliminar un ticket
- `GET /api/v1/tickets/page/{page}/size/{size}` - Listar tickets paginados

## API GitHub
- Api Rest Publica: `https://api.github.com/search/users?q=YOUR_NAME`

Descripción: Este endpoint permite buscar usuarios en GitHub y mostrar información de sus perfiles.
- Consumo de Api, para poder acceder a la api en Postman `http://localhost:8080/api/v1/github/users`