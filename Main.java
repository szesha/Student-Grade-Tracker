//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    private static final Map<String, List<Integer>> studentRecords = new HashMap<>();
    private static final Scanner input = new Scanner(System.in);
    private static final StudentBST studentBST = new StudentBST();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int selection = getIntInput("Select an option: ");

            switch (selection) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> addGrade();
                case 4 -> updateGrade();
                case 5 -> showGrades();
                case 6 -> viewTopStudents();
                case 7 -> {
                    System.out.println("Exiting... Have a great day!");
                    return;
                }
                case 8 -> viewAllSortedByAverage();
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Student Grade Tracker ===");
        System.out.println("1. Add a Student");
        System.out.println("2. Remove a Student");
        System.out.println("3. Add Assignment Grade");
        System.out.println("4. Update Assignment Grade");
        System.out.println("5. View Student Grades");
        System.out.println("6. View Top Students");
        System.out.println("7. Exit");
        System.out.println("8. View All Students Sorted by Average Grade");
    }

    private static void addStudent() {
        String name = getStringInput("Enter the student's name: ");
        if (name.isEmpty()) {
            System.out.println("⚠ Name cannot be empty.");
            return;
        }
        if (studentRecords.containsKey(name)) {
            System.out.println("⚠ Student already exists.");
            return;
        }
        studentRecords.put(name, new ArrayList<>());
        System.out.println("Student added successfully.");
    }

    private static void removeStudent() {
        String name = getStringInput("Enter the student's name to remove: ");
        if (studentRecords.remove(name) != null) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("⚠ Student not found.");
        }
    }

    private static void addGrade() {
        String name = getStringInput("Enter the student's name: ");
        if (!studentRecords.containsKey(name)) {
            System.out.println("⚠ Student not found.");
            return;
        }

        int grade = getIntInput("Enter the grade (0 to 100): ");
        if (grade < 0 || grade > 100) {
            System.out.println("⚠ Grade must be between 0 and 100.");
            return;
        }

        studentRecords.get(name).add(grade);
        System.out.println("Grade added successfully.");
    }

    private static void updateGrade() {
        String name = getStringInput("Enter the student's name: ");
        List<Integer> grades = studentRecords.get(name);
        if (grades == null || grades.isEmpty()) {
            System.out.println("⚠ No grades found for this student.");
            return;
        }

        System.out.println("Current grades: " + grades);
        int index = getIntInput("Enter the grade index to update (starting from 0): ");
        if (index < 0 || index >= grades.size()) {
            System.out.println("⚠ Invalid index.");
            return;
        }

        int newGrade = getIntInput("Enter the new grade: ");
        if (newGrade < 0 || newGrade > 100) {
            System.out.println("⚠ Grade must be between 0 and 100.");
            return;
        }

        grades.set(index, newGrade);
        System.out.println("Grade updated successfully.");
    }

    private static void showGrades() {
        String name = getStringInput("Enter the student's name: ");
        List<Integer> grades = studentRecords.get(name);
        if (grades != null && !grades.isEmpty()) {
            System.out.println(capitalize(name) + "'s grades: " + grades);
        } else {
            System.out.println("⚠ No grades found for this student.");
        }
    }

    private static void viewTopStudents() {
        studentRecords.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0.0)))
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(3)
                .forEach(entry -> System.out.printf(" %s: %.2f%n", capitalize(entry.getKey()), entry.getValue()));
    }

    private static void viewAllSortedByAverage() {
        studentBST.clear();
        for (Map.Entry<String, List<Integer>> entry : studentRecords.entrySet()) {
            List<Integer> grades = entry.getValue();
            if (!grades.isEmpty()) {
                double avg = grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                studentBST.insert(entry.getKey(), avg);
            }
        }

        System.out.println("=== Students Sorted by Average Grade ===");
        studentBST.inOrderTraversal();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid input. Please enter a valid number.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim().toLowerCase(); // Normalize for case-insensitive handling
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
class StudentBST {
    private StudentNode root;

    public void insert(String name, double average) {
        root = insertRecursive(root, name, average);
    }

    private StudentNode insertRecursive(StudentNode node, String name, double average) {
        if (node == null) return new StudentNode(name, average);
        if (average < node.average) {
            node.left = insertRecursive(node.left, name, average);
        } else {
            node.right = insertRecursive(node.right, name, average);
        }
        return node;
    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(StudentNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.printf(" %s: %.2f%n", capitalize(node.name), node.average);
            inOrder(node.right);
        }
    }

    public void clear() {
        root = null;
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static class StudentNode {
        String name;
        double average;
        StudentNode left, right;

        StudentNode(String name, double average) {
            this.name = name;
            this.average = average;
        }
    }
}
