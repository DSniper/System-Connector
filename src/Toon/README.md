```markdown
# 🎨 Toon Microservice API

**A lightweight Node.js + Express microservice** for converting between **JSON ⇄ TOON** formats.  
Designed to integrate seamlessly with your **Spring Converter API** for modular data transformation.

---

## 📦 Overview

This microservice runs independently on **Node.js (default port: 4000)** and provides:

- ✅ JSON → TOON conversion  
- ✅ TOON → JSON conversion  
- ✅ Health monitoring endpoint  
- ✅ Easy integration with Spring Boot via WebClient  
- ✅ Works standalone or containerized via Docker  

---

## 🧱 Folder Structure



D:/Softwares/System-Connector/src/Toon/
├── index.js             # Main Express server
├── package.json         # Dependencies & scripts
├── Dockerfile           # Container configuration
├── setup.bat            # Windows auto setup & start script
├── setup.ps1            # PowerShell version (optional)
└── README.md            # This file

````
## ⚙️ Installation (Local)

### 1️⃣ Clone the Repository

```bash
  git clone https://github.com/toon-format/toon.git
  cd toon
````

> 💡 If you're using this within **System-Connector**, navigate directly:
>
> ```bash
> cd D:\Softwares\System-Connector\src\Toon
> ```

---

### 2️⃣ Install Dependencies

```bash
  npm install
```

---

### 3️⃣ Start the Server

```bash
  npm start
```

When successful, you’ll see:

```
🚀 Toon microservice running on port 4000
```

Access the health endpoint:
👉 [http://localhost:4000](http://localhost:4000)

---

## ⚡️ API Endpoints

* Base URL: `[http://localhost:4000](http://localhost:4000)`
* Health URL: `[http://localhost:4000/health](http://localhost:4000/health)`
* Swagger UI URL: `[http://localhost:4000](http://localhost:4000/api-docs)`
* JSON-TOON URL: `[http://localhost:4000](http://localhost:4000/api-docs/#/default/post_convert_json_to_toon)`
* TOON-JSON URL: `[http://localhost:4000/api-docs/#/default/post_convert_toon_to_json](http://localhost:4000/api-docs/#/default/post_convert_toon_to_json)`

---

### 🩺 Health Check

**GET /**

**Response:**

```json
{
  "status": "ok",
  "message": "Toon microservice running"
}
```

---

### 🔁 JSON → TOON

**POST /convert/json-to-toon**

**Request Body:**

```json
{
  "name": "Alice",
  "age": 30,
  "skills": ["Java", "Spring", "Toon"]
}
```

**Response:**

```json
{
  "status": "success",
  "toon": "(name:Alice, age:30, skills:[Java,Spring,Toon])"
}
```

---

### 🔄 TOON → JSON

**POST /convert/toon-to-json**

**Request Body:**

```json
{
  "toon": "(name:Alice, age:30, skills:[Java,Spring,Toon])"
}
```

**Response:**

```json
{
  "status": "success",
  "json": {
    "name": "Alice",
    "age": 30,
    "skills": ["Java", "Spring", "Toon"]
  }
}
```

---

## 🧩 Integration with Spring Boot

If you’re using **Spring Converter API** on port `8085`, it connects to the Toon Microservice automatically.

**Example Spring Code:**

```java
WebClient webClient = WebClient.builder()
    .baseUrl("http://localhost:4000")
    .build();
```

| Spring Endpoint             | Calls Toon Endpoint     |
| --------------------------- | ----------------------- |
| `/api/v1/toon/json-to-toon` | `/convert/json-to-toon` |
| `/api/v1/toon/toon-to-json` | `/convert/toon-to-json` |

---

## 🧪 Testing via cURL

**1️⃣ JSON → TOON**

```bash
  curl -X POST http://localhost:4000/convert/json-to-toon \
     -H "Content-Type: application/json" \
     -d "{\"name\":\"Alice\",\"age\":30,\"skills\":[\"Java\",\"Spring\",\"Toon\"]}"
```

**2️⃣ TOON → JSON**

```bash
  curl -X POST http://localhost:4000/convert/toon-to-json \
     -H "Content-Type: application/json" \
     -d "{\"toon\":\"(name:Alice, age:30, skills:[Java,Spring,Toon])\"}"
```

---

## 🧰 Quick Start Script (Windows)

To make sure it **always works without npm issues**, use the provided automation script.

### ▶️ `setup.bat`

```bat
@echo off
title Toon Microservice - Setup & Start
color 0a
echo.
echo ==============================================
echo        🎨 TOON MICROSERVICE SETUP TOOL
echo ==============================================
echo.

cd /d "%~dp0"

echo 🔍 Checking Node.js installation...
where node >nul 2>nul
if %errorlevel% neq 0 (
    echo ❌ Node.js not found. Please install Node.js from https://nodejs.org/
    pause
    exit /b
)
echo ✅ Node.js found!
echo.

echo 🧹 Cleaning old dependencies...
if exist node_modules rmdir /s /q node_modules
if exist package-lock.json del /q package-lock.json
echo ✅ Cleanup complete.
echo.

echo 🧽 Clearing npm cache...
npm cache clean --force >nul
echo ✅ NPM cache cleared!
echo.

echo 📦 Installing dependencies...
npm install express cors body-parser >nul
if %errorlevel% neq 0 (
    echo ❌ Dependency installation failed.
    pause
    exit /b
)
echo ✅ Dependencies installed.
echo.

echo 🚀 Starting Toon microservice...
npm start
pause
```

Just double-click this file anytime you want to **reset and start** the Toon service.

---

## 🐳 Docker Setup

### 1️⃣ Build Docker Image

```bash
  docker build -t toon-service .
```

### 2️⃣ Run the Container

```bash
  docker run -d -p 4000:4000 --name toon toon-service
```

### 3️⃣ Verify

```bash
  docker ps
```

Expected output:

```
CONTAINER ID   IMAGE           COMMAND         STATUS         PORTS
abc123456789   toon-service    "npm start"     Up 2 minutes   0.0.0.0:4000->4000/tcp
```

---

## 🧩 Docker Compose Integration (Spring + Toon)

You can run both the **Spring Converter API** and **Toon Microservice** together using Docker Compose.

Create a `docker-compose.yml` in the project root:

```yaml
version: "3.8"
services:
  toon:
    build: ./src/Toon
    ports:
      - "4000:4000"

  spring-converter:
    build: ./spring-converter
    ports:
      - "8085:8085"
    depends_on:
      - toon
```

Run:

```bash
   docker-compose up --build
```

---

## 🧭 Troubleshooting

| Issue                  | Cause                      | Fix                                                    |
| ---------------------- | -------------------------- | ------------------------------------------------------ |
| `Cannot GET /`         | Accessed base URL directly | Use `/convert/json-to-toon` or `/convert/toon-to-json` |
| `ERR_MODULE_NOT_FOUND` | Missing dependencies       | Run `setup.bat`                                        |
| Port already in use    | Another service on 4000    | Change `PORT` in `index.js`                            |
| JSON parsing error     | Invalid JSON               | Validate request body before sending                   |

---

## 🧠 Future Enhancements

* [ ] File-based JSON/TOON upload
* [ ] TOON syntax validation engine
* [ ] Swagger / OpenAPI documentation
* [ ] Docker Compose health checks
* [ ] Integrated test suite

---

## ⚖️ License

This project is licensed under the **MIT License**.

---

* **Author:** *Daisy Manmohan Singh*
* 📧 [singhdmgangian@gmail.com](mailto:singhdmgangian@gmail.com)

* 🌍 GitHub: [@DSniper](https://github.com/DSniper)
