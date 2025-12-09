============================================================================================================================================================
                                                            ♻️  ECOFIND - RECYCLING CENTRE LOCATOR
============================================================================================================================================================

    PROJECT:        EcoFind
    COURSE:         CS 213 Object Oriented Programming
    CONTEXT:        Environmental Sustainability in African Communities
    LANGUAGE:       Java
    VERSION:        1.0

=============================================================================================================================================================
                                                                        TABLE OF CONTENTS
=============================================================================================================================================================

    1. Project Overview
    2. Features & Capabilities
    3. Project Directory Structure
    4. Prerequisites
    5. How to Run (IDE & Command Line)
    6. Design & OOP Principles

=============================================================================================================================================================
1. PROJECT OVERVIEW
=============================================================================================================================================================
EcoFind is a Java-based application designed to promote environmental sustainability. It helps users efficiently locate nearby recycling centres 
based on their current location and the specific materials they wish to recycle.

The system models real-world entities (recycling centres, locations, materials) and separates data (model), logic (service), and interaction (main) to ensure 
clean code architecture.

Our Core Goal is to simplify the process of finding the nearest recycling facility that accepts specific waste types like Plastic, Glass, or Metal waste.

=============================================================================================================================================================
2. FEATURES & CAPABILITIES
=============================================================================================================================================================
a. Distance Calculatio uses Euclidean geometry(1) to compute the precise distance between the user and 
  any centre.

b. Material Filtering filters available centres to show only those that accept the user's specific waste (e.g., paper, metal, plastic).

c. Proximity Sorting automatically sorts results from "Nearest" to "Farthest".

d. Nearest Centre Finder instantly identifies the single closest facility to the user.

e. Eco-Centre Support includes specialized handling for "Eco Recycling Centres" which have stricter material acceptance rules.

f. Data Integrity in the code ensures that there is robust validation to help prevent errors from invalid or missing data.

============================================================================================================================================================
3. PROJECT DIRECTORY STRUCTURE
============================================================================================================================================================
The project follows a standard MVC-style(2) package structure:

ECOFIND/
├── out/                        (Compiled .class files / build artifact)
├── src/                        (Source code)
│   ├── model/                  (Data Entities)
│   │   ├── Location.java
│   │   ├── Material.java
│   │   ├── RecyclingCentre.java
│   │   └── EcoRecyclingCentre.java
│   │
│   ├── service/                (Business Logic)
│   │   └── ServiceSortLogic.java
│   │
│   └── main/                   (User Interface & Entry Point)
│       └── SimpleDialogGUI.java
│
└── README.txt                  (This file)

============================================================================================================================================================
4. PREREQUISITES
============================================================================================================================================================
a. Java Development Kit (JDK): Version 8 or higher (Recommended: JDK 17).
b. IDE (Optional): IntelliJ IDEA, Eclipse, or VS Code.

============================================================================================================================================================
5. HOW TO RUN
============================================================================================================================================================

<<<<<<<<--- OPTION 1: Run in IntelliJ IDEA --->>>>>>>>

1. Open the project folder in IntelliJ IDEA or any IDE.
2. Navigate to: src/main/SimpleDialogGUI.java
3. Right-click the file and select "Run 'SimpleDialogGUI.main()'".
4. Follow the GUI prompts to use the application.


<<<<<<<<--- OPTION 2: Command Line (Terminal) --->>>>>>>>>

Step 1: Compile the Code
Navigate to the "src" directory and compile all packages to the "out" folder.

   > cd src
   > javac -d ../out model/*.java service/*.java main/*.java

Step 2: Run the Application
Execute the program from the "out" directory.

   > java -cp ../out main.SimpleDialogGUI

============================================================================================================================================================
6. DESIGN & OOP PRINCIPLES
============================================================================================================================================================
This project demonstrates key Computer Science concepts:

a. Encapsulation
  All fields in RecyclingCentre and Location are private and accessed via 
  getters to protect data state.

b. Inheritance
  'EcoRecyclingCentre' extends 'RecyclingCentre' to inherit common properties 
  (name, location) while adding specialized behavior.

c. Polymorphism
  The 'acceptsMaterial()' method is overridden in the Eco subclass to enforce 
  stricter recycling rules transparently.

d. Separation of Concerns
  - 'model' package: Handles data representation.
  - 'service' package: Handles sorting, searching, and logic.
  - 'main' package: Handles user interaction.

==========================================================================================================================================================
APPENDIX A: GLOSSARY OF TECHNICAL TERMS
==========================================================================================================================================================
1. EUCLIDEAN GEOMETRY is a mathematical system attributed to the Greek mathematician Euclid. In the context of Computer Science and this application, it 
refers to the method used to calculate the straight-line distance between two points on a 2-dimensional plane (flat surface).

Application in EcoFind:
We treat the map as a grid where every location has an X (horizontal) and Y (vertical) coordinate. The distance between the User (x1, y1) and a Recycling 
Centre (x2, y2) is calculated using the Pythagorean theorem formula:

    Distance = √((x2 - x1)² + (y2 - y1)²)

This assumes a flat surface ("as the crow flies") rather than measuring travel distance along curved roads.

2. MVC-STYLE (Model-View-Controller) is a software design pattern or framework that separates an application into three interconnected components to separate 
internal representations of information from the ways information is presented to and accepted from the user.

Application in EcoFind:
While simplified, our project strictly follows this separation of concerns:
    a. MODEL (The 'src/model' package) manages the data and rules of the application (e.g., RecyclingCentre, Material). It knows nothing about the user interface.
    b. VIEW (The 'src/main' package) is the User Interface (Console or GUI) that displays data to the user and sends user commands to the Service.
    c. CONTROLLER/SERVICE (The 'src/service' package) acts as the brain/bridge. It takes input from the View, processes it (sorting, searching) using the Model, 
    and returns the results.

Benefits:
This structure allows us to change the User Interface (e.g., swapping a console for a window) without having to rewrite the core math or data logic.

============================================================================================================================================================
                                                    Created for CS 213 Object Oriented Programming, Fall 2025 by MCYS
============================================================================================================================================================