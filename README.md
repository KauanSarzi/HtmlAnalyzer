# HTML Analyzer — Stack-Based HTML Depth Analyzer
![Java](https://img.shields.io/badge/Java-17-red?style=flat&logo=openjdk)
![Algorithm](https://img.shields.io/badge/Algorithm-Stack--Based-blue?style=flat)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=flat)


## 📌 Overview

HTML Analyzer is a command-line Java application that processes remote HTML documents and identifies the text located at the deepest level of the tag hierarchy.

The solution was developed under strict technical constraints as part of a Software Development Internship technical assessment.

The program implements manual parsing logic using only JDK 17, without any external libraries or built-in HTML/DOM utilities.

## 🧠 Problem Context

Given a valid HTML document retrieved from a URL, the program must:

* Identify the text node positioned at the deepest level of nested tags
* Return the first occurrence in case of ties
* Detect malformed HTML structures (bonus requirement)
* Handle URL connection errors

The challenge emphasizes:

* Hierarchical structure validation
* Stack-based depth tracking
* Manual parsing logic
* Strict adherence to technical constraints

## 🏗️ System Design

The application follows a structured processing pipeline:

```
URL Input → HTTP Connection → Line-by-Line Parsing → Stack-Based Depth Tracking → Deepest Text Identification → Structured Console Output
```

The solution uses a Stack (LIFO) data structure to:

* Track tag nesting levels
* Validate structural consistency
* Detect mismatched or unbalanced tags

Depth is calculated dynamically using the stack size.

## ⚙️ Core Engineering Decisions

### 1️⃣ Manual Parsing (No Libraries)

The implementation avoids:

* DOM parsers
* XML utilities
* External frameworks

All parsing logic is implemented manually using string manipulation and conditional checks.

### 2️⃣ Stack-Based Hierarchy Validation

* Opening tags → push to stack
* Closing tags → validate against stack top
* Text nodes → compare current depth

This guarantees structural validation and correct depth measurement.

### 3️⃣ Malformed HTML Detection

The program validates:

* Closing tags without matching opening tags
* Incorrect nesting order
* Unclosed tags at end of parsing

If detected, the output is:

```
malformed HTML
```

### 4️⃣ Controlled Output Contract

The application strictly follows the required output constraints:

* Deepest text
* `malformed HTML`
* `URL connection error`

No additional console output is generated.

## 🛠️ Technologies Used

* Java (JDK 17)
* `java.net` for HTTP connection
* `java.io` for stream handling
* `Stack` from Java Collections
* Manual string processing

No external dependencies.

## ⚙️ Features

✔ Stack-based depth calculation  
✔ First deepest node selection  
✔ Structural validation (malformed detection)  
✔ URL connection error handling  
✔ Strict CLI compliance  
✔ No external libraries  

## 🚀 How to Run

### Requirements

* JDK 17+

### Compile

```bash
javac HtmlAnalyzer.java
```

### Run

```bash
java HtmlAnalyzer <valid-url>
```

### Example

```bash
java HtmlAnalyzer http://example.com/page.html
```

## 📈 Technical Highlights

* Hierarchical structure validation using LIFO logic
* Linear time complexity O(n)
* Space complexity proportional to maximum nesting depth O(d)
* Defensive parsing implementation
* Clear separation between input handling and parsing logic

## ⚠️ Constraints Respected

* No HTML/DOM libraries
* No external dependencies
* CLI execution only
* Single source file compilation
* Strict output contract

## 💡 Engineering Takeaways

This project reinforces core programming concepts:

* Stack-based structure validation
* Parsing without abstraction layers
* Controlled I/O behavior
* Defensive programming under constraints

## 👤 Author

**Kauan Sarzi da Rocha**  
- [![LinkedIn](https://img.shields.io/badge/LinkedIn-Kauan%20Sarzi-0077B5?style=flat&logo=linkedin)](https://linkedin.com/in/kauan-sarzi)
- [![Email](https://img.shields.io/badge/Email-kauansarzi24@gmail.com-D14836?style=flat&logo=gmail)](mailto:kauansarzi24@gmail.com)

