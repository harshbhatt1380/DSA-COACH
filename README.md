# 🧠 DSA Coach

A secure **REST API for managing Data Structures & Algorithms questions and tracking user progress**.

DSA Coach allows users to create accounts, authenticate securely using **JWT**, browse DSA questions by difficulty, and maintain their personal solved/unsolved progress.

The project was built to practice and demonstrate backend development concepts including **REST API design, Spring Boot, Spring Security, JWT authentication, PostgreSQL, JPA/Hibernate, validation, exception handling, and role-based authorization**.

> **Note:** DSA Coach is currently a backend-only project. There is no frontend client at this stage.

---

## 🌐 Project Links

* **GitHub Repository:** [DSA-COACH](https://github.com/harshbhatt1380/DSA-COACH)
* **API Base URL:** `http://localhost:8080`

---

## ✨ Features

### 👤 User Management

* User registration with email, username, and password
* Login using username or email
* Secure password hashing using **BCrypt**
* JWT-based authentication
* Retrieve user information by username
* Update authenticated user's username
* Update authenticated user's email
* Delete authenticated user's account
* Unique username and email enforcement

### 🔐 Authentication & Authorization

* Stateless authentication using **JSON Web Tokens**
* JWT validation through a custom security filter
* Protected API endpoints
* Role-based authorization using `ROLE_USER` and `ROLE_ADMIN`
* Public access restricted to registration and login endpoints
* Administrative operations protected using Spring Security

### 📚 Question Management

* Create DSA questions
* Retrieve all questions
* Search questions by ID
* Search questions by title
* Filter questions by difficulty
* Update question difficulty
* Update question title
* Delete questions
* Prevent duplicate question titles

Supported difficulty levels:

* `EASY`
* `MEDIUM`
* `HARD`

### 📈 Progress Tracking

Users can maintain their progress for individual DSA questions.

* Mark questions as solved or unsolved
* Retrieve progress for a specific question
* Retrieve all progress records belonging to the authenticated user
* Automatically update an existing progress record instead of creating duplicates
* Database-level uniqueness constraint prevents duplicate user/question progress records

### ⚠️ Exception Handling & Validation

The application includes centralized exception handling for common API errors:

* Invalid credentials
* User not found
* Username already taken
* Email already taken
* Question already exists
* Question not found
* Duplicate question title
* Request validation failures

Errors are returned through a consistent response structure instead of exposing raw application exceptions.

---

## 🛠️ Tech Stack

| Technology          | Purpose                        |
| ------------------- | ------------------------------ |
| **Java 21**         | Programming language           |
| **Spring Boot 4**   | Backend framework              |
| **Spring Web MVC**  | REST API development           |
| **Spring Security** | Authentication & authorization |
| **JWT (JJWT)**      | Token-based authentication     |
| **Spring Data JPA** | Database persistence           |
| **Hibernate**       | ORM                            |
| **PostgreSQL**      | Relational database            |
| **Maven**           | Dependency management & build  |
| **Bean Validation** | Request validation             |

---

## 🏗️ Architecture

DSA Coach follows a layered backend architecture:

```text
                    ┌─────────────────────┐
                    │      Client         │
                    │ Postman / Frontend  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Controllers      │
                    │   REST Endpoints    │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Services       │
                    │ Business Logic      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Repositories     │
                    │   Spring Data JPA   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     PostgreSQL      │
                    │      Database       │
                    └─────────────────────┘
```

Authentication is handled separately through Spring Security and a custom JWT authentication filter:

```text
Request
   │
   ▼
Authorization: Bearer <JWT>
   │
   ▼
JwtAuthFilter
   │
   ├── Extract JWT
   │
   ├── Extract username
   │
   ├── Load user
   │
   ├── Validate token
   │
   ▼
SecurityContext
   │
   ▼
Controller
```

---

## 📂 Project Structure

```text
src/
└── main/
    ├── java/com/example/dsacoach/
    │
    ├── controller/
    │   ├── UserController.java
    │   ├── QuestionController.java
    │   └── ProgressController.java
    │
    ├── service/
    │   ├── UserService.java
    │   ├── UserDetailService.java
    │   ├── QuestionService.java
    │   ├── ProgressService.java
    │   └── JwtService.java
    │
    ├── repository/
    │   ├── UserRepository.java
    │   ├── QuestionRepository.java
    │   └── ProgressRepository.java
    │
    ├── entity/
    │   ├── User.java
    │   ├── Question.java
    │   └── Progress.java
    │
    ├── DTO/
    │   ├── RequestDTO/
    │   └── ResponseDTO/
    │
    ├── Security/
    │   ├── SecurityConfig.java
    │   ├── JwtAuthFilter.java
    │   └── UserDetail.java
    │
    ├── MyExceptions/
    │   ├── GlobalExceptionHandler.java
    │   └── Custom Exceptions
    │
    └── enumFolder/
        ├── Difficulty.java
        └── Role.java
```

---

# 🔐 Authentication Flow

DSA Coach uses JWT-based stateless authentication.

### 1. Register

A user creates an account using:

```http
POST /users/register
```

The password is never stored directly. It is encoded using BCrypt before being persisted.

### 2. Login

The user can authenticate using either their username or email:

```http
POST /users/login
```

After successful authentication, the server generates a JWT containing the authenticated username.

### 3. Send JWT With Requests

Protected endpoints require the token in the request header:

```http
Authorization: Bearer <your-jwt-token>
```

### 4. JWT Validation

The custom `JwtAuthFilter`:

1. Reads the `Authorization` header.
2. Extracts the JWT.
3. Extracts the username from the token.
4. Loads the corresponding user.
5. Validates the token and expiration.
6. Places the authenticated user into Spring Security's `SecurityContext`.

---

# 👥 Roles & Authorization

The application currently supports two roles:

```text
ROLE_USER
ROLE_ADMIN
```

Newly registered users are assigned:

```text
ROLE_USER
```

Administrative question-management operations require:

```text
ROLE_ADMIN
```

### Admin-only operations

```http
POST   /questions/add
PUT    /questions/changeDifficulty
PUT    /questions/changeTitle
DELETE /questions/delete
```

Regular authenticated users can access protected question-reading and progress-related operations.

---

# 📡 API Documentation

## 👤 User Endpoints

### Register User

```http
POST /users/register
```

**Request Body**

```json
{
  "username": "harsh",
  "email": "harsh@example.com",
  "password": "password123"
}
```

**Response**

```json
{
  "success": true,
  "message": "User creation successful",
  "username": "harsh",
  "email": "harsh@example.com",
  "role": "ROLE_USER"
}
```

---

### Login

```http
POST /users/login
```

**Request Body**

```json
{
  "username": "harsh",
  "email": "",
  "password": "password123"
}
```

The API accepts either username or email for authentication.

**Response**

```json
{
  "token": "<JWT_TOKEN>"
}
```

---

### Get User By Username

```http
GET /users/getByUsername?user=harsh
```

🔒 Authentication required.

---

### Update Username

```http
PUT /users/update/username?newUsername=newName
```

🔒 Authentication required.

The authenticated user is identified from the JWT rather than accepting a user ID from the client.

---

### Update Email

```http
PUT /users/update/email
```

**Request Body**

```json
{
  "email": "newemail@example.com"
}
```

🔒 Authentication required.

---

### Delete Account

```http
DELETE /users/deleteUser
```

🔒 Authentication required.

The account belonging to the authenticated user is deleted.

---

# 📚 Question Endpoints

## Get All Questions

```http
GET /questions/all
```

🔒 Authentication required.

**Example Response**

```json
[
  {
    "title": "Two Sum",
    "difficulty": "EASY"
  },
  {
    "title": "Binary Tree Level Order Traversal",
    "difficulty": "MEDIUM"
  }
]
```

---

## Get Question By ID

```http
GET /questions/getById?id=1
```

🔒 Authentication required.

---

## Get Question By Title

```http
GET /questions/getByTitle?title=Two%20Sum
```

🔒 Authentication required.

---

## Get Questions By Difficulty

```http
GET /questions/getByDifficulty?difficulty=EASY
```

Supported values:

```text
EASY
MEDIUM
HARD
```

🔒 Authentication required.

---

## Add Question

```http
POST /questions/add
```

🔐 Admin only.

**Request Body**

```json
{
  "title": "Two Sum",
  "difficulty": "EASY"
}
```

**Response**

```json
{
  "success": true,
  "message": "Question saved successfully",
  "title": "Two Sum",
  "difficulty": "EASY"
}
```

---

## Change Question Difficulty

```http
PUT /questions/changeDifficulty?id=1&difficulty=HARD
```

🔐 Admin only.

---

## Change Question Title

```http
PUT /questions/changeTitle?id=1&title=New%20Question%20Title
```

🔐 Admin only.

---

## Delete Question

```http
DELETE /questions/delete?id=1&title=Two%20Sum
```

🔐 Admin only.

The endpoint requires both the question ID and title before deletion.

---

# 📈 Progress Endpoints

Progress is associated with both a **user** and a **question**.

This means two different users can independently maintain their own solved/unsolved state for the same DSA question.

---

## Add / Update Progress

```http
POST /progress/add
```

🔒 Authentication required.

**Request Body**

```json
{
  "questionTitle": "Two Sum",
  "solved": true
}
```

If no progress record exists, a new record is created.

If a progress record already exists, its solved status is updated.

---

## Get Progress For A Question

```http
GET /progress/questionProgress?qid=1
```

🔒 Authentication required.

**Example Response**

```json
{
  "success": true,
  "message": "Progress of user for provided question id fetched successfully",
  "username": "harsh",
  "questionTitle": "Two Sum",
  "solved": true
}
```

If no progress record exists, the API treats the question as unsolved.

---

## Get All User Progress

```http
GET /progress/allQuestionProgress
```

🔒 Authentication required.

**Example Response**

```json
[
  {
    "questionTitle": "Two Sum",
    "solved": true
  },
  {
    "questionTitle": "Binary Search",
    "solved": false
  }
]
```

---

# 🗄️ Data Model

The application uses three primary entities:

```text
┌───────────────┐
│     User      │
├───────────────┤
│ id            │
│ username      │
│ email         │
│ password      │
│ role          │
└───────┬───────┘
        │
        │ 1
        │
        │ *
┌───────▼───────┐       ┌────────────────┐
│   Progress    │       │    Question    │
├───────────────┤       ├────────────────┤
│ id            │       │ id             │
│ user_id       │──────▶│ title          │
│ question_id   │       │ difficulty     │
│ solved        │       └────────────────┘
└───────────────┘
```

### User

Stores authentication and account information.

### Question

Stores the DSA question title and difficulty.

### Progress

Connects a user to a question and stores whether the user has solved it.

A database-level unique constraint is applied to:

```text
(user_id, question_id)
```

This prevents duplicate progress records for the same user/question combination.

---

# 🔒 Security

Several security practices are implemented in the project:

### Password Hashing

Passwords are encoded using:

```text
BCryptPasswordEncoder
```

Plain-text passwords are never intentionally stored in the database.

### JWT Authentication

Authentication is stateless and handled using signed JWTs.

Tokens currently expire after:

```text
1 hour
```

### Protected Endpoints

Spring Security protects application endpoints by default, while registration and login are explicitly permitted.

### Role-Based Access Control

Question administration endpoints require the `ADMIN` role.

### Environment Variables

Database credentials and the JWT signing secret are loaded through environment variables rather than being hard-coded into `application.properties`.

Required environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
```

---

# ⚙️ Getting Started

## Prerequisites

Make sure you have the following installed:

* Java 21+
* Maven
* PostgreSQL
* Git

---

## 1. Clone the Repository

```bash
git clone https://github.com/harshbhatt1380/DSA-COACH.git
cd DSA-COACH
```

---

## 2. Configure Environment Variables

Create a `.env` file or configure the variables in your environment:

```env
DB_URL=jdbc:postgresql://localhost:5432/dsacoach
DB_USERNAME=postgres
DB_PASSWORD=your_database_password
JWT_SECRET=your_long_secure_jwt_secret
```

> Do not commit real database credentials or JWT secrets to GitHub.

---

## 3. Configure PostgreSQL

Create a PostgreSQL database:

```sql
CREATE DATABASE dsacoach;
```

The application uses Hibernate/JPA to manage the database schema.

The current configuration uses:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

## 4. Run the Application

Using Maven:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Or build the application:

```bash
./mvnw clean package
```

Then run:

```bash
java -jar target/dsacoach-0.0.1-SNAPSHOT.jar
```

The application starts on:

```text
http://localhost:8080
```

---

# 🧪 Testing The API

You can test the API using tools such as:

* Postman
* Insomnia
* cURL
* Any frontend HTTP client

A typical workflow is:

```text
1. Register
      ↓
2. Login
      ↓
3. Copy JWT
      ↓
4. Add JWT to Authorization header
      ↓
5. Access protected endpoints
      ↓
6. Create/update progress
```

For protected requests:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

# 🧩 Example API Workflow

### Step 1 — Register

```http
POST /users/register
```

```json
{
  "username": "developer",
  "email": "developer@example.com",
  "password": "securePassword"
}
```

### Step 2 — Login

```http
POST /users/login
```

```json
{
  "username": "developer",
  "email": "",
  "password": "securePassword"
}
```

### Step 3 — Receive JWT

```json
{
  "token": "<JWT_TOKEN>"
}
```

### Step 4 — Authenticate

Add the token to subsequent requests:

```http
Authorization: Bearer <JWT_TOKEN>
```

### Step 5 — Browse Questions

```http
GET /questions/all
```

### Step 6 — Track Progress

```http
POST /progress/add
```

```json
{
  "questionTitle": "Two Sum",
  "solved": true
}
```

---

# 🧠 What This Project Demonstrates

DSA Coach was built as a practical backend project to apply core concepts of modern Java backend development.

### Backend Development

* RESTful API development
* Layered architecture
* Dependency injection
* Service/repository separation
* DTO-based request and response handling

### Database

* Relational data modeling
* PostgreSQL
* JPA/Hibernate
* Entity relationships
* Unique constraints
* Repository-based data access

### Security

* Spring Security
* JWT authentication
* BCrypt password hashing
* Role-based authorization
* Security context
* Custom authentication filter

### API Design

* HTTP methods and status codes
* Request validation
* Query parameters
* JSON request/response bodies
* Centralized exception handling
* Consistent API responses

---

# 🚧 Current Limitations

DSA Coach is intentionally focused on backend development, so several features are not currently included:

* No frontend UI
* No question descriptions or solution explanations
* No coding/submission engine
* No automated DSA problem judging
* No pagination for question lists
* No refresh-token mechanism
* No password reset/email verification flow
* No API documentation through Swagger/OpenAPI yet

These provide potential directions for future development.

---

# 🔮 Future Improvements

Possible future improvements include:

* [ ] Build a frontend client
* [ ] Add Swagger/OpenAPI documentation
* [ ] Add pagination and sorting
* [ ] Add question categories/topics
* [ ] Add question descriptions and constraints
* [ ] Add solution/editorial support
* [ ] Add user progress statistics
* [ ] Add a dashboard for solved questions
* [ ] Add refresh tokens
* [ ] Add email verification
* [ ] Add password reset functionality
* [ ] Add automated integration tests
* [ ] Add Docker support
* [ ] Add CI/CD pipeline
* [ ] Add production monitoring and logging

---

# 📌 API Summary

| Method   | Endpoint                        | Authentication | Access        |
| -------- | ------------------------------- | -------------- | ------------- |
| `POST`   | `/users/register`               | ❌              | Public        |
| `POST`   | `/users/login`                  | ❌              | Public        |
| `GET`    | `/users/getByUsername`          | 🔒             | Authenticated |
| `PUT`    | `/users/update/username`        | 🔒             | Authenticated |
| `PUT`    | `/users/update/email`           | 🔒             | Authenticated |
| `DELETE` | `/users/deleteUser`             | 🔒             | Authenticated |
| `GET`    | `/questions/all`                | 🔒             | Authenticated |
| `GET`    | `/questions/getById`            | 🔒             | Authenticated |
| `GET`    | `/questions/getByTitle`         | 🔒             | Authenticated |
| `GET`    | `/questions/getByDifficulty`    | 🔒             | Authenticated |
| `POST`   | `/questions/add`                | 🔐             | Admin         |
| `PUT`    | `/questions/changeDifficulty`   | 🔐             | Admin         |
| `PUT`    | `/questions/changeTitle`        | 🔐             | Admin         |
| `DELETE` | `/questions/delete`             | 🔐             | Admin         |
| `POST`   | `/progress/add`                 | 🔒             | Authenticated |
| `GET`    | `/progress/questionProgress`    | 🔒             | Authenticated |
| `GET`    | `/progress/allQuestionProgress` | 🔒             | Authenticated |

**Legend**

* ❌ Public endpoint
* 🔒 Authentication required
* 🔐 Admin role required

---

# 📜 License

This project is currently intended as a personal learning and portfolio project.

---

## 👨‍💻 Author

**Harsh Bhatt**

Built with Java, Spring Boot, Spring Security, PostgreSQL, JPA/Hibernate, and JWT.

If you found the project interesting, feel free to explore the repository and the implementation.
