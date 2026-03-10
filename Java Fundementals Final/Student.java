public class Student {

    private String studentId;
    private String firstName;
    private String lastName;
    private double gpa;

    // Constructor
    public Student(String id, String first, String last, double gpaValue) {
        studentId = id;
        firstName = first;
        lastName = last;
        gpa = gpaValue;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters
    public void setStudentId(String id) {
        studentId = id;
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    public void setLastName(String last) {
        lastName = last;
    }

    public void setGpa(double gpaValue) {
        gpa = gpaValue;
    }

    // Display student information
    @Override
    public String toString() {
        return "Student ID: " + studentId +
               "\nFirst Name: " + firstName +
               "\nLast Name: " + lastName +
               "\nGPA: " + gpa + "\n";
    }
}