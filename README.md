# InteractiveSorting
**InteractiveSorting** is web-based interactive visualization game that teaches users the procedures of a selected sorting algorithm. Users can select an algorithm and the size of the array before starting. Then they are given an unsorted array to perform sorting operations on (swap, insert, merge, etc.) depending on the selected algorithm. Designed for computer science students like myself and others who want to learn the procedures of sorting algorithms.

## Features
- Interactive visualizations for sorting arrays manually
- Supported Algorithms
    - Bubble Sort
    - Selection Sort
    - Insertion Sort
    - Merge Sort (in progress)
- Feedback on correct/incorrect actions
- Separation of logic between backend and frontend

 ## Technologies Used
**Backend**
- Java 17+
- Spring Boot
- JPA + H2 (in-memory database)
- Maven

**Frontend**
- HTML, CSS, JavaScript
- Basic custom styling and frontend logic (no frameworks yet)

**Tools**
- IntelliJ IDEA
- WebStorm (for frontend editing)
- Git + GitHub for version control

## Project Structure
```
InteractiveSort/
├── backend/
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── HELP.md
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/sortinggame/
│           │       ├── controller/
│           │       │   └── GameController.java #REST Controller
│           │       ├── model/
│           │       │   ├── ActionRequest.java #Data class for JSON 
|           |       |   ├── GameState.java     #Stores the current Game State
|           |       |   ├── StartRequest.java  #Data class for JSON
│           │       ├── repository/
│           │       │   ├── GameStateRepository.java #In Memory database
│           │       ├── service/
│           │       │   ├── GameService.java         #Logic for user requests
|           |       |   ├── SortingValidator.java   #Logic for action validation
│           │       └── SortingGameApplication.java #Runs the Spring Boot Application
│           └── resources/
│               ├── application.properties
├── frontend/
│   ├── public
│   ├── src/             #React frontend
        ├── components/  #React Components
            ├── ActionPanel.jsx
            ├── ArrayDisplay.jsx
            ├── GameSetup.jsx
        ├── App.css
        ├── App.js
        ├── App.test.js
        ├── index.css
        ├── index.js
        ├── logo.svg
        ├── reportWebVitals.js
        ├── setupTests.js
        ├── styles.css
        ├── tree.css        # Tree struture for merge sort 
    └── package.json        # (if using npm)
├── .gitignore
├── .gitattributes
└── README.md
```

## Running App Locally (Deployment to Render coming soon)

### Prerequisites:
- Java 17+
- Maven

### Step 1: Run Backend
- Clone the Repository
- Run cd backend in InteractiveSort path
- Then ./mvnw spring-boot:run

### Step 2: Run Frontend
- Run package.json script
- Open on browser at localhost:3000 or where is specified by npm
- Start the game!

## Future Plans
- Completing Merge Sort and Quick Sort support
- React frontend rewrite
- Animations, increased visual feedback
- Deployment of full stack application to Render
- Time Complexities
