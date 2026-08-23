# Emergency Ambulance Routing System

A Java-based application that optimizes emergency response by calculating the shortest route between patients, ambulances, and hospitals using graph algorithms.

## Overview

The system models patients, ambulances, and hospitals as nodes in a graph, with routes between them as weighted edges. Given a patient's location, it calculates the optimal path to route the nearest available ambulance and, subsequently, the nearest suitable hospital — with the goal of minimizing emergency response time.

## Core Logic

- **Graph representation** of locations (patients, ambulances, hospitals) and the routes connecting them
- **Dijkstra's algorithm** to compute the shortest path between nodes
- **Dispatch logic** to assign the nearest available ambulance to an incoming patient request
- **Hospital routing** to direct the ambulance to the nearest appropriate hospital after pickup

## Tech Stack

- **Language:** Java
- **Core concepts:** Object-Oriented Programming, Graph Algorithms, Data Structures
- **Database:** SQL (for storing hospital/ambulance records)

## Project Structure

```
AmbulanceRoute/
├── Main.java                  # Entry point
├── Graph.java                 # Graph representation and Dijkstra's algorithm
├── Ambulance.java              # Ambulance entity and status
├── AmbulanceService.java       # Ambulance assignment logic
├── Hospital.java                # Hospital entity
├── HospitalService.java         # Hospital lookup/routing logic
├── DispatchService.java         # Core dispatch orchestration
└── Sql_project                  # Database setup/queries
```

## How to Run Locally

1. Clone the repository
   ```bash
   git clone https://github.com/rohan2531/AmbulanceRoute.git
   cd AmbulanceRoute
   ```

2. Compile the Java files
   ```bash
   javac *.java
   ```

3. Run the application
   ```bash
   java Main
   ```

## What I Learned

This project was built to apply data structures and graph algorithms (specifically Dijkstra's shortest path) to a real-world routing problem, reinforcing core OOP design by modeling the system as separate entity and service classes.

## Author

Rohan Bansode — [GitHub](https://github.com/rohan2531) · [LinkedIn](https://www.linkedin.com/in/rohan-bansode-1ba07627b)
