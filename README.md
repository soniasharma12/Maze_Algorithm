# Maze_Algorithm

## Overview 
#### This project is an implementation of a maze-solving algorithm in Java. The program reads a maze from an input file, represents it as an undirected graph, and finds a path from the entrance to the exit, considering the constraints of opening doors with limited coins

## Tools Used 
- Java: The primary programming language used for the implementation
- Java I/O: For reading input files and handling exceptions
- Java Collections Framework: Used for storing and managing graph data structures (e.g., ArrayList, Stack, and Iterator)

## Algorithm 
#### The maze-solving algorithm utilizes a modified Depth-First Search (DFS). This method traverses the graph while monitoring the number of coins spent on opening doors. If a path exhausts the available coins, the algorithm backtracks and explores alternative paths. The essential steps are:
- Parse the input file to build the graph.
- Implement DFS to discover a path from the entrance to the exit, considering the coin constraint.
- Track the current path and employ backtracking when necessary.

## Concepts 
- Graph Theory: The maze is depicted as an undirected graph with nodes representing rooms and edges representing corridors or doors
- Depth-First Search (DFS): A traversal technique used to explore paths within the maze.
- Backtracking: A method to find alternative paths when the current path is not viable due to constraints
- Exception Handling: Effective management of file I/O and custom exceptions to handle errors gracefully.
