import express from "express";
import cors from "cors";
import bodyParser from "body-parser";
import swaggerUi from "swagger-ui-express";
import swaggerJsdoc from "swagger-jsdoc";

const app = express();

// ✅ Middleware
app.use(cors());
app.use(bodyParser.json({ limit: "10mb" }));
app.use(bodyParser.text({ type: "text/*" }));

// 🧩 Dummy Encode/Decode Logic (Replace with real ones)
const encode = (jsonData) => {
  // Convert JSON to a TOON-like text format (for now just stringify)
  return JSON.stringify(jsonData);
};

const decode = (toonData) => {
  // Convert TOON-like text (stringified JSON or XML) back to JSON
  try {
    return JSON.parse(toonData);
  } catch {
    return { message: "Invalid TOON format or placeholder decoder not implemented yet" };
  }
};

// --------------------------------------------------------
// 🩺 Health Check
/**
 * @swagger
 * /health:
 *   get:
 *     summary: Check if the Toon Connector service is running
 *     responses:
 *       200:
 *         description: Service status
 */
app.get("/health", (req, res) => {
  res.json({ status: "ok", message: "Toon microservice running" });
});

// --------------------------------------------------------
// 🧠 JSON → TOON
/**
 * @swagger
 * /convert/json-to-toon:
 *   post:
 *     summary: Converts JSON data into TOON text format
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             example:
 *               Employee:
 *                 Name: Mohan
 *                 Salary: 50000
 *     responses:
 *       200:
 *         description: TOON formatted data as text
 */
app.post("/convert/json-to-toon", (req, res) => {
  try {
    const jsonData = req.body;
    const toonData = encode(jsonData);
    res.type("text/plain").send(toonData);
  } catch (err) {
    res.status(500).json({
      error: "Failed to convert JSON to TOON",
      details: err.message,
    });
  }
});

// --------------------------------------------------------
// 🔁 TOON → JSON
/**
 * @swagger
 * /convert/toon-to-json:
 *   post:
 *     summary: Converts TOON text data into JSON format
 *     requestBody:
 *       required: true
 *       content:
 *         text/plain:
 *           schema:
 *             type: string
 *             example: '{"Employee":{"Name":"Mohan","Salary":50000}}'
 *     responses:
 *       200:
 *         description: JSON representation of TOON data
 */
app.post("/convert/toon-to-json", (req, res) => {
  try {
    // if bodyParser.text used, req.body will be string
    const toonData = typeof req.body === "string" ? req.body : req.body.toon;
    if (!toonData) {
      return res.status(400).json({ error: "Missing 'toon' data in body" });
    }
    const jsonData = decode(toonData);
    res.json(jsonData);
  } catch (err) {
    res.status(500).json({
      error: "Failed to convert TOON to JSON",
      details: err.message,
    });
  }
});

// --------------------------------------------------------
// 🧾 Swagger Setup
const swaggerOptions = {
  definition: {
    openapi: "3.0.0",
    info: {
      title: "Toon Connector API",
      version: "1.0.0",
      description: "API documentation for Toon ⇄ JSON converter service",
    },
    servers: [{ url: "http://localhost:4000" }],
  },
  apis: ["./index.js"],
};

const swaggerDocs = swaggerJsdoc(swaggerOptions);
app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerDocs, {
  customSiteTitle: "Toon Connector - Swagger Docs",
}));

// --------------------------------------------------------
const PORT = 4000;
app.listen(PORT, () => {
  console.log(`🚀 Toon microservice running on port ${PORT}`);
  console.log(`📘 Swagger UI available at: http://localhost:${PORT}/api-docs`);
});
