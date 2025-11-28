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

1. **Navigate** into backend:

   ```bash
   cd vg-ms-users/
   ```

1. **Run** Spring Boot app:

   ```bash
   mvn spring-boot:run
   ```

1. **Navigate** into frontend:

   ```bash
   cd ../vg-web-sigei/
   ```

1. **Install** dependencies and **serve** the React app:

   ```bash
   npm install
   npm run dev
   ```

---

## 🧩 How to Use the App (Advice with "should")

- You **should** open `http://localhost:9083` after the backend is running.
- You **should** access the frontend at `http://localhost:5173` to interact with the user management interface.
- You **should** create administrator accounts to manage student and teacher profiles.
- You **should** test all CRUD operations through the REST endpoints before production use.

---

## 🎯 Future Plans (Advice & Suggestions)

- We **should** integrate OAuth2 authentication before the final release.
- We **should** implement role-based access control (admin, teacher, student) for better security.
- We **should** add comprehensive logging and monitoring for all microservices.
- We **should** implement service discovery and API gateway for microservices orchestration.
- We **should** consider containerization with Docker for easier local development.
- We **should** plan server deployment strategy for production environment.

---

## 📁 Repository Structure

```text
/vg-ms-users-management
├── src/main/java/          # Java 17 + Spring Boot REST API
│   ├── application/        # Application services and configuration
│   ├── domain/            # Domain models and enums
│   └── infrastructure/    # REST controllers and repositories
├── src/main/resources/    # Application configuration files
├── src/test/             # Unit and integration tests
├── Dockerfile           # Container configuration
├── pom.xml             # Maven dependencies
├── README.md           # ← You are here
└── target/             # Compiled classes and build artifacts
```

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
