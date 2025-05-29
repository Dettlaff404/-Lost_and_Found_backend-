# 🔍 Lost and Found System - 2025

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Authentication-purple?style=for-the-badge&logo=jsonwebtokens)

**A secure and robust backend system for managing lost and found items**

*Built with Spring Boot | Powered by modern Java technologies*

</div>

---

## ✨ Features

<table>
<tr>
<td width="50%">

### 🔐 **Security & Authentication**
- JWT-based authentication
- Role-based access control (Admin, Staff, User)
- Secure endpoints with Spring Security
- Auto-generated admin user

</td>
<td width="50%">

### 📊 **Data Management**
- Full CRUD operations for Users, Items, and Requests
- MySQL database integration
- Custom exception handling
- Comprehensive logging system

</td>
</tr>
<tr>
<td width="50%">

### 🌐 **API & Integration**
- RESTful API design
- CORS configuration for frontend
- Health check endpoints
- Structured response format

</td>
<td width="50%">

### 🛠️ **Development**
- Clean architecture
- ModelMapper for DTOs
- Lombok for cleaner code
- Comprehensive testing support

</td>
</tr>
</table>

---

## 🚀 Tech Stack

<div align="center">

| Technology | Version | Purpose |
|------------|---------|---------|
| ☕ **Java** | 21 | Core Language |
| 🍃 **Spring Boot** | 3.4.5 | Framework |
| 🗃️ **Spring Data JPA** | Latest | Data Access |
| 🔒 **Spring Security** | Latest | Security Layer |
| 🐬 **MySQL** | Latest | Database |
| 🎫 **JWT** | Latest | Authentication |
| 🧹 **Lombok** | Latest | Code Simplification |
| 🗺️ **ModelMapper** | Latest | Object Mapping |
| 📋 **Log4j2** | Latest | Logging |

</div>

---

## 🏁 Getting Started

### 📋 Prerequisites

Before you begin, ensure you have:

```bash
✅ Java 21 or higher
✅ Maven 3.9+
✅ MySQL Server
✅ Your favorite IDE (IntelliJ IDEA recommended)
```

### ⚙️ Configuration

#### 1. 🗄️ Database Setup
```sql
-- MySQL will auto-create the database if it doesn't exist
-- Just ensure MySQL server is running and accessible
```

#### 2. 📝 Application Properties
Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/lostandfound
spring.datasource.username=your_username
spring.datasource.password=your_password

# Server Configuration
server.port=8085
server.servlet.context-path=/lostandfound

# JWT Configuration
jwt.signing.key=your_jwt_signing_key
```

#### 3. 👨‍💼 Default Admin User
The system automatically creates an admin user on first startup!

### 🔨 Build & Run

```bash
# 📦 Build the project
./mvnw clean install

# 🚀 Run the application
./mvnw spring-boot:run
```

<div align="center">

🎉 **Your backend will be available at:** `http://localhost:8085/lostandfound`

</div>

---

## 📚 API Documentation

### 🔐 Authentication Endpoints

| Method | Endpoint | Description | 🔒 Auth Required |
|--------|----------|-------------|------------------|
| `POST` | `/api/v1/auth/signin` | User login | ❌ |
| `POST` | `/api/v1/auth/signup` | User registration | ❌ |

### 👥 User Management

| Method | Endpoint | Description | 🔒 Auth Required |
|--------|----------|-------------|------------------|
| `POST` | `/api/v1/users` | Create new user | ✅ |
| `GET` | `/api/v1/users?userId={id}` | Get user by ID | ✅ |
| `GET` | `/api/v1/users/getuserbyemail?email={email}` | Get user by email | ✅ |
| `GET` | `/api/v1/users/getallusers` | List all users | ✅ |
| `PATCH` | `/api/v1/users?userId={id}` | Update user | ✅ |
| `DELETE` | `/api/v1/users?userId={id}` | Delete user | ✅ |

### 📦 Item Management

| Method | Endpoint | Description | 🔒 Auth Required |
|--------|----------|-------------|------------------|
| `GET` | `/api/v1/items?itemId={id}` | Get item by ID | ✅ |
| `GET` | `/api/v1/items/getallitems` | List all items | ✅ |
| `PATCH` | `/api/v1/items?itemId={id}` | Update item | ✅ |
| `DELETE` | `/api/v1/items?itemId={id}` | Delete item | ✅ |

### 📝 Request Management

| Method | Endpoint | Description | 🔒 Auth Required |
|--------|----------|-------------|------------------|
| `POST` | `/api/v1/requests` | Create new request | ✅ |
| `GET` | `/api/v1/requests?requestId={id}` | Get request by ID | ✅ |
| `GET` | `/api/v1/requests/getallrequests` | List all requests | ✅ |
| `PATCH` | `/api/v1/requests?requestId={id}` | Update request | ✅ |
| `DELETE` | `/api/v1/requests?requestId={id}` | Delete request | ✅ |

### 🏥 Health Check

| Method | Endpoint | Description | 🔒 Auth Required |
|--------|----------|-------------|------------------|
| `GET` | `/api/v1/health` | System health status | ❌ |

---

## 👨‍💼 Default Admin Credentials

<div align="center">

### 🚨 **Important: Change these credentials in production!**

| Field | Value |
|-------|-------|
| 📧 **Email** | `admin@mail.com` |
| 🔐 **Password** | `Admin2025@Lost&found` |
| 👑 **Role** | `ADMIN` |

</div>

---

## 🏗️ Project Architecture

```
📁 src/
├── 📁 main/
│   ├── 📁 java/lk/ijse/cmjd108/LostandFoundSys_2025/
│   │   ├── 🎮 controller/       # REST Controllers
│   │   ├── 🔧 service/          # Business Logic
│   │   ├── 💾 dao/              # Data Access Layer
│   │   ├── 📋 entities/         # JPA Entities
│   │   ├── 📦 dto/              # Data Transfer Objects
│   │   ├── ⚠️ exception/        # Custom Exceptions
│   │   ├── 🔐 security/         # Security Configuration
│   │   ├── 🛠️ util/             # Utility Classes
│   │   └── ⚙️ config/           # Application Configuration
│   └── 📁 resources/
│       ├── 📄 application.properties
│       ├── 🎨 banner.txt
│       ├── 📋 log4j2.xml
│       └── 📁 logs/
└── 📁 test/
    └── 📁 java/                 # Test Classes
```

---

## 📊 Logging Configuration

<div align="center">

### 📝 Log Levels & Outputs

| Level | Description | Output |
|-------|-------------|---------|
| 🔴 **ERROR** | Critical errors | Console + File |
| 🟡 **WARN** | Warning messages | Console + File |
| 🔵 **INFO** | General information | Console + File |
| 🟣 **DEBUG** | Debug information | File only |

**Log File Location:** `src/main/resources/logs/lostandfound.log`

</div>

---

## 🌐 CORS Configuration

The application is configured to accept requests from:

```javascript
🌍 Allowed Origins: http://localhost:3000
📝 Allowed Methods: GET, POST, PUT, PATCH, DELETE, OPTIONS
🔧 Allowed Headers: Authorization, Content-Type, Accept
```

Perfect for React/Angular/Vue.js frontends running on port 3000!

---

## 🛡️ Security Features

<div align="center">

| Feature | Implementation | Status |
|---------|----------------|---------|
| 🔐 **JWT Authentication** | Bearer Token | ✅ Active |
| 👥 **Role-Based Access** | ADMIN, STAFF, USER | ✅ Active |
| 🔒 **Password Encryption** | BCrypt | ✅ Active |
| 🛡️ **CORS Protection** | Configured Origins | ✅ Active |
| 🚫 **SQL Injection Prevention** | JPA/Hibernate | ✅ Active |

</div>

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. 🍴 Fork the repository
2. 🌱 Create a feature branch (`git checkout -b feature/amazing-feature`)
3. 💾 Commit your changes (`git commit -m 'Add amazing feature'`)
4. 📤 Push to the branch (`git push origin feature/amazing-feature`)
5. 🔃 Open a Pull Request

---

## 📋 Future Enhancements

- [ ] 📱 Mobile API optimizations
- [ ] 🔔 Real-time notifications
- [ ] 📊 Analytics dashboard
- [ ] 🗂️ File upload for item images
- [ ] 🔍 Advanced search filters
- [ ] 📧 Email notifications
- [ ] 🌍 Multi-language support

---

## 📄 License

<div align="center">

📚 **This project is for educational purposes**

Built with ❤️

---

⭐ **Found this helpful? Give me a star!** ⭐

</div>