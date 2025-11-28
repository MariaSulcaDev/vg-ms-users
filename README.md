# SIGEI – User Management Microservice

## 🔧 Project Stack

- **Backend**: Java 17 (IntelliJ IDEA, Spring Boot Reactive)
- **Frontend**: React and TailwindCSS 4
- **Database**: MongoDB and PostgreSQL

---

## ✅ Project Purpose

The SIGEI project is a comprehensive system that seeks to **optimize and improve management** in early childhood education schools through modern microservices architecture, empowering educators to build and maintain efficient educational systems.

---

## 🛠️ Setup Instructions (Imperatives)

1. **Clone** the repository:

   ```bash
   git clone https://github.com/MariaSulcaDev/vg-ms-users.git
   ```

1. **Navigate** into the project directory:

   ```bash
   cd vg-ms-users-management/
   ```

1. **Run** the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

1. **Access** the Swagger UI documentation:

   ```bash
   http://localhost:9083/swagger-ui/index.html
   ```

1. **Test** the API endpoints:

   ```bash
   http://localhost:9083/v3/api-docs
   ```

1. **Access** the frontend (if running separately):

   ```bash
   http://localhost:5173
   ```

---

## ⚙️ User Management Functionalities (Imperatives)

1. **Manage** user accounts with comprehensive CRUD operations
1. **Assign** specific roles to users based on their responsibilities:
   - `ADMIN`: **Manage** all system users and configurations
   - `DIRECTOR`: **Oversee** institutional operations and staff management
   - `PROFESOR`: **Handle** classroom activities and student assessments
   - `AUXILIAR`: **Assist** teachers with daily educational tasks
   - `TUTOR`: **Guide** and **support** individual student development
   - `PADRE`: **Monitor** their children's educational progress
   - `MADRE`: **Track** family involvement in educational activities
1. **Create**, **update**, and **delete** user profiles with validation
1. **Authenticate** users through secure login mechanisms
1. **Filter** and **search** users by role, status, and institution affiliation
1. **Export** user data for reporting and administrative purposes

---

## 🧩 How to Use the User Management System (Advice with "should")

- You **should** access the API documentation at `http://localhost:9083/swagger-ui/index.html` to explore all endpoints.
- You **should** start by creating an ADMIN user to manage the entire system.
- You **should** assign appropriate roles (DIRECTOR, PROFESOR, AUXILIAR, TUTOR, PADRE, MADRE) based on user responsibilities.
- You **should** use the frontend interface at `http://localhost:5173` for user-friendly management operations.
- You **should** validate user permissions before performing administrative actions.
- You **should** regularly backup user data through the export functionality.

---

## 🎯 Future Plans (Advice & Suggestions)

- We **should** integrate OAuth2 authentication before the final release.
- We **should** implement role-based access control (admin, teacher, student) for better security.
- We **should** add comprehensive logging and monitoring for all microservices.
- We **should** implement service discovery and API gateway for microservices orchestration.
- We **should** consider containerization with Docker for easier local development.
- We **should** plan server deployment strategy for production environment.

---

## 📁 Hexagonal Architecture Structure

```text
/vg-ms-users-management
├── src/main/java/pe/edu/vallegrande/vgmsusersmanagement/
│   ├── application/           # Application Layer (Hexagonal Architecture)
│   │   ├── config/           # Configuration and CORS settings
│   │   └── service/          # Business logic and use cases
│   ├── domain/              # Domain Layer (Core Business Logic)
│   │   ├── enums/           # UserRole, UserStatus enums
│   │   └── model/           # User entity and domain models
│   └── infrastructure/      # Infrastructure Layer (External Adapters)
│       ├── client/          # External service clients
│       ├── dto/            # Data transfer objects
│       ├── repository/     # Data persistence adapters
│       └── rest/           # REST API controllers (Input Adapters)
├── src/main/resources/      # Application configuration (application.yml)
├── src/test/               # Unit and integration tests
├── Dockerfile             # Container configuration
├── pom.xml               # Maven dependencies
└── README.md             # ← You are here
```

> **Hexagonal Architecture Benefits**: Clean separation between business logic and external concerns, making the system more testable and maintainable.

---

## 🧑‍🏫 Development Workflow (Imperatives & Advice)

- **Create** a feature branch for microservice development:

  ```bash
  git switch -c feature/ms-###
  ```

- **Implement**, **test**, and **verify** your feature locally.
- **Run** the test suite:

  ```bash
  mvn test
  ```

- **Stage**, **commit**, and **push** your changes:

  ```bash
  git status
  git add .
  git commit -m "feat: your feature description"
  git push
  ```

- **Merge** the feature branch (done from frontend):

  ```bash
  git merge feature/ms-### -m "merge: integrate microservice feature"
  ```

  > You **should** follow Conventional Commits specification for all commit messages.
  > Backend microservice development **must** be completed before frontend integration.

---

## 🚀 Local Development Setup (Must & Need To)

- You **must** configure the environment variables in `application.yml`:
  - `MONGODB_URI` for database connection
  - Server port is configured to run on `9083`
- You **need to** configure CORS settings in the Spring configuration for frontend access.
- You **must** build the application for local testing:

  ```bash
  mvn clean package
  ```

- **Run** the JAR file locally:

  ```bash
  java -jar target/vg-ms-users-management-0.0.1-SNAPSHOT.jar
  ```

  > Currently all services are running **locally** without external server deployment.

---

## 💡 Best Practices & Tips

- You **should** write unit tests using JUnit 5 for all service classes.
- You **should** document any new REST endpoints in the API specification.
- You **should** run `mvn clean verify` before each commit to ensure code quality.
- You **should** follow Spring Boot naming conventions for all components.

---

## 📞 Questions & Support

If you need help:

- **Open** an issue in this repository.
- **Tag** `@MariaSulcaDev` for urgent technical issues.
- **Join** our development team chat for real-time collaboration and support.

---

**Thank you for your contributions to SIGEI!**
👍 *Let's build efficient educational management systems together.*

---
