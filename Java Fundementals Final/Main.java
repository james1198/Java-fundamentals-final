import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static StudentManager manager = new StudentManager();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
    }

    // Main menu (recursive)
    private static void showMenu() {

        System.out.println("\n===== Student Records Manager =====");
        System.out.println("1. Add Student");
        System.out.println("2. List All Students");
        System.out.println("3. Search by ID");
        System.out.println("4. Search by Last Name");
        System.out.println("5. Update Student");
        System.out.println("6. Remove Student");
        System.out.println("7. Sort by ID");
        System.out.println("8. Sort by Last Name");
        System.out.println("9. View Statistics");
        System.out.println("10. Quit");
        System.out.print("Select an option: ");

        int choice = getValidChoice();

        switch (choice) {
            case 1:
                addStudent();
                break;

            case 2:
                listStudents();
                break;

            case 3:
                searchById();
                break;

            case 4:
                searchByLastName();
                break;

            case 5:
                updateStudent();
                break;

            case 6:
                removeStudent();
                break;

            case 7:
                manager.sortStudentsById();
                break;

            case 8:
                manager.sortStudentsByLastName();
                break;

            case 9:
                showStatistics();
                break;

            case 10:
                System.out.println("Program terminated.");
                return;

            default:
                System.out.println("Invalid option.");
        }

        showMenu(); // call menu again
    }

    // Validate menu choice
    private static int getValidChoice() {

        while (true) {

            if (input.hasNextInt()) {

                int choice = input.nextInt();

                if (choice >= 1 && choice <= 10) {
                    return choice;
                }

                System.out.print("Please enter a number between 1 and 10: ");

            } else {

                System.out.print("Invalid input. Enter a number: ");
                input.next();
            }
        }
    }

    // Add student
    private static void addStudent() {

        System.out.print("Student ID: ");
        String id = input.next();

        System.out.print("First Name: ");
        String firstName = input.next();

        System.out.print("Last Name: ");
        String lastName = input.next();

        System.out.print("GPA: ");
        double gpa = input.nextDouble();

        Student s = new Student(id, firstName, lastName, gpa);

        if (manager.addStudent(s)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with that ID already exists.");
        }
    }

    // List all students
    private static void listStudents() {

        manager.displayStudents();
    }

    // Search by student ID
    private static void searchById() {

        System.out.print("Enter Student ID: ");
        String id = input.next();

        Student s = manager.findStudentById(id);

        if (s != null) {
            System.out.println("\n" + s);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search by last name
    private static void searchByLastName() {

        System.out.print("Enter Last Name: ");
        String name = input.next();

        ArrayList<Student> results = manager.findStudentsByLastName(name);

        if (results.isEmpty()) {

            System.out.println("No students found.");

        } else {

            System.out.println("\nSearch Results:");

            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }

    // Update student
    private static void updateStudent() {

        System.out.print("Student ID: ");
        String id = input.next();

        System.out.print("New First Name: ");
        String firstName = input.next();

        System.out.print("New Last Name: ");
        String lastName = input.next();

        System.out.print("New GPA: ");
        double gpa = input.nextDouble();

        if (manager.editStudent(id, firstName, lastName, gpa)) {
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Remove student
    private static void removeStudent() {

        System.out.print("Enter Student ID: ");
        String id = input.next();

        if (manager.deleteStudent(id)) {
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Show statistics
    private static void showStatistics() {

        double avg = manager.calculateAverageGpa();
        double highest = manager.findHighestGpa();

        System.out.println("\n===== Statistics =====");
        System.out.println("Average GPA: " + String.format("%.2f", avg));
        System.out.println("Highest GPA: " + String.format("%.2f", highest));
    }
}