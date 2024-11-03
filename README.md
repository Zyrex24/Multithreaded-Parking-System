# Multithreaded Parking System Simulation

A Java-based parking system simulation that handles concurrent car arrivals and departures through multiple gates using thread synchronization and semaphores.

## Project Description

This project simulates a parking lot system with the following specifications:
- 4 available parking spots
- 3 entrance gates
- Concurrent car arrivals and departures
- Thread synchronization using semaphores
- Real-time parking status tracking

## Features

- Multiple gate management
- Thread-safe parking operations
- Real-time status updates
- Comprehensive logging
- Statistics generation
- File-based input system

## Prerequisites

- Java JDK 11 or higher
- Git (for version control)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── classes/
│   │   │   ├── Car.java
│   │   │   ├── Gate.java
│   │   │   └── Parking.java
│   │   ├── io/
│   │   │   ├── FileReader.java
│   │   │   └── Logger.java
│   │   │   └── InputReceiver.java
│   │   └── Main.java
│   └── resources/
└──│       └── input.txt

```

## Getting Started

1. Clone the repository:
```bash
git clone --single-branch --branch master https://github.com/Zyrex24/Multithreaded-Parking-System-Simulation.git
cd Multithreaded-Parking-System-Simulation
```

2. Compile the project:
```bash
javac src/main/java/Main.java
```

3. Run the simulation:
```bash
java -cp src/main/java Main
```

## Input File Format

The system expects an input file (`input.txt`) in the following format:
```
Gate 1, Car 0, Arrive 0, Parks 3
Gate 1, Car 1, Arrive 1, Parks 4
...
```

## Output Format

The system generates output in the following format:
```
Car 0 from Gate 1 arrived at time 0
Car 0 from Gate 1 parked. (Parking Status: 1 spots occupied)
...
```

## Contributors

- Team Member 1: Core Threading Implementation
- Team Member 2: Synchronization Management
- Team Member 3: I/O Operations
- Team Member 4: Integration & Statistics

## License

This project is licensed under the Apache License - see the LICENSE file for details.
