Student Grade Tracker

Project Overview

This project was developed for CS300 at Southeast Missouri State University in Spring 2025. It is a Java-based application that manages student records, calculates average grades, and displays students in sorted order.

Project Objectives

- Store and retrieve student records efficiently.
- Add, remove, and update student grades.
- Identify the top three students.
- Display students sorted by their average grades.

Data Structures and Algorithms

| Data Structure / Algorithm | Purpose |
|---|---|
| HashMap | Store and retrieve student records |
| ArrayList | Store individual assignment grades |
| Binary Search Tree | Organize students by average grade |
| In-order Traversal | Display students in ascending order |
| Sorting | Identify the top three students |

Algorithm Complexity

- HashMap lookup: O(1) on average.
- Binary search tree insertion: O(log n) when balanced, O(n) in the worst case.
- In-order traversal: O(n).
- Sorting student averages: O(n log n).

Algorithmic Challenge

One challenge in this project was maintaining accurate rankings when student grades changed. The implemented solution recalculates student averages and rebuilds the binary search tree whenever sorted results are requested.

Limitations and Future Improvements

Although rebuilding the tree maintains accurate results, it can become inefficient as the number of students increases. The binary search tree can also become unbalanced depending on the insertion order.

Possible improvements include:

- Using a self-balancing binary search tree, such as an AVL tree.
- Updating individual records instead of rebuilding the entire tree.
- Maintaining running averages to reduce repeated calculations.
- Comparing algorithm performance with different input sizes.

These are proposed improvements, not features implemented in the original project.

How to Run

Compile and run the application using:

`javac Main.java`

`java Main`

Project Contributors

- Samia Zaman Esha
- Md Fahad Imam Akash

Academic Information

- Course: CS300
- Semester: Spring 2025
- University: Southeast Missouri State University
- Programming Language: Java
