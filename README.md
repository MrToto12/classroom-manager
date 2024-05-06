# Classroom Manager

Simple classroom manager developed with Java, Java Swing and PostgreSQL.

## Table of Contents

..

## About

This project is classwork for "Programming Paradigms" course at Univerisdad Siglo 21 to demonstrate our knowledge in design patterns and user interface in Java Swing. 

## Goal and Implementation

The project aim is to design and implement a classroom manager where we can create, modify and delete courses, professors and students and manage the courses inscriptions. We had to use different design patterns in the implementation and make a fully functional UI with Java Swing.
This project implements the following design patterns:
* Abstract Factory: Implemented in the professors and students creation through the _PersonaFactory_ class
* Strategy: Implementd in _HasLegajo_ interface to allow future extensions or variations of the way legacy is created for classes that implement this interface.
* DAO: It was implemented in _PersonaDAO_ and _CursoDAO_ interfaces to separate the DB access logic from the rest of the program code. This facilitates maintainability and future changes that might be introduced on the system.
* Singleton: Implemented in different classes like _DbConnect_ or _AlumnoFacotry_ to ensure that these classes have only one instance and provide a global access point to that instance.

## Key Learnings
* Organize the project development using RUP methodology.
* Team work and communication.
* Implement design patterns that match the problem requirements.
* Develop a user-friendly UI using Java Swing.
* Creation of UML diagrams to represent the project structure.
* Database connection using JDBC driver.

## Installation
1. Download this project as zip and extract it.
2. Import it in IntelliJ IDE.
3. Migrate the database file into your PostgreSQL.
4. Connect the database
