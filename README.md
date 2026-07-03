♻️ Smart E-Waste Collection and Management System (Backend)

## 📌 Overview

The **Smart E-Waste Management System** is a Spring Boot backend application developed as part of the **Infosys Springboard 6.0 Internship**.

The system digitizes the complete e-waste pickup process by allowing users to submit pickup requests, administrators to manage assignments and scheduling, and collectors to update pickup status.

The project demonstrates secure backend development using **Spring Boot, Spring Security, JWT Authentication, REST APIs, and MySQL**.

⚠️ **Note**: The frontend (UI) was developed separately by another team and is not included in this repository.

---

## 🚀 Features

- JWT Authentication & Spring Security
- Role-Based Access Control (User, Admin, Collector)
- User Registration & Login
- E-Waste Pickup Request Management
- Collector Assignment & Pickup Scheduling
- Request Status Tracking
- Email Notifications using JavaMail (Mailtrap)
- Image Upload using MultipartFile
- Request History (Audit Trail)
- Analytics APIs
- REST API Testing with Postman

---

## 🏗️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- JavaMail
- Postman
- Git & GitHub

---
## 🗄 Database

The application uses **5 relational tables**:

- Users
- Ewaste Requests
- Collector Assignments
- Pickup Schedule
- Request Status History

---
## 🔗 REST APIs

Implemented **18 REST APIs** covering:

- Authentication (Register & Login)
- User Operations
- Admin Operations
- Collector Operations
- Pickup Scheduling
- Status Updates
- Analytics
- Request Tracking

---
## ⚙️ Setup & Run

### 1. Clone the repository

```bash
git clone https://github.com/PadmaGadi17/Smart-E-Waste-Management-System.git
cd Smart-E-Waste-Management-System
```

### 2. Configure Database

* Create a MySQL database
* Update `application.properties` with your DB credentials

### 3. Run the application

```bash
mvn spring-boot:run
```

Server runs at:

```
http://localhost:8080
```

---
## 🧪 API Testing

The APIs were tested using:

- Postman
- MySQL Workbench

Verified:

- Authentication
- CRUD Operations
- Role-Based Authorization
- Email Notifications
- Database Integrity

---

## 📚 Learning Outcomes

- Spring Boot REST API Development
- JWT Authentication
- Spring Security
- Spring Data JPA
- MySQL Database Design
- JavaMail Integration
- File Upload Handling
- Git & GitHub
- API Testing using Postman

---

## 👩‍💻 Author

**Padma Gadi**
🔗 LinkedIn: https://www.linkedin.com/in/padma-gadi

---

## ⭐ Note
## 📌 Internship

Developed as part of the **Infosys Springboard 6.0 Internship**, demonstrating secure REST API development, role-based access control, database management, and real-world backend application development.
## ⭐ If you found this project helpful, please consider giving it a Star.
