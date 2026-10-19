---

### **Key Enhancements Included Below**
1. **Package & Class Name Check**: Formatted the paths to fit your `quickchat` package structure (`quickchat.QuickChatApp` / `com.prog5121`).
2. **Password Special Character Fix**: Updated the test data password to `Ch&&sec@ke99!` (with the double `&&`) as specified in the assignment test brief.
3. **Submission & Compliance Section**: Added a section explicitly confirming **Console-only execution**, **GitHub Version Control (minimum 6 commits requirement)**, and a dedicated placeholder for your **Unlisted YouTube Video link**.
4. **Part 3 / Final PoE Extension Section**: Added an optional, commented/clean section for **Part 3 (`MessageManager.java` & JSON management)** so this exact README easily scales from **Formative 2 Part 2** to the **Final PoE submission**.
5. **CI/CD Badge**: Included a GitHub Actions workflow status badge at the top.

---

### 📝 **Updated `README.md` (Copy & Paste Ready)**

```markdown
# QuickChat — PROG5121 POE

![Build Status]([suspicious link removed])

A console-based communication application built in Java for Parts 1, 2, and 3 of the **PROG5121 (Programming 1A)** Portfolio of Evidence (POE).

---

## 📁 Repository & Project Structure

```text
QuickChatApp/
├── pom.xml
├── README.md
├── .github/workflows/maven.yml       # Automated CI/CD running 'mvn test' on every push
└── src/
    ├── main/java/quickchat/
    │   ├── Login.java                 # Part 1 — User registration & authentication
    │   ├── Message.java               # Part 2 — Messaging logic & hash creation
    │   ├── MessageManager.java        # Part 3 — Parallel arrays, JSON storage & reports
    │   └── QuickChatApp.java          # Console entry point & menu driver
    └── test/java/quickchat/
        ├── LoginTest.java             # Part 1 JUnit 5 test suite
        ├── MessageTest.java           # Part 2 JUnit 5 test suite
        └── MessageManagerTest.java    # Part 3 JUnit 5 test suite
```

---

## 💡 Design Notes & System Features

### 👤 Part 1 — Registration & Authentication (`Login.java`)
- **Constructor-Based Onboarding**: Captures user details (`firstName`, `lastName`, `username`, `password`, `cellPhoneNumber`).
- **Validation Rules**:
  - `checkUserName()`: Verifies username contains an underscore (`_`) and is \\(\le 5\\) characters.
  - `checkPasswordComplexity()`: Enforces \\(\ge 8\\) characters, at least 1 uppercase letter, 1 digit, and 1 special character (`!@#$%^&*...`).
  - `checkCellPhoneNumber()`: Validates South African international format (`+27` followed by digits, max 13 characters).
- **Authentication**: `registerUser()` coordinates validation feedback; `loginUser()` and `returnLoginStatus()` handle login verification.

### 💬 Part 2 — QuickChat Messaging Engine (`Message.java`)
- **Random 10-Digit ID**: Autogenerates a unique 10-digit Message ID upon instantiation.
- **250-Character Limit Check**: `validateMessageLength()` checks input size and calculates exact overflow characters if exceeded.
- **Message Hash Generation**: `createMessageHash()` formats hashes as `[ID Prefix]:[SeqNum]:[FirstWordLastWord]` in uppercase (e.g., `00:0:HITONIGHT`).
- **Action Selection**: `sendMessage()` / `sentMessage()` handles:
  - `1: Send Message` (`"Message successfully sent"`)
  - `2: Disregard Message` (`"Press 0 to delete the message"`)
  - `3: Store Message` (`"Message successfully stored"`)
- **Volume Tracking & Storage**: Updates static total message count (`returnTotalMessages()`) and handles JSON serialization via `storeMessage()`.

### 📊 Part 3 — Array Management & Reporting (`MessageManager.java`)
- **Parallel Collections**: Organizes sent, disregarded, and stored messages across parallel lists.
- **JSON File Import/Export**: Reads from and writes stored messages to `messages.json`.
- **Search, Filter & Delete**: Search by Message ID, search all messages by Recipient cell number, identify the longest message, delete records by Message Hash, and generate full task reports.

---

## 🚀 Running the Application

### Command Line / Terminal (Maven)
```bash
# Compile and run via Maven
mvn compile exec:java -Dexec.mainClass="quickchat.QuickChatApp"

# Or package into JAR and run:
mvn package
java -jar target/quickchat.jar
```

---

## 🧪 Running Automated Unit Tests

Automated testing is configured with **JUnit 5** and runs automatically via GitHub Actions CI/CD on every commit push.

To run tests locally:
```bash
mvn test
```

### Test Data
All unit tests strictly utilize the official POE assignment test data:
- **Username**: `"kyl_1"`
- **Password**: `"Ch&&sec@ke99!"`
- **Cell Number**: `"+27838968976"` / `"+27839589762"`
- **Part 2 Test Message**: `"Hi Mike, can you join us for dinner tonight?"` sent to `"+27718693002"`

---

## 📌 Submission Compliance Checklist

- [x] **Console Application Only**: Built strictly as a CLI console app (no GUI/`JOptionPane` components used).
- [x] **Version Control**: Managed with continuous commits on GitHub (meets minimum commit history requirements).
- [x] **Automated CI/CD**: GitHub Actions workflow (`maven.yml`) passes `mvn test` on every push.
- [ ] **Video Presentation Link**: ``
```
