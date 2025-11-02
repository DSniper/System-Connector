````markdown
# 🌿 Spring Converter API

**A modular Spring Boot 3.2 application for XML ⇄ JSON conversion and Base64 file encoding/decoding.**

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Build-orange.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

---

## 📚 Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Project Structure](#project-structure)
4. [Technology Stack](#technology-stack)
5. [Setup & Installation](#setup--installation)
6. [Configuration](#configuration)
7. [API Endpoints](#api-endpoints)
   - [Health Check](#health-check)
   - [XML ⇄ JSON Conversion](#xml--json-conversion)
   - [Base64 Encode/Decode](#base64-encodedecode)
8. [Example Folder Paths](#example-folder-paths)
9. [Error Handling](#error-handling)
10. [Troubleshooting & FAQ](#troubleshooting--faq)
11. [License](#license)

---

## 🧭 Overview

The **Spring Converter API** is a cleanly modularized Spring Boot app designed for developers working with **data format transformations** such as:

- XML ⇄ JSON conversion  
- Base64 encoding/decoding of text and files  
- Health check endpoint for DevOps monitoring  

It’s structured for scalability — easily extendable with new modules (e.g., CSV, YAML, PDF encoders).

---

## ✨ Features

✅ Convert **XML → JSON** and **JSON → XML**  
✅ Encode/Decode **text or binary files** using Base64  
✅ Configurable port via `application.yml`  
✅ Clear modular packaging (`xml`, `base64`, `config`)  
✅ Industry-grade exception handling and JSON responses  
✅ Ready for CI/CD integration and containerization  

---

## 🧱 Project Structure

```bash
spring-converter/
├── src/
│   ├── main/
│   │   ├── java/com/example/converter/
│   │   │   ├── SpringConverterApplication.java
│   │   │   ├── config/
│   │   │   │   └── AppConfig.java
│   │   │   ├── xml/
│   │   │   │   ├── controller/XmlController.java
│   │   │   │   └── service/XmlService.java
│   │   │   ├── base64/
│   │   │   │   ├── controller/Base64Controller.java
│   │   │   │   └── service/Base64Service.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── logback-spring.xml
│   └── test/
├── pom.xml
└── README.md
````

---

## ⚙️ Technology Stack

| Component   | Version  | Purpose               |
| ----------- | -------- | --------------------- |
| Java        | 17+      | Core language         |
| Spring Boot | 3.2.x    | Application framework |
| Maven       | 3.8+     | Build automation      |
| Jackson     | Latest   | JSON/XML parsing      |
| Lombok      | Optional | Code simplification   |
| JUnit       | 5.x      | Testing               |

---

## 🚀 Setup & Installation

### 1️⃣ Clone the project

```bash
git clone https://github.com/your-username/spring-converter.git
cd spring-converter
```

### 2️⃣ Build and Run

```bash
mvn clean package
java -jar target/spring-converter-0.0.1-SNAPSHOT.jar
```

The app will start at:
👉 **[http://localhost:8085](http://localhost:8085)**

---

## ⚙️ Configuration

The default port is defined in `src/main/resources/application.yml`:

```yaml
server:
  port: 8085
spring:
  application:
    name: spring-converter
```

> 💡 Change `8085` to any port — e.g., `9090` — and the entire project adapts automatically.

---

## 🌐 API Endpoints

### 🩺 Health Check

**Endpoint:**
`GET /api/v1/health`

**Response:**

```json
{
  "status": "UP",
  "timestamp": "2025-11-03T10:00:00Z"
}
```

---

### 🔁 XML ⇄ JSON Conversion

#### 1️⃣ XML → JSON

**Endpoint:**
`POST /api/v1/xml/convert-to-json`

**Headers:**

```
Content-Type: application/xml
Accept: application/json
```

**Request Body (example):**

```xml
<person>
    <name>John</name>
    <age>30</age>
    <address>
        <street>Main Street</street>
        <city>New York</city>
        <zip>12345</zip>
    </address>
</person>
```

**Response:**

```json
{
  "status": "success",
  "message": "Converted XML to JSON",
  "data": {
    "person": {
      "name": "John",
      "age": "30",
      "address": {
        "street": "Main Street",
        "city": "New York",
        "zip": "12345"
      }
    }
  }
}
```

---

#### 2️⃣ JSON → XML

**Endpoint:**
`POST /api/v1/xml/convert-to-xml`

**Headers:**

```
Content-Type: application/json
Accept: application/xml
```

**Request Body:**

```json
{
  "person": {
    "name": "John",
    "age": 30
  }
}
```

**Response:**

```xml
<person>
  <name>John</name>
  <age>30</age>
</person>
```

---

### 🔐 Base64 Encode/Decode

#### 1️⃣ Encode File

**Endpoint:**
`POST /api/v1/base64/encode`

**Form Data:**

| Key  | Type | Description    |
| ---- | ---- | -------------- |
| file | File | File to encode |

**Response:**

```json
{
  "status": "success",
  "message": "File encoded successfully",
  "data": "U29tZSBlbmNvZGVkIHRleHQ="
}
```

---

#### 2️⃣ Decode File

**Endpoint:**
`POST /api/v1/base64/decode`

**Request Body:**

```json
{
  "base64": "U29tZSBlbmNvZGVkIHRleHQ=",
  "outputPath": "D:/AI_Mode/decoded_output.txt"
}
```

**Response:**

```json
{
  "status": "success",
  "message": "File decoded and saved successfully",
  "outputPath": "D:/AI_Mode/decoded_output.txt"
}
```

---

## 📁 Example Folder Paths

When working with files:

```
D:/
└── AI_Mode/
    ├── input/
    │   └── sample.xml
    ├── output/
    │   ├── encoded.txt
    │   └── decoded_output.txt
```

---

## 🚨 Error Handling

| Error                     | Cause                   | Example Response                                  |
| ------------------------- | ----------------------- | ------------------------------------------------- |
| 400 Bad Request           | Invalid XML/JSON format | `{"status":"error","message":"Malformed JSON"}`   |
| 404 Not Found             | Wrong URL               | `{"error":"Not Found"}`                           |
| 500 Internal Server Error | Server failure          | `{"status":"error","message":"Unexpected error"}` |

---

## 🧩 Troubleshooting & FAQ

**Q:** I get `404 Not Found` for `/convert/xml-to-json`
**A:** Ensure your endpoint is `/api/v1/xml/convert-to-json` (note the difference).

**Q:** My XML file is too large (500MB+)
**A:** Use streaming parsers like `StAX` or configure Spring `multipart.max-file-size` in `application.yml`.

**Q:** I want to change output folder for Base64 decoded files
**A:** Update the `outputPath` field in the request JSON.

---

## 🧭 Future Enhancements

* [ ] CSV ⇄ JSON Converter
* [ ] PDF ⇄ Base64 Encoder
* [ ] YAML ⇄ JSON Support
* [ ] OpenAPI/Swagger UI Integration

---

## ⚖️ License

This project is licensed under the [MIT License](LICENSE).

---

* 🛠 **Author:** *Daisy Manmohan Singh*
* 📧 **Contact:** [singhdmgangian@gmail.com](mailto:singhdmgangian@gmail.com)
* 🌍 **GitHub:** [@DSniper](https://github.com/DSniper)