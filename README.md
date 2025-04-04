# COSC 223 GROUP WORK ASSIGNMENT  

## Student Management System

Project for **COSC 223 (OOP)** course and serves as a **Student Management System** with two implementations:

1. **File-based Storage System**  
2. **Database-based Storage System**  (Yet to fully implement this)

---
## Project Description
The system manages students, lecturers, courses, and programmes. It provides functionality to:
- Assign lecturers to courses
- Enroll students in programmes and courses
- Assign student scores
- Retrieve student details

### Key Features
#### ✅ Person Hierarchy:
- `Person` (Superclass)
  - `Student` (Subclass)
  - `Lecturer` (Subclass)

#### ✅ Student Management:
- Students can register for **only one programme**.
- Students can enroll in **a maximum of three courses**.

#### ✅ Lecturer Management:
- Each lecturer can be assigned **a maximum of two courses**.
- No course sharing between lecturers.

#### ✅ Course & Programme Management:
- Courses are attached to specific programmes.
- Students can be enrolled in courses within their programme.

#### ✅ Data Storage:
- **File-based System**: Uses multiple files (`student-data.txt`, `lecturer-data.txt`, etc.) to store data.
- **Database-based System**: Uses **MySQL** for data persistence.

#### ✅ Results Management:
- Assign and update student scores.
- Retrieve student results from file/database.
- Display students registered in a unit/course.
- Find lecturers assigned to a particular course.

---
## System Functionalities
- 📌 Assign lecturers to courses and enforce the **2-course limit** per lecturer.  
- 📌 Attach courses to programmes and manage course assignments.  
- 📌 Enroll students in programmes and allow them to register for courses.  
- 📌 Assign scores to students and store the results in either **files or a database**.  
- 📌 Retrieve student results and update where necessary.  
- 📌 Search for students and list courses they are registered in.  
- 📌 Ensure that no lecturer shares the same course with another lecturer.  
- 📌 Find all students registered in a particular programme or unit.  

---
## Technologies Used
- **Java** (Core logic)
- **Swing** (GUI for user interaction)
- **File Handling** (Text-based data storage)
- **MySQL** (Database storage option)
- **Object-Oriented Programming (OOP)**

---
## Setup Instructions
### Running the File-Based System
1. Clone this repository:
   ```bash
   git clone https://github.com/OsbornNyakaru/student_management_system.git
   ```
2. Open the project in **your preferred Java IDE** (e.g., IntelliJ, VS Code, Eclipse, or NetBeans).
3. Run the `Main.java` class to start the system.

### Running the Database-Based System
1. Install **MySQL** and create a database for the project.
2. Configure database connection settings in the `DatabaseConfig.java` file.
3. Run the SQL script provided in `database_setup.sql` to create tables.
4. Run the `Main.java` class to launch the application.

---
## Future Enhancements
- 🚀 Implement **REST APIs** to extend the system for web and mobile use.  
- 🚀 Add **role-based authentication** (admin, lecturer, student).  
- 🚀 Introduce **real-time notifications** for results updates.  
- 🚀 Implement a **dashboard with analytics and reporting**.  

---
## Author
👨‍💻 **OSBORN NYAKARU** and the COSC 223 group as collaborators
📧 Email: [osbornnyakaru44@gmail.com](mailto:osbornnyakaru44@gmail.com)  
🔗 GitHub: [https://github.com/OsbornNyakaru](https://github.com/OsbornNyakaru)  


---

# Installation & Execution Guide

## Prerequisites
- Java 8 or later
- Maven

## Installation Steps
1. Download and extract the project files.
2. Double-click `install.bat` (Windows) or run the equivalent shell script for macOS/Linux.
3. The installer will:
   - Check if Java and Maven are installed
   - Build the project
   - Create a desktop shortcut automatically

## Running the Application
- **Windows**: Double-click the "Student Management System" shortcut on your desktop.
- **Manual Execution**:
  ```bash
  cd project_directory
  java -jar StudentManagement.jar
  ```

## Features
- 📌 Student registration
- 📌 Course management
- 📌 Score assignment
- 📌 Result retrieval
- 📌 Admin and Student interfaces

## Troubleshooting
1. Ensure Java 8 or later is installed.
2. Verify Maven installation.
3. Confirm that both are properly added to your system's PATH.
4. Retry the installation script.

## Support
For any issues or questions, please create an issue in the GitHub repository.

