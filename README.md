---

```markdown
# ⚙️ System Connector API – Unified Spring Boot Microservice (v1.0.0)

A powerful **Spring Boot 3.2** based microservice designed to perform **multi-format data conversions** and system utilities — all from a single service running on **port 8085**.

> Modules included: XML ⇄ JSON, TOON ⇄ JSON, Base64 Encode/Decode, Config, and Health.
---

## 📚 Table of Contents
1. [Overview](#overview)
2. [Modules](#modules)
3. [Architecture](#architecture)
4. [Technology Stack](#technology-stack)
5. [Project Structure](#project-structure)
6. [Setup & Installation](#setup--installation)
7. [Configuration](#configuration)
8. [Swagger API Docs](#swagger-api-docs)
9. [API Endpoints](#api-endpoints)
10. [Error Handling](#error-handling)
11. [Docker Support](#docker-support)
12. [Troubleshooting & FAQ](#troubleshooting--faq)
13. [Future Enhancements](#future-enhancements)
14. [License](#license)

---

## 🧭 Overview

**System Connector API** is a single backend service that centralizes system operations for enterprise integration workflows.

### ✨ Supported Features
- XML ⇄ JSON Conversion  
- TOON ⇄ JSON Conversion *(Token-Oriented Object Notation)*  
- Base64 Encoding & Decoding  
- Health & Configuration Endpoints  
- Built-in Swagger UI for API documentation  

---

## 🧱 Modules

| Module | Purpose |
|--------|----------|
| 🧩 **XML** | Convert XML ⇄ JSON |
| 🌀 **TOON** | Convert TOON ⇄ JSON |
| 🔐 **Base64** | Encode or decode strings |
| ❤️ **Health** | Service status & uptime |
| ⚙️ **Config** | View runtime configuration |

---

## 🏗️ Architecture

```

+--------------------------------------------------------+

| SYSTEM CONNECTOR API (Spring Boot)                         |      |        |        |        |         |
| ---------------------------------------------------------- | ---- | ------ | ------ | ------ | ------- |
| XML                                                        | TOON | BASE64 | CONFIG | HEALTH | SWAGGER |
| +--------------------------------------------------------+ |      |        |        |        |         |

```
                     ↓
             REST APIs @ port 8085

## ⚙️ Technology Stack

| Component | Version | Description |
|------------|----------|-------------|
| Java | 17+ | Core language |
| Spring Boot | 3.2.x | Framework |
| Jackson | Latest | JSON/XML parsing |
| Lombok | Latest | Boilerplate removal |
| Swagger (Springdoc) | 2.6+ | API docs |
| Maven | 3.8+ | Build tool |
| Docker | 24+ | Containerization |

---

## 📁 Project Structure

```

system-connector/
├── src/main/java/com/example/connector/
│   ├── SystemConnectorApplication.java
│   ├── base64/
│   │   ├── controller/Base64Controller.java
│   │   └── service/Base64Service.java
│   ├── toon/
│   │   ├── controller/ToonController.java
│   │   └── service/ToonService.java
│   ├── xml/
│   │   ├── controller/XmlController.java
│   │   └── service/XmlService.java
│   ├── config/
│   │   └── ConfigController.java
│   ├── health/
│   │   └── HealthController.java
│   ├── config/SwaggerConfig.java
│   └── exception/GlobalExceptionHandler.java
├── src/main/resources/
│   ├── application.yml
│   └── logback-spring.xml
├── pom.xml
└── README.md

````

---

## 🚀 Setup & Installation

### 1️⃣ Clone Repository
```bash
  git clone https://github.com/your-username/system-connector.git
  cd system-connector
````

### 2️⃣ Build

```bash
  mvn clean package -DskipTests
```

### 3️⃣ Run

```bash
  mvn spring-boot:run
```

or

```bash
 java -jar target/system-connector-0.0.1-SNAPSHOT.jar
```

✅ Access: [http://localhost:8085/api/v1/health](http://localhost:8085/api/v1/health)

---

## ⚙️ Configuration

**`application.yml`**

```yaml
server:
  port: 8085

spring:
  application:
    name: system-connector

logging:
  level:
    root: INFO
```

---

## 📘 Swagger API Docs

* **Swagger UI:** [http://localhost:8085/swagger-ui.html](http://localhost:8085/swagger-ui.html)
* **OpenAPI JSON:** [http://localhost:8085/v3/api-docs](http://localhost:8085/v3/api-docs)

**SwaggerConfig.java**

```java
package com.example.connector.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI systemConnectorOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("System Connector API")
                        .description("Unified data conversion service (XML, TOON, Base64)")
                        .version("1.0.0"));
    }
}
```

---

## 🌐 API Endpoints

### 🩺 Health Check

`GET /api/v1/health`

```json
{ "status": "UP", "timestamp": "2025-11-03T10:00:00Z" }
```

---

### 🧩 XML ⇄ JSON

#### XML → JSON

`POST /api/v1/xml/to-json`

```xml
<user><name>Daisy</name><age>26</age></user>
```

Response:

```json
{"user":{"name":"Daisy","age":"26"}}
```

#### JSON → XML

`POST /api/v1/xml/to-xml`

```json
{"user":{"name":"Daisy","age":26}}
```

Response:

```xml
<user><name>Daisy</name><age>26</age></user>
```

---

### 🌀 TOON ⇄ JSON

**TOON** (*Token-Oriented Object Notation*) – a compact syntax for lightweight data objects.

#### JSON → TOON

`POST /api/v1/toon/json-to-toon`

```json
{"name":"Alice","age":30,"skills":["Java","Spring"]}
```

Response:

```json
{"toon":"(name:Alice, age:30, skills:[Java,Spring])"}
```

#### TOON → JSON

`POST /api/v1/toon/toon-to-json`

```json
{"toon":"(name:Alice, age:30, skills:[Java,Spring])"}
```

Response:

```json
{"json":{"name":"Alice","age":30,"skills":["Java","Spring"]}}
```

---

### 🔐 Base64 Encode/Decode

#### Encode

`POST /api/v1/base64/encode`

```json
{"text":"System Connector Rocks!"}
```

Response:

```json
{"encoded":"U3lzdGVtIENvbm5lY3RvciBSb2Nrcw=="}
```

#### Decode

`POST /api/v1/base64/decode`

```json
{"base64":"U3lzdGVtIENvbm5lY3RvciBSb2Nrcw=="}
```

Response:

```json
{"decoded":"System Connector Rocks!"}
```

---

### ⚙️ Config

`GET /api/v1/config`

```json
{"appName":"System Connector API","port":8085,"status":"active"}
```

---

## 🚨 Error Handling

| Code | Meaning        | Example                          |
| ---- | -------------- | -------------------------------- |
| 400  | Invalid Input  | `{"error":"Malformed JSON"}`     |
| 404  | Not Found      | `{"error":"Endpoint Not Found"}` |
| 500  | Internal Error | `{"error":"Unexpected Error"}`   |

---

## 🐳 Docker Support

### Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/system-connector-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app/app.jar"]
```

### docker-compose.yml

```yaml
version: '3.8'
services:
  system-connector:
    build: .
    container_name: system-connector
    ports:
      - "8085:8085"
    restart: always
```

Run:

```bash
docker-compose up --build
```

Access Swagger:
👉 [http://localhost:8085/swagger-ui.html](http://localhost:8085/swagger-ui.html)

---

## 🧩 Troubleshooting & FAQ

**Q:** Port 8085 in use?
→ Change `server.port` in `application.yml`.

**Q:** Swagger not showing?
→ Add dependency:

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.6.0</version>
</dependency>
```

**Q:** TOON conversion invalid?
→ Check parentheses `( )` and brackets `[ ]` in request.

---

## 🧭 Future Enhancements

* ✅ Unified microservice structure *(Done)*
* ✅ Swagger UI Integration *(Done)*
* [ ] YAML ⇄ JSON Converter
* [ ] File Upload Support
* [ ] Advanced TOON Syntax Validation
* [ ] JWT Authentication

---

## ⚖️ License

Licensed under the **MIT License**.

---

* 🛠 **Author:** Daisy Manmohan Singh
* 📧 [singhdmgangian@gmail.com](mailto:singhdmgangian@gmail.com)
* 🌍 GitHub: [@DSniper](https://github.com/DSniper)

```