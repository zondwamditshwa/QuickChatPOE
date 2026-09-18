QuickChat — PROG5121 POE

Console-based chat application built for Parts 1 and 2 of the PROG5121 POE.

Structure

QuickChatApp/
├── pom.xml
├── .github/workflows/maven.yml # runs mvn test on every push
└── src/
├── main/java/quickchat/
│ ├── Login.java # Part 1 — registration & login
│ ├── Message.java # Part 2 — sending messages
│ └── QuickChatApp.java # console entry point (menu-driven)
└── test/java/quickchat/
├── LoginTest.java
└── MessageTest.java


Design notes

- **`Login`** takes the user's details through its constructor. `checkUserName()`,
  `checkPasswordComplexity()` and `checkCellPhoneNumber()` validate those stored
  details; `registerUser()` combines the three checks into the required
  messaging; `loginUser()` / `returnLoginStatus()` handle authentication.
- **`Message`** generates its own 10-digit ID in the constructor. `sendMessage()`
  handles the send/store/disregard menu options; sending assigns a sequential
  `messageNumber`, builds the message hash via `createMessageHash()`, and adds
  the message to the static, in-memory sent log used by `printMessages()` /
  `returnTotalMessages()`. `storeMessage()` appends the message to
  `messages.json` as a hand-written JSON array.
- Cell numbers are validated as South African international-format numbers:
  `+27` followed by up to ten digits.

Running it

```bash
mvn compile exec:java -Dexec.mainClass=quickchat.QuickChatApp
# or, after `mvn package`:
java -jar target/quickchat.jar
```

Running the tests

```bash
mvn test
```

Test data matches the exact values given in the POE brief, e.g. username
`"kyl_1"`, password `"Ch&@sec@ke99!"`, cell number `"+27838968976"`, and the
Part 2 message `"Hi Mike, can you join us for dinner tonight?"` sent to
`"+27718693002"`.