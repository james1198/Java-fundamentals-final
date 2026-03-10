import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
    }

    public boolean addStudent(Student s) {
        if (findStudentById(s.getStudentId()) != null) {
            return false;
        }
        studentList.add(s);
        return true;
    }

    public void displayStudents() {
        if (studentList.size() == 0) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n=== Student List ===");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println((i + 1) + ". " + studentList.get(i));
        }
    }

    public Student findStudentById(String id) {
        for (Student s : studentList) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Student> findStudentsByLastName(String lname) {
        ArrayList<Student> matches = new ArrayList<>();

        for (Student s : studentList) {
            if (s.getLastName().equalsIgnoreCase(lname)) {
                matches.add(s);
            }
        }

        return matches;
    }

    public boolean editStudent(String id, String first, String last, double gpa) {
        Student s = findStudentById(id);

        if (s == null) {
            return false;
        }

        s.setFirstName(first);
        s.setLastName(last);
        s.setGpa(gpa);
        return true;
    }

    public boolean deleteStudent(String id) {
        Student s = findStudentById(id);

        if (s != null) {
            studentList.remove(s);
            return true;
        }

        return false;
    }

    public void sortStudentsById() {
        if (studentList.isEmpty()) {
            System.out.println("Nothing to sort.");
            return;
        }

        sortIdRecursive(0);
        System.out.println("Students sorted by ID.");
    }

    private void sortIdRecursive(int start) {
        if (start >= studentList.size() - 1) {
            return;
        }

        int smallest = start;

        for (int i = start + 1; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId()
                    .compareTo(studentList.get(smallest).getStudentId()) < 0) {
                smallest = i;
            }
        }

        if (smallest != start) {
            Student temp = studentList.get(start);
            studentList.set(start, studentList.get(smallest));
            studentList.set(smallest, temp);
        }

        sortIdRecursive(start + 1);
    }

    public void sortStudentsByLastName() {
        if (studentList.isEmpty()) {
            System.out.println("Nothing to sort.");
            return;
        }

        sortLastNameRecursive(0);
        System.out.println("Students sorted by last name.");
    }

    private void sortLastNameRecursive(int start) {
        if (start >= studentList.size() - 1) {
            return;
        }

        int smallest = start;

        for (int i = start + 1; i < studentList.size(); i++) {
            if (studentList.get(i).getLastName()
                    .compareTo(studentList.get(smallest).getLastName()) < 0) {
                smallest = i;
            }
        }

        if (smallest != start) {
            Student temp = studentList.get(start);
            studentList.set(start, studentList.get(smallest));
            studentList.set(smallest, temp);
        }

        sortLastNameRecursive(start + 1);
    }

    public double calculateAverageGpa() {
        if (studentList.isEmpty()) {
            return 0;
        }

        double total = sumGpaRecursive(0);
        return total / studentList.size();
    }

    private double sumGpaRecursive(int index) {
        if (index >= studentList.size()) {
            return 0;
        }

        return studentList.get(index).getGpa() + sumGpaRecursive(index + 1);
    }

    public double findHighestGpa() {
        if (studentList.isEmpty()) {
            return 0;
        }

        return highestGpaRecursive(0, studentList.get(0).getGpa());
    }

    private double highestGpaRecursive(int index, double currentMax) {
        if (index >= studentList.size()) {
            return currentMax;
        }

        double gpa = studentList.get(index).getGpa();

        if (gpa > currentMax) {
            currentMax = gpa;
        }

        return highestGpaRecursive(index + 1, currentMax);
    }

    public int countStudentsAbove(double threshold) {
        if (studentList.isEmpty()) {
            return 0;
        }

        return countRecursive(0, threshold);
    }

    private int countRecursive(int index, double threshold) {
        if (index >= studentList.size()) {
            return 0;
        }

        int count = 0;
        if (studentList.get(index).getGpa() >= threshold) {
            count = 1;
        }

        return count + countRecursive(index + 1, threshold);
    }

    public int getStudentCount() {
        return studentList.size();
    }
}