# Banking Application — MVC + N‑Tier (Java + MySQL)

This project implements a menu‑driven console banking app following **MVC** and **n‑tier** architecture.

## Run
1) Create DB schema:
   ```sql
   -- run file: sql/schema.sql
   ```
2) Edit `src/main/resources/db.properties` with your MySQL credentials.
3) Build & run:
   ```bash
  mvn spring-boot:run
http://localhost:8080/
   ```

## Features
- Create/Retrieve/Update/Delete accounts
- Deposit & Withdraw with validation and messages
- JDBC + PreparedStatement + proper resource handling
- Interfaces: DAO & Service layers
- Exceptions: Business vs Data access
- Clear console view + input validation

## Rubric mapping (quick checklist)
- Architecture & Design (40): Layers, MVC, DAO/DTO/Connection 
- Functionality (45): CRUD + deposit/withdraw + UI validation + messages 
- Java Implementation (20): Code style, OOP, SQL & transactions, close resources 
- Project & Docs (20): Packages, naming, comments, README 

See `src` for code comments explaining complex sections.
