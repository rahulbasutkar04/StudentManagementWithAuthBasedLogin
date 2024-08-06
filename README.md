
## Problem Statement

Create a basic Spring Boot application with MVC, Security, and Spring Data support. The application should manage `Student` and `Subject` entities with a many-to-many relationship (a student can enroll in multiple subjects).

### REST API Endpoints
- **Create a Student**: `POST /students`
- **Get List of All Students**: `GET /students`
- **Get List of All Subjects**: `GET /subjects`

### Security
- **Authentication**: JWT-based login
- **Roles**: `student` and `admin`

### Database
- **Type**: Relational DB with JPA
- **In-Memory DB**: H2

### Environment
- **Project**: Maven
- **JDK**: 19+

### Additional Requirements
- Host the project on GitHub.
- Include a README file with instructions for setup, running the application, and testing the APIs.



## Instructions to Run the Application

### Step 1: Run the Spring Boot Application
- Use IntelliJ IDEA to run the Spring Boot application.

### Step 2: Test the APIs with Postman
- Since the frontend is not developed, you will need to use Postman to test the backend APIs.

### Step 3: API Testing Instructions

1. **Register a New User**

 - **Endpoint**: `POST /auth/signUp`
 - **Instruction**: Register a new user.
 - **Request Body**:
   ```json
   {
     "name": "User's Full Name",
     "email": "user@example.com",
     "password": "userpassword",
     "role": "ADMIN or STUDENT"
   }
   ```

2. **Authenticate and Obtain JWT Token**

 - **Endpoint**: `POST /auth/signIn`
 - **Instruction**: Authenticate a user and obtain a JWT token.
 - **Request Body**:
   ```json
   {
     "email": "user@example.com",
     "password": "userpassword"
   }
   ```

3. **Save a New Subject**

 - **Endpoint**: `POST /admin/saveSubject`
 - **Instruction**: Save a new subject (requires admin token).
 - **Request Body**:
   ```json
   {
     "name": "Subject Name"
   }
   ```
 - **Authentication**: Include the JWT token in the `Authorization` header as a Bearer token.

4. **Enroll a Student in a Subject**

 - **Endpoint**: `POST /student/enroll`
 - **Instruction**: Enroll a student in a subject (requires student token). Ensure the user is signed in and the role is STUDENT.
 - **Request Body**:
   ```json
   {
     "subjectId": 1
   }
   ```
 - **Authentication**: Include the JWT token in the `Authorization` header as a Bearer token.

5. **Refresh an Expired JWT Token**

 - **Endpoint**: `POST /auth/refresh`
 - **Instruction**: Refresh an expired JWT token.
 - **Request Body**:
   ```json
   {
     "tokens": "old_jwt_token"
   }
   ```
 - `tokens`: The old JWT token that needs to be refreshed.

6. **Retrieve a List of All Available Subjects**

 - **Endpoint**: `GET /public/subjects`
 - **Instruction**: Retrieve a list of all available subjects (no authentication required).
 - **Response**:
   ```json
   [
     {
       "id": 1,
       "name": "Mathematics"
     },
     {
       "id": 2,
       "name": "History"
     }
   ]
   ```

7. **Retrieve a List of All Student Enrollments**

 - **Endpoint**: `GET /admin/enrollments`
 - **Instruction**: Retrieve a list of all student enrollments (requires admin token).
 - **Response**:
   ```json
   [
     {
       "studentId": 1,
       "studentName": "John Doe",
       "enrolledSubjects": [
         {
           "subjectId": 1,
           "subjectName": "Mathematics"
         }
       ]
     },
     {
       "studentId": 2,
       "studentName": "Jane Smith",
       "enrolledSubjects": [
         {
           "subjectId": 2,
           "subjectName": "History"
         }
       ]
     }
   ]
   ```
 - **Authentication**: Include the JWT token in the `Authorization` header as a Bearer token.



### NOTE: I have attache api documentation for clear unsertanding in ` F:\JWTAuth\apidocumentation`
# Used Technologies & Tools
### JAVA 21
### Spring BOOT version 3.x.x
### JWT Authentication
### IntellijeIDE
### POSTMAN API
### H2 DATABASE(InMemeory)

