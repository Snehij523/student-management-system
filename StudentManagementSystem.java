import java.util.ArrayList;
import java.util.Scanner;
class Student {
    private int id;
    private String name;
    private double marks;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters and Setters (Encapsulation)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Marks: %.2f", id, name, marks);
    }
}



public class StudentManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;
    private int nextId;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
    }

    public void displayMenu() {
        System.out.println("\n=== STUDENT RECORD MANAGEMENT SYSTEM ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Exit");
        System.out.print("Choose an option (1-6): ");
    }

    // CREATE - Add new student
    public void addStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        double marks;
        while (true) {
            System.out.print("Enter student marks: ");
            try {
                marks = Double.parseDouble(scanner.nextLine());
                if (marks < 0 || marks > 100) {
                    System.out.println("Please enter marks between 0 and 100.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number for marks.");
            }
        }

        Student student = new Student(nextId++, name, marks);
        students.add(student);
        System.out.println("Student added successfully!");
        System.out.println("Generated Student ID: " + student.getId());
    }

    // READ - View all students
    public void viewAllStudents() {
        System.out.println("\n--- ALL STUDENTS ---");

        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.println("Total Students: " + students.size());
        System.out.println("----------------------------------------");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // UPDATE - Update student information
    public void updateStudent() {
        System.out.println("\n--- UPDATE STUDENT ---");

        if (students.isEmpty()) {
            System.out.println("No students found to update.");
            return;
        }

        System.out.print("Enter student ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            Student studentToUpdate = findStudentById(id);
            if (studentToUpdate == null) {
                System.out.println("Student with ID " + id + " not found.");
                return;
            }

            System.out.println("Current details: " + studentToUpdate);

            System.out.print("Enter new name (press enter to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                studentToUpdate.setName(newName);
            }

            System.out.print("Enter new marks (press enter to keep current): ");
            String marksInput = scanner.nextLine();
            if (!marksInput.trim().isEmpty()) {
                try {
                    double newMarks = Double.parseDouble(marksInput);
                    if (newMarks >= 0 && newMarks <= 100) {
                        studentToUpdate.setMarks(newMarks);
                    } else {
                        System.out.println("Marks must be between 0 and 100. Keeping current marks.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid marks format. Keeping current marks.");
                }
            }

            System.out.println("Student updated successfully!");
            System.out.println("Updated details: " + studentToUpdate);

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format! Please enter a valid number.");
        }
    }

    // DELETE - Remove student
    public void deleteStudent() {
        System.out.println("\n--- DELETE STUDENT ---");

        if (students.isEmpty()) {
            System.out.println("No students found to delete.");
            return;
        }

        System.out.print("Enter student ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            Student studentToDelete = findStudentById(id);
            if (studentToDelete == null) {
                System.out.println("Student with ID " + id + " not found.");
                return;
            }

            System.out.println("Student to delete: " + studentToDelete);
            System.out.print("Are you sure you want to delete this student? (y/n): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                students.remove(studentToDelete);
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format! Please enter a valid number.");
        }
    }

    // SEARCH - Find student by ID
    public void searchStudentById() {
        System.out.println("\n--- SEARCH STUDENT ---");

        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.print("Enter student ID to search: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            Student student = findStudentById(id);
            if (student != null) {
                System.out.println("Student found:");
                System.out.println(student);
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format! Please enter a valid number.");
        }
    }

    // Helper method to find student by ID
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Main method to run the application
    public void run() {
        System.out.println("Welcome to Student Record Management System!");

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    viewAllStudents();
                    break;
                case "3":
                    updateStudent();
                    break;
                case "4":
                    deleteStudent();
                    break;
                case "5":
                    searchStudentById();
                    break;
                case "6":
                    System.out.println("Thank you for using Student Record Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please choose between 1-6.");
            }
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }
}
