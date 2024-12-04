import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public Student findStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void editStudent(String id, String newName, double newScore) {
        Student student = findStudent(id);
        if (student != null) {
            Student updatedStudent = new Student(id, newName, newScore);
            removeStudent(id);
            addStudent(updatedStudent);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    // Sắp xếp danh sách sinh viên theo điểm (Quick Sort)
    public void sortByScoreQuickSort() {
        quickSort(0, students.size() - 1);
        System.out.println("Students sorted by score (Quick Sort):");
        displayStudents();
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = students.get(high).getScore(); // Điểm chuẩn (pivot)
        int i = low - 1; // Vị trí của phần tử nhỏ hơn pivot
        for (int j = low; j < high; j++) {
            if (students.get(j).getScore() <= pivot) {
                i++;
                // Đổi chỗ các sinh viên
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        // Đổi chỗ pivot với phần tử đúng vị trí
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }
}
