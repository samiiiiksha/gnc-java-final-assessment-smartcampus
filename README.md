**Problem Statement:**

Create a menu-driven Java application that manages students, courses, and enrollments in an educational system.




The system supports the following operations via an interactive menu:

1\. Add students and courses to the system

2\. Enroll students into one or more courses

3\. View all students and their enrolled courses

4\. Process enrollments using threads to simulate async processing

5\. Handle invalid inputs with custom exceptions





**# Project Structure:** 



**1. Student.java**


- Represents a student entity in the system.

- Stores student details:

&#x09;studentId

&#x09;name

&#x09;studentEmail

- Provides a constructor to initialize student objects



**2. Courses.java**


- Represents a course entity.

- Stores course details:

&#x09;courseId

&#x09;courseName

&#x09;courseFee

- Used to manage available courses in the system



**3. Main.java**


- The main driver class that runs the application.

- Provides a menu-driven interface for user interaction

- Manages:

&#x09;Student list (ArrayList)

&#x09;Course list (ArrayList)

&#x09;Enrollment records (HashMap)

&#x09;Core features:

&#x09;Add Student

&#x09;Add Course

&#x09;Enroll Student in Course

&#x09;View Students

&#x09;View Enrollments

&#x09;Process Enrollments



**# How to run:**



1. Compile all files:

    javac Main.java Student.java Courses.java

2. Run the main file:


    java Main.java



**# MCQs:**



Q1. A developer is implementing student enrollments where each student can enroll in multiple courses. The system should allow quick lookup of a student and their enrolled courses. Which is the most optimal data structure?

\-> HashMap<Student, ArrayList<Course>>



Q2. During enrollment, a student enters a negative course fee, causing incorrect processing. The developer wants to ensure invalid data is handled properly. What is the best approach?

\-> Throw a custom exception like InvalidFeeException



Q3. The system simulates enrollment processing using a thread. However, multiple threads are accessing the same enrollment list, causing inconsistent results. What should be done?

\-> Use synchronized block or thread-safe collection



Q4. A developer wants to enforce a rule that every type of course must implement a method calculateFee() but allow different implementations. Which concept should be used?

\-> Interface

