# Classifieds API

A robust, production-ready Spring Boot REST API for managing classified advertisements, listings, user interactions, and categories.

## 🚀 Features

*   **Spring Boot Backend**: High-performance, enterprise-ready API leveraging the Spring ecosystem.
*   **PostgreSQL Database**: Relational database storage managing user details, categories, and active ad listings.
*   **Dockerized Ecosystem**: Complete multi-container setup with `Dockerfile` and `docker-compose.yml` for single-command database and app provisioning.
*   **Data Persistence**: Built with Spring Data JPA / Hibernate for seamless database object-relational mapping.

## 🛠️ Tech Stack

*   **Language**: Java
*   **Framework**: Spring Boot
*   **Database**: PostgreSQL
*   **Build Tool**: Apache Maven
*   **Containerization**: Docker & Docker Compose

## 📁 Repository Structure

```text
classifiedsapi/
├── .mvn/                # Maven Wrapper configuration files
├── src/                 # Main application source code
│   ├── main/            # Application codebase and properties
│   └── test/            # Unit and integration test configurations
├── Dockerfile           # Multi-stage Docker build configuration
├── docker-compose.yml   # Multi-container orchestration (App + Postgres)
├── mvnw                 # Maven wrapper script for Linux/macOS
├── mvnw.cmd             # Maven wrapper script for Windows
├── pom.xml              # Maven project dependency management
└── README.md            # Project documentation
```

## ⚙️ Getting Started

### Prerequisites

*   **Java Development Kit (JDK)**: 17 or higher
*   **Docker** & **Docker Compose** (Highly Recommended)
*   Alternatively, a local **PostgreSQL** database instance running on port `5432`

---

### 🐳 Method 1: Instant Start with Docker Compose (Recommended)

The easiest way to run the application is to let Docker handle both the Spring Boot app and the PostgreSQL database container automatically. 

1. **Clone the repository:**
   ```bash
   git clone https://github.com
   cd classifiedsapi
   ```

2. **Spin up the whole ecosystem:**
   ```bash
   docker-compose up --build
   ```

Docker will create an internal network, provision the PostgreSQL database instance, execute your Maven build schema, and bring the API online.

---

### 💻 Method 2: Manual Local Development

If you prefer to run the Spring Boot application locally while targeting your own local or containerised database.

1. **Configure Environment Variables / Properties:**
   Ensure your local `src/main/resources/application.properties` (or `application.yml`) matches your local database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/classifieds_db
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

2. **Build and install dependencies:**
   ```bash
   ./mvnw clean install
   ```

3. **Boot up the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The local application will start and listen on port `8080` by default (`http://localhost:8080`).

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
