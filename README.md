## Allazani-User-Service
The User Service is responsible for authentication, authorization, and user profile management within the E-Commerce Inventory & Checkout System.
It handles user registration, login, role assignment, JWT token generation, and secure access for other microservices.

This service is designed for a distributed microservice architecture and integrates with the API Gateway, Config Server, and other backend services.

### Core Responsibilities:
- User registration
- Secure login
- JWT-based authentication
- Role management (USER, ADMIN)
- Token validation endpoint for other services
- Password hashing (BCrypt)
- Exposing user info for other modules
- Integration with Config Server
- Actuator endpoints for observability

### Features:

#### Authentication and Authorization
- Username + Password Login
- JWT access token generation
- Token validation for other services (Gateway + Order service)
- Password hashing (BCrypt)

#### User Management
- Create user
- Update user profile
- Fetch user details
- Assign user roles (admin only)

#### Security Architecture
- Spring security
- Stateless authentication
- JWT Filter
- Role-based API protection


### Tech Stack
- Java 17
- Spring Boot 3.5.6
- Spring Security
- Spring Data JPA
- Spring Cloud Config
- PostgreSQL
- Lombok
- Docker
- Bruno
- Kafka
- Kubernetes
















