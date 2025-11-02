import express from "express";
import cors from "cors";
import bodyParser from "body-parser";


const app = express();
app.use(bodyParser.json());

// 🩺 Health Check
app.get("/health", (req, res) => {
  res.json({ status: "ok", message: "Toon microservice running" });
});

// 🧠 Convert JSON → TOON
app.post("/convert/json-to-toon", (req, res) => {
  try {
    const jsonData = req.body;
    const toonData = encode(jsonData);
    res.type("text/plain").send(toonData);
  } catch (err) {
    res.status(500).json({ error: "Failed to convert JSON to TOON", details: err.message });
  }
});

// 🔁 Convert TOON → JSON
app.post("/convert/toon-to-json", (req, res) => {
  try {
    const toonData = req.body.toon;
    if (!toonData) {
      return res.status(400).json({ error: "Missing 'toon' field in body" });
    }
    const jsonData = decode(toonData);
    res.json(jsonData);
  } catch (err) {
    res.status(500).json({ error: "Failed to convert TOON to JSON", details: err.message });
  }
});

const PORT = 4000;
app.listen(PORT, () => {
  console.log(`🚀 Toon microservice running on port ${PORT}`);
});
