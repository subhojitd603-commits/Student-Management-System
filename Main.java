package com.bca.sms;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        while (true) {
            showMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> displayStudents();
                    case 3 -> searchStudent();
                    case 4 -> updateStudent();
                    case 5 -> deleteStudent();
                    case 6 -> {
                        System.out.println("Application closed.");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                System.out.println("Check that MySQL is running and the database settings are correct.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
    }

    private static void addStudent() throws SQLException {
        Student student = readStudent(0);
        System.out.println(dao.addStudent(student) ? "Student added successfully." : "Student was not added.");
    }

    private static void displayStudents() throws SQLException {
        List<Student> students = dao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        printStudents(students);
    }

    private static void searchStudent() throws SQLException {
        int id = readInt("Enter student ID: ");
        Student student = dao.getStudentById(id);
        if (student == null) System.out.println("Student not found.");
        else printStudent(student);
    }

    private static void updateStudent() throws SQLException {
        int id = readInt("Enter student ID to update: ");
        if (dao.getStudentById(id) == null) {
            System.out.println("Student not found.");
            return;
        }
        Student updated = readStudent(id);
        System.out.println(dao.updateStudent(updated) ? "Student updated successfully." : "Update failed.");
    }

    private static void deleteStudent() throws SQLException {
        int id = readInt("Enter student ID to delete: ");
        System.out.println(dao.deleteStudent(id) ? "Student deleted successfully." : "Student not found.");
    }

    private static Student readStudent(int id) {
        String name = readText("Name: ");
        String department = readText("Department: ");
        int semester = readInt("Semester (1-8): ");
        while (semester < 1 || semester > 8) semester = readInt("Enter semester from 1 to 8: ");
        String email = readText("Email: ");
        double s1 = readMarks("Subject 1 marks: ");
        double s2 = readMarks("Subject 2 marks: ");
        double s3 = readMarks("Subject 3 marks: ");
        return new Student(id, name, department, semester, email, s1, s2, s3);
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double marks = Double.parseDouble(input.nextLine().trim());
                if (marks >= 0 && marks <= 100) return marks;
            } catch (NumberFormatException ignored) {
                // Show the same validation message below.
            }
            System.out.println("Marks must be a number from 0 to 100.");
        }
    }

    private static void printStudents(List<Student> students) {
        System.out.printf("%-5s %-20s %-15s %-8s %-25s %-8s %-8s %-8s %-8s %-8s%n",
                "ID", "Name", "Department", "Sem", "Email", "Total", "Average", "Grade", "S1", "S2/S3");
        for (Student s : students) printStudent(s);
    }

    private static void printStudent(Student s) {
        System.out.printf("ID: %d | Name: %s | Department: %s | Semester: %d | Email: %s%n",
                s.getId(), s.getName(), s.getDepartment(), s.getSemester(), s.getEmail());
        System.out.printf("Marks: %.1f, %.1f, %.1f | Total: %.1f/300 | Average: %.2f | Grade: %s%n",
                s.getSubject1(), s.getSubject2(), s.getSubject3(),
                s.getTotal(), s.getAverage(), s.getGrade());
    }
}
