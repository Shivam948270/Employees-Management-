# 🚀 User Management System (Java Servlet + JSP)

## 📌 Overview

This project is a **full-featured User Management System** built using **Java Servlets, JSP, and MySQL**.
It supports complete **CRUD operations**, file uploads, Excel/PDF export, filtering, and email integration.

The system simulates a real-world enterprise application with multi-table relationships and dynamic UI.

---

## ✨ Features

### 🔹 User Management

* Add new users
* Update user details
* Delete users
* View all users in table

### 🔹 Advanced Features

* Upload multiple images
* Upload bank/table image
* Store multiple bank accounts per user
* Dynamic filtering (UID, Email, Status)

### 🔹 Export Functionality

* 📊 Export users to Excel (with images)
* 📄 Export users to PDF (with images)

### 🔹 File Handling

* Multiple image upload
* File storage on server
* Dynamic image mapping

### 🔹 Email Integration

* Send email on successful login
* HTML-based email template

---

## 🛠️ Tech Stack

| Layer     | Technology                            |
| --------- | ------------------------------------- |
| Backend   | Java Servlets                         |
| Frontend  | JSP, Bootstrap                        |
| Database  | MySQL                                 |
| Libraries | Apache POI, iText PDF, Gson, JavaMail |
| Server    | Apache Tomcat                         |

---

## 📂 Project Structure

```text id="x9p2sm"
project/
│
├── src/company/
│   ├── User.java
│   ├── UserDao.java
│   ├── register.java
│   ├── login.java
│   ├── view.java
│   ├── ExcelImg.java
│   ├── PdfImg.java
│   ├── UploadExcel.java
│   ├── SaveServlet.java
│   ├── SendEmail.java
│
├── webapp/
│   ├── home.jsp
│   ├── vieww.jsp
│   ├── image/
│   ├── tableimage/
│
├── lib/
│   ├── poi.jar
│   ├── gson.jar
│   ├── itextpdf.jar
│   ├── javax.mail.jar
│
└── README.md
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone Project

```bash id="g6zzc1"
git clone https://github.com/your-username/user-management-system.git
```

---

### 2️⃣ Database Setup

```sql id="3fd6b3"
CREATE DATABASE r;

USE r;

CREATE TABLE userDetails (
    uid VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50),
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    gender VARCHAR(10),
    dob VARCHAR(20),
    status VARCHAR(20),
    role VARCHAR(20)
);

CREATE TABLE bankdetails (
    id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(50),
    accno VARCHAR(50),
    bank VARCHAR(100),
    ifsc VARCHAR(20),
    url VARCHAR(255)
);

CREATE TABLE multipleDocs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(50),
    url VARCHAR(255)
);
```

---

### 3️⃣ Configure Database Connection

Update your DB config:

```java id="k7g4kp"
DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/r", "root", "root"
);
```

---

### 4️⃣ Configure Email

Set environment variables:

```bash id="t32f8v"
MAIL_USER=your_email@gmail.com
MAIL_PASS=your_app_password
```

⚠️ Use Gmail App Password (not your real password)

---

### 5️⃣ Deploy

* Import project into Eclipse / IntelliJ
* Add Apache Tomcat server
* Run project

---

## 🌐 Endpoints

| Endpoint                  | Method | Description       |
| ------------------------- | ------ | ----------------- |
| `/register`               | POST   | Add / Update user |
| `/register?action=delete` | GET    | Delete user       |
| `/login`                  | POST   | Login             |
| `/view`                   | GET    | View users        |
| `/excelimg`               | GET    | Download Excel    |
| `/pdfimg`                 | GET    | Download PDF      |
| `/UploadExcel`            | POST   | Upload Excel      |
| `/SaveServlet`            | POST   | Save JSON         |

---

## 📊 UI Features

* Modern Bootstrap UI
* Add user form
* Filter users
* Table view with actions
* Export buttons
* Logout functionality

---

## 🔄 Workflow

1. Register user (with images + bank details)
2. Store data in multiple tables
3. View users in dashboard
4. Filter users
5. Export to Excel / PDF
6. Send email on login

---

## ⚠️ Known Limitations

* Password stored in plain text (needs hashing)
* No authentication security (JWT/session protection)
* No file type validation (security risk)
* Servlet-heavy architecture

---

## 🔮 Future Enhancements

* Spring Boot REST API
* JWT Authentication
* React frontend
* File validation & security
* Pagination & search
* Cloud deployment (AWS)

---

## 👨‍💻 Author

Shivam Jain

---

## ⭐ Notes

This project demonstrates:

* Full CRUD operations
* File handling
* Multi-table database design
* Export functionality
* Real-world backend logic

---
