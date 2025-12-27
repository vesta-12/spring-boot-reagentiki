# Research Laboratory Management System

## Project Overview

This project is a **Spring Boot backend application** for managing a **research laboratory environment**.
It provides functionality for managing laboratories, researchers, experiments, equipment, and scientific publications, as well as a **fully implemented security system** with authentication, authorization, and role-based access control.

The project was developed as a **Software Engineering final project** and follows best practices in layered architecture, database migrations, security, and testing.

---

## Project Goals

* Provide a clean **REST API** for managing laboratory-related data
* Demonstrate **layered backend architecture**
* Implement **Spring Security** with JWT authentication
* Apply **role-based access control**
* Use **Liquibase** for database schema management
* Cover business logic with **unit tests**
* Provide complete **API testing via Postman**

---

## Architecture Overview

The project follows a **classic layered architecture**:

```
Controller → Service → Repository → Database
            ↓
           DTO ↔ Mapper
```

### Layers Description

* **Controller layer**

    * Exposes REST endpoints
    * Handles HTTP requests and responses
    * Returns `ResponseEntity`
    * Never exposes entities directly

* **Service layer**

    * Contains business logic
    * Handles transactions and validations
    * Coordinates repositories and mappers

* **Repository layer**

    * Uses Spring Data JPA
    * Responsible for database access

* **DTO layer**

    * Separates API contracts from database entities
    * Each entity has at least:

        * Request DTO
        * Response DTO

* **Mapper layer (MapStruct)**

    * Converts entities ↔ DTOs
    * Keeps controllers and services clean

* **Security layer**

    * JWT authentication
    * Role-based authorization
    * User management

---

## Database & Migrations

### Database

* Relational SQL database
* JPA / Hibernate ORM

### Migrations

* **Liquibase** is used for schema management
* No Flyway is used (by requirement)

#### Liquibase structure:

```
src/main/resources/db/changelog/
├── db.changelog-master.xml
└── changes/
    ├── tables.xml
    ├── insert.xml
    ├── security-tables.xml
    └── security-insert.xml
```

Liquibase automatically:

* Creates all tables
* Inserts default users (ADMIN / MANAGER / USER)
* Keeps database schema versioned

---

## Security

### Authentication

* JWT-based authentication
* Stateless sessions
* Passwords stored as **BCrypt hashes**

### Roles

The system supports **three roles**:

| Role    | Description                           |
| ------- | ------------------------------------- |
| USER    | Regular authenticated user            |
| MANAGER | Advanced user with extended access    |
| ADMIN   | Full access including user management |

### Security Features (Required)

✅ User registration
✅ User login (authentication)
✅ Change password
✅ Profile editing
✅ User creation by administrator
✅ User blocking / unblocking
✅ User deletion
✅ Role-based access control

### Security Endpoints

#### Authentication

```
POST /api/auth/register
POST /api/auth/login
POST /api/auth/change-password
```

#### Profile

```
GET /api/profile/me
PUT /api/profile/me
```

#### Admin User Management (ADMIN only)

```
GET    /api/admin/users
GET    /api/admin/users/{id}
POST   /api/admin/users
PUT    /api/admin/users/{id}/block
PUT    /api/admin/users/{id}/unblock
PUT    /api/admin/users/{id}/role/{role}
DELETE /api/admin/users/{id}
```

---

## 🧩 Domain Model

### Entities

* **Laboratory**
* **Researcher**
* **Experiment**
* **Equipment**
* **Publication**
* **AppUser** (security)

### Entity Relationships

* Laboratory ↔ Equipment
* Laboratory ↔ Experiment
* Experiment ↔ Researcher (Many-to-Many)
* Experiment ↔ Publication (One-to-Many)

---

## REST API Overview

Each entity supports standard CRUD operations:

| Entity      | Endpoints           |
| ----------- | ------------------- |
| Laboratory  | `/api/laboratories` |
| Researcher  | `/api/researchers`  |
| Experiment  | `/api/experiments`  |
| Equipment   | `/api/equipment`    |
| Publication | `/api/publications` |

All endpoints (except `/api/auth/**`) require authentication.

---

## 🧪 Testing

### Unit Tests

* Implemented using:

    * **JUnit 5**
    * **Mockito**
* Service layer is fully covered:

    * Data retrieval
    * Data creation
    * Update logic
    * Error handling
* Mapper tests verify correct DTO ↔ Entity conversions

---

## Postman Collection

A complete **Postman collection** is provided to test:

* Authentication flows
* Profile management
* Admin user management
* CRUD operations for all entities
* Role-based access checks
* Negative scenarios (401 / 403 / 404 / 400)

### Environment Variables

```
baseUrl
token_admin
token_manager
token_user
labId
researcherId
experimentId
equipmentId
publicationId
```

Tokens and IDs are automatically saved using Postman test scripts.

---

## How to Run the Project

### Prerequisites

* Java 17+
* Gradle
* SQL database (configured in `application.properties`)

### Steps

1. Clone the repository
2. Configure database connection in `application.properties`
3. Run the application:

```bash
./gradlew bootRun
```

4. Liquibase will apply migrations automatically
5. Use Postman to test the API

---

## Default Users

| Username | Password | Role    |
| -------- | -------- | ------- |
| admin    | SLON123! | ADMIN   |
| manager  | SLON123! | MANAGER |
| user     | SLON123! | USER    |

---

## Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security (JWT)
* Liquibase
* MapStruct
* PostgreSQL / SQL DB
* JUnit 5 + Mockito
* Postman

---

## Conclusion

This project demonstrates a **complete backend system** with:

* Clean architecture
* Secure authentication
* Role-based authorization
* Database versioning
* Full API testing
* Production-ready design principles

The system is extensible and can be easily expanded with new entities, roles, or business logic.
