# 🔍 HTML Analyzer — Deepest Text Finder

![Java](https://img.shields.io/badge/Java-17-red?style=flat&logo=openjdk)
![Algorithm](https://img.shields.io/badge/Algorithm-Stack--Based-blue?style=flat)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=flat)

> Java application that analyzes HTML structure and identifies the deepest nested text element using Stack-based parsing.

---

## 📋 Table of Contents

- [Description](#-description)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [How to Run](#-how-to-run)
- [What I Learned](#-what-i-learned)
- [Author](#-author)

---

## 📖 Description

Given an HTML page fetched from a URL, this application traverses the DOM structure using a **stack-based algorithm** to determine which text node is nested at the greatest depth. It handles tag parsing, error scenarios, and external data fetching entirely in Java.

---

## ✨ Features

- 🌐 Fetches HTML content from any URL
- 🧱 Parses HTML tags using a Stack data structure
- 📍 Identifies the deepest nested text element
- ⚠️ Handles malformed HTML and connection errors

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Core language |
| Stack (Java Collections) | DOM traversal algorithm |
| Java Net (HttpURLConnection) | URL fetching |

---

## ▶️ How to Run

```bash
# Compile
javac HtmlAnalyzer.java

# Run with a URL
java HtmlAnalyzer https://example.com
```

---

## 📚 What I Learned

- Stack-based parsing logic applied to real data structures
- Algorithm design for tree traversal problems
- Error handling and defensive programming in Java
- Processing and consuming external data sources via HTTP

---

## 👤 Author

**Kauan Sarzi**

[![GitHub](https://img.shields.io/badge/GitHub-KauanSarzi-181717?style=flat&logo=github)](https://github.com/KauanSarzi)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Kauan%20Sarzi-0077B5?style=flat&logo=linkedin)](https://linkedin.com/in/kauan-sarzi)
[![Email](https://img.shields.io/badge/Email-kauansarzi24@gmail.com-D14836?style=flat&logo=gmail)](mailto:kauansarzi24@gmail.com)
