# Student Management System

A console-based Student Management System built with **Java, JDBC and MySQL**. It supports CRUD operations and calculates each student's total, average and grade.

## Features

- Add a student
- Display all student records
- Search a student by ID
- Update student details and marks
- Delete a student
- Calculate total marks, average and grade
- Validate semester and marks
- Use JDBC `PreparedStatement` for database operations

## Technology

- Java 17+
- MySQL 8+
- JDBC
- Maven

## Project structure

```text
Student-Management-System/
├── database/
│   └── student_management.sql
├── src/main/java/com/bca/sms/
│   ├── DBConnection.java
│   ├── Main.java
│   ├── Student.java
│   └── StudentDAO.java
├── .gitignore
├── pom.xml
└── README.md
```

## Requirements

Install:
1. JDK 17 or newer
2. MySQL Server and MySQL Workbench (or MySQL command line)
3. Apache Maven
4. IntelliJ IDEA, Eclipse or VS Code with Java support

## Setup instructions

### 1. Create the database

Open MySQL Workbench or the MySQL command line. Run the complete script in `database/student_management.sql`.

This creates the `student_management` database and the `students` table. It also inserts two optional sample records.

### 2. Configure database access

The application reads these environment variables:

- `DB_URL` (default: `jdbc:mysql://localhost:3306/student_management`)
- `DB_USER` (default: `root`)
- `DB_PASSWORD` (default: empty)

Set `DB_USER` and `DB_PASSWORD` to match your local MySQL account. Do not upload real passwords to GitHub.

Example (Windows PowerShell):

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="your_mysql_password"
```

Example (macOS/Linux):

```bash
export DB_USER="root"
export DB_PASSWORD="your_mysql_password"
```

### 3. Open the project

Open the folder containing `pom.xml` in your Java IDE. Allow Maven to download the MySQL JDBC driver.

### 4. Run the application

Run `com.bca.sms.Main` from your IDE.

Alternatively, from the project root:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.bca.sms.Main"
```

If your Maven setup does not have the exec plugin available, run `Main.java` from your IDE.

## Menu

```text
STUDENT MANAGEMENT SYSTEM

1. Add Student
2. Display All Students
3. Search Student by ID
4. Update Student
5. Delete Student
6. Exit
```

## Grade scale

| Average marks | Grade |
|---|---|
| 90–100 | A+ |
| 80–89.99 | A |
| 70–79.99 | B |
| 60–69.99 | C |
| 50–59.99 | D |
| Below 50 | F |

## Database table

`students` stores student ID, name, department, semester, email and marks in three subjects. Student ID is generated automatically and email must be unique.

## GitHub upload

1. Create a new GitHub repository named `Student-Management-System`.
2. Keep it public if you want to show it in your portfolio.
3. On your computer, open a terminal in this project folder.
4. Run:

```bash
git init
git add .
git commit -m "Initial Student Management System"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/Student-Management-System.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username. Never commit database passwords.

## Future improvements

- Add a graphical interface using Java Swing or JavaFX
- Add login and role-based access
- Export student reports to PDF or CSV
- Add unit tests and pagination

## Author

Add your name and course details here.
