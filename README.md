# Classroom Manager
Simple classroom manager developed with _Java_, _Java Swing_ and _PostgreSQL_.

## Table of Contents
1. [About](#about)
2. [Project Goal and Implementation](#project-goal-and-design-patterns-implemented)
3. [Key Learnings](#key-learnings)
4. [Installation](#installation)
5. [Credits](#credits)

## About
This project is classwork for **"Programming Paradigms"** course at Univerisdad Siglo 21 to demonstrate our knowledge in design patterns and user interface in Java Swing. 

## Project Goal and Design Patterns Implemented
The project aim is to design and implement a classroom manager where we can create, modify and delete courses, professors and students and manage the courses inscriptions. We had to use different **design patterns** in the implementation and make a fully functional **UI** with Java Swing.
This project implements the following design patterns:
* Abstract Factory: Implemented in the professors and students creation through the _PersonaFactory_ class
* Strategy: Implementd in _HasLegajo_ interface to allow future extensions or variations of the way legacy is created for classes that implement this interface.
* DAO: Implemented in _PersonaDAO_ and _CursoDAO_ interfaces to separate the DB access logic from the rest of the program code. This facilitates **maintainability** and future changes that might be introduced on the system.
* Singleton: Implemented in different classes like _DbConnect_ or _AlumnoFacotry_ to ensure that these classes have only one instance and provide a global access point to that instance.

For further understanding on how we implemented the design patterns mentioned above, along with UML diagrams and examples, you can download the PDF document
[Download PDF with Design Patterns Explanation](https://drive.google.com/file/d/13nKLwkKsUrTHJKEi6KVOIUl6LfjNab0y/view?usp=sharing)


## Key Learnings
* Organize the project development using **RUP methodology**.
* Team work and communication.
* Implement design patterns that match the problem requirements.
* Develop a user-friendly UI using **Java Swing**.
* Creation of **UML diagrams** to represent the project structure.
* Database connection using **JDBC driver**.

## Installation
1. Download this project as zip and extract it.
2. Import it in your preferred IDE.
3. Migrate the [database file](DBFacultad.sql) into your **PostgreSQL** manager.
4. Verify you have already installed `org.postgresql.Driver` and it's been added to project external libraries. In case you don't download it from the [official page](https://jdbc.postgresql.org/download/)
5. Connect your database by modifying the following lines in
   `src/Db/DbConnect/DbConnect.java`
   ```java
   private static final String DB_USER = "yourDbUser";
   private static final String DB_PASS = "yourDbPass";     
   private static final String DB_URL = "yourLocalDbURL";

6. Run the program.

## Credits
Authors:
* [Tomas Temporelli](github.com/tototempo)
* [Valentino Mezzavilla](github.com/valenmezza)
* Alejo Yammal
